package com.telegrambot.demo.services;

import com.telegrambot.demo.models.City;
import com.telegrambot.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private CityServiceImpl() {
    }

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void delete(String name) {
        cityRepository.deleteByNameIgnoreCase(name);
    }

    @Override
    public void delete(long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public void delete(City city) {
        cityRepository.delete(city);
    }

    @Override
    public long count() {
        return cityRepository.count();
    }

    @Override
    public City findCity(String city) {
        Optional<City> optionalCity = cityRepository.findCityByNameIgnoreCase(city);
        return optionalCity.isPresent() ? optionalCity.get() : null;
    }

}
