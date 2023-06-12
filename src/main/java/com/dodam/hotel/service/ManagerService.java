package com.dodam.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dodam.hotel.dto.ManagerSignInFormDto;
import com.dodam.hotel.dto.StatusParams;
import com.dodam.hotel.repository.interfaces.MUserRepository;
import com.dodam.hotel.repository.interfaces.ManagerRepository;
import com.dodam.hotel.repository.interfaces.RoomRepository;
import com.dodam.hotel.repository.model.GradeInfo;
import com.dodam.hotel.repository.model.MUser;
import com.dodam.hotel.repository.model.Manager;
import com.dodam.hotel.repository.model.MembershipInfo;
import com.dodam.hotel.repository.model.Room;
import com.dodam.hotel.util.PagingObj;

@Service
public class ManagerService {

	@Autowired // DI 처리
	private ManagerRepository managerRepository;

	@Autowired
	private MUserRepository mUserRepository;

	@Autowired
	private RoomRepository roomRepository;
	
	@Transactional
	public int readAllRoomListCount() {
		int resultCount = roomRepository.findAllRoomListCount();
		return resultCount;
	}
	
	@Transactional
	public List<Room> readAllRoomList() {
		List<Room> roomEntitys = roomRepository.findAllRoomList();
		return roomEntitys;
	}

	@Transactional
	public List<Room> readConditionsRoomList(StatusParams statusParams) {
		Boolean roomStatus = statusParams.getRoomStatus();
		Integer numberOfP = statusParams.getNumberOfP();
		Integer price = statusParams.getPrice();
		if(roomStatus == null) {
			statusParams.setRoomStatus(false);
		}else{
			statusParams.setRoomStatus(true);
		}

		List<Room> roomEntitys;
		// where 조건 or 절 사용해도 문제 없는 조건 리스트(1개만 서치)
		// 1. 방 상태만 걸려있는 경우
		// 2. 수용 인원만 걸려있는 경우
		// 3. 가격만 걸려있는경우
		// where 조건 and 절 무조건 사용해야하는 조건 리스트(2&3개만 서치)
		// 4. 방 & 수용 인원
		if (roomStatus != null && numberOfP != null && price == null) {
			roomEntitys = roomRepository.findOptionStatusAndNumberOfpRoomList(statusParams);
		}
		// 5. 방 & 가격
		else if (roomStatus != null && numberOfP == null && price != null) {
			roomEntitys = roomRepository.findOptionStatusAndPriceRoomList(statusParams);
		}
		// 6. 수용인원 & 가격
		else if (roomStatus == null && numberOfP != null && price != null) {
			roomEntitys = roomRepository.findOptionNumberOfpAndPriceRoomList(statusParams);
		}
		// 7. 전부 옵션이 걸려있는 경우
		else if (roomStatus != null && numberOfP != null && price != null) {
			roomEntitys = roomRepository.findAllOptionRoomList(statusParams);
		} else {
			// 1,2,3 조건
			roomEntitys = roomRepository.findOptionSearchOneRoomList(statusParams);
		}
		return roomEntitys;
	}

	@Transactional
	public Room readByRoom(Integer roomId) {
		Room roomEntity = roomRepository.findById(roomId);
		return roomEntity;
	}

	// 매니저 로그인
	@Transactional
	public Manager managerSignIn(ManagerSignInFormDto managerSignInFormDto) {
		Manager managerEntity = managerRepository.findByManagernameAndPassword(managerSignInFormDto);
		return managerEntity;
	}

	// 회원 전체 리스트 검색
	@Transactional
	public List<MUser> readUserListAllForManager() {
		List<MUser> userListEntity = mUserRepository.findByAll();
		return userListEntity;
	}
	
	//페이징
	@Transactional
	public List<MUser> readManagerUserListAllPaging(PagingObj obj) {
		List<MUser> userListEntity = mUserRepository.findByAllPaging(obj);
		return userListEntity;
	}
	
	@Transactional
	public Integer readByAllCount() {
		return mUserRepository.findByAllCount(); 
	}
	
	// 이름으로 회원 검색
	@Transactional
	public List<MUser> readUserByNameForManager(PagingObj obj,String name) {
		List<MUser> userListEntity = mUserRepository.findByNamePaging(obj, name);
		return userListEntity;
	}
	
	@Transactional
	public Integer readByNameCount(String name) {
		return mUserRepository.findByNameCount(name); 
	}
	
	//등급별 회원 조회
	@Transactional
	public List<GradeInfo> readUserGradeListForManager(PagingObj obj,Integer gradeId){
		List<GradeInfo> userListEntity = mUserRepository.findByGradeAll(obj,gradeId);
		return userListEntity;
	}
	
	@Transactional
	public Integer readByGradeAllCount(Integer gradeId) {
		return mUserRepository.findByGradeAllCount(gradeId); 
	}
	
	
	//블랙 리스트로 지정
	@Transactional
	public int updateBlackList(Integer id) {
		int resultRow = mUserRepository.updateBlackList(id);
		return resultRow;
	}
	
	//블랙 리스트로 해제
	@Transactional
	public int updateWhiteList(Integer id) {
		int resultRow = mUserRepository.updateWhiteList(id);
		return resultRow;
	}
	
	// 블랙리스트 조회
	@Transactional
	public List<MUser> readUserBlackListForManager(PagingObj obj) {
		List<MUser> userBlackListEntity = mUserRepository.findByBlackList(obj);
		return userBlackListEntity;
	}
	
	public int readByBlackListCount() {
		return mUserRepository.findByBlackListCount();
	}
	
	// 회원등급 조회
	@Transactional
	public GradeInfo readUserGrade(Integer id) {
		GradeInfo userGradeEntity = mUserRepository.findByUserId(id);
		return userGradeEntity;
	}
	
	//맴버쉽 회원 조회
	public List<MembershipInfo> readByMembershipUserList(PagingObj obj){
		List<MembershipInfo> membershipUserListEntity = mUserRepository.findByMembershipAll(obj);
		return membershipUserListEntity;		
	}
	public Integer readByMembershipAllCount() {
		return mUserRepository.findByMembershipAllCount(); 
	}
	// 회원 등급 수정
	@Transactional
	public int updateGradeByUserIdAndGradeId(Integer gradeId, Integer id) {
		int resultRowCount = mUserRepository.updateGrade(gradeId, id);	
		return resultRowCount;
	}

}
