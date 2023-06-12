package com.dodam.hotel.repository.model;

import lombok.Data;

@Data
public class Fitness {

	private Integer id;
	private String hours;
	private Boolean availability;
	private Integer price;
	private String statusDesc;
	private Facilities facilities;
	private FacilitiesDesc facilitiesDesc;
}
