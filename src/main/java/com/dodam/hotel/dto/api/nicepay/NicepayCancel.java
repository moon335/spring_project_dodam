package com.dodam.hotel.dto.api.nicepay;

import lombok.Data;

@Data
public class NicepayCancel {
	private String tid;
    private Integer amount;
    private String cancelledAt;
    private String reason;
    private String receiptUrl;
    private Integer couponAmt;
}
