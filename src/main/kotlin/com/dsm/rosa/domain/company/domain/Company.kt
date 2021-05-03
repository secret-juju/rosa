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

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    val stocks: MutableSet<Stock> = mutableSetOf()

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    val news: MutableSet<News> = mutableSetOf()

    override fun toString(): String {
        return "Company(tickerSymbol='$tickerSymbol', name='$name', id=$id, stocks=$stocks, news=$news)"
    }
}