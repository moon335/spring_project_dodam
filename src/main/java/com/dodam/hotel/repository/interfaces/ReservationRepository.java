package com.dodam.hotel.repository.interfaces;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Past;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dodam.hotel.dto.ReservationRequestDto;
import com.dodam.hotel.repository.model.Reservation;
import com.dodam.hotel.repository.model.DiningReservation;
import com.dodam.hotel.util.PagingObj;

@Mapper
public interface ReservationRepository {

	// 다이닝 예약 (성희)
	public int insertReserveDining(ReservationRequestDto reservationRequestDto);

	// 특정 유저의 전체 예약 조회
	public List<Reservation> findAllReservationByUserId(@Param("userId") Integer userId);
	
	// 특정 유저의 전체 예약 조회
	public List<Reservation> findAllReservationByUserName(@Param("name") String name);

	// 특정 유저의 전체 예약 수 조회
	public int findAllReservationByUserIdCount(Integer UserId);

	// 특정 유저 전체 예약 조회(페이징, findAll---과 따로 쓰임)
	public List<Reservation> findAllReservationByUserIdPaging(@Param("obj") PagingObj obj,
			@Param("userId") Integer userId);
	
	// 다이닝 예약취소
	public Integer deleteDiningByReservationId(Integer reservationId);

	// 객실 예약 (성희)
	public int insertReserveRoom(ReservationRequestDto reservationRequestDto);

	public Reservation findReservationByUserIdSuccessful(Integer userId);

	List<Reservation> findReservationByUserId(Integer userId);

	List<Reservation> findTodayReservation();

	List<Reservation> findAllReservation();

	// 전체 예약 수 조회
	public int findAllReservationCount();

	// 전체 예약 조회(페이징, findAll---과 따로 쓰임)
	public List<Reservation> findAllReservationPaging(PagingObj obj);

	// 오늘 예약 수 조회
	public int findTodayReservationCount();

	// 오늘 예약 조회 페이징
	public List<Reservation> findTodayReservationPaging(PagingObj obj);

	int updateReservation(Reservation reservation);

	int deleteReservation(Integer id);

	Reservation findReservationById(Integer id);

	List<DiningReservation> findDiningReservation(Date date);

	List<DiningReservation> findAllDining();

	List<DiningReservation> findTodayDining();

	// 예약시 부대시설 사용가능 조회 - 민우

	// 오늘 예약 매출 조회
	public Integer findTodayTotalPrice();

	// 이전 매출 조회
	public Integer findBeforeTotalPrice(Integer count);

}