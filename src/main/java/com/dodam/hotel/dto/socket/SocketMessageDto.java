package com.dodam.hotel.dto.socket;


import lombok.Data;

/**
 * @author lhs-devloper
 */
@Data
public class SocketMessageDto {
    private MessageType type;
    private String msg;
    private String roomName;
}
