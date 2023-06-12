package com.dodam.hotel.dto.kakao;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoAccount {
    private Boolean profile_needs_agreement;
    private Boolean profile_nickname_needs_agreement;
    private Boolean profile_image_needs_agreement;
    private KakaoProfile profile;
    private Boolean name_needs_agreement;
    private String name;
    private Boolean is_email_valid;
    private String email;
    private Boolean age_range_needs_agreement;
    private String age_range;
    private Boolean birthyear_needs_agreement;
    private String birthday;
    private String birthday_type;
    private String gender;
    private Boolean phone_number_needs_agreement;
    private String phone_number;
    private Boolean ci_needs_agreement;
    private String ci;
    private Date ci_authenticated_at;

}
