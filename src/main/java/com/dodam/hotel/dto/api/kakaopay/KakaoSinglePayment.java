package com.dodam.hotel.dto.api.kakaopay;

import com.dodam.hotel.dto.api.pay.PayApproveRequest;
import lombok.Data;

import java.sql.Date;

@Data
public class KakaoSinglePayment implements PayApproveRequest {
    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private String partner_order_id;
    private String partner_user_id;
    private String partner_method_type;
    private KakaoAmount amount;
    private KakaoCardInfo card_info;
    private String item_name;
    private String item_code;
    private Integer quantity;
    private Date created_at;
    private Date approved_at;
    private String payload;

}
