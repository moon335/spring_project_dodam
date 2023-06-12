package com.dodam.hotel.repository.interfaces;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.dodam.hotel.dto.EventInsertForm;
import com.dodam.hotel.repository.model.Event;
import com.dodam.hotel.util.PagingObj;

@Mapper
public interface EventRepository {
	
	public int insert(EventInsertForm eventInsertForm);
	public List<Event> findByAll();
	public Event findById(Integer id);
	public int updateEvent(EventInsertForm eventInsertForm);
	public int deleteById(Integer id);
	
	//공지사항 리스트 조회 페이징
	public List<Event> findByAllPage(PagingObj obj);
	
	// 매니저 메인 공지사항 출력
	public List<Event> findLimit5();
	
	//게시글수 카운팅
	int count();
	
	// 진행중인 이벤트 조회
	public int findByNowAll();
	public List<Event> findNowEventByPage(PagingObj obj);
	public List<Event> findNowEvent();
	
	// 종료된 이벤트 조회
	public int findByPrevAll();
	public List<Event> findByPrevEventPage(PagingObj obj);
	
}
