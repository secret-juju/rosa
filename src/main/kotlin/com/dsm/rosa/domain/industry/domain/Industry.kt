package com.dsm.rosa.domain.industry.domain

import com.dsm.rosa.domain.company.domain.Company
import javax.persistence.*

@Entity
@Table(name = "industry")
class Industry(

    @Column(name = "name")
    val name: String,

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    val company: Company,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
}