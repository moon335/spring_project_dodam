package com.dodam.hotel.repository.interfaces;

import com.dodam.hotel.repository.model.ReservationCancel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SinglePaymentRepository {
    ReservationCancel findReservationCancelByReservationId(Integer reservationId);

    int createReservationCancel(Integer reservationId, String description);
    int createRefund(Integer reservCancelId, Integer price);
    int createPayment(String tid, Integer price, Integer reservationId, String pgType);
}
