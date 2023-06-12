package com.dodam.hotel.repository.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Event {
	
	private Integer id;
	private Date startDate;
	private Date endDate;
	private String title;
	private String content;
	private String uploadFile;
	private String originFile;
	
}
