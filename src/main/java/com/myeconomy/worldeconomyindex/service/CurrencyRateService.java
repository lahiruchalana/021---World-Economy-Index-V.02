package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.exceptions.DataExistingException;
import com.myeconomy.worldeconomyindex.exceptions.InvalidInputException;
import com.myeconomy.worldeconomyindex.exceptions.NoDataAvailableException;
import com.myeconomy.worldeconomyindex.model.Currency;
import com.myeconomy.worldeconomyindex.model.CurrencyRate;
import com.myeconomy.worldeconomyindex.repository.CurrencyRateRepository;
import com.myeconomy.worldeconomyindex.repository.CurrencyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public void addNewCurrencyRate(CurrencyRate currencyRate) {
        if(currencyRate.getCurrency().getCurrencyId().equals(currencyRate.getEqualsCurrency().getCurrencyId())) {
            throw new InvalidInputException("Currency and EqualsCurrency are same!!!");
        }

        Optional<CurrencyRate> optionalCurrencyRate = currencyRateRepository.getCurrencyRatesByYearAndMonthAndDayAndCurrencyCurrencyIdAndEqualsCurrencyCurrencyId(currencyRate.getYear(), currencyRate.getMonth(), currencyRate.getDay(), currencyRate.getCurrency().getCurrencyId(), currencyRate.getEqualsCurrency().getCurrencyId());

        if (optionalCurrencyRate.isPresent()) {
            throw new DataExistingException("CurrencyRate exists for relevant date!!!");
        }

        currencyRateRepository.save(currencyRate);
    }

    public List<CurrencyRate> getCurrencyRates() {
        return currencyRateRepository.findAll();
    }

    @Transactional
    public void updateCurrencyRate(Long currencyRateId, Long currencyId, CurrencyRate currencyRateNew) {
        if(currencyId.equals(currencyRateNew.getEqualsCurrency().getCurrencyId())) {
            throw new InvalidInputException("Currency and EqualsCurrency are same!!!");
        }

        Optional<CurrencyRate> optionalCurrencyRate = currencyRateRepository.getCurrencyRatesByYearAndMonthAndDayAndCurrencyCurrencyIdAndEqualsCurrencyCurrencyId(currencyRateNew.getYear(), currencyRateNew.getMonth(), currencyRateNew.getDay(), currencyId, currencyRateNew.getEqualsCurrency().getCurrencyId());

        if (optionalCurrencyRate.isPresent() && !currencyRateId.equals(optionalCurrencyRate.get().getCurrencyRateId())) {
            throw new DataExistingException("Existing CurrencyRate available for relevant " + currencyRateNew.getYear() + "-" + currencyRateNew.getMonth() + "-" + currencyRateNew.getDay() +" date, so please change year, month, date or update!!!");
        }

        CurrencyRate currencyRate = currencyRateRepository.findById(currencyRateId).orElseThrow(() -> new NoDataAvailableException("CurrencyRate does not exist!!!"));

        Currency currency = currencyRepository.findById(currencyId).orElseThrow(() -> new NoDataAvailableException("Currency does not exist!!!"));
        Currency equalsCurrency = currencyRepository.findById(currencyRateNew.getEqualsCurrency().getCurrencyId()).orElseThrow(() -> new NoDataAvailableException("EqualsCurrency does not exist!!!"));



        currencyRate.setCurrencyRateValue(currencyRateNew.getCurrencyRateValue());
        currencyRate.setYear(currencyRateNew.getYear());
        currencyRate.setMonth(currencyRateNew.getMonth());
        currencyRate.setDay(currencyRateNew.getDay());
        currencyRate.setCurrency(currency);
        currencyRate.setEqualsCurrency(equalsCurrency);

    }

    public List<CurrencyRate> getCurrencyRatesByCurrencyAndEqualsCurrency(String currencyName, String  equalsCurrencyName, String sortingProperty, String order) {

        // sorting by year-month-day in ascending for initial loading of all data by currency and equals currency
        List<CurrencyRate> currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByYearAscMonthAscDayAsc(currencyName, equalsCurrencyName);

        // sorting by year-month-day in descending for initial loading of all data by currency and equals currency
        if (sortingProperty.equals("Date") && order.equals("Desc")) {
            currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByYearDescMonthDescDayDesc(currencyName, equalsCurrencyName);
        } else if (sortingProperty.equals("Value") && order.equals("Asc")) {
            currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateValueAsc(currencyName, equalsCurrencyName);
        } else if (sortingProperty.equals("Value") && order.equals("Desc")) {
            currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateValueDesc(currencyName, equalsCurrencyName);
        } else if (sortingProperty.equals("Id") && order.equals("Asc")) {
            currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateIdAsc(currencyName, equalsCurrencyName);
        } else if (sortingProperty.equals("Id") && order.equals("Desc")) {
            currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateIdDesc(currencyName, equalsCurrencyName);
        }

        if (currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName.isEmpty()) {
            throw new NoDataAvailableException("CurrencyName : " + currencyName + " and EqualsCurrencyName : " + equalsCurrencyName + " does not exist any CurrencyRate!!!");
        }

        return currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName;
    }

    public void deleteCurrencyRate(Long currencyRateId) {
        CurrencyRate currencyRate = currencyRateRepository.findById(currencyRateId).orElseThrow(() -> new NoDataAvailableException("CurrencyRate does not exist!!!"));

        currencyRateRepository.delete(currencyRate);
    }

    public Page<CurrencyRate> getCurrencyRatesWithPagination(String currencyName, String equalsCurrencyName, Integer pageNumber, Integer pageSize, String sortingProperty, String order) {      // pagination
        // sorting and pagination by year-month-day in ascending for initial loading of all data by currency and equals currency (pagination)
        Page<CurrencyRate> currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByYearAscMonthAscDayAsc(currencyName, equalsCurrencyName, PageRequest.of(pageNumber, pageSize));

        // sorting and pagination by year-month-day in descending for initial loading of all data by currency and equals currency (pagination)
        if (sortingProperty.equals("Date") && order.equals("Desc")) {
            currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByYearDescMonthDescDayDesc(currencyName, equalsCurrencyName, PageRequest.of(pageNumber, pageSize));
        } else if (sortingProperty.equals("Value") && order.equals("Asc")) {
            currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateValueAsc(currencyName, equalsCurrencyName, PageRequest.of(pageNumber, pageSize));
        } else if (sortingProperty.equals("Value") && order.equals("Desc")) {
            currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateValueDesc(currencyName, equalsCurrencyName, PageRequest.of(pageNumber, pageSize));
        } else if (sortingProperty.equals("Id") && order.equals("Asc")) {
            currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateIdAsc(currencyName, equalsCurrencyName, PageRequest.of(pageNumber, pageSize));
        } else if (sortingProperty.equals("Id") && order.equals("Desc")) {
            currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName = currencyRateRepository.getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateIdDesc(currencyName, equalsCurrencyName, PageRequest.of(pageNumber, pageSize));
        }

        if (currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName.isEmpty()) {
            throw new NoDataAvailableException("CurrencyName : " + currencyName + " and EqualsCurrencyName : " + equalsCurrencyName + " does not exist any CurrencyRate!!!");
        }

        return currencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName;

    }
}
