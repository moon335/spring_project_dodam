package com.dodam.hotel.dto.api.kakaopay;

import com.dodam.hotel.dto.api.pay.PayReadyResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
public class KakaoPaymentDto implements PayReadyResponse{
    private String tid;
//    private String tid;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
    private String android_app_scheme;
    private String ios_app_scheme;
    private Date created_at;
}
