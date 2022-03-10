package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

class GovernanceRuleCohortModel {
    public String Id;

    /** GETTER
     * Id
     * @return the value
     */
    @JsonGetter("id")
    public String getId ( ) {
        return this.Id;
    }

    /** SETTER
     * Id
     * @param value the value to set
     */
    @JsonSetter("id")
    public void setId (String value) {
        this.Id = value;
    }
}
