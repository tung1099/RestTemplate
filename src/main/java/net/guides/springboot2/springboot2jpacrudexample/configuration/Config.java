package net.guides.springboot2.springboot2jpacrudexample.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory("http://localhost:8080/api/v1/employees");
        restTemplate.setUriTemplateHandler(defaultUriBuilderFactory);
        return restTemplate;
    }
}
