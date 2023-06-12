package com.dodam.hotel.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dodam.hotel.repository.interfaces.FacilitiesRepository;
import com.dodam.hotel.repository.model.Fitness;
import com.dodam.hotel.repository.model.Pool;
import com.dodam.hotel.repository.model.Spa;

import lombok.Data;

@Component
@Data
public class ReservationOptionPrice {
	// 메뉴 그냥 가격 고정(변경 필요)
	private final Integer diningPrice = 30_000;
	private Integer spaPrice;
	private Integer poolPrice;
	private Integer fitnessPrice;

	@Autowired
	private FacilitiesRepository facilitiesRepository;
	
	@Autowired
	public ReservationOptionPrice(
			FacilitiesRepository facilitiesRepository
			) {
		this.facilitiesRepository = facilitiesRepository;
		
		
		
		Spa spa = this.facilitiesRepository.findSpa();
		Pool pool = this.facilitiesRepository.findPool();
		Fitness fitness = this.facilitiesRepository.findFitness();
		this.spaPrice = spa.getPrice();
		this.poolPrice = pool.getPrice();
		this.fitnessPrice = fitness.getPrice();
	}
}
