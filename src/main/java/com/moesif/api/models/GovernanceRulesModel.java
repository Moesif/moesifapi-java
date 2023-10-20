package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.*;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GovernanceRulesModel
        implements java.io.Serializable {
    private String Id;
    private String createdAt;
    private String orgId;
    private String appId;
    private String name;
    private boolean block;

    @JsonGetter("applied_to_unidentified")
    public boolean isAppliedToUnidentified() {
        return appliedToUnidentified;
    }

    @JsonSetter("applied_to_unidentified")
    public void setAppliedToUnidentified(boolean appliedToUnidentified) {
        this.appliedToUnidentified = appliedToUnidentified;
    }

    private boolean appliedToUnidentified;

    @JsonGetter("applied_to")
    public String getAppliedTo() {
        return appliedTo == null ? "matching" : appliedTo;
    }

    @JsonSetter("applied_to")
    public void setAppliedTo(String appliedTo) {
        this.appliedTo = appliedTo;
    }

    private String type;

    private String appliedTo;
    private List<GovernanceRulesVariableModel> variables;
    private List<GovernanceRuleRegexRuleModel> regexConfig;
    private GovernanceRulesResponseModel response;

    /** GETTER
     * Id
     * @return the value
     */
    @JsonGetter("_id")
    public String getId ( ) {
        return this.Id;
    }

    /** SETTER
     * Id
     * @param value the value to set
     */
    @JsonSetter("_id")
    public void setId (String value) {
        this.Id = value;
    }

    /** GETTER
     * createdAt
     * @return the value
     */
    @JsonGetter("created_at")
    public String getcreatedAt ( ) {
        return this.createdAt;
    }

    /** SETTER
     * createdAt
     * @param value the value to set
     */
    @JsonSetter("created_at")
    public void setcreatedAt (String value) {
        this.createdAt = value;
    }

    /** GETTER
     * orgId
     * @return the value
     */
    @JsonGetter("org_id")
    public String getOrgId ( ) {
        return this.orgId;
    }

    /** SETTER
     * orgId
     * @param value the value to set
     */
    @JsonSetter("org_id")
    public void setOrgId (String value) {
        this.orgId = value;
    }

    /** GETTER
     * appId
     * @return the value
     */
    @JsonGetter("app_id")
    public String getAppId ( ) {
        return this.appId;
    }

    /** SETTER
     * appId
     * @param value the value to set
     */
    @JsonSetter("app_id")
    public void setAppId (String value) {
        this.appId = value;
    }

    /** GETTER
     * name
     * @return the value
     */
    @JsonGetter("name")
    public String getName ( ) {
        return this.name;
    }

    /** SETTER
     * name
     * @param value the value to set
     */
    @JsonSetter("name")
    public void setName (String value) {
        this.name = value;
    }

    /** GETTER
     * block
     * @return the value
     */
    @JsonGetter("block")
    public boolean getBlock ( ) {
        return this.block;
    }

    /** SETTER
     * block
     * @param value the value to set
     */
    @JsonSetter("block")
    public void setBlock (boolean value) {
        this.block = value;
    }

    /** GETTER
     * type
     * @return the value
     */
    @JsonGetter("type")
    public String getType ( ) {
        return this.type;
    }

    /** SETTER
     * type
     * @param value the value to set
     */
    @JsonSetter("type")
    public void setType (String value) {
        this.type = value;
    }

    /** GETTER
     * variables
     * @return the value
     */
    @JsonGetter("variables")
    public List<GovernanceRulesVariableModel> getVariables ( ) {
        return this.variables == null? new ArrayList<>(): this.variables; }

    /** SETTER
     * type
     * @param value the value to set
     */
    @JsonSetter("variables")
    public void setVariables (List<GovernanceRulesVariableModel> value) {
        this.variables = value;
    }

    /** GETTER
     * regexConfig
     * @return the value
     */
    @JsonGetter("regex_config")
    public List<GovernanceRuleRegexRuleModel> getRegexConfig ( ) {
        return this.regexConfig;
    }

    /** SETTER
     * regexConfig
     * @param value the value to set
     */
    @JsonSetter("regex_config")
    public void setRegexConfig (List<GovernanceRuleRegexRuleModel> value) {
        this.regexConfig = value;
    }

    /** GETTER
     * Response object
     * @return the value
     */
    @JsonGetter("response")
    public GovernanceRulesResponseModel getResponse ( ) {
        return this.response;
    }

    /** SETTER
     * Response object
     * @param value the value to set
     */
    @JsonSetter("response")
    public void setResponse (GovernanceRulesResponseModel value) {
        this.response = value;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("***** GovernanceRulesModel Details *****\n");
        sb.append("Id="+getId()+"\n");
        sb.append("CreatedAt="+getcreatedAt()+"\n");
        sb.append("OrgId="+getOrgId()+"\n");
        sb.append("AppId="+getAppId()+"\n");
        sb.append("Name="+getName()+"\n");
        sb.append("Block="+getBlock()+"\n");
        sb.append("Type="+getType()+"\n");
        sb.append("AppliedTo="+getAppliedTo()+"\n");
        sb.append("AppliedToUnidentified="+isAppliedToUnidentified()+"\n");
        List<GovernanceRulesVariableModel> variableList = getVariables();
        if (variableList != null) {
            sb.append("Variables=" + convertVariables(variableList) + "\n");
        }
        List<GovernanceRuleRegexRuleModel> regexConfigs = getRegexConfig();
        if (regexConfigs != null) {
            sb.append("RegexConfigs=" + convertRegexConfigs(regexConfigs) + "\n");
        }
        sb.append("Response="+convertResponse(getResponse())+"\n");
        sb.append("*****************************");
        return sb.toString();
    }

    private String convertResponse(GovernanceRulesResponseModel response) {
        StringBuilder sb = new StringBuilder();
        sb.append("Status="+response.getStatus()+"\n");
        sb.append("Headers="+convertWithStream(response.getHeaders())+"\n");
        sb.append("Body="+response.getBody()+"\n");
        return sb.toString();
    }

    private List<String> convertVariables(List<GovernanceRulesVariableModel> variableList) {
        List<String> vlist = new ArrayList<String>();
        for (GovernanceRulesVariableModel variables : variableList) {
            StringBuilder sb = new StringBuilder();
            sb.append("Name="+variables.getName()+"\n");
            sb.append("Path="+variables.getPath()+"\n");
            vlist.add(sb.toString());
        }
        return vlist;
    }

    private List<String> convertRegexConfigs(List<GovernanceRuleRegexRuleModel> regexConfigList) {
        List<String> rlist = new ArrayList<String>();
        for (GovernanceRuleRegexRuleModel regexConfigs: regexConfigList) {
            StringBuilder sb = new StringBuilder();
            sb.append("Conditions="+convertRegexCondition(regexConfigs.getConditions())+"\n");
            sb.append("SampleRate="+regexConfigs.getSampleRate()+"\n");
            rlist.add(sb.toString());
        }
        return rlist;
    }

    private List<String> convertRegexCondition(List<GovernanceRuleRegexConditionModel> regexConditionList) {
        List<String> rclist = new ArrayList<String>();
        for (GovernanceRuleRegexConditionModel regexConditions: regexConditionList) {
            StringBuilder sb = new StringBuilder();
            sb.append("Path="+regexConditions.getPath()+"\n");
            sb.append("Value="+regexConditions.getValue()+"\n");
            rclist.add(sb.toString());
        }
        return rclist;
    }

    private String convertWithStream(Map<String, String> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }
}
