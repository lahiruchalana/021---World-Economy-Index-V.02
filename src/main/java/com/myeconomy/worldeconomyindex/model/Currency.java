package com.myeconomy.worldeconomyindex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "currency_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Currency {

    @Id
    @SequenceGenerator(
            name = "currency_sequence",
            sequenceName = "currency_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "currency_sequence"
    )
    private Long currencyId;
    private String currencyName;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "currency_country_tbl",
            joinColumns = @JoinColumn(
                    name = "currency_id",
                    referencedColumnName = "currencyId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "country_id",
                    referencedColumnName = "countryId"
            )
    )
    private List<Country> countryList;

    public Currency(String currencyName, List<Country> countryList) {
        this.currencyName = currencyName;
        this.countryList = countryList;
    }
}
