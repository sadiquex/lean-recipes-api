package com.saddik.leanRecipes.config.com.saddik.leanRecipes.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

//@Configuration
//class SecurityBeansConfig {
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
//
//    @Bean
//    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager =
//        authConfig.authenticationManager
//}
