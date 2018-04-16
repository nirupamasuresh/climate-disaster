package com.fema.disaster.controllers;

import com.fema.disaster.entities.DisasterIntensity;
import com.fema.disaster.entities.DisasterSummaryDTO;
import com.fema.disaster.exceptions.UnparseableDateException;
import com.fema.disaster.service.DisasterSummaryService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DisasterSummaryController.class)
public class DisasterSummaryControllerTest {

    private static Map<String, DisasterSummaryDTO> disaster = new HashMap<>();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DisasterSummaryService service;

    @BeforeClass
    public static void setUpBefore() throws Exception {
        disaster.put("GA", new DisasterSummaryDTO(DisasterIntensity.MEDIUM, 1));
    }

    @Test
    public void getAllTest() throws Exception {
        given(service.getDisasters("", "", "")).willReturn(disaster);
        mvc.perform(get("/api/disaster").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$['GA']['fillKey']", is("MEDIUM"))).andExpect(jsonPath("$['GA']['count']", is(1)));
    }

    @Test
    public void getAllWithNoStartDate() throws Exception {
        given(service.getDisasters("", "1954-01-01", "DR")).willReturn(disaster);
        mvc.perform(get("/api/disaster?endDate=1954-01-01&&type=DR").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$['GA']['fillKey']", is("MEDIUM"))).andExpect(jsonPath("$['GA']['count']", is(1)));
    }

    @Test
    public void getAllWithNoEndDate() throws Exception {
        given(service.getDisasters("2015-01-01", "", "DR")).willReturn(disaster);
        mvc.perform(get("/api/disaster?startDate=2015-01-01&&type=DR").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$['GA']['fillKey']", is("MEDIUM"))).andExpect(jsonPath("$['GA']['count']", is(1)));
    }

    @Test
    public void getAllWithNoType() throws Exception {
        given(service.getDisasters("2015-01-01", "2015-01-01", "")).willReturn(disaster);
        mvc.perform(get("/api/disaster?startDate=2015-01-01&&endDate=2015-01-01").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$['GA']['fillKey']", is("MEDIUM"))).andExpect(jsonPath("$['GA']['count']", is(1)));
    }

    @Test
    public void getAllInvalidDateTest() throws Exception {
        given(service.getDisasters("2011", "", "")).willThrow(UnparseableDateException.class);
        mvc.perform(get("/api/disaster?startDate=2011").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}