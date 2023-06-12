package com.dodam.hotel.service;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import com.dodam.hotel.dto.MailFormDto;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dodam.hotel.dto.ReservationRequestDto;
import com.dodam.hotel.dto.UserResponseDto;
import com.dodam.hotel.enums.CouponInfo;
import com.dodam.hotel.enums.Grade;
import com.dodam.hotel.handler.exception.CustomRestFullException;
import com.dodam.hotel.repository.interfaces.CouponRepository;
import com.dodam.hotel.repository.interfaces.DiningRepository;
import com.dodam.hotel.repository.interfaces.FacilitiesRepository;
import com.dodam.hotel.repository.interfaces.GradeRepository;
import com.dodam.hotel.repository.interfaces.PointRepository;
import com.dodam.hotel.repository.interfaces.ReservationRepository;
import com.dodam.hotel.repository.model.Coupon;
import com.dodam.hotel.repository.model.Dining;
import com.dodam.hotel.repository.model.Fitness;
import com.dodam.hotel.repository.model.GradeInfo;
import com.dodam.hotel.repository.model.Pool;
import com.dodam.hotel.repository.model.Reservation;
import com.dodam.hotel.repository.model.Spa;
import com.dodam.hotel.util.Define;
import com.dodam.hotel.util.PagingObj;

import net.nurigo.java_sdk.api.Message;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private PointRepository pointRepository;

	@Autowired
	private CouponRepository couponRepository;

	private int count;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private FacilitiesRepository facilitiesRepository;
	
	@Autowired
	private DiningRepository diningRepository;

	@Autowired
	private MailService mailService;

	// 특정 유저의 모든 예약 정보 출력
	@Transactional
	public List<Reservation> readAllReservationByUserId(Integer userId) {
		List<Reservation> reservationEntitys = reservationRepository.findAllReservationByUserId(userId);
		return reservationEntitys;
	}

	// 특정 유저 모든 예약 정보 카운트
	@Transactional
	public int readAllReservationByUserIdCount(Integer userId) {
		int resultCount = reservationRepository.findAllReservationByUserIdCount(userId);
		return resultCount;
	}

	// 특정 유저 모든 예약 정보 페이징
	@Transactional
	public List<Reservation> readAllResrevationByUserIdPaging(PagingObj obj, Integer userId) {
		List<Reservation> reservationEntitys = reservationRepository.findAllReservationByUserIdPaging(obj, userId);
		return reservationEntitys;
	}
	

	// 다이닝 예약
	@Transactional
	public int createReserveDining(ReservationRequestDto reservationRequestDto, Integer userId) {
		reservationRequestDto.setUserId(userId);
		reservationRequestDto.setStartDate(reservationRequestDto.getDate());
		reservationRequestDto.setEndDate(reservationRequestDto.getDate());
		reservationRequestDto.setDiningId(1);
		int numberOfP = reservationRequestDto.getCountPerson() + reservationRequestDto.getCountChild()
				+ reservationRequestDto.getCountBaby();
		reservationRequestDto.setNumberOfP(numberOfP);
		int resultRowCount = reservationRepository.insertReserveDining(reservationRequestDto);
		return resultRowCount;
	}

	// 객실 할인 체크
	@Transactional
	public Map<String, Object> readAvailableCouponOrPoint(ReservationRequestDto reservationRequestDto) {
		// 사용 가능 쿠폰 조회
		List<Coupon> couponList = couponRepository.findByUserId(reservationRequestDto.getUserId());

		// 포인트 조회
		Integer point = pointRepository.findByUserId(reservationRequestDto.getUserId());
		
		Map<String, Object> selectList = new HashMap<>();
		selectList.put("point", point);
		selectList.put("couponList", couponList);
		return selectList;
	}

	// 객실 예약
	@Transactional
	public int createReserveRoom(ReservationRequestDto reservationRequestDto, Integer userId) {
		if(reservationRequestDto.getCoupon() != null){
			couponRepository.deleteByUserIdandCouponInfoId(userId, reservationRequestDto.getCoupon());
		}

		// 부대시설 추가 신청 여부 처리
		if (reservationRequestDto.getDiningCount() != 0) {
			reservationRequestDto.setDiningId(1);
		}
		if (reservationRequestDto.getSpaCount() != 0) {
			reservationRequestDto.setSpaId(1);
		}
		if (reservationRequestDto.getPoolCount() != 0) {
			reservationRequestDto.setPoolId(1);
		}
		if (reservationRequestDto.getFitnessCount() != 0) {
			reservationRequestDto.setFitnessId(1);
		}
		reservationRequestDto.setUserId(userId);
		List<Reservation> responseEntitys = readAllReservationByUserId(userId);
		// 해당 회원 현재 등급 조회
		GradeInfo userGrade = gradeRepository.findGradeByUserId(userId);
		// 해당 회원의 모든 예약 정보 불러와서 날짜 계산하기
		count = 0;

		responseEntitys.forEach(res -> {
			Long startDate = res.getStartDate().getTime();
			Long endDate = res.getEndDate().getTime();
			Long dayCount = ((endDate - startDate) / 1000) / (24 * 60 * 60);
			count += dayCount.intValue();
		});

		int resultRowCount = reservationRepository.insertReserveRoom(reservationRequestDto);


		// 포인트 처리
		// 다이아 일때 처리
		Integer totalPrice = reservationRequestDto.getTotalPrice();
		if(userGrade.getGrade().getId() == Grade.DIA.getGrade()){
			pointRepository.insertPoint(Integer.valueOf((int) Math.round(totalPrice * 0.07)), userId);
		}
		if(userGrade.getGrade().getId() == Grade.GOLD.getGrade()){
			pointRepository.insertPoint(Integer.valueOf((int) Math.round(totalPrice * 0.05)), userId);
		}
		if(userGrade.getGrade().getId() == Grade.BROWN.getGrade()){
			pointRepository.insertPoint(Integer.valueOf((int) Math.round(totalPrice * 0.04)), userId);
		}

		Integer nowReservationCount = reservationRequestDto.getDay();
		count += nowReservationCount;
		if(resultRowCount != 1) {
			throw new CustomRestFullException("예약에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			if (count >= 5 && userGrade.getGrade().getId() < 3) {
				// 골드 등급업 처리
				gradeRepository.updateUserGrade(userId, Grade.DIA);
				// 쿠폰 부여
				couponRepository.insert(CouponInfo.DIA , userId);
				couponRepository.insert(CouponInfo.DIA2 , userId);
			} else if (count >= 2 && userGrade.getGrade().getId() < 2) {
				// 다이아 등급업 처리
				gradeRepository.updateUserGrade(userId, Grade.GOLD);
				// 쿠폰 부여
				couponRepository.insert(CouponInfo.GOLD , userId);
			}
		}

		String reservation = "예약일 : "+reservationRequestDto.getStartDate() +" - "+ reservationRequestDto.getEndDate()+"<br>";
		
		if (resultRowCount == 1) {

			MailFormDto mailFormDto = MailFormDto
					.builder()
					.from(Define.ADMIN_EMAIL)
					.to((String)((UserResponseDto.LoginResponseDto)session.getAttribute("principal")).getEmail())
					.subject((String)((UserResponseDto.LoginResponseDto)session.getAttribute("principal")).getName() + "님의 예약 내역")
					.content("<p>"+ reservation +"<br>예약해주셔서 감사합니다.<br> 예약일을 한번 더 잘 확인해주세요.</p> <br> <h2>(주) 호텔 도담</h2>")
					.build();
			mailService.sendMail(mailFormDto);
		} else {
			// 예외처리 이메일 전송 실패
			throw new CustomRestFullException("이메일 전송 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return resultRowCount;
	}
	
	public Integer deleteDiningReservation(Integer reservationId) {
		Integer resultRow = reservationRepository.deleteDiningByReservationId(reservationId);
		return resultRow;
	}
	
	
	public Reservation readReservationByUserIdSuccessful(Integer userId) {
		return reservationRepository.findReservationByUserIdSuccessful(userId);
	}
	
	//객실 예약시 띄워줄 부대시설 상태값 서비스들
	public List<Dining> readDiningStatus(){
		return diningRepository.findAllDining();
	}
	
	public List<Spa> readSpaStatus(){
		return facilitiesRepository.findByAllSpa();
	}
	
	public List<Fitness> readFitnessStatus(){
		return facilitiesRepository.findByAllFitness();
	}
	
	public List<Pool> readPoolStatus(){
		return facilitiesRepository.findByAllPool();
	}
}
