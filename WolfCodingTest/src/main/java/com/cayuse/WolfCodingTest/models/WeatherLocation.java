package com.cayuse.WolfCodingTest.models;

import lombok.Data;

@Data
public class WeatherLocation {

    private String city;
    private Long currentTemp;
    private Long elevation;
    private String timeZone;
}
