package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.exceptions.DataExistingException;
import com.myeconomy.worldeconomyindex.model.Country;
import com.myeconomy.worldeconomyindex.repository.CountryRepository;
import org.springframework.stereotype.Service;

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
}
