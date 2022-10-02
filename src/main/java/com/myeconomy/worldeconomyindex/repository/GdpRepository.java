package com.myeconomy.worldeconomyindex.repository;

import com.myeconomy.worldeconomyindex.model.Gdp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GdpRepository extends JpaRepository<Gdp, Long> {

    List<Gdp> findGdpsByCountryCountryName(String countryName);

    @Query("select s from Gdp s where s.country.countryId = ?1")
    List<Gdp> findGdpsByCountryId(Long countryId);
}
