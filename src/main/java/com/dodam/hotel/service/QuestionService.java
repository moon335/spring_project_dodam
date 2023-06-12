package com.dodam.hotel.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dodam.hotel.dto.QuestionRequestDto.InsertQuestionRequestDto;
import com.dodam.hotel.handler.exception.CustomRestFullException;
import com.dodam.hotel.repository.interfaces.QuestionRepository;
import com.dodam.hotel.repository.interfaces.ReplyRepository;
import com.dodam.hotel.repository.model.FAQ;
import com.dodam.hotel.repository.model.Question;
import com.dodam.hotel.repository.model.Reply;
import com.dodam.hotel.util.PagingObj;
@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	// 질문 등록 처리 (현우)
	@Transactional
	public void createQuestion(InsertQuestionRequestDto question) {
		int resultRow = questionRepository.insertQuestion(question);
		if(resultRow != 1) {
			// 예외처리
			throw new CustomRestFullException("질문 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 매니저 main - 답변 안달린 질문 개수 조회
	@Transactional
	public int readQuestionCountByStatus0() {
		int resultCount = questionRepository.findByStatusCount();
		return resultCount;
	}
	
	// 특정 유저 모든 질문 개수
	@Transactional
	public int readQuestionCountByUserId(Integer userId) {
		int resultCount = questionRepository.findByUserIdCount(userId);
		return resultCount;
	}
	
	// 특정 유저 질문 조회(페이징)
	@Transactional
	public List<Reply> readQuestionByUserIdPaging(PagingObj obj, Integer userId) {
		List<Reply> replyEntitys = questionRepository.findByUserIdPaging(obj, userId);
		return replyEntitys;
	}
	
	// 모든 자주 묻는 질문 조회
	@Transactional
	public List<FAQ> readAllFaq() {
		List<FAQ> faqEntitys = questionRepository.findAllFaq();
		return faqEntitys;
	}
	
	// 특정 유저 질문 조회 기능
	@Transactional
	public List<Reply> readQuestionByUserId(Integer userId) {
		List<Reply> questionEntity = questionRepository.findQuestionByUserId(userId);
		return questionEntity;
	}
	//질문 삭제 기능
	@Transactional
	public int deleteQuestionById(Integer questionId) {
		int deleteReplyEntity = replyRepository.deleteReply(questionId);
		int questionDeleteEntity = questionRepository.deleteQuestion(questionId);
		// 예외처리
		return questionDeleteEntity;
	}
	
	// 모든 문의 개수
	@Transactional
	public int readAllQuestionCount() {
		int resultCount = questionRepository.findAllQuestionCount();
		return resultCount;
	}
	
	// 문의 전부 보기
	@Transactional
	public List<Question> readAllQuestionList(PagingObj obj){
		List<Question> questionListEntity = questionRepository.findAllQuestion(obj); 
		return questionListEntity;
	}
	
	// 문의 아이디로 검색후 상세보기
	@Transactional
	public Question readById(Integer id) {
		Question questionEntity = questionRepository.findById(id);
		return questionEntity;
	}
	
	// 댓글 상태값 변경
	@Transactional
	public int updateStatusById(Integer id) {
		int updateStatusEntity = questionRepository.updateById(id);
		// 예외처리
		return updateStatusEntity;
	}
	
	//댓글작성
	@Transactional
	public int insertReply(String content,Integer questionId,Integer managerId) {
		int insertReplyEntity = replyRepository.insert(content, questionId, managerId);
		// 예외처리
		return insertReplyEntity; 
	}
	
	// 문의 카테고리로 검색
	@Transactional
	public List<Question> readByCategory(String category){
		List<Question> questionCategoryEntity = questionRepository.findByCategory(category);
		return questionCategoryEntity;
	}
}
