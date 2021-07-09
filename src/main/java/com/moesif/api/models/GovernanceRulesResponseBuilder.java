package com.moesif.api.models;

import java.util.Map;

public class GovernanceRulesResponseBuilder {

    //the instance to build
    private GovernanceRulesResponseModel GovernanceRulesResponseModel;

    /**
     * Default constructor to initialize the instance
     */
    public GovernanceRulesResponseBuilder() { GovernanceRulesResponseModel = new GovernanceRulesResponseModel(); }

    /**
     * Response status
     * @param status the field to set
     * @return itself
     */
    public GovernanceRulesResponseBuilder status(int status) {
        GovernanceRulesResponseModel.setStatus(status);
        return this;
    }

    /**
     * Response headers
     * @param headers the field to set
     * @return itself
     */
    public GovernanceRulesResponseBuilder headers(Map<String, String> headers) {
        GovernanceRulesResponseModel.setHeaders(headers);
        return this;
    }

    /**
     * Response body
     * @param body the field to set
     * @return itself
     */
    public GovernanceRulesResponseBuilder body(Object body) {
        GovernanceRulesResponseModel.setBody(body);
        return this;
    }

    /**
     * Build the instance with the given values
     * @return The built GovernanceRulesResponseModel
     */
    public GovernanceRulesResponseModel build() {
        return GovernanceRulesResponseModel;
    }
}
