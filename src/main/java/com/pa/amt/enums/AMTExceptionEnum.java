package com.pa.amt.enums;

import com.pa.amt.exception.ServiceException;

public enum AMTExceptionEnum implements ServiceException {

    REQUEST_NULL(400, "REQUEST ERROR"),
    SERVER_ERROR(500, "SERVER ERROR"),
    RESOURCE_NOT_FOUND(404, "REQUEST ERROR");

    AMTExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
