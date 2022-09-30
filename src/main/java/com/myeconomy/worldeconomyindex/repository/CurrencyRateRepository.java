package com.myeconomy.worldeconomyindex.repository;

import com.myeconomy.worldeconomyindex.model.CurrencyRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    @Query("select s from CurrencyRate s where s.currency.currencyName = ?1 and s.equalsCurrency.currencyName = ?2")
    Optional<CurrencyRate> getCurrencyRateByCurrencyAndEqualsCurrency(String currencyName, String equalsCurrencyName);

    Optional<CurrencyRate> getCurrencyRatesByYearAndMonthAndDayAndCurrencyCurrencyIdAndEqualsCurrencyCurrencyId(Year year, Month month, Integer day, Long currencyId, Long equalsCurrencyId);




    Page<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByYearAscMonthAscDayAsc(String currencyName, String equalsCurrencyName, Pageable pageable);

    Page<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByYearDescMonthDescDayDesc(String currencyName, String equalsCurrencyName, Pageable pageable);

    Page<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateValueAsc(String currencyName, String equalsCurrencyName, Pageable pageable);

    Page<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateValueDesc(String currencyName, String equalsCurrencyName, Pageable pageable);

    Page<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateIdAsc(String currencyName, String equalsCurrencyName, Pageable pageable);

    Page<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateIdDesc(String currencyName, String equalsCurrencyName, Pageable pageable);



    List<CurrencyRate> findAllByOrderByYearAscMonthAscDayAsc();

    Page<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyName(String currencyName, String equalsCurrencyName, Pageable pageable);

    List<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByYearAscMonthAscDayAsc(String currencyName, String equalsCurrencyName);

    List<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByYearDescMonthDescDayDesc(String currencyName, String equalsCurrencyName);

    List<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateValueAsc(String currencyName, String equalsCurrencyName);

    List<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateValueDesc(String currencyName, String equalsCurrencyName);

    List<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateIdAsc(String currencyName, String equalsCurrencyName);

    List<CurrencyRate> getCurrencyRatesByCurrencyCurrencyNameAndEqualsCurrencyCurrencyNameOrderByCurrencyRateIdDesc(String currencyName, String equalsCurrencyName);


}
