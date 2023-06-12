package com.dodam.hotel.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ManagerSignInFormDto {
	@NotBlank(message = "아이디를 입력해주세요.")
	private String username;
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;
	
}
