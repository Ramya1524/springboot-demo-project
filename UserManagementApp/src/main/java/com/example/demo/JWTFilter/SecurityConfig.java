package com.example.demo.JWTFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private JwtValidate validate;
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }

	    @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	
	    	 System.out.println("Inside entry");
	        http.csrf().disable()
	                .authorizeHttpRequests((authorize) ->
	                        authorize.mvcMatchers(HttpMethod.POST, "/auth/login").permitAll()
	                                .mvcMatchers(HttpMethod.POST, "/auth/addUser").permitAll()
	                                .anyRequest().authenticated()

	                ).exceptionHandling( exception -> exception
	                        .authenticationEntryPoint(authenticationEntryPoint)
	                ).sessionManagement( session -> session
	                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                );

	        http.addFilterBefore(validate, UsernamePasswordAuthenticationFilter.class);
	        System.out.println("filter added");
	        return http.build();
	    }

}
