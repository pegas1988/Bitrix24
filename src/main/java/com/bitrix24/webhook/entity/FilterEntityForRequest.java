package com.bitrix24.webhook.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterEntityForRequest {

    @JsonProperty("USER_ID")
    Long userId;
    @JsonProperty("ACTIVITY")
    String[] activity;
    @JsonProperty("STATUS")
    long status;

    @JsonCreator
    public FilterEntityForRequest(@JsonProperty("USER_ID") Long userId,
                                  @JsonProperty("ACTIVITY") String[] activity,
                                  @JsonProperty("STATUS") long status) {
        this.userId = userId;
        this.activity = activity;
        this.status = status;
    }
}
