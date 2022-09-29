package com.myeconomy.worldeconomyindex.controller;

import com.myeconomy.worldeconomyindex.model.Country;
import com.myeconomy.worldeconomyindex.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
