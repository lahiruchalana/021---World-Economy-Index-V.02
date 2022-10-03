package com.myeconomy.worldeconomyindex.controller;

import com.myeconomy.worldeconomyindex.model.Country;
import com.myeconomy.worldeconomyindex.service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*"
)
@RequestMapping(path = "world-economy-index/api/data/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<?> addNewCountry(
            @RequestBody Country country
            ) {
        countryService.addNewCountry(country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        return new ResponseEntity<>(countryService.getCountries(), HttpStatus.OK);
    }

    @GetMapping(path = "countries/{countryName}")
    public ResponseEntity<Optional<Country>> getCountries(
            @PathVariable("countryName") String countryName
    ) {
        return new ResponseEntity<>(countryService.getCountriesByName(countryName), HttpStatus.OK);
    }

    @GetMapping(path = "countries-pagination")
    public ResponseEntity<Page<Country>> getCountries(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize
    ) {
        return new ResponseEntity<>(countryService.getCountriesWithPagination(pageNumber, pageSize), HttpStatus.OK);
    }

    @DeleteMapping(path = "countries/{countryId}")
    public ResponseEntity<?> deleteCountry(
            @PathVariable("countryId") Long countryId
    ) {
        countryService.deleteCountry(countryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "countries/{countryID}")
    public ResponseEntity<?> updateCountry(
            @PathVariable("countryID") Long countryId,
            @RequestBody Country country
    ) {
        countryService.updateCountry(countryId, country.getCountryName(), country.getContinentName(), country.getSubContinentName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
