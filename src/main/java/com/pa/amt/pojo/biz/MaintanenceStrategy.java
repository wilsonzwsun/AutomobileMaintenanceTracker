package com.pa.amt.pojo.biz;

import com.pa.amt.enums.MaintanenceEnum;
import com.pa.amt.pojo.entity.CarsMaintanence;

public interface MaintanenceStrategy {
    boolean setMaintanenceTask(CarsMaintanence cm , MaintanenceEnum task);
}
