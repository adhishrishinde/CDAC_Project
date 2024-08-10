package com.sunbeam.controller;



import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sunbeam.dto.UserDTO;
import com.sunbeam.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> addNewUser(@RequestBody @Valid UserDTO dto) {
		System.out.println("in add user " + dto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.addNewUser(dto));
				//.body(UserService.addNewUser(dto));
	}
	
	@GetMapping("/{email}/{pass}")
	public ResponseEntity<?> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String pass)
	{
		
		return ResponseEntity.ok(userService.getUserDetails(email, pass));
		
	}


}
