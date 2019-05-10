/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class AppConfigModel
        implements java.io.Serializable {
    private String orgId;
    private String appId;
    private int sampleRate;
    private String etag;

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
     * Config's etag string
     * @return the value
     */
    public String getEtag ( ) {
        return this.etag;
    }

    /** SETTER
     * Config's etag string
     * @param value the value to set
     */
    public void setEtag (String value) {
        this.etag = value;
    }
}
 