package com.moesif.api.models;

public class GovernanceRulesVariableBuilder {

    //the instance to build
    private GovernanceRulesVariableModel GovernanceRulesVariableModel;

    /**
     * Default constructor to initialize the instance
     */
    public GovernanceRulesVariableBuilder() {
        GovernanceRulesVariableModel = new GovernanceRulesVariableModel();
    }

    /**
     * the name
     * @param name the field to set
     * @return itself
     */
    public GovernanceRulesVariableBuilder name(String name) {
        GovernanceRulesVariableModel.setName(name);
        return this;
    }

    /**
     * the path
     * @param path the field to set
     * @return itself
     */
    public GovernanceRulesVariableBuilder path(String path) {
        GovernanceRulesVariableModel.setPath(path);
        return this;
    }

    /**
     * Build the instance with the given values
     * @return The built GovernanceRulesVariableModel
     */
    public GovernanceRulesVariableModel build() {
        return GovernanceRulesVariableModel;
    }
}
