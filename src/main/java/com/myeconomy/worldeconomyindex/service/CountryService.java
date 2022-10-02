package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.exceptions.DataExistingException;
import com.myeconomy.worldeconomyindex.exceptions.NoDataAvailableException;
import com.myeconomy.worldeconomyindex.model.Country;
import com.myeconomy.worldeconomyindex.model.CurrencyRate;
import com.myeconomy.worldeconomyindex.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
            throw new NoDataAvailableException("No available country data!!!");
        }

        return countryList;
    }

    public Optional<Country> getCountriesByName(String countryName) {
        Optional<Country> countryList = countryRepository.findCountryByCountryName(countryName);

        if (countryList.isEmpty()) {
            throw new NoDataAvailableException("No available country data!!!");
        }

        return countryList;
    }

    public Page<Country> getCountriesWithPagination(Integer pageNumber, Integer pageSize) {
        Page<Country> countryList = countryRepository.findCountriesByOrderByCountryNameAsc(PageRequest.of(pageNumber, pageSize));

        if (countryList.isEmpty()) {
            throw new NoDataAvailableException("No available country data!!!");
        }

        return countryList;
    }

    public void deleteCountry(Long countryId) {
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new NoDataAvailableException("CountryId does not available to delete!!!"));

        countryRepository.delete(country);
    }

    @Transactional
    public void updateCountry(Long countryId, String countryName, String continentName, String subContinentName) {
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new NoDataAvailableException("CountryId does not available to update!!!"));

        Optional<Country> countryOptional = countryRepository.findCountryByCountryName(countryName);

        if (countryOptional.isPresent() && !countryOptional.get().getCountryId().equals(countryId)) {
            throw new DataExistingException("CountryName already exists!!!");
        }

        country.setCountryName(countryName);
        country.setContinentName(continentName);
        country.setSubContinentName(subContinentName);
    }
}
