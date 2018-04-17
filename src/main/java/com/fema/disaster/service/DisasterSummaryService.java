package com.fema.disaster.service;

import com.fema.disaster.entities.DisasterIntensity;
import com.fema.disaster.entities.DisasterSummaryDTO;
import com.fema.disaster.entities.StateCount;
import com.fema.disaster.exceptions.UnparseableDateException;
import com.fema.disaster.repositories.DisasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Service layer for the fema summaries
 */
@Service
public class DisasterSummaryService {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String BEGINNING_DATE = "0000-00-00 00:00:00";
    private static final Logger LOGGER = LoggerFactory.getLogger(DisasterSummaryService.class);

    @Autowired
    private DisasterRepository disasterRepository;

    /**
     * Fetches the disasters by calling methods in the repository
     *
     * @param start filter by start date
     * @param end   filter by end date
     * @param type  filter by fema type
     * @return map of fema summaries
     */
    public Map<String, DisasterSummaryDTO> getDisasters(String start, String end, String type) throws UnparseableDateException {
        Date startDate, endDate;
        List<StateCount> disasters;
        try {
            /**
             * If start date or end date are not mentioned, take default values as '0000-00-00 00:00:00'
             * and current date respectively
             */
            startDate = start.isEmpty() ? FORMAT.parse(BEGINNING_DATE) : FORMAT.parse(start);
            endDate = end.isEmpty() ? FORMAT.parse(FORMAT.format(new Date())) : FORMAT.parse(end);

            LOGGER.debug("startdate: " + startDate + "\nenddate:" + endDate + "\ntype:" + type);

            //If no type is mentioned then filter only by the dates else include type as well
            if (type.isEmpty()) {
                disasters = disasterRepository.find(startDate, endDate);
            } else {
                disasters = disasterRepository.findByType(startDate, endDate, type);
            }
        } catch (ParseException e) {
            LOGGER.error("Error while parsing date", e);
            throw new UnparseableDateException(e.getMessage() + "\nPlease specify the format in \"yyyy-MM-dd HH:mm:ss\"");
        }

        Map<String, Integer> stateCounts = new HashMap<>();

        //unwinding the list and storing it in a map
        for (StateCount stateCount : disasters) {
            stateCounts.put(stateCount.getState(), stateCount.getCount().intValue());
        }
        //if there no results from the query
        if (stateCounts.isEmpty()) {
            return new HashMap<>();
        }
        //finding max and min count to compute the correct intensities based on the results
        int max = Collections.max(stateCounts.values());
        int min = Collections.min(stateCounts.values());

        Map<String, DisasterSummaryDTO> intensitySubMap = new HashMap<>();
        //forms a map suitable to be consumed for visualization
        for (String state : stateCounts.keySet()) {
            intensitySubMap.put(state, new DisasterSummaryDTO(getIntensity(min, max, stateCounts.get(state)), stateCounts.get(state)));
        }
        return intensitySubMap;
    }

    /**
     * Compute the intensity based per count
     *
     * @param min   minimum count
     * @param max   maximum count
     * @param count count per state
     * @return intensity of disasters per state
     */
    private DisasterIntensity getIntensity(int min, int max, int count) {
        double split = (double) (max-min) / 5.0;
        if (count <= split) {
            return DisasterIntensity.VERY_LOW;
        } else if (count <= split * 2) {
            return DisasterIntensity.LOW;
        } else if (count <= split * 3) {
            return DisasterIntensity.MEDIUM;
        } else if (count <= split * 4) {
            return DisasterIntensity.HIGH;
        }
        return DisasterIntensity.VERY_HIGH;
    }
}
