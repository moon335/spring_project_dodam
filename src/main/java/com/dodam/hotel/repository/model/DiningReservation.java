package com.dodam.hotel.repository.model;

import java.sql.Date;

import lombok.Data;

/**
 * @author lhs-devloper
 */
@Data
public class DiningReservation {
    private Integer id;
    private Date startDate;
    private Date endDate;
    private Integer numberOfP;
    private User user;
    private Dining dining;
}
