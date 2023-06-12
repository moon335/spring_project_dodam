package com.dodam.hotel.repository.model;

import java.text.DecimalFormat;

import lombok.Data;

@Data
public class Membership {
	
	private Integer id;
	private String price;
	private String content1;
	private String content2;
	private String content3;
	
	public String formatPrice() {
		DecimalFormat df = new DecimalFormat("###,###");
		String formatNumber = df.format(price);
		return formatNumber;
	}
}
