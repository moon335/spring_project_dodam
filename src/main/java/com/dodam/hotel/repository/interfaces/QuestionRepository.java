package com.dodam.hotel.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dodam.hotel.dto.InsertReplyDto;
import com.dodam.hotel.dto.QuestionRequestDto.InsertQuestionRequestDto;
import com.dodam.hotel.repository.model.FAQ;
import com.dodam.hotel.repository.model.Question;
import com.dodam.hotel.repository.model.Reply;
import com.dodam.hotel.util.PagingObj;
@Mapper
public interface QuestionRepository {
	
	// 질문 등록 처리
	public int insertQuestion(InsertQuestionRequestDto question);
	
	// 질문 조회 기능
	public List<Reply> findQuestionByUserId(Integer userId);
	
	// 모든 faq 조회
	public List<FAQ> findAllFaq();
	public List<Reply> findAll();
	public int insert(InsertReplyDto insertReplyDto);
	public int findAllQuestionCount();
	public List<Question> findAllQuestion(PagingObj obj);
	public Question findById(Integer id);
	public int updateById(Integer id); 
	public List<Question> findByCategory(String category);
	
	// 매니저 main -  답변 없는 문의사항 개수 조회
	public int findByStatusCount();
	
	// 특정 유저 질문 개수 조회
	public int findByUserIdCount(Integer userId);
	
	// 특정 유저 질문 조회(페이징)
	public List<Reply> findByUserIdPaging(@Param("obj") PagingObj obj, @Param("userId") Integer userId);
	public int deleteQuestion(Integer questionId);
	
} // end of class
