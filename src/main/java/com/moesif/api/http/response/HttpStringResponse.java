/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.http.response;

import java.io.InputStream;
import java.util.Map;

public class HttpStringResponse extends HttpResponse {
    /**
     * Private store for properties
     */
    private String body;

    /**
     * String body of the http response
     * @return the HTTP response body as a string
     */
    public String getBody() {
        return body;
    }

    /**
     * @param _code    The HTTP status code
     * @param _headers The HTTP headers read from response
     * @param _rawBody The raw data returned by the HTTP request
     * @param body     The HTTP body as a string
     */
    public HttpStringResponse(int _code, Map<String, String> _headers, InputStream _rawBody, String body) {
        super(_code, _headers, _rawBody);
        this.body = body;
    }
}
