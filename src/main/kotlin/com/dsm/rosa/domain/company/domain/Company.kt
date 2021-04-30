package com.dsm.rosa.domain.company.domain

import javax.persistence.*

@Entity
@Table(name = "company")
class Company(

    @Column(name = "ticker_symbol")
    val tickerSymbol: String,

    @Column(name = "name")
    val name: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
}