package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.exceptions.DataExistingException;
import com.myeconomy.worldeconomyindex.exceptions.NoDataAvailableException;
import com.myeconomy.worldeconomyindex.model.Currency;
import com.myeconomy.worldeconomyindex.repository.CurrencyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Page<Currency> getCurrenciesWithPagination(Integer pageNumber, Integer pageSize) {
        Page<Currency> currencyPage = currencyRepository.findCurrenciesByOrderByCurrencyNameAsc(PageRequest.of(pageNumber, pageSize));

        if (currencyPage.isEmpty()) {
            throw new NoDataAvailableException("No available currency data!!!");
        }

        return currencyPage;
    }

    @Transactional
    public void updateCurrency(Long currencyId, Currency currency) {
        Currency currencyFound = currencyRepository.findById(currencyId).orElseThrow(() -> new NoDataAvailableException("CurrencyId does not exist!!!"));

        Optional<Currency> currencyOptional = currencyRepository.findCurrencyByCurrencyName(currency.getCurrencyName());

        if (currencyOptional.isPresent() && !currencyOptional.get().getCurrencyId().equals(currencyId)) {
            throw new DataExistingException("CurrencyName already exists!!!");
        }

        currencyFound.setCurrencyName(currency.getCurrencyName());
        currencyFound.setCountryList(currency.getCountryList());
    }

    public void deleteCurrency(Long currencyId) {
        Currency currency = currencyRepository.findById(currencyId).orElseThrow(() -> new NoDataAvailableException("CurrencyId does not exist!!!"));

        currencyRepository.delete(currency);
    }

}
