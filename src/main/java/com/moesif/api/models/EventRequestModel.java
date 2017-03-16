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
    private Object headers;
    private String apiVersion;
    private String ipAddress;
    private Object body;
    private String transferEncoding;
    /** GETTER
     * Time when request was made
     */
    @JsonGetter("time")
    @JsonFormat(pattern = Configuration.DateTimeFormat)
    public Date getTime ( ) {
        return this.time;
    }
    
    /** SETTER
     * Time when request was made
     */
    @JsonSetter("time")
    public void setTime (Date value) { 
        this.time = value;
    }
 
    /** GETTER
     * full uri of request such as https://www.example.com/my_path?param=1
     */
    @JsonGetter("uri")
    public String getUri ( ) { 
        return this.uri;
    }
    
    /** SETTER
     * full uri of request such as https://www.example.com/my_path?param=1
     */
    @JsonSetter("uri")
    public void setUri (String value) { 
        this.uri = value;
    }
 
    /** GETTER
     * verb of the API request such as GET or POST
     */
    @JsonGetter("verb")
    public String getVerb ( ) { 
        return this.verb;
    }
    
    /** SETTER
     * verb of the API request such as GET or POST
     */
    @JsonSetter("verb")
    public void setVerb (String value) { 
        this.verb = value;
    }
 
    /** GETTER
     * Key/Value map of request headers
     */
    @JsonGetter("headers")
    public Object getHeaders ( ) { 
        return this.headers;
    }
    
    /** SETTER
     * Key/Value map of request headers
     */
    @JsonSetter("headers")
    public void setHeaders (Object value) { 
        this.headers = value;
    }
 
    /** GETTER
     * Optionally tag the call with your API or App version
     */
    @JsonGetter("api_version")
    public String getApiVersion ( ) { 
        return this.apiVersion;
    }
    
    /** SETTER
     * Optionally tag the call with your API or App version
     */
    @JsonSetter("api_version")
    public void setApiVersion (String value) { 
        this.apiVersion = value;
    }
 
    /** GETTER
     * IP Address of the client if known.
     */
    @JsonGetter("ip_address")
    public String getIpAddress ( ) { 
        return this.ipAddress;
    }
    
    /** SETTER
     * IP Address of the client if known.
     */
    @JsonSetter("ip_address")
    public void setIpAddress (String value) { 
        this.ipAddress = value;
    }
 
    /** GETTER
     * Request body
     */
    @JsonGetter("body")
    public Object getBody ( ) { 
        return this.body;
    }
    
    /** SETTER
     * Request body
     */
    @JsonSetter("body")
    public void setBody (Object value) { 
        this.body = value;
    }

    /** GETTER
     * Transfer Encoding of the body such as "base64", null value implies "json" transfer encoding
     */
    @JsonGetter("transfer_encoding")
    public String getTransferEncoding ( ) {
        return this.transferEncoding;
    }

    /** SETTER
     * Transfer Encoding of the body such as "base64", null value implies "json" transfer encoding
     */
    @JsonSetter("transfer_encoding")
    public void setTransferEncoding (String value) {
        this.transferEncoding = value;
    }
 
}
 