package com.pa.amt.service;

import com.pa.amt.enums.AMTStateEnum;
import com.pa.amt.exception.AMTException;
import com.pa.amt.pojo.entity.Cars;
import com.pa.amt.pojo.entity.CarsMaintanence;

import java.util.List;
import java.util.Map;

public interface AmtService {

    //-	listing : query car / maintenance record by id
    //[GET] /api/v{version}/cars/{id}/list
    Map<String,Object> listCarInfo(long carid) throws AMTException;

    //-	adding
    //[POST]/api/v{version}/cars/save
    //[POST]/api/v{version}/cars/{id}/maintenance/save
    AMTStateEnum saveCarInfo(Cars car,CarsMaintanence carM) throws AMTException;

    //-	updating
    //[PUT] /api/v{version}/cars/{id}/{token}/update   carjson @RequestBody
    //[PUT] /api/v{version}/cars/{id}/{token}/maintenance/update
    AMTStateEnum updateCarInfo(Cars car,CarsMaintanence carM) throws AMTException;

//-	deleting
//[DELETE] /api/v{version}/cars/{id}/{token}/delete
//[DELETE] /api/v{version}/cars/{id}/{token}/delete
    AMTStateEnum deleteCarInfo(long carid) throws AMTException;
}
