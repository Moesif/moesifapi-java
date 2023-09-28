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
     * Default constructor 
     */     
    private MoesifAPIClient() {

        controller = new APIController();
        config = new Configuration();
        healthController = new HealthController();
	}

    /**
     * Client initialization constructor
     * @param applicationId The Application Id for authentication
     */
    public MoesifAPIClient(String applicationId) {
        this();
        config.applicationId = applicationId;
        config.baseUri = Configuration.BaseUri;
        controller.setConfig(config);
        healthController.setConfig(config);
    }

    /**
     * Client initialization constructor
     * @param applicationId The Application Id for authentication
     */     
    public MoesifAPIClient(String applicationId, String baseUri) {
        this();
        config.applicationId = applicationId;
        config.baseUri = baseUri;
        controller.setConfig(config);
        healthController.setConfig(config);
    }
}