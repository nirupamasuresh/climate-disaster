package com.fema.disaster.repositories;

import com.fema.disaster.entities.Disaster;
import com.fema.disaster.entities.DisasterSummaryDTO;
import com.fema.disaster.entities.StateCount;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DisasterRepositoryTest {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    DisasterRepository disasterRepository;

    @Before
    public void setUp() throws Exception{
        Disaster dr = new Disaster(1,0,1,1,1,"GA",FORMAT.parse("1953-05-02 00:00:00"),"1953","DR","Tornado","TORNADO",FORMAT.parse("1953-05-02 00:00:00"),FORMAT.parse("1953-05-02 00:00:00"),FORMAT.parse("1954-06-01 00:00:00"),null,null,"e6f77c3a97c63d478bf14c9a58f60a0d",FORMAT.parse("2018-02-09 14:38:26"));
        entityManager.persist(dr);
        dr = new Disaster(1,0,1,1,1,"CA",FORMAT.parse("1953-05-02 00:00:00"),"1953","DR","Tornado","TORNADO",FORMAT.parse("1953-05-02 00:00:00"),null,FORMAT.parse("1954-06-01 00:00:00"),null,null,"e6f77c3a97c63d478bf14c9a58f60a0d",FORMAT.parse("2018-02-09 14:38:26"));
        entityManager.persist(dr);
    }

    @Test
    public void findByTypeTest() throws Exception{
        List<StateCount> found = disasterRepository.findByType(FORMAT.parse("1953-01-01 00:00:00"), FORMAT.parse("1954-01-01 00:00:00"), "DR");
        assertEquals(found.get(0).getState(), "GA");
        assertEquals((long)found.get(0).getCount(), 1L);
    }

    @Test
    public void findTest() throws Exception {
        List<StateCount> found = disasterRepository.find(FORMAT.parse("1953-01-01 00:00:00"), FORMAT.parse("1954-01-01 00:00:00"));
        assertEquals(found.get(0).getState(), "GA");
        assertEquals((long)found.get(0).getCount(), 1L);
    }

    @Test
    public void findTestWithCurrentEndDate() throws Exception {
        List<StateCount> found = disasterRepository.find(FORMAT.parse("1953-01-01 00:00:00"), FORMAT.parse(FORMAT.format(new Date())));
        assertEquals(found.get(0).getState(), "GA");
        assertEquals((long)found.get(0).getCount(), 1L);
    }

    @Test
    public void findTestWithZeroStartDate() throws Exception {
        List<StateCount> found = disasterRepository.find(FORMAT.parse("0000-00-00 00:00:00"), FORMAT.parse("1954-01-01 00:00:00"));
        assertEquals(found.get(0).getState(), "GA");
        assertEquals((long)found.get(0).getCount(), 1L);
    }

    @Test
    public void findTestWithCurrentEndDateWithType() throws Exception {
        List<StateCount> found = disasterRepository.findByType(FORMAT.parse("1953-01-01 00:00:00"), FORMAT.parse(FORMAT.format(new Date())),"DR");
        assertEquals(found.get(0).getState(), "GA");
        assertEquals((long)found.get(0).getCount(), 1L);
    }

    @Test
    public void findTestWithZeroStartDateWithType() throws Exception {
        List<StateCount> found = disasterRepository.findByType(FORMAT.parse("0000-00-00 00:00:00"), FORMAT.parse("1954-01-01 00:00:00"),"DR");
        assertEquals(found.get(0).getState(), "GA");
        assertEquals((long)found.get(0).getCount(), 1L);
    }

    @Test
    public void findTestTypeDoesNotExist() throws Exception {
        List<StateCount> found = disasterRepository.findByType(FORMAT.parse("0000-00-00 00:00:00"), FORMAT.parse("1954-01-01 00:00:00"),"FS");
        assertTrue(found.isEmpty());
    }

    @After
    public void tearDown() {
        entityManager.flush();
    }
}