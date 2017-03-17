/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

import java.util.*;

public class EventRequestBuilder {
    //the instance to build
    private EventRequestModel eventRequestModel;

    /**
     * Default constructor to initialize the instance
     */
    public EventRequestBuilder() {
        eventRequestModel = new EventRequestModel();
    }

    /**
     * Time when request was made
     */
    public EventRequestBuilder time(Date time) {
        eventRequestModel.setTime(time);
        return this;
    }

    /**
     * full uri of request such as https://www.example.com/my_path?param=1
     */
    public EventRequestBuilder uri(String uri) {
        eventRequestModel.setUri(uri);
        return this;
    }

    /**
     * verb of the API request such as GET or POST
     */
    public EventRequestBuilder verb(String verb) {
        eventRequestModel.setVerb(verb);
        return this;
    }

    /**
     * Key/Value map of request headers
     */
    public EventRequestBuilder headers(Map<String, String> headers) {
        eventRequestModel.setHeaders(headers);
        return this;
    }

    /**
     * Optionally tag the call with your API or App version
     */
    public EventRequestBuilder apiVersion(String apiVersion) {
        eventRequestModel.setApiVersion(apiVersion);
        return this;
    }

    /**
     * IP Address of the client if known.
     */
    public EventRequestBuilder ipAddress(String ipAddress) {
        eventRequestModel.setIpAddress(ipAddress);
        return this;
    }

    /**
     * Request body
     */
    public EventRequestBuilder body(Object body) {
        eventRequestModel.setBody(body);
        return this;
    }

    /**
     * Transfer Encoding of the body such as "base64", null value implies "json" transfer encoding
     */
    public EventRequestBuilder transferEncoding(String transferEncoding) {
        eventRequestModel.setTransferEncoding(transferEncoding);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public EventRequestModel build() {
        return eventRequestModel;
    }
}