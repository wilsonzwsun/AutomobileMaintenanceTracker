package com.pa.amt.pojo.entity;

import com.pa.amt.enums.CarStatusEnum;
import com.pa.amt.enums.MaintanenceEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "cars_mt_tb", schema = "automobile_maintanence", catalog = "")
public class CarsMaintanence {
    private long taskId;
    private long carId;
    private int odometerReading;
    private String taskName;
    private Timestamp maintenanceTime;
    private String status;
    private Timestamp createTime;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "task_id")
    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "car_id")
    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    @Basic
    @Column(name = "odometer_reading")
    public int getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(int odometerReading) {
        this.odometerReading = odometerReading;
    }

    @Basic
    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "maintenance_time")
    public Timestamp getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(Timestamp maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
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
    @Column(name = "create_time")
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

        CarsMaintanence that = (CarsMaintanence) o;

        if (taskId != that.taskId) return false;
        if (carId != that.carId) return false;
        if (odometerReading != that.odometerReading) return false;
        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (maintenanceTime != null ? !maintenanceTime.equals(that.maintenanceTime) : that.maintenanceTime != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (taskId ^ (taskId >>> 32));
        result = 31 * result + (int) (carId ^ (carId >>> 32));
        result = 31 * result + odometerReading;
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (maintenanceTime != null ? maintenanceTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
//   public abstract void doMaintanenceTask(MaintanenceEnum task);
}
