package com.restaurantproject.services.auth;

import com.restaurantproject.dtos.SignUpRequest;
import com.restaurantproject.dtos.UserDto;

public interface AuthService {

  UserDto createUser(SignUpRequest signUpRequest);

}
