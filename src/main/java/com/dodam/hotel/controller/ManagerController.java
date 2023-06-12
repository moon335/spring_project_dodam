package com.dodam.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.dodam.hotel.dto.FacilitiesRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodam.hotel.dto.ManagerSignInFormDto;
import com.dodam.hotel.dto.StatusParams;
import com.dodam.hotel.dto.socket.ChatRoom;
import com.dodam.hotel.handler.SocketHandler;
import com.dodam.hotel.handler.exception.ManagerCustomRestFullException;
import com.dodam.hotel.repository.interfaces.ChatRepository;
import com.dodam.hotel.repository.model.GradeInfo;
import com.dodam.hotel.repository.model.MUser;
import com.dodam.hotel.repository.model.Manager;
import com.dodam.hotel.repository.model.MembershipInfo;
import com.dodam.hotel.repository.model.Reservation;
import com.dodam.hotel.repository.model.Room;
import com.dodam.hotel.repository.model.User;
import com.dodam.hotel.service.DiningService;
import com.dodam.hotel.service.EventService;
import com.dodam.hotel.service.FacilitiesService;
import com.dodam.hotel.service.ManagerReservationService;
import com.dodam.hotel.service.ManagerService;
import com.dodam.hotel.service.QuestionService;
import com.dodam.hotel.service.ReservationService;
import com.dodam.hotel.service.RoomService;
import com.dodam.hotel.service.UserService;
import com.dodam.hotel.util.Define;
import com.dodam.hotel.util.PagingObj;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	// 일단은 수정 필요한 코드
	@Autowired
	private ChatRepository chatRepository;
	private SocketHandler socketHandler = new SocketHandler(chatRepository);

	@Autowired
	private ManagerService managerService;
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	@Autowired
	private RoomService roomService;
	@Autowired
	private EventService eventService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private DiningService diningService;
	@Autowired
	private FacilitiesService facilitiesService;
	@Autowired
	private ManagerReservationService managerReservationService;
	@Autowired
	private ReservationService reservationService;

	@GetMapping("/managerPage")
	public String managerPage() {

		return "/manager/managerLogin";
	}
	
    @GetMapping("/payCancel")
    public String payCancel() {
    	return "/user/payCancel";
    }
	
	@GetMapping("/managerMain")
	public String managerMain(Model model) {
		model.addAttribute("event", eventService.readByIdLimit());
		model.addAttribute("question", questionService.readQuestionCountByStatus0());
		model.addAttribute("availableRoom", roomService.readRoomAvailableCount());
		model.addAttribute("notAvailableRoom", roomService.readRoomNotAvailableCount());
		model.addAttribute("restaurant", diningService.readRestaurantStatus());
		model.addAttribute("bar", diningService.readBarStatus());
		model.addAttribute("pool", facilitiesService.readPoolStatus());
		model.addAttribute("spa", facilitiesService.readSpaStatus());
		model.addAttribute("fitness", facilitiesService.readFitnessStatus());

		List<User> todayJoinUser = userService.readJoinUserToday();
		int todayJoinUserCount = todayJoinUser.size();
		List<MembershipInfo> todayJoinMembership = userService.readJoinMembershipToday();
		int todayJoinMembershipCount = todayJoinMembership.size();
		if (todayJoinUser != null) {
			model.addAttribute("userTodayCount", todayJoinUserCount);
		}
		if (todayJoinMembership != null) {
			model.addAttribute("membershipTodayCount", todayJoinMembershipCount);
		}

		int totalPrice = managerReservationService.readBeforeTodayTotalPrice();
		List<Integer> price = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			Integer beforetotalPrice = managerReservationService.readBeforeTotalPrice(i);
			if (beforetotalPrice == null) {
				beforetotalPrice = 0;
			}
			price.add(beforetotalPrice);
		}
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("price", price);

		return "/manager/managerMain";
	}

	@GetMapping("/managerLogout")
	public String managerLogout() {
		session.invalidate();
		return "redirect:/manager/managerPage";
	}

	// 유저 리스트
	@GetMapping("/userList")
	public String mUserListAll(Model model
			,@RequestParam(name ="nowPage", defaultValue = "1", required = false)String nowPage
			,@RequestParam(name ="cntPerPage", defaultValue = "5", required = false)String cntPerPage) {
		List<MUser> responseUsers = managerService.readUserListAllForManager();
		List<User> todayJoinUser = userService.readJoinUserToday();
		int todayJoinUserCount = todayJoinUser.size();
		List<MembershipInfo> todayJoinMembership = userService.readJoinMembershipToday();
		int todayJoinMembershipCount = todayJoinMembership.size();
		if (responseUsers != null) {
			model.addAttribute("userList", responseUsers);
		}
		if (todayJoinUser != null) {
			model.addAttribute("userTodayCount", todayJoinUserCount);
		}
		if (todayJoinMembership != null) {
			model.addAttribute("membershipTodayCount", todayJoinMembershipCount);
		}
		int total = managerService.readByAllCount();
		PagingObj obj = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", obj);
		model.addAttribute("viewAll",managerService.readManagerUserListAllPaging(obj));
		return "/manager/userList";
	}

	// 이름으로 유저 조회;
	@GetMapping("/userNameList")
	public String mUserList(String name, Model model
			,@RequestParam(name ="nowPage", defaultValue = "1", required = false)String nowPage
			,@RequestParam(name ="cntPerPage", defaultValue = "5", required = false)String cntPerPage){
		int total = managerService.readByNameCount(name);
		PagingObj obj = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		List<MUser> responseUsers = managerService.readUserByNameForManager(obj,name);
		model.addAttribute("paging", obj);
		model.addAttribute("viewAll", responseUsers);
		return "/manager/userList";
	}

	// 등급으로 유저 조회
	@GetMapping("/userGradeList")
	public String mUserGradeList(Integer gradeId, Model model
			,@RequestParam(name ="nowPage", defaultValue = "1", required = false)String nowPage
			,@RequestParam(name ="cntPerPage", defaultValue = "5", required = false)String cntPerPage) {
		int total = managerService.readByGradeAllCount(gradeId);
		PagingObj obj = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		List<GradeInfo> responseUserGrades = managerService.readUserGradeListForManager(obj, gradeId);
		if (responseUserGrades != null) {
			model.addAttribute("userList", responseUserGrades);
		}
		model.addAttribute("paging", obj);
		model.addAttribute("viewAll", responseUserGrades);
		return "/manager/userGrade";
	}
	
	// 회원 정보 상세 조회 or
	@GetMapping("/userDetail/{id}")
	public String userDetail(@PathVariable Integer id, Model model
			,@RequestParam(name ="nowPage", defaultValue = "1", required = false)String nowPage
			,@RequestParam(name ="cntPerPage", defaultValue = "5", required = false)String cntPerPage) {
		if(id == null) {
    		throw new ManagerCustomRestFullException("아이디가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
    	}
		int total = reservationService.readAllReservationByUserIdCount(id);
		PagingObj obj = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		List<Reservation> reservations = reservationService.readAllResrevationByUserIdPaging(obj, id);
		GradeInfo userGradeDetail = managerService.readUserGrade(id);
		if (userGradeDetail != null) {
			model.addAttribute("userDetail", userGradeDetail);
			model.addAttribute("paging", obj);
			model.addAttribute("reservations", reservations);
		}
		return "/manager/userDetail";
	}
	
	// 맴버쉽 회원 조회
	@GetMapping("/membershipUserList")
	public String membershipUserList(Model model
			,@RequestParam(name ="nowPage", defaultValue = "1", required = false)String nowPage
			,@RequestParam(name ="cntPerPage", defaultValue = "5", required = false)String cntPerPage) {
		int total = managerService.readByMembershipAllCount();
		PagingObj obj = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		List<MembershipInfo> responseMembershipUsers = managerService.readByMembershipUserList(obj);
		if (responseMembershipUsers != null) {
			model.addAttribute("userList", responseMembershipUsers);
		}
		model.addAttribute("paging", obj);
		model.addAttribute("viewAll", responseMembershipUsers);
		return "/manager/membershipUserList";
	}

	// 블랙리스트 조회
	@GetMapping("/blackList")
	public String mUserBlackList(Model model
			,@RequestParam(name ="nowPage", defaultValue = "1", required = false)String nowPage
			,@RequestParam(name ="cntPerPage", defaultValue = "5", required = false)String cntPerPage){
		int total = managerService.readByBlackListCount();
		PagingObj obj = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		List<MUser> userBlackList =  managerService.readUserBlackListForManager(obj);
		if (userBlackList != null) {
			model.addAttribute("userList", userBlackList);
		}
		model.addAttribute("paging", obj);
		model.addAttribute("viewAll", userBlackList);
		return "/manager/userBlackList";
	}

	// 매니저 로그인
	@PostMapping("/managerSignInProc")
	public String managersign(@Validated ManagerSignInFormDto managerSignInFormDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> {
				throw new ManagerCustomRestFullException(e.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			});
		}
		Manager principal = managerService.managerSignIn(managerSignInFormDto);
		if (principal != null) {
			session.setAttribute(Define.MANAGERPRINCIPAL, principal);
		}
		return "redirect:/manager/managerMain";
	}

	@GetMapping("/roomStatus")
	public String Check(StatusParams statusParams, Integer roomId, Model model) {
		List<Room> rooms;
		Room room = managerService.readByRoom(roomId);
		model.addAttribute("room", room);
		// 전체 조회
		if (statusParams.getRoomStatus() == null && statusParams.getPrice() == null
				&& statusParams.getNumberOfP() == null) {
			rooms = managerService.readAllRoomList();
		}
		// 선택 조회(?? 변경 필요)
		else {
			rooms = managerService.readConditionsRoomList(statusParams);
		}
		model.addAttribute("roomList", rooms);
		model.addAttribute("reservation", managerReservationService.readTodayReservation());
		return "/manager/status";
	}

	// 객실 정보 상세 조회
	@GetMapping("/roomStatusDetail")
	public String RoomStatusDetail(Integer roomId, Model model) {
		Room room = managerService.readByRoom(roomId);
		model.addAttribute("room", room);
		return "/manager/roomDetailStatus";
	}

	// 객실 사용가능 상태값 변경
	@PostMapping("/roomStatus/{id}")
	public String roomStatus(@PathVariable Integer id, Boolean availability, String statusDesc) {
		if(id == null) {
    		throw new ManagerCustomRestFullException("아이디가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
    	}
		roomService.updateRoomStatusTrueAndFalse(id, availability, statusDesc);
		return "redirect:/manager/roomStatus";
	}

	// 블랙 리스트 지정
	@GetMapping("/updateBlack/{id}")
	public String updateBlackList(@PathVariable Integer id) {
		if(id == null) {
    		throw new ManagerCustomRestFullException("아이디가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
    	}
		managerService.updateBlackList(id);
		return "redirect:/manager/userDetail/{id}";
	}

	// 블랙 리스트 해제
	@GetMapping("/updateWhite/{id}")
	public String updateWhiteList(@PathVariable Integer id) {
		if(id == null) {
    		throw new ManagerCustomRestFullException("아이디가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
    	}
		managerService.updateWhiteList(id);
		return "redirect:/manager/userDetail/{id}";
	}

	// 등급 수정
	@PostMapping("/updateGrade/{id}")
	public String updateGradeProc(@PathVariable Integer id, Integer gradeId) {
		if(id == null) {
    		throw new ManagerCustomRestFullException("아이디가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
    	}
		managerService.updateGradeByUserIdAndGradeId(gradeId, id);
		return "redirect:/manager/userDetail/" + id;
	}

	@GetMapping("/deleteBlackList")
	public String deleteBlackList() {
		return "redirect:/manager/";
	}

	@GetMapping("/chatRoomList")
	public String chatRoomList(Model model){
		List<ChatRoom> chatRoomList = socketHandler.viewAllRoom();
		model.addAttribute("chatRoomList", chatRoomList);
		return "/manager/chatRoomList";
	}

	@GetMapping("/chatRoom/{roomName}")
	public String chatRoom(@PathVariable String roomName, Model model){
		ChatRoom chatRoom = socketHandler.viewAllRoom().get(0);
		model.addAttribute("roomName", roomName);
		model.addAttribute("userName", chatRoom.getUserName());
		return "/manager/chatRoom";
	}

	@PostMapping("/facilities/update")
	public String updateFacilities(FacilitiesRequestDto facilitiesRequestDto){
		int result = facilitiesService.updateFacilites(facilitiesRequestDto);
		return "redirect:/manager/facilities";
	}
}
