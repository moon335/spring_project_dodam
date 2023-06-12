package com.dodam.hotel.dto.api.nicepay;

import com.dodam.hotel.dto.api.pay.PayReadyInterface;
import lombok.Data;

@Data
public class NicepayDto implements PayReadyInterface {
    private String tid;
    private Long amount;
    private String orderId;
}
