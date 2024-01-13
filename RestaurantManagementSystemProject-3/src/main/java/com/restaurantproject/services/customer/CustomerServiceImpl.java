package com.restaurantproject.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.restaurantproject.dtos.CategoryDto;
import com.restaurantproject.dtos.ProductDto;
import com.restaurantproject.dtos.ReservationDto;
import com.restaurantproject.entities.Category;
import com.restaurantproject.entities.Product;
import com.restaurantproject.entities.Reservation;
import com.restaurantproject.entities.User;
import com.restaurantproject.enums.ReservationStatus;
import com.restaurantproject.repositories.CategoryRepository;
import com.restaurantproject.repositories.ProductRepository;
import com.restaurantproject.repositories.ReservationRepository;
import com.restaurantproject.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	 private final CategoryRepository categoryRepository;
	  private final ProductRepository productRepository;
	  private final ReservationRepository  reservationRepository;
	  private final UserRepository userRepository;
	@Override
	public List<CategoryDto> getAllCategories() {
		return categoryRepository.findAll()
	            .stream()
	            .map(Category::getCategoryDto)
	            .collect(Collectors.toList());
	}
	@Override
	public List<CategoryDto> getCategoriesByName(String title) {
		return categoryRepository.findAllByNameContaining(title)
	            .stream()
	            .map(Category::getCategoryDto)
	            .collect(Collectors.toList());	
	}
	@Override
	public List<ProductDto> getProductsByCategory(Long categoryId) {
		return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
	}
	@Override
	public List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title) {
		return productRepository.findAllByCategoryIdAndNameContaining(categoryId,title).stream().map(Product::getProductDto).collect(Collectors.toList());
	}
	@Override
	public ReservationDto postReservation(ReservationDto reservationDto) {
		Optional<User> optionalUser=userRepository.findById(reservationDto.getCustomerId());
		if(optionalUser.isPresent()) {
			Reservation reservation =new Reservation();
			reservation.setTableType(reservationDto.getTableType());
			reservation.setDateTime(reservationDto.getDateTime());
			reservation.setDescription(reservationDto.getDescription());
			reservation.setUser(optionalUser.get());
			reservation.setReservationStatus(ReservationStatus.PENDING);
			Reservation postedReservation= reservationRepository.save(reservation);
			ReservationDto postedReservationDto = new ReservationDto();
			postedReservationDto.setId(postedReservation.getId());
			return postedReservationDto;
		}
		return null;
	}
	@Override
	public List<ReservationDto> getReservationsByUser(Long customerId) {
		return reservationRepository.findAllByUserId(customerId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
	}
}
