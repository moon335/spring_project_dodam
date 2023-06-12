package com.dodam.hotel.dto.api.tosspay;

import com.dodam.hotel.dto.api.nicepay.NicepayDto;
import com.dodam.hotel.dto.api.nicepay.NicepayResultDto;
import com.dodam.hotel.dto.api.pay.PayApproveRequest;
import com.dodam.hotel.dto.api.pay.PayInterface;
import com.dodam.hotel.dto.api.pay.PayReadyInterface;
import com.dodam.hotel.dto.api.pay.PayReadyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class TossPay implements PayInterface {
    private String TOSS_SECRET_KEY ="test_sk_Kma60RZblrqlMyBJ2qRVwzYWBn14:";
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public PayReadyResponse payReady() {
        return null;
    }

    @Override
    public PayReadyResponse payReady(PayReadyInterface payReadyInterface) {
        TosspayRequest tosspayRequest = (TosspayRequest) payReadyInterface;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((TOSS_SECRET_KEY).getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> AuthenticationMap = new HashMap<>();
        AuthenticationMap.put("paymentKey", String.valueOf(tosspayRequest.getPaymentKey()));
        AuthenticationMap.put("orderId", String.valueOf(tosspayRequest.getOrderId()));
        AuthenticationMap.put("amount", tosspayRequest.getAmount());


        HttpEntity<String> request = null;
        try {
            request = new HttpEntity<>(objectMapper.writeValueAsString(AuthenticationMap), headers);
            // Jackson JsonNode
            ResponseEntity<TossResponse> responseEntity = restTemplate.exchange(
                    "https://api.tosspayments.com/v1/payments/confirm",
                    HttpMethod.POST,
                    request,
                    TossResponse.class
            );
            TossResponse responseNode = responseEntity.getBody();
            
            return responseNode;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PayApproveRequest payApprove(String token) {
        return null;
    }

    public TossResponse tossCancel(String tid, String totalPrice) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((TOSS_SECRET_KEY).getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> AuthenticationMap = new HashMap<>();
        AuthenticationMap.put("cancelReason", "고객에 의한 환불");

        HttpEntity<String> request = null;
        try{
            request = new HttpEntity<>(objectMapper.writeValueAsString(AuthenticationMap), headers);
            ResponseEntity<TossResponse> responseEntity = restTemplate.postForEntity(
                    "https://api.tosspayments.com/v1/payments/ " + tid + "/cancel",
                    request,
                    TossResponse.class
            );
            TossResponse tossResponse = responseEntity.getBody();
            return tossResponse;
        }catch (JsonProcessingException e){
            throw  new RuntimeException(e);
        }

    }

}
