package com.crud.cloud.evanliu2968.exception;

public class RequestException extends BusinessException {

    public RequestException(int code, String feedback) {
        super(code, feedback);
    }
}
