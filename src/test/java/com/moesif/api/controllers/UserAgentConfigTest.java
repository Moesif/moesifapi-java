/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.controllers;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.moesif.api.Configuration;
import com.moesif.api.MoesifAPIClient;
import com.moesif.api.APIHelper;
import com.moesif.api.http.client.APICallBack;
import com.moesif.api.http.client.HttpContext;
import com.moesif.api.http.response.HttpResponse;
import com.moesif.api.models.EventBuilder;
import com.moesif.api.models.EventModel;
import com.moesif.api.models.EventRequestBuilder;
import com.moesif.api.models.EventRequestModel;
import com.moesif.api.testing.HttpCallBackCatcher;
import com.moesif.api.exceptions.APIException;

import org.junit.Test;

/**
 * Test class for User-Agent configuration functionality
 */
public class UserAgentConfigTest extends ControllerTestBase {
    
    private static final String TEST_APPLICATION_ID = EnvVars.readMoesifApplicationId();
    private static final String CUSTOM_USER_AGENT = "MyTestApp/1.0";
    
    /**
     * Build a minimal EventModel sufficient to trigger an outbound request.
     */
    private static EventModel buildSimpleEvent() {
        EventRequestModel eventReq = new EventRequestBuilder()
                .time(new java.util.Date())
                .uri("https://api.acmeinc.com/test")
                .verb("GET")
                .build();

        return new EventBuilder()
                .request(eventReq)
                .build();
    }

    /**
     * Execute createEvent and return the captured User-Agent header.
     */
    private static String captureUserAgent(APIController controller, EventModel event) throws Throwable {
        HttpCallBackCatcher catcher = new HttpCallBackCatcher();
        controller.setHttpCallBack(catcher);

        try {
            controller.createEvent(event);
        } catch (APIException e) {
            // We only care about the request that was sent.
        }

        // Wait for the async callback to populate the context.
        catcher.await(5000, TimeUnit.MILLISECONDS);

        Map<String, String> headers = catcher.getRequest().getHeaders();
        return headers.get("User-Agent");
    }
    
    /**
     * Test default User-Agent header in actual HTTP requests
     */
    @Test
    public void testDefaultUserAgentHeader() throws Throwable {
        MoesifAPIClient client = new MoesifAPIClient(TEST_APPLICATION_ID);
        APIController controller = client.getAPI();
        
        // Execute request and capture UA header.
        String userAgent = captureUserAgent(controller, buildSimpleEvent());
        
        assertNotNull("User-Agent header should be present", userAgent);
        assertTrue("User-Agent should start with library name", 
                userAgent.startsWith("moesifapi-java/"));
        assertFalse("User-Agent should contain version", userAgent.equals("moesifapi-java/"));
    }
    
    /**
     * Test custom User-Agent header via setUserAgent method
     */
    @Test
    public void testCustomUserAgentHeaderViaMethod() throws Throwable {
        MoesifAPIClient client = new MoesifAPIClient(TEST_APPLICATION_ID);
        client.setUserAgent(CUSTOM_USER_AGENT);
        APIController controller = client.getAPI();
        
        // Execute request and capture UA header.
        String userAgent = captureUserAgent(controller, buildSimpleEvent());
        
        assertNotNull("User-Agent header should be present", userAgent);
        assertTrue("User-Agent should start with library name", 
                userAgent.startsWith("moesifapi-java/"));
        assertTrue("User-Agent should contain custom part", 
                userAgent.contains(CUSTOM_USER_AGENT));
        assertTrue("User-Agent should have space separator", 
                userAgent.contains(" " + CUSTOM_USER_AGENT));
    }
    
    /**
     * Test custom User-Agent header via Configuration
     */
    @Test
    public void testCustomUserAgentHeaderViaConfiguration() throws Throwable {
        Configuration config = new Configuration();
        config.applicationId = TEST_APPLICATION_ID;
        config.baseUri = Configuration.DefaultBaseUri;
        config.userAgent = CUSTOM_USER_AGENT;
        
        MoesifAPIClient client = new MoesifAPIClient(config);
        APIController controller = client.getAPI();
        
        // Execute request and capture UA header.
        String userAgent = captureUserAgent(controller, buildSimpleEvent());
        
        assertNotNull("User-Agent header should be present", userAgent);
        assertTrue("User-Agent should start with library name", 
                userAgent.startsWith("moesifapi-java/"));
        assertTrue("User-Agent should contain custom part", 
                userAgent.contains(CUSTOM_USER_AGENT));
        assertTrue("User-Agent should have space separator", 
                userAgent.contains(" " + CUSTOM_USER_AGENT));
    }
} 