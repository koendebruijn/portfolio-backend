package com.koendebruijn.portfolio_backend.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Project {

    @JsonProperty("id")
    @Id
    private long id;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("html_url")
    private String html_url;

    @JsonProperty("homepage")
    private String homepage;

    @Column(columnDefinition="TEXT")
    private String readMe;

}
