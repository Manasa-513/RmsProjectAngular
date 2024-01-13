package com.restaurantproject.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurantproject.dtos.ProductDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String price;
    private String description;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] img;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name ="category_id",nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;
    
    public ProductDto getProductDto(){
    	ProductDto productDto=new ProductDto();
    	productDto.setId(id);
    	productDto.setName(name);
    	productDto.setPrice(price);
    	productDto.setDescription(description);
    	productDto.setReturnedImg(img);
    	productDto.setCategoryId(category.getId());
    	productDto.setCategoryName(category.getName());
    	return productDto;
    }
    

}
