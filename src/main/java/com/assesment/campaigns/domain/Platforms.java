package com.assesment.campaigns.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class Platforms {
    @JsonProperty("facebook")
    Facebook facebook;

    @JsonProperty("instagram")
    Instagram instagram;

    @JsonProperty("google")
    Google google;
}
