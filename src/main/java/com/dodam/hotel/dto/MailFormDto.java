package com.dodam.hotel.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class MailFormDto {
    private String subject;
    private String content;
    private String from;
    private String to;
}
