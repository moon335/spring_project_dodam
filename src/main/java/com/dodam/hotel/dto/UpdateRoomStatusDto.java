package com.dodam.hotel.dto;

import lombok.Data;

@Data
public class UpdateRoomStatusDto {
	
	private Integer id;
	private String name;
	private Integer price;
	private Boolean availability;
	private String description;
	private String image;
	private String statusDesc;
	private Integer numberOfP;
	
}
