package com.moesif.api.testing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moesif.api.models.ActionModel;
import com.moesif.api.models.ActionRequestModel;
import org.junit.Test;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

// ActionModelTest tests the ActionModel and ActionRequestModel classes more thoroughly
// to validate the new model classes' compatibility
public class ActionModelTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        // Initialize ObjectMapper for JSON serialization
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testActionRequestModelBuilderRequiredFields() {
        // Test that providing null or empty uri throws an exception
        try {
            new ActionRequestModel.Builder(null).build();
            fail("Expected IllegalArgumentException for null uri");
        } catch (IllegalArgumentException e) {
            assertEquals("uri is required", e.getMessage());
        }

        try {
            new ActionRequestModel.Builder("").build();
            fail("Expected IllegalArgumentException for empty uri");
        } catch (IllegalArgumentException e) {
            assertEquals("uri is required", e.getMessage());
        }

        // Test successful creation with required field
        ActionRequestModel requestModel = new ActionRequestModel.Builder("https://example.com").build();
        assertNotNull(requestModel);
    }

    @Test
    public void testActionRequestModelBuilderOptionalFields() {
        // Build ActionRequestModel with all fields
        ActionRequestModel requestModel = new ActionRequestModel.Builder("https://example.com")
                .setUserAgentString("Mozilla/5.0")
                .setIpAddress("192.168.1.1")
                .setTime("2023-10-05T12:34:56Z")
                .build();

        // Serialize to JSON and verify fields
        try {
            String json = objectMapper.writeValueAsString(requestModel);
            assertTrue(json.contains("\"uri\":\"https://example.com\""));
            assertTrue(json.contains("\"userAgentString\":\"Mozilla/5.0\""));
            assertTrue(json.contains("\"ipAddress\":\"192.168.1.1\""));
            assertTrue(json.contains("\"time\":\"2023-10-05T12:34:56Z\""));
        } catch (JsonProcessingException e) {
            fail("JSON serialization failed: " + e.getMessage());
        }
    }

    @Test
    public void testActionModelBuilderRequiredFields() {
        ActionRequestModel requestModel = new ActionRequestModel.Builder("https://example.com").build();

        // Test that providing null or empty actionName throws an exception
        try {
            new ActionModel.Builder(null, requestModel).build();
            fail("Expected IllegalArgumentException for null actionName");
        } catch (IllegalArgumentException e) {
            assertEquals("action_name is required", e.getMessage());
        }

        try {
            new ActionModel.Builder("", requestModel).build();
            fail("Expected IllegalArgumentException for empty actionName");
        } catch (IllegalArgumentException e) {
            assertEquals("action_name is required", e.getMessage());
        }

        // Test that providing null request throws an exception
        try {
            new ActionModel.Builder("Clicked Sign Up", null).build();
            fail("Expected IllegalArgumentException for null request");
        } catch (IllegalArgumentException e) {
            assertEquals("request is required", e.getMessage());
        }

        // Test successful creation with required fields
        ActionModel actionModel = new ActionModel.Builder("Clicked Sign Up", requestModel).build();
        assertNotNull(actionModel);
    }

    @Test
    public void testActionModelBuilderOptionalFields() {
        ActionRequestModel requestModel = new ActionRequestModel.Builder("https://example.com")
                .setUserAgentString("Mozilla/5.0")
                .build();

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("button_label", "Get Started");
        metadata.put("sign_up_method", "Google SSO");

        // Build ActionModel with all fields
        ActionModel actionModel = new ActionModel.Builder("Clicked Sign Up", requestModel)
                .setUserId("12345")
                .setCompanyId("67890")
                .setTransactionId("a3765025-46ec-45dd-bc83-b136c8d1d257")
                .setSessionToken("session-token")
                .setSubscriptionId("subscription-id")
                .setMetadata(metadata)
                .build();

        // Serialize to JSON and verify fields
        try {
            String json = objectMapper.writeValueAsString(actionModel);
            assertTrue(json.contains("\"actionName\":\"Clicked Sign Up\""));
            assertTrue(json.contains("\"userId\":\"12345\""));
            assertTrue(json.contains("\"companyId\":\"67890\""));
            assertTrue(json.contains("\"transactionId\":\"a3765025-46ec-45dd-bc83-b136c8d1d257\""));
            assertTrue(json.contains("\"sessionToken\":\"session-token\""));
            assertTrue(json.contains("\"subscriptionId\":\"subscription-id\""));
            assertTrue(json.contains("\"metadata\":{\"button_label\":\"Get Started\",\"sign_up_method\":\"Google SSO\"}"));
            assertTrue(json.contains("\"request\""));
        } catch (JsonProcessingException e) {
            fail("JSON serialization failed: " + e.getMessage());
        }
    }

    @Test
    public void testActionModelSerialization() {
        ActionRequestModel requestModel = new ActionRequestModel.Builder("https://example.com")
                .setUserAgentString("Mozilla/5.0")
                .build();

        ActionModel actionModel = new ActionModel.Builder("Clicked Sign Up", requestModel)
                .build();

        // Expected JSON
        String expectedJson = "{\"actionName\":\"Clicked Sign Up\",\"request\":{\"uri\":\"https://example.com\",\"userAgentString\":\"Mozilla/5.0\"}}";

        try {
            String json = objectMapper.writeValueAsString(actionModel);
            assertEquals(objectMapper.readTree(expectedJson), objectMapper.readTree(json));
        } catch (JsonProcessingException e) {
            fail("JSON serialization failed: " + e.getMessage());
        }
    }
}
