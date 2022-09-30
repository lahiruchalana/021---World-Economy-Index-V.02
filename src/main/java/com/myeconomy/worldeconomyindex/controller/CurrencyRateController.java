package com.myeconomy.worldeconomyindex.controller;

import com.myeconomy.worldeconomyindex.model.CurrencyRate;
import com.myeconomy.worldeconomyindex.service.CurrencyRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*"
)
@RequestMapping(path = "world-economy-index/api/data/currencies/rates")
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    public CurrencyRateController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @PostMapping
    public ResponseEntity<?> addNewCurrencyRateData(
            @RequestBody CurrencyRate currencyRate
    ) {
        currencyRateService.addNewCurrencyRateData(currencyRate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CurrencyRate>> getAllCurrencyRateData() {
        return new ResponseEntity<>(currencyRateService.getAllCurrencyRateData(), HttpStatus.OK);
    }
}
