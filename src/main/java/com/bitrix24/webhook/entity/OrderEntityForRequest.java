package com.bitrix24.webhook.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEntityForRequest {

    @JsonProperty("ID")
    private String id;

    @JsonCreator
    public OrderEntityForRequest(@JsonProperty("ID") String id) {
        this.id = id;
    }
}
