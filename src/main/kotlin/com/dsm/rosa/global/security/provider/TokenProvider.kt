package com.dsm.rosa.global.security.provider

import com.dsm.rosa.global.attribute.Token
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class TokenProvider(
    @Value("\${auth.jwt.secret-key}")
    private val secretKey: String,
    private val userDetailsService: UserDetailsService,
) {
    private val encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())

    fun createToken(accountEmail: String, tokenType: Token): String =
        Jwts.builder()
            .setSubject(accountEmail)
            .setExpiration(Date(System.currentTimeMillis() + tokenType.millisecondOfExpirationTime))
            .claim("kind", tokenType.kind)
            .signWith(SignatureAlgorithm.HS384, encodedSecretKey)
            .compact()

    fun getData(token: String): String =
        Jwts.parser()
            .setSigningKey(encodedSecretKey)
            .parseClaimsJws(token)
            .body
            .subject

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getData(token))
        return UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.authorities,
        )
    }

    fun extractToken(request: HttpServletRequest): String? {
        val token: String = request.getHeader("Authorization")
            ?: return null

        return if (token.startsWith("Bearer ")) token.substring(7)
            else null
    }

    fun validateToken(token: String) =
        try {
            val expirationTime = Jwts.parser()
                .setSigningKey(encodedSecretKey)
                .parseClaimsJws(token)
                .body
                .expiration
            expirationTime.after(Date())
        } catch (e: Exception) { false }

    fun isToken(token: String, tokenType: Token) =
        try {
            Jwts.parser()
                .setSigningKey(encodedSecretKey)
                .parseClaimsJws(token)
                .body["kind"] == tokenType.kind
        } catch (e: Exception) { false }
}