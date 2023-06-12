package com.dodam.hotel.repository.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class FAQ {
    private Integer id;
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
