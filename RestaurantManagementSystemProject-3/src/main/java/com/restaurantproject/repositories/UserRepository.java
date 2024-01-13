package com.restaurantproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantproject.entities.User;
import com.restaurantproject.enums.UserRole;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>
{
   Optional<User> findFirstByEmail(String email);

User findByUserRole(UserRole admin);
}
