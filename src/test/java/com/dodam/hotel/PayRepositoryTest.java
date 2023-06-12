package com.dodam.hotel;

import com.dodam.hotel.enums.Grade;
import com.dodam.hotel.repository.interfaces.PayRepository;
import com.dodam.hotel.repository.interfaces.ReservationRepository;
import com.dodam.hotel.repository.model.Pay;
import com.dodam.hotel.repository.model.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PayRepositoryTest {
    @Autowired
    private PayRepository payRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void PayObjectTest(){
        Reservation reservation = reservationRepository.findReservationById(53);
        String tid = reservation.getPayTid();
        Pay payInfo = payRepository.findByTidPay(tid);
        Integer point = payInfo.getPrice();
        if(Grade.DIA.equals(payInfo.getGradeName())){
            point = Integer.valueOf((int) Math.round(point * 0.07));
        }else if(Grade.GOLD.equals(payInfo.getGradeName())){
            point = Integer.valueOf((int) Math.round(point * 0.05));
        }else{
            point = Integer.valueOf((int) Math.round(point * 0.04));
        }

    }
}
