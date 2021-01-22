package com.pa.amt.pojo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "cars_tb", schema = "automobile_maintanence", catalog = "")
public class Cars {
    private long carId;
    private String vin;
    private String plate;
    private String maker;
    private String model;
    private String type;
    private int odometerReading;
    private Timestamp produceYear;
    private String status;
    private Timestamp createTime;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "car_id")
    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    @Basic
    @Column(name = "vin",nullable = false, unique = true)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Basic
    @Column(name = "plate",nullable = false)
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Basic
    @Column(name = "maker",nullable = false)
    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Basic
    @Column(name = "model",nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "type",nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "odometer_reading",nullable = false)
    public int getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(int odometerReading) {
        this.odometerReading = odometerReading;
    }

    @Basic
    @Column(name = "produce_year",nullable = false)
    public Timestamp getProduceYear() {
        return produceYear;
    }

    public void setProduceYear(Timestamp produceYear) {
        this.produceYear = produceYear;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_time",nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cars cars = (Cars) o;

        if (carId != cars.carId) return false;
        if (odometerReading != cars.odometerReading) return false;
        if (vin != null ? !vin.equals(cars.vin) : cars.vin != null) return false;
        if (plate != null ? !plate.equals(cars.plate) : cars.plate != null) return false;
        if (maker != null ? !maker.equals(cars.maker) : cars.maker != null) return false;
        if (model != null ? !model.equals(cars.model) : cars.model != null) return false;
        if (type != null ? !type.equals(cars.type) : cars.type != null) return false;
        if (produceYear != null ? !produceYear.equals(cars.produceYear) : cars.produceYear != null) return false;
        if (status != null ? !status.equals(cars.status) : cars.status != null) return false;
        if (createTime != null ? !createTime.equals(cars.createTime) : cars.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (carId ^ (carId >>> 32));
        result = 31 * result + (vin != null ? vin.hashCode() : 0);
        result = 31 * result + (plate != null ? plate.hashCode() : 0);
        result = 31 * result + (maker != null ? maker.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + odometerReading;
        result = 31 * result + (produceYear != null ? produceYear.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

}
