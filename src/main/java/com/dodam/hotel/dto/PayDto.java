package com.dodam.hotel.dto;

import com.dodam.hotel.enums.Grade;
import com.dodam.hotel.enums.PGType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayDto {
    private String payTid;
    private Integer price;
    private PGType pgType;
    private String grade;
}
