package com.upn.sistemas.capsof_project.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/user/addUser").permitAll().anyRequest().authenticated().and().cors();
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowedOrigins(Arrays.asList("*"));
//		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//		config.setAllowCredentials(true);
//		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
//		config.addAllowedHeader("access-control-allow-origin");
//
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", config);
//
//		return source;
//	}
//
//	@Bean
//	public FilterRegistrationBean<CorsFilter> corsFilter() {
//		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
//				new CorsFilter(corsConfigurationSource()));
//		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return bean;
//	}
}
