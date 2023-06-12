package com.dodam.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodam.hotel.repository.model.DiningDesc;
import com.dodam.hotel.service.DiningService;


@Controller
public class DiningController {
	
	@Autowired
	private DiningService diningService;
	
	@GetMapping("/dining")
	public String diningPage(Model model, @RequestParam(name = "type", defaultValue = "All", required = false) String type) {
		List<DiningDesc> responseDinings = diningService.readAllDining(type);
		model.addAttribute("diningList", responseDinings);
		model.addAttribute("type", type);
		return "/facilities/dining";
	}
	
	// dining 예약 페이지 
	@GetMapping("/reserveDining")
	public String reserve() {
		return "/reservation/reserveDining";
	}
	
	
} // end of class
