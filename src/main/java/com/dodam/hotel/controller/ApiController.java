package com.dodam.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.dodam.hotel.handler.exception.CustomRestFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodam.hotel.dto.UserResponseDto;
import com.dodam.hotel.dto.api.ResponseMsg;
import com.dodam.hotel.repository.model.Dining;
import com.dodam.hotel.repository.model.MembershipInfo;
import com.dodam.hotel.repository.model.Reservation;
import com.dodam.hotel.repository.model.Room;
import com.dodam.hotel.repository.model.User;
import com.dodam.hotel.service.ChatService;
import com.dodam.hotel.service.DiningService;
import com.dodam.hotel.service.ManagerReservationService;
import com.dodam.hotel.service.ManagerService;
import com.dodam.hotel.service.ReservationService;
import com.dodam.hotel.service.RoomService;
import com.dodam.hotel.service.UserService;
import com.dodam.hotel.util.Define;
/**
 * 
 * @author 김현우
 *
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ManagerReservationService managerReservationService;
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private DiningService diningService;
	
	// 회원 정보 수정
	@GetMapping("/myInfo")
	public UserResponseDto.MyPageResponseDto updateUserInfo() {
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto)session.getAttribute(Define.PRINCIPAL);
		// 바뀐 회원정보 출력 용도
		UserResponseDto.MyPageResponseDto responseUser = userService.readUserByEmail(principal.getEmail());
		
		return responseUser;
	}
	
	/**
	 *  성희
	 *  구글 차트 ajax 작업
	 */
	// 매출 조회
	@GetMapping("/totalPrice")
	public List<Integer> totalPrice() {
	    Integer totalPrice = managerReservationService.readBeforeTodayTotalPrice();
	    List<Integer> price = new ArrayList<>();
	    for (int i = 6; i > 0; i--) {
	        Integer beforeTotalPrice = managerReservationService.readBeforeTotalPrice(i);
	        if (beforeTotalPrice == null) {
	            beforeTotalPrice = 0;
	        }
	        price.add(beforeTotalPrice);
	    }
	    price.add(totalPrice);
	    return price;
}
	
	// 회원가입 / 멤버쉽가입 카운트 조회 (성희)
	@GetMapping("/joinCount")
	public List<Integer> joinCount() {
		List<User> userToday = userService.readJoinUserToday();
		Integer memberCount = userToday.size();
		List<MembershipInfo> membershipToday = userService.readJoinMembershipToday();
		Integer membershipCount = membershipToday.size();
		
		List<Integer> countBox = new ArrayList<>();
		countBox.add(memberCount);
		countBox.add(membershipCount);
		
		return countBox;
	}
	
	// 아이디 중복검사(현우)
	@GetMapping("/duplicationUser")
	public ResponseMsg checkDuplicationUser(@RequestParam String email) {
		User responseUser = userService.readUserForDuplicationCheck(email);
		if(responseUser == null) {
			ResponseMsg successMsg = ResponseMsg
					.builder()
					.status_code(HttpStatus.OK.value())
					.msg("사용가능한 아이디 입니다.")
					.build();
			return successMsg;
		} else {
		 ResponseMsg failMsg = ResponseMsg
				 .builder()
				 // 대상 리소스의 현재 상태와 충돌하여 요청을 완료할 수 없음을 뜻한다.
				 .status_code(HttpStatus.CONFLICT.value())
				 .msg("중복된 아이디가 존재합니다.")
				 .build();
		 return failMsg;
		}
	}
	
	// 예약조회  (성희)
	@GetMapping("/reserve")
	public List<Reservation> reserveDetail(Integer id) {		
			List<Reservation> reservations = reservationService.readAllReservationByUserId(id);
			return reservations;
		}
	
	// 예약 전체 조회
	@GetMapping("/allReserve")
	public List<Reservation> reservation() {
		List<Reservation> reservations = managerReservationService.readAllReservation();
		return reservations;
	}
	
	@GetMapping("/findRoomInfo/{id}")
	public Room findRoomInfo(@PathVariable Integer id) {
		Room room = managerService.readByRoom(id);
		return room;
	}
	
	@GetMapping("/findDiningInfo/{id}")
	public Dining findDiningInfo(@PathVariable Integer id) {
		Dining dining = diningService.readDiningById(id);
		return dining;
	}
	
	@GetMapping("/checkNewMessage")
	public ResponseMsg checkNewMessage() {
		Integer responseCount = chatService.readNewMessageCount();
		if(responseCount == 0) {
			ResponseMsg noMessage = ResponseMsg
					.builder()
					.status_code(HttpStatus.BAD_REQUEST.value())
					.build();
			return noMessage;
		} else {
		 ResponseMsg createNew = ResponseMsg
				 .builder()
				 .status_code(HttpStatus.OK.value())
				 .build();
		 return createNew;
		}
	}

	@GetMapping("/checkMemberShip")
	public ResponseMsg checkMemberShip(){
		HttpStatus httpStatus;
		String returnMsg = null;
		UserResponseDto.LoginResponseDto user = (UserResponseDto.LoginResponseDto) session.getAttribute(Define.PRINCIPAL);
		Integer userId = user != null ? user.getId() : null;
		MembershipInfo membershipInfo = userService.readMemberShipInfoById(userId);
		String redirectURL = null;
		if(userId == null){
			httpStatus = HttpStatus.UNAUTHORIZED;
			returnMsg = "로그인 후 이용해주세요";
			redirectURL = "/login";
		}
		else if(membershipInfo != null){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			returnMsg = "이미 멤버쉽이 등록된 회원입니다";
		}else{
			httpStatus = HttpStatus.OK;
		}

		return ResponseMsg
				.builder()
				.status_code(httpStatus.value())
				.msg(returnMsg)
				.redirect_uri(redirectURL)
				.build();
	}
	
	@DeleteMapping("deleteDining")
	public ResponseMsg deleteDining(@RequestParam Integer reservationId) {
		Integer responseDining = reservationService.deleteDiningReservation(reservationId);
		if(responseDining == 1) {
			ResponseMsg successMsg = ResponseMsg
					.builder()
					.status_code(HttpStatus.OK.value())
					.msg("예약 취소에 성공했습니다.")
					.build();
			return successMsg;
		} else {
		 ResponseMsg failMsg = ResponseMsg
				 .builder()
				 // 대상 리소스의 현재 상태와 충돌하여 요청을 완료할 수 없음을 뜻한다.
				 .status_code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				 .msg("예약 취소에 실패했습니다.")
				 .build();
		 return failMsg;
		}
	}

} // end of class
