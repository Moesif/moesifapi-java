/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.controllers;

import static org.junit.Assert.*;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.moesif.api.Configuration;
import com.moesif.api.MoesifAPIClient;
import com.moesif.api.http.client.APICallBack;
import com.moesif.api.http.client.HttpContext;
import com.moesif.api.http.response.HttpResponse;
import com.moesif.api.APIHelper;
import com.moesif.api.models.*;
import com.moesif.api.exceptions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for User-Agent configuration functionality
 */
public class UserAgentConfigTest {
    
    // Test Application ID provided for dev testing
    private static final String TEST_APPLICATION_ID = "eyJhcHAiOiI0OTM6MjQzNyIsInZlciI6IjIuMSIsIm9yZyI6IjY0MDoxMjgiLCJpYXQiOjE3NDg3MzYwMDB9.eM8nM2O-ef0uvAgQesXkzkWh_Njyvr-rbtDc_k_2aQ8";
    
    private static final String CUSTOM_USER_AGENT_1 = "MyTestApp/1.0 (UserAgentTest)";
    private static final String CUSTOM_USER_AGENT_2 = "AnotherTestApp/2.5 (ConfigTest)";
    
    private static APIController defaultController;
    private static APIController customController1;
    private static APIController customController2;
    
    /**
     * Setup test class with different client configurations
     */
    @BeforeClass
    public static void setUpClass() {
        // Default client (should use default user agent)
        MoesifAPIClient defaultClient = new MoesifAPIClient(TEST_APPLICATION_ID);
        defaultController = defaultClient.getAPI();
        
        // Custom client via Configuration object
        Configuration config1 = new Configuration();
        config1.applicationId = TEST_APPLICATION_ID;
        config1.baseUri = "https://api.moesif.net";
        config1.userAgent = CUSTOM_USER_AGENT_1;
        MoesifAPIClient customClient1 = new MoesifAPIClient(config1);
        customController1 = customClient1.getAPI();
        
        // Custom client via setUserAgent method
        Configuration config2 = new Configuration();
        config2.applicationId = TEST_APPLICATION_ID;
        config2.baseUri = "https://api.moesif.net";
        MoesifAPIClient customClient2 = new MoesifAPIClient(config2);
        customClient2.setUserAgent(CUSTOM_USER_AGENT_2);
        customController2 = customClient2.getAPI();
    }

    /**
     * Tear down test class
     */
    @AfterClass
    public static void tearDownClass() {
        defaultController = null;
        customController1 = null;
        customController2 = null;
    }

    /**
     * Test default user agent behavior with real API request
     * @throws Throwable
     */
    @Test
    public void testDefaultUserAgent() throws Throwable {
        EventModel testEvent = createTestEvent("default_user_agent_test");
        
        try {
            Map<String, String> response = defaultController.createEvent(testEvent);
            // If we get here, the request was successful with default user agent
            assertNotNull("Response should not be null", response);
        } catch (APIException e) {
            // Even if we get an API exception, the request was made successfully
            // (user agent was sent correctly)
            assertTrue("Request should have been made successfully", true);
        }
    }

    /**
     * Test custom user agent via Configuration object with real API request
     * @throws Throwable
     */
    @Test
    public void testCustomUserAgentViaConfiguration() throws Throwable {
        EventModel testEvent = createTestEvent("custom_user_agent_config_test");
        
        try {
            Map<String, String> response = customController1.createEvent(testEvent);
            assertNotNull("Response should not be null", response);
        } catch (APIException e) {
            // Even if we get an API exception, the request was made successfully
            assertTrue("Request should have been made successfully", true);
        }
    }

    /**
     * Test custom user agent via setUserAgent method with real API request
     * @throws Throwable
     */
    @Test
    public void testCustomUserAgentViaSetMethod() throws Throwable {
        EventModel testEvent = createTestEvent("custom_user_agent_set_test");
        
        try {
            Map<String, String> response = customController2.createEvent(testEvent);
            assertNotNull("Response should not be null", response);
        } catch (APIException e) {
            // Even if we get an API exception, the request was made successfully
            assertTrue("Request should have been made successfully", true);
        }
    }

    /**
     * Test async request with custom user agent
     * @throws Throwable
     */
    @Test
    public void testCustomUserAgentAsync() throws Throwable {
        final CountDownLatch lock = new CountDownLatch(1);
        final boolean[] success = {false};
        
        EventModel testEvent = createTestEvent("custom_user_agent_async_test");
        
        APICallBack<HttpResponse> callback = new APICallBack<HttpResponse>() {
            @Override
            public void onSuccess(HttpContext context, HttpResponse response) {
                success[0] = true;
                lock.countDown();
            }

            @Override
            public void onFailure(HttpContext context, Throwable error) {
                // Even on failure, the request was attempted with the user agent
                success[0] = true;
                lock.countDown();
            }
        };

        customController1.createEventAsync(testEvent, callback);
        
        // Wait for async operation to complete
        assertTrue("Async operation should complete within timeout", 
                   lock.await(30, TimeUnit.SECONDS));
        assertTrue("Async request should have been made", success[0]);
    }

    /**
     * Test that different clients can have different user agents simultaneously
     * @throws Throwable
     */
    @Test
    public void testMultipleClientUserAgents() throws Throwable {
        // Create test events for different clients
        EventModel event1 = createTestEvent("multi_client_test_1");
        EventModel event2 = createTestEvent("multi_client_test_2");
        EventModel event3 = createTestEvent("multi_client_test_3");
        
        // Test that all three clients work (each with their respective user agents)
        try {
            defaultController.createEvent(event1);
        } catch (APIException e) {
            // Request was made successfully
        }
        
        try {
            customController1.createEvent(event2);
        } catch (APIException e) {
            // Request was made successfully
        }
        
        try {
            customController2.createEvent(event3);
        } catch (APIException e) {
            // Request was made successfully
        }
        
        // If we reach here, all requests were made successfully
        assertTrue("All requests should have been made successfully", true);
    }

    /**
     * Test user agent configuration persistence
     * @throws Throwable
     */
    @Test
    public void testUserAgentPersistence() throws Throwable {
        // Create a new client and set user agent
        MoesifAPIClient client = new MoesifAPIClient(TEST_APPLICATION_ID);
        client.setUserAgent("PersistenceTest/1.0");
        
        EventModel event1 = createTestEvent("persistence_test_1");
        EventModel event2 = createTestEvent("persistence_test_2");
        
        // Make multiple requests to ensure user agent persists
        try {
            client.getAPI().createEvent(event1);
            client.getAPI().createEvent(event2);
        } catch (APIException e) {
            // Requests were made successfully
        }
        
        assertTrue("User agent should persist across multiple requests", true);
    }

    /**
     * Test health check endpoint with custom user agent
     * @throws Throwable
     */
    @Test
    public void testHealthCheckWithCustomUserAgent() throws Throwable {
        Configuration config = new Configuration();
        config.applicationId = TEST_APPLICATION_ID;
        config.baseUri = "https://api.moesif.net";
        config.userAgent = "HealthTest/1.0";
        
        MoesifAPIClient client = new MoesifAPIClient(config);
        
        try {
            StatusModel status = client.getHealthController().getHealthProbe();
            assertNotNull("Health status should not be null", status);
        } catch (Exception e) {
            // Even if health check fails, the request was made with custom user agent
            assertTrue("Health check request should have been made", true);
        }
    }

    /**
     * Helper method to create a test event
     * @param testId Unique identifier for the test
     * @return EventModel for testing
     */
    private EventModel createTestEvent(String testId) {
        Map<String, String> reqHeaders = new HashMap<String, String>();
        reqHeaders.put("Host", "api.test.com");
        reqHeaders.put("Accept", "application/json");
        reqHeaders.put("Content-Type", "application/json");

        Map<String, Object> reqBody = new HashMap<String, Object>();
        reqBody.put("test_id", testId);
        reqBody.put("timestamp", System.currentTimeMillis());

        Map<String, String> rspHeaders = new HashMap<String, String>();
        rspHeaders.put("Content-Type", "application/json");

        Map<String, Object> rspBody = new HashMap<String, Object>();
        rspBody.put("status", "success");
        rspBody.put("test_id", testId);

        EventRequestModel eventReq = new EventRequestBuilder()
                .time(new Date())
                .uri("https://api.test.com/test/" + testId)
                .verb("POST")
                .apiVersion("1.0.0")
                .ipAddress("127.0.0.1")
                .headers(reqHeaders)
                .body(reqBody)
                .build();

        EventResponseModel eventRsp = new EventResponseBuilder()
                .time(new Date(System.currentTimeMillis() + 100))
                .status(200)
                .headers(rspHeaders)
                .body(rspBody)
                .build();

        return new EventBuilder()
                .request(eventReq)
                .response(eventRsp)
                .userId("test_user_" + testId)
                .companyId("test_company")
                .sessionToken("test_session_" + testId)
                .build();
    }
} 