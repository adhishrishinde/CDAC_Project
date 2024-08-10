package com.sunbeam.dto;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.sunbeam.entities.Category;

public class ItemAddDto {

	    @NotBlank
	    private String itemName;
	    @NotBlank
	    private Float price;
	    @NotBlank 
	    private String itemDesc;
  
	//	private byte[] itemImage;

		public ItemAddDto() {
			super();
		}

		public ItemAddDto(@NotBlank String itemName, @NotBlank Float price, @NotBlank String itemDesc) {
			super();
			this.itemName = itemName;
			this.price = price;
			this.itemDesc = itemDesc;
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public Float getPrice() {
			return price;
		}

		public void setPrice(Float price) {
			this.price = price;
		}

		public String getItemDesc() {
			return itemDesc;
		}

		public void setItemDesc(String itemDesc) {
			this.itemDesc = itemDesc;
		}
//
//		public byte[] getItemImage() {
//			return itemImage;
//		}
//
//		public void setItemImage(byte[] itemImage) {
//			this.itemImage = itemImage;
//		}
		
		
}
