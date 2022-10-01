package com.myeconomy.worldeconomyindex.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.YearMonth;

@Entity
@Table(name = "gdp_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Gdp {

    @Id
    @SequenceGenerator(
            name = "gdp_sequence",
            sequenceName = "gdp_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gdp_sequence"
    )
    private Long gdpId;
    private Float gdpValue;
    private Float gdpGrowthRate;
    private Float gdpPerCapita;

    @JsonFormat(pattern = "MM/yyyy")
    private YearMonth yearMonth;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "country_id",
            referencedColumnName = "countryId"
    )
    private Country country;

}
