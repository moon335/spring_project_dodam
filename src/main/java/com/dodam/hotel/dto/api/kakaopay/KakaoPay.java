package com.dodam.hotel.dto.api.kakaopay;

import com.dodam.hotel.dto.api.pay.PayApproveRequest;
import com.dodam.hotel.dto.api.pay.PayInterface;
import com.dodam.hotel.dto.api.pay.PayReadyInterface;
import com.dodam.hotel.dto.api.pay.PayReadyResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class KakaoPay implements PayInterface {

	private final String KAKAO_TESTCID = "TC0ONETIME";
	private final String KAKAO_APP_ADMIN_KEY = "74e472b2d495ed6df25464561df4bf71";
	private RestTemplate restTemplate = new RestTemplate();
	private String kakaoTid;
//    @Override
//    public PayReadyResponse payReady(){
//        return null;
//    }

	@Override
	public PayReadyResponse payReady() {
		return null;
	}

	public PayReadyResponse payReady(PayReadyInterface kakaoRequestDto) {
		KakaoRequestDto typeConverKakaoRequestDto = (KakaoRequestDto) kakaoRequestDto;
		URI uri = UriComponentsBuilder.fromUriString("https://kapi.kakao.com").path("/v1/payment/ready").encode()
				.build().toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + KAKAO_APP_ADMIN_KEY);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", KAKAO_TESTCID);
		parameters.add("partner_order_id", "partner_order_id");
		parameters.add("partner_user_id", "partner_user_id");
		parameters.add("item_name", typeConverKakaoRequestDto.getItem_name());
		parameters.add("quantity", "1");
		parameters.add("total_amount", typeConverKakaoRequestDto.getTotal_amount());
		parameters.add("vat_amount",
				typeConverKakaoRequestDto.getVat_amount() != null ? typeConverKakaoRequestDto.getVat_amount() : "0");
		parameters.add("tax_free_amount", "0");
		parameters.add("approval_url", "http://localhost:8080/pay/kakao/success");
		parameters.add("fail_url", "http://localhost:8080/pay/kakao/fail");
		parameters.add("cancel_url", "http://localhost:8080/pay/kakao/cancel");
//		parameters.add("approval_url", "http://192.168.0.84:8080/pay/kakao/success");
//		parameters.add("fail_url", "http://192.168.0.84:8080/pay/kakao/fail");
//		parameters.add("cancel_url", "http://192.168.0.84:8080/pay/kakao/cancel");

		HttpEntity<MultiValueMap<String, String>> reqEntity = new HttpEntity<>(parameters, headers);

		ResponseEntity<KakaoPaymentDto> response = restTemplate.exchange(uri, HttpMethod.POST, reqEntity,
				KakaoPaymentDto.class);

		KakaoPaymentDto kakaoPaymentDto = response.getBody();
		kakaoTid = kakaoPaymentDto.getTid();

		return kakaoPaymentDto;
	}

	@Override
	public PayApproveRequest payApprove(String pg_token) {
		URI uri = UriComponentsBuilder.fromUriString("https://kapi.kakao.com").path("/v1/payment/approve").encode()
				.build().toUri();

		// https://kapi.kakao.com/v1/payment/ready

		// header 끝
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + KAKAO_APP_ADMIN_KEY);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", KAKAO_TESTCID);
		parameters.add("tid", kakaoTid);
		parameters.add("partner_order_id", "partner_order_id");
		parameters.add("partner_user_id", "partner_user_id");
		parameters.add("pg_token", pg_token);

		HttpEntity<MultiValueMap<String, String>> reqEntity = new HttpEntity<>(parameters, headers);

		ResponseEntity<KakaoSinglePayment> response = restTemplate.exchange(uri, HttpMethod.POST, reqEntity,
				KakaoSinglePayment.class);

		KakaoSinglePayment kakaoSinglePayment = response.getBody();

		return kakaoSinglePayment;
	}

	//환불
	public KakaoCancelResponse kakaoCancel(String tid, String totalPrice) {
		URI uri = UriComponentsBuilder.fromUriString("https://kapi.kakao.com").path("/v1/payment/cancel").encode()
				.build().toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + KAKAO_APP_ADMIN_KEY);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// 카카오페이 요청
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", KAKAO_TESTCID);
		parameters.add("tid", tid);
		parameters.add("cancel_amount", totalPrice);
		parameters.add("cancel_tax_free_amount", "0");
		parameters.add("cancel_vat_amount", "0");

		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);

		// 외부에 보낼 url
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			KakaoCancelResponse cancelResponse = restTemplate.postForObject(uri,
					requestEntity, KakaoCancelResponse.class);
			return cancelResponse;
		}catch(Exception e) {
			return null;
		}
	}

}
