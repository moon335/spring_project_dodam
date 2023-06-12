package com.dodam.hotel.dto.api.kakaopay;

import com.dodam.hotel.dto.api.pay.PayReadyInterface;
import lombok.Data;

@Data
public class KakaoRequestDto implements PayReadyInterface {
    private String item_name;
    private String total_amount;
    private String vat_amount;
}
