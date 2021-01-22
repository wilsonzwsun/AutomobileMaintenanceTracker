package com.pa.amt.exception;

public class AMTException extends RuntimeException {

    private Integer code;
    private String message;

    public AMTException(ServiceException serviceException) {
        this.code = serviceException.getCode();
        this.message = serviceException.getMessage();
    }

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
