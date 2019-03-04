package com.cayuse.WolfCodingTest.controllers;

import com.cayuse.WolfCodingTest.models.WeatherLocation;
import com.cayuse.WolfCodingTest.services.GoogleApiServiceI;
import com.cayuse.WolfCodingTest.services.OpenWeatherServiceI;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationWeatherController {

    private GoogleApiServiceI googleApiService;
    private OpenWeatherServiceI openWeatherService;

    @Autowired
    public LocationWeatherController(GoogleApiServiceI googleApiService, OpenWeatherServiceI openWeatherService){
        this.googleApiService = googleApiService;
        this.openWeatherService = openWeatherService;
    }

    @GetMapping("/getInfo")
    public ResponseEntity<?> getInfo(@RequestParam(name="zipcode") String zipCode) {
        LatLng latLng = googleApiService.getLatLng(zipCode);
        WeatherLocation weatherLocation = new WeatherLocation();
        weatherLocation.setCity(googleApiService.getCity(zipCode));
        weatherLocation.setCurrentTemp(openWeatherService.getWeather(latLng));
        weatherLocation.setElevation(googleApiService.getElevation(latLng));
        weatherLocation.setTimeZone(googleApiService.getTimeZone(latLng));
        return new ResponseEntity<>(weatherLocation, HttpStatus.OK);
    }

    @GetMapping("/getMessage")
    public String getMessage(@RequestParam(name="zipcode") String zipCode) {
        LatLng latLng = googleApiService.getLatLng(zipCode);
        WeatherLocation weatherLocation = new WeatherLocation();
        weatherLocation.setCity(googleApiService.getCity(zipCode));
        weatherLocation.setCurrentTemp(openWeatherService.getWeather(latLng));
        weatherLocation.setElevation(googleApiService.getElevation(latLng));
        weatherLocation.setTimeZone(googleApiService.getTimeZone(latLng));
        return "At the location " + weatherLocation.getCity() + ", the temperature is " + weatherLocation.getCurrentTemp() + ", the timezone is " + weatherLocation.getTimeZone() + ", and the elevation is " + weatherLocation.getElevation() + ".";
    }
}
