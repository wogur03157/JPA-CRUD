package com.mever.api.domain.upload.exception;

import com.mever.api.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum UploadExceptionType implements BaseExceptionType {
    //== 회원가입, 로그인 시 ==//
    ALREADY_EXIST_USERNAME(600, HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    WRONG_PASSWORD(601,HttpStatus.BAD_REQUEST, "비밀번호는 최소 8자 이상이어야 하며, 특수문자와 숫자를 포함해야 합니다."),
    NOT_FOUND_VIDEO(702, HttpStatus.NOT_FOUND, "영상 정보가 없습니다.");


    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    UploadExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
