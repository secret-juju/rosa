package com.dsm.rosa.global.security.user

import com.dsm.rosa.domain.account.domain.User
import org.springframework.security.core.GrantedAuthority

import org.springframework.security.core.authority.SimpleGrantedAuthority

import org.springframework.security.core.userdetails.UserDetails

import org.springframework.security.oauth2.core.user.OAuth2User

class UserPrincipal(
    private val id: Long,
    private val email: String,
    private val attributes: Map<String, Any>,
) : OAuth2User, UserDetails {

    override fun getName() = id.toString()
    override fun getUsername() = email
    override fun getPassword() = null
    override fun getAuthorities() = listOf<SimpleGrantedAuthority>()
    override fun getAttributes() = attributes

    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true

    companion object {
        fun create(user: User) = UserPrincipal(
            id = user.id!!,
            email = user.email,
            attributes = mapOf()
        )

        fun create(user: User, attributes: Map<String, Any>) =
            UserPrincipal(
                id = user.id!!,
                email = user.email,
                attributes = attributes,
            )
    }
}