/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.moesif.api.Configuration;

public class EventRequestModel
        implements java.io.Serializable {
    private static final long serialVersionUID = 5434552751827014259L;
    private Date time;
    private String uri;
    private String verb;
    private Map<String, String> headers;
    private String apiVersion;
    private String ipAddress;
    private Object body;
    private String transferEncoding;
    /** GETTER
     * Time when request was made
     * @return the value
     */
    @JsonGetter("time")
    @JsonFormat(pattern = Configuration.DateTimeFormat)
    public Date getTime ( ) {
        return this.time;
    }
    
    /** SETTER
     * Time when request was made
     * @param value the value to set
     */
    @JsonSetter("time")
    public void setTime (Date value) { 
        this.time = value;
    }
 
    /** GETTER
     * full uri of request such as https://www.example.com/my_path?param=1
     * @return the value
     */
    @JsonGetter("uri")
    public String getUri ( ) { 
        return this.uri;
    }
    
    /** SETTER
     * full uri of request such as https://www.example.com/my_path?param=1
     * @param value the value to set
     */
    @JsonSetter("uri")
    public void setUri (String value) { 
        this.uri = value;
    }
 
    /** GETTER
     * verb of the API request such as GET or POST
     * @return the value
     */
    @JsonGetter("verb")
    public String getVerb ( ) { 
        return this.verb;
    }
    
    /** SETTER
     * verb of the API request such as GET or POST
     * @param value the value to set
     */
    @JsonSetter("verb")
    public void setVerb (String value) { 
        this.verb = value;
    }
 
    /** GETTER
     * Key/Value map of request headers
     * @return the value
     */
    @JsonGetter("headers")
    public Map<String, String> getHeaders ( ) {
        return this.headers;
    }
    
    /** SETTER
     * Key/Value map of request headers
     * @param value the value to set
     */
    @JsonSetter("headers")
    public void setHeaders (Map<String, String> value) {
        this.headers = value;
    }
 
    /** GETTER
     * Optionally tag the call with your API or App version
     * @return the value
     */
    @JsonGetter("api_version")
    public String getApiVersion ( ) { 
        return this.apiVersion;
    }
    
    /** SETTER
     * Optionally tag the call with your API or App version
     * @param value the value to set
     */
    @JsonSetter("api_version")
    public void setApiVersion (String value) { 
        this.apiVersion = value;
    }
 
    /** GETTER
     * IP Address of the client if known.
     * @return the value
     */
    @JsonGetter("ip_address")
    public String getIpAddress ( ) { 
        return this.ipAddress;
    }
    
    /** SETTER
     * IP Address of the client if known.
     * @param value the value to set
     */
    @JsonSetter("ip_address")
    public void setIpAddress (String value) { 
        this.ipAddress = value;
    }
 
    /** GETTER
     * Request body
     * @return the value
     */
    @JsonGetter("body")
    public Object getBody ( ) { 
        return this.body;
    }
    
    /** SETTER
     * Request body
     * @param value the value to set
     */
    @JsonSetter("body")
    public void setBody (Object value) { 
        this.body = value;
    }

    /** GETTER
     * Transfer Encoding of the body such as "base64", null value implies "json" transfer encoding
     * @return the value
     */
    @JsonGetter("transfer_encoding")
    public String getTransferEncoding ( ) {
        return this.transferEncoding;
    }

    /** SETTER
     * Transfer Encoding of the body such as "base64", null value implies "json" transfer encoding
     * @param value the value to set
     */
    @JsonSetter("transfer_encoding")
    public void setTransferEncoding (String value) {
        this.transferEncoding = value;
    }
 
}
 