package com.upn.sistemas.capsof_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication
public class CapsofProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapsofProjectApplication.class, args);
	}

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.cors(cors -> cors.disable());
		return http.build();
	}

}
