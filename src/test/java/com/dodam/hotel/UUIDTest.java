package com.dodam.hotel;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class UUIDTest {
    @Test
    public void uuidTest(){
        UUID uuid = UUID.randomUUID();
        Date date = new Date();
        System.out.println(String.valueOf(date.getTime())+ "-" + uuid);
    }
}
