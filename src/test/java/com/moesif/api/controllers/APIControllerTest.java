/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.controllers;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moesif.api.http.client.APICallBack;
import com.moesif.api.http.client.HttpContext;
import com.moesif.api.http.response.HttpResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.moesif.api.models.*;
import com.moesif.api.exceptions.*;
import com.moesif.api.APIHelper;

public class APIControllerTest extends ControllerTestBase {
    
    /**
     * Controller instance (for all tests)
     */
    private static APIController controller;
    
    /**
     * Setup test class
     */
    @BeforeClass
    public static void setUpClass() {
        controller = getClient().getAPI();
    }

    /**
     * Tear down test class
     * @throws IOException
     */
    @AfterClass
    public static void tearDownClass() throws IOException {
        controller = null;
    }

    /**
     * Add Single Event via Injestion API
     * @throws Throwable
     */
    @Test
    public void testAddEvent() throws Throwable {

        // Parameters for the API call
        Map<String, String> reqHeaders = new HashMap<String, String>();
        reqHeaders.put("Host", "api.acmeinc.com");
        reqHeaders.put("Accept", "*/*");
        reqHeaders.put("Connection", "Keep-Alive");
        reqHeaders.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)");
        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.put("Content-Length", "126");
        reqHeaders.put("Accept-Encoding", "gzip");

        Object reqBody = APIHelper.deserialize("{" +
                    "\"items\": [" +
                        "{" +
                            "\"type\": 1," +
                            "\"id\": \"fwfrf\"" +
                        "}," +
                        "{" +
                            "\"type\": 2," +
                             "\"id\": \"d43d3f\"" +
                         "}" +
                    "]" +
                "}");

        Map<String, String> rspHeaders = new HashMap<String, String>();
        rspHeaders.put("Date", "Tue, 23 Feb 2019 23:46:49 GMT");
        rspHeaders.put("Vary", "Accept-Encoding");
        rspHeaders.put("Pragma", "no-cache");
        rspHeaders.put("Expires", "-1");
        rspHeaders.put("Content-Type", "application/json; charset=utf-8");
        rspHeaders.put("Cache-Control","no-cache");

        Object rspBody = APIHelper.deserialize("{" +
                    "\"Error\": \"InvalidArgumentException\"," +
                    "\"Message\": \"Missing field field_a\"" +
                "}");


        EventRequestModel eventReq = new EventRequestBuilder()
                .time(new Date())
                .uri("https://api.acmeinc.com/items/feed/")
                .verb("PATCH")
                .apiVersion("1.1.0")
                .ipAddress("61.48.220.123")
                .headers(reqHeaders)
                .body(reqBody)
                .build();


        EventResponseModel eventRsp = new EventResponseBuilder()
                .time(new Date(System.currentTimeMillis() + 1000))
                .status(500)
                .headers(rspHeaders)
                .body(rspBody)
                .build();

        Map<String, Object> customMetadata = new HashMap<String, Object>();
        Map<String, Object> subObject = new HashMap<String, Object>();
        subObject.put("some_bool", true);
        customMetadata.put("some_string", "value_a");
        customMetadata.put("some_int", 77);
        customMetadata.put("some_obj", subObject);

        EventModel eventModel = new EventBuilder()
                .request(eventReq)
                .response(eventRsp)
                .userId("my_user_id")
                .companyId("my_company_id")
                .sessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f")
                .metadata(customMetadata)
                .build();

        // Set callback and perform API call
        controller.setHttpCallBack(httpResponse);
        try {
        controller.createEvent(eventModel);
        } catch(APIException e) {};

        // Test response code
        assertEquals("Status is not 201",
                201, httpResponse.getResponse().getStatusCode());
    }

    /**
     * Add Single Event Async via Injestion API
     * @throws Throwable
     */
    @Test
    public void testAddEventAsync() throws Throwable {
        final CountDownLatch lock = new CountDownLatch(1);

        // Parameters for the API call
        Map<String, String> reqHeaders = new HashMap<String, String>();
        reqHeaders.put("Host", "api.acmeinc.com");
        reqHeaders.put("Accept", "*/*");
        reqHeaders.put("Connection", "Keep-Alive");
        reqHeaders.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)");
        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.put("Content-Length", "126");
        reqHeaders.put("Accept-Encoding", "gzip");

        Object reqBody = APIHelper.deserialize("{" +
                "\"items\": [" +
                "{" +
                "\"type\": 1," +
                "\"id\": \"fwfrf\"" +
                "}," +
                "{" +
                "\"type\": 2," +
                "\"id\": \"d43d3f\"" +
                "}" +
                "]" +
                "}");

        Map<String, String> rspHeaders = new HashMap<String, String>();
        rspHeaders.put("Date", "Tue, 23 Feb 2019 23:46:49 GMT");
        rspHeaders.put("Vary", "Accept-Encoding");
        rspHeaders.put("Pragma", "no-cache");
        rspHeaders.put("Expires", "-1");
        rspHeaders.put("Content-Type", "application/json; charset=utf-8");
        rspHeaders.put("Cache-Control","no-cache");

        Object rspBody = APIHelper.deserialize("{" +
                "\"Error\": \"InvalidArgumentException\"," +
                "\"Message\": \"Missing field field_a\"" +
                "}");


        EventRequestModel eventReq = new EventRequestBuilder()
                .time(new Date())
                .uri("https://api.acmeinc.com/items/reviews/")
                .verb("PATCH")
                .apiVersion("1.1.0")
                .ipAddress("61.48.220.123")
                .headers(reqHeaders)
                .body(reqBody)
                .build();


        EventResponseModel eventRsp = new EventResponseBuilder()
                .time(new Date(System.currentTimeMillis() + 1000))
                .status(500)
                .headers(rspHeaders)
                .body(rspBody)
                .build();

        EventModel eventModel = new EventBuilder()
                .request(eventReq)
                .response(eventRsp)
                .userId("my_user_id")
                .companyId("my_company_id")
                .sessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f")
                .build();


        APICallBack<HttpResponse> callBack = new APICallBack<HttpResponse>() {
            public void onSuccess(HttpContext context, HttpResponse response) {
                assertEquals("Status is not 201",
                        201, context.getResponse().getStatusCode());
                lock.countDown();
            }

            public void onFailure(HttpContext context, Throwable error) {
                fail();
            }
        };

        controller.createEventAsync(eventModel, callBack);
        assertEquals(true, lock.await(10000, TimeUnit.MILLISECONDS));
    }

    /**
     * Add Batched Events via Ingestion API
     * @throws Throwable
     */
    @Test
    public void testAddBatchedEvents() throws Throwable {
        // Parameters for the API call
        Map<String, String> reqHeaders = new HashMap<String, String>();
        reqHeaders.put("Host", "api.acmeinc.com");
        reqHeaders.put("Accept", "*/*");
        reqHeaders.put("Connection", "Keep-Alive");
        reqHeaders.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)");
        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.put("Content-Length", "126");
        reqHeaders.put("Accept-Encoding", "gzip");

        Object reqBody = APIHelper.deserialize("{" +
                "\"items\": [" +
                "{" +
                "\"type\": 1," +
                "\"id\": \"fwfrf\"" +
                "}," +
                "{" +
                "\"type\": 2," +
                "\"id\": \"d43d3f\"" +
                "}" +
                "]" +
                "}");

        Map<String, String> rspHeaders = new HashMap<String, String>();
        rspHeaders.put("Date", "Tue, 23 Feb 2019 23:46:49 GMT");
        rspHeaders.put("Vary", "Accept-Encoding");
        rspHeaders.put("Pragma", "no-cache");
        rspHeaders.put("Expires", "-1");
        rspHeaders.put("Content-Type", "application/json; charset=utf-8");
        rspHeaders.put("Cache-Control","no-cache");

        Object rspBody = APIHelper.deserialize("{" +
                "\"Error\": \"InvalidArgumentException\"," +
                "\"Message\": \"Missing field field_a\"" +
                "}");


        EventRequestModel eventReq = new EventRequestBuilder()
                .time(new Date())
                .uri("https://api.acmeinc.com/items/feed/")
                .verb("PATCH")
                .apiVersion("1.1.0")
                .ipAddress("61.48.220.123")
                .headers(reqHeaders)
                .body(reqBody)
                .build();


        EventResponseModel eventRsp = new EventResponseBuilder()
                .time(new Date(System.currentTimeMillis() + 1000))
                .status(500)
                .headers(rspHeaders)
                .body(rspBody)
                .build();

        EventModel eventModel = new EventBuilder()
                .request(eventReq)
                .response(eventRsp)
                .userId("my_user_id")
                .companyId("my_company_id")
                .sessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f")
                .build();

        List<EventModel> events = new ArrayList<EventModel>();
        events.add(eventModel);
        events.add(eventModel);
        events.add(eventModel);
        events.add(eventModel);

        // Set callback and perform API call
        controller.setHttpCallBack(httpResponse);
        try {
        controller.createEventsBatch(events);
        } catch(APIException e) {};

        // Test response code
        assertEquals("Status is not 201",
                201, httpResponse.getResponse().getStatusCode());
    }

    /**
     * Add Batched Events Async via Ingestion API
     * @throws Throwable
     */
    @Test
    public void testAddBatchedEventsAsync() throws Throwable {
        final CountDownLatch lock = new CountDownLatch(1);

        // Parameters for the API call
        Map<String, String> reqHeaders = new HashMap<String, String>();
        reqHeaders.put("Host", "api.acmeinc.com");
        reqHeaders.put("Accept", "*/*");
        reqHeaders.put("Connection", "Keep-Alive");
        reqHeaders.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)");
        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.put("Content-Length", "126");
        reqHeaders.put("Accept-Encoding", "gzip");

        Object reqBody = APIHelper.deserialize("{" +
                "\"items\": [" +
                "{" +
                "\"type\": 1," +
                "\"id\": \"fwfrf\"" +
                "}," +
                "{" +
                "\"type\": 2," +
                "\"id\": \"d43d3f\"" +
                "}" +
                "]" +
                "}");

        Map<String, String> rspHeaders = new HashMap<String, String>();
        rspHeaders.put("Date", "Tue, 23 Feb 2019 23:46:49 GMT");
        rspHeaders.put("Vary", "Accept-Encoding");
        rspHeaders.put("Pragma", "no-cache");
        rspHeaders.put("Expires", "-1");
        rspHeaders.put("Content-Type", "application/json; charset=utf-8");
        rspHeaders.put("Cache-Control","no-cache");

        Object rspBody = APIHelper.deserialize("{" +
                "\"Error\": \"InvalidArgumentException\"," +
                "\"Message\": \"Missing field field_a\"" +
                "}");


        EventRequestModel eventReq = new EventRequestBuilder()
                .time(new Date())
                .uri("https://api.acmeinc.com/items/feed/")
                .verb("PATCH")
                .apiVersion("1.1.0")
                .ipAddress("61.48.220.123")
                .headers(reqHeaders)
                .body(reqBody)
                .build();


        EventResponseModel eventRsp = new EventResponseBuilder()
                .time(new Date(System.currentTimeMillis() + 1000))
                .status(500)
                .headers(rspHeaders)
                .body(rspBody)
                .build();

        EventModel eventModel = new EventBuilder()
                .request(eventReq)
                .response(eventRsp)
                .userId("my_user_id")
                .companyId("my_company_id")
                .sessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f")
                .build();

        List<EventModel> events = new ArrayList<EventModel>();
        events.add(eventModel);
        events.add(eventModel);
        events.add(eventModel);
        events.add(eventModel);

        APICallBack<HttpResponse> callBack = new APICallBack<HttpResponse>() {
            public void onSuccess(HttpContext context, HttpResponse response) {
                assertEquals("Status is not 201",
                        201, context.getResponse().getStatusCode());
                lock.countDown();
            }

            public void onFailure(HttpContext context, Throwable error) {
                fail();
            }
        };

        controller.createEventsBatchAsync(events, callBack);
        assertEquals(true, lock.await(10000, TimeUnit.MILLISECONDS));
    }
    @Test
    public void testAddBatchedEventsGzipAsync() throws Throwable {
        final CountDownLatch lock = new CountDownLatch(1);

        // Parameters for the API call
        Map<String, String> reqHeaders = new HashMap<String, String>();
        reqHeaders.put("Host", "api.acmeinc.com");
        reqHeaders.put("Accept", "*/*");
        reqHeaders.put("Connection", "Keep-Alive");
        reqHeaders.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)");
        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.put("Content-Length", "126");
        reqHeaders.put("Accept-Encoding", "gzip");

        Object reqBody = APIHelper.deserialize("{" +
                "\"items\": [" +
                "{" +
                "\"type\": 1," +
                "\"id\": \"fwfrf\"" +
                "}," +
                "{" +
                "\"type\": 2," +
                "\"id\": \"d43d3f\"" +
                "}" +
                "]" +
                "}");

        Map<String, String> rspHeaders = new HashMap<String, String>();
        rspHeaders.put("Date", "Tue, 23 Feb 2019 23:46:49 GMT");
        rspHeaders.put("Vary", "Accept-Encoding");
        rspHeaders.put("Pragma", "no-cache");
        rspHeaders.put("Expires", "-1");
        rspHeaders.put("Content-Type", "application/json; charset=utf-8");
        rspHeaders.put("Cache-Control","no-cache");

        Object rspBody = APIHelper.deserialize("{" +
                "\"Error\": \"InvalidArgumentException\"," +
                "\"Message\": \"Missing field field_a\"" +
                "}");


        EventRequestModel eventReq = new EventRequestBuilder()
                .time(new Date())
                .uri("https://api.acmeinc.com/items/feed/")
                .verb("PATCH")
                .apiVersion("1.1.0")
                .ipAddress("61.48.220.123")
                .headers(reqHeaders)
                .body(reqBody)
                .build();


        EventResponseModel eventRsp = new EventResponseBuilder()
                .time(new Date(System.currentTimeMillis() + 1000))
                .status(500)
                .headers(rspHeaders)
                .body(rspBody)
                .build();


        APICallBack<HttpResponse> callBack = new APICallBack<HttpResponse>() {
            public void onSuccess(HttpContext context, HttpResponse response) {
                assertEquals("Status is not 201",
                        201, context.getResponse().getStatusCode());
                lock.countDown();
            }

            public void onFailure(HttpContext context, Throwable error) {
                fail();
            }
        };

        EventModel eventModelForGzip = new EventBuilder()
                .request(eventReq)
                .response(eventRsp)
                .userId("my_user_id4gzip")
                .companyId("my_company_id4gzip")
                .sessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f")
                .build();

        List<EventModel> events4Gzip = new ArrayList<EventModel>();
        events4Gzip.add(eventModelForGzip);
        events4Gzip.add(eventModelForGzip);
        events4Gzip.add(eventModelForGzip);
        events4Gzip.add(eventModelForGzip);

        controller.createEventsBatchAsync(events4Gzip, callBack, true);
        assertEquals(true, lock.await(10000, TimeUnit.MILLISECONDS));

    }

    /**
     * Update Single User via Injestion API
     * @throws Throwable
     */
    @Test
    public void testUpdateUser() throws Throwable {

        CampaignModel campaign = new CampaignBuilder()
        .utmSource("Newsletter")
        .utmMedium("Email")
        .build();

        UserModel user = new UserBuilder()
            .userId("12345")
            .companyId("67890")
            .modifiedTime(new Date())
            .ipAddress("29.80.250.240")
            .sessionToken("di3hd982h3fubv3yfd94egf")
            .userAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
            .metadata(APIHelper.deserialize("{" +
                "\"email\": \"johndoe@acmeinc.com\"," +
                "\"string_field\": \"value_1\"," +
                "\"number_field\": 0," +
                "\"object_field\": {" +
                "\"field_1\": \"value_1\"," +
                "\"field_2\": \"value_2\"" +
                "}" +
                "}"))
            .campaign(campaign)
            .build();

        // Set callback and perform API call
        controller.setHttpCallBack(httpResponse);
        try {
            controller.updateUser(user);
        } catch(APIException e) {};

        // Test response code
        assertEquals("Status is not 201",
                201, httpResponse.getResponse().getStatusCode());
    }

    /**
     * Update Single User Async via Injestion API
     * @throws Throwable
     */
    @Test
    public void testUpdateUserAsync() throws Throwable {
        final CountDownLatch lock = new CountDownLatch(1);

        UserModel user = new UserBuilder()
            .userId("12345")
            .companyId("67890")
            .modifiedTime(new Date())
            .ipAddress("29.80.250.240")
            .sessionToken("di3hd982h3fubv3yfd94egf")
            .userAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
            .metadata(APIHelper.deserialize("{" +
                    "\"email\": \"johndoe@acmeinc.com\"," +
                    "\"string_field\": \"value_1\"," +
                    "\"number_field\": 0," +
                    "\"object_field\": {" +
                    "\"field_1\": \"value_1\"," +
                    "\"field_2\": \"value_2\"" +
                    "}" +
                    "}"))
            .build();

        APICallBack<HttpResponse> callBack = new APICallBack<HttpResponse>() {
            public void onSuccess(HttpContext context, HttpResponse response) {
                assertEquals("Status is not 201",
                        201, context.getResponse().getStatusCode());
                lock.countDown();
            }

            public void onFailure(HttpContext context, Throwable error) {
                fail();
            }
        };

        controller.updateUserAsync(user, callBack);
        assertEquals(true, lock.await(10000, TimeUnit.MILLISECONDS));
    }

    /**
     * Update Batched Users via Ingestion API
     * @throws Throwable
     */
    @Test
    public void testUpdateBatchedUsers() throws Throwable {

        // Parameters for the API call
        List<UserModel> users = new ArrayList<UserModel>();

        UserModel userA = new UserBuilder()
                .userId("12345")
                .companyId("67890")
                .metadata(APIHelper.deserialize("{" +
                        "\"email\": \"johndoe@acmeinc.com\"," +
                        "\"first_name\": \"John\"," +
                        "\"last_name\": \"Doe\"," +
                        "\"title\": \"Software Engineer\"," +
                        "\"sales_info\": {" +
                        "\"stage\": \"Customer\"," +
                        "\"lifetime_value\": 24000," +
                        "\"account_owner\": \"mary@contoso.com\"" +
                        "}" +
                        "}"))
                .build();

        users.add(userA);

        UserModel userB = new UserBuilder()
            .userId("54321")
            .companyId("67890")
            .metadata(APIHelper.deserialize("{" +
                    "\"email\": \"johndoe@acmeinc.com\"," +
                    "\"first_name\": \"John\"," +
                    "\"last_name\": \"Doe\"," +
                    "\"title\": \"Software Engineer\"," +
                    "\"sales_info\": {" +
                    "\"stage\": \"Customer\"," +
                    "\"lifetime_value\": 24000," +
                    "\"account_owner\": \"mary@contoso.com\"" +
                    "}" +
                    "}"))
            .build();
        users.add(userB);

        // Set callback and perform API call
        controller.setHttpCallBack(httpResponse);
        try {
            controller.updateUsersBatch(users);
        } catch(APIException e) {};

        // Test response code
        assertEquals("Status is not 201",
                201, httpResponse.getResponse().getStatusCode());
    }

    /**
     * Update Batched Users Async via Ingestion API
     * @throws Throwable
     */
    @Test
    public void testUpdateBatchedUsersAsync() throws Throwable {
        final CountDownLatch lock = new CountDownLatch(1);

        // Parameters for the API call
        List<UserModel> users = new ArrayList<UserModel>();

        UserModel userA = new UserBuilder()
                .userId("12345")
                .companyId("67890")
                .metadata(APIHelper.deserialize("{" +
                        "\"email\": \"johndoe@acmeinc.com\"," +
                        "\"first_name\": \"John\"," +
                        "\"last_name\": \"Doe\"," +
                        "\"title\": \"Software Engineer\"," +
                        "\"sales_info\": {" +
                        "\"stage\": \"Customer\"," +
                        "\"lifetime_value\": 24000," +
                        "\"account_owner\": \"mary@contoso.com\"" +
                        "}" +
                        "}"))
                .build();
        users.add(userA);

        UserModel userB = new UserBuilder()
                .userId("54321")
                .companyId("67890")
                .metadata(APIHelper.deserialize("{" +
                        "\"email\": \"johndoe@acmeinc.com\"," +
                        "\"first_name\": \"John\"," +
                        "\"last_name\": \"Doe\"," +
                        "\"title\": \"Software Engineer\"," +
                        "\"sales_info\": {" +
                        "\"stage\": \"Customer\"," +
                        "\"lifetime_value\": 24000," +
                        "\"account_owner\": \"mary@contoso.com\"" +
                        "}" +
                        "}"))
                .build();
        users.add(userB);


        APICallBack<HttpResponse> callBack = new APICallBack<HttpResponse>() {
            public void onSuccess(HttpContext context, HttpResponse response) {
                assertEquals("Status is not 201",
                        201, context.getResponse().getStatusCode());
                lock.countDown();
            }

            public void onFailure(HttpContext context, Throwable error) {
                fail();
            }
        };

        controller.updateUsersBatchAsync(users, callBack);
        assertEquals(true, lock.await(10000, TimeUnit.MILLISECONDS));
    }

    
    /**
     * Get Application Config via Injestion API
     * @throws Throwable
     */
    @Test
    public void testGetAppConfig() throws Throwable {
    	// Set callback and perform API call
        controller.setHttpCallBack(httpResponse);

        try {
        controller.getAppConfig();
        } catch(APIException e) {};

        // Test response code
        assertEquals("Status is not 200", 200, httpResponse.getResponse().getStatusCode());
        // Test the header x-moesif-config-etag
        assertNotNull(httpResponse.getResponse().getHeaders().get("x-moesif-config-etag"));
        // Test the raw body
        InputStream bodyIs = httpResponse.getResponse().getRawBody();
        assertNotNull(bodyIs);
        AppConfigModel appConfig = controller.parseAppConfigModel(bodyIs);
        bodyIs.close();
        assertNotNull(appConfig);

    }

    /**
     * Get Application Config via Injestion Aysync API
     * @throws Throwable
     */
    @Test
    public void testGetAppConfigAsync() throws Throwable {
        final CountDownLatch lock = new CountDownLatch(1);

        APICallBack<HttpResponse> callBack = new APICallBack<HttpResponse>() {
            public void onSuccess(HttpContext context, HttpResponse response) {
                // Read the response body
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> jsonMap = null;
                AppConfigModel model = null;

                try {
                    model = mapper.readValue(context.getResponse().getRawBody(), AppConfigModel.class);
                } catch (Exception e) {
                    System.out.println("Invalid Json input");
                }

                System.out.println("App Config Model returned is " + model);

                assertEquals("Status is not 200",
                        200, context.getResponse().getStatusCode());

                assertNotNull(context.getResponse().getHeaders().get("x-moesif-config-etag"));
                assertNotNull(context.getResponse().getRawBody());
                lock.countDown();

            }

            @Override
            public void onFailure(HttpContext context, Throwable error) {
                // nothing
            }
        };

        try {
            controller.getAppConfigAsync(callBack);
        } catch(Exception e) {};
        assertEquals(true, lock.await(10000, TimeUnit.MILLISECONDS));
    }

    /**
     * Get Governance Rules via Injestion API
     * @throws Throwable
     */
    @Test
    public void testGovernanceRules() throws Throwable {
        // Set callback and perform API call
        controller.setHttpCallBack(httpResponse);

        try {
            controller.getGovernanceRules();
        } catch(APIException e) {};

        // Test response code
        assertEquals("Status is not 200", 200, httpResponse.getResponse().getStatusCode());
        // Test the header x-moesif-config-etag
        assertNotNull(httpResponse.getResponse().getHeaders().get("x-moesif-rules-tag"));
        // Test the raw body
        InputStream bodyIs = httpResponse.getResponse().getRawBody();
        assertNotNull(bodyIs);
        List<GovernanceRulesModel> rules = controller.parseGovernanceRulesModel(bodyIs);
        bodyIs.close();
        assertNotNull(rules);

    }

    /**
     * Get Governance Rules via Injection Aysnc API
     * @throws Throwable
     */
    @Test
    public void testGetGovernanceRulesAsync() throws Throwable {
        final CountDownLatch lock = new CountDownLatch(1);

        APICallBack<HttpResponse> callBack = new APICallBack<HttpResponse>() {
            public void onSuccess(HttpContext context, HttpResponse response) {
                // Read the response body
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> jsonMap = null;
                List<GovernanceRulesModel> rules = null;

                try {
                    rules = mapper.readValue(context.getResponse().getRawBody(), new TypeReference<List<GovernanceRulesModel>>(){});
                } catch (Exception e) {
                    System.out.println("Invalid Json input");
                }

                System.out.println("Governance Rule Model returned is " + rules);

                assertEquals("Status is not 200",
                        200, context.getResponse().getStatusCode());

                assertNotNull(context.getResponse().getHeaders().get("x-moesif-rules-tag"));
                assertNotNull(context.getResponse().getRawBody());
                lock.countDown();

            }

            @Override
            public void onFailure(HttpContext context, Throwable error) {
                // nothing
            }
        };

        try {
            controller.getGovernanceRulesAsync(callBack);
        } catch(Exception e) {};
        assertEquals(true, lock.await(10000, TimeUnit.MILLISECONDS));
    }

    /**
     * Test user sample rate is being used correctly
     * @throws Throwable
     */
    @Test
    public void testUserAndCompanySampleRate() {

        AppConfigModel appConfigModel = new AppConfigModel();
        appConfigModel.setOrgId("100:0");
        appConfigModel.setAppId("10:0");
        appConfigModel.setUserSampleRate(new HashMap<String, Integer>() {
            {
                put("12345", 10); put("user2", 11);
            }
        });
        appConfigModel.setCompanySampleRate(new HashMap<String, Integer>() {
            {
                put("67890", 90); put("c2", 99);
            }
        });
        EventModel eventModel1 = new EventBuilder()
                .request(null)
                .response(null)
                .userId("12345")
                .companyId("67890")
                .build();

        EventModel eventModel2 = new EventBuilder()
                .request(null)
                .response(null)
                .companyId("67890")
                .build();


        assertEquals(10, controller.getSampleRateToUse(eventModel1,appConfigModel));
        assertEquals(90, controller.getSampleRateToUse(eventModel2, appConfigModel));
    }


    /**
     * Test regex sample rate is being used correctly
     * @throws Throwable
     */
    @Test
    public void testRegexSampleRate() {

        AppConfigModel appConfigModel = new AppConfigModel();
        appConfigModel.setOrgId("100:0");
        appConfigModel.setAppId("10:0");
        RegexConfigModel regexConfigModel = new RegexConfigModel();
        regexConfigModel.sampeleRate = 12;
        GovernanceRuleRegexConditionModel regexConditionModel = new GovernanceRuleRegexConditionModel();
        regexConditionModel.setPath("response.status");
        regexConditionModel.setValue("400");
        regexConfigModel.conditions = new ArrayList<>();
        regexConfigModel.conditions.add(regexConditionModel);
        List<RegexConfigModel> regexConfig = new ArrayList<>();
        regexConfig.add(regexConfigModel);
        appConfigModel.setRegex_config(regexConfig);


        EventResponseModel eventResponseModel = new EventResponseModel();
        eventResponseModel.setStatus(400);
        EventModel eventModel1 = new EventBuilder()
                .response(eventResponseModel)
                .build();

        EventResponseModel eventResponseModel1 = new EventResponseModel();
        eventResponseModel1.setStatus(200);
        EventModel eventModel2 = new EventBuilder()
                .response(eventResponseModel1)
                .build();

        assertEquals(12, controller.getSampleRateToUse(eventModel1, appConfigModel));
        assertEquals(100, controller.getSampleRateToUse(eventModel2, appConfigModel));
    }

    /**
	 * Update Single Company via Injestion API
	 * @throws Throwable
	 */
    @Test
    public void testUpdateCompany() throws Throwable {

      CampaignModel campaign = new CampaignBuilder()
          .utmSource("Adwords")
          .utmMedium("Twitter")
          .build();

      CompanyModel company = new CompanyBuilder()
          .companyId("67890")
          .modifiedTime(new Date())
          .metadata(APIHelper.deserialize("{" +
              "\"email\": \"johndoe@acmeinc.com\"," +
              "\"string_field\": \"value_1\"," +
              "\"number_field\": 0," +
              "\"object_field\": {" +
              "\"field_1\": \"value_1\"," +
              "\"field_2\": \"value_2\"" +
              "}" +
              "}"))
          .campaign(campaign)
          .build();

      // Set callback and perform API call
      controller.setHttpCallBack(httpResponse);
      try {
          controller.updateCompany(company);
      } catch(APIException e) {};

      // Test response code
      assertEquals("Status is not 201",
              201, httpResponse.getResponse().getStatusCode());
  }
    
    /**
     * Update Single Company Async via Injestion API
     * @throws Throwable
     */
    @Test
    public void testUpdateCompanyAsync() throws Throwable {
        final CountDownLatch lock = new CountDownLatch(1);

        CompanyModel company = new CompanyBuilder()
            .companyId("67890")
            .metadata(APIHelper.deserialize("{" +
                    "\"email\": \"johndoe@acmeinc.com\"," +
                    "\"string_field\": \"value_1\"," +
                    "\"number_field\": 0," +
                    "\"object_field\": {" +
                    "\"field_1\": \"value_1\"," +
                    "\"field_2\": \"value_2\"" +
                    "}" +
                    "}"))
            .build();

        APICallBack<HttpResponse> callBack = new APICallBack<HttpResponse>() {
            public void onSuccess(HttpContext context, HttpResponse response) {
                assertEquals("Status is not 201",
                        201, context.getResponse().getStatusCode());
                lock.countDown();
            }

            public void onFailure(HttpContext context, Throwable error) {
                fail();
            }
        };

        controller.updateCompanyAsync(company, callBack);
        assertEquals(true, lock.await(10000, TimeUnit.MILLISECONDS));
    }
    
    /**
     * Update Batched Companies via Ingestion API
     * @throws Throwable
     */
    @Test
    public void testUpdateBatchedCompanies() throws Throwable {

        // Parameters for the API call
        List<CompanyModel> companies = new ArrayList<CompanyModel>();

        CompanyModel companyA = new CompanyBuilder()
            .companyId("67890")
            .modifiedTime(new Date())
            .ipAddress("29.80.250.240")
            .companyDomain("moesif")
            .build();
        companies.add(companyA);

        CompanyModel companyB = new CompanyBuilder()
            .companyId("67890")
            .modifiedTime(new Date())
            .ipAddress("21.80.11.242")
            .sessionToken("zceadckekvsfgfpsakvnbfouavsdvds")
            .companyDomain("moesif")
            .metadata(APIHelper.deserialize("{" +
                    "\"email\": \"johndoe@acmeinc.com\"," +
                    "\"string_field\": \"value_1\"," +
                    "\"number_field\": 0," +
                    "\"object_field\": {" +
                    "\"field_1\": \"value_1\"," +
                    "\"field_2\": \"value_2\"" +
                    "}" +
                    "}"))
            .build();
        companies.add(companyB);

        // Set callback and perform API call
        controller.setHttpCallBack(httpResponse);
        try {
            controller.updateCompaniesBatch(companies);
        } catch(APIException e) {};

        // Test response code
        assertEquals("Status is not 201",
                201, httpResponse.getResponse().getStatusCode());
    }
    
    /**
     * Update Batched Companies Async via Ingestion API
     * @throws Throwable
     */
    @Test
    public void testUpdateBatchedCompaniesAsync() throws Throwable {
        final CountDownLatch lock = new CountDownLatch(1);

        // Parameters for the API call
        List<CompanyModel> companies = new ArrayList<CompanyModel>();

        CompanyModel companyA = new CompanyBuilder()
                .companyId("67890")
                .modifiedTime(new Date())
                .companyDomain("moesif")
                .build();
        companies.add(companyA);

        CompanyModel companyB = new CompanyBuilder()
        		.companyId("67890")
                .modifiedTime(new Date())
                .companyDomain("moesif")
                .metadata(APIHelper.deserialize("{" +
                        "\"email\": \"johndoe@acmeinc.com\"," +
                        "\"string_field\": \"value_1\"," +
                        "\"number_field\": 0," +
                        "\"object_field\": {" +
                        "\"field_1\": \"value_1\"," +
                        "\"field_2\": \"value_2\"" +
                        "}" +
                        "}"))
                .build();
        companies.add(companyB);


        APICallBack<HttpResponse> callBack = new APICallBack<HttpResponse>() {
            public void onSuccess(HttpContext context, HttpResponse response) {
                assertEquals("Status is not 201",
                        201, context.getResponse().getStatusCode());
                lock.countDown();
            }

            public void onFailure(HttpContext context, Throwable error) {
                fail();
            }
        };

        controller.updateCompaniesBatchAsync(companies, callBack);
        assertEquals(true, lock.await(10000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void shouldBlockonUserRule() throws Throwable {
        String appConfigJson = "{\"org_id\":\"640:128\",\"app_id\":\"617:473\",\"sample_rate\":99,\"block_bot_traffic\":false,\"user_sample_rate\":{\"azure_user_id\":100,\"tyk-bearer-token\":100,\"basic-auth-test\":100,\"abc\":60,\"masked_user_id\":100,\"keyur@moesif.com\":100,\"385\":99,\"tyk-basic-auth\":100,\"outgoing_user_id\":90,\"tyk-user\":100,\"keyur\":100,\"nginxapiuser\":95,\"Jason\":100,\"8ce866a1-1ba1-47ec-9130-f046bd8e3df5\":99,\"1234\":0,\"tyk-jwt-token\":100,\"jwt-token\":100,\"2d45bf73-bfa2-4b0a-918a-ee4010dfb5a3\":92,\"dev_user_id\":100,\"12345\":100,\"7ab8b13c-866d-4587-b99d-a8166391171b\":94,\"OAuth\":100,\"my_user_id\":97,\"bearer-token\":100,\"deva-1\":70},\"company_sample_rate\":{\"67890\":98,\"34\":100,\"12\":100,\"8\":100,\"678\":100,\"40\":100,\"9\":100,\"26\":100,\"123\":82,\"37\":100,\"13\":100,\"46\":100,\"24\":100,\"16\":100,\"48\":100,\"43\":100,\"32\":100,\"36\":100,\"39\":100,\"47\":100,\"20\":100,\"27\":100,\"2\":100,\"azure_company_id\":100,\"18\":100,\"3\":100,\"undefined\":80},\"user_rules\":{\"masked_user_id\":[{\"rules\":\"5f4910ab5f09092bd0e4ec79\",\"values\":{\"8\":\"body.phone\",\"4\":\"San Francisco\",\"5\":\"body.title\",\"1\":\"company_id\",\"0\":\"masked_user_id\",\"2\":\"name\",\"7\":\"body.age\",\"3\":\"2021-01-08T19:05:38.482Z\"}}]},\"company_rules\":{\"tyk-company\":[{\"rules\":\"5f49118a5f09092bd0e4ec7a\",\"values\":{\"0\":\"tyk-company\",\"1\":\"2020-11-02T20:22:42.845Z\",\"2\":\"body.age\",\"3\":\"campaign.utm_term\"}}],\"azure_company_id\":[{\"rules\":\"5f49118a5f09092bd0e4ec7a\",\"values\":{\"0\":\"azure_company_id\",\"1\":\"2020-08-28T15:11:32.402Z\",\"2\":\"42\",\"3\":\"campaign.utm_term\"}}]},\"ip_addresses_blocked_by_name\":{},\"regex_config\":[],\"billing_config_jsons\":{}}";
        String governaceJson = "[{\"_id\":\"5f4910ab5f09092bd0e4ec79\",\"created_at\":\"2020-08-28T14:11:55.117\",\"org_id\":\"640:128\",\"app_id\":\"617:473\",\"name\":\"First User Rule\",\"block\":true,\"type\":\"user\",\"variables\":[{\"name\":\"0\",\"path\":\"user_id\"},{\"name\":\"1\",\"path\":\"company_id\"},{\"name\":\"2\",\"path\":\"name\"},{\"name\":\"3\",\"path\":\"created\"},{\"name\":\"4\",\"path\":\"geo_ip.city_name\"},{\"name\":\"5\",\"path\":\"body.title\"},{\"name\":\"7\",\"path\":\"body.age\"},{\"name\":\"8\",\"path\":\"body.phone\"}],\"response\":{\"status\":400,\"headers\":{\"X-Company-Id\":\"{{1}}\",\"X-City\":\"{{4}}\",\"X-Created\":\"{{3}}\",\"X-Avg\":\"{{7}} / {{8}}\",\"X-User-Id\":\"{{0}}\"},\"body\":{\"test\":{\"nested\":{\"msg\":\"At {{4}} on {{3}} we {{2}} did this - {{5}}\"}}}}},{\"_id\":\"5f49118a5f09092bd0e4ec7a\",\"created_at\":\"2020-08-28T14:15:38.611\",\"org_id\":\"640:128\",\"app_id\":\"617:473\",\"name\":\"First Company Rule\",\"block\":true,\"type\":\"company\",\"variables\":[{\"name\":\"0\",\"path\":\"company_id\"},{\"name\":\"1\",\"path\":\"created\"},{\"name\":\"2\",\"path\":\"body.age\"},{\"name\":\"3\",\"path\":\"campaign.utm_term\"}],\"response\":{\"status\":500,\"headers\":{\"X-Company-Id\":\"{{0}}\",\"X-Created\":\"{{1}}\",\"X-Age\":\"{{2}}\",\"X-Term\":\"{{3}}\"},\"body\":\"This is a string example for - {{0}}, at {{1}}\"}}]";
        AppConfigModel appConfig = APIHelper.deserialize(appConfigJson, AppConfigModel.class);
        List<GovernanceRulesModel> rules = APIHelper.deserialize(governaceJson, new TypeReference<List<GovernanceRulesModel>>(){});

        EventRequestModel requestModel = new EventRequestModel();
        requestModel.setUri("https://www.google.com/search");
        requestModel.setVerb("GET");
        requestModel.setTime(new Date());
        requestModel.setHeaders(new HashMap<String, String>());
        EventModel eventModel = new EventModel();
        eventModel.setRequest(requestModel);
        eventModel.setUserId("masked_user_id");
        eventModel.setDirection("Outgoing");
        assertTrue(controller.isBlockedByGovernanceRules(eventModel, rules, appConfig));
        assertEquals("5f4910ab5f09092bd0e4ec79", eventModel.getBlockedBy());

    }

    @Test
    public void shouldBlockonCompanyRule() throws Throwable {
        String appConfigJson = "{\"org_id\":\"640:128\",\"app_id\":\"617:473\",\"sample_rate\":99,\"block_bot_traffic\":false,\"user_sample_rate\":{\"azure_user_id\":100,\"tyk-bearer-token\":100,\"basic-auth-test\":100,\"abc\":60,\"masked_user_id\":100,\"keyur@moesif.com\":100,\"385\":99,\"tyk-basic-auth\":100,\"outgoing_user_id\":90,\"tyk-user\":100,\"keyur\":100,\"nginxapiuser\":95,\"Jason\":100,\"8ce866a1-1ba1-47ec-9130-f046bd8e3df5\":99,\"1234\":0,\"tyk-jwt-token\":100,\"jwt-token\":100,\"2d45bf73-bfa2-4b0a-918a-ee4010dfb5a3\":92,\"dev_user_id\":100,\"12345\":100,\"7ab8b13c-866d-4587-b99d-a8166391171b\":94,\"OAuth\":100,\"my_user_id\":97,\"bearer-token\":100,\"deva-1\":70},\"company_sample_rate\":{\"67890\":98,\"34\":100,\"12\":100,\"8\":100,\"678\":100,\"40\":100,\"9\":100,\"26\":100,\"123\":82,\"37\":100,\"13\":100,\"46\":100,\"24\":100,\"16\":100,\"48\":100,\"43\":100,\"32\":100,\"36\":100,\"39\":100,\"47\":100,\"20\":100,\"27\":100,\"2\":100,\"azure_company_id\":100,\"18\":100,\"3\":100,\"undefined\":80},\"user_rules\":{\"masked_user_id\":[{\"rules\":\"5f4910ab5f09092bd0e4ec79\",\"values\":{\"8\":\"body.phone\",\"4\":\"San Francisco\",\"5\":\"body.title\",\"1\":\"company_id\",\"0\":\"masked_user_id\",\"2\":\"name\",\"7\":\"body.age\",\"3\":\"2021-01-08T19:05:38.482Z\"}}]},\"company_rules\":{\"tyk-company\":[{\"rules\":\"5f49118a5f09092bd0e4ec7a\",\"values\":{\"0\":\"tyk-company\",\"1\":\"2020-11-02T20:22:42.845Z\",\"2\":\"body.age\",\"3\":\"campaign.utm_term\"}}],\"azure_company_id\":[{\"rules\":\"5f49118a5f09092bd0e4ec7a\",\"values\":{\"0\":\"azure_company_id\",\"1\":\"2020-08-28T15:11:32.402Z\",\"2\":\"42\",\"3\":\"campaign.utm_term\"}}]},\"ip_addresses_blocked_by_name\":{},\"regex_config\":[],\"billing_config_jsons\":{}}";
        String governaceJson = "[{\"_id\":\"5f4910ab5f09092bd0e4ec79\",\"created_at\":\"2020-08-28T14:11:55.117\",\"org_id\":\"640:128\",\"app_id\":\"617:473\",\"name\":\"First User Rule\",\"block\":true,\"type\":\"user\",\"variables\":[{\"name\":\"0\",\"path\":\"user_id\"},{\"name\":\"1\",\"path\":\"company_id\"},{\"name\":\"2\",\"path\":\"name\"},{\"name\":\"3\",\"path\":\"created\"},{\"name\":\"4\",\"path\":\"geo_ip.city_name\"},{\"name\":\"5\",\"path\":\"body.title\"},{\"name\":\"7\",\"path\":\"body.age\"},{\"name\":\"8\",\"path\":\"body.phone\"}],\"response\":{\"status\":400,\"headers\":{\"X-Company-Id\":\"{{1}}\",\"X-City\":\"{{4}}\",\"X-Created\":\"{{3}}\",\"X-Avg\":\"{{7}} / {{8}}\",\"X-User-Id\":\"{{0}}\"},\"body\":{\"test\":{\"nested\":{\"msg\":\"At {{4}} on {{3}} we {{2}} did this - {{5}}\"}}}}},{\"_id\":\"5f49118a5f09092bd0e4ec7a\",\"created_at\":\"2020-08-28T14:15:38.611\",\"org_id\":\"640:128\",\"app_id\":\"617:473\",\"name\":\"First Company Rule\",\"block\":true,\"type\":\"company\",\"variables\":[{\"name\":\"0\",\"path\":\"company_id\"},{\"name\":\"1\",\"path\":\"created\"},{\"name\":\"2\",\"path\":\"body.age\"},{\"name\":\"3\",\"path\":\"campaign.utm_term\"}],\"response\":{\"status\":500,\"headers\":{\"X-Company-Id\":\"{{0}}\",\"X-Created\":\"{{1}}\",\"X-Age\":\"{{2}}\",\"X-Term\":\"{{3}}\"},\"body\":\"This is a string example for - {{0}}, at {{1}}\"}}]";
        AppConfigModel appConfig = APIHelper.deserialize(appConfigJson, AppConfigModel.class);
        List<GovernanceRulesModel> rules = APIHelper.deserialize(governaceJson, new TypeReference<List<GovernanceRulesModel>>(){});

        EventRequestModel requestModel = new EventRequestModel();
        requestModel.setUri("https://www.google.com/search");
        requestModel.setVerb("GET");
        requestModel.setTime(new Date());
        requestModel.setHeaders(new HashMap<String, String>());
        EventModel eventModel = new EventModel();
        eventModel.setRequest(requestModel);
        eventModel.setCompanyId("tyk-company");
        eventModel.setDirection("Outgoing");
        assertTrue(controller.isBlockedByGovernanceRules(eventModel, rules, appConfig));
        assertEquals("5f49118a5f09092bd0e4ec7a", eventModel.getBlockedBy());
        assertEquals(500, eventModel.getResponse().getStatus());

    }

    @Test
    public void shouldNotBlockonWhenNoGovernanceRule() throws Throwable {
        assertFalse(controller.isBlockedByGovernanceRules(new EventModel(), new ArrayList<>(), controller.getDefaultAppConfig()));
    }

    @Test
    public void shouldBlockonRegexRule() throws Throwable {
        String governanceJson = "[{\"_id\":\"62f69205ec701a4f0400a377\",\"created_at\":\"2022-08-12T17:46:45.670\",\"org_id\":\"421:67\",\"app_id\":\"46:73\",\"name\":\"my gov\",\"block\":true,\"type\":\"company\",\"regex_config\":[{\"conditions\":[{\"path\":\"request.verb\",\"value\":\"POST\"}]}],\"response\":{\"status\":203,\"headers\":{},\"body\":{\"ok\":1}}},{\"_id\":\"62f6c661ac3331776950eba1\",\"created_at\":\"2022-08-12T21:30:09.523\",\"org_id\":\"421:67\",\"app_id\":\"46:73\",\"name\":\"my regex\",\"block\":true,\"type\":\"regex\",\"regex_config\":[{\"conditions\":[{\"path\":\"request.verb\",\"value\":\"POST\"}]}],\"response\":{\"status\":203,\"headers\":{},\"body\":{\"ok\":1}}},{\"_id\":\"62fd061e51f905712d73f72d\",\"created_at\":\"2022-08-17T15:15:42.909\",\"org_id\":\"421:67\",\"app_id\":\"46:73\",\"name\":\"user\",\"block\":true,\"type\":\"user\",\"regex_config\":[{\"conditions\":[{\"path\":\"request.body.operationName\",\"value\":\"Get.*\"}]}],\"response\":{\"status\":203,\"headers\":{},\"body\":{\"error\":\"this is a test\"}}},{\"_id\":\"62fe6f3bf199ee4cf35762d7\",\"created_at\":\"2022-08-18T16:56:27.767\",\"org_id\":\"421:67\",\"app_id\":\"46:73\",\"name\":\"company rule 2\",\"block\":true,\"type\":\"company\",\"regex_config\":[{\"conditions\":[{\"path\":\"request.verb\",\"value\":\"DELETE\"}]}],\"response\":{\"status\":401,\"headers\":{},\"body\":{\"error\":\"test\"}}},{\"_id\":\"62ffc2e77a9aca1bfdefc3e3\",\"created_at\":\"2022-08-19T17:05:43.321\",\"org_id\":\"421:67\",\"app_id\":\"46:73\",\"name\":\"graphql2\",\"block\":true,\"type\":\"regex\",\"regex_config\":[{\"conditions\":[{\"path\":\"request.body.query\",\"value\":\".*Get.*\"}]}],\"response\":{\"status\":401,\"headers\":{},\"body\":{\"test\":\"graph2\"}}},{\"_id\":\"62fff3367a9aca1bfdefc3f1\",\"created_at\":\"2022-08-19T20:31:50.765\",\"org_id\":\"421:67\",\"app_id\":\"46:73\",\"name\":\"company rule no regex\",\"block\":true,\"type\":\"company\",\"regex_config\":[],\"response\":{\"status\":402,\"headers\":{},\"body\":{\"status\":\"make payment\"}}},{\"_id\":\"6317c7ba63501c63e3ff4ee0\",\"created_at\":\"2022-09-06T22:20:42.060\",\"org_id\":\"421:67\",\"app_id\":\"46:73\",\"name\":\"user rule\",\"block\":true,\"type\":\"user\",\"variables\":[{\"name\":\"0\",\"path\":\"cohort_names\"},{\"name\":\"1\",\"path\":\"created\"},{\"name\":\"2\",\"path\":\"identified_user_id\"},{\"name\":\"3\",\"path\":\"company_id\"}],\"regex_config\":[],\"response\":{\"status\":204,\"headers\":{\"my header\":\"{{0}}\",\"my header2\":\"{{1}}\",\"header3\":\"{{2}}\"},\"body\":{\"yes\":true,\"{{3}}\":\"yes\"}}}]";
        String appConfigJson = "{\"org_id\":\"421:67\",\"app_id\":\"46:73\",\"sample_rate\":100,\"block_bot_traffic\":false,\"user_sample_rate\":{\"sean-user-11\":70,\"sean-user-10\":70,\"sean-user-9\":70},\"company_sample_rate\":{\"sean-company-5\":50,\"67890\":50,\"sean-company-9\":50,\"sean-company-6\":50,\"sean-company-10\":50,\"sean-company-11\":50,\"company_1234\":50},\"user_rules\":{\"sean-user-11\":[{\"rules\":\"6317c7ba63501c63e3ff4ee0\",\"values\":{\"0\":\"cohort_names\",\"1\":\"created\",\"2\":\"identified_user_id\",\"3\":\"sean-company-11\"}}],\"sean-user-10\":[{\"rules\":\"6317c7ba63501c63e3ff4ee0\",\"values\":{\"0\":\"cohort_names\",\"1\":\"created\",\"2\":\"identified_user_id\",\"3\":\"sean-company-10\"}}],\"sean-user-9\":[{\"rules\":\"6317c7ba63501c63e3ff4ee0\",\"values\":{\"0\":\"cohort_names\",\"1\":\"created\",\"2\":\"identified_user_id\",\"3\":\"sean-company-9\"}}]},\"company_rules\":{\"sean-company-5\":[{\"rules\":\"62fe6f3bf199ee4cf35762d7\"},{\"rules\":\"62fff3367a9aca1bfdefc3f1\"}],\"67890\":[{\"rules\":\"62fe6f3bf199ee4cf35762d7\"},{\"rules\":\"62fff3367a9aca1bfdefc3f1\"}],\"sean-company-9\":[{\"rules\":\"62fe6f3bf199ee4cf35762d7\"},{\"rules\":\"62fff3367a9aca1bfdefc3f1\"}],\"sean-company-6\":[{\"rules\":\"62fe6f3bf199ee4cf35762d7\"},{\"rules\":\"62fff3367a9aca1bfdefc3f1\"}],\"sean-company-10\":[{\"rules\":\"62fe6f3bf199ee4cf35762d7\"},{\"rules\":\"62fff3367a9aca1bfdefc3f1\"}],\"sean-company-11\":[{\"rules\":\"62fe6f3bf199ee4cf35762d7\"},{\"rules\":\"62fff3367a9aca1bfdefc3f1\"}],\"company_1234\":[{\"rules\":\"62fe6f3bf199ee4cf35762d7\"},{\"rules\":\"62fff3367a9aca1bfdefc3f1\"}]},\"ip_addresses_blocked_by_name\":{},\"regex_config\":[{\"conditions\":[{\"path\":\"request.verb\",\"value\":\"post\"}],\"sample_rate\":20}],\"billing_config_jsons\":{}}";
        AppConfigModel appConfig = APIHelper.deserialize(appConfigJson, AppConfigModel.class);
        List<GovernanceRulesModel> rules = APIHelper.deserialize(governanceJson, new TypeReference<List<GovernanceRulesModel>>(){});


        EventRequestModel requestModel = new EventRequestModel();
        requestModel.setUri("https://www.google.com/search");
        requestModel.setVerb("POST");
        requestModel.setTime(new Date());
        requestModel.setHeaders(new HashMap<String, String>());
        EventModel eventModel = new EventModel();
        eventModel.setRequest(requestModel);

        assertTrue(controller.isBlockedByGovernanceRules(eventModel, rules, appConfig));
        assertEquals("62f6c661ac3331776950eba1", eventModel.getBlockedBy());
        assertEquals(203, eventModel.getResponse().getStatus());

    }

    @Test
    public void shouldRespsectRegexRules() throws Exception {
        String appConfigJson = "{\"org_id\":\"640:128\",\"app_id\":\"487:163\",\"sample_rate\":80,\"block_bot_traffic\":false,\"user_sample_rate\":{},\"company_sample_rate\":{},\"user_rules\":{},\"company_rules\":{},\"ip_addresses_blocked_by_name\":{},\"regex_config\":[{\"conditions\":[{\"path\":\"request.verb\",\"value\":\"GET\"}],\"sample_rate\":90}],\"billing_config_jsons\":{}}";
        String governanceJson = "[{\"_id\":\"631d6d065ef7bb0f43ccd3f8\",\"created_at\":\"2022-09-11T05:07:18.679\",\"org_id\":\"640:128\",\"app_id\":\"487:163\",\"name\":\"regex 1\",\"block\":true,\"type\":\"regex\",\"regex_config\":[{\"conditions\":[{\"path\":\"request.route\",\"value\":\"/api/*\"},{\"path\":\"request.verb\",\"value\":\"POST\"}]},{\"conditions\":[{\"path\":\"request.ip_address\",\"value\":\"120.110.10.11\"}]}],\"response\":{\"status\":401,\"headers\":{},\"body\":{\"test\":1}}}]";
        AppConfigModel appConfig = APIHelper.deserialize(appConfigJson, AppConfigModel.class);
        List<GovernanceRulesModel> rules = APIHelper.deserialize(governanceJson, new TypeReference<List<GovernanceRulesModel>>(){});


        EventRequestModel eventReq = new EventRequestModel();
        eventReq.setUri("https://localhost:5001/api/Employee");
        eventReq.setVerb("Get");


        EventModel eventModel = new EventModel();
        eventModel.setRequest(eventReq);

        assertFalse(controller.isBlockedByGovernanceRules(eventModel, rules, appConfig));


        eventReq.setVerb("GET");
        eventReq.setIpAddress("120.110.10.11");
        assertTrue(controller.isBlockedByGovernanceRules(eventModel, rules, appConfig));

    }
}
