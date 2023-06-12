package com.dodam.hotel.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PayServiceTest {
    @Autowired
    private PayService payService;

    @Test
    public void refundTest(){
        Integer userId  = 25;
        Integer reservationId = 53;
        boolean isSuccess = payService.refundPay(reservationId, userId);
        Assertions.assertEquals(true, isSuccess);
    }

}