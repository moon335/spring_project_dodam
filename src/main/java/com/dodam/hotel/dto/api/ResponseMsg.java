package com.dodam.hotel.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMsg {
    private Integer status_code;
    private String msg;
    private String redirect_uri;
}
