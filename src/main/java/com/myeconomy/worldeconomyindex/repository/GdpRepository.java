package com.myeconomy.worldeconomyindex.repository;

import com.myeconomy.worldeconomyindex.model.Gdp;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.YearMonth;
import java.util.List;

@Repository
public interface GdpRepository extends JpaRepository<Gdp, Long> {

    List<Gdp> findGdpsByCountryCountryName(String countryName);

    @Query("select s from Gdp s where s.country.countryId = ?1")
    List<Gdp> findGdpsByCountryId(Long countryId);

    List<Gdp> findGdpsByCountryCountryNameOrderByYearMonthAsc(String countryName);

    List<Gdp> findGdpsByCountryCountryNameOrderByGdpValueAsc(String countryName);

    List<Gdp> findGdpsByCountryCountryNameOrderByGdpGrowthRateAsc(String countryName);

    List<Gdp> findGdpsByCountryCountryNameOrderByGdpPerCapitaAsc(String countryName);

    List<Gdp> findGdpsByCountryCountryNameOrderByYearMonthDesc(String countryName);

    List<Gdp> findGdpsByCountryCountryNameOrderByGdpValueDesc(String countryName);

    List<Gdp> findGdpsByCountryCountryNameOrderByGdpGrowthRateDesc(String countryName);

    List<Gdp> findGdpsByCountryCountryNameOrderByGdpPerCapitaDesc(String countryName);

    Page<Gdp> findGdpsByCountryCountryNameOrderByYearMonthAsc(String countryName, Pageable pageable);

    Page<Gdp> findGdpsByCountryCountryNameOrderByGdpValueAsc(String countryName, Pageable pageable);

    Page<Gdp> findGdpsByCountryCountryNameOrderByGdpGrowthRateAsc(String countryName, Pageable pageable);

    Page<Gdp> findGdpsByCountryCountryNameOrderByGdpPerCapitaAsc(String countryName, Pageable pageable);

    Page<Gdp> findGdpsByCountryCountryNameOrderByYearMonthDesc(String countryName, Pageable pageable);

    Page<Gdp> findGdpsByCountryCountryNameOrderByGdpValueDesc(String countryName, Pageable pageable);

    Page<Gdp> findGdpsByCountryCountryNameOrderByGdpGrowthRateDesc(String countryName, Pageable pageable);

    Page<Gdp> findGdpsByCountryCountryNameOrderByGdpPerCapitaDesc(String countryName, Pageable pageable);
}
