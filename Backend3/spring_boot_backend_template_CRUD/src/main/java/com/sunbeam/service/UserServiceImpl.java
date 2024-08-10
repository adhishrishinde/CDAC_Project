package com.sunbeam.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sunbeam.custom_exception.ApiException;
import com.sunbeam.custom_exception.ResourceNotFoundException;
import com.sunbeam.custom_exception.ApiException;
//import com.sunbeam.custom_exceptions.ResourceNotFoundException;
import com.sunbeam.dao.UserDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.UserDTO;
import com.sunbeam.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userRepo;


	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDTO addNewUser(UserDTO dto) {

		// validate confirm password
		if (dto.getPassword().equals(dto.getConfirmPassword())) {
			// validate dept id
			User userEntity = mapper.map(dto, User.class);
			User savedUser = userRepo.save(userEntity);
			System.out.println("emp entity id " + userEntity.getUserId() + " " + savedUser.getUserId());
			return mapper.map(savedUser, UserDTO.class);			
		}
		throw new ApiException("Passwords don't match");

	}

	@Override
	public UserDTO getUserDetails(String email, String pass) {
		User user = userRepo.findByEmailAndPassword(email, pass);
		
		if(user == null)
			throw new ResourceNotFoundException("email or password incorrect !!!");
		
		return mapper.map(user, UserDTO.class);
	}

	
	

}
