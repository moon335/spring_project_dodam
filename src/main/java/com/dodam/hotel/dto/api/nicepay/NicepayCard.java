package com.dodam.hotel.dto.api.nicepay;

import lombok.Data;

@Data
public class NicepayCard {
    private String cardCode;
    private String cardName;
    private String cardNum;
    private Integer cardQuota;
    private Boolean isInterestFree;
    private String cardType;
    private String canPartCancel;
    private String acquCardCode;
    private String acquCardName;
}
