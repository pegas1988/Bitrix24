package com.bitrix24.webhook;

import com.bitrix24.webhook.entity.OwnResponseEntity;
import com.bitrix24.webhook.service.MainServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class WebhookApplication {
    private static final Logger log = LoggerFactory.getLogger(WebhookApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebhookApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        MainServiceImpl mainService = new MainServiceImpl();
        return args -> {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<String> quote = restTemplate.postForEntity(
                    "https://b24-pc5i7x.bitrix24.ua/rest/1/k4hubf7q73t9e21i/bizproc.task.list", mainService.theMethod(), String.class, headers);
            String body = quote.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            log.info(objectMapper.readValue(body, OwnResponseEntity.class).toString());
        };
    }
}
