package com.dodam.hotel.dto.api.kakaopay;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * @author lhs-devloper
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class KakaoAmount {
    private Integer total;
    private Integer taxFree;
    private Integer vat;
    private Integer point;
    private Integer discount;
    private Integer greenDeposit;
}
