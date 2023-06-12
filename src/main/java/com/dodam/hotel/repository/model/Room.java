package com.dodam.hotel.repository.model;

import lombok.Data;

@Data
public class Room {
	
	private Integer id;
	private Boolean availability;
	private String statusDesc;
	private Integer roomTypeId;
	
	private RoomType roomType;
	
} // end of class
