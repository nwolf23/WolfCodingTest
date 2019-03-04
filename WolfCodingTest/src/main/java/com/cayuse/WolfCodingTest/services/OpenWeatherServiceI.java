package com.cayuse.WolfCodingTest.services;

import com.google.maps.model.LatLng;

public interface OpenWeatherServiceI {

    Long getWeather(LatLng latLng);
}
