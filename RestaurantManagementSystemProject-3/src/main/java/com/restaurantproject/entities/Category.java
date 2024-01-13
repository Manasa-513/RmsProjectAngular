package com.restaurantproject.entities;

import com.restaurantproject.dtos.CategoryDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Table(name="categories")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] img;

    public CategoryDto getCategoryDto()
    {
    	CategoryDto categoryDto =new CategoryDto();
    	categoryDto.setId(id);
    	categoryDto.setName(name);
    	categoryDto.setDescription(description);
    	categoryDto.setReturnedImg(img);
    	return categoryDto;
    }

}

