package com.dodam.hotel.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dodam.hotel.handler.exception.ManagerCustomRestFullException;
import com.dodam.hotel.dto.EventInsertForm;
import com.dodam.hotel.repository.interfaces.EventRepository;
import com.dodam.hotel.repository.model.Event;
import com.dodam.hotel.util.PagingObj;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	// 행사 일정 작성
	@Transactional
	public int insertEvent(EventInsertForm noticeInsertForm) {
		int eventEntity = eventRepository.insert(noticeInsertForm);
		if (eventEntity == 0) {
			throw new ManagerCustomRestFullException("이벤트 등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return eventEntity;
	}

	// 행사일정 리스트로 띄우기
	@Transactional
	public List<Event> readByEventAllPaging(PagingObj obj) {
		List<Event> eventListEntitys = eventRepository.findByAllPage(obj);
		return eventListEntitys;
	}
	
	// 행사 일정 메인 리스트 조회
	@Transactional
	public List<Event> readByIdLimit() {
		List<Event> eventListEntitys = eventRepository.findLimit5();
		return eventListEntitys;
	}

	//행사 일정 리스트 숫자
	@Transactional
	public Integer readEventCount() {
		int resultCount = eventRepository.count();
		return resultCount;
	}

	// 행사 일정 아이디 조회
	@Transactional
	public Event readById(Integer id) {
		Event eventEntity = eventRepository.findById(id);
		if (eventEntity == null) {
			throw new ManagerCustomRestFullException("행사 일정 조회 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return eventEntity;
	}

	// 행사 일정 수정
	@Transactional
	public int updateEvent(EventInsertForm noticeInsertForm) {
		int eventUpdateEntity = eventRepository.updateEvent(noticeInsertForm);
		if (eventUpdateEntity == 0) {
			throw new ManagerCustomRestFullException("이벤트 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return eventUpdateEntity;
	}

	// 행사 일정 삭제
	@Transactional
	public int deleteEvent(Integer id) {
		int eventDeleteEntity = eventRepository.deleteById(id);
		if (eventDeleteEntity == 0) {
			throw new ManagerCustomRestFullException("이벤트 삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return eventDeleteEntity;
	}
	
	// 진행중인 이벤트 조회 - 현우
	@Transactional
	public List<Event> readOnGoingEvent(PagingObj obj) {
		List<Event> eventEntitys = eventRepository.findNowEventByPage(obj);
		return eventEntitys;
	}
	
	// 진행중인 이벤트 조회 - 현우
	@Transactional
	public List<Event> readAllOnGoingEvent() {
		List<Event> eventEntitys = eventRepository.findNowEvent();
		return eventEntitys;
	}
	
	// 진행중인 리스트 숫자 - 현우
	@Transactional
	public int readOnGoingEventCount() {
		int resultCount = eventRepository.findByNowAll();
		return resultCount;
	}
	
	// 종료된 이벤트 조회 - 현우
	@Transactional
	public List<Event> readClosedEvent(PagingObj obj) {
		List<Event> eventEntitys = eventRepository.findByPrevEventPage(obj);
		return eventEntitys;
	}
	
	// 종료된 리스트 숫자 - 현우
	@Transactional
	public int readClosedEventCount() {
		int resultCount = eventRepository.findByPrevAll();
		return resultCount;
	}
	
}
