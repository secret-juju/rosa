package com.dsm.rosa.global.security.configuration

import com.dsm.rosa.global.security.service.CustomOAuth2UserService
import com.dsm.rosa.global.security.service.CustomUserDetailsService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true,
)
class SecurityConfiguration(
    val customUserDetailsService: CustomUserDetailsService,
    val customOAuth2UserService: CustomOAuth2UserService,
) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
                .authorizeRequests()
                    .antMatchers("/", "/oauth2/**", "/login/**", "/h2-console/**").permitAll()
                    .anyRequest().authenticated()
            .and()
                .logout()
                    .logoutSuccessUrl("/")
            .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService)
    }
}