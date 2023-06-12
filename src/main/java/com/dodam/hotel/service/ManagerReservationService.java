package com.dodam.hotel.service;

import java.sql.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dodam.hotel.handler.exception.ManagerCustomRestFullException;
import com.dodam.hotel.repository.interfaces.DiningRepository;
import com.dodam.hotel.repository.interfaces.MUserRepository;
import com.dodam.hotel.repository.interfaces.ReservationRepository;
import com.dodam.hotel.repository.interfaces.RoomRepository;
import com.dodam.hotel.repository.model.Dining;
import com.dodam.hotel.repository.model.DiningReservation;
import com.dodam.hotel.repository.model.MUser;
import com.dodam.hotel.repository.model.Reservation;
import com.dodam.hotel.repository.model.Room;
import com.dodam.hotel.util.PagingObj;

/**
 * @author lhs-devloper
 */
@Service
public class ManagerReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private MUserRepository mUserRepository;
	@Autowired
	private DiningRepository diningRepository;

	@Transactional
	public List<Reservation> readUserReservation(String name) {
		List<Reservation> reservationList = new ArrayList<>();
		List<MUser> userEntitys = mUserRepository.findByName(name);
		userEntitys.stream().forEach((user) -> {
			List<Reservation> reservationUserList = reservationRepository.findReservationByUserId(user.getId());
			reservationUserList.forEach((reservation -> {
				reservationList.add(reservation);
			}));
		});

		return reservationList;
	}

	// 오늘 예약 정보
	@Transactional
	public List<Reservation> readTodayReservation() {
		List<Reservation> reservationEntitys = reservationRepository.findTodayReservation();
		return reservationEntitys;
	}

	// 오늘 예약 정보 페이징
	@Transactional
	public List<Reservation> readTodayReservationPaging(PagingObj obj) {
		List<Reservation> reservationEntitys = reservationRepository.findTodayReservationPaging(obj);
		return reservationEntitys;
	}

	// 오늘 예약 정보 카운트
	@Transactional
	public int readTodayReservationCount() {
		int resultCount = reservationRepository.findTodayReservationCount();
		return resultCount;
	}

	// 전체 예약 정보
	@Transactional
	public List<Reservation> readAllReservation() {
		List<Reservation> reservationEntitys = reservationRepository.findAllReservation();
		return reservationEntitys;
	}

	@Transactional
	public Map<String, Object> readReservationById(Integer id) {
		Map<String, Object> mapList = new HashMap<>();
		Reservation reservationEntity = reservationRepository.findReservationById(id);
		List<Room> roomEntitys = roomRepository.findAllRoomList();
		List<Dining> diningEntitys = diningRepository.findAllDining();
		mapList.put("reservation", reservationEntity);
		mapList.put("roomList", roomEntitys);
		mapList.put("diningList", diningEntitys);

		return mapList;
	}

	// 모든 예약 정보 카운트
	@Transactional
	public int readAllReservationCount() {
		int resultCount = reservationRepository.findAllReservationCount();
		return resultCount;
	}

	// 모든 예약 정보 페이징
	@Transactional
	public List<Reservation> readAllReservationPaging(PagingObj obj) {
		List<Reservation> reservationEntitys = reservationRepository.findAllReservationPaging(obj);
		return reservationEntitys;
	}

	@Transactional
	public int updateReservation(Reservation reservation) {
		int result = reservationRepository.updateReservation(reservation);
		if (result == 0) {
			// Exception Error
			throw new ManagerCustomRestFullException("예약 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@Transactional
	public int deleteReservation(Integer id) {
		int result = reservationRepository.deleteReservation(id);
		return result;
	}

	// 다이닝 날짜 조회
	@Transactional
	public List<DiningReservation> readDiningReservationAllListByDate(Date date) {
		List<DiningReservation> diningReservationEntitys = reservationRepository.findDiningReservation(date);
		return diningReservationEntitys;
	}

	// 다이닝 오늘 조회
	@Transactional
	public List<DiningReservation> readTodayDining() {
		List<DiningReservation> diningReservationList = reservationRepository.findTodayDining();
		return diningReservationList;
	}

	// 다이닝 전체 조회
	@Transactional
	public List<DiningReservation> readAllDining() {
		List<DiningReservation> diningReservationList = reservationRepository.findAllDining();
		return diningReservationList;
	}

	// 오늘 예약 매출 조회
	@Transactional
	public int readBeforeTodayTotalPrice() {
		int resultRowCount = reservationRepository.findTodayTotalPrice() == null ? 0
				: reservationRepository.findTodayTotalPrice();
		return resultRowCount;
	}

	// 이전 예약 매출 조회
	@Transactional
	public Integer readBeforeTotalPrice(Integer count) {
		Integer resultRowCount = reservationRepository.findBeforeTotalPrice(count);
		if (resultRowCount == null) {
			resultRowCount = 0;
		}
		return resultRowCount;
	}

}
