package com.restaurantproject.services.auth;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurantproject.enums.UserRole;
import com.restaurantproject.dtos.SignUpRequest;
import com.restaurantproject.dtos.UserDto;
import com.restaurantproject.entities.User;
import com.restaurantproject.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AuthServiceImplementation implements AuthService

{
	
	private final UserRepository userRepository;
	
	
	

	public AuthServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
		
	}
   @PostConstruct
   public void createAdminAccount() {
	   User adminAccount=userRepository.findByUserRole(UserRole.ADMIN);
	   if(adminAccount == null) {
		   User user = new User();
		   user.setName("admin");
		   user.setEmail("admin@gmail.com");
		   user.setPassword(new BCryptPasswordEncoder().encode("admin"));
		   user.setUserRole(UserRole.ADMIN);
		   userRepository.save(user);
	   }
   }

	@Override
	public UserDto createUser(SignUpRequest signUpRequest) {
		User user=new User();
		user.setName(signUpRequest.getName());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode( signUpRequest.getPassword()));
		user.setUserRole(UserRole.CUSTOMER);
		User createdUser= userRepository.save(user);
		UserDto createdUserDto=new UserDto();
		createdUserDto.setId(createdUser.getId());
		createdUserDto.setName(createdUser.getName());
		createdUserDto.setEmail(createdUser.getEmail());
		createdUserDto.setUserRole(createdUser.getUserRole());
		
		
		return createdUserDto;
	}

}
