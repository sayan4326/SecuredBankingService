package com.yourcompany.securedbankingservice.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager()
	{
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		List<UserDetails> userDetails = new ArrayList<>();
		userDetails.add(User.withUsername("Sayan").password("root").authorities("ADMIN").build());
		userDetails.add(User.withUsername("Rahul").password("system").authorities("CUSTOMER").build());
		for(UserDetails userDetail : userDetails)
		{
			inMemoryUserDetailsManager.createUser(userDetail);
		}
		return inMemoryUserDetailsManager;
	}
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.authorizeRequests().anyRequest().authenticated();
		httpSecurity.formLogin();
		return httpSecurity.build();
	}
	
}
