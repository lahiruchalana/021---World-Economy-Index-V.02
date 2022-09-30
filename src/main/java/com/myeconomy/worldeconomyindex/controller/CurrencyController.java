package com.myeconomy.worldeconomyindex.controller;

import com.myeconomy.worldeconomyindex.model.Country;
import com.myeconomy.worldeconomyindex.model.Currency;
import com.myeconomy.worldeconomyindex.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*"
)
@RequestMapping(path = "world-economy-index/api/data/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping
    public ResponseEntity<?> addNewCurrency(
            @RequestBody Currency currency
    ) {
        currencyService.addNewCurrency(currency);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Currency>> getCurrencies() {
        return new ResponseEntity<>(currencyService.getCurrencies(), HttpStatus.OK);
    }

    @PutMapping(path = "currencies/{currencyId}")
    public ResponseEntity<?> updateCurrency(
            @PathVariable("currencyId") Long currencyId,
            @RequestBody Currency currency
    ) {
        currencyService.updateCurrency(currencyId, currency);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "currencies/{currencyId}")
    public ResponseEntity<?> deleteCurrency(
            @PathVariable("currencyId") Long currencyId
    ) {
        currencyService.deleteCurrency(currencyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
