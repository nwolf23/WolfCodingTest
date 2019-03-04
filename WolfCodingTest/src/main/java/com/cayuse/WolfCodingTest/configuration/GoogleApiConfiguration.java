package com.cayuse.WolfCodingTest.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix ="google")
public class GoogleApiConfiguration {

    private String apiKey;

    private String geocodePath;

    public GoogleApiConfiguration(){}

    @Bean
    public String getApiKey() {
        return apiKey;
    }

    @Bean
    public String getGeocodePath() {
        return geocodePath;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setGeocodePath(String geocodePath) {
        this.geocodePath = geocodePath;
    }
}
