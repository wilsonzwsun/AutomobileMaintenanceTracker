package com.pa.amt.pojo.biz;

import com.pa.amt.enums.MaintanenceEnum;
import com.pa.amt.pojo.entity.CarsMaintanence;

public class ElectricMaintanenceTask implements MaintanenceStrategy {

    @Override
    public boolean setMaintanenceTask(CarsMaintanence cm , MaintanenceEnum task) {
        if(task == MaintanenceEnum.CAR_MAINTANENC_OIL){ //add some other limited conditions
            return false;
        }
        cm.setTaskName(task.getTask());
        return true;
    }
}
