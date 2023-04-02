package com.example.ExampleCrudApiRest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.ExampleCrudApiRest.security.jwt.JwtEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;
import com.example.ExampleCrudApiRest.security.jwt.JwtProvider;
import com.example.ExampleCrudApiRest.security.jwt.JwtTokenFilter;
import com.example.ExampleCrudApiRest.security.service.MiUserDetailService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurity {

	@Autowired
	JwtEntryPoint jwtEntryPoint;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	JwtTokenFilter filterTokenFilter;

	@Autowired
	MiUserDetailService miUserDetailService;

	AuthenticationManager authenticationManager;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(miUserDetailService).passwordEncoder(passwordEncoder());
		authenticationManager = builder.build();
		http.authenticationManager(authenticationManager);

		http.headers(headers -> headers.frameOptions().disable());
		http.csrf().disable();
		http.cors().disable();
		http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.authorizeHttpRequests().requestMatchers("/auth/**", "/rest/**", "/v3/api-docs/**").permitAll()
				.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()

				.requestMatchers("/api/user/**").hasAnyRole("ADMIN").requestMatchers("/api/authority/**")
				.hasAnyRole("ADMIN").requestMatchers("/api/department/**").hasAnyRole("ADMIN", "DEPARTMENT")
				.requestMatchers("/api/employe/**").hasAnyRole("ADMIN", "EMPLOYE")

				.anyRequest().authenticated()

		;

		http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
		http.addFilterBefore(filterTokenFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();

	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(miUserDetailService).passwordEncoder(passwordEncoder());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
