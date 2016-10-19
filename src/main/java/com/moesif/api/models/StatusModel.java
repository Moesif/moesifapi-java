/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class StatusModel
        implements java.io.Serializable {
    private static final long serialVersionUID = 5555082808982388742L;
    private boolean status;
    private String region;
    /** GETTER
     * Status of Call
     */
    @JsonGetter("status")
    public boolean getStatus ( ) { 
        return this.status;
    }
    
    /** SETTER
     * Status of Call
     */
    @JsonSetter("status")
    public void setStatus (boolean value) { 
        this.status = value;
    }
 
    /** GETTER
     * Location
     */
    @JsonGetter("region")
    public String getRegion ( ) { 
        return this.region;
    }
    
    /** SETTER
     * Location
     */
    @JsonSetter("region")
    public void setRegion (String value) { 
        this.region = value;
    }
 
}
 