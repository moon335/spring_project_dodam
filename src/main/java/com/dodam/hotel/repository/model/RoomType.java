package com.dodam.hotel.repository.model;

import java.text.DecimalFormat;

import lombok.Data;

@Data
public class RoomType {
	
	private Integer id;
	private String name;
	private Integer price;
	private String description;
	private String image;
	private String image2;
	private String image3;
	private Integer numberOfP;
	
	public String formatPrice() {
		DecimalFormat df = new DecimalFormat("###,###");
		String formatNumber = df.format(price);
		return formatNumber;
	}
} // end of class
