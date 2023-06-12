package com.dodam.hotel.handler;

import com.dodam.hotel.dto.UserResponseDto;
import com.dodam.hotel.dto.socket.ChatRoom;
import com.dodam.hotel.dto.socket.MessageType;
import com.dodam.hotel.dto.socket.SocketMessageDto;
import com.dodam.hotel.enums.ChatRole;
import com.dodam.hotel.repository.interfaces.ChatRepository;
import com.dodam.hotel.util.Define;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SocketHandler extends TextWebSocketHandler {
	
    private ChatRepository chatRepository;
    
    @Autowired
    public SocketHandler(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }
    
    // 매니저 세션
    private static Set<WebSocketSession> managerSessions = ConcurrentHashMap.newKeySet();
    // 유저 세션
    private static Set<WebSocketSession> userSessions = ConcurrentHashMap.newKeySet();

    private static Map<WebSocketSession, List<TextMessage>> awaitMessageList = new HashMap<>();
    private static Map<String, WebSocketSession> userSessionMap = new HashMap<>();
    private static Map<WebSocketSession, ChatRoom> roomMap = new HashMap();
    // 연결 시 이벤트(소켓 연결)
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 유저인 경우
        if(session.getAttributes().get(Define.PRINCIPAL) != null){
            userSessions.add(session);
            userSessionMap.put(((UserResponseDto.LoginResponseDto) session.getAttributes().get(Define.PRINCIPAL)).getEmail() + "(" + ((UserResponseDto.LoginResponseDto) session.getAttributes().get(Define.PRINCIPAL)).getName() + ")", session);
            awaitMessageList.put(session, new ArrayList<>());
            // 유저 전용 방 만들기
            ChatRoom chatRoom = new ChatRoom(session);
            roomMap.put(session, chatRoom);
        }
        // 매니저인 경우
        else if(session.getAttributes().get(Define.MANAGERPRINCIPAL) != null){
            managerSessions.add(session);
        }else{
            String message = "로그인 후 이용해 주세요!";
            ObjectMapper objectMapper = new ObjectMapper();
            SocketMessageDto msg = new SocketMessageDto();
            msg.setMsg(message);
            msg.setType(MessageType.ERROR);
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(msg)));
        }
    }

    // 메시지 이벤트
    @Transactional
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ObjectMapper objectMapper = new ObjectMapper();
        SocketMessageDto msg = objectMapper.readValue(payload, SocketMessageDto.class);
        if(msg.getType() == MessageType.ENTER){
            WebSocketSession targetSession = userSessionMap.get(msg.getRoomName());
            ChatRoom room = roomMap.get(targetSession);
            room.setManagerSession(session);

            WebSocketSession userSession =  room.getUserSession();
            userSession.sendMessage(message);

            List<TextMessage> queueMessage = awaitMessageList.get(userSession);
            queueMessage.stream().forEach(textmsg -> {
                try {
                    session.sendMessage(textmsg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            queueMessage.clear();
        }

        if(msg.getType() == MessageType.MANAGER_CHAT){
            WebSocketSession targetSession = userSessionMap.get(msg.getRoomName());
            targetSession.sendMessage(message);

            UserResponseDto.LoginResponseDto userInfo = (UserResponseDto.LoginResponseDto) targetSession.getAttributes().get(Define.PRINCIPAL);
            Integer targetUserId = userInfo.getId();
            ChatRole role = ChatRole.MANAGER;
            chatRepository.insertManagerChat(targetUserId, msg.getMsg(), role);
            chatRepository.updateUserMessageStatus(targetUserId);
        }

        if(msg.getType() == MessageType.CHAT){
            ChatRoom room = roomMap.get(session);
            WebSocketSession managerSession = room.getManagerSession();
            if(managerSession == null){
                List<TextMessage> textMessageList = awaitMessageList.get(session);
                textMessageList.add(message);
            }else{
                managerSession.sendMessage(message);
            }
            UserResponseDto.LoginResponseDto userInfo = (UserResponseDto.LoginResponseDto) session.getAttributes().get(Define.PRINCIPAL);
            Integer targetUserId = userInfo.getId();
            ChatRole role = ChatRole.USER;
            chatRepository.insertChat(targetUserId, msg.getMsg(), role);
        }
    }

    // 웹 소켓 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        WebSocketSession managerSession = roomMap.get(session).getManagerSession();
//        TextMessage message = null;
//        if(session.getAttributes().get(Define.PRINCIPAL) != null){
//            message = new TextMessage("");
//        }
//        // 매니저인 경우
//        else{
//            message = new TextMessage("");
//        }
//        managerSession.sendMessage(message);
    	UserResponseDto.LoginResponseDto userSession = (UserResponseDto.LoginResponseDto) session.getAttributes().get(Define.PRINCIPAL);
    	if(userSession != null) {
    		chatRepository.updateUserMessageStatus(userSession.getId());
    	}
        roomMap.remove(session);
        userSessions.remove(session);
        awaitMessageList.remove(session);
    }
    public List<ChatRoom> viewAllRoom(){
        List<ChatRoom> chatRoomList = new ArrayList<>();
        userSessions.stream().forEach(userSession -> {
            ChatRoom room = roomMap.get(userSession);
            chatRoomList.add(room);
        });
        return chatRoomList;
    }

    public ChatRoom findRoom(String roomName){
        for(WebSocketSession session : roomMap.keySet()){
            if(roomMap.get(session).getRoomName().equals(roomName)){
                return roomMap.get(session);
            }
        }
        return null;
    }
}
