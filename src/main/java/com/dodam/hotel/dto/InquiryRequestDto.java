package com.dodam.hotel.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InquiryRequestDto {
	
	@Data
	public static class IdInquiryRequestDto {
		@NotBlank(message = "이름은 공백이 들어갈 수 없습니다.")
		private String name;
		private Date birth;
		@NotBlank(message = "전화번호를 입력해주세요. ")
		@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호는 010-0000-0000 형식으로 입력해야 합니다. ")
		private String tel;
	}
	
	@Data
	public static class PwInquiryRequestDto {
		@Email
		@NotBlank(message = "이메일은 공백이 들어갈 수 없습니다.")
		private String email;
		@NotBlank(message = "이름은 공백이 들어갈 수 없습니다.")
		private String name;
		private Date birth;
		@NotBlank(message = "전화번호를 입력해주세요. ")
		@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호는 010-0000-0000 형식으로 입력해야 합니다. ")
		private String tel;
		private String password;
	}
	
	
} // end of class
