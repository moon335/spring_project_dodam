package com.dodam.hotel.repository.interfaces;

import com.dodam.hotel.repository.model.FAQ;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FAQRepository {
    List<FAQ> findAllFAQ();
    FAQ findFAQById(Integer id);

    int insertFAQ(FAQ faq);
    int updateFAQById(FAQ faq);
    int deleteFAQById(Integer id);
}
