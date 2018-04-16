package com.fema.disaster.repositories;

import com.fema.disaster.entities.Disaster;
import com.fema.disaster.entities.StateCount;
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
     * Find by fema type filtered by incident end and begin date
     *
     * @param startDate to satisfy condition incidentBeginDate >= startDate
     * @param endDate   to satisfy condition incidentEndDate <= endDate
     * @param type      filter by fema type
     * @return list of state and its count
     */
    @Query("SELECT NEW com.fema.disaster.entities.StateCount(state, COUNT(state)) FROM Disaster WHERE incidentBeginDate >= :startDate AND incidentEndDate <= :endDate AND incidentEndDate is not null AND disasterType = :type GROUP BY state")
    List<StateCount> findByType(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("type") String type);

    /**
     * Filter by incident end and begin date only
     *
     * @param startDate to satisfy condition incidentBeginDate >= startDate
     * @param endDate   to satisfy condition incidentEndDate <= endDate
     * @return list of state and its count
     */
    @Query("SELECT NEW com.fema.disaster.entities.StateCount(state, COUNT(state)) FROM Disaster WHERE incidentBeginDate >= :startDate AND incidentEndDate <= :endDate AND incidentEndDate is not null GROUP BY state")
    List<StateCount> find(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
