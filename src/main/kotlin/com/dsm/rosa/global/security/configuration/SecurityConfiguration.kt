package com.dsm.rosa.global.security.configuration

import com.dsm.rosa.global.security.entrypoint.InvalidTokenExceptionEntryPoint
import com.dsm.rosa.global.security.filter.AuthenticationFilter
import com.dsm.rosa.global.security.filter.LogFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    val invalidTokenExceptionEntryPoint: InvalidTokenExceptionEntryPoint,
    val logFilter: LogFilter,
    val authenticationFilter: AuthenticationFilter,
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .cors()
                .and()
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(invalidTokenExceptionEntryPoint)
                .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()

        http
            .addFilterBefore(logFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/swagger-ui.html/**", "/webjars/**", "/swagger/**", "/v2/api-docs", "/swagger-resources/**")
    }
}