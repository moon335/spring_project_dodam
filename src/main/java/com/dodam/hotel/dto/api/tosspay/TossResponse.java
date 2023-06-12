package com.dodam.hotel.dto.api.tosspay;

import com.dodam.hotel.dto.api.pay.PayReadyResponse;
import lombok.Data;

@Data
public class TossResponse implements PayReadyResponse {
    private String version;
    private String paymentKey;
    private String type;
    private String orderId;
    private String orderName;
    private String mId;
    private String currency;
    private String method;
    private Integer totalAmount;
    private Integer balanceAmount;
    private String status;
    private String requestedAt;
    private String approvedAt;
    private Boolean useEscrow;
    private String lastTransactionKey;
    private Integer suppliedAmount;
    private Integer vat;
    private Boolean cultureExpense;
    private Integer taxFreeAmount;
    private Integer taxExemptionAmount;
    private Boolean isPartialCancelable;
    private String country;
}
