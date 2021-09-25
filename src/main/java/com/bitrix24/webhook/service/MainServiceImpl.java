package com.bitrix24.webhook.service;

import com.bitrix24.webhook.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@NoArgsConstructor
public class MainServiceImpl implements MainService {

    @Override
    public OwnResponseEntity findAll() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        OrderEntityForRequest order = new OrderEntityForRequest("DESC");

        String[] array = new String[2];
        array[0] = "ApproveActivity";
        array[1] = "RpaApproveActivity";
        FilterEntityForRequest filter = new FilterEntityForRequest((long) 1, array, 0);

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

        assert body != null;
        return objectMapper.readValue(body, OwnResponseEntity.class);
    }

    public List<MainEntity> deserialization(OwnResponseEntity data) {
        List<MainEntity> list;
        list = data.getResult();
        return list;
    }

    public void changeStatusToAccept(String id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String [] neededId = id.split("&");
        String mainString;
        if (id.contains("Accept")) {
            mainString = "https://b24-pc5i7x.bitrix24.ua/rest/1/k4hubf7q73t9e21i/bizproc.task.complete?TASK_ID=" + neededId[0] + "&STATUS=1";
        }
        else {
            mainString = "https://b24-pc5i7x.bitrix24.ua/rest/1/k4hubf7q73t9e21i/bizproc.task.complete?TASK_ID=" + neededId[0] + "&STATUS=2";
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.postForEntity(mainString, entity, String.class);
    }
}
