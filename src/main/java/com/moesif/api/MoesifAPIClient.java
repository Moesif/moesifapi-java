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
     * Singleton access to API controller
     * @return	Returns the APIController instance
     */
    public APIController getAPI() {
        return APIController.getInstance();
    }

    /**
     * Singleton access to Health controller
     * @return	Returns the HealthController instance 
     */
    public HealthController getHealth() {
        return HealthController.getInstance();
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
	}

    /**
     * Client initialization constructor
     * @param applicationId The Application Id for authentication
     */     
    public MoesifAPIClient(String applicationId) {
        this();
        Configuration.ApplicationId = applicationId;
    }
}