package com.dsm.rosa.domain.industry.domain

import javax.persistence.*

@Entity
@Table(name = "industry")
class Industry(

    @Column(name = "name")
    val name: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
}