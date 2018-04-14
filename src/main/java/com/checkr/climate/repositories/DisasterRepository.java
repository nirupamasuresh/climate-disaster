package com.checkr.climate.repositories;

import com.checkr.climate.entities.Disaster;
import com.checkr.climate.entities.StateCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface DisasterRepository extends CrudRepository<Disaster, Long>{

    @Query("SELECT NEW com.checkr.climate.entities.StateCount(state, COUNT(state)) FROM Disaster WHERE incidentBeginDate >= :startDate AND incidentEndDate <= :endDate AND incidentEndDate NOT LIKE '0000-00-00 00:00:00' AND disasterType = :type GROUP BY state")
    List<StateCount> findByType(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("type") String type);

    @Query("SELECT NEW com.checkr.climate.entities.StateCount(state, COUNT(state)) FROM Disaster WHERE incidentBeginDate >= :startDate AND incidentEndDate <= :endDate AND incidentEndDate NOT LIKE '0000-00-00 00:00:00' GROUP BY state")
    List<StateCount> find(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
