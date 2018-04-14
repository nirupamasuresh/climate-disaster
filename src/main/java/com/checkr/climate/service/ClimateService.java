package com.checkr.climate.service;

import com.checkr.climate.entities.*;
import com.checkr.climate.exceptions.UnparseableDateException;
import com.checkr.climate.repositories.DisasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.nimbus.State;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ClimateService {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String BEGINNING_DATE = "0000-00-00 00:00:00";
    private static final Logger LOGGER = LoggerFactory.getLogger(ClimateService.class);

    @Autowired
    private DisasterRepository disasterRepository;

    public Map<String, IntensitySub> getDisasters(String start, String end, String type) {
        Date startDate, endDate;
        List<StateCount> disasters;
        try {
            if (start.isEmpty()) {
                startDate = FORMAT.parse(BEGINNING_DATE);
            } else {
                startDate = FORMAT.parse(start);
            }
            if (end.isEmpty()) {
                endDate = FORMAT.parse(FORMAT.format(new Date()));
            } else {
                endDate = FORMAT.parse(end);
            }
            LOGGER.info("startdate: " + startDate + "\nenddate:" + endDate + "\ntype:" + type);
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
        for (StateCount stateCount:disasters) {
            stateCounts.put(stateCount.getState(), stateCount.getCount().intValue());
        }
        int max = Collections.max(stateCounts.values());
        int min = Collections.min(stateCounts.values());
        Map<String,IntensitySub> intensitySubMap = new HashMap<>();
        for (String state: stateCounts.keySet()) {
            intensitySubMap.put(state, new IntensitySub(getIntensity(min, max, stateCounts.get(state)), stateCounts.get(state)));
        }
        return intensitySubMap;
    }

    private DisasterIntensity getIntensity(int min, int max, int count) {
        float split = (min + max) / 5;
        if(count <= split) {
            return DisasterIntensity.VERY_LOW;
        } else if (count <= split * 2) {
            return DisasterIntensity.LOW;
        } else if (count <= split * 3) {
            return DisasterIntensity.MEDIUM;
        }else if (count <= split * 4) {
            return DisasterIntensity.HIGH;
        }
        return DisasterIntensity.VERY_HIGH;
    }
}
