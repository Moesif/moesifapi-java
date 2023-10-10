/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AppConfigModel
        implements java.io.Serializable {
    private String orgId;
    private String appId;
    private int sampleRate = 100;
    private Map<String, Integer> userSampleRate = new HashMap<String, Integer>();
    private Map<String, Integer> companySampleRate = new HashMap<String, Integer>();
    private Map<String, List<EntityRuleModel>> userRules = new HashMap<String, List<EntityRuleModel>>();
    private Map<String, List<EntityRuleModel>> companyRules = new HashMap<String, List<EntityRuleModel>>();

    private List<RegexConfigModel> regex_config = new ArrayList<RegexConfigModel>();

    /** GETTER
     * Config's org_id string
     * @return the value
     */
    @JsonGetter("org_id")
    public String getOrgId ( ) {
        return this.orgId;
    }

    /** SETTER
     * Config's org_id string
     * @param value the value to set
     */
    @JsonSetter("org_id")
    public void setOrgId (String value) {
        this.orgId = value;
    }

    /** GETTER
     * Config's app_id string
     * @return the value
     */
    @JsonGetter("app_id")
    public String getAppId ( ) {
        return this.appId;
    }

    /** SETTER
     * Config's app_id string
     * @param value the value to set
     */
    @JsonSetter("app_id")
    public void setAppId (String value) {
        this.appId = value;
    }

    /** GETTER
     * Config's sample_rate string
     * @return the value
     */
    @JsonGetter("sample_rate")
    public int getSampleRate ( ) {
        return this.sampleRate;
    }

    /** SETTER
     * Config's sample_rate string
     * @param value the value to set
     */
    @JsonSetter("sample_rate")
    public void setSampleRate (int value) {
        this.sampleRate = value;
    }

    /** GETTER
     * Config's userSampleRate map
     * @return the value
     */
    @JsonGetter("user_sample_rate")
    public Map<String, Integer> getUserSampleRate ( ) {
        return this.userSampleRate;
    }

    /** SETTER
     * Config's userSampleRate map
     * @param value the value to set
     */
    @JsonSetter("user_sample_rate")
    public void setUserSampleRate (Map<String, Integer>  value) {
        this.userSampleRate = value;
    }

    /** GETTER
     * Config's userSampleRate map
     * @return the value
     */
    @JsonGetter("company_sample_rate")
    public Map<String, Integer> getCompanySampleRate ( ) {
        return this.companySampleRate;
    }

    /** SETTER
     * Config's userSampleRate map
     * @param value the value to set
     */
    @JsonSetter("company_sample_rate")
    public void setCompanySampleRate (Map<String, Integer>  value) {
        this.companySampleRate = value;
    }

    @JsonGetter("user_rules")
    public Map<String, List<EntityRuleModel>> getUserRules() {
        return userRules;
    }

    @JsonSetter("user_rules")
    public void setUserRules(Map<String, List<EntityRuleModel>> userRules) {
        this.userRules = userRules;
    }

    @JsonGetter("company_rules")
    public Map<String, List<EntityRuleModel>> getCompanyRules() {
        return companyRules;
    }

    @JsonSetter("company_rules")
    public void setCompanyRules(Map<String, List<EntityRuleModel>> companyRules) {
        this.companyRules = companyRules;
    }

    @Override
    public String toString(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @JsonGetter("regex_config")
    public List<RegexConfigModel> getRegex_config() {
        return regex_config;
    }
    @JsonSetter("regex_config")
    public void setRegex_config(List<RegexConfigModel> regex_config) {
        this.regex_config = regex_config;
    }
}
 