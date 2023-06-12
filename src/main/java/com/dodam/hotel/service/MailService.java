package com.dodam.hotel.service;

import com.dodam.hotel.dto.MailFormDto;
import com.dodam.hotel.handler.exception.CustomRestFullException;
import net.nurigo.java_sdk.api.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;


@Service
public class MailService {

    private final String api_key = "NCSYYRDX9Y5UNIO7";
    private final String api_secret = "YEHIFZWNUP9GCLPXD9SHND2DWEOQRIQP";
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendMail(MailFormDto mailFormDto){
        Message message = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");

            mailHelper.setFrom(mailFormDto.getFrom());
            mailHelper.setTo(mailFormDto.getTo());
            mailHelper.setSubject(mailFormDto.getSubject());
            mailHelper.setText(mailFormDto.getContent(), true);

            mailSender.send(mail);

        } catch (Exception e) {
            throw new CustomRestFullException("이메일 전송 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
