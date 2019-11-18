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

public class CampaignModel
        implements java.io.Serializable {
    private static final long serialVersionUID = -5678430578597637724L;
    private String utmSource;
    private String utmMedium;
    private String utmCampaign;
    private String utmTerm;
    private String utmContent;
    private String referrer;
    private String referringDomain;
    private String gclid;
    
    /** GETTER
     * the utm source
     * @return the value
     */
    @JsonGetter("utm_source")
    public String getUtmSource ( ) {
        return this.utmSource;
    }

    /** SETTER
     * the utm source
     * @param value the value to set
     */
    @JsonSetter("utm_source")
    public void setUtmSource (String value) {
        this.utmSource = value;
    }

    /** GETTER
     * the utm medium
     * @return the value
     */
    @JsonGetter("utm_medium")
    public String getUtmMedium( ) {
        return this.utmMedium;
    }

    /** SETTER
     * the utm medium
     * @param value the value to set
     */
    @JsonSetter("utm_medium")
    public void setUtmMedium (String value) {
        this.utmMedium = value;
    }

    /** GETTER
     * the utm campaign
     * @return the value
     */
    @JsonGetter("utm_campaign")
    public String getUtmCampaign( ) {
        return this.utmCampaign;
    }
    
    /** SETTER
     * the utm campaign
     * @param value the value to set
     */
    @JsonSetter("utm_campaign")
    public void setUtmCampaign (String value) {
        this.utmCampaign = value;
    }
 
    /** GETTER
     * the utm term
     * @return the value
     */
    @JsonGetter("utm_term")
    public String getUtmTerm ( ) {
        return this.utmTerm;
    }
    
    /** SETTER
     * the utm term
     * @param value the value to set
     */
    @JsonSetter("utm_term")
    public void setUtmTerm (String value) {
        this.utmTerm = value;
    }

    /** GETTER
     * the utm content
     * @return the value
     */
    @JsonGetter("utm_content")
    public String getUtmContent ( ) {
        return this.utmContent;
    }

    /** SETTER
     * the utm term
     * @param value the value to set
     */
    @JsonSetter("utm_content")
    public void setUtmContent (String value) {
        this.utmContent = value;
    }

    /** GETTER
     * the referrer
     * @return the value
     */
    @JsonGetter("referrer")
    public String getReferrer ( ) {
        return this.referrer;
    }

    /** SETTER
     * the referrer
     * @param value the value to set
     */
    @JsonSetter("referrer")
    public void setReferrer (String value) {
        this.referrer = value;
    }

    /** GETTER
     * the referring domain
     * @return the value
     */
    @JsonGetter("referring_domain")
    public String getReferringDomain ( ) {
        return this.referringDomain;
    }

    /** SETTER
     * the referring domain
     * @param value the value to set
     */
    @JsonSetter("referring_domain")
    public void setReferringDomain (String value) {
        this.referringDomain = value;
    }

    /** GETTER
     * the gclid
     * @return the value
     */
    @JsonGetter("gclid")
    public String getGclid ( ) {
        return this.gclid;
    }

    /** SETTER
     * the gclid
     * @param value the value to set
     */
    @JsonSetter("gclid")
    public void setGclid (String value) {
        this.gclid = value;
    }
}
