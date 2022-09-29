package com.myeconomy.worldeconomyindex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "country_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Country {

    @Id
    @SequenceGenerator(
            name = "country_sequence",
            sequenceName = "country_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "country_sequence"
    )
    private Long countryId;
    private String countryName;
    private String continentName;
    private String subContinentName;

}
