package com.myeconomy.worldeconomyindex.repository;

import com.myeconomy.worldeconomyindex.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
