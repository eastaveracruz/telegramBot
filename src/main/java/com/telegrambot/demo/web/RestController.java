package com.telegrambot.demo.web;

import com.telegrambot.demo.models.City;
import com.telegrambot.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = RestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestController {

    public static final String REST_URL = "/rest";

    @Autowired
    CityService cityService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<City> getAll() {
        return cityService.findAll();
    }

    @GetMapping("/{name}")
    public City get(@PathVariable String name) {
        return cityService.findCity(name);
    }

    @DeleteMapping("/name/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String name) {
        cityService.delete(name);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        cityService.delete(id);
    }

    @DeleteMapping(value = "/object", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody City city) {
        cityService.delete(city);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody City city) {
        cityService.save(city);
    }

}
