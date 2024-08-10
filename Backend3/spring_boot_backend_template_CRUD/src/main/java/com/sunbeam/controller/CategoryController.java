package com.sunbeam.controller;

import java.io.IOException;

import java.util.List;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.ItemAddDto;
import com.sunbeam.entities.Category;
import com.sunbeam.service.CategoryService;
import com.sunbeam.service.ImageHandlingService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ImageHandlingService imageService;
	
	@PostMapping
	public ResponseEntity<?> addNewCategory(@RequestBody Category category){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(category));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping
	public List<Category> listCategories() {
		System.out.println("in list categories");
		return categoryService.getAllCategories();
	}
	
	
	@GetMapping("/get/{categoryName}")
	public ResponseEntity<?> getCategoryDetails(@PathVariable String categoryName)
	{
		try
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.getCategoryByCategoryName(categoryName));
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	@Operation(description = "Update category details")
	public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable Long id) {
		System.out.println("in update " + id + " " + category);
		try {
			return ResponseEntity.ok(new ApiResponse(categoryService.updateCategoryDetails(id, category)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		System.out.println("in delete " + id);
		try {
			return ResponseEntity.ok(new ApiResponse(categoryService.deleteCategoryDetails(id)));
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}

	}
	
	@PostMapping(value = "/images/{categoryId}",
			consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImage(@PathVariable 
			Long categoryId, 
			@RequestParam MultipartFile image)
			throws IOException {
		System.out.println("in upload image " + categoryId);
		return ResponseEntity.status(HttpStatus.CREATED).
				body(imageService.uploadImage(categoryId, image));
	}
	
	
	@GetMapping(value = "/images/{categoryId}", 
			produces = 
		{ IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	public ResponseEntity<?> downloadImage(@PathVariable long categoryId) throws IOException {
		System.out.println("in download image " + categoryId);
		return ResponseEntity.ok(imageService.serveImage(categoryId));
	}
}
