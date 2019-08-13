/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AppConfigModel
        implements java.io.Serializable {
    private String orgId;
    private String appId;
    private int sampleRate = 100;
    private Map<String, Integer> userSampleRate = new HashMap<String, Integer>();
    private Map<String, Integer> companySampleRate = new HashMap<String, Integer>();

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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("***** AppConfigModel Details *****\n");
        sb.append("OrgId="+getOrgId()+"\n");
        sb.append("AppId="+getAppId()+"\n");
        sb.append("SampleRate="+getSampleRate()+"\n");
        sb.append("UserSampleRate="+convertWithStream(getUserSampleRate())+"\n");
        sb.append("CompanySampleRate="+convertWithStream(getCompanySampleRate())+"\n");
        sb.append("*****************************");
        return sb.toString();
    }

    private String convertWithStream(Map<String, Integer> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }
}
 