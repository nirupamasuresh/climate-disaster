package com.fema.disaster.service;

import com.fema.disaster.entities.DisasterIntensity;
import com.fema.disaster.entities.DisasterSummaryDTO;
import com.fema.disaster.entities.StateCount;
import com.fema.disaster.exceptions.UnparseableDateException;
import com.fema.disaster.repositories.DisasterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class DisasterSummaryServiceTest {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @TestConfiguration
    static class DisasterSummaryServiceContextConfiguration {

        @Bean
        public DisasterSummaryService employeeService() {
            return new DisasterSummaryService();
        }
    }

    @Autowired
    private DisasterSummaryService disasterSummaryService;
    @MockBean
    private DisasterRepository disasterRepository;

    @Before
    public void setUp() throws Exception {
        List<StateCount> stateCounts = new ArrayList<>();
        stateCounts.add(new StateCount("GA", 1L));
        Mockito.when(disasterRepository.find(FORMAT.parse("1953-01-01 00:00:00"), FORMAT.parse("1954-01-01 00:00:00")))
                .thenReturn(stateCounts);
        Mockito.when(disasterRepository.find(FORMAT.parse("0000-00-00 00:00:00"), FORMAT.parse(FORMAT.format(new Date()))))
                .thenReturn(stateCounts);
        Mockito.when(disasterRepository.findByType(FORMAT.parse("1953-01-01 00:00:00"), FORMAT.parse("1954-01-01 00:00:00"), "DR"))
                .thenReturn(stateCounts);
        Mockito.when(disasterRepository.findByType(FORMAT.parse("1953-01-01 00:00:00"), FORMAT.parse(FORMAT.format(new Date())), "DR"))
                .thenReturn(stateCounts);
        Mockito.when(disasterRepository.findByType(FORMAT.parse("0000-00-00 00:00:00"), FORMAT.parse(FORMAT.format(new Date())), "DR"))
                .thenReturn(stateCounts);
        Mockito.when(disasterRepository.findByType(FORMAT.parse("0000-00-00 00:00:00"), FORMAT.parse("1954-01-01 00:00:00"), "DR"))
                .thenReturn(stateCounts);
    }

    @Test
    public void findTest() throws Exception {
        Map<String, DisasterSummaryDTO> found = disasterSummaryService.getDisasters("1953-01-01 00:00:00", "1954-01-01 00:00:00", "DR");
        assertTrue(found.containsKey("GA"));
        assertEquals(found.get("GA").getCount(), 1L);
        assertEquals(found.get("GA").getFillKey(), DisasterIntensity.MEDIUM);
    }

    @Test
    public void findTestWithNoStartDate() throws Exception {
        Map<String, DisasterSummaryDTO> found = disasterSummaryService.getDisasters("", "1954-01-01 00:00:00", "DR");
        assertTrue(found.containsKey("GA"));
        assertEquals(found.get("GA").getCount(), 1L);
        assertEquals(found.get("GA").getFillKey(), DisasterIntensity.MEDIUM);
    }

    @Test
    public void findTestWithNoEndDate() throws Exception {
        Map<String, DisasterSummaryDTO> found = disasterSummaryService.getDisasters("1953-01-01 00:00:00", "", "DR");
        assertTrue(found.containsKey("GA"));
        assertEquals(found.get("GA").getCount(), 1L);
        assertEquals(found.get("GA").getFillKey(), DisasterIntensity.MEDIUM);
    }

    @Test
    public void findTestWithNoType() throws Exception {
        Map<String, DisasterSummaryDTO> found = disasterSummaryService.getDisasters("1953-01-01 00:00:00", "1954-01-01 00:00:00", "");
        assertTrue(found.containsKey("GA"));
        assertEquals(found.get("GA").getCount(), 1L);
        assertEquals(found.get("GA").getFillKey(), DisasterIntensity.MEDIUM);
    }

    @Test
    public void findTestWithAll() throws Exception {
        Map<String, DisasterSummaryDTO> found = disasterSummaryService.getDisasters("", "", "");
        assertTrue(found.containsKey("GA"));
        assertEquals(found.get("GA").getCount(), 1L);
        assertEquals(found.get("GA").getFillKey(), DisasterIntensity.MEDIUM);
    }

    @Test(expected = UnparseableDateException.class)
    public void findTestWithInvalidDate() {
        Map<String, DisasterSummaryDTO> found = disasterSummaryService.getDisasters("1987-99-99", "", "");
    }
}