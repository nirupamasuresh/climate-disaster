package com.checkr.climate.repositories;

import com.checkr.climate.entities.Disaster;
import com.checkr.climate.entities.StateCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository layer for the application to get the output of the query
 */
@Repository
public interface DisasterRepository extends CrudRepository<Disaster, Long> {

    /**
     * Find by disaster type filtered by incident end and begin date
     *
     * @param startDate to satisfy condition incidentBeginDate >= startDate
     * @param endDate   to satisfy condition incidentEndDate <= endDate
     * @param type      filter by disaster type
     * @return list of state and its count
     */
    @Query("SELECT NEW com.checkr.climate.entities.StateCount(state, COUNT(state)) FROM Disaster WHERE incidentBeginDate >= :startDate AND incidentEndDate <= :endDate AND incidentEndDate NOT LIKE '0000-00-00 00:00:00' AND disasterType = :type GROUP BY state")
    List<StateCount> findByType(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("type") String type);

    /**
     * Filter by incident end and begin date only
     *
     * @param startDate to satisfy condition incidentBeginDate >= startDate
     * @param endDate   to satisfy condition incidentEndDate <= endDate
     * @return list of state and its count
     */
    @Query("SELECT NEW com.checkr.climate.entities.StateCount(state, COUNT(state)) FROM Disaster WHERE incidentBeginDate >= :startDate AND incidentEndDate <= :endDate AND incidentEndDate NOT LIKE '0000-00-00 00:00:00' GROUP BY state")
    List<StateCount> find(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
