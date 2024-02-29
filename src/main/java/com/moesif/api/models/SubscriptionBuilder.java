/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import java.util.*;

public class SubscriptionBuilder {
    //the instance to build
    private SubscriptionModel SubscriptionModel;

    /**
     * Default constructor to initialize the instance
     */
    public SubscriptionBuilder() {
        SubscriptionModel = new SubscriptionModel();
    }

    /**
     * Subscription Id
     * @param subscriptionId the field to set
     * @return itself
     */
    public SubscriptionBuilder subscriptionId(String subscriptionId) {
        SubscriptionModel.setSubscriptionId(subscriptionId);
        return this;
    }

    /**
     * User's company_id string
     * @param companyId the field to set
     * @return itself
     */
    public SubscriptionBuilder companyId(String companyId) {
    	SubscriptionModel.setCompanyId(companyId);
        return this;
    }
    
    /**
     * Start date of the current subscription period
     * @param currentPeriodStart the field to set
     * @return itself
     */
    public SubscriptionBuilder currentPeriodStart(Date currentPeriodStart) {
        SubscriptionModel.setCurrentPeriodStart(currentPeriodStart);
        return this;
    }

    /**
     * End date of the current subscription period
     * @param currentPeriodEnd the field to set
     * @return itself
     */
    public SubscriptionBuilder currentPeriodEnd(Date currentPeriodEnd) {
        SubscriptionModel.setCurrentPeriodEnd(currentPeriodEnd);
        return this;
    }

    /**
     * Subscription status
     * @param status the field to set
     * @return itself
     */
    public SubscriptionBuilder status(String status) {
        SubscriptionModel.setStatus(status);
        return this;
    }

    /**
     * Custom user metadata as a JSON object
     * @param metadata the field to set
     * @return itself
     */
    public SubscriptionBuilder metadata(Object metadata) {
    	SubscriptionModel.setMetadata(metadata);
        return this;
    }

    /**
     * Build the instance with the given values
     * @return The built UserModel
     */
    public SubscriptionModel build() {
        return SubscriptionModel;
    }
}