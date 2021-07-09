package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.*;

public class GovernanceRuleRegexRuleModel
        implements java.io.Serializable {
    private List<GovernanceRuleRegexConditionModel> conditions;
    private int sampleRate;

    /** GETTER
     * conditions
     * @return the value
     */
    @JsonGetter("conditions")
    public List<GovernanceRuleRegexConditionModel> getConditions ( ) {
        return this.conditions;
    }

    /** SETTER
     * conditions
     * @param value the value to set
     */
    @JsonSetter("conditions")
    public void setConditions (List<GovernanceRuleRegexConditionModel> value) {
        this.conditions = value;
    }

    /** GETTER
     * sampleRate
     * @return the value
     */
    @JsonGetter("sample_rate")
    public int getSampleRate ( ) {
        return this.sampleRate;
    }

    /** SETTER
     * sampleRate
     * @param value the value to set
     */
    @JsonSetter("sample_rate")
    public void setSampleRate (int value) {
        this.sampleRate = value;
    }
}
