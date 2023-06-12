package com.dodam.hotel.repository.model;

import lombok.Data;

@Data
public class Coupon {
	
	private Integer id;
	private String name;
	private String content;
	private String startDate;
	private String endDate;
	private Integer couponId;
	private User user;
	private CouponInfo couponInfo;
	
}
