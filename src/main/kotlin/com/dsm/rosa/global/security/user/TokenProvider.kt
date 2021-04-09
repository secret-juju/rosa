package com.dsm.rosa.global.security.user

import com.dsm.rosa.global.security.dto.AppProperties
import io.jsonwebtoken.*
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenProvider(private val appProperties: AppProperties) {

    fun createToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserPrincipal
        val now = Date()
        val expirationDate = Date(now.time + appProperties.getAuth().tokenExpirationMsec)
        return Jwts.builder()
            .setSubject(userPrincipal.name)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().tokenSecret)
            .compact()
    }

    fun getUserIdFromToken(token: String): Long {
        val claims = Jwts.parser()
            .setSigningKey(appProperties.getAuth().tokenSecret)
            .parseClaimsJws(token)
            .body
        return claims.subject.toLong()
    }

    fun validateToken(authToken: String?) =
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().tokenSecret).parseClaimsJws(authToken)
            true
        } catch (e: Exception) {
            false
        }
}