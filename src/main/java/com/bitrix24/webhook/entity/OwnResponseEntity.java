package com.bitrix24.webhook.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnResponseEntity {
    @Override
    public String toString() {
        return "OwnResponseEntity{" +
                "result=" + result +
                '}';
    }

    @JsonProperty("result")
    List<MainEntity> result;

    @JsonCreator
    OwnResponseEntity(@JsonProperty("result") List<MainEntity> result) {
        this.result = result;
    }
}
