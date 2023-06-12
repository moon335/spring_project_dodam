package com.dodam.hotel.repository.model;

import java.sql.Date;

public class ReservationCancel {
    private Integer id;
    private Integer reservationId;
    private Date cancelDate;
    private String description;
}
