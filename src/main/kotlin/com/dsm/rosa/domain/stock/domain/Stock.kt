package com.dsm.rosa.domain.stock.domain

import com.dsm.rosa.domain.company.domain.Company
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "stock")
class Stock(

    @Column(name = "date")
    val date: LocalDate,

    @Column(name = "closing_price")
    val closingPrice: Long,

    @Column(name = "difference_from_yesterday")
    val differenceFromYesterday: Long,

    @Column(name = "fluctuation_rate")
    val fluctuationRate: Double,

    @Column(name = "opening_price")
    val openingPrice: Long,

    @Column(name = "high_price")
    val highPrice: Long,

    @Column(name = "low_price")
    val lowPrice: Long,

    @Column(name = "market_capitalization")
    val marketCapitalization: Long,

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    val company: Company,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
}