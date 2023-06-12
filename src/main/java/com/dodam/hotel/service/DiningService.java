package com.dodam.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dodam.hotel.repository.interfaces.DiningRepository;
import com.dodam.hotel.repository.model.Dining;
import com.dodam.hotel.repository.model.DiningDesc;

@Service
public class DiningService {

	@Autowired
	private DiningRepository diningRepository;

	@Transactional
	public List<DiningDesc> readAllDining(String type) {
		List<DiningDesc> diningDescEntitiys = diningRepository.findByTypeAllDining(type);
		return diningDescEntitiys;
	}
	
	@Transactional
	public List<Dining> readAllDining() {
		List<Dining> diningEntitiys = diningRepository.findAllDining();
		return diningEntitiys;
	}

	// 레스토랑 상태 조회
	public int readRestaurantStatus() {
		int resultRowCount = diningRepository.findStatusRestaurant();
		return resultRowCount;
	}

	// 라운지&바 상태 조회
	public int readBarStatus() {
		int resultRowCount = diningRepository.findStatusBar();
		return resultRowCount;
	}
	
	public Dining readDiningById(Integer id) {
		Dining dining = diningRepository.findDiningById(id);
		return dining;
	}

} // end of class
