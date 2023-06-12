package com.dodam.hotel.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodam.hotel.dto.api.ResponseMsg;
import com.dodam.hotel.handler.exception.ManagerCustomRestFullException;
import com.dodam.hotel.repository.model.Manager;
import com.dodam.hotel.repository.model.Reservation;
import com.dodam.hotel.service.ManagerReservationService;
import com.dodam.hotel.service.ManagerService;
import com.dodam.hotel.service.ReservationService;
import com.dodam.hotel.util.PagingObj;

/**
 * @author lhs-devloper
 */
@Controller
@RequestMapping("/manager")
public class ManagerReservationController {
	@Autowired
	private HttpSession session;
	@Autowired
	private ManagerReservationService managerReservationService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private ManagerService managerService;

	// 매니저 예약 페이지 이동
	@GetMapping("/reservation")
	public String reservationList(String name, Model model,
			@RequestParam(name = "nowPage", defaultValue = "1", required = false) String nowPage,
			@RequestParam(name = "cntPerPage", defaultValue = "5", required = false) String cntPerPage) {
		if (name == null || name.equals("")) {
			int total = managerReservationService.readTodayReservationCount();
			PagingObj obj = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			model.addAttribute("paging", obj);
			model.addAttribute("reservationList", managerReservationService.readTodayReservationPaging(obj));
		} else {
			int total = managerService.readByNameCount(name);
			PagingObj obj = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			model.addAttribute("paging", obj);
			model.addAttribute("reservationList", managerReservationService.readUserReservation(name));
		}
		return "/manager/reservation";
	}

	@GetMapping("/reservation/{id}")
	public String reservationDetail(@PathVariable Integer id, Model model) {
		if (id == null) {
			throw new ManagerCustomRestFullException("아이디가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
		}
		Map<String, Object> reservationMap = managerReservationService.readReservationById(id);
		model.addAttribute("reservation", reservationMap.get("reservation"));
		model.addAttribute("roomList", reservationMap.get("roomList"));
		model.addAttribute("spaList", reservationMap.get("spaList"));
		model.addAttribute("poolList", reservationMap.get("poolList"));
		model.addAttribute("fitnessList", reservationMap.get("fitnessList"));
		model.addAttribute("diningList", reservationMap.get("diningList"));
		model.addAttribute("packageList", reservationMap.get("packageList"));
		return "/manager/reservationDetail";
	}

	@PostMapping("/reservation/update")
	public String reservationUpdate(Reservation reservation) {
		managerReservationService.updateReservation(reservation);
		return "redirect:/manager/reservation";
	}

	// 전체 예약 리스트 조회
	@GetMapping("/allReservation")
	public String allReservationList(String name, Model model,
			@RequestParam(name = "nowPage", defaultValue = "1", required = false) String nowPage,
			@RequestParam(name = "cntPerPage", defaultValue = "5", required = false) String cntPerPage) {

		if (name == null || name.equals("")) {
			int total = managerReservationService.readAllReservationCount();
			PagingObj obj = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			model.addAttribute("paging", obj);
			model.addAttribute("reservationList", managerReservationService.readAllReservationPaging(obj));
		} else {
			int total = managerService.readByNameCount(name);
			PagingObj obj = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			model.addAttribute("paging", obj);
			model.addAttribute("reservationList", managerService.readUserByNameForManager(obj, name));
		}
		return "/manager/allReservation";
	}

	@ResponseBody
	@DeleteMapping("/reservation/delete")
	public ResponseMsg resrvationDelete(Integer id) {
		Manager manager = (Manager) session.getAttribute("principal");
		if (manager == null) {
			ResponseMsg failMsg = ResponseMsg.builder().status_code(HttpStatus.FORBIDDEN.value()).msg("삭제권한이 없습니다")
					.redirect_uri(null).build();
			return failMsg;
		}
		int result = managerReservationService.deleteReservation(id);
		if (result == 0) {
			ResponseMsg failMsg = ResponseMsg.builder().status_code(HttpStatus.BAD_REQUEST.value()).msg("삭제에 실패하였습니다")
					.redirect_uri(null).build();
			return failMsg;
		} else {
			ResponseMsg successMsg = ResponseMsg.builder().status_code(HttpStatus.OK.value()).msg("삭제되었습니다")
					.redirect_uri("/manager/reservation").build();
			return successMsg;
		}
	}

}