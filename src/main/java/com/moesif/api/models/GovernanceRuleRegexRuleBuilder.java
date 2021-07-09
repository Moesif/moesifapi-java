package com.moesif.api.models;

import java.util.*;

public class GovernanceRuleRegexRuleBuilder {
    //the instance to build
    private GovernanceRuleRegexRuleModel GovernanceRuleRegexRuleModel;

    /**
     * Default constructor to initialize the instance
     */
    public GovernanceRuleRegexRuleBuilder() {
        GovernanceRuleRegexRuleModel = new GovernanceRuleRegexRuleModel();
    }

    /**
     * the conditions
     * @param conditions the field to set
     * @return itself
     */
    public GovernanceRuleRegexRuleBuilder conditions(List<GovernanceRuleRegexConditionModel> conditions) {
        GovernanceRuleRegexRuleModel.setConditions(conditions);
        return this;
    }

    /**
     * the sampleRate
     * @param sampleRate the field to set
     * @return itself
     */
    public GovernanceRuleRegexRuleBuilder sampleRate(int sampleRate) {
        GovernanceRuleRegexRuleModel.setSampleRate(sampleRate);
        return this;
    }

    /**
     * Build the instance with the given values
     * @return The built GovernanceRuleRegexRuleModel
     */
    public GovernanceRuleRegexRuleModel build() {
        return GovernanceRuleRegexRuleModel;
    }
}
