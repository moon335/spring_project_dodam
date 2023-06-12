package com.dodam.hotel.repository.model;

import lombok.Data;

@Data
public class Reply {
	
	private Integer Id;
	private String content;
	private Question question;
	private Manager manager;
	private Integer questionId;
	private Integer managerId;
	
} // end of class
