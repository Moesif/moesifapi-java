/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class EventModel
        implements java.io.Serializable {
    private static final long serialVersionUID = 5526151489602879126L;
    private EventRequestModel request;
    private EventResponseModel response;
    private String sessionToken;
    private String tags;
    private String userId;
    /** GETTER
     * API request object
     */
    @JsonGetter("request")
    public EventRequestModel getRequest ( ) {
        return this.request;
    }
    
    /** SETTER
     * API request object
     */
    @JsonSetter("request")
    public void setRequest (EventRequestModel value) {
        this.request = value;
    }
 
    /** GETTER
     * API response Object
     */
    @JsonGetter("response")
    public EventResponseModel getResponse ( ) {
        return this.response;
    }
    
    /** SETTER
     * API response Object
     */
    @JsonSetter("response")
    public void setResponse (EventResponseModel value) {
        this.response = value;
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
     * comma separated list of tags, see documentation
     */
    @JsonGetter("tags")
    public String getTags ( ) { 
        return this.tags;
    }
    
    /** SETTER
     * comma separated list of tags, see documentation
     */
    @JsonSetter("tags")
    public void setTags (String value) { 
        this.tags = value;
    }
 
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
 
}
 