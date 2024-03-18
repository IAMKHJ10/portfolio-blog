package com.portfolio.blog.dto.common;

import lombok.Data;

@Data
public class MessageDto<T> {
    private String msg;
    private T data;

    public MessageDto(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

}
