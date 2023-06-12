package com.dodam.hotel.service;

import com.dodam.hotel.dto.MailFormDto;
import com.dodam.hotel.dto.PayDto;
import com.dodam.hotel.dto.UserResponseDto;
import com.dodam.hotel.enums.Grade;
import com.dodam.hotel.handler.exception.CustomRestFullException;
import com.dodam.hotel.repository.interfaces.*;
import com.dodam.hotel.repository.model.GradeInfo;
import com.dodam.hotel.repository.model.Pay;
import com.dodam.hotel.repository.model.Reservation;
import com.dodam.hotel.util.Define;

import net.nurigo.java_sdk.api.Message;

import java.util.HashMap;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayService {
    @Autowired
    private PayRepository payRepository;
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
	private JavaMailSenderImpl mailSender;
	@Autowired
	private HttpSession session;

    @Autowired
    private MailService mailService;

    @Transactional
    public int createPay(PayDto payDto){
        int result = payRepository.insertPay(payDto);
        return result == 1 ? 1 : 0;
    }

    @Transactional
    public boolean refundPay(Integer reservationId, Integer userId){
    	Reservation reservation = reservationRepository.findReservationById(reservationId);

        // 예약검사 실패 시
        if(reservation == null){
            return false;
        }

        String tid = reservation.getPayTid();
        Pay payInfo = payRepository.findByTidPay(tid);
        Integer point = payInfo.getPrice();
        String beforeUserGrade = payInfo.getGradeName();
        if(Grade.DIA == Grade.valueOf(Grade.class, beforeUserGrade)){
            point = Integer.valueOf((int) Math.round(point * 0.07));
        }else if(Grade.GOLD == Grade.valueOf(Grade.class, beforeUserGrade)){
            point = Integer.valueOf((int) Math.round(point * 0.05));
        }else{
            point = Integer.valueOf((int) Math.round(point * 0.04));
        }
        point *= -1;

        pointRepository.insertPoint(point, userId);
        reservationRepository.deleteReservation(reservationId);

        GradeInfo userGrade = gradeRepository.findGradeByUserId(userId);
        Grade grade = Grade.valueOf(userGrade.getGrade().getName());
        
        if(!beforeUserGrade.equals(userGrade.getGrade().getName())){
            if(Grade.DIA.equals(beforeUserGrade)){
                grade = Grade.DIA;
            }else if(Grade.GOLD.equals(beforeUserGrade)){
                grade = Grade.GOLD;
            }else{
                grade = Grade.BROWN;
            }
            gradeRepository.updateUserGrade(userId, grade);
        }
        if(grade.getGrade() == 1){
            couponRepository.deleteByUserIdandCouponInfoId(userId, 2);
            couponRepository.deleteByUserIdandCouponInfoId(userId, 3);
            couponRepository.deleteByUserIdandCouponInfoId(userId, 4);
        }else if(grade.getGrade() == 2){
            couponRepository.deleteByUserIdandCouponInfoId(userId, 3);
            couponRepository.deleteByUserIdandCouponInfoId(userId, 4);
        }
        
        if (reservation != null) {

            MailFormDto mailFormDto = MailFormDto.builder()
                    .from(Define.ADMIN_EMAIL)
                    .to((String)((UserResponseDto.LoginResponseDto)session.getAttribute("principal")).getEmail())
                    .subject((String)((UserResponseDto.LoginResponseDto)session.getAttribute("principal")).getName() + "님의 예약이 취소되었습니다")
                    .content("<p>예약 현황을 숙지하여 차질이 없도록 하세요.</p> <br> <h2>호텔 도담 이사회</h2>")
                    .build();

            mailService.sendMail(mailFormDto);

		}
        return true;
    }
    
    @Transactional
    public Pay searchType(String tid) {
    	
    	return payRepository.findByTidPay(tid);
    }
}
