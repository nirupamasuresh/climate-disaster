package com.fema.disaster.controllers;

import com.fema.disaster.entities.DisasterSummaryDTO;
import com.fema.disaster.exceptions.UnparseableDateException;
import com.fema.disaster.service.DisasterSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller to access fema summaries
 */
@RestController
@RequestMapping(path = "/api")
public class DisasterSummaryController {

    @Autowired
    DisasterSummaryService disasterSummaryService;

    /**
     * Fetches the fema summaries according to the params
     *
     * @param start filter by date greater than or equal to start
     * @param end   filter by date less than or equal to end
     * @param type  filter by distaster type
     * @return map of fema summary entity
     * @throws UnparseableDateException
     */
    @GetMapping(path = "/disaster")
    public Map<String, DisasterSummaryDTO> getDisasterSummaries(@RequestParam(name = "startDate", required = false, defaultValue = "") String start, @RequestParam(name = "endDate", required = false, defaultValue = "") String end, @RequestParam(name = "type", required = false, defaultValue = "") String type) throws UnparseableDateException {
        return disasterSummaryService.getDisasters(start, end, type);
    }
}
