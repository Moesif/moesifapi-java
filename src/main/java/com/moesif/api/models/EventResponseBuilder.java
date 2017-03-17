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
     */
    public EventResponseBuilder time(Date time) {
        eventResponseModel.setTime(time);
        return this;
    }

    /**
     * HTTP Status code such as 200
     */
    public EventResponseBuilder status(int status) {
        eventResponseModel.setStatus(status);
        return this;
    }

    /**
     * Key/Value map of response headers
     */
    public EventResponseBuilder headers(Map<String, String> headers) {
        eventResponseModel.setHeaders(headers);
        return this;
    }

    /**
     * Response body
     */
    public EventResponseBuilder body(Object body) {
        eventResponseModel.setBody(body);
        return this;
    }

    /**
     * Transfer Encoding of the body such as "base64", null value implies "json" transfer encoding
     */
    public EventResponseBuilder transferEncoding(String transferEncoding) {
        eventResponseModel.setTransferEncoding(transferEncoding);
        return this;
    }

    /**
     * IP Address from the response, such as the server IP Address
     */
    public EventResponseBuilder ipAddress(String ipAddress) {
        eventResponseModel.setIpAddress(ipAddress);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public EventResponseModel build() {
        return eventResponseModel;
    }
}