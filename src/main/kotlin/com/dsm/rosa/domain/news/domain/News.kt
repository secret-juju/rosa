package com.dsm.rosa.domain.news.domain

import com.dsm.rosa.domain.company.domain.Company
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "news")
class News(
    @Column(name = "content")
    val content: String,

    @Column(name = "positivity")
    val positivity: Double,

    @Column(name = "published_date")
    val publishedDate: LocalDate,

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    val company: Company,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
}