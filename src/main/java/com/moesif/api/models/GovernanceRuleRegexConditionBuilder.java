package com.moesif.api.models;

public class GovernanceRuleRegexConditionBuilder {
    //the instance to build
    private GovernanceRuleRegexConditionModel GovernanceRuleRegexConditionModel;

    /**
     * Default constructor to initialize the instance
     */
    public GovernanceRuleRegexConditionBuilder() {
        GovernanceRuleRegexConditionModel = new GovernanceRuleRegexConditionModel();
    }

    /**
     * the path
     * @param path the field to set
     * @return itself
     */
    public GovernanceRuleRegexConditionBuilder path(String path) {
        GovernanceRuleRegexConditionModel.setPath(path);
        return this;
    }

    /**
     * the value
     * @param value the field to set
     * @return itself
     */
    public GovernanceRuleRegexConditionBuilder value(String value) {
        GovernanceRuleRegexConditionModel.setValue(value);
        return this;
    }


    /**
     * Build the instance with the given values
     * @return The built GovernanceRuleRegexConditionModel
     */
    public GovernanceRuleRegexConditionModel build() {
        return GovernanceRuleRegexConditionModel;
    }
}
