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
    private String companyId;
    private Object metadata;
    /** GETTER
     * API request object
     * @return the value
     */
    @JsonGetter("request")
    public EventRequestModel getRequest ( ) {
        return this.request;
    }
    
    /** SETTER
     * API request object
     * @param value the value to set
     */
    @JsonSetter("request")
    public void setRequest (EventRequestModel value) {
        this.request = value;
    }
 
    /** GETTER
     * API response Object
     * @return the value
     */
    @JsonGetter("response")
    public EventResponseModel getResponse ( ) {
        return this.response;
    }
    
    /** SETTER
     * API response Object
     * @param value the value to set
     */
    @JsonSetter("response")
    public void setResponse (EventResponseModel value) {
        this.response = value;
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
     * comma separated list of tags, see documentation
     * @return the value
     */
    @JsonGetter("tags")
    public String getTags ( ) { 
        return this.tags;
    }
    
    /** SETTER
     * comma separated list of tags, see documentation
     * @param value the value to set
     */
    @JsonSetter("tags")
    public void setTags (String value) { 
        this.tags = value;
    }
 
    /** GETTER
     * End user's user_id string from your app
     * @return the value
     */
    @JsonGetter("user_id")
    public String getUserId ( ) { 
        return this.userId;
    }
    
    /** SETTER
     * End user's user_id string from your app
     * @param value the value to set
     */
    @JsonSetter("user_id")
    public void setUserId (String value) { 
        this.userId = value;
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
     * Custom event metadata as a JSON object
     * @return the value
     */
    @JsonGetter("metadata")
    public Object getMetadata ( ) {
        return this.metadata;
    }

    /** SETTER
     * Custom event metadata as a JSON object
     * @param value the value to set
     */
    @JsonSetter("metadata")
    public void setMetadata (Object value) {
        this.metadata = value;
    }
}
 