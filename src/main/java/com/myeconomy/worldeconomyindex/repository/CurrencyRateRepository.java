package com.myeconomy.worldeconomyindex.repository;

import com.myeconomy.worldeconomyindex.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Year;
import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    @Query("select s from CurrencyRate s where s.currency.currencyName = ?1 and s.equalsCurrency.currencyName = ?2")
    Optional<CurrencyRate> getCurrencyRateByCurrencyAndEqualsCurrency(String currencyName, String equalsCurrencyName);

    Optional<CurrencyRate> getCurrencyRatesByYearAndMonthAndDayAndCurrencyCurrencyIdAndEqualsCurrencyCurrencyId(Year year, Month month, Integer day, Long currencyId, Long equalsCurrencyId);

}
