/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

public class StatusBuilder {
    //the instance to build
    private StatusModel statusModel;

    /**
     * Default constructor to initialize the instance
     */
    public StatusBuilder() {
        statusModel = new StatusModel();
    }

    /**
     * Status of Call
     * @param status the field to set
     * @return itself
     */
    public StatusBuilder status(boolean status) {
        statusModel.setStatus(status);
        return this;
    }

    /**
     * Location
     * @param region the field to set
     * @return itself
     */
    public StatusBuilder region(String region) {
        statusModel.setRegion(region);
        return this;
    }
    /**
     * Build the instance with the given values
     * @return The built StatusModel
     */
    public StatusModel build() {
        return statusModel;
    }
}