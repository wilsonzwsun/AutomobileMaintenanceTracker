package com.pa.amt.enums;

import lombok.Getter;
import lombok.Setter;

public enum MaintanenceEnum {

    CAR_MAINTANENC_OIL("change oil"),
    CAR_MAINTANENC_TIRE("tire rotation"),
    CAR_MAINTANENC_CHANGE_PARTS("change parts");

    @Getter
    @Setter
    private String task;

    MaintanenceEnum(String str){
        this.task = str;
    }

}
