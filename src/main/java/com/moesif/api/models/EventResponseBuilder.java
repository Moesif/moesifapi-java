/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import java.util.*;

public class EventResponseBuilder {
    //the instance to build
    private EventResponseModel eventResponseModel;

    /**
     * Default constructor to initialize the instance
     */
    public EventResponseBuilder() {
        eventResponseModel = new EventResponseModel();
    }

    /**
     * Time when response received
     * @param time the field to set
     * @return itself
     */
    public EventResponseBuilder time(Date time) {
        eventResponseModel.setTime(time);
        return this;
    }

    /**
     * HTTP Status code such as 200
     * @param status the field to set
     * @return itself
     */
    public EventResponseBuilder status(int status) {
        eventResponseModel.setStatus(status);
        return this;
    }

    /**
     * Key/Value map of response headers
     * @param headers the field to set
     * @return itself
     */
    public EventResponseBuilder headers(Map<String, String> headers) {
        eventResponseModel.setHeaders(headers);
        return this;
    }

    /**
     * Response body
     * @param body the field to set
     * @return itself
     */
    public EventResponseBuilder body(Object body) {
        eventResponseModel.setBody(body);
        return this;
    }

    /**
     * Transfer Encoding of the body such as "base64", null value implies "json" transfer encoding
     * @param transferEncoding the field to set
     * @return itself
     */
    public EventResponseBuilder transferEncoding(String transferEncoding) {
        eventResponseModel.setTransferEncoding(transferEncoding);
        return this;
    }

    /**
     * IP Address from the response, such as the server IP Address
     * @param ipAddress the field to set
     * @return itself
     */
    public EventResponseBuilder ipAddress(String ipAddress) {
        eventResponseModel.setIpAddress(ipAddress);
        return this;
    }
    /**
     * Build the instance with the given values
     * @return The built EventResponseModel
     */
    public EventResponseModel build() {
        return eventResponseModel;
    }
}