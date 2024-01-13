package com.restaurantproject.services.admin;

import java.io.IOException;
import java.util.List;

import com.restaurantproject.dtos.CategoryDto;
import com.restaurantproject.dtos.ProductDto;
import com.restaurantproject.dtos.ReservationDto;

public interface AdminService {

	CategoryDto postCategory(CategoryDto categoryDto) throws IOException;

	List<CategoryDto> getAllCategories();

	List<CategoryDto> getAllcategoriesByTitle(String title);

	ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException;

	List<ProductDto> getAllProductsByCategory(Long categoryId);

	List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title);

	void deleteProduct(Long productId);

	ProductDto getProductsById(Long productId);

	ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException;

	List<ReservationDto> getReservations();

	ReservationDto changeReservationStatus(Long reservationId, String status);

}
