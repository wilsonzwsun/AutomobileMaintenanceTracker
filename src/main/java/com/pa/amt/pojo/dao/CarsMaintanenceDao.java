package com.pa.amt.pojo.dao;

import com.pa.amt.pojo.entity.CarsMaintanence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsMaintanenceDao extends JpaRepository<CarsMaintanence,Long> {
    @Query(name = "select * from CarsMaintanence cm where cm.carId=?1")
     List<CarsMaintanence> findByCarId(@Param("carId")long carId);
}
