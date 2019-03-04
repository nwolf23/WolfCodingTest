package com.cayuse.WolfCodingTest.services;

import com.cayuse.WolfCodingTest.configuration.OpenWeatherConfiguration;
import com.cayuse.WolfCodingTest.models.WeatherTemp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherServiceTests {

    @Mock
    private OpenWeatherConfiguration mockOpenWeatherConfiguration;

    @Mock
    private ApiRestTemplate mockRestTemplate;

    @InjectMocks
    private OpenWeatherService openWeatherService;

    @Before
    public void setup() {
        mockOpenWeatherConfiguration = mock(OpenWeatherConfiguration.class);
        mockRestTemplate = mock(ApiRestTemplate.class);
        openWeatherService = new OpenWeatherService(mockOpenWeatherConfiguration, mockRestTemplate);
    }

    @Test
    public void calculateTempFromKelvin_expected_result() {
        Long result = openWeatherService.calculateTempFromKelvin(265.2);
        Assert.assertEquals(Long.valueOf(18), result);
    }

    @Test
    public void getTempObjFromJson_populates_object_correctly() throws IOException {
        WeatherTemp result = openWeatherService.getTempObjFromJson(TESTJSON);
        Assert.assertEquals(Double.valueOf(265.2), result.getTemp());
    }

    private String TESTJSON = "{\n" +
            "    \"coord\": {\n" +
            "        \"lon\": -121.22,\n" +
            "        \"lat\": 43.97\n" +
            "    },\n" +
            "    \"weather\": [\n" +
            "        {\n" +
            "            \"id\": 701,\n" +
            "            \"main\": \"Mist\",\n" +
            "            \"description\": \"mist\",\n" +
            "            \"icon\": \"50n\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"base\": \"stations\",\n" +
            "    \"main\": {\n" +
            "        \"temp\": 265.2,\n" +
            "        \"pressure\": 1020,\n" +
            "        \"humidity\": 85,\n" +
            "        \"temp_min\": 263.15,\n" +
            "        \"temp_max\": 267.15\n" +
            "    },\n" +
            "    \"visibility\": 4023,\n" +
            "    \"wind\": {\n" +
            "        \"speed\": 4.6,\n" +
            "        \"deg\": 340\n" +
            "    },\n" +
            "    \"clouds\": {\n" +
            "        \"all\": 90\n" +
            "    },\n" +
            "    \"dt\": 1551588591,\n" +
            "    \"sys\": {\n" +
            "        \"type\": 1,\n" +
            "        \"id\": 5643,\n" +
            "        \"message\": 0.0049,\n" +
            "        \"country\": \"US\",\n" +
            "        \"sunrise\": 1551623889,\n" +
            "        \"sunset\": 1551664561\n" +
            "    },\n" +
            "    \"id\": 5722958,\n" +
            "    \"name\": \"Deschutes County\",\n" +
            "    \"cod\": 200\n" +
            "}";
}