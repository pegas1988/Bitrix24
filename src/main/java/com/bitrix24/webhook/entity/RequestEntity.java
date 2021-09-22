package com.bitrix24.webhook.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestEntity {

    @JsonProperty("select")
    private String[] result;
    @JsonProperty("filter")
    private FilterEntityForRequest filter;
    @JsonProperty("order")
    private OrderEntityForRequest order;

    @JsonCreator
    public RequestEntity(@JsonProperty("result") String[] result,
                         @JsonProperty("order") OrderEntityForRequest order,
                         @JsonProperty("filter") FilterEntityForRequest filter) {

        this.result = result;
        this.filter = filter;
        this.order = order;
    }
}
