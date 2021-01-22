package com.pa.amt.enums;

import com.pa.amt.exception.ServiceException;

public enum AMTStateEnum implements ServiceException {

    QUERRY_SUCCESS(200,"QUERRY SUCCESS"),
    UPDATE_SUCCESS(200, "RECORD UPDATING SUCCESS"),
    DELETE_SUCCESS(200, "RECORD DELETE SUCCESS"),
    SAVE_SUCCESS(200, "RECORD SAVING SUCCESS"),
    QUERRY_ERROR(504,"QUERRY FAIL"),
    UPDATE_ERROR(501, "RECORD UPDATING FAIL"),
    UPDATE_MAINTANENCE_ERROR(506, "UPDATE MAINTANENCE FAIL"),
    DELETE_ERROR(502, "RECORD DELETE FAIL"),
    SAVE_ERROR(503, "RECORD SAVING FAIL"),
    CAR_MAINTANENCE_ERROR(506, "CART TYPE DO NOT MATCH MAINTANENCE TASK"),
    CAR_INFO_NOT_FOUND(506, "DON NOT HAVE THIS CAR INFORMATION"),
    TOKEN_INVALID(401,"TOKEN INVALID"),
    RECORD_NOT_FOUND(400, "RECORD NOT FOUND ");

    AMTStateEnum(int code, String message) {
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
