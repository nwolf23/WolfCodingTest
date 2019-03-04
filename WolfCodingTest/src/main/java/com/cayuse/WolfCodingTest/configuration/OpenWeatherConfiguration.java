package com.cayuse.WolfCodingTest.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openweather")
public class OpenWeatherConfiguration {

    private String testapiKey;
    private String apipath;

    public OpenWeatherConfiguration() {}

    @Bean
    public String getTestapiKey() {
        return testapiKey;
    }

    @Bean
    public String getApipath(){ return apipath; }

    public void setTestapiKey(String apiKey) {
        this.testapiKey = apiKey;
    }

    public void setApipath(String apipath) {
        this.apipath = apipath;
    }
}
