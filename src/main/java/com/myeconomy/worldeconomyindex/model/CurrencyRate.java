package com.myeconomy.worldeconomyindex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Month;
import java.time.Year;

@Entity
@Table(name = "currency_rate_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrencyRate {

    @Id
    @SequenceGenerator(
            name = "currency_rate_sequence",
            sequenceName = "currency_rate_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "currency_rate_sequence"
    )
    private Long currencyRateId;
    private Float currencyRateValue;
    private Year year;
    private Month month;
    private Integer day;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "currency_id",
            referencedColumnName = "currencyId"
    )
    private Currency currency;          // equalsCurrency value 1 equals to XYZ currency value

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "equals_currency_id",
            referencedColumnName = "currencyId"
    )
    private Currency equalsCurrency;            // equalsCurrency value 1 equals to XYZ currency value

}
