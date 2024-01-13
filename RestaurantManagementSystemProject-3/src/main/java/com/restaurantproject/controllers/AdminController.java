package com.restaurantproject.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurantproject.dtos.CategoryDto;
import com.restaurantproject.dtos.ProductDto;
import com.restaurantproject.dtos.ReservationDto;
import com.restaurantproject.services.admin.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
  private final AdminService adminService;
  @PostMapping("/category")
	public ResponseEntity<CategoryDto> postCategory(@ModelAttribute CategoryDto categoryDto) throws IOException
	{
		CategoryDto createdCategoryDto =adminService.postCategory(categoryDto);
		if(createdCategoryDto==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(createdCategoryDto);
	}
  @GetMapping("/categories")
	public ResponseEntity<List<CategoryDto>> getAllcategories()
	{
		List<CategoryDto> categoryDtoList=adminService.getAllCategories();
		if(categoryDtoList ==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(categoryDtoList);
	}
  @GetMapping("/categories/{title}")
 	public ResponseEntity<List<CategoryDto>> getAllcategoriesByTitle(@PathVariable String title)
 	{
 		List<CategoryDto> categoryDtoList=adminService.getAllcategoriesByTitle(title);
 		if(categoryDtoList ==null) return ResponseEntity.notFound().build();
 		return ResponseEntity.ok(categoryDtoList);
 	}
  
  //product operations
  @PostMapping("/{categoryId}/product")
 	public ResponseEntity<?> postProduct(@PathVariable Long categoryId ,@ModelAttribute ProductDto productDto) throws IOException
 	{
 		ProductDto createdProductDto =adminService.postProduct(categoryId,productDto);
 		if(createdProductDto==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
 		return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDto);
 	}
  
  @GetMapping("/{categoryId}/products")
	public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@PathVariable Long categoryId)
	{
		List<ProductDto> productDtoList=adminService.getAllProductsByCategory(categoryId);
		if(productDtoList ==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(productDtoList);
	}
  @GetMapping("/{categoryId}/product/{title}")
	public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title)
	{
		List<ProductDto> productDtoList=adminService.getProductsByCategoryAndTitle(categoryId,title);
		if(productDtoList ==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(productDtoList);
	}
  
  @DeleteMapping("/product/{productId}")
 	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId)
 	{
 		adminService.deleteProduct(productId);
 		
 		return ResponseEntity.noContent().build();
 	}
  @GetMapping("/product/{productId}")
	public ResponseEntity<ProductDto> getProductsById(@PathVariable Long productId)
	{
		ProductDto productDto=adminService.getProductsById(productId);
		if(productDto ==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(productDto);
	}
  @PutMapping("/product/{productId}")
	public ResponseEntity<?> updateProduct(@PathVariable Long productId ,@ModelAttribute ProductDto productDto) throws IOException
	{
		ProductDto updatedProductDto =adminService.updateProduct(productId,productDto);
		if(updatedProductDto==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		return ResponseEntity.status(HttpStatus.OK).body(updatedProductDto);
	}

  //reservation
  
  @GetMapping("/reservations")
	public ResponseEntity<List<ReservationDto>> getReservations()
	{
		List<ReservationDto> reservationDtoList= adminService.getReservations();
		if( reservationDtoList==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(reservationDtoList);
	}
  @GetMapping("/reservation/{reservationId}/{status}")
 	public ResponseEntity<ReservationDto> changeReservationStatus(@PathVariable Long reservationId,@PathVariable String status )
 	{
 		ReservationDto updatedReservationDto= adminService.changeReservationStatus(reservationId,status);
 		if( updatedReservationDto==null) return ResponseEntity.notFound().build();
 		return ResponseEntity.ok(updatedReservationDto);
 	}
}
