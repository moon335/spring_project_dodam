package com.dodam.hotel.repository.model;

import java.sql.Date;

import java.sql.Timestamp;
import java.text.DecimalFormat;

import org.hibernate.validator.constraints.Range;

import com.dodam.hotel.util.DateFormat;

import lombok.Data;

@Data
public class Reservation {
	
    private Integer id;
    private Date startDate;
    private Date endDate;
    @Range(min = 1, max = 5, message = "최소 1명부터 최대 5명까지 선택할 수 있습니다.")
    private Integer numberOfP;
    private Integer userId;
    private Integer roomId;
    private Integer totalPrice;
    private Timestamp createdAt;
    private Integer diningId;
    private Integer fitnessId;
    private Integer poolId;
    private Integer spaId;
    private String payTid;

    // ========= Mapping ===========
    private User user;
    private Room room;
    private Dining dining;
    private Fitness fitness;
    private Pool pool;
    private Spa spa;
    
    public String dateFormat() {
		String formatDate = DateFormat.dateFormat(this.createdAt);
		return formatDate;
	}
    
    public String formatPrice() {
		DecimalFormat df = new DecimalFormat("###,###");
		String formatNumber = df.format(totalPrice);
		return formatNumber;
	}
}
