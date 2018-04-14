package com.checkr.climate.controllers;

import com.checkr.climate.entities.Disaster;
import com.checkr.climate.entities.IntensitySub;
import com.checkr.climate.entities.StateCount;
import com.checkr.climate.exceptions.UnparseableDateException;
import com.checkr.climate.repositories.DisasterRepository;
import com.checkr.climate.service.ClimateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/api")
public class ClimateController {

    @Autowired
    ClimateService climateService;

    @GetMapping(path = "/disaster")
    public Map<String, IntensitySub> showWelcomePage(@RequestParam(name = "startDate", required = false, defaultValue = "") String start, @RequestParam(name = "endDate", required = false, defaultValue = "") String end, @RequestParam(name = "type", required = false, defaultValue = "") String type) throws UnparseableDateException{
        return climateService.getDisasters(start,end,type);
    }
}
