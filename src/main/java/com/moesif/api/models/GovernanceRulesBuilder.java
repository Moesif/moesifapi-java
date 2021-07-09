package com.moesif.api.models;

import java.util.*;

public class GovernanceRulesBuilder {
    //the instance to build
    private GovernanceRulesModel GovernanceRulesModel;

    /**
     * Default constructor to initialize the instance
     */
    public GovernanceRulesBuilder() {
        GovernanceRulesModel = new GovernanceRulesModel();
    }

    /**
     * the Id
     * @param Id the field to set
     * @return itself
     */
    public GovernanceRulesBuilder id(String Id) {
        GovernanceRulesModel.setId(Id);
        return this;
    }

    /**
     * the createdAt
     * @param createdAt the field to set
     * @return itself
     */
    public GovernanceRulesBuilder createdAt(String createdAt) {
        GovernanceRulesModel.setcreatedAt(createdAt);
        return this;
    }

    /**
     * the orgId
     * @param orgId the field to set
     * @return itself
     */
    public GovernanceRulesBuilder orgId(String orgId) {
        GovernanceRulesModel.setOrgId(orgId);
        return this;
    }

    /**
     * the appId
     * @param appId the field to set
     * @return itself
     */
    public GovernanceRulesBuilder appId(String appId) {
        GovernanceRulesModel.setAppId(appId);
        return this;
    }

    /**
     * the name
     * @param name the field to set
     * @return itself
     */
    public GovernanceRulesBuilder name(String name) {
        GovernanceRulesModel.setName(name);
        return this;
    }

    /**
     * the block
     * @param block the field to set
     * @return itself
     */
    public GovernanceRulesBuilder block(boolean block) {
        GovernanceRulesModel.setBlock(block);
        return this;
    }

    /**
     * the type
     * @param type the field to set
     * @return itself
     */
    public GovernanceRulesBuilder type(String type) {
        GovernanceRulesModel.setType(type);
        return this;
    }

    /**
     * the variables
     * @param variables the field to set
     * @return itself
     */
    public GovernanceRulesBuilder variables(List<GovernanceRulesVariableModel> variables) {
        GovernanceRulesModel.setVariables(variables);
        return this;
    }

    /**
     * the regexConfig
     * @param regexConfig the field to set
     * @return itself
     */
    public GovernanceRulesBuilder regexConfig(List<GovernanceRuleRegexRuleModel> regexConfig) {
        GovernanceRulesModel.setRegexConfig(regexConfig);
        return this;
    }

    /**
     * the response
     * @param response the field to set
     * @return itself
     */
    public GovernanceRulesBuilder response(GovernanceRulesResponseModel response) {
        GovernanceRulesModel.setResponse(response);
        return this;
    }

    /**
     * Build the instance with the given values
     * @return The built GovernanceRulesModel
     */
    public GovernanceRulesModel build() {
        return GovernanceRulesModel;
    }
}
