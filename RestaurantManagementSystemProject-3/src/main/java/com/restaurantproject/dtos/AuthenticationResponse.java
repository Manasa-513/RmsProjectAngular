package com.restaurantproject.dtos;
import com.restaurantproject.enums.UserRole;

import lombok.Data;
@Data
public class AuthenticationResponse 
{
  private String jwt;
  private UserRole userRole;
  private Long userId;
}
