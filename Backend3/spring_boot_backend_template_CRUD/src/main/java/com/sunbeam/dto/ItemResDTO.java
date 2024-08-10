package com.sunbeam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemResDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long itemId;
	private String itemName;
	private float price;
	private String itemDesc;
	
	
}
