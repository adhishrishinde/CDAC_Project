package com.sunbeam.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.entities.Category;
import com.sunbeam.entities.Item;

public interface ImageHandlingService {
	
	ApiResponse uploadImage(Long categoryId, MultipartFile image) throws IOException;

	byte[] serveImage(Long categoryId) throws IOException;

	void uploadImage(Category category, MultipartFile image) throws IOException;
	
	ApiResponse uploadItemImage(Long itemId, MultipartFile image) throws IOException;

	byte[] serveItemImage(Long itemId) throws IOException;

	void uploadItemImage(Item item, MultipartFile image) throws IOException;

}
