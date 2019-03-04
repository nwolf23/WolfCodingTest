package com.cayuse.WolfCodingTest.services;

import com.cayuse.WolfCodingTest.configuration.OpenWeatherConfiguration;
import com.cayuse.WolfCodingTest.models.WeatherTemp;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

@Service
public class OpenWeatherService implements OpenWeatherServiceI {

    private OpenWeatherConfiguration openWeatherConfiguration;

    private ApiRestTemplate restTemplate;

    @Autowired
    public OpenWeatherService(OpenWeatherConfiguration config, ApiRestTemplate restTemplate) {
        openWeatherConfiguration = config;
        this.restTemplate = restTemplate;
    }


    @Override
    public Long getWeather(LatLng latLng) {
        Long temp = null;
        try {
            String weatherRequest = openWeatherConfiguration.getApipath() + "?lat=" + latLng.lat + "&lon="
                    + latLng.lng + "&APPID=" + openWeatherConfiguration.getTestapiKey();
            String result = restTemplate.ApiRestTemplate().getForObject(new URI(weatherRequest), String.class);
            WeatherTemp weatherTemp = getTempObjFromJson(result);
            temp = calculateTempFromKelvin(weatherTemp.getTemp());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public WeatherTemp getTempObjFromJson(String tempJson) {
        WeatherTemp weatherTemp = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node =  mapper.readTree(tempJson);
            weatherTemp = mapper.readValue(node.get("main").toString(), WeatherTemp.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherTemp;
    }

    public Long calculateTempFromKelvin(Double kelvinTemp) {
        Double result =  ((9D/5)*(kelvinTemp - 273)) + 32;
        return Math.round(result);
    }
}
