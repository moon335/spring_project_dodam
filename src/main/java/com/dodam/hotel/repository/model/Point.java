package com.dodam.hotel.repository.model;

import java.text.DecimalFormat;

import lombok.Data;

@Data
public class Point {
	private Integer id;
	private Integer point;
	private User user;
	private String endDate;
	
	public String formatPoint() {
		DecimalFormat df = new DecimalFormat("###,###");
		String formatNumber = df.format(point);
		return formatNumber;
	}
}
