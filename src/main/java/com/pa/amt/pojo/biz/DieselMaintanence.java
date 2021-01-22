package com.pa.amt.pojo.biz;

import com.pa.amt.enums.MaintanenceEnum;
import com.pa.amt.pojo.entity.CarsMaintanence;

public class DieselMaintanence implements MaintanenceStrategy {
    @Override
    public boolean setMaintanenceTask(CarsMaintanence cm , MaintanenceEnum task) {
        //add diesel car some other limited conditions , if(not diesel maintanence) return false
        cm.setTaskName(task.getTask());
        return true;
    }
}
