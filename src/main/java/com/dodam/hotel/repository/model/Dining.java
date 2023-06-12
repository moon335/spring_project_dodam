package com.dodam.hotel.repository.model;

import lombok.Data;

@Data
public class Dining {
	
	private Integer id;
	private String name;
	private String hours;
	private String location;
	private Boolean availability;
	private String statusDesc;
	private String image1;
	private String image2;
	private String image3;
	
} // end of class
