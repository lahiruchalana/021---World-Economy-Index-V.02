package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.exceptions.NoDataAvailableException;
import com.myeconomy.worldeconomyindex.model.Gdp;
import com.myeconomy.worldeconomyindex.repository.GdpRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<Gdp> getGdpsByCountryName(String countryName) {
        List<Gdp> gdpList = gdpRepository.findGdpsByCountryCountryName(countryName);

        if (gdpList.isEmpty()) {
            throw new NoDataAvailableException("CountryName does not exist any Gdp data!!!");
        }

        return gdpList;
    }

    public List<Gdp> getGdpsByCountryId(Long countryId) {
        List<Gdp> gdpList = gdpRepository.findGdpsByCountryId(countryId);

        if (gdpList.isEmpty()) {
            throw new NoDataAvailableException("CountryId does not exist any Gdp data!!!");
        }

        return gdpList;
    }

    @Transactional
    public void updateGdp(Long countryId, Long gdpId, Gdp gdp) {
        Gdp gdpExist = gdpRepository.findById(gdpId).orElseThrow(() -> new NoDataAvailableException("Gdp does not exist for the relevant gdpId!!!"));

        gdpExist.setGdpValue(gdp.getGdpValue());
        gdpExist.setGdpGrowthRate(gdp.getGdpGrowthRate());
        gdpExist.setGdpPerCapita(gdp.getGdpPerCapita());
        gdpExist.setYearMonth(gdp.getYearMonth());
        gdpExist.getCountry().setCountryId(countryId);
    }

    public void deleteGdp(Long gdpId) {
        Gdp gdp = gdpRepository.findById(gdpId).orElseThrow(() -> new NoDataAvailableException("Gdp does not exist for the relevant gdpId!!!"));

        gdpRepository.delete(gdp);
    }
}
