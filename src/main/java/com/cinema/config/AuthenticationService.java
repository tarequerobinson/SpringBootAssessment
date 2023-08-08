package com.cinema.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

import com.cinema.dao.UserDao;
import com.cinema.entities.User;
import com.cinema.services.UserService;

import lombok.Builder;

@Service
@Builder
public class AuthenticationService {
	private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserDao userDao;

    public AuthenticationService(
        AuthenticationManager authenticationManager,
        UserDetailsService userDetailsService,
        UserDao userDao,
        PasswordEncoder passwordEncoder,
        JwtTokenService jwtTokenService,
        UserService userService
    ) {
        this.userService = userService;
		this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }
    
    //method to register a user 

    public AuthenticationResponse register(RegisterRequest request) {
        var userDetails =
            org.springframework.security.core.userdetails.User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRole())
                //.roles(Role.USER.name())

                .build();
        // This has allowed me to catch an error I was having with the role of the user being set to null so since it shows that there
        // exists a value for userDetails.getAuthorities() it means that the issue lies with the creation of a User object using the 
        // fromUserDetails method of the User class
        System.out.println("The user " +  userDetails.getUsername()+ " has the role of : " + userDetails.getAuthorities());
        User customUser = User.fromUserDetails(userDetails);

        userService.createUser(customUser);
//        Map<String, Object> extraClaims = new HashMap<>();
//        extraClaims.put("role", Role.USER.name());

        var jwtToken = jwtTokenService.generateToken(userDetails);
        return AuthenticationResponse.builder().jwtToken(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (UsernameNotFoundException ex) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
       // User customUser = User.fromUserDetails(userDetails);

        //userDao.createUser(customUser);
        if (userDetails.getAuthorities().isEmpty()) {
            throw new IllegalStateException("User role is not defined");
        }



        var jwtToken = jwtTokenService.generateToken(userDetails);
        return AuthenticationResponse.builder().jwtToken(jwtToken).build();
    }
}
