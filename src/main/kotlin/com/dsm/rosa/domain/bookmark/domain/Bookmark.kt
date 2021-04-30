package com.dsm.rosa.domain.bookmark.domain

import com.dsm.rosa.domain.account.domain.Account
import com.dsm.rosa.domain.company.domain.Company
import javax.persistence.*

@Entity
@Table(name = "bookmark")
class Bookmark(

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    val account: Account,

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    val company: Company,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
}