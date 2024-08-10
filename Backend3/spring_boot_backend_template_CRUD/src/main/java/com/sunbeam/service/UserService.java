package com.sunbeam.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.UserDTO;


public interface UserService {


	 UserDTO addNewUser(UserDTO dto);
	 UserDTO getUserDetails(String email, String pass);
	
}