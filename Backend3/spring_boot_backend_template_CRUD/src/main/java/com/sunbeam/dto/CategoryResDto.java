package com.sunbeam.dto;

public class CategoryResDto {
	
	private String categoryName;

	public CategoryResDto() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CategoryResDto(String categoryName) {
		super();
		this.categoryName = categoryName;
	}



	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
