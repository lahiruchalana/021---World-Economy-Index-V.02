package com.myeconomy.worldeconomyindex.repository;

import com.myeconomy.worldeconomyindex.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findCurrencyByCurrencyName(String currencyName);

}
