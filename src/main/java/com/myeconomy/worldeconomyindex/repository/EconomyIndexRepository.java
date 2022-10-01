package com.myeconomy.worldeconomyindex.repository;

import com.myeconomy.worldeconomyindex.model.EconomyIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EconomyIndexRepository extends JpaRepository<EconomyIndex, Long> {

    @Query("select s from EconomyIndex s where s.country.countryName = ?1")
    Optional<EconomyIndex> findEconomyIndexByCountryName(String countryName);
}
