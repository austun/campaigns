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
public class Insights {
    @JsonProperty("impressions")
    public Integer impressions;

    @JsonProperty("clicks")
    public Integer clicks;

    @JsonProperty("website_visits")
    public Integer websiteVisits;

    @JsonProperty("nanos_score")
    public Double nanosScore;

    @JsonProperty("cost_per_click")
    public Integer costPerClick;

    @JsonProperty("click_through_rate")
    public Integer clickThroughRate;

    @JsonProperty("advanced_kpi_1")
    public Integer advancedKpi1;

    @JsonProperty("advanced_kpi_2")
    public Integer advancedKpi2;
}
