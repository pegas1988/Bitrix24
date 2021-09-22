package com.bitrix24.webhook;

import com.bitrix24.webhook.entity.FilterEntityForRequest;
import com.bitrix24.webhook.entity.OrderEntityForRequest;
import com.bitrix24.webhook.entity.OwnResponseEntity;
import com.bitrix24.webhook.entity.RequestEntity;
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
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            OrderEntityForRequest order = new OrderEntityForRequest("DESC");

            String[] array = new String[2];
            array[0] = "ApproveActivity";
            array[1] = "RpaApproveActivity";
            FilterEntityForRequest filter = new FilterEntityForRequest((long) 5, array, 0);

            String[] propertiesArray = new String[10];
            propertiesArray[0] = "user_id";
            propertiesArray[1] = "MODULE_ID";
            propertiesArray[2] = "ID";
            propertiesArray[3] = "WORKFLOW_ID";
            propertiesArray[4] = "DOCUMENT_NAME";
            propertiesArray[5] = "DESCRIPTION";
            propertiesArray[6] = "NAME";
            propertiesArray[7] = "STATUS";
            propertiesArray[8] = "ACTIVITY";
            propertiesArray[9] = "PARAMETERS";

            RequestEntity requestEntity = new RequestEntity(propertiesArray, order, filter);

            HttpHeaders headers = new HttpHeaders();

            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentLength(512);

            ResponseEntity<String> quote = restTemplate.postForEntity(
                    "https://b24-pc5i7x.bitrix24.ua/rest/1/k4hubf7q73t9e21i/bizproc.task.list", requestEntity, String.class, headers);
            String body = quote.getBody();
            ObjectMapper objectMapper = new ObjectMapper();

            log.info(objectMapper.readValue(body, OwnResponseEntity.class).toString());
        };
    }
}
