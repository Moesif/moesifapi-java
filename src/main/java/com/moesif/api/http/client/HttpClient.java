/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moesif.api.exceptions.APIException;
import com.moesif.api.http.request.HttpBodyRequest;
import com.moesif.api.http.request.HttpRequest;
import com.moesif.api.http.response.HttpResponse;
import java.util.Map;

public interface HttpClient {

    /**
     * Execute a given HttpRequest to get string response back
     * @param   request     The given HttpRequest to execute
     * @param   callBack    Callback after execution
     */
    public void executeAsStringAsync(final HttpRequest request, final APICallBack<HttpResponse> callBack);

    /**
     * Execute a given HttpRequest to get binary response back
     * @param   request     The given HttpRequest to execute
     * @param   callBack    Callback after execution
     */
    public void executeAsBinaryAsync(final HttpRequest request, final APICallBack<HttpResponse> callBack);
    public void executeAsBinaryAsync(final HttpRequest request, final APICallBack<HttpResponse> callBack, boolean debug) throws JsonProcessingException;

    /**
     * Execute a given HttpRequest to get binary response back
     * @param   request     The given HttpRequest to execute
     * @return the HTTP response
     * @throws APIException on error executing request
     */
    public HttpResponse executeAsBinary(final HttpRequest request) throws APIException;

    /**
     * Execute a given HttpRequest to get string response back
     * @param   request     The given HttpRequest to execute
     * @return the HTTP response
     * @throws APIException on error executing request
     */
    public HttpResponse executeAsString(final HttpRequest request) throws APIException;

    /**
     * Create a simple HTTP GET request with basic authentication
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _parameters the query params
     * @param _username for basic auth header
     * @param _password for basic auth header
     * @return the request object
     */
    public HttpRequest get(String _queryUrl,
                    Map<String, String> _headers, Map<String, Object> _parameters,
                    String _username, String _password);

    /**
     * Create a simple HTTP GET request
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _parameters the query params
     * @return the request object
     */
    public HttpRequest get(String _queryUrl,
                    Map<String, String> _headers, Map<String, Object> _parameters);

    /**
     * Create an HTTP POST request with parameters
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _parameters the query params
     * @return the request object
     */
    public HttpRequest post(String _queryUrl,
                     Map<String, String> _headers, Map<String, Object> _parameters);


    /**
     * Create an HTTP POST request with parameters and with basic authentication
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _parameters the query params
     * @param _username for basic auth header
     * @param _password for basic auth header
     * @return the request object
     */
    public HttpRequest post(String _queryUrl,
                     Map<String, String> _headers, Map<String, Object> _parameters,
                     String _username, String _password);

    /**
     * Create an HTTP POST request with body
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _body the request body
     * @return the request object
     */
    public HttpBodyRequest postBody(String _queryUrl,
                             Map<String, String> _headers, String _body);

    /**
     * Create an HTTP POST request with body and with basic authentication
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _body the request body
     * @param _username for basic auth header
     * @param _password for basic auth header
     * @return the request object
     */
    public HttpBodyRequest postBody(String _queryUrl,
                             Map<String, String> _headers, String _body,
                             String _username, String _password);

    /**
     * Create an HTTP PUT request with parameters
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _parameters the request parameters
     * @return the request object
     */
    public HttpRequest put(String _queryUrl,
                    Map<String, String> _headers, Map<String, Object> _parameters);

    /**
     * Create an HTTP PUT request with parameters and with basic authentication
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _parameters the request parameters
     * @param _username for basic auth header
     * @param _password for basic auth header
     * @return the request object
     */
    HttpRequest put(String _queryUrl,
                    Map<String, String> _headers, Map<String, Object> _parameters,
                    String _username, String _password);

    /**
     * Create an HTTP PUT request with body
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _body the request body
     * @return the request object
     */
    HttpBodyRequest putBody(String _queryUrl,
                            Map<String, String> _headers, String _body);

    /**
     * Create an HTTP PUT request with body and with basic authentication
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _body the request body
     * @param _username for basic auth header
     * @param _password for basic auth header
     * @return the request object
     */
    public HttpBodyRequest putBody(String _queryUrl,
                            Map<String, String> _headers, String _body,
                            String _username, String _password);

    /**
     * Create an HTTP PATCH request with parameters
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _parameters the request parameters
     * @return the request object
     */
    public HttpRequest patch(String _queryUrl,
                      Map<String, String> _headers, Map<String, Object> _parameters);

    /**
     * Create an HTTP PATCH request with parameters and with basic authentication
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _parameters the request parameters
     * @param _username for basic auth header
     * @param _password for basic auth header
     * @return the request object
     */
    HttpRequest patch(String _queryUrl,
                      Map<String, String> _headers, Map<String, Object> _parameters,
                      String _username, String _password);

    /**
     * Create an HTTP PATCH request with body
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _body the request body
     * @return the request object
     */
    public HttpBodyRequest patchBody(String _queryUrl,
                              Map<String, String> _headers, String _body);

    /**
     * Create an HTTP PATCH request with body and with basic authentication
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _body the request body
     * @param _username for basic auth header
     * @param _password for basic auth header
     * @return the request object
     */
    public HttpBodyRequest patchBody(String _queryUrl,
                              Map<String, String> _headers, String _body,
                              String _username, String _password);

    /**
     * Create an HTTP DELETE request with parameters
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _parameters the request parameters
     * @return the request object
     */
    HttpRequest delete(String _queryUrl,
                       Map<String, String> _headers, Map<String, Object> _parameters);

    /**
     * Create an HTTP DELETE request with parameters and with basic authentication
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _parameters the request parameters
     * @param _username for basic auth header
     * @param _password for basic auth header
     * @return the request object
     */
    public HttpRequest delete(String _queryUrl,
                       Map<String, String> _headers, Map<String, Object> _parameters,
                       String _username, String _password);

    /**
     * Create an HTTP DELETE request with body
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _body the request body
     * @return the request object
     */
    public HttpBodyRequest deleteBody(String _queryUrl,
                               Map<String, String> _headers, String _body);

    /**
     * Create an HTTP DELETE request with body and with basic authentication
     * @param _queryUrl the URL of the HTTP request
     * @param _headers the HTTP request headers as map
     * @param _body the request body
     * @param _username for basic auth header
     * @param _password for basic auth header
     * @return the request object
     */
    public HttpBodyRequest deleteBody(String _queryUrl,
                               Map<String, String> _headers, String _body,
                               String _username, String _password);
}