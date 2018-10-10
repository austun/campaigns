package com.assesment.campaigns.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class TargetAudiance {
    @JsonProperty("languages")
    private List<String> languages;

    @JsonProperty("genders")
    private List<String> genders;

    @JsonProperty("age_range")
    private List<Integer> ageRange;

    @JsonProperty("locations")
    private List<String> locations;

    @JsonProperty("KeyWords")
    private List<String> KeyWords;

    @JsonProperty("interests")
    private List<String> interests;
}
