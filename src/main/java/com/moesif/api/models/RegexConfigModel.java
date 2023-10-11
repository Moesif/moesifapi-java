package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RegexConfigModel implements java.io.Serializable {
    @JsonProperty("sample_rate")
    public int sampeleRate;
    @JsonProperty("conditions")
    public List<GovernanceRuleRegexConditionModel> conditions;
}
