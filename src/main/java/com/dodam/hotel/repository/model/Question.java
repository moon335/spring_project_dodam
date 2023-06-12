package com.dodam.hotel.repository.model;

import java.sql.Timestamp;

import com.dodam.hotel.util.DateFormat;

import lombok.Data;

/**
 * 
 * @author 김현우
 *
 */

@Data
public class Question {
	
	private Integer id;
	private String title;
	private String content;
	private Integer userId;
	private User user;
	private Timestamp createdAt;
	private String category;
	private String originFile;
	private String uploadFile;
	private Boolean status;
	
	public String formatDate() {
		String returnDate = DateFormat.dateFormat(this.createdAt);
		return returnDate;
	}
	
} // end of class
