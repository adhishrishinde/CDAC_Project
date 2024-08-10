package com.sunbeam.controller;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.sunbeam.dto.ItemResDTO;
import com.sunbeam.entities.Category;
import com.sunbeam.entities.Item;
import com.sunbeam.service.ImageHandlingService;
import com.sunbeam.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ImageHandlingService imageService;
	
	@PostMapping("/{categoryId}")
	public ResponseEntity<?> addNewItem(@RequestBody ItemAddDto dto, @PathVariable Long categoryId){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(itemService.addItem(dto, categoryId));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/get/{categoryName}")
	public ResponseEntity<?> getVehicleDetails(@PathVariable String categoryName){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemByCategoryName(categoryName));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
		
	@PutMapping("/{itemId}")
	@Operation(summary = "Updating Complete item details")
	public ResponseEntity<?> updateItem(@PathVariable Long itemId, @RequestBody ItemResDTO dto) {
		System.out.println("in update proj " + itemId + " " + dto);
		return ResponseEntity.ok().body(itemService.updateItem(itemId, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable Long id) {
		System.out.println("in delete " + id);
		try {
			return ResponseEntity.ok(new ApiResponse(itemService.deleteItemDetails(id)));
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}

	}
	
	@PostMapping(value = "/images/{itemId}",
			consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImage(@PathVariable 
			Long itemId, 
			@RequestParam MultipartFile image)
			throws IOException {
		System.out.println("in upload image " + itemId);
		return ResponseEntity.status(HttpStatus.CREATED).
				body(imageService.uploadImage(itemId, image));
	}
	
	
	@GetMapping(value = "/images/{itemId}", 
			produces = 
		{ IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	public ResponseEntity<?> downloadImage(@PathVariable long itemId) throws IOException {
		System.out.println("in download image " + itemId);
		return ResponseEntity.ok(imageService.serveImage(itemId));
	}
}
