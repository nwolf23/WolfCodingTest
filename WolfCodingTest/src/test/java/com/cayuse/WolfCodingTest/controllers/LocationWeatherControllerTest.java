package com.cayuse.WolfCodingTest.controllers;

import com.cayuse.WolfCodingTest.services.ApiRestTemplate;
import com.cayuse.WolfCodingTest.services.GoogleApiServiceI;
import com.cayuse.WolfCodingTest.services.OpenWeatherServiceI;
import com.google.maps.model.LatLng;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LocationWeatherControllerTest {

    @MockBean
    private GoogleApiServiceI googleApiServiceI;
    @MockBean
    private OpenWeatherServiceI openWeatherServiceI;
    @Autowired
    private ApiRestTemplate mockRestTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getMessage_returns_expected_formed_sentence() throws Exception {
        LatLng latLng = new LatLng(44.05594000000001,-120.995047);

        when(googleApiServiceI.getLatLng(anyString())).thenReturn(latLng);
        when(googleApiServiceI.getCity(anyString())).thenReturn("Bend");
        when(googleApiServiceI.getElevation(latLng)).thenReturn(4041L);
        when(googleApiServiceI.getTimeZone(latLng)).thenReturn("Pacific Standard Time");
        when(openWeatherServiceI.getWeather(latLng)).thenReturn(18L);
        mockMvc.perform(get("/getMessage?zipcode=97702")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("At the location Bend, the temperature is 18, the timezone is Pacific Standard Time, and the elevation is 4041.")));
    }

    @Test
    public void getInfo_returns_expected_json_structure() throws Exception {
        LatLng latLng = new LatLng(44.05594000000001,-120.995047);

        when(googleApiServiceI.getLatLng(anyString())).thenReturn(latLng);
        when(googleApiServiceI.getCity(anyString())).thenReturn("Bend");
        when(googleApiServiceI.getElevation(latLng)).thenReturn(4041L);
        when(googleApiServiceI.getTimeZone(latLng)).thenReturn("Pacific Standard Time");
        when(openWeatherServiceI.getWeather(latLng)).thenReturn(18L);
        mockMvc.perform(get("/getInfo?zipcode=97702")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bend")));
    }
}