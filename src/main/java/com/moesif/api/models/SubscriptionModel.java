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

public class SubscriptionModel
        implements java.io.Serializable {
    private static final long serialVersionUID = -5678430578597637724L;
    private String subscriptionId;
    private String companyId;
    private Date currentPeriodStart;
    private Date currentPeriodEnd;
    private String status;
    private Object metadata;

    /** GETTER
     * Subscription Id
     * @return the value
     */
    @JsonGetter("subscription_id")
    public String getSubscriptionId ( ) {
        return this.subscriptionId;
    }

    /** SETTER
     * Subscription Id
     * @param value the value to set
     */
    @JsonSetter("subscription_id")
    public void setSubscriptionId (String value) {
        this.subscriptionId = value;
    }

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
     * Start date of the current subscription period
     * @return the value
     */
    @JsonGetter("current_period_start")
    @JsonFormat(pattern = Configuration.DateTimeFormat)
    public Date getCurrentPeriodStart ( ) {
        return this.currentPeriodStart;
    }

    /** SETTER
     * Start date of the current subscription period
     * @param value the value to set
     */
    @JsonSetter("current_period_start")
    public void setCurrentPeriodStart (Date value) {
        this.currentPeriodStart = value;
    }

    /** GETTER
     * End date of the current subscription period
     * @return the value
     */
    @JsonGetter("current_period_end")
    @JsonFormat(pattern = Configuration.DateTimeFormat)
    public Date getCurrentPeriodEnd ( ) {
        return this.currentPeriodEnd;
    }

    /** SETTER
     * End date of the current subscription period
     * @param value the value to set
     */
    @JsonSetter("current_period_end")
    public void setCurrentPeriodEnd (Date value) {
        this.currentPeriodEnd = value;
    }

    /** GETTER
     * Subscription Status
     * @return the value
     */
    @JsonGetter("status")
    public String getStatus ( ) {
        return this.status;
    }

    /** SETTER
     * Subscription Status
     * @param value the value to set
     */
    @JsonSetter("status")
    public void setStatus (String value) {
        this.status = value;
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
}
 