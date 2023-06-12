package com.dodam.hotel.dto.api.pay;

import lombok.Data;

@Data
public class PayOption {
    private String paySelect;
    private String clientKey;
    private Integer total_amount;
    private String orderName;
}
