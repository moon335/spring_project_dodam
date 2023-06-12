package com.dodam.hotel.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodam.hotel.dto.PayDto;
import com.dodam.hotel.dto.UserResponseDto;
import com.dodam.hotel.dto.api.kakaopay.KakaoCancelResponse;
import com.dodam.hotel.dto.api.kakaopay.KakaoPay;
import com.dodam.hotel.dto.api.kakaopay.KakaoPaymentDto;
import com.dodam.hotel.dto.api.kakaopay.KakaoRequestDto;
import com.dodam.hotel.dto.api.kakaopay.KakaoSinglePayment;
import com.dodam.hotel.dto.api.nicepay.NicePay;
import com.dodam.hotel.dto.api.nicepay.NicepayDto;
import com.dodam.hotel.dto.api.nicepay.NicepayResultDto;
import com.dodam.hotel.dto.api.pay.PayOption;
import com.dodam.hotel.dto.api.tosspay.TossPay;
import com.dodam.hotel.dto.api.tosspay.TossResponse;
import com.dodam.hotel.dto.api.tosspay.TosspayRequest;
import com.dodam.hotel.enums.PGType;
import com.dodam.hotel.handler.exception.CustomRestFullException;
import com.dodam.hotel.repository.interfaces.GradeRepository;
import com.dodam.hotel.repository.interfaces.ReservationRepository;
import com.dodam.hotel.repository.model.GradeInfo;
import com.dodam.hotel.repository.model.Pay;
import com.dodam.hotel.service.PayService;
import com.dodam.hotel.util.Define;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author lhs-devloper
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    private KakaoPay kakaoPay = new KakaoPay();
    private NicePay nicePay = new NicePay();
    private TossPay tossPay = new TossPay();

    @Autowired
    private HttpSession session;
    @Autowired
    private PayService payService;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    
    
    @GetMapping("/payReady")
    public String paySelectController(PayOption payOption, Model model){
        model.addAttribute("option", payOption.getPaySelect());
        model.addAttribute("clientId", payOption.getClientKey());
        model.addAttribute("totalAmount", payOption.getTotal_amount());
        model.addAttribute("orderId", payOption.getOrderName());
        return "/pay/pay";
    }

    @GetMapping("/success")
    public String successController(String tid, Model model) {
        model.addAttribute("tid", tid);
        return "/pay/paySuccess";
    }

    // nicepay 결제 성공
    @PostMapping("/payments")
    public String nicePayController(NicepayDto nicepayDto, Model model) throws JsonProcessingException {
        NicepayResultDto nicepayResultDto = (NicepayResultDto) nicePay.payReady(nicepayDto);

        UserResponseDto.LoginResponseDto user = (UserResponseDto.LoginResponseDto) session.getAttribute(Define.PRINCIPAL);

        GradeInfo userGrade = gradeRepository.findGradeByUserId(user.getId());

        if("0000".equals(nicepayResultDto.getResultCode())){
            // 결제 성공 케이스 작성하고
            // pay결제성공시 서비스 넣고
            PayDto dto = PayDto
                    .builder()
                    .payTid(nicepayResultDto.getTid())
                    .price(nicepayResultDto.getAmount().intValue())
                    .pgType(PGType.NICEPAY)
                    .grade(userGrade.getGrade().getName())
                    .build();

            payService.createPay(dto);
            
            return "redirect:/pay/success?tid="+nicepayResultDto.getTid();
        }else{
            // 결제 실패 케이스 작성
            return "redirect:/pay/fail";
        }
    }
    // 카카오 결제(SDK 지원 방식이 보이지 않아 다음과 같이 사용)
    @GetMapping("/kakaopay")
    public String kakaopayController(KakaoRequestDto kakaoRequestDto){
        if(kakaoRequestDto.getItem_name() == null || kakaoRequestDto.getTotal_amount() == null){
            //인터셉터 처리
        	throw new CustomRestFullException("잘못된 입력입니다.", HttpStatus.BAD_REQUEST);
        }


        KakaoPaymentDto kakaoPaymentDto = (KakaoPaymentDto) kakaoPay.payReady(kakaoRequestDto);
        return "redirect:" +kakaoPaymentDto.getNext_redirect_pc_url();
    }
    
    @GetMapping("/kakao/success")
    public void kakaoPaySuccessControler(String pg_token, HttpServletResponse response) throws IOException {
        KakaoSinglePayment kakaoSinglePayment = (KakaoSinglePayment) kakaoPay.payApprove(pg_token);

        UserResponseDto.LoginResponseDto user = (UserResponseDto.LoginResponseDto) session.getAttribute(Define.PRINCIPAL);

        GradeInfo userGrade = gradeRepository.findGradeByUserId(user.getId());

        PayDto dto = PayDto
                .builder()
                .payTid(kakaoSinglePayment.getTid())
                .price(kakaoSinglePayment.getAmount().getTotal())
                .pgType(PGType.KAKAO)
                .grade(userGrade.getGrade().getName())
                .build();

        payService.createPay(dto);
        response.sendRedirect("/pay/success?tid="+kakaoSinglePayment.getTid());
        // 성공 서비스

//        return "redirect:/pay/success";
    }
    // 토스결제 성공 시
    @GetMapping("/toss/success")
    public String tossSuccessController(TosspayRequest tosspayRequest){
        TossResponse tossResponse = (TossResponse) tossPay.payReady(tosspayRequest);

        UserResponseDto.LoginResponseDto user = (UserResponseDto.LoginResponseDto) session.getAttribute(Define.PRINCIPAL);

        GradeInfo userGrade = gradeRepository.findGradeByUserId(user.getId());

        // 성공 서비스
        PayDto dto = PayDto
                .builder()
                .payTid(tossResponse.getPaymentKey())
                .price(tossResponse.getTotalAmount())
                .pgType(PGType.TOSS)
                .grade(userGrade.getGrade().getName())
                .build();

        payService.createPay(dto);
        return "redirect:/pay/success?tid="+tossResponse.getPaymentKey();
    }
    /***
    // ------------------------------------------ 여기서 부터는 결제 취소 ---------------------------------------------------
     ***/
    //결제 취소후 예약취소처리까
    @PostMapping("/kakao/refund/{tid}/{totalPrice}/{reservationId}")
    public String kakaoPayRefund(@PathVariable("tid") String tid,@PathVariable("totalPrice") String totalPrice
    		,@PathVariable("reservationId") Integer reservationId) {
    	UserResponseDto.LoginResponseDto user = (UserResponseDto.LoginResponseDto)session.getAttribute(Define.PRINCIPAL);
    	KakaoCancelResponse kakaoCancelResponse = kakaoPay.kakaoCancel(tid,totalPrice);
    	if(kakaoCancelResponse == null) {
    		throw new CustomRestFullException("결제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    	}else if(kakaoCancelResponse != null) {
    		payService.refundPay(reservationId, user.getId());
    	}
        return "redirect:/myReservations";
    }
    
    @PostMapping("/refund/{tid}/{reservationId}")
    public String payRefund(@PathVariable("tid") String tid
            ,@PathVariable("reservationId") Integer reservationId) {

        UserResponseDto.LoginResponseDto user = (UserResponseDto.LoginResponseDto)session.getAttribute(Define.PRINCIPAL);
        Pay payType = payService.searchType(tid);

        // 총 결제
        String totalPrice = String.valueOf(payType.getPrice());
        // KAKAO결제 일 때
        if(payType.getPgType() == PGType.KAKAO) {
            KakaoCancelResponse kakaoCancelResponse = kakaoPay.kakaoCancel(tid, totalPrice);
        }
        // TOSS결제 일 때
        else if(payType.getPgType() == PGType.TOSS) {
            tossPay.tossCancel(tid, totalPrice);
        }
        // NICEPAY 결제
        else if (payType.getPgType() == PGType.NICEPAY) {
            nicePay.requestCancel(tid, totalPrice);
        }
        payService.refundPay(reservationId, user.getId());
        return "redirect:/myReservations";
    }

}
