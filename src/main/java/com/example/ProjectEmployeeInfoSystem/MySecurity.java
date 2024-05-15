package com.example.ProjectEmployeeInfoSystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class MySecurity {
	@SuppressWarnings("removal")
  @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic();
		http.authorizeHttpRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .logout()
            .logoutUrl("/logout");
	return http.build();
	}
}


