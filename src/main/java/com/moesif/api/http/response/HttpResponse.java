/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.http.response;

import java.io.InputStream;
import java.util.Map;
import org.apache.http.client.methods.HttpRequestBase;
public class HttpResponse {
    /**
     * Private store for properties
     */
    int statusCode;
    Map<String, String> headers;
    InputStream rawBody;
    HttpRequestBase baseRequest;    
    /**
     * HTTP Status code of the http response
     * @return the HTTP status code
     */
    public int getStatusCode() { return statusCode; }

    /**
     * Headers of the http response
     * @return the HTTP response headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Raw body of the http response
     * @return the raw HTTP body
     */
    public InputStream getRawBody() {
        return rawBody;
    }

    /**
     * @param _code    The HTTP status code
     * @param _headers The HTTP headers read from response
     * @param _rawBody The raw data returned by the HTTP request
     */
    public HttpResponse(int _code, Map<String, String> _headers, InputStream _rawBody) {
        this.statusCode = _code;
        this.headers = _headers;
        this.rawBody = _rawBody;
    }
    
    /**
     * @param _code    The HTTP status code
     * @param _headers The HTTP headers read from response
     * @param _rawBody The raw data returned by the HTTP request
     * @param _baseReq The underlying http base request from the apache http library
     */
    public HttpResponse(int _code, Map<String, String> _headers, InputStream _rawBody, HttpRequestBase _baseReq) {
        this(_code, _headers, _rawBody);
        this.baseRequest = _baseReq;
    }
 
    /**
     * Shutdowns any pending http resources e.g., input streams and realses connection
     */
    public void shutdown(){
        if(baseRequest != null) {
            baseRequest.releaseConnection();
        }
    }
}