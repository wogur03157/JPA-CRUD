package com.mever.api.domain.upload.exception;

import com.mever.api.global.exception.BaseException;
import com.mever.api.global.exception.BaseExceptionType;

public class UploadException extends BaseException {
    private BaseExceptionType exceptionType;

    public UploadException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
