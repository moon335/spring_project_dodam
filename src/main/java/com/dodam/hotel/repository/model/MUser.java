package com.dodam.hotel.repository.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.dodam.hotel.util.DateFormat;

import lombok.Data;

@Data
public class MUser {

	private int id;
	private String email;
	private String password; 
	private String name;
	private String gender;
	private String tel;
	private Date birth;
	private int blacklist;
	private int withdrawal;
	private Timestamp createdAt;
	private String originEmail;
	private Boolean socialLogin;
	
}
