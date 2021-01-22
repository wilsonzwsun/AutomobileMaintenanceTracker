package com.pa.amt.pojo.biz;

import com.pa.amt.enums.MaintanenceEnum;
import com.pa.amt.pojo.biz.MaintanenceStrategy;
import com.pa.amt.pojo.entity.CarsMaintanence;

public class MainTanenceTask {
    private MaintanenceStrategy context;

    public MainTanenceTask(MaintanenceStrategy ms){
        context = ms;
    }

    public boolean doMainTanenceTask(CarsMaintanence cm , MaintanenceEnum name){
        return context.setMaintanenceTask(cm,name);
    }
}
