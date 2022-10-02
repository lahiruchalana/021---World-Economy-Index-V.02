package com.myeconomy.worldeconomyindex.repository;

import com.myeconomy.worldeconomyindex.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findCountryByCountryName(String countryName);

    Page<Country> findCountriesByOrderByCountryNameAsc(Pageable pageable);
}
