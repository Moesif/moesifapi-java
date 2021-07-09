package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GovernanceRulesVariableModel
        implements java.io.Serializable {
    private String name;
    private String path;

    /** GETTER
     * name
     * @return the value
     */
    @JsonGetter("name")
    public String getName ( ) {
        return this.name;
    }

    /** SETTER
     * name
     * @param value the value to set
     */
    @JsonSetter("name")
    public void setName (String value) {
        this.name = value;
    }

    /** GETTER
     * path
     * @return the value
     */
    @JsonGetter("path")
    public String getPath ( ) {
        return this.path;
    }

    /** SETTER
     * path
     * @param value the value to set
     */
    @JsonSetter("path")
    public void setPath (String value) {
        this.path = value;
    }
}
