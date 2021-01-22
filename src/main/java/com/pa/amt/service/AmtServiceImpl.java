package com.pa.amt.service;

import com.pa.amt.enums.AMTExceptionEnum;
import com.pa.amt.enums.AMTStateEnum;
import com.pa.amt.enums.CarStatusEnum;
import com.pa.amt.enums.MaintanenceEnum;
import com.pa.amt.exception.AMTException;
import com.pa.amt.pojo.biz.AutomobileMaintanence;
import com.pa.amt.pojo.biz.ElectricMaintanenceTask;
import com.pa.amt.pojo.biz.GasMaintanenceTask;
import com.pa.amt.pojo.biz.MainTanenceTask;
import com.pa.amt.pojo.dao.CarsDao;
import com.pa.amt.pojo.dao.CarsMaintanenceDao;
import com.pa.amt.pojo.entity.*;
import com.pa.amt.util.MaintanenceTaskNameCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AmtServiceImpl implements AmtService {

    @Autowired
    private CarsDao carsDao;

    @Autowired
    private CarsMaintanenceDao carsMDao;

    private AutomobileMaintanence am;

    @Override
    public Map<String, Object> listCarInfo(long carid) throws AMTException {
        Map<String,Object> model = new HashMap<>();

       // Optional<Cars> car = carsDao.findById(carid);
        Optional<Cars> car = carsDao.findByCarIdAndStatus(carid, CarStatusEnum.CAR_STATUS_NORMAL.getStatus());
        List<CarsMaintanence> carsMlist = carsMDao.findByCarId(carid);

        model.put(Cars.class.getName(),car);
        model.put(CarsMaintanence.class.getName(),carsMlist);

//        for(CarsMaintanence cm : carsMlist){
//            System.out.println(cm);
//        }
        return model;
    }

    @Override
    //@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public AMTStateEnum saveCarInfo(Cars car, CarsMaintanence carM) throws AMTException {
        if (car == null && carM == null) {
            throw new AMTException(AMTExceptionEnum.REQUEST_NULL);
        }

        if (car == null) {
            try {
                //check maintannence task name,
                //if task name is not { "change oil","tire rotation","Change parts"}, will throw IllegalArgumentException
                MaintanenceEnum mTask = MaintanenceTaskNameCheck.checkName(carM.getTaskName());
                MainTanenceTask mainTanenceTask;
                Optional<Cars> theCar = carsDao.findByCarId(carM.getCarId());

                if(theCar.isPresent()){
                    Cars c = theCar.get();
                    if ( c.getType().equals(CarStatusEnum.CAR_TYPE_ELECTRIC.getStatus())) {
                        mainTanenceTask = new MainTanenceTask(new ElectricMaintanenceTask());
                    } else {
                        mainTanenceTask = new MainTanenceTask(new GasMaintanenceTask());
                    }
                    am = new AutomobileMaintanence(c, mainTanenceTask);
                    //electric car do change oil will throw exception
                    am.addMaintanenceRecord(carM, mTask);
                    //save car maintanence task record
                    carsMDao.save(carM);

                }
            } catch (IllegalArgumentException e) {
                return AMTStateEnum.CAR_MAINTANENCE_ERROR;
            } catch (AMTException e) {
                return AMTStateEnum.SAVE_ERROR;
            } catch(Exception e)  {
                return AMTStateEnum.SAVE_ERROR;
            }

            return AMTStateEnum.CAR_INFO_NOT_FOUND;
        }

        if (carM == null) {
            try {
                Cars entity = carsDao.save(car);
                return AMTStateEnum.SAVE_SUCCESS;
            } catch (AMTException e) {
                return AMTStateEnum.SAVE_ERROR;
            } catch(Exception e) {
                return AMTStateEnum.SAVE_ERROR;
            }
        }

        return AMTStateEnum.SAVE_SUCCESS;
    }

    @Override
    public AMTStateEnum updateCarInfo(Cars car, CarsMaintanence carM) throws AMTException {
        return saveCarInfo(car, carM);
    }

    @Override
    public AMTStateEnum deleteCarInfo(long carid) throws AMTException {
        try {
            carsDao.deleteById(carid);
            carsMDao.deleteById(carid);
        }catch (Exception e){
            return AMTStateEnum.DELETE_ERROR;
        }
        return AMTStateEnum.DELETE_SUCCESS;
    }
}
