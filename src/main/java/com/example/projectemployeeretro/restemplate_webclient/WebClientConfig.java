package com.example.projectemployeeretro.restemplate_webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${web-client.url}")
    String url;
//    String url = "http://localhost:8085/employees";
    @Bean
    public WebClient getWebClient(WebClient.Builder webClientBuilder){
        System.out.println(url);
        return webClientBuilder.baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
