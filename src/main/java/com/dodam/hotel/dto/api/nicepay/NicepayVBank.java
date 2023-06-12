package com.dodam.hotel.dto.api.nicepay;

import lombok.Data;

/**
 * @author lhs-devloper
 */
@Data
public class NicepayVBank {
    private String vbankCode;
    private String vbankName;
    private String vbankNumber;
    private String vbankExpDate;
    private String vbankHolder;
}
