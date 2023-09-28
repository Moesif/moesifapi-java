package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RegexConfigModel implements java.io.Serializable {
    @JsonProperty("sample_rate")
    private int sampeleRate;
    @JsonProperty("conditions")
    private List<GovernanceRuleRegexConditionModel> conditions;
}
