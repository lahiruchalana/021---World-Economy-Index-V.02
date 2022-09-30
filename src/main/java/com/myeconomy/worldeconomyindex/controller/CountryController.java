package com.myeconomy.worldeconomyindex.controller;

import com.myeconomy.worldeconomyindex.model.Country;
import com.myeconomy.worldeconomyindex.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping(path = "countries/{countryId}")
    public ResponseEntity<?> deleteCountryById(
            @PathVariable("countryId") Long countryId
    ) {
        countryService.deleteCountryById(countryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
