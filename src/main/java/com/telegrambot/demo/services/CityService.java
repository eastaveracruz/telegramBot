package com.telegrambot.demo.services;

import com.telegrambot.demo.models.City;

import java.util.List;

public interface CityService {
    List<City> findAll();
    <S extends City> S save(S s);
    void delete(long id);
    void delete(City city);
    long count();
    City findCity(String city);
    void delete(String name);
}
