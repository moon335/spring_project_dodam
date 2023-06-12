package com.dodam.hotel.service;

import com.dodam.hotel.handler.exception.ManagerCustomRestFullException;
import com.dodam.hotel.repository.interfaces.FAQRepository;
import com.dodam.hotel.repository.model.FAQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lhs-devloper
 * FAQ_TB 관리
 * ServiceLayer
 * CUD만 트랜잭션 걸어둠 (select는 걸 필요없다 판단하여 다음과 같이 구성)
 */
@Service
public class ManagerFAQService {
    @Autowired
    private FAQRepository faqRepository;

    public List<FAQ> readAllFAQ(){
        List<FAQ> faqEntitys = faqRepository.findAllFAQ();
        if(faqEntitys == null) {
        	throw new ManagerCustomRestFullException("faq 조회 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return faqEntitys;
    }

    public FAQ readFAQById(Integer id){
        FAQ faqEntity = faqRepository.findFAQById(id);
        if(faqEntity == null) {
        	throw new ManagerCustomRestFullException("faq 조회 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return faqEntity;
    }

    @Transactional
    public int createFAQ(FAQ faq){
        int resultRow = faqRepository.insertFAQ(faq);
        if(resultRow == 0) {
        	throw new ManagerCustomRestFullException("insert 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resultRow;
    }
    @Transactional
    public int updateFAQ(FAQ faq){
        int resultRow = faqRepository.updateFAQById(faq);
        if(resultRow == 0) {
        	throw new ManagerCustomRestFullException("update 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resultRow;
    }
    @Transactional
    public int deleteFAQ(Integer id){
        int resultRow = faqRepository.deleteFAQById(id);
        if(resultRow == 0) {
        	throw new ManagerCustomRestFullException("delete 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resultRow;
    }
}
