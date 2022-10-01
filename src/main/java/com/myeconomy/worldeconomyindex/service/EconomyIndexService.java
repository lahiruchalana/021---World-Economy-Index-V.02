package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.exceptions.DataExistingException;
import com.myeconomy.worldeconomyindex.exceptions.NoDataAvailableException;
import com.myeconomy.worldeconomyindex.model.EconomyIndex;
import com.myeconomy.worldeconomyindex.repository.EconomyIndexRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EconomyIndexService {

    private final EconomyIndexRepository economyIndexRepository;

    public EconomyIndexService(EconomyIndexRepository economyIndexRepository) {
        this.economyIndexRepository = economyIndexRepository;
    }

    public void addNewEconomyIndex(EconomyIndex economyIndex) {
        Optional<EconomyIndex> economyIndexOptional = economyIndexRepository.findEconomyIndexByCountryName(economyIndex.getCountry().getCountryName());

        if (economyIndexOptional.isPresent()) {
            throw new DataExistingException("Country already has an EconomyIndex!!!");
        }

        economyIndexRepository.save(economyIndex);
    }

    public List<EconomyIndex> getEconomyIndexes() {
        return economyIndexRepository.findAll();
    }

    @Transactional
    public void updateEconomyIndex(Long economyIndexId, EconomyIndex economyIndex) {
        EconomyIndex economyIndexExist = economyIndexRepository.findById(economyIndexId).orElseThrow(() -> new NoDataAvailableException("EconomyIndex does not exist for relevant economyIndexId!!!"));

        Optional<EconomyIndex> economyIndexOptional = economyIndexRepository.findEconomyIndexByCountryCountryId(economyIndex.getCountry().getCountryId());

        if (economyIndexOptional.isPresent() && !economyIndexOptional.get().getEconomyIndexId().equals(economyIndexId)) {
            throw new DataExistingException("CountryId of EconomyIndex already exists, Could not create many EconomyIndex data for a country!!!");
        }

        economyIndexExist.setOverallRank(economyIndex.getOverallRank());
        economyIndexExist.setOverallRate(economyIndex.getOverallRate());
        economyIndexExist.setEconomyFreedomRank(economyIndex.getEconomyFreedomRank());
        economyIndexExist.setEconomyFreedomRate(economyIndex.getEconomyFreedomRate());
        economyIndexExist.setGlobalShareOfEconomy(economyIndex.getGlobalShareOfEconomy());
        economyIndexExist.setGdp(economyIndex.getGdp());
        economyIndexExist.setGnp(economyIndex.getGnp());
        economyIndexExist.setCountry(economyIndex.getCountry());
    }

    public void deleteEconomyIndex(Long economyIndexId) {
        EconomyIndex economyIndex = economyIndexRepository.findById(economyIndexId).orElseThrow(() -> new NoDataAvailableException("EconomyIndex does not exist for relevant economyIndexId!!!"));

        economyIndexRepository.delete(economyIndex);
    }
}
