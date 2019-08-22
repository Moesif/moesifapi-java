/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import java.util.*;

public class UserBuilder {
    //the instance to build
    private UserModel UserModel;

    /**
     * Default constructor to initialize the instance
     */
    public UserBuilder() {
        UserModel = new UserModel();
    }

    /**
     * End user's user_id string from your app
     * @param userId the field to set
     * @return itself
     */
    public UserBuilder userId(String userId) {
        UserModel.setUserId(userId);
        return this;
    }

    /**
     * Company_id associated with the user if known
     * @param companyId the field to set
     * @return itself
     */
    public UserBuilder companyId(String companyId) {
        UserModel.setCompanyId(companyId);
        return this;
    }

    /**
     * Timestamp of when user was updated. (Moesif will use the current time if not set)
     * @param modifiedTime the field to set
     * @return itself
     */
    public UserBuilder modifiedTime(Date modifiedTime) {
        UserModel.setMetadata(modifiedTime);
        return this;
    }

    /**
     * Last seen IP Address of the user if known
     * @param ipAddress the field to set
     * @return itself
     */
    public UserBuilder ipAddress(String ipAddress) {
        UserModel.setIpAddress(ipAddress);
        return this;
    }

    /**
     * End user's auth/session token
     * @param sessionToken the field to set
     * @return itself
     */
    public UserBuilder sessionToken(String sessionToken) {
        UserModel.setSessionToken(sessionToken);
        return this;
    }

    /**
     * User Agent String of the user's device
     * such as "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36"
     * @param userAgentString the field to set
     * @return itself
     */
    public UserBuilder userAgentString(String userAgentString) {
        UserModel.setUserAgentString(userAgentString);
        return this;
    }

    /**
     * Custom user metadata as a JSON object
     * @param metadata the field to set
     * @return itself
     */
    public UserBuilder metadata(Object metadata) {
        UserModel.setMetadata(metadata);
        return this;
    }

    /**
     * Build the instance with the given values
     * @return The built UserModel
     */
    public UserModel build() {
        return UserModel;
    }
}