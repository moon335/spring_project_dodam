package com.dodam.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodam.hotel.repository.interfaces.ChatRepository;

@Service
public class ChatService {
	
	@Autowired
	private ChatRepository chatRepository;
	
	public Integer readNewMessageCount() {
		Integer resultCount = chatRepository.findNewMessage();
		return resultCount;
	}
	
}
