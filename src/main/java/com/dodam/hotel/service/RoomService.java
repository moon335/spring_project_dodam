package com.dodam.hotel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dodam.hotel.dto.ReservationRequestDto;
import com.dodam.hotel.repository.interfaces.RoomRepository;
import com.dodam.hotel.repository.model.Room;
import com.dodam.hotel.repository.model.RoomType;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	// 모든 객실 조회
	@Transactional
	public List<RoomType> readAllRoom(String type) {
		String name = "%" + type + "%";
		List<RoomType> rooms = roomRepository.findAllRoom(type, name);
		
		return rooms;
	}
	
	// 특정 객실 조회
	@Transactional
	public RoomType readRoomById(Integer id) {
		RoomType roomEntity = roomRepository.findRoomById(id);
		return roomEntity;
	}
	
	//룸 사용가능 상태 변경 <- 매니저 부분
	@Transactional
	public int updateRoomStatusTrueAndFalse(Integer id, Boolean availability, String statusDesc) {
		int roomEntity = roomRepository.updateManagerRoom(id, availability, statusDesc);
		return roomEntity;
	}
	
	// 사용 가능 객실 갯수 조회
	@Transactional
	public int readRoomAvailableCount() {
		int resultRowCount = roomRepository.findRoomByCount1();
		return resultRowCount;
	}
	
	// 사용 불가 객실 객수 조회
	@Transactional
	public int readRoomNotAvailableCount() {
		int resultRowCount = roomRepository.findRoomByCount0();
		return resultRowCount;
	}
	
	/**
	 *  전체 예약 객실 조회 (성희)
	 */
	@Transactional
	public Map<String, Object> readRoomAvailablebyDate(ReservationRequestDto requestDto) {
		String[] array = requestDto.getDate().split(" to ");
		Integer countAll = requestDto.getCountPerson() + requestDto.getCountChild() + requestDto.getCountBaby();
		requestDto.setNumberOfP(countAll);
		// 출력
		for (int i = 0; i < array.length; i++) {
			requestDto.setStartDate(array[0]);
			requestDto.setEndDate(array[1]);
		}
		List<Room> roomList = roomRepository.findRoombyDate(requestDto);
		Map<String, Object> selectList = new HashMap<>();
		selectList.put("roomList", roomList);
		selectList.put("searchDto", requestDto);
		
		return selectList;
	}
	
} // end of class
