package com.cinema.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	
	SecurityConfig (JwtAuthenticationFilter jwtAuthFilter , AuthenticationProvider authenticationProvider){
		this.jwtAuthFilter =jwtAuthFilter;
		this.authenticationProvider=authenticationProvider;
	}
	
//	 @Bean
//	    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//	        return new JdbcTemplate(dataSource);
//	    }
//	 

		 
			
		@Bean
		public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity ) throws Exception
		{
			
			httpSecurity
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers("/api/v1/auth/**")
			.permitAll()
			//.requestMatchers("/movies/createNewMovie").hasAuthority("ADMIN")
			.anyRequest()
			//.permitAll()

			.authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class);
			
			
			
			return httpSecurity.build();
			
			
			/*
			 * httpSecurity.httpBasic(); httpSecurity .authorizeHttpRequests()
			 * //.requestMatchers("/").permitAll() //
			 * 
			 * 
			 * // .requestMatchers("/api/users").hasAuthority("ADMIN")
			 * .anyRequest().permitAll()
			 * 
			 * .and().formLogin().loginPage("/login").loginProcessingUrl("/login").
			 * defaultSuccessUrl("/analyticsPage"); //.and().build(); httpSecurity.csrf(csrf
			 * -> csrf.disable());
			 * 
			 * return httpSecurity.build();
			 */
	         
		}
	        

		public void configure(WebSecurity web) throws Exception {
	        web
	            .ignoring()
	            .requestMatchers("/custom-login.css"); // Allow access to CSS resources
	    }

}
