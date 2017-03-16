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

public class UserModel
        implements java.io.Serializable {
    private static final long serialVersionUID = -5678430578597637724L;
    private String userId;
    private Date modifiedTime;
    private String ipAddress;
    private String sessionToken;
    private String userAgentString;
    private Object metadata;
    /** GETTER
     * End user's user_id string from your app
     */
    @JsonGetter("user_id")
    public String getUserId ( ) {
        return this.userId;
    }

    /** SETTER
     * End user's user_id string from your app
     */
    @JsonSetter("user_id")
    public void setUserId (String value) {
        this.userId = value;
    }

    /** GETTER
     * Timestamp of when user was updated. (Moesif will use the current time if not set)
     */
    @JsonGetter("modified_time")
    @JsonFormat(pattern = Configuration.DateTimeFormat)
    public Date getModifiedTime( ) {
        return this.modifiedTime;
    }
    
    /** SETTER
     * Timestamp of when user was updated. (Moesif will use the current time if not set)
     */
    @JsonSetter("modified_time")
    public void setModifiedTime (Date value) {
        this.modifiedTime = value;
    }
 
    /** GETTER
     * Last seen IP Address of the user if known
     */
    @JsonGetter("ip_address")
    public String getIpAddress ( ) {
        return this.ipAddress;
    }
    
    /** SETTER
     * Last seen IP Address of the user if known
     */
    @JsonSetter("ip_address")
    public void setIpAddress (String value) {
        this.ipAddress = value;
    }

    /** GETTER
     * End user's auth/session token
     */
    @JsonGetter("session_token")
    public String getSessionToken ( ) {
        return this.sessionToken;
    }

    /** SETTER
     * End user's auth/session token
     */
    @JsonSetter("session_token")
    public void setSessionToken (String value) {
        this.sessionToken = value;
    }

    /** GETTER
     * User Agent String of the user's device
     * such as "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36"
     */
    @JsonGetter("user_agent_string")
    public String getUserAgentString ( ) {
        return this.userAgentString;
    }

    /** SETTER
     * User Agent String of the user's device
     * such as "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36"
     */
    @JsonSetter("user_agent_string")
    public void setUserAgentString (String value) {
        this.userAgentString = value;
    }

    /** GETTER
     * Custom user metadata as a JSON object
     */
    @JsonGetter("metadata")
    public Object getMetadata ( ) {
        return this.metadata;
    }

    /** SETTER
     * Custom user metadata as a JSON object
     */
    @JsonSetter("metadata")
    public void setMetadata (Object value) {
        this.metadata = value;
    }
}
 