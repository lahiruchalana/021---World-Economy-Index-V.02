package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.exceptions.DataExistingException;
import com.myeconomy.worldeconomyindex.model.Currency;
import com.myeconomy.worldeconomyindex.model.CurrencyRate;
import com.myeconomy.worldeconomyindex.repository.CurrencyRateRepository;
import com.myeconomy.worldeconomyindex.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;
    private final CurrencyRepository currencyRepository;

    public CurrencyRateService(CurrencyRateRepository currencyRateRepository, CurrencyRepository currencyRepository) {
        this.currencyRateRepository = currencyRateRepository;
        this.currencyRepository = currencyRepository;
    }

    public void addNewCurrencyRateData(CurrencyRate currencyRate) {
        if(currencyRate.getCurrency().getCurrencyId().equals(currencyRate.getEqualsCurrency().getCurrencyId())) {
            throw new IllegalStateException("adding currencyRate currencyID : " + currencyRate.getCurrency().getCurrencyId() + " and equalsCurrencyId : " + currencyRate.getEqualsCurrency().getCurrencyId() + " are same.");
        }

        Optional<CurrencyRate> optionalCurrencyRate = currencyRateRepository.getCurrencyRatesByYearAndMonthAndDayAndCurrencyCurrencyIdAndEqualsCurrencyCurrencyId(currencyRate.getYear(), currencyRate.getMonth(), currencyRate.getDay(), currencyRate.getCurrency().getCurrencyId(), currencyRate.getEqualsCurrency().getCurrencyId());

        if (optionalCurrencyRate.isPresent()) {
            throw new DataExistingException("Data exist for relevant date");
        }

        currencyRateRepository.save(currencyRate);
    }

    public List<CurrencyRate> getAllCurrencyRateData() {
        return currencyRateRepository.findAll();
    }

}
