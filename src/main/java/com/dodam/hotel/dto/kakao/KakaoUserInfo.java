package com.dodam.hotel.dto.kakao;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoUserInfo {
    private Long id;
    private Boolean has_signed_up;
    private Date connected_at;
    private Date synched_at;
    private KakaoAccount kakao_account;
    private KakaoPartner for_partner;
}
