package com.dodam.hotel.dto.api.tosspay;

import com.dodam.hotel.dto.api.pay.PayApproveRequest;
import com.dodam.hotel.dto.api.pay.PayReadyInterface;
import lombok.Data;

@Data
public class TosspayRequest implements PayReadyInterface {
    private String paymentKey;
    private String orderId;
    private Integer amount;
    private String paymentType;
}
