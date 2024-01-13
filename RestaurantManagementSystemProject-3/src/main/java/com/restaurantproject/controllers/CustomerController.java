package com.restaurantproject.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurantproject.dtos.CategoryDto;
import com.restaurantproject.dtos.ProductDto;
import com.restaurantproject.dtos.ReservationDto;
import com.restaurantproject.services.customer.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
 private final CustomerService customerService;
 @GetMapping("/categories")
	public ResponseEntity<List<CategoryDto>> getAllcategories()
	{
		List<CategoryDto> categoryDtoList= customerService.getAllCategories();
		if(categoryDtoList ==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(categoryDtoList);
	}
 @GetMapping("/categories/{title}")
	public ResponseEntity<List<CategoryDto>> getCategoriesByName(@PathVariable String title)
	{
		List<CategoryDto> categoryDtoList= customerService.getCategoriesByName(title);
		if(categoryDtoList ==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(categoryDtoList);
	}
    //product operations
 @GetMapping("/{categoryId}/products")
	public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId )
	{
		List<ProductDto> productDtoList= customerService.getProductsByCategory(categoryId);
		if(productDtoList ==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(productDtoList);
	}
 @GetMapping("/{categoryId}/product/{title}")
	public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title)
	{
		List<ProductDto> productDtoList=customerService.getProductsByCategoryAndTitle(categoryId,title);
		if(productDtoList ==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(productDtoList);
	}
 
   //Reservation 
    
 @PostMapping("/reservation")
	public ResponseEntity<?> postReservation(@RequestBody ReservationDto reservationDto) throws IOException
	{
	 ReservationDto postedReservationDto =customerService.postReservation(reservationDto);
		if(postedReservationDto==null) return new ResponseEntity<>("something went wrong",HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(postedReservationDto);
	}
 @GetMapping("/reservations/{customerId}")
	public ResponseEntity<List<ReservationDto>> getReservationsByUser(@PathVariable Long customerId )
	{
		List<ReservationDto> reservationDtoList= customerService.getReservationsByUser(customerId);
		if( reservationDtoList==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(reservationDtoList);
	}
 
}
