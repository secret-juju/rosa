package com.dsm.rosa.domain.account.domain

import com.dsm.rosa.global.security.dto.AuthProvider
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "user")
class User(

    @Column(name = "email")
    val email: String,

    @Column(name = "name")
    val name: String,

    @Enumerated(EnumType.STRING)
    val provider: AuthProvider,

    val providerId: String,
) : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

    override fun getAuthorities() = mutableListOf<SimpleGrantedAuthority>()
    override fun getPassword() = null
    override fun getUsername() = email
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}