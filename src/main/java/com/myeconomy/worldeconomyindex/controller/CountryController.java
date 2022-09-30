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
    public ResponseEntity<?> deleteCountry(
            @PathVariable("countryId") Long countryId
    ) {
        countryService.deleteCountry(countryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "countries/{countryID}")
    public ResponseEntity<?> updateCountry(
            @PathVariable("countryID") Long countryId,
            @RequestParam(required = false) String countryName,
            @RequestParam(required = false) String continentName,
            @RequestParam(required = false) String subContinentName
    ) {
        countryService.updateCountry(countryId, countryName, continentName, subContinentName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
