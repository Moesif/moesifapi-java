/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api;

import com.moesif.api.controllers.*;
import com.moesif.api.http.client.HttpClient;

public class MoesifAPIClient {
    /**
     * @return	Returns the APIController instance
     */
    public APIController getAPI() {
        return controller;
    }

    private APIController controller;

    private Configuration config;

    private HealthController healthController;

    public HealthController getHealthController() {
        return healthController;
    }


    /**
     * Get the shared http client currently being used for API calls
     * @return The http client instance currently being used
     */
    public HttpClient getSharedHttpClient() {
        return BaseController.getClientInstance();
    }
    
    /**
     * Set a shared http client to be used for API calls
     * @param httpClient The http client to use
     */
    public void setSharedHttpClient(HttpClient httpClient) {
        BaseController.setClientInstance(httpClient);
    }

    /**
     * Set a custom User-Agent string for HTTP requests
     * @param userAgent The custom User-Agent string to use
     */
    public void setUserAgent(String userAgent) {
        config.userAgent = userAgent;
        com.moesif.api.http.client.UnirestClient.setUserAgent(userAgent);
    }


    /**
     * Client initialization constructor
     * @param applicationId The Application Id for authentication
     */
    public MoesifAPIClient(String applicationId) {
        this(applicationId, Configuration.DefaultBaseUri);
    }

    /**
     * Client initialization constructor
     * @param applicationId The Application Id for authentication
     * @param baseUri The base Uri for API calls
     */     
    public MoesifAPIClient(String applicationId, String baseUri) {
        this(applicationId, baseUri, false);
    }

    /**
     * Client initialization constructor
     * @param applicationId The Application Id for authentication
     * @param baseUri The base Uri for API calls
     * @param debug flag to enable debug logs
     */
    public MoesifAPIClient(String applicationId, String baseUri, boolean debug) {
        config = new Configuration();
        config.applicationId = applicationId;
        config.baseUri = baseUri;
        config.debug = debug;
        controller = new APIController(config);
        healthController = new HealthController(config);
    }

    /**
     * Client initialization constructor
     * @param configParam
     */

    public MoesifAPIClient(Configuration configParam) {
        config = configParam;
        controller = new APIController(config);
        healthController = new HealthController(config);
    }
}