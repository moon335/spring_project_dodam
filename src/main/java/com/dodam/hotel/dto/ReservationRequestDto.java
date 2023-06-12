package com.dodam.hotel.dto;

import lombok.Data;

@Data
public class ReservationRequestDto {
	
	private String startDate;
	private String endDate;
	private String date;
	private Integer numberOfP;
	private Integer countPerson;
	private Integer countChild;
	private Integer countBaby;
	private Integer roomId;
	private Integer userId;
	private Integer diningId;
	private Integer spaId;
	private Integer poolId;
	private Integer fitnessId;
	private Integer price;
	private String name;
	private Integer totalPrice;
	private Integer day;
	// 부대시설 사용 여부 체크
	private Integer diningCount;
	private Integer spaCount;
	private Integer poolCount;
	private Integer fitnessCount;

	private Integer coupon;

	private String tid;
}
