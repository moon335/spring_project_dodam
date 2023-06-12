package com.dodam.hotel.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dodam.hotel.dto.ReservationRequestDto;
import com.dodam.hotel.dto.StatusParams;
import com.dodam.hotel.repository.model.Room;
import com.dodam.hotel.repository.model.RoomType;
import com.dodam.hotel.util.PagingObj;

@Mapper
public interface RoomRepository {
	
	// 전체 객실 조회 (카테고리별)
	public List<RoomType> findAllRoom(@Param("type") String type, @Param("name") String name);
	
	// 객실 상세페이지 조회
	public RoomType findRoomById(Integer id);
	
	// 예약된 객실 조회
	public List<Room> findRoombyDate(ReservationRequestDto reservationRequestDto); 
	
	//룸 사용가능 상태변경
	public int updateManagerRoom(@Param("id") Integer id, @Param("availability") Boolean availability, @Param("statusDesc") String statusDesc);
	
	// 모든 룸 개수 조회
	public int findAllRoomListCount();
	
	// 사용 가능 객실 개수 조회
	public int findRoomByCount1(); 
	
	// 사용 불가 객실 개수 조회
	public int findRoomByCount0();
	
	List<Room> findAllRoomList();
	List<Room> findAllRoomListPaging(PagingObj obj);
	
    // 인원 수
    List<Room> findOptionSearchOneRoomList(StatusParams statusParams);
    List<Room> findOptionStatusAndNumberOfpRoomList(StatusParams statusParams);
    // 현재 상태 및 인원 수
    List<Room> findOptionStatusAndPriceRoomList(StatusParams statusParams);
    // 인원 수 및 가격
    List<Room> findOptionNumberOfpAndPriceRoomList(StatusParams statusParams);

    // 모든 옵션 조건
    List<Room> findAllOptionRoomList(StatusParams statusParams);

    int insertRoom(Room room);
    
    Room findById(Integer roomId);
    
} // end of class
