/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.controllers;

import static org.junit.Assert.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.fasterxml.jackson.core.type.TypeReference;

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
        Object reqHeaders = APIHelper.deserialize("{" +
                    "\"Host\": \"api.acmeinc.com\"," +
                    "\"Accept\": \"*/*\"," +
                    "\"Connection\": \"Keep-Alive\"," +
                    "\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\"," +
                    "\"Content-Type\": \"application/json\"," +
                    "\"Content-Length\": \"126\"," +
                    "\"Accept-Encoding\": \"gzip\"" +
                "}");

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

        Object rspHeaders = APIHelper.deserialize("{" +
                    "\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\"," +
                    "\"Vary\": \"Accept-Encoding\"," +
                    "\"Pragma\": \"no-cache\"," +
                    "\"Expires\": \"-1\"," +
                    "\"Content-Type\": \"application/json; charset=utf-8\"," +
                    "\"Cache-Control\": \"no-cache\"" +
                "}");

        Object rspBody = APIHelper.deserialize("{" +
                    "\"Error\": \"InvalidArgumentException\"," +
                    "\"Message\": \"Missing field field_a\"" +
                "}");


        EventRequestModel eventReq = new EventRequestModel();

        eventReq.setTime(new Date());
        eventReq.setUri("https://api.acmeinc.com/items/reviews/");
        eventReq.setVerb("PATCH");
        eventReq.setApiVersion("1.1.0");
        eventReq.setIpAddress("61.48.220.123");
        eventReq.setHeaders(reqHeaders);
        eventReq.setBody(reqBody);


        EventResponseModel eventRsp = new EventResponseModel();

        eventRsp.setTime(new Date(System.currentTimeMillis() + 1000));
        eventRsp.setStatus(500);
        eventRsp.setHeaders(rspHeaders);
        eventRsp.setBody(rspBody);

        EventModel eventModel = new EventModel();
        eventModel.setRequest(eventReq);
        eventModel.setResponse(eventRsp);
        eventModel.setUserId("my_user_id");
        eventModel.setSessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f");

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
        Object reqHeaders = APIHelper.deserialize("{" +
                    "\"Host\": \"api.acmeinc.com\"," +
                    "\"Accept\": \"*/*\"," +
                    "\"Connection\": \"Keep-Alive\"," +
                    "\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\"," +
                    "\"Content-Type\": \"application/json\"," +
                    "\"Content-Length\": \"126\"," +
                    "\"Accept-Encoding\": \"gzip\"" +
                "}");

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

        Object rspHeaders = APIHelper.deserialize("{" +
                    "\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\"," +
                    "\"Vary\": \"Accept-Encoding\"," +
                    "\"Pragma\": \"no-cache\"," +
                    "\"Expires\": \"-1\"," +
                    "\"Content-Type\": \"application/json; charset=utf-8\"," +
                    "\"Cache-Control\": \"no-cache\"" +
                "}");

        Object rspBody = APIHelper.deserialize("{" +
                    "\"Error\": \"InvalidArgumentException\"," +
                    "\"Message\": \"Missing field field_a\"" +
                "}");


        EventRequestModel eventReq = new EventRequestModel();

        eventReq.setTime(new Date());
        eventReq.setUri("https://api.acmeinc.com/items/reviews/");
        eventReq.setVerb("PATCH");
        eventReq.setApiVersion("1.1.0");
        eventReq.setIpAddress("61.48.220.123");
        eventReq.setHeaders(reqHeaders);
        eventReq.setBody(reqBody);


        EventResponseModel eventRsp = new EventResponseModel();

        eventRsp.setTime(new Date(System.currentTimeMillis() + 1000));
        eventRsp.setStatus(500);
        eventRsp.setHeaders(rspHeaders);
        eventRsp.setBody(rspBody);

        EventModel eventModel = new EventModel();
        eventModel.setRequest(eventReq);
        eventModel.setResponse(eventRsp);
        eventModel.setUserId("my_user_id");
        eventModel.setSessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f");

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
        List<EventModel> body = APIHelper.deserialize("[{ 					\"request\": { 						\"time\": \"2016-09-09T04:45:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:45:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"mndug437f43\", 					\"session_token\": \"23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f\" 					 }, { 					\"request\": { 						\"time\": \"2016-09-09T04:46:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:46:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"mndug437f43\", 					\"session_token\": \"23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f\" 					 }, { 					\"request\": { 						\"time\": \"2016-09-09T04:47:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:47:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"mndug437f43\", 					\"session_token\": \"23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f\" 					 }, { 					\"request\": { 						\"time\": \"2016-09-09T04:48:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:48:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"mndug437f43\", 					\"session_token\": \"exfzweachxjgznvKUYrxFcxv]s98y18cx98q3yhwmnhcfx43f\" 					 }, { 					\"request\": { 						\"time\": \"2016-09-09T04:49:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:49:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"mndug437f43\", 					\"session_token\": \"23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f\" 					 }, { 					\"request\": { 						\"time\": \"2016-09-09T04:50:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:50:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"recvreedfef\", 					\"session_token\": \"xcvkrjmcfghwuignrmcmhxdhaaezse4w]s98y18cx98q3yhwmnhcfx43f\" 					 } ]", new TypeReference<List<EventModel>>(){});

        // Set callback and perform API call
        controller.setHttpCallBack(httpResponse);
        try {
        controller.createEventsBatch(body);
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
        List<EventModel> body = APIHelper.deserialize("[{ 					\"request\": { 						\"time\": \"2016-09-09T04:45:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:45:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"mndug437f43\", 					\"session_token\": \"23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f\" 					 }, { 					\"request\": { 						\"time\": \"2016-09-09T04:46:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:46:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"mndug437f43\", 					\"session_token\": \"23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f\" 					 }, { 					\"request\": { 						\"time\": \"2016-09-09T04:47:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:47:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"mndug437f43\", 					\"session_token\": \"23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f\" 					 }, { 					\"request\": { 						\"time\": \"2016-09-09T04:48:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:48:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"mndug437f43\", 					\"session_token\": \"exfzweachxjgznvKUYrxFcxv]s98y18cx98q3yhwmnhcfx43f\" 					 }, { 					\"request\": { 						\"time\": \"2016-09-09T04:49:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:49:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"mndug437f43\", 					\"session_token\": \"23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f\" 					 }, { 					\"request\": { 						\"time\": \"2016-09-09T04:50:42.914\", 						\"uri\": \"https://api.acmeinc.com/items/reviews/\", 						\"verb\": \"PATCH\", 						\"api_version\": \"1.1.0\", 						\"ip_address\": \"61.48.220.123\", 						\"headers\": { 							\"Host\": \"api.acmeinc.com\", 							\"Accept\": \"*/*\", 							\"Connection\": \"Keep-Alive\", 							\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\", 							\"Content-Type\": \"application/json\", 							\"Content-Length\": \"126\", 							\"Accept-Encoding\": \"gzip\" 						}, 						\"body\": { 							\"items\": [ 								{ 									\"direction_type\": 1, 									\"discovery_id\": \"fwfrf\", 									\"liked\": false 								}, 								{ 									\"direction_type\": 2, 									\"discovery_id\": \"d43d3f\", 									\"liked\": true 								} 							] 						} 					}, 					\"response\": { 						\"time\": \"2016-09-09T04:50:42.914\", 						\"status\": 500, 						\"headers\": { 							\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\", 							\"Vary\": \"Accept-Encoding\", 							\"Pragma\": \"no-cache\", 							\"Expires\": \"-1\", 							\"Content-Type\": \"application/json; charset=utf-8\", 							\"X-Powered-By\": \"ARR/3.0\", 							\"Cache-Control\": \"no-cache\", 							\"Arr-Disable-Session-Affinity\": \"true\" 						}, 						\"body\": { 							\"Error\": \"InvalidArgumentException\", 							\"Message\": \"Missing field field_a\" 						} 					}, 					\"user_id\": \"recvreedfef\", 					\"session_token\": \"xcvkrjmcfghwuignrmcmhxdhaaezse4w]s98y18cx98q3yhwmnhcfx43f\" 					 } ]", new TypeReference<List<EventModel>>(){});

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

        controller.createEventsBatchAsync(body, callBack);
        assertEquals(true, lock.await(10000, TimeUnit.MILLISECONDS));
    }

    /**
     * Update Single User via Injestion API
     * @throws Throwable
     */
    @Test
    public void testUpdateUser() throws Throwable {

        UserModel user = new UserModel();
        user.setUserId("12345");
        user.setModifiedTime(new Date());
        user.setIpAddress("29.80.250.240");
        user.setSessionToken("di3hd982h3fubv3yfd94egf");
        user.setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        user.setMetadata(APIHelper.deserialize("{" +
                "\"email\": \"johndoe@acmeinc.com\"," +
                "\"string_field\": \"value_1\"," +
                "\"number_field\": 0," +
                "\"object_field\": {" +
                "\"field_1\": \"value_1\"," +
                "\"field_2\": \"value_2\"" +
                "}" +
                "}"));

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

        UserModel user = new UserModel();
        user.setUserId("12345");
        user.setModifiedTime(new Date());
        user.setIpAddress("29.80.250.240");
        user.setSessionToken("di3hd982h3fubv3yfd94egf");
        user.setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        user.setMetadata(APIHelper.deserialize("{" +
                "\"email\": \"johndoe@acmeinc.com\"," +
                "\"string_field\": \"value_1\"," +
                "\"number_field\": 0," +
                "\"object_field\": {" +
                "\"field_1\": \"value_1\"," +
                "\"field_2\": \"value_2\"" +
                "}" +
                "}"));

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
        List<UserModel> body = new ArrayList<UserModel>();

        UserModel user = new UserModel();
        user.setUserId("12345");
        user.setModifiedTime(new Date());
        user.setIpAddress("29.80.250.240");
        user.setSessionToken("di3hd982h3fubv3yfd94egf");
        user.setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        user.setMetadata(APIHelper.deserialize("{" +
                "\"email\": \"johndoe@acmeinc.com\"," +
                "\"string_field\": \"value_1\"," +
                "\"number_field\": 0," +
                "\"object_field\": {" +
                "\"field_1\": \"value_1\"," +
                "\"field_2\": \"value_2\"" +
                "}" +
                "}"));
        body.add(user);

        user = new UserModel();
        user.setUserId("456789");
        user.setModifiedTime(new Date());
        user.setIpAddress("31.77.210.105");
        user.setSessionToken("daskbdxhsarwjlifgwefbEI");
        user.setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        user.setMetadata(APIHelper.deserialize("{" +
                "\"email\": \"maryjane@acmeinc.com\"," +
                "\"string_field\": \"value_1\"," +
                "\"number_field\": 1," +
                "\"object_field\": {" +
                "\"field_1\": \"value_1\"," +
                "\"field_2\": \"value_2\"" +
                "}" +
                "}"));
        body.add(user);


        // Set callback and perform API call
        controller.setHttpCallBack(httpResponse);
        try {
            controller.updateUsersBatch(body);
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
    public void testAddBatchedUsersAsync() throws Throwable {
        final CountDownLatch lock = new CountDownLatch(1);

        // Parameters for the API call
        List<UserModel> body = new ArrayList<UserModel>();

        UserModel user = new UserModel();
        user.setUserId("12345");
        user.setModifiedTime(new Date());
        user.setIpAddress("29.80.250.240");
        user.setSessionToken("di3hd982h3fubv3yfd94egf");
        user.setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        user.setMetadata(APIHelper.deserialize("{" +
            "\"email\": \"johndoe@acmeinc.com\"," +
            "\"string_field\": \"value_1\"," +
            "\"number_field\": 0," +
            "\"object_field\": {" +
                "\"field_1\": \"value_1\"," +
                "\"field_2\": \"value_2\"" +
                "}" +
            "}"));
        body.add(user);

        user = new UserModel();
        user.setUserId("456789");
        user.setModifiedTime(new Date());
        user.setIpAddress("31.77.210.105");
        user.setSessionToken("daskbdxhsarwjlifgwefbEI");
        user.setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        user.setMetadata(APIHelper.deserialize("{" +
                "\"email\": \"maryjane@acmeinc.com\"," +
                "\"string_field\": \"value_1\"," +
                "\"number_field\": 1," +
                "\"object_field\": {" +
                "\"field_1\": \"value_1\"," +
                "\"field_2\": \"value_2\"" +
                "}" +
                "}"));
        body.add(user);

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

        controller.updateUsersBatchAsync(body, callBack);
        assertEquals(true, lock.await(10000, TimeUnit.MILLISECONDS));
    }

}
