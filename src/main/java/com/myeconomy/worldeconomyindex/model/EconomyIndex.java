package com.myeconomy.worldeconomyindex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "economy_index_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EconomyIndex {

    @Id
    @SequenceGenerator(
            name = "economy_index_sequence",
            sequenceName = "economy_index_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "economy_index_sequence"
    )
    private Long economyIndexId;
    private Integer overallRank;
    private Float overallRate;
    private Integer economyFreedomRank;
    private Float economyFreedomRate;
    private Float globalShareOfEconomy;
    private Float gdp;
    private Float gnp;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "country_id",
            referencedColumnName = "countryId"
    )
    private Country country;

    // no need to have date properties -> because this indexes released by an organization annually at once for every country

}
