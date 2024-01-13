package com.restaurantproject.services.customer;

import java.util.List;

import com.restaurantproject.dtos.CategoryDto;
import com.restaurantproject.dtos.ProductDto;
import com.restaurantproject.dtos.ReservationDto;

public interface CustomerService {

	List<CategoryDto> getAllCategories();

	List<CategoryDto> getCategoriesByName(String title);

	List<ProductDto> getProductsByCategory(Long categoryId);

	List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title);

	ReservationDto postReservation(ReservationDto reservationDto);

	List<ReservationDto> getReservationsByUser(Long customerId);

}
