/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.moesif.api.Configuration;

import java.util.*;

public class CompanyModel
        implements java.io.Serializable {
    private static final long serialVersionUID = -5678430578597637724L;
    private String companyId;
    private Date modifiedTime;
    private String ipAddress;
    private String sessionToken;
    private String companyDomain;
    private Object metadata;
    private CampaignModel campaign;
    /** GETTER
     * User's company_id string
     * @return the value
     */
    @JsonGetter("company_id")
    public String getCompanyId ( ) {
        return this.companyId;
    }

    /** SETTER
     * User's company_id string
     * @param value the value to set
     */
    @JsonSetter("company_id")
    public void setCompanyId (String value) {
        this.companyId = value;
    }

    /** GETTER
     * Timestamp of when user was updated. (Moesif will use the current time if not set)
     * @return the value
     */
    @JsonGetter("modified_time")
    @JsonFormat(pattern = Configuration.DateTimeFormat)
    public Date getModifiedTime( ) {
        return this.modifiedTime;
    }
    
    /** SETTER
     * Timestamp of when user was updated. (Moesif will use the current time if not set)
     * @param value the value to set
     */
    @JsonSetter("modified_time")
    public void setModifiedTime (Date value) {
        this.modifiedTime = value;
    }
 
    /** GETTER
     * Last seen IP Address of the user if known
     * @return the value
     */
    @JsonGetter("ip_address")
    public String getIpAddress ( ) {
        return this.ipAddress;
    }
    
    /** SETTER
     * Last seen IP Address of the user if known
     * @param value the value to set
     */
    @JsonSetter("ip_address")
    public void setIpAddress (String value) {
        this.ipAddress = value;
    }

    /** GETTER
     * End user's auth/session token
     * @return the value
     */
    @JsonGetter("session_token")
    public String getSessionToken ( ) {
        return this.sessionToken;
    }

    /** SETTER
     * End user's auth/session token
     * @param value the value to set
     */
    @JsonSetter("session_token")
    public void setSessionToken (String value) {
        this.sessionToken = value;
    }

    /** GETTER
     * Company Domain
     * @return the value
     */
    @JsonGetter("company_domain")
    public String getCompanyDomain ( ) {
        return this.companyDomain;
    }

    /** SETTER
     * Company Domain
     * @param value the value to set
     */
    @JsonSetter("company_domain")
    public void setCompanyDomain (String value) {
        this.companyDomain = value;
    }

    /** GETTER
     * Custom company metadata as a JSON object
     * @return the value
     */
    @JsonGetter("metadata")
    public Object getMetadata ( ) {
        return this.metadata;
    }

    /** SETTER
     * Custom company metadata as a JSON object
     * @param value the value to set
     */
    @JsonSetter("metadata")
    public void setMetadata (Object value) {
        this.metadata = value;
    }

    /** GETTER
     * the campaign object
     * @return the value
     */
    @JsonGetter("campaign")
    public CampaignModel getCampaign ( ) {
        return this.campaign;
    }
    
    /** SETTER
     * the campaign object
     * @param value the value to set
     */
    @JsonSetter("campaign")
    public void setCampaign (CampaignModel value) {
        this.campaign = value;
    }
}
 