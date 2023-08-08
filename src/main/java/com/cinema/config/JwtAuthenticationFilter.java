package com.cinema.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
	private UserDetailsService userDetailsService;
	private final JwtTokenService jwtTokenService;
	
	JwtAuthenticationFilter (JwtTokenService jwtTokenService, UserDetailsService userDetailsService){
		
		this.jwtTokenService=jwtTokenService;	
		this.userDetailsService=userDetailsService;	

	}
	
	
	
	
	
	
	
	@Override
	protected void doFilterInternal(
			@NotNull HttpServletRequest request, 
			@NotNull HttpServletResponse response, 
			@NotNull FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final String authHeader = request.getHeader("Authorization");
		final String jwtToken;
		final String username;
		
		if (authHeader == null || ! authHeader.startsWith("Bearer")){
		filterChain.doFilter( request, response);
		return;
		
		}
		
		jwtToken = authHeader.substring(7);
		username  = jwtTokenService.extractUserName(jwtToken);
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null ) 
		{
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if ( jwtTokenService.isTokenValid(jwtToken, userDetails)) {
				
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
			}
			
			
		}

		
		filterChain.doFilter(request, response);
	}
	

	


}
