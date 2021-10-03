package com.bitrix24.webhook.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainEntity {

    @JsonProperty("DOCUMENT_NAME")
    private String documentName;
    @JsonProperty("DESCRIPTION")
    private String description;
    @JsonProperty("ACTIVITY")
    private String activity;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("ID")
    private Long id;

    @JsonCreator
    MainEntity(@JsonProperty("DOCUMENT_NAME") String documentName,
               @JsonProperty("DESCRIPTION") String description,
               @JsonProperty("ACTIVITY") String activity,
               @JsonProperty("NAME") String name,
               @JsonProperty("ID") Long id) {
        this.documentName = documentName;
        this.description = description;
        this.activity = activity;
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "MainEntity{" +
                "description='" + description + '\'' +
                ", activity='" + activity + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
