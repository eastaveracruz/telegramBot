package com.telegrambot.demo.repositories;

import com.telegrambot.demo.models.City;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    @Override
    List<City> findAll();

    @Transactional
    @Modifying
    @Override
    City save(City city);

    @Transactional
    @Modifying
    void deleteByNameIgnoreCase(String name);

    @Transactional
    @Modifying
    @Override
    void deleteById(Long aLong);

    @Transactional
    @Modifying
    void delete(City city);

    @Override
    long count();

    Optional<City> findCityByNameIgnoreCase(String city);

}
