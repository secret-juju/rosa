package com.dsm.rosa.domain.industry.domain

import com.dsm.rosa.domain.company.domain.Company
import javax.persistence.*

@Entity
@Table(name = "company_industry_affiliation")
class CompanyIndustryAffiliation(

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    val company: Company,

    @ManyToOne
    @JoinColumn(name = "industry_id", referencedColumnName = "id")
    val industry: Industry,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
}