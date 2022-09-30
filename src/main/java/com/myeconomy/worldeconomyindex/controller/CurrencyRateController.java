package com.myeconomy.worldeconomyindex.controller;

import com.myeconomy.worldeconomyindex.model.CurrencyRate;
import com.myeconomy.worldeconomyindex.service.CurrencyRateService;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<?> addNewCurrencyRate(
            @RequestBody CurrencyRate currencyRate
    ) {
        currencyRateService.addNewCurrencyRate(currencyRate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CurrencyRate>> getCurrencyRates() {
        return new ResponseEntity<>(currencyRateService.getCurrencyRates(), HttpStatus.OK);
    }

    @PutMapping(path = "currencies/{currencyId}/currencyRates/{currencyRateId}")
    public ResponseEntity<?> updateCurrencyRate(
            @PathVariable("currencyRateId") Long currencyRateId,
            @PathVariable("currencyId") Long currencyId,
            @RequestBody CurrencyRate currencyRate
    ) {
        currencyRateService.updateCurrencyRate(currencyRateId, currencyId, currencyRate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "currencies/{currencyName}/allCurrencyRates")
    public ResponseEntity<List<CurrencyRate>> getCurrencyRatesByCurrencyAndEqualsCurrency(
            @PathVariable("currencyName") String currencyName,
            @RequestParam(required = false) String equalsCurrencyName,
            @RequestParam(required = false) String sortingProperty,
            @RequestParam(required = false) String order
    ) {
        return new ResponseEntity<>(currencyRateService.getCurrencyRatesByCurrencyAndEqualsCurrency(currencyName, equalsCurrencyName, sortingProperty, order), HttpStatus.OK);
    }

    @DeleteMapping(path = "currencyRates/{currencyRateId}")
    public ResponseEntity<?> deleteCurrencyRate(
            @PathVariable("currencyRateId") Long currencyRateId
    ) {
        currencyRateService.deleteCurrencyRate(currencyRateId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "currencies/{currencyName}/paginationAllCurrencyRates")
    public ResponseEntity<Page<CurrencyRate>> getCurrencyRatesWithPagination(
            @PathVariable("currencyName") String currencyName,
            @RequestParam(required = false) String equalsCurrencyName,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortingProperty,
            @RequestParam(required = false) String order
    ) {
        return new ResponseEntity<>(currencyRateService.getCurrencyRatesWithPagination(currencyName, equalsCurrencyName, pageNumber,pageSize ,sortingProperty, order), HttpStatus.OK);
    }
}
