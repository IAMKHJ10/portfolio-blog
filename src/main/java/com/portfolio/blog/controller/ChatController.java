package com.portfolio.blog.controller;

import com.portfolio.blog.dto.chat.ChatRoomDto;
import com.portfolio.blog.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "chat/room";
    }

    // 모든 채팅방 목록 반환
    @ResponseBody
    @GetMapping("/rooms")
    public List<ChatRoomDto> room() {
        return chatService.findAllRoom();
    }

    // 채팅방 생성
    @ResponseBody
    @PostMapping("/room")
    public ChatRoomDto createRoom(@RequestParam(name = "name") String name) {
        return chatService.createRoom(name);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable(name = "roomId") String roomId) {
        model.addAttribute("roomId", roomId);
        return "chat/roomDetail";
    }

    // 특정 채팅방 조회
    @ResponseBody
    @GetMapping("/room/{roomId}")
    public ChatRoomDto roomInfo(@PathVariable(name = "roomId") String roomId) {
        return chatService.findById(roomId);
    }

}
