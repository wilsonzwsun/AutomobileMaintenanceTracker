package com.pa.amt.enums;

public enum CarStatusEnum {
    CAR_TYPE_ELECTRIC("electric"),
    CAR_TYPE_GAS("gas"),
    CAR_TYPE_DIESEL("diesel"),

    CAR_STATUS_REMOVED("removed"),
    CAR_STATUS_NORMAL("normal");

    private String status;
    CarStatusEnum(String str){
        this.status = str;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
