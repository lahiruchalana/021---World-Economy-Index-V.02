package com.myeconomy.worldeconomyindex.repository;

import com.myeconomy.worldeconomyindex.model.Gdp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GdpRepository extends JpaRepository<Gdp, Long> {


}
