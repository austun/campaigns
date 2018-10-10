package com.assesment.campaigns.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
abstract class Platform {
    @JsonProperty("status")
    private String status;

    @JsonProperty("total_budget")
    private int totalBudget;

    @JsonProperty("remaining_budget")
    private int remainingBudget;

    @JsonProperty("start_date")
    private Long startDate;

    @JsonProperty("end_date")
    private Long endDate;

    @JsonProperty("target_audiance")
    TargetAudiance targetAudiance;

    @JsonProperty("creatives")
    Creatives creatives;

    @JsonProperty("insights")
    Insights insights;
}
