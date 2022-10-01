package com.myeconomy.worldeconomyindex.service;

import com.myeconomy.worldeconomyindex.exceptions.DataExistingException;
import com.myeconomy.worldeconomyindex.model.EconomyIndex;
import com.myeconomy.worldeconomyindex.repository.EconomyIndexRepository;
import org.springframework.stereotype.Service;

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
            throw new DataExistingException("Country already has an EconomyIndex");
        }

        economyIndexRepository.save(economyIndex);
    }

    public List<EconomyIndex> getEconomyIndexes() {
        return economyIndexRepository.findAll();
    }
}
