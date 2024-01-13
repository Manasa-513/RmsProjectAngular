package com.restaurantproject.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantproject.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

	List<Category> findAllByNameContaining(String title);

	

}
