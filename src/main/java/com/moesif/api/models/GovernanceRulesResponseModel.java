package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.HashMap;
import java.util.Map;

public class GovernanceRulesResponseModel
        implements java.io.Serializable {
    private int status;
    private Map<String, String> headers = new HashMap<String, String>();
    private Object body;

    /** GETTER
     * Response status string
     * @return the value
     */
    @JsonGetter("status")
    public int getStatus ( ) {
        return this.status;
    }

    /** SETTER
     * Response status string
     * @param value the value to set
     */
    @JsonSetter("status")
    public void setStatus (int value) {
        this.status = value;
    }

    /** GETTER
     * Response headers map
     * @return the value
     */
    @JsonGetter("headers")
    public Map<String, String> getHeaders ( ) {
        return this.headers;
    }

    /** SETTER
     * Response headers map
     * @param value the value to set
     */
    @JsonSetter("headers")
    public void setHeaders (Map<String, String>  value) {
        this.headers = value;
    }


    /** GETTER
     * Body as a JsValue
     * @return the value
     */
    @JsonGetter("body")
    public Object getBody ( ) {
        return this.body;
    }

    /** SETTER
     * Body as a JsValue
     * @param value the value to set
     */
    @JsonSetter("body")
    public void setBody (Object value) {
        this.body = value;
    }

}
