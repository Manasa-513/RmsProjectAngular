package com.restaurantproject.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurantproject.dtos.UserDto;
import com.restaurantproject.entities.User;
import com.restaurantproject.repositories.UserRepository;
import com.restaurantproject.dtos.AuthenticationRequest;
import com.restaurantproject.dtos.AuthenticationResponse;
import com.restaurantproject.dtos.SignUpRequest;
import com.restaurantproject.services.auth.AuthService;
import com.restaurantproject.services.auth.jwt.UserService;
import com.restaurantproject.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController

{
   private final AuthService authService;
   
   private final AuthenticationManager authenticationManager;
   private final UserService userService;
   private final JwtUtil jwtUtil;
   private final UserRepository userRepository;

   
    
    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest)
    {
    	UserDto createdUserDto= authService.createUser(signUpRequest);
      if(createdUserDto==null)
	  {
		  return new ResponseEntity<>( "user not created.come again later" ,HttpStatus.BAD_REQUEST);  
		  
	  }
	  return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);

    }
    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws IOException
    {
    	try
    	{
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
    	}
    	catch(BadCredentialsException e)
    	{
    		throw new BadCredentialsException("incorrect username or password");
    		
    	}
    	catch(DisabledException disabledException)
    	{
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,"user not active");
    		return null;
    	}
		
    	final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
    	final String jwt=jwtUtil.generateToken(userDetails);
    	Optional<User> optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
    	AuthenticationResponse authenticationResponse=new AuthenticationResponse();
    	if(optionalUser.isPresent()) {
    		authenticationResponse.setJwt(jwt);
    		authenticationResponse.setUserRole(optionalUser.get().getUserRole());
    		authenticationResponse.setUserId(optionalUser.get().getId());
    	}
    	return authenticationResponse;
    }
}
