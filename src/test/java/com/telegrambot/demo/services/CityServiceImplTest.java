package com.telegrambot.demo.services;

import com.telegrambot.demo.models.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Sql(scripts = "/data.sql", config = @SqlConfig(encoding = "UTF-8"))
@SpringBootTest
public class CityServiceImplTest {

    @Autowired
    CityService cityService;

    private static final String cityName = "Брюссель";
    private static final String description = "Столица Бельгии";
    private static final int countExp = 42;
    private static final City testCity = new City("test", "test");

    @Test
    public void findCity() {
        City city = cityService.findCity(CityServiceImplTest.cityName);
        assertEquals(description, city.getDescription());
    }

    @Test
    public void deleteByName() {
        cityService.delete(cityName);
        City city = cityService.findCity(CityServiceImplTest.cityName);
        assertEquals(null, city);
    }

    @Test
    public void count() {
        long count = cityService.count();
        assertEquals(count, countExp);
    }

    @Test
    public void findAll() {
        List<City> all = cityService.findAll();
        assertEquals(all.size(), countExp);
    }

    @Test
    public void save() {
        cityService.save(testCity);
        City city = cityService.findCity(CityServiceImplTest.testCity.getName());
        assertEquals(testCity.getDescription(), city.getDescription());
    }

    @Test
    public void delete() {
    }
}