package com.checkr.climate.controllers;

import com.checkr.climate.entities.DisasterSummaryDTO;
import com.checkr.climate.exceptions.UnparseableDateException;
import com.checkr.climate.service.DisasterSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller to access disaster summaries
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/api")
public class DisasterSummaryController {

    @Autowired
    DisasterSummaryService disasterSummaryService;

    /**
     * Fetches the disaster summaries according to the params
     *
     * @param start filter by date greater than or equal to start
     * @param end   filter by date less than or equal to end
     * @param type  filter by distaster type
     * @return map of disaster summary entity
     * @throws UnparseableDateException
     */
    @GetMapping(path = "/disaster")
    public Map<String, DisasterSummaryDTO> getDisasterSummaries(@RequestParam(name = "startDate", required = false, defaultValue = "") String start, @RequestParam(name = "endDate", required = false, defaultValue = "") String end, @RequestParam(name = "type", required = false, defaultValue = "") String type) throws UnparseableDateException {
        return disasterSummaryService.getDisasters(start, end, type);
    }
}
