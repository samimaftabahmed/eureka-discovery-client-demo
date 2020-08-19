package com.sam.eurekadiscoveryclientdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/sam")
@RestController
public class MyController {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${spring.application.name}")
    private String applicationName;
    @Autowired
    private ServletWebServerApplicationContext server;

    @GetMapping("/service-instances")
    public String serviceInstancesByApplicationName() {

        return applicationName + ":" + server.getWebServer().getPort();
    }

    @GetMapping("/get-details")
    public String getOtherInstances() {

        // same project copied but application name changed to mas-client
        String url = "http://mas-client/mas/service-instances/";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        return responseEntity.getBody();
    }
}
