package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GovernanceRuleRegexConditionModel
        implements java.io.Serializable {
    private String path;
    private String value;

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

    /** GETTER
     * value
     * @return the value
     */
    @JsonGetter("value")
    public String getValue ( ) {
        return this.value;
    }

    /** SETTER
     * value
     * @param value the value to set
     */
    @JsonSetter("value")
    public void setValue (String value) {
        this.value = value;
    }

}
