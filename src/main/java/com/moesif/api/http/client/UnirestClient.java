/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.options.*;

import com.moesif.api.APIHelper;
import com.moesif.api.MoesifAPIClient;
import com.moesif.api.http.client.HttpContext;
import com.moesif.api.http.request.HttpBodyRequest;
import com.moesif.api.http.request.HttpMethod;
import com.moesif.api.http.request.HttpRequest;
import com.moesif.api.http.response.HttpResponse;
import com.moesif.api.http.response.HttpStringResponse;
import com.moesif.api.exceptions.APIException;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Logger;

public class UnirestClient implements HttpClient {
    private static final Logger logger = Logger.getLogger(UnirestClient.class.getName());

    static ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    /**
     * Private variables to implement singleton pattern
     */
    private static Object synRoot = new Object();
    private static HttpClient sharedInstance = null;
    private static String version = APIHelper.getVersion();
    private static String customUserAgent = null;

    /**
     * Singleton access to the shared instance
     * @return A shared instance of UnirestClient
     */
    public static HttpClient getSharedInstance() {
        synchronized (synRoot) {
            if(sharedInstance == null){
                sharedInstance = new UnirestClient();
            }
            return sharedInstance;
        }
    }

    /**
     * Set a custom User-Agent string for HTTP requests
     * @param userAgent The custom User-Agent string to use
     */
    public static void setUserAgent(String userAgent) {
        customUserAgent = userAgent;
    }

    /**
     * Execute a given HttpRequest to get string response back
     * @param   request     The given HttpRequest to execute
     * @param   callBack    Async callback for events
     */
    public void executeAsStringAsync(final HttpRequest request, final APICallBack<HttpResponse> callBack) {
        com.mashape.unirest.request.HttpRequest uniRequest = UnirestClient.convertRequest(request);
        try {
            com.mashape.unirest.http.HttpResponse<String> response = uniRequest.asString();
            UnirestClient.publishResponse(response, request, callBack, null);
        }
        catch  (UnirestException ex) {
            UnirestClient.publishResponse(null, request, callBack, ex);
        }
    }

    /**
     * Execute a given HttpRequest to get binary response back
     * @param   request     The given HttpRequest to execute
     * @param   callBack    Async callback for events
     */
    public void executeAsBinaryAsync(final HttpRequest request, final APICallBack<HttpResponse> callBack) {
        com.mashape.unirest.request.HttpRequest uniRequest = UnirestClient.convertRequest(request);
        try {
            com.mashape.unirest.http.HttpResponse<InputStream> response = uniRequest.asBinary();
            UnirestClient.publishResponse(response, request, callBack, null);
        }
        catch  (UnirestException ex) {
            UnirestClient.publishResponse(null, request, callBack, ex);
        }
    }

    public void executeAsBinaryAsync(final HttpRequest request, final APICallBack<HttpResponse> callBack, boolean debug) throws JsonProcessingException {
        try {
            if(debug) {
                if (request == null) {
                    logger.warning("[DEBUG] requestJson is null | executeAsBinaryAsync");
                } else {
                    String requestJson = objectMapper.writeValueAsString(request);
                    logger.warning("[DEBUG] requestJson : " + requestJson + " | executeAsBinaryAsync");
                }
            }
            com.mashape.unirest.request.HttpRequest uniRequest = UnirestClient.convertRequest(request);

            com.mashape.unirest.http.HttpResponse<InputStream> response = uniRequest.asBinary();

            if(debug){
                if(response == null){
                    logger.warning("[DEBUG] responseJson is null | executeAsBinaryAsync");
                } else {
                    String responseJson = objectMapper.writeValueAsString(response);
                    logger.warning("[DEBUG] responseJson : " + responseJson + " | executeAsBinaryAsync");
                }
            }

            UnirestClient.publishResponse(response, request, callBack, null, debug);
        }
        catch  (UnirestException ex) {
            logger.warning("[DEBUG] UnirestException : " + ex.getMessage() + " | executeAsBinaryAsync");
            UnirestClient.publishResponse(null, request, callBack, ex, debug);
        }
    }

    /**
     * Execute a given HttpRequest to get binary response back
     * @param   request     The given HttpRequest to execute     
     */
    public HttpResponse executeAsBinary(final HttpRequest request) throws APIException {
        com.mashape.unirest.request.HttpRequest uniRequest = UnirestClient.convertRequest(request);
        try {
            com.mashape.unirest.http.HttpResponse<InputStream> response = uniRequest.asBinary();
            return convertResponse(response);
        }
        catch  (UnirestException ex) {
            throw new APIException(ex.getMessage());
        }
    }

    /**
     * Execute a given HttpRequest to get string response back
     * @param   request     The given HttpRequest to execute     
     */
    public HttpResponse executeAsString(final HttpRequest request) throws APIException {
        com.mashape.unirest.request.HttpRequest uniRequest = UnirestClient.convertRequest(request);
        try {
            com.mashape.unirest.http.HttpResponse<String> response = uniRequest.asString();
            return  convertResponse(response);
        }
        catch  (UnirestException ex) {
            throw new APIException(ex.getMessage());
        }
    }

    /**
     * Publishes success or failure result as HttpResponse from a HttpRequest
     * @param   response    The http response to publish
     * @param   request     The http request
     * @param   completionBlock     The success and failure code block reference to invoke the delegate
     * @param   uniException       The reported errors for getting the http response
     */
    protected static void publishResponse (com.mashape.unirest.http.HttpResponse<?> response,
                                 HttpRequest request, APICallBack<HttpResponse> completionBlock, UnirestException uniException)
    {
        HttpResponse httpResponse = ((response == null) ? null : UnirestClient.convertResponse(response));
        HttpContext context = new HttpContext(request, httpResponse);

        //if there are no errors, try to convert to our internal format
        if(uniException == null && httpResponse != null)
        {            
            completionBlock.onSuccess(context, httpResponse);
        }
        else
        {
            Throwable innerException = uniException.getCause();
            completionBlock.onFailure(context, new APIException(innerException.getMessage()));
        }
    }

    protected static void publishResponse (com.mashape.unirest.http.HttpResponse<?> response,
                                           HttpRequest request, APICallBack<HttpResponse> completionBlock, UnirestException uniException, boolean debug) throws JsonProcessingException {
        HttpResponse httpResponse = ((response == null) ? null : UnirestClient.convertResponse(response, debug));
        if(debug){
            if(httpResponse == null){
                logger.warning("[DEBUG] httpResponse is null | publishResponse");
            } else {
                String httpResponseJson = objectMapper.writeValueAsString(httpResponse);
                logger.warning("[DEBUG] httpResponse " + httpResponseJson + " | publishResponse");
            }
        }

        HttpContext context = new HttpContext(request, httpResponse);
        if(debug){
            if(context == null){
                logger.warning("[DEBUG] context is null | publishResponse");
            }
            else{
                String contextJson = objectMapper.writeValueAsString(context);
                logger.warning("[DEBUG] context " + contextJson + " | publishResponse");
            }
        }

        //if there are no errors, try to convert to our internal format
        if(uniException == null && httpResponse != null)
        {
            if(debug){
                logger.warning("[DEBUG] uniException == null && httpResponse != null");
            }
            completionBlock.onSuccess(context, httpResponse);
        }
        else
        {
            Throwable innerException = uniException.getCause();

            if(debug){
                logger.warning("[DEBUG] uniException != null || httpResponse == null | innerException: " + innerException.getMessage());
            }

            completionBlock.onFailure(context, new APIException(innerException.getMessage()));
        }
    }

    /**
     * Converts a given Unirest http response into our internal http response model
     * @param   response    The given unirest http response
     * @return              The converted http response
     */
    public static HttpResponse convertResponse(com.mashape.unirest.http.HttpResponse<?> response)
    {
        HttpResponse httpResponse = null;

        if(null == response)
        {
            return null;
        }
        else if(response.getBody() instanceof String)
        {
            httpResponse = new HttpStringResponse(response.getStatus(),
                    response.getHeaders().getFlatHeaders(), response.getRawBody(), (String)response.getBody());
        }
        else
        {
            httpResponse = new HttpResponse(response.getStatus(), response.getHeaders().getFlatHeaders(),
                    response.getRawBody(), response.getBaseRequest());
        }

        return httpResponse;
    }

    public static HttpResponse convertResponse(com.mashape.unirest.http.HttpResponse<?> response, boolean debug) throws JsonProcessingException {
        HttpResponse httpResponse = null;

        if(null == response)
        {
            if(debug){
                logger.warning("response is null | convertResponse");
            }
            return null;
        }
        else if(response.getBody() instanceof String)
        {
            httpResponse = new HttpStringResponse(response.getStatus(),
                    response.getHeaders().getFlatHeaders(), response.getRawBody(), (String)response.getBody());
            if(debug){
                logger.warning("[DEBUG] response body is string " + response.getBody() + " | convertResponse");
            }

        }
        else
        {
            httpResponse = new HttpResponse(response.getStatus(), response.getHeaders().getFlatHeaders(),
                    response.getRawBody(), response.getBaseRequest());
            if(debug){
                logger.warning("[DEBUG] response is not null and response body is not a string | convertResponse");
            }
        }

        if(debug){
            if(httpResponse == null){
                logger.warning("[DEBUG] convertHttpResponse is null | convertResponse");
            } else {
                String httpResponseJson = objectMapper.writeValueAsString(httpResponse);
                logger.warning("[DEBUG] convertHttpResponse " + httpResponseJson + " | convertResponse");
            }
        }

        return httpResponse;
    }

    /**
     * Converts a given internal http request into unirest http request model
     * @param   request     The given http request in internal format
     * @return              The converted unirest http request
     */
    protected static com.mashape.unirest.request.HttpRequest convertRequest(HttpRequest request)
    {
        com.mashape.unirest.http.HttpMethod uniMethod = UnirestClient.convertHttpMetod(request.getHttpMethod());
        String url = request.getQueryUrl();

        //instantiate unirest request object
        com.mashape.unirest.request.HttpRequestWithBody uniRequest
                = new com.mashape.unirest.request.HttpRequestWithBody(uniMethod, url);

        //set request payload
        if(request instanceof HttpBodyRequest)
        {
            //set request body
            ((com.mashape.unirest.request.HttpRequestWithBody)uniRequest).body(((HttpBodyRequest) request).getBody());
        }
        else
        {
            //set request fields
            uniRequest.fields(request.getParameters());
        }

        //set request headers        
        uniRequest.headers(request.getHeaders());

        // Always include the library identifier as the primary user-agent and
        // optionally append a secondary identifier supplied by the consumer.
        String baseUserAgent = "moesifapi-java/" + UnirestClient.version;
        String userAgent = (customUserAgent != null && !customUserAgent.trim().isEmpty())
                ? baseUserAgent + " " + customUserAgent.trim()
                : baseUserAgent;
        uniRequest.header("User-Agent", userAgent);
        // For test callbacks that inspect HttpRequest headers and down stream extension
        if (request.getHeaders() != null) {
            request.getHeaders().put("User-Agent", userAgent);
        }

        //set json header if needed
        if(request instanceof HttpBodyRequest) {
            uniRequest.header("content-type", "application/json; charset=UTF-8");
        } 

        //set basic auth credentials if needed
        if((null != request.getUsername()) && (!request.getUsername().isEmpty()))
        {
            uniRequest.basicAuth(request.getUsername(), request.getPassword());
        }

        return uniRequest;
    }

    /**
     * Converts a given internal http method enumeration into unirest http method unirest
     * @param   method      The given http method enum in internal format
     * @return              The converted unirest http method enum
     */
    protected static com.mashape.unirest.http.HttpMethod convertHttpMetod(HttpMethod  method)
    {
        switch (method) {
            case POST:
                return com.mashape.unirest.http.HttpMethod.POST;

            case PATCH:
                return com.mashape.unirest.http.HttpMethod.PATCH;

            case PUT:
                return com.mashape.unirest.http.HttpMethod.PUT;

            case DELETE:
                return com.mashape.unirest.http.HttpMethod.DELETE;

            default:
                return com.mashape.unirest.http.HttpMethod.GET;
        }
    }

    /**
     * Create a simple HTTP GET request with basic authentication
     */
    public HttpRequest get(String _queryUrl,
                           Map<String, String> _headers, Map<String, Object> _parameters,
                           String _username, String _password) {
        return new HttpRequest(HttpMethod.GET, _queryUrl, _headers, _parameters, _username, _password);
    }

    /**
     * Create a simple HTTP GET request
     */
    public HttpRequest get(String _queryUrl,
                           Map<String, String> _headers, Map<String, Object> _parameters) {
        return new HttpRequest(HttpMethod.GET, _queryUrl, _headers, _parameters);
    }

    /**
     * Create an HTTP POST request with parameters
     */
    public HttpRequest post(String _queryUrl,
                            Map<String, String> _headers, Map<String, Object> _parameters) {
        return new HttpRequest(HttpMethod.POST, _queryUrl, _headers, _parameters);
    }

    /**
     * Create an HTTP POST request with parameters and with basic authentication
     */
    public HttpRequest post(String _queryUrl,
                            Map<String, String> _headers, Map<String, Object> _parameters,
                            String _username, String _password) {
        return new HttpRequest(HttpMethod.POST, _queryUrl, _headers, _parameters, _username, _password);
    }

    /**
     * Create an HTTP POST request with body
     */
    public HttpBodyRequest postBody(String _queryUrl,
                                    Map<String, String> _headers, String _body) {
        return new HttpBodyRequest(HttpMethod.POST, _queryUrl, _headers, _body);
    }

    /**
     * Create an HTTP POST request with body and with basic authentication
     */
    public HttpBodyRequest postBody(String _queryUrl,
                                    Map<String, String> _headers, String _body,
                                    String _username, String _password) {
        return new HttpBodyRequest(HttpMethod.POST, _queryUrl, _headers, _body, _username, _password);
    }

    /**
     * Create an HTTP PUT request with parameters
     */
    public HttpRequest put(String _queryUrl,
                           Map<String, String> _headers, Map<String, Object> _parameters) {
        return new HttpRequest(HttpMethod.PUT, _queryUrl, _headers, _parameters);
    }

    /**
     * Create an HTTP PUT request with parameters and with basic authentication
     */
    public HttpRequest put(String _queryUrl,
                           Map<String, String> _headers, Map<String, Object> _parameters,
                           String _username, String _password) {
        return new HttpRequest(HttpMethod.PUT, _queryUrl, _headers, _parameters, _username, _password);
    }

    /**
     * Create an HTTP PUT request with body
     */
    public HttpBodyRequest putBody(String _queryUrl,
                                   Map<String, String> _headers, String _body) {
        return new HttpBodyRequest(HttpMethod.PUT, _queryUrl, _headers, _body);
    }

    /**
     * Create an HTTP PUT request with body and with basic authentication
     */
    public HttpBodyRequest putBody(String _queryUrl,
                                   Map<String, String> _headers, String _body,
                                   String _username, String _password) {
        return new HttpBodyRequest(HttpMethod.PUT, _queryUrl, _headers, _body, _username, _password);
    }

    /**
     * Create an HTTP PATCH request with parameters
     */
    public HttpRequest patch(String _queryUrl,
                             Map<String, String> _headers, Map<String, Object> _parameters) {
        return new HttpRequest(HttpMethod.PATCH, _queryUrl, _headers, _parameters);
    }

    /**
     * Create an HTTP PATCH request with parameters and with basic authentication
     */
    public HttpRequest patch(String _queryUrl,
                             Map<String, String> _headers, Map<String, Object> _parameters,
                             String _username, String _password) {
        return new HttpRequest(HttpMethod.PATCH, _queryUrl, _headers, _parameters, _username, _password);
    }

    /**
     * Create an HTTP PATCH request with body
     */
    public HttpBodyRequest patchBody(String _queryUrl,
                                     Map<String, String> _headers, String _body) {
        return new HttpBodyRequest(HttpMethod.PATCH, _queryUrl, _headers, _body);
    }

    /**
     * Create an HTTP PATCH request with body and with basic authentication
     */
    public HttpBodyRequest patchBody(String _queryUrl,
                                     Map<String, String> _headers, String _body,
                                     String _username, String _password) {
        return new HttpBodyRequest(HttpMethod.PATCH, _queryUrl, _headers, _body, _username, _password);
    }

    /**
     * Create an HTTP DELETE request with parameters
     */
    public HttpRequest delete(String _queryUrl,
                              Map<String, String> _headers, Map<String, Object> _parameters) {
        return new HttpRequest(HttpMethod.DELETE, _queryUrl, _headers, _parameters);
    }

    /**
     * Create an HTTP DELETE request with parameters and with basic authentication
     */
    public HttpRequest delete(String _queryUrl,
                              Map<String, String> _headers, Map<String, Object> _parameters,
                              String _username, String _password) {
        return new HttpRequest(HttpMethod.DELETE, _queryUrl, _headers, _parameters, _username, _password);
    }

    /**
     * Create an HTTP DELETE request with body
     */
    public HttpBodyRequest deleteBody(String _queryUrl,
                                      Map<String, String> _headers, String _body) {
        return new HttpBodyRequest(HttpMethod.DELETE, _queryUrl, _headers, _body);
    }

    /**
     * Create an HTTP DELETE request with body and with basic authentication
     */
    public HttpBodyRequest deleteBody(String _queryUrl,
                                      Map<String, String> _headers, String _body,
                                      String _username, String _password) {
        return new HttpBodyRequest(HttpMethod.DELETE, _queryUrl, _headers, _body, _username, _password);
    }
}
