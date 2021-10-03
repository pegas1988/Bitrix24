package com.bitrix24.webhook.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterForOne {

    @JsonProperty("USER_ID")
    Long userId;
    @JsonProperty("ACTIVITY")
    String[] activity;
    @JsonProperty("STATUS")
    long status;
    @JsonProperty("ID")
    String id;

    @JsonCreator
    public FilterForOne(@JsonProperty("USER_ID") Long userId,
                        @JsonProperty("ID") String id,
                        @JsonProperty("ACTIVITY") String[] activity,
                        @JsonProperty("STATUS") long status) {
        this.userId = userId;
        this.activity = activity;
        this.status = status;
        this.id = id;
    }
}
