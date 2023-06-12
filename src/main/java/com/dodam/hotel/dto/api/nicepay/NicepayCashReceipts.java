package com.dodam.hotel.dto.api.nicepay;

import lombok.Data;

@Data
public class NicepayCashReceipts {
    private String receiptTid;
    private String orgTid;
    private String status;
    private Long amount;
    private Long taxFreeAmt;
    private String receiptType;
    private String issueNo;
    private String receiptUrl;
}
