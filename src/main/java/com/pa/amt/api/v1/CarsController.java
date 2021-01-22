package com.pa.amt.api.v1;

import com.pa.amt.enums.AMTExceptionEnum;
import com.pa.amt.enums.AMTStateEnum;
import com.pa.amt.enums.CarStatusEnum;
import com.pa.amt.exception.AMTException;
import com.pa.amt.pojo.entity.Cars;
import com.pa.amt.pojo.dao.*;
import com.pa.amt.pojo.entity.CarsMaintanence;
import com.pa.amt.service.AmtServiceImpl;
import com.pa.amt.util.TokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CarsController {

    @Autowired
    private CarsDao carDao;

    @Autowired
    private CarsMaintanenceDao carMDao;

    @Autowired
    private AmtServiceImpl amtService;

    //http://localhost:8080/api/v1/cars/12/maintenance/list
    // http://localhost:8080/api/v1/cars/13/list
    @GetMapping("/api/v1/cars/{id}/list")
    public HttpResponse getOneCarById(@PathVariable("id") long id){
        final  Optional<Cars>  car;
        Map<String, Object> map = new HashMap<>();
        try{
            //car = carDao.findById(id);
           // car = carDao.findByCarIdAndStatus(id, CarStatusEnum.CAR_STATUS_NORMAL.getStatus());
            map = amtService.listCarInfo(id);
            car = (Optional<Cars>) map.get(Cars.class.getName());
        }catch (Exception e){
            return HttpResponse.error(AMTStateEnum.QUERRY_ERROR.getMessage()).setCode(AMTStateEnum.QUERRY_ERROR.getCode());
        }
        return HttpResponse.ok().setData(car);
    }

    @GetMapping("/api/v1/cars/{id}/maintenance/list")
    public HttpResponse getCarMaintanenceById(@PathVariable("id") long id){
        //return carMDao.findAllById(id);
        Map<String, Object> map = new HashMap<>();
        List<CarsMaintanence> res;
        try {
            map = amtService.listCarInfo(id);
            res = (List<CarsMaintanence>)map.get(CarsMaintanence.class.getName());
        }catch(Exception e){
            return HttpResponse.error(AMTStateEnum.QUERRY_ERROR.getMessage()).setCode(AMTStateEnum.QUERRY_ERROR.getCode());
        }

        return HttpResponse.ok().addListItem(res);
    }

    // /api/v{version}/cars/save
    @PostMapping("/api/v1/cars/save")
    public HttpResponse saveCar(@RequestBody Cars car) {
        AMTStateEnum status = AMTStateEnum.SAVE_ERROR;
        try{
            status = amtService.saveCarInfo(car,null);
            return HttpResponse.ok().setMessage(status.getMessage());
        }catch (AMTException e){
            return HttpResponse.error(e.getMessage());
        }finally {
            return HttpResponse.error(AMTExceptionEnum.SERVER_ERROR.getMessage());
        }

    }

    // /api/v{version}/cars/save
    @PostMapping("/api/v1/cars/maintenance/save")
    public HttpResponse saveCarM(@RequestBody  CarsMaintanence carM) {
        AMTStateEnum status = AMTStateEnum.SAVE_ERROR;
        try{
            Optional<Cars> car = carDao.findById(carM.getCarId());
            if(car == null) return HttpResponse.error(AMTStateEnum.UPDATE_MAINTANENCE_ERROR.getMessage());
            status = amtService.saveCarInfo(null,carM);

            return HttpResponse.ok().setMessage(status.getMessage());
        }catch (AMTException e){
            return HttpResponse.error(e.getMessage());
        }catch(Exception e)  {
            return HttpResponse.error(AMTExceptionEnum.SERVER_ERROR.getMessage());
        }
    }

    @PutMapping("/api/v1/cars/{token}/update")
    public HttpResponse updateCar(@PathVariable("token") String tk, @RequestBody Cars car) {
        if(!TokenCheck.updateAuth(tk)) {
            return HttpResponse.ok().setMessage(AMTStateEnum.TOKEN_INVALID.getMessage());
        }
        try{
            AMTStateEnum status = amtService.saveCarInfo(car,null);
        }catch (AMTException e){
            return HttpResponse.error(AMTStateEnum.UPDATE_ERROR.getMessage());
        }

        return HttpResponse.ok();

    }

    @PutMapping("/api/v1/cars/{token}/maintenance/update")
    public HttpResponse updateCarM(@PathVariable("token") String tk, @RequestBody CarsMaintanence carsM) {
        if(!TokenCheck.updateAuth(tk)) {
            return HttpResponse.ok().setMessage(AMTStateEnum.TOKEN_INVALID.getMessage());
        }
        try{
            AMTStateEnum status = amtService.saveCarInfo(null,carsM);
        }catch (AMTException e){
            return HttpResponse.error(AMTStateEnum.UPDATE_ERROR.getMessage());
        }

        return HttpResponse.ok().setMessage(AMTStateEnum.UPDATE_SUCCESS.getMessage());

    }

    @DeleteMapping("/api/v1/cars/{id}/{token}/delete")
    public HttpResponse deleteCarM(@PathVariable("id") String id,@PathVariable("token") String tk) {
        if(!TokenCheck.deleteAuth(tk)) {
            return HttpResponse.ok().setMessage(AMTStateEnum.TOKEN_INVALID.getMessage());
        }
        try{
            AMTStateEnum status = amtService.deleteCarInfo(Long.valueOf(id));
        }catch (AMTException e){
            return HttpResponse.error(AMTStateEnum.DELETE_ERROR.getMessage());
        }

        return HttpResponse.ok().setMessage(AMTStateEnum.DELETE_SUCCESS.getMessage());
    }

    @DeleteMapping("/api/v1/cars/cars/{id}/{token}/maintenance/delete")
    public HttpResponse deleteCar(@PathVariable("id") String id,@PathVariable("token") String tk) {
        if(!TokenCheck.updateAuth(tk)) {
            return HttpResponse.ok().setMessage(AMTStateEnum.TOKEN_INVALID.getMessage());
        }
        try{
            AMTStateEnum status = amtService.deleteCarInfo(Long.valueOf(id));
        }catch (AMTException e){
            return HttpResponse.error(AMTStateEnum.DELETE_ERROR.getMessage());
        }

        return HttpResponse.ok().setMessage(AMTStateEnum.DELETE_SUCCESS.getMessage());
    }

}
