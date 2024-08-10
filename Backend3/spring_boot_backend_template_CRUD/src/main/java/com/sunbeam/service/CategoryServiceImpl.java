package com.sunbeam.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.custom_exception.ResourceNotFoundException;
import com.sunbeam.dao.CategoryDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.CategoryResDto;
import com.sunbeam.entities.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse addCategory(Category category) {
		categoryDao.save(category);
		return new ApiResponse("New Category Added !!");
	}
	
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}
	
	
	public List<CategoryResDto> getCategoryByCategoryName(String categoryName) {

		List<Category> persistentCategory = categoryDao.findByCategoryName(categoryName);
		List<CategoryResDto> categoryDtoList = new ArrayList<>();
		for (Category category : persistentCategory) {
			categoryDtoList.add(mapper.map(category, CategoryResDto.class));
		}
		return categoryDtoList;
		
	}
	
	public String updateCategoryDetails(Long categoryId, Category category) {
		// validate if category exists !
		if (categoryDao.existsById(categoryId)) {
			// => exists
			Category persistentCategory = categoryDao.save(category);
			return "Category updated.....";
		}
		throw new ResourceNotFoundException("Invalid Category ID !!!!!!!!");
	}
	
	public String deleteCategoryDetails(Long categoryId) {
		if (categoryDao.existsById(categoryId)) {
			categoryDao.deleteById(categoryId);
			return "deleted category !!!";
		}
		throw new ResourceNotFoundException("Invalid category id !!!");
	}
}
