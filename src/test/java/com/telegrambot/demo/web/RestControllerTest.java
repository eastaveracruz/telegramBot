package com.telegrambot.demo.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telegrambot.demo.models.City;
import com.telegrambot.demo.services.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.PostConstruct;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(scripts = "/data.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();
    public static final String REST_URL = RestController.REST_URL;

    private static final String cityName = "Брюссель";

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    private MockMvc mockMvc;

    @Autowired
    private CityService cityService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() throws JsonProcessingException {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + "/" + cityName))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + "/id/" + 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDelete1() throws Exception {
        mockMvc.perform(delete(REST_URL + "/name/" + cityName))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDelete2() throws Exception {
        mockMvc.perform(delete(REST_URL + "/object")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonCity()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonCity()))
                .andExpect(status().isNoContent());
    }

    private String jsonCity() throws JsonProcessingException {
        City city = cityService.findCity(cityName);
        ObjectMapper mapper = new ObjectMapper();
        String string = mapper.writeValueAsString(city);
        return string;
    }
}