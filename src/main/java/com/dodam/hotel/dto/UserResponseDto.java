package com.dodam.hotel.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class UserResponseDto {
	
	@Data
	public static class LoginResponseDto {
		private Integer id;
		private String email;
		private String password;
		private String name;
		private Boolean blacklist;
		private Boolean withdrawal;
		private String originEmail;
		private Boolean randomPwdStatus;
		private Boolean socialLogin;
		private String tel;
	}
	
	@Data
	public static class MyPageResponseDto {
		private Integer id;
		private String email;
		private String password;
		private String name;
		private String gender;
		private Date birth;
		private String tel;
		private String address;
	}
	
} // end of class
