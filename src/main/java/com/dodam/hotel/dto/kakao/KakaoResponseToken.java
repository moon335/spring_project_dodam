package com.dodam.hotel.dto.kakao;

import lombok.Data;

@Data
public class KakaoResponseToken {
    private String token_type;
    private String access_token;
    private String id_token;
    private Integer expires_in;
    private String refresh_token;
    private Integer refresh_token_expires_in;
    private String scope;
}
