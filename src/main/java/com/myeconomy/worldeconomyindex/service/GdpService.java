package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.exceptions.NoDataAvailableException;
import com.myeconomy.worldeconomyindex.model.Gdp;
import com.myeconomy.worldeconomyindex.repository.GdpRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
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

    public List<Gdp> getGdpsByCountryNameWithSorting(String countryName, String sortingProperty, String order) {
        List<Gdp> gdpList = gdpRepository.findGdpsByCountryCountryNameOrderByYearMonthAsc(countryName);

        if (sortingProperty.equals("Date") && order.equals("Desc")) {
            gdpList = gdpRepository.findGdpsByCountryCountryNameOrderByYearMonthDesc(countryName);
        } else if (sortingProperty.equals("gdpValue") && order.equals("Asc")) {
            gdpList = gdpRepository.findGdpsByCountryCountryNameOrderByGdpValueAsc(countryName);
        } else if (sortingProperty.equals("gdpValue") && order.equals("Desc")) {
            gdpList = gdpRepository.findGdpsByCountryCountryNameOrderByGdpValueDesc(countryName);
        } else if (sortingProperty.equals("gdpGrowthRate") && order.equals("Asc")) {
            gdpList = gdpRepository.findGdpsByCountryCountryNameOrderByGdpGrowthRateAsc(countryName);
        } else if (sortingProperty.equals("gdpGrowthRate") && order.equals("Desc")) {
            gdpList = gdpRepository.findGdpsByCountryCountryNameOrderByGdpGrowthRateDesc(countryName);
        } else if (sortingProperty.equals("gdpPerCapita") && order.equals("Asc")) {
            gdpList = gdpRepository.findGdpsByCountryCountryNameOrderByGdpPerCapitaAsc(countryName);
        } else if (sortingProperty.equals("gdpPerCapita") && order.equals("Desc")) {
            gdpList = gdpRepository.findGdpsByCountryCountryNameOrderByGdpPerCapitaDesc(countryName);
        }

        if (gdpList.isEmpty()) {
            throw new NoDataAvailableException("CountryId does not exist any Gdp data!!!");
        }

        return gdpList;
    }

    public Page<Gdp> getGdpsByCountryNameWithPagination(String countryName, Integer pageNumber, Integer pageSize, String sortingProperty, String order) {
        Page<Gdp> gdpPage = gdpRepository.getGdpByCountryCountryNameOrderByYearMonthAsc(countryName, PageRequest.of(pageNumber, pageSize));

        if (sortingProperty.equals("Date") && order.equals("Desc")) {
            gdpPage = gdpRepository.getGdpByCountryCountryNameOrderByYearMonthDesc(countryName, PageRequest.of(pageNumber, pageSize));
        } else if (sortingProperty.equals("gdpValue") && order.equals("Asc")) {
            gdpPage = gdpRepository.getGdpByCountryCountryNameOrderByGdpValueAsc(countryName, PageRequest.of(pageNumber, pageSize));
        } else if (sortingProperty.equals("gdpValue") && order.equals("Desc")) {
            gdpPage = gdpRepository.getGdpByCountryCountryNameOrderByGdpValueDesc(countryName, PageRequest.of(pageNumber, pageSize));
        } else if (sortingProperty.equals("gdpGrowthRate") && order.equals("Asc")) {
            gdpPage = gdpRepository.getGdpByCountryCountryNameOrderByGdpGrowthRateAsc(countryName, PageRequest.of(pageNumber, pageSize));
        } else if (sortingProperty.equals("gdpGrowthRate") && order.equals("Desc")) {
            gdpPage = gdpRepository.getGdpByCountryCountryNameOrderByGdpGrowthRateDesc(countryName, PageRequest.of(pageNumber, pageSize));
        } else if (sortingProperty.equals("gdpPerCapita") && order.equals("Asc")) {
            gdpPage = gdpRepository.getGdpByCountryCountryNameOrderByGdpPerCapitaAsc(countryName, PageRequest.of(pageNumber, pageSize));
        } else if (sortingProperty.equals("gdpPerCapita") && order.equals("Desc")) {
            gdpPage = gdpRepository.getGdpByCountryCountryNameOrderByGdpPerCapitaDesc(countryName, PageRequest.of(pageNumber, pageSize));
        }

        if (gdpPage.isEmpty()) {
            throw new NoDataAvailableException("CountryId does not exist any Gdp data!!!");
        }

        return gdpPage;
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
