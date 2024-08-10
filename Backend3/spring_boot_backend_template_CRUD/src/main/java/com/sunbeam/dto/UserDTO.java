package com.sunbeam.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.sunbeam.entities.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
	@JsonProperty(access = Access.READ_ONLY) // used during serialization
	private Long userId;
	@NotBlank
	private String name;
	@Email
	private String email;
	@Column(name = "phone_no" , length = 10)
	private String phoneNo;
	private String address;
	@JsonProperty(access = Access.WRITE_ONLY) //required only in de-ser.
	private String password;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String confirmPassword;
	//@JsonFormat(pattern ="HH:mm:ss",shape =JsonFormat.Shape.STRING)
//	@DateTimeFormat(pattern ="HH:mm:ss")
//	private LocalTime outTime;
	private Role role;
	
	
}
