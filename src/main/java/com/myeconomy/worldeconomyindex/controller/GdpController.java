package com.myeconomy.worldeconomyindex.controller;

import com.myeconomy.worldeconomyindex.model.Gdp;
import com.myeconomy.worldeconomyindex.service.GdpService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*"
)
@RequestMapping(path = "world-economy-index/api/data/gdps")
public class GdpController {

    private final GdpService gdpService;

    public GdpController(GdpService gdpService) {
        this.gdpService = gdpService;
    }

    @PostMapping
    public ResponseEntity<?> addNewGdp(
            @RequestBody Gdp gdp
    ) {
        gdpService.addNewGdp(gdp);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Gdp>> getGdps() {
        return new ResponseEntity<>(gdpService.getGdps(), HttpStatus.OK);
    }

    @GetMapping(path = "countries/{countryName}/gdps-by-name")
    public ResponseEntity<List<Gdp>> getGdpsByCountryName(
            @PathVariable("countryName") String countryName
    ) {
        return new ResponseEntity<>(gdpService.getGdpsByCountryName(countryName), HttpStatus.OK);
    }

    @GetMapping(path = "countries/{countryId}/gdps-by-id")
    public ResponseEntity<List<Gdp>> getGdpsByCountryId(
            @PathVariable("countryId") Long countryId
    ) {
        return new ResponseEntity<>(gdpService.getGdpsByCountryId(countryId), HttpStatus.OK);
    }

    @GetMapping(path = "countries/{countryName}/gdps-by-name-sorting")
    public ResponseEntity<List<Gdp>> getGdpsByCountryNameWithSorting(
            @PathVariable("countryName") String countryName,
            @RequestParam(required = false) String sortingProperty,
            @RequestParam(required = false) String order
    ) {
        return new ResponseEntity<>(gdpService.getGdpsByCountryNameWithSorting(countryName, sortingProperty, order), HttpStatus.OK);
    }

    @GetMapping(path = "countries/{countryName}/gdps-by-name-pagination")
    public ResponseEntity<Page<Gdp>> getGdpsByCountryNameWithPagination(
            @PathVariable("countryName") String countryName,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortingProperty,
            @RequestParam(required = false) String order
    ) {
        return new ResponseEntity<>(gdpService.getGdpsByCountryNameWithPagination(countryName, pageNumber, pageSize, sortingProperty, order), HttpStatus.OK);
    }

    @PutMapping(path = "countries/{countryId}/gdps/{gdpId}")
    public ResponseEntity<?> updateGdp(
            @PathVariable("countryId") Long countryId,
            @PathVariable("gdpId") Long gdpId,
            @RequestBody Gdp gdp
    ) {
        gdpService.updateGdp(countryId, gdpId, gdp);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "gdps/{gdpId}")
    public ResponseEntity<?> deleteGdp(
            @PathVariable("gdpId") Long gdpId
    ) {
        gdpService.deleteGdp(gdpId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
