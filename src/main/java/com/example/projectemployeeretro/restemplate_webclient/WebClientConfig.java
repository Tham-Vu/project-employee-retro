package com.example.projectemployeeretro.restemplate_webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient getWebClient(WebClient.Builder webClientBuilder){
        return webClientBuilder.baseUrl("http://hrm-api.nccsoft.vn/api/services/app/CheckIn/GetUserForCheckIn")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
