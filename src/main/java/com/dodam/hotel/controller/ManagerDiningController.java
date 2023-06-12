package com.dodam.hotel.controller;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodam.hotel.repository.model.Dining;
import com.dodam.hotel.repository.model.DiningReservation;
import com.dodam.hotel.repository.model.Fitness;
import com.dodam.hotel.repository.model.Pool;
import com.dodam.hotel.repository.model.Spa;
import com.dodam.hotel.service.DiningService;
import com.dodam.hotel.service.FacilitiesService;
import com.dodam.hotel.service.ManagerReservationService;

/**
 * @author lhs-devloper
 */
@Controller
@RequestMapping("/manager")
public class ManagerDiningController {
    @Autowired
    private ManagerReservationService managerReservationService;
    @Autowired
    private FacilitiesService facilitiesService;
    @Autowired
    private DiningService diningService;
    
    @GetMapping("/dining")
    public String diningPage(Date date, Model model){
        List<DiningReservation> responseDiningReservations = null;
        if(date == null){
        	responseDiningReservations = managerReservationService.readTodayDining();
        }else{
        	responseDiningReservations = managerReservationService.readDiningReservationAllListByDate(date);
        }
        AtomicInteger reservationNumberOfP = new AtomicInteger();

        responseDiningReservations.stream().forEach(
                (diningReservation) -> {
                    reservationNumberOfP.addAndGet(diningReservation.getNumberOfP());
                }
        );

        model.addAttribute("totalReservationNumOfP", reservationNumberOfP);
        model.addAttribute("diningList", responseDiningReservations);

        return "/manager/checkDining";
    }
    
    @GetMapping("/allDining")
    public String allDiningPage(Date date, Model model) {
    	List<DiningReservation> responseDiningReservations = null;
    	 AtomicInteger reservationNumberOfP = new AtomicInteger();
    	 if(date == null){
         	responseDiningReservations = managerReservationService.readAllDining();
         }else{
         	responseDiningReservations = managerReservationService.readDiningReservationAllListByDate(date);
         }

         responseDiningReservations.stream().forEach(
                 (diningReservation) -> {
                     reservationNumberOfP.addAndGet(diningReservation.getNumberOfP());
                 }
         );
         model.addAttribute("totalReservationNumOfP", reservationNumberOfP);
         model.addAttribute("diningList", responseDiningReservations);
         
         return "/manager/checkDining";
    }
    
    
    // 부대시설 페이지
    @GetMapping("/facilities")
    public String facPage(Model model) {
    	List<Dining> responseDinings = diningService.readAllDining();
    	model.addAttribute("diningList", responseDinings);
    	
    	Pool responsePool = facilitiesService.readPoolAll();
    	Spa responseSpa = facilitiesService.readSpaAll();
    	Fitness responseFitness = facilitiesService.readFitnessAll();
    	model.addAttribute("pool", responsePool);
    	model.addAttribute("spa", responseSpa);
    	model.addAttribute("fitness", responseFitness);
    	return "/manager/facilities";
    }
}

// 오전만 이용가능 조식(패키지예약) 조식관련된거 띄워야될거같음
// 2순위만들고
