package com.sunbeam.service;

import java.util.List;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.CategoryResDto;
import com.sunbeam.entities.Category;

public interface CategoryService {
    
	ApiResponse addCategory(Category category);
	
	List<Category> getAllCategories();
	
	List<CategoryResDto> getCategoryByCategoryName( String categoryName);

	String updateCategoryDetails(Long categoryId,Category category);

	String deleteCategoryDetails(Long categoryId);

    
}
