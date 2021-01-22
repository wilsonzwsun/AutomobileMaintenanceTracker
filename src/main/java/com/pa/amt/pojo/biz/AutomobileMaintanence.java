package com.pa.amt.pojo.biz;

import com.pa.amt.enums.AMTStateEnum;
import com.pa.amt.enums.MaintanenceEnum;
import com.pa.amt.exception.AMTException;
import com.pa.amt.pojo.entity.Cars;
import com.pa.amt.pojo.entity.CarsMaintanence;
import com.pa.amt.pojo.biz.MainTanenceTask;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class AutomobileMaintanence {

    @Setter
    @Getter
    private Cars car;

    @Setter
    @Getter
    private List<CarsMaintanence> mList;

    @Setter
    @Getter
    private MainTanenceTask mtTask;


    public AutomobileMaintanence(Cars car, MainTanenceTask task){
        this.car = car;
        this.mtTask = task;
        mList = new ArrayList<>();
    }

    public void addMaintanenceRecord(CarsMaintanence cm, MaintanenceEnum task) throws AMTException {
        if(mtTask.doMainTanenceTask(cm,task)){
            mList.add(cm);
        }else{
            throw  new AMTException(AMTStateEnum.CAR_MAINTANENCE_ERROR);
        }
    }

}
