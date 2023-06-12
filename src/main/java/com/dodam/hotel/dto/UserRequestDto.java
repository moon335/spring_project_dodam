package com.dodam.hotel.dto;

import java.sql.Date;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRequestDto {

	// 로그인 requestDto
	@Data
	public static class LoginFormDto {
		@NotBlank(message = "이메일을 입력해주세요.")
		@Email(message = "이메일 형식이 아닙니다.")
		private String email;
		@NotBlank(message = "비밀번호를 입력해주세요.")
		@Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
		private String password;
	}
	
	// 정보수정 용도
	@Data
	public static class MyPageFormDto {
		@Email(message = "이메일 형식이 아닙니다.")
		private String email;
		@NotBlank(message = "비밀번호를 입력해주세요.")
		@Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
		private String password;
		@NotBlank(message = "이름을 입력해주세요.")
		private String name;
		@NotBlank(message = "성별을 선택해주세요.")
		private String gender;
		private Date birth;
		@NotBlank(message = "전화번호를 입력해주세요. ")
		@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호는 010-0000-0000 형식으로 입력해야 합니다. ")
		private String tel;
		@NotBlank(message = "주소를 입력해주세요.")
		private String address;
		private String detailAddress;
		private String originEmail;
	}
	
	@Data
	public static class InsertDto {
		private Integer id;
		@Email(message = "이메일 형식이 아닙니다.")
		private String email;
		@NotBlank(message = "비밀번호를 입력해주세요.")
		@Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
		private String password;
		@NotBlank(message = "이름을 입력해주세요.")
		private String name;
		@NotBlank(message = "성별을 입력해주세요.")
		private String gender;
		private Date birth;
		@NotBlank(message = "전화번호를 입력해주세요. ")
		@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호는 010-0000-0000 형식으로 입력해야 합니다. ")
		private String tel;
		@NotBlank(message = "주소를 입력해주세요.")
		private String address;
		@NotBlank(message = "상세 주소를 입력해주세요.")
		private String detailAddress;
		private Boolean socialLogin;
		
	}
	
	// 비밀번호 변경
	@Data
	public static class UpdatePwdDto {
		@NotBlank(message = "현재 비밀번호를 입력해주세요.")
		private String currentPwd;
		@NotBlank(message = "새 비밀번호를 입력해주세요.")
		@Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
		private String changePwd;
		@NotBlank(message = "비밀번호 확인란을 입력해주세요.")
		private String checkChangePwd;
	}

}
