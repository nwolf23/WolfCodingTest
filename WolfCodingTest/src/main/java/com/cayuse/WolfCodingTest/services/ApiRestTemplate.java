package com.cayuse.WolfCodingTest.services;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiRestTemplate {

    public RestTemplate ApiRestTemplate(){
        return new RestTemplate();
    }
}
