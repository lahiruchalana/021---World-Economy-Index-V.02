package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.exceptions.DataExistingException;
import com.myeconomy.worldeconomyindex.exceptions.NoDataAvailable;
import com.myeconomy.worldeconomyindex.model.Country;
import com.myeconomy.worldeconomyindex.repository.CountryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void addNewCountry(Country country) {
        Optional<Country> countryOptional = countryRepository.findCountryByCountryName(country.getCountryName());

        if (countryOptional.isPresent()) {
            throw new DataExistingException("Country already exists!!!");
        }

        countryRepository.save(country);
    }

    public List<Country> getCountries() {
        List<Country> countryList = countryRepository.findAll();

        if (countryList.isEmpty()) {
            throw new NoDataAvailable("No available country data");
        }

        return countryList;
    }

    public void deleteCountry(Long countryId) {
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new NoDataAvailable("CountryId not available to delete"));

        countryRepository.delete(country);
    }

    @Transactional
    public void updateCountry(Long countryId, String countryName, String continentName, String subContinentName) {
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new NoDataAvailable("CountryId not available to update"));

        Optional<Country> countryOptional = countryRepository.findCountryByCountryName(countryName);

        if (countryOptional.isPresent()) {
            throw new DataExistingException("CountryName already exists");
        }

        country.setCountryName(countryName);
        country.setContinentName(continentName);
        country.setSubContinentName(subContinentName);
    }
}
