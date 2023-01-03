package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UserManagementAppApplication 
{
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				 registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD","OPTIONS")
                .allowedHeaders("Content-Type", "Authorization","Origin","X-Auth-Token")
                .exposedHeaders("Content-Type", "Authorization","Origin","X-Auth-Token")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD","OPTIONS")
                .maxAge(3600);;
			}
		};
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(UserManagementAppApplication.class, args);
	}

}
