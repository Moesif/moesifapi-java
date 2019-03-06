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

import com.moesif.api.http.client.APICallBack;
import com.moesif.api.http.client.HttpContext;
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
        rspHeaders.put("Date", "Tue, 23 Feb 2017 23:46:49 GMT");
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
        rspHeaders.put("Date", "Tue, 23 Feb 2017 23:46:49 GMT");
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
                .sessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f")
                .build();


        APICallBack<Object> callBack = new APICallBack<Object>() {
            public void onSuccess(HttpContext context, Object response) {
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
        rspHeaders.put("Date", "Tue, 23 Feb 2017 23:46:49 GMT");
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
        rspHeaders.put("Date", "Tue, 23 Feb 2017 23:46:49 GMT");
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
                .sessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f")
                .build();

        List<EventModel> events = new ArrayList<EventModel>();
        events.add(eventModel);
        events.add(eventModel);
        events.add(eventModel);
        events.add(eventModel);

        APICallBack<Object> callBack = new APICallBack<Object>() {
            public void onSuccess(HttpContext context, Object response) {
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

    /**
     * Update Single User via Injestion API
     * @throws Throwable
     */
    @Test
    public void testUpdateUser() throws Throwable {

        UserModel user = new UserBuilder()
            .userId("12345")
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

        APICallBack<Object> callBack = new APICallBack<Object>() {
            public void onSuccess(HttpContext context, Object response) {
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
        users.add(userA);

        UserModel userB = new UserBuilder()
            .userId("56789")
            .modifiedTime(new Date())
            .ipAddress("21.80.11.242")
            .sessionToken("zceadckekvsfgfpsakvnbfouavsdvds")
            .userAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
            .metadata(APIHelper.deserialize("{" +
                    "\"email\": \"maryjane@acmeinc.com\"," +
                    "\"string_field\": \"value_1\"," +
                    "\"number_field\": 1," +
                    "\"object_field\": {" +
                    "\"field_1\": \"value_1\"," +
                    "\"field_2\": \"value_2\"" +
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
        users.add(userA);

        UserModel userB = new UserBuilder()
                .userId("56789")
                .modifiedTime(new Date())
                .ipAddress("21.80.11.242")
                .sessionToken("zceadckekvsfgfpsakvnbfouavsdvds")
                .userAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                .metadata(APIHelper.deserialize("{" +
                        "\"email\": \"maryjane@acmeinc.com\"," +
                        "\"string_field\": \"value_1\"," +
                        "\"number_field\": 1," +
                        "\"object_field\": {" +
                        "\"field_1\": \"value_1\"," +
                        "\"field_2\": \"value_2\"" +
                        "}" +
                        "}"))
                .build();
        users.add(userB);


        APICallBack<Object> callBack = new APICallBack<Object>() {
            public void onSuccess(HttpContext context, Object response) {
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
        assertNotNull(httpResponse.getResponse().getRawBody());
    }
    
	/**
	 * Update Single Company via Injestion API
	 * @throws Throwable
	 */
    @Test
    public void testUpdateCompany() throws Throwable {

      CompanyModel company = new CompanyBuilder()
          .companyId("1")
          .modifiedTime(new Date())
          .ipAddress("29.80.250.240")
          .sessionToken("di3hd982h3fubv3yfd94egf")
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
            .companyId("1")
            .modifiedTime(new Date())
            .ipAddress("29.80.250.240")
            .sessionToken("di3hd982h3fubv3yfd94egf")
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

        APICallBack<Object> callBack = new APICallBack<Object>() {
            public void onSuccess(HttpContext context, Object response) {
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
    		.companyId("1")
            .modifiedTime(new Date())
            .ipAddress("29.80.250.240")
            .companyDomain("moesif")
            .build();
        companies.add(companyA);

        CompanyModel companyB = new CompanyBuilder()
            .companyId("2")
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
                .companyId("1")
                .modifiedTime(new Date())
                .companyDomain("moesif")
                .build();
        companies.add(companyA);

        CompanyModel companyB = new CompanyBuilder()
        		.companyId("2")
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


        APICallBack<Object> callBack = new APICallBack<Object>() {
            public void onSuccess(HttpContext context, Object response) {
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
}
