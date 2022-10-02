package com.myeconomy.worldeconomyindex.repository;

import com.myeconomy.worldeconomyindex.model.Gdp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

    Page<Gdp> getGdpByCountryCountryNameOrderByYearMonthAsc(String countryName, Pageable pageable);

    Page<Gdp> getGdpByCountryCountryNameOrderByYearMonthDesc(String countryName, Pageable pageable);

    Page<Gdp> getGdpByCountryCountryNameOrderByGdpValueAsc(String countryName, Pageable pageable);

    Page<Gdp> getGdpByCountryCountryNameOrderByGdpValueDesc(String countryName, Pageable pageable);

    Page<Gdp> getGdpByCountryCountryNameOrderByGdpGrowthRateAsc(String countryName, Pageable pageable);

    Page<Gdp> getGdpByCountryCountryNameOrderByGdpGrowthRateDesc(String countryName, Pageable pageable);

    Page<Gdp> getGdpByCountryCountryNameOrderByGdpPerCapitaAsc(String countryName, Pageable pageable);

    Page<Gdp> getGdpByCountryCountryNameOrderByGdpPerCapitaDesc(String countryName, Pageable pageable);
}
