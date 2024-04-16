package com.portfolio.blog.controller;

import com.portfolio.blog.dto.chat.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chat/enter")
    public void enter(ChatMessageDto dto) {
        if(ChatMessageDto.MessageType.ENTER.equals(dto.getType()))
            dto.setMessage(dto.getSender() + "님이 입장하였습니다.");
        sendingOperations.convertAndSend("/sub/chat/" + dto.getRoomId(), dto);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto dto) {
        if(ChatMessageDto.MessageType.TALK.equals(dto.getType()))
            dto.setMessage(dto.getSender() + " : " + dto.getMessage());
        sendingOperations.convertAndSend("/sub/chat/"+dto.getRoomId(), dto);
    }

    @MessageMapping("/chat/leave")
    public void leave(ChatMessageDto dto) {
        if(ChatMessageDto.MessageType.LEAVE.equals(dto.getType()))
            dto.setMessage(dto.getSender() + "님이 퇴장하였습니다.");
        sendingOperations.convertAndSend("/sub/chat/"+dto.getRoomId(), dto);
    }

}
