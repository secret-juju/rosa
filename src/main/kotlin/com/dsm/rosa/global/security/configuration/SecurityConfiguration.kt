package com.dsm.rosa.global.security.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint

@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/", "/oauth2/**", "/login/**", "/console/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .oauth2Login()
            .and()
                .exceptionHandling()
                    .authenticationEntryPoint(LoginUrlAuthenticationEntryPoint("/login"))
    }
}