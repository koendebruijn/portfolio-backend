package com.koendebruijn.portfolio_backend.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table
public class Project {

    public long getId() {
        return id;
    }

    @JsonProperty("id")
    @Id
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("html_url")
    private String html_url;

    @JsonProperty("homepage")
    private String homepage;

    public Project(long id, String name, String description, String html_url, String homepage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.html_url = html_url;
        this.homepage = homepage;
    }


    public Project() {
    }
}
