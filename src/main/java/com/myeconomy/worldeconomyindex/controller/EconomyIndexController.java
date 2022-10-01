package com.myeconomy.worldeconomyindex.controller;

import com.myeconomy.worldeconomyindex.model.EconomyIndex;
import com.myeconomy.worldeconomyindex.service.EconomyIndexService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*"
)
@RequestMapping(path = "world-economy-index/api/data/economy-indexes")
public class EconomyIndexController {

    private final EconomyIndexService economyIndexService;

    public EconomyIndexController(EconomyIndexService economyIndexService) {
        this.economyIndexService = economyIndexService;
    }

    @PostMapping
    public ResponseEntity<?> addNewEconomyIndex(
            @RequestBody EconomyIndex economyIndex
            ) {
        economyIndexService.addNewEconomyIndex(economyIndex);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EconomyIndex>> getEconomyIndexes() {
        return new ResponseEntity<>(economyIndexService.getEconomyIndexes(), HttpStatus.OK);
    }

}
