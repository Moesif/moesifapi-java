package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.HashMap;
import java.util.Map;

public class EntityRuleModel {
    private String rules;
    private Map<String, String> values = new HashMap<String, String>();

    @JsonGetter("rules")
    public String getRules() {
        return rules;
    }

    @JsonSetter("rules")
    public void setRules(String rules) {
        this.rules = rules;
    }

    @JsonGetter("values")
    public Map<String, String> getValues() {
        return values;
    }

    @JsonSetter("values")
    public void setValues(Map<String, String> values) {
        this.values = values;
    }
}
