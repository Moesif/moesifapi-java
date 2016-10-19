/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.http.request;

import com.moesif.api.http.request.HttpMethod;

import java.util.Map;

public class HttpRequest {
    /**
     * Private store for properties
     */
    private HttpMethod httpMethod;
    private Map<String, String> headers;
    private String queryUrl;
    private Map<String, Object> parameters;
    private String username;
    private String password;

    /**
     * Headers for the http request
     */
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    /**
     * Headers for the http request
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Query url for the http request
     */
    public String getQueryUrl() {
        return queryUrl;
    }

    /**
     * Parameters for the http request
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * Username for basic authentication
     */
    public String getUsername() {
        return username;
    }

    /**
     * Password for basic authentication
     */
    public String getPassword() {
        return password;
    }

    /**
     * Initializes a simple http request
     *
     * @param _method     The HTTP method to use. Can be GET, HEAD, PUT, POST, DELETE and PATCH
     * @param _queryUrl   The http url to create the HTTP Request. Expect a fully qualified absolute Url
     * @param _headers    The key-value map of all http headers to be sent
     * @param _parameters The form data values in a key-value map
     * @return Http request initialized with the given method, url and headers
     */
    public HttpRequest(HttpMethod _method, String _queryUrl,
                       Map<String, String> _headers, Map<String, Object> _parameters) {
        this.httpMethod = _method;
        this.queryUrl = _queryUrl;
        this.headers = _headers;
        this.parameters = _parameters;
    }

    /**
     * Initializes a simple http request
     *
     * @param _method     The HTTP method to use. Can be GET, HEAD, PUT, POST, DELETE and PATCH
     * @param _queryUrl   The http url to create the HTTP Request. Expect a fully qualified absolute Url
     * @param _headers    The key-value map of all http headers to be sent
     * @param _parameters The form data values in a key-value map
     * @param _username   Username for basic authentication
     * @param _password   Password for basic authentication
     * @return Http request initialized with the given method, url and headers
     */
    public HttpRequest(HttpMethod _method, String _queryUrl,
                       Map<String, String> _headers, Map<String, Object> _parameters,
                       String _username, String _password) {
        this(_method, _queryUrl, _headers, _parameters);
        this.username = _username;
        this.password = _password;
    }
}
