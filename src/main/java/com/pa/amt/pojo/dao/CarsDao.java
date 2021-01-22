package com.pa.amt.pojo.dao;

import com.pa.amt.pojo.entity.Cars;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarsDao extends JpaRepository<Cars,Long> {

    @Query(name="select * from Cars c where c.carId=?1 and c.status=?2")
    Optional<Cars> findByCarIdAndStatus(@Param("carId")long carId,@Param("status")String status);

    Optional<Cars> findByCarId(@Param("carId")long carId);
}
