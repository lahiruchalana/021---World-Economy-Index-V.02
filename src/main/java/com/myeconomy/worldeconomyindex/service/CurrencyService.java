package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.exceptions.DataExistingException;
import com.myeconomy.worldeconomyindex.model.Currency;
import com.myeconomy.worldeconomyindex.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void addNewCurrency(Currency currency) {
        Optional<Currency> currencyOptional = currencyRepository.findCurrencyByCurrencyName(currency.getCurrencyName());

        if (currencyOptional.isPresent()) {
            throw new DataExistingException("CurrencyName already exists!!!");
        }

        currencyRepository.save(currency);
    }

    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }


}
