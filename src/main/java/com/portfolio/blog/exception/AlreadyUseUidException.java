package com.portfolio.blog.exception;

public class AlreadyUseUidException extends RuntimeException {

    private static final String MESSAGE = "이미 등록된 사용자입니다.";

    public AlreadyUseUidException() {
        super(MESSAGE);
    }
}



