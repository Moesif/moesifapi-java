/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import java.util.*;

public class CampaignBuilder {
    //the instance to build
    private CampaignModel CampaignModel;

    /**
     * Default constructor to initialize the instance
     */
    public CampaignBuilder() {
        CampaignModel = new CampaignModel();
    }

    /**
     * the utm source
     * @param utmSource the field to set
     * @return itself
     */
    public CampaignBuilder utmSource(String utmSource) {
        CampaignModel.setUtmSource(utmSource);
        return this;
    }

    /**
     * the utm medium
     * @param utmMedium the field to set
     * @return itself
     */
    public CampaignBuilder utmMedium(String utmMedium) {
        CampaignModel.setUtmMedium(utmMedium);
        return this;
    }

    /**
     * the utm campaign
     * @param utmCampaign the field to set
     * @return itself
     */
    public CampaignBuilder utmCampaign(String utmCampaign) {
        CampaignModel.setUtmCampaign(utmCampaign);
        return this;
    }

    /**
     * the utm term
     * @param utmTerm the field to set
     * @return itself
     */
    public CampaignBuilder utmTerm(String utmTerm) {
        CampaignModel.setUtmTerm(utmTerm);
        return this;
    }

    /**
     * the utm content
     * @param utmContent the field to set
     * @return itself
     */
    public CampaignBuilder utmContent(String utmContent) {
        CampaignModel.setUtmContent(utmContent);
        return this;
    }

    /**
     * the referrer
     * @param referrer the field to set
     * @return itself
     */
    public CampaignBuilder referrer(String referrer) {
        CampaignModel.setReferrer(referrer);
        return this;
    }

    /**
     * the referring model
     * @param referringDomain the field to set
     * @return itself
     */
    public CampaignBuilder referringDomain(String referringDomain) {
        CampaignModel.setReferringDomain(referringDomain);
        return this;
    }

    /**
     * the gclid
     * @param gclid the field to set
     * @return itself
     */
    public CampaignBuilder gclid(String gclid) {
        CampaignModel.setGclid(gclid);
        return this;
    }

    /**
     * Build the instance with the given values
     * @return The built CampaignModel
     */
    public CampaignModel build() {
        return CampaignModel;
    }
}
