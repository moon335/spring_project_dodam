package com.dodam.hotel.dto;

import lombok.Data;

@Data
public class CouponDto {
	
	private String name;
	private String content;
	private Integer userId;
	private String startDate;
	private String endDate;
}
