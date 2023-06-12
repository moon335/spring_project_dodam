package com.dodam.hotel.dto.api.pay;

public interface PayInterface {
    PayReadyResponse payReady();
    PayReadyResponse payReady(PayReadyInterface payReadyInterface);
    PayApproveRequest payApprove(String token);
}
