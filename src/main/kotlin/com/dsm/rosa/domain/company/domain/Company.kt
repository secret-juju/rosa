package com.dsm.rosa.domain.company.domain

import com.dsm.rosa.domain.news.domain.News
import com.dsm.rosa.domain.stock.domain.Stock
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

    @OneToMany(mappedBy = "company")
    val stocks: Set<Stock> = setOf()

    @OneToMany(mappedBy = "company")
    val news: Set<News> = setOf()
}