package com.dodam.hotel.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class QuestionRequestDto {
	
	// 문의 등록 dto
	@Data
	public static class InsertQuestionRequestDto {
		@NotBlank(message = "제목을 입력해주세요.")
		private String title;
		@NotBlank(message = "내용을 입력해주세요.")
		private String content;
		private Integer userId;
		@NotBlank(message = "카테고리를 선택해주세요.")
		private String category;
		private MultipartFile file;
		private String uploadFile;
		private String originFile;
	}
	
}
