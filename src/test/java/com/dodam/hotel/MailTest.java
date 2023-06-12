package com.dodam.hotel;

import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.dodam.hotel.enums.Grade;

@SpringBootTest
public class MailTest{
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Test
	public void sendMessage(){
		String subject = "성희쓰 반갑다네";
        String content = "하이요~";
        String from = "내 이메일 주소";
        String to = "받는 사람 이메일 주소";
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");
            
            mailHelper.setFrom(from);
            mailHelper.setTo(to);	
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	@Test
	public void enumTest() {
		Integer id = Grade.BROWN.getGrade();
	}
}
