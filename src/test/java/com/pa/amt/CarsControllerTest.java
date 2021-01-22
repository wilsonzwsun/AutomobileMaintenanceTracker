package com.pa.amt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.pa.amt.api.v1.CarsController;
import com.pa.amt.enums.AMTStateEnum;
import com.pa.amt.enums.CarStatusEnum;
import com.pa.amt.pojo.entity.Cars;
import com.pa.amt.pojo.entity.CarsMaintanence;
import com.pa.amt.service.AmtServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.sql.Timestamp;

/** 
* CarsController Tester. 
* 
* @author <Authors name> 
* @since <pre>21, 2021</pre>
* @version 1.0 
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarsControllerTest {

    @Autowired
    private AmtServiceImpl service;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CarsController restController;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(restController).build();
        //mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetOneCarById() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders
                .get("/api/v1/cars/10/list");
        MvcResult res = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":200,\"data\":{\"carId\":10,\"vin\":\"ns23309139343it454\",\"plate\":\"800.nld\",\"maker\":\"Nissan\",\"model\":\"pathfinder\",\"type\":\"gas\",\"odometerReading\":12340,\"produceYear\":1515686400000,\"status\":\"normal\",\"createTime\":1611087887000}}"))
                .andReturn();
        System.out.println(res.getResponse().getContentAsString());

        builder = MockMvcRequestBuilders
                .get("/api/v1/cars/100/list");
        res = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":200,\"data\":null}"))
                .andReturn();
        System.out.println(res.getResponse().getContentAsString());
    }

    /**
     *
     * Method: getCarMaintanenceById(@PathVariable("id") long id)
     *
     */
    @Test
    public void testGetCarMaintanenceById() throws Exception {

        RequestBuilder builder = MockMvcRequestBuilders ///api/v1/cars/{id}/maintenance/list
                .get("/api/v1/cars/10/maintenance/list");
        MvcResult res = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":200,\"total\":1,\"table\":[[{\"taskId\":1,\"carId\":10,\"odometerReading\":24560,\"taskName\":\"tire rotation\",\"maintenanceTime\":\"2018-01-17T16:00:00.000+00:00\",\"status\":\"normal\",\"createTime\":\"2021-01-20T20:50:47.000+00:00\"},{\"taskId\":2,\"carId\":10,\"odometerReading\":28060,\"taskName\":\"change oil\",\"maintenanceTime\":\"2019-02-17T16:00:00.000+00:00\",\"status\":\"normal\",\"createTime\":\"2021-01-20T20:50:47.000+00:00\"}]]}"))
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());
    }

    /**
     *
     * Method: saveCar(@RequestBody Cars car)
     *
     */
    @Test
    public void testSaveCar() throws Exception {

        Cars car = new Cars();
        //('ty124430567334kln30', '400.8ul','Toyota', '4runner','gas','22334','2018-5-2 00:00:00','normal'),
        car.setCarId(17);
        car.setVin("12409384ksdk");
        car.setPlate("3242-84");
        car.setMaker("Toyota");
        car.setModel("corolla");
        car.setType(CarStatusEnum.CAR_TYPE_GAS.getStatus());
        car.setOdometerReading(232453);
        car.setProduceYear(new Timestamp(System.currentTimeMillis()));
        car.setStatus(CarStatusEnum.CAR_STATUS_NORMAL.getStatus());
        car.setCreateTime(new Timestamp(System.currentTimeMillis()));

        //AMTStateEnum se = service.saveCarInfo(car,null);
        //Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String gson =ow.writeValueAsString(car);

        RequestBuilder builder = MockMvcRequestBuilders
                .post("/api/v1/cars/save")
                .contentType(APPLICATION_JSON)
                //.content(toByteArray(car))
                //.content(gson.toJson(car));
                .content(gson);//toByteArray(car)

        MvcResult res = mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":200,\"message\":\"RECORD SAVING SUCCESS\"}"))
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());

    }

    /**
     *
     * Method: saveCarM(@RequestBody CarsMaintanence carM)
     *
     */
    @Test
    public void testSaveCarM() throws Exception {

        //testcase 1  tesla update change part;
        CarsMaintanence carM = new CarsMaintanence();
        carM.setCarId(12);
        carM.setCreateTime(new Timestamp(System.currentTimeMillis()));
        carM.setTaskName("change parts");//change parts
        carM.setMaintenanceTime(new Timestamp(System.currentTimeMillis()));
        carM.setOdometerReading(293823);
        carM.setStatus("normal");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String gson = ow.writeValueAsString(carM);

        RequestBuilder builder = MockMvcRequestBuilders
                .post("/api/v1/cars/maintenance/save")
                .contentType(APPLICATION_JSON)
                //.content(toByteArray(car))
                //.content(gson.toJson(car));
                .content(gson);//toByteArray(car)

        MvcResult res = mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk()) //{"code":200,"message":"RECORD SAVING SUCCESS"}
                .andExpect(content().json("{\"code\":200,\"message\":\"RECORD SAVING SUCCESS\"}"))
                //.andExpect(content().json("{"code":200,"message":"CART TYPE DO NOT MATCH MAINTANENCE TASK"}"))
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());

        //testcase 2  tesla update change oil;
        carM.setTaskName("change oil");
        gson = toRequestBody(carM);

        builder = MockMvcRequestBuilders
                .post("/api/v1/cars/maintenance/save")
                .contentType(APPLICATION_JSON)
                //.content(toByteArray(car))
                //.content(gson.toJson(car));
                .content(gson);//toByteArray(car)

        res = mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk()) //{"code":200,"message":"RECORD SAVING SUCCESS"}
                .andExpect(content().json("{\"code\":200,\"message\":\"CART TYPE DO NOT MATCH MAINTANENCE TASK\"}"))
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());

        //testcase 3  toyata  update change error;
    }

    /**
     *
     * Method: updateCar(@PathVariable("token") String tk, @RequestBody Cars car)
     *
     */
    @Test
    public void testUpdateCar() throws Exception {
        Cars car = new Cars();
        //('ty124430567334kln30', '400.8ul','Toyota', '4runner','gas','22334','2018-5-2 00:00:00','normal'),
        car.setCarId(6);
        car.setVin("pfdok84ksdk");
        car.setPlate("3242-84");
        car.setMaker("Toyota");
        car.setModel("corolla");
        car.setType(CarStatusEnum.CAR_TYPE_GAS.getStatus());
        car.setOdometerReading(232453);
        car.setProduceYear(new Timestamp(System.currentTimeMillis()));
        car.setStatus(CarStatusEnum.CAR_STATUS_NORMAL.getStatus());
        car.setCreateTime(new Timestamp(System.currentTimeMillis()));

        //AMTStateEnum se = service.saveCarInfo(car,null);
        //Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String gson =ow.writeValueAsString(car);

        RequestBuilder builder = MockMvcRequestBuilders
                .put("/api/v1/cars/02943ldsfjsdfsfsdll/update")
                .contentType(APPLICATION_JSON)
                //.content(toByteArray(car))
                //.content(gson.toJson(car));
                .content(gson);//toByteArray(car)

        MvcResult res = mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(content().json("{\"code\":200,\"message\":\"RECORD SAVING SUCCESS\"}"))
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());

    }

    /**
     *
     * Method: updateCarM(@PathVariable("token") String tk, @RequestBody CarsMaintanence carsM)
     *
     */
    @Test
    public void testUpdateCarM() throws Exception {
        CarsMaintanence carM = new CarsMaintanence();
        carM.setCarId(10002); //NOT EXIST
        carM.setCreateTime(new Timestamp(System.currentTimeMillis()));
        carM.setTaskName("change parts");//change parts
        carM.setMaintenanceTime(new Timestamp(System.currentTimeMillis()));
        carM.setOdometerReading(293823);
        carM.setStatus("normal");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String gson = ow.writeValueAsString(carM);

        RequestBuilder builder = MockMvcRequestBuilders
                .put("/api/v1/cars/ad%335adad/maintenance/update")
                .contentType(APPLICATION_JSON)
                //.content(toByteArray(car))
                //.content(gson.toJson(car));
                .content(gson);//toByteArray(car)

        MvcResult res = mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk()) //{"code":200,"message":"RECORD SAVING SUCCESS"}
                .andExpect(content().json("{\"code\":200,\"message\":\"TOKEN INVALID\"}"))
                //.andExpect(content().json("{"code":200,"message":"CART TYPE DO NOT MATCH MAINTANENCE TASK"}"))
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());
    }

    /**
     *
     * Method: deleteCarM(@PathVariable("id") String id, @PathVariable("token") String tk)
     *
     */
    @Test
    public void testDeleteCarM() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders ///api/v1/cars/{id}/maintenance/list
                .delete("/api/v1/cars/30/1322*8sdjssg0459kglfal/maintenance/delete");
        MvcResult res = mvc.perform(builder)
                .andExpect(status().isOk())
                //.andExpect(content().json("{\"code\":200,\"message\":\"RECORD DELETE SUCCESS\"}"))
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());
    }

    /**
     *
     * Method: deleteCar(@PathVariable("id") String id, @PathVariable("token") String tk)
     *
     */
    @Test
    public void testDeleteCar() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders ///api/v1/cars/{id}/maintenance/list
                .delete("/api/v1/cars/30/1322*8sdjssg0459kglfal/delete");
        MvcResult res = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":200,\"message\":\"RECORD DELETE SUCCESS\"}"))
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());
    }


    public byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
    public String toRequestBody(Object obj){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(obj);
        }catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }
} 
