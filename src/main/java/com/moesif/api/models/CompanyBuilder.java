/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import java.util.*;

public class CompanyBuilder {
    //the instance to build
    private CompanyModel CompanyModel;

    /**
     * Default constructor to initialize the instance
     */
    public CompanyBuilder() {
        CompanyModel = new CompanyModel();
    }

    /**
     * User's company_id string
     * @param companyId the field to set
     * @return itself
     */
    public CompanyBuilder companyId(String companyId) {
    	CompanyModel.setCompanyId(companyId);
        return this;
    }

    /**
     * Timestamp of when user was updated. (Moesif will use the current time if not set)
     * @param modifiedTime the field to set
     * @return itself
     */
    public CompanyBuilder modifiedTime(Date modifiedTime) {
    	CompanyModel.setModifiedTime(modifiedTime);
        return this;
    }

    /**
     * Last seen IP Address of the user if known
     * @param ipAddress the field to set
     * @return itself
     */
    public CompanyBuilder ipAddress(String ipAddress) {
    	CompanyModel.setIpAddress(ipAddress);
        return this;
    }

    /**
     * End user's auth/session token
     * @param sessionToken the field to set
     * @return itself
     */
    public CompanyBuilder sessionToken(String sessionToken) {
    	CompanyModel.setSessionToken(sessionToken);
        return this;
    }

    /**
     * Company Domain String
     * @param companyDomain the field to set
     * @return itself
     */
    public CompanyBuilder companyDomain(String companyDomain) {
    	CompanyModel.setCompanyDomain(companyDomain);
        return this;
    }

    /**
     * Custom user metadata as a JSON object
     * @param metadata the field to set
     * @return itself
     */
    public CompanyBuilder metadata(Object metadata) {
    	CompanyModel.setMetadata(metadata);
        return this;
    }

    /**
     * Build the instance with the given values
     * @return The built UserModel
     */
    public CompanyModel build() {
        return CompanyModel;
    }
}