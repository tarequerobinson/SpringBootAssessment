package com.cinema.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.HashMap;


@Service
public class JwtTokenService {
	
	private static String SECRET_KEY = "656c686b574348384d655b2951726c3d396f573a2e792c2b283c22617965544b";
    private static final long TOKEN_EXPIRATION_TIME_MS = 1000 * 60 * 24; // 1 day in milliseconds

	
	
	
	
	public String extractUserName(String jwtToken){
		
		return extractClaim(jwtToken , Claims::getSubject);
		
	}
	
	
	//mETHOD TO EXTARCT A CLAIM FROM THE TOKEN 

	private <T> T extractClaim (String jwtToken , Function<Claims, T> claimsresolver) {
		final Claims claims = extractAllClaims(jwtToken);
		return claimsresolver.apply(claims);
		
		
	}
	
	
	//mETHOD TO EXTARCT ALL THE CLAIM FROM THE TOKEN 
	private Claims extractAllClaims(String jwtToken ) {
		
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(jwtToken)
				.getBody();
		
	}

	//Method to create sign in key 
	private Key getSignInKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	public String generateToken(UserDetails userDetails) {
		
		return generateToken(new HashMap<>(), userDetails);
	}

	//method to generate JWT TOKEN 
	public String generateToken(Map <String , Object> extraClaims, UserDetails userDetails) 
	
	{
		
        Date now = new Date();
        Date expiration = new Date(now.getTime() + TOKEN_EXPIRATION_TIME_MS);

		return Jwts
				.builder().setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public boolean isTokenValid (String jwtToken,  UserDetails userDetails) {
		
		final String username = extractUserName (jwtToken);
		
		return (username.equals(userDetails.getUsername()))&& !isTokenExpired (jwtToken);
		
		
	}


	private boolean isTokenExpired(String jwtToken) {
		// TODO Auto-generated method stub
		return extractExpiration(jwtToken).before(new Date());
	}


	private Date extractExpiration(String jwtToken) {
		// TODO Auto-generated method stub
		return extractClaim(jwtToken , Claims::getExpiration);
	}

}
