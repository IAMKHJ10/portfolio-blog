package com.portfolio.blog.service;

import com.portfolio.blog.dto.chat.ChatRoomDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private Map<String, ChatRoomDto> chatRooms;

    @PostConstruct
    //의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<ChatRoomDto> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        List<ChatRoomDto> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);
        return result;
    }

    //채팅방 하나 불러오기
    public ChatRoomDto findById(String roomId) {
        return chatRooms.get(roomId);
    }

    //채팅방 생성
    public ChatRoomDto createRoom(String name) {
        ChatRoomDto chatRoom = ChatRoomDto.builder()
                .roomId(UUID.randomUUID().toString())
                .roomName(name)
                .build();
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

}
