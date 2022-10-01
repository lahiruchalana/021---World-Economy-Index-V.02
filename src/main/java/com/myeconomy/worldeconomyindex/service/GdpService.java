package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.model.Gdp;
import com.myeconomy.worldeconomyindex.repository.GdpRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GdpService {

    private final GdpRepository gdpRepository;

    public GdpService(GdpRepository gdpRepository) {
        this.gdpRepository = gdpRepository;
    }

    public void addNewGdp(Gdp gdp) {
        gdpRepository.save(gdp);
    }

    public List<Gdp> getGdps() {
        return gdpRepository.findAll();
    }
}
