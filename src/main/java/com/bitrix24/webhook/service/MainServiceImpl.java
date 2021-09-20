package com.bitrix24.webhook.service;

import com.bitrix24.webhook.entity.OwnResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@NoArgsConstructor
public class MainServiceImpl implements MainService {

    public String theMethod() {
        return "{" +
                "    \"select\": [" +
                "         \"user_id\"," +
                "         \"MODULE_ID\"," +
                "         \"ID\",\n" +
                "         \"WORKFLOW_ID\"," +
                "         \"DOCUMENT_NAME\"," +
                "         \"DESCRIPTION\"," +
                "         \"NAME\"," +
                "         \"STATUS\"," +
                "         \"ACTIVITY\"," +
                "         \"PARAMETERS\"" +
                "      ]," +
                "      \"order\": {" +
                "          \"ID\":\"DESC\"" +
                "          }," +
                "      \"filter\": " +
                "          { " +
                "          \"USER_ID\" : 5," +
                "          \"ACTIVITY\" : [\"ApproveActivity\",\"RpaApproveActivity\"]," +
                "          \"STATUS\" : 0" +
                "          }" +
                "}";
    }

    @Override
    public OwnResponseEntity findAll() {
        try {
            return creator();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new OwnResponseEntity();
    }

    private OwnResponseEntity creator() throws JsonProcessingException {
        String tempJson = "{\n" +
                "    \"select\": [\n" +
                "         \"user_id\",\n" +
                "         \"MODULE_ID\",\n" +
                "         \"ID\",\n" +
                "         \"WORKFLOW_ID\",\n" +
                "         \"DOCUMENT_NAME\",\n" +
                "         \"DESCRIPTION\",\n" +
                "         \"NAME\",\n" +
                "         \"STATUS\",\n" +
                "         \"ACTIVITY\",\n" +
                "         \"PARAMETERS\"\n" +
                "      ],\n" +
                "      \"order\": {\n" +
                "          \"ID\":\"DESC\"\n" +
                "          },\n" +
                "      \"filter\": \n" +
                "          { \n" +
                "          \"USER_ID\" : 5,\n" +
                "          \"ACTIVITY\" : [\"ApproveActivity\",\"RpaApproveActivity\"],\n" +
                "          \"STATUS\" : 0\n" +
                "          }\n" +
                "}";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://b24-pc5i7x.bitrix24.ua/rest/1/k4hubf7q73t9e21i/bizproc.task.list", tempJson, String.class);

        String body = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();

        assert body != null;
        return objectMapper.readValue(body, OwnResponseEntity.class);
    }
}
