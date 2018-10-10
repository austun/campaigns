package com.assesment.campaigns.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Creatives {
    @JsonProperty("header")
    public String header;

    @JsonProperty("header_1")
    public String header1;

    @JsonProperty("header_2")
    public String header2;

    @JsonProperty("description")
    public String description;

    @JsonProperty("url")
    public String url;

    @JsonProperty("image")
    public String image;

}
