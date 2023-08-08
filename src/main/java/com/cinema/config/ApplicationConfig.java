package com.cinema.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cinema.dao.UserDao;
import com.cinema.dto.MovieRowMapper;
import com.cinema.dto.PersonRowMapper;
//import com.cinema.dto.UserRowMapper;

@Configuration
@ComponentScan(basePackages = "com.cinema.dao")

public class ApplicationConfig {
	
	private final UserDao userDao;
	
	
	public ApplicationConfig(UserDao userDao){
		this.userDao = userDao;	
	}
	

    @Bean
    public MovieRowMapper movieRowMapper() {
        return new MovieRowMapper();
    }
    

    @Bean
    public PersonRowMapper personRowMapper() {
        return new PersonRowMapper();
    }
    
    
	/*
	 * @Bean public UserRowMapper userRowMapper() { return new UserRowMapper(); }
	 */
    
    
	
	@Bean
	   public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider (); 
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
public AuthenticationManager authenticationManager ( AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
		
		
	}
	
	
	@Bean 
	public UserDetailsService userDetailsService() {
		
		
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				return userDao.getUserByUsername(username);
			}
		};

	}
	
	
	
	

}
