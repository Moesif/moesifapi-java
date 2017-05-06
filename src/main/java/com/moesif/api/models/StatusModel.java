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
     * @return the value
     */
    @JsonGetter("status")
    public boolean getStatus ( ) { 
        return this.status;
    }
    
    /** SETTER
     * Status of Call
     * @param value the value to set
     */
    @JsonSetter("status")
    public void setStatus (boolean value) { 
        this.status = value;
    }
 
    /** GETTER
     * Location
     * @return the value
     */
    @JsonGetter("region")
    public String getRegion ( ) { 
        return this.region;
    }
    
    /** SETTER
     * Location
     * @param value the value to set
     */
    @JsonSetter("region")
    public void setRegion (String value) { 
        this.region = value;
    }
 
}
 