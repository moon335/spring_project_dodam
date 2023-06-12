package com.dodam.hotel.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodam.hotel.dto.ReservationRequestDto;
import com.dodam.hotel.dto.UserResponseDto;
import com.dodam.hotel.handler.exception.CustomRestFullException;
import com.dodam.hotel.repository.model.Pay;
import com.dodam.hotel.repository.model.Reservation;
import com.dodam.hotel.service.PayService;
import com.dodam.hotel.service.ReservationService;
import com.dodam.hotel.util.DateFormat;
import com.dodam.hotel.util.Define;
import com.dodam.hotel.util.PagingObj;
import com.dodam.hotel.util.ReservationOptionPrice;

@Controller
public class ReservationController {
	
	@Autowired
	private ReservationOptionPrice reservationOptionPrice;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private PayService payService;
	
	// 메인페이지 -> 예약버튼 처리 (성희)
	@GetMapping("/reserve")
	public String reserveMain(ReservationRequestDto requestDto, Model model) {
		if(requestDto == null) {
			throw new CustomRestFullException("예약 정보가 제대로 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
		}
		String[] array = requestDto.getDate().split(" to ");
		Integer countAll = requestDto.getCountPerson() + requestDto.getCountChild() + requestDto.getCountBaby();
		requestDto.setNumberOfP(countAll);
		requestDto.setStartDate(array[0]);
		requestDto.setEndDate(array[1]);
		
		return null;
	}

	// 예약 페이지
	@GetMapping("/selectDate")
	public String selectDate() {
		return "/reservation/selectDate";
	}

	// 다이닝 예약 처리
	@PostMapping("/dining")
	public String reserveDining(ReservationRequestDto requestDto) {
		if(requestDto == null) {
			throw new CustomRestFullException("예약 정보가 제대로 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
		}
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto)session.getAttribute(Define.PRINCIPAL);
		reservationService.createReserveDining(requestDto, principal.getId());
		return "redirect:/";
	}
	
	// 객실 예약 상세 페이지
	@GetMapping("/reserveRoom")
	public String reservation(ReservationRequestDto selectReserveDetail, Model model) {
		if(selectReserveDetail == null) {
			throw new CustomRestFullException("예약 정보가 제대로 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
		}
		model.addAttribute("selectDetail", selectReserveDetail);
		model.addAttribute("diningPrice", reservationOptionPrice.getDiningPrice());
		model.addAttribute("spaPrice", reservationOptionPrice.getSpaPrice());
		model.addAttribute("poolPrice", reservationOptionPrice.getPoolPrice());
		model.addAttribute("fitnessPrice", reservationOptionPrice.getFitnessPrice());
		
		model.addAttribute("diningStatus", reservationService.readDiningStatus().get(0));
		model.addAttribute("fitnessStatus", reservationService.readFitnessStatus().get(0));
		model.addAttribute("poolStatus", reservationService.readPoolStatus().get(0));
		model.addAttribute("spaStatus", reservationService.readSpaStatus().get(0));
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto)session.getAttribute(Define.PRINCIPAL);
		selectReserveDetail.setUserId(principal.getId());
		Map<String, Object> selectList = reservationService.readAvailableCouponOrPoint(selectReserveDetail);
		
		model.addAttribute("point", selectList.get("point"));
		model.addAttribute("couponList", selectList.get("couponList"));
		model.addAttribute("orderName", UUID.randomUUID().toString() + System.currentTimeMillis());
		return "/reservation/reservation";
	}
	
	// 객실 예약 처리
	@PostMapping("/reserveRoom")
	public String reserveRoom(ReservationRequestDto requestDto) {
		if(requestDto == null) {
			throw new CustomRestFullException("예약 정보가 제대로 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
		}
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto)session.getAttribute(Define.PRINCIPAL);
		reservationService.createReserveRoom(requestDto, principal.getId());
		return "redirect:/reservationSuccessful";
	}
	
	//예약후 예약 정보 확인 페이지
	@GetMapping("/reservationSuccessful")
	public String reservationSuccessful(Model model) {
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto)session.getAttribute(Define.PRINCIPAL);
		Reservation reservationSuccessful = reservationService.readReservationByUserIdSuccessful(principal.getId());
		model.addAttribute("successful", reservationSuccessful);
		Pay payType = payService.searchType(reservationSuccessful.getPayTid());
		model.addAttribute("payType", payType);
		return "/reservation/reservationSuccessful";
	}
	
	// 특정 유저 예약 현황 - 현우
	@GetMapping("/myReservations")
	public String myReservations(Model model,
			@RequestParam(name = "nowPage", defaultValue = "1", required = false) String nowPage,
			@RequestParam(name = "cntPerPage", defaultValue = "5", required = false) String cntPerPage) {
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto) session
				.getAttribute(Define.PRINCIPAL);

		int total = reservationService.readAllReservationByUserIdCount(principal.getId());

		PagingObj po = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

		List<Reservation> responseReservations = reservationService.readAllResrevationByUserIdPaging(po, principal.getId());
		model.addAttribute("paging", po);
		if(responseReservations.size() == 0) {
			model.addAttribute("reservations", null);
		}else {
			model.addAttribute("reservations", responseReservations);
		}
		return "/user/reservationList";
	}
	

}

