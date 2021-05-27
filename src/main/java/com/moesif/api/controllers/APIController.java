/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.lang.Math;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moesif.api.*;
import com.moesif.api.models.*;
import com.moesif.api.exceptions.*;
import com.moesif.api.http.client.HttpContext;
import com.moesif.api.http.request.HttpRequest;
import com.moesif.api.http.response.HttpResponse;
import com.moesif.api.http.client.APICallBack;
import com.moesif.api.controllers.syncwrapper.APICallBackCatcher;

import java.util.logging.Logger;

public class APIController extends BaseController implements IAPIController {
    //private static variables for the singleton pattern
    private static Object syncObject = new Object();
    private static APIController instance = null;

    private static final Logger logger = Logger.getLogger(APIController.class.toString());

    private static final String APP_CONFIG_ETAG_HEADER = "x-moesif-config-etag";

    // wait 5 minutes before grabbing the new config (different servers might have different states)
    private static final int APP_CONFIG_DEBOUNCE = 1000 * 60 * 5; // 5 minutes
    private long lastAppConfigFetch;
    private boolean shouldSyncAppConfig = false;
    private AppConfigModel appConfigModel;
    private String appConfigEtag;

    /**
     * Singleton pattern implementation 
     * @return The singleton instance of the APIController class
     */
    public static APIController getInstance() {
        synchronized (syncObject) {
            if (null == instance) {
                instance = new APIController();
            }
        }
        return instance;
    }


    /**
     * Add Single API Event Call
     * @param    body    The event to create
     * @throws Throwable on error creating event
     */
    public Map<String, String> createEvent(
                final EventModel body
    ) throws Throwable {

        QueryInfo qInfo = getQueryInfo("/v1/events");

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(qInfo._queryUrl, qInfo._headers, APIHelper.serialize(body));

        //invoke the callback before request if its not null
        if (getHttpCallBack() != null)
        {
            getHttpCallBack().OnBeforeRequest(_request);
        }
        
        // make the API call
        return executeRequest(_request);
    }



    /**
     * Add Single API Event Call
     * @param    body    The event to create
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error creating event
     */
    public void createEventAsync(
                final EventModel body,
                final APICallBack<HttpResponse> callBack
    ) throws JsonProcessingException {

        QueryInfo qInfo = getQueryInfo("/v1/events");

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(qInfo._queryUrl, qInfo._headers, APIHelper.serialize(body));

        executeRequestAsync(_request, callBack);
    }

    /**
     * Add multiple API Events in a single batch
     * @param    body    The events to create
     * @throws Throwable on error creating event
     */
    public Map<String, String> createEventsBatch(
                final List<EventModel> body
    ) throws Throwable {
        QueryInfo qInfo = getQueryInfo("/v1/events/batch");

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(qInfo._queryUrl, qInfo._headers, APIHelper.serialize(body));
        
        // make the API call
        return executeRequest(_request);
    }

    /**
     * Add multiple API Events in a single batch
     * @param    body    The events to create
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error creating event
     */
    public void createEventsBatchAsync(
                final List<EventModel> body,
                final APICallBack<HttpResponse> callBack
    ) throws JsonProcessingException {

        QueryInfo qInfo = getQueryInfo("/v1/events/batch");

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(qInfo._queryUrl, qInfo._headers, APIHelper.serialize(body));

        executeRequestAsync(_request, callBack);
    }

    /**
     * Update a Single User
     * @param    body    The user to update
     * @throws Throwable on error updating user
     */
    public void updateUser(
            final UserModel body
    ) throws Throwable {
        APICallBackCatcher<HttpResponse> callback = new APICallBackCatcher<HttpResponse>();
        updateUserAsync(body, callback);
        if(!callback.isSuccess())
            throw callback.getError();
        callback.getResult();
    }

    /**
     * Update a Single User async
     * @param    body    The user to update
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error updating user
     */
    public void updateUserAsync(
            final UserModel body,
            final APICallBack<HttpResponse> callBack
    ) throws JsonProcessingException {
        QueryInfo qInfo = getQueryInfo("/v1/users");

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(qInfo._queryUrl, qInfo._headers, APIHelper.serialize(body));

        executeRequestAsync(_request, callBack);
    }

    /**
     * Update multiple Users in a single batch
     * @param    body    The list of users to update
     * @throws Throwable on error updating users
     */
    public void updateUsersBatch(
            final List<UserModel> body
    ) throws Throwable {
        APICallBackCatcher<HttpResponse> callback = new APICallBackCatcher<HttpResponse>();
        updateUsersBatchAsync(body, callback);
        if(!callback.isSuccess())
            throw callback.getError();
        callback.getResult();
    }

    /**
     * Update multiple Users in a single batch async
     * @param    body    The list of users to update
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error updating users
     */
    public void updateUsersBatchAsync(
            final List<UserModel> body,
            final APICallBack<HttpResponse> callBack
    ) throws JsonProcessingException {

        QueryInfo qInfo = getQueryInfo("/v1/users/batch");

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(qInfo._queryUrl, qInfo._headers, APIHelper.serialize(body));

        executeRequestAsync(_request, callBack);
    }


    /**
     * Update a Single Company
     * @param    body    The company to update
     * @throws Throwable on error updating a company
     */
    public void updateCompany(
            final CompanyModel body
    ) throws Throwable {
        APICallBackCatcher<HttpResponse> callback = new APICallBackCatcher<HttpResponse>();
        updateCompanyAsync(body, callback);
        if(!callback.isSuccess())
            throw callback.getError();
        callback.getResult();
    }

    /**
     * Update a Single Company async
     * @param    body    The company to update
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error updating a company
     */
    public void updateCompanyAsync(
            final CompanyModel body,
            final APICallBack<HttpResponse> callBack
    ) throws JsonProcessingException {
        QueryInfo qInfo = getQueryInfo("/v1/companies");

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(qInfo._queryUrl, qInfo._headers, APIHelper.serialize(body));

        executeRequestAsync(_request, callBack);
    }

    /**
     * Update multiple Companies in a single batch
     * @param    body    The list of companies to update
     * @throws Throwable on error updating companies
     */
    public void updateCompaniesBatch(
            final List<CompanyModel> body
    ) throws Throwable {
        APICallBackCatcher<HttpResponse> callback = new APICallBackCatcher<HttpResponse>();
        updateCompaniesBatchAsync(body, callback);
        if(!callback.isSuccess())
            throw callback.getError();
        callback.getResult();
    }

    /**
     * Update multiple Companies in a single batch async
     * @param    body    The list of companies to update
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error updating companies
     */
    public void updateCompaniesBatchAsync(
            final List<CompanyModel> body,
            final APICallBack<HttpResponse> callBack
    ) throws JsonProcessingException {
        QueryInfo qInfo = getQueryInfo("/v1/companies/batch");

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(qInfo._queryUrl, qInfo._headers, APIHelper.serialize(body));

        executeRequestAsync(_request, callBack);
    }

    /**
     * Get the Application config
     * @throws Throwable on error getting app config
     */
    public HttpResponse getAppConfig() throws Throwable {
        QueryInfo qInfo = getQueryInfo("/v1/config");

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().get(qInfo._queryUrl, qInfo._headers, null);

        //invoke the callback before request if its not null
        if (getHttpCallBack() != null)
        {
            getHttpCallBack().OnBeforeRequest(_request);
        }
        
        // make the API call
        HttpResponse _response = getClientInstance().executeAsString(_request);
        
        // Wrap the request and the response in an HttpContext object
        HttpContext _context = new HttpContext(_request, _response);
        
        //invoke the callback after response if its not null
        if (getHttpCallBack() != null)
        {
            getHttpCallBack().OnAfterResponse(_context);
        }

        //handle errors defined at the API level
        validateResponse(_response, _context);
        
        // Return headers to the client
        return _response;

    }

    /**
     * Get the Application config async
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error getting app config
     */
    public void getAppConfigAsync(
    		final APICallBack<HttpResponse> callBack
    ) throws JsonProcessingException {
        QueryInfo qInfo = getQueryInfo("/v1/config");

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().get(qInfo._queryUrl, qInfo._headers, null);

        executeRequestAsync(_request, callBack);
    }


    private static class QueryInfo {
        String _queryUrl;
        Map<String, String> _headers;

        public QueryInfo(String url, Map<String, String> headers) {
            _queryUrl = url; _headers = headers;
        }
    }

    private QueryInfo getQueryInfo(String url) {

        if (Configuration.ApplicationId == null || Configuration.ApplicationId.equals("")) {
            throw new IllegalArgumentException("A Moesif Application Id is required. Please obtain it through your settings at www.moesif.com");
        }

        if (Configuration.BaseUri == null || Configuration.BaseUri.equals("")) {
            throw new IllegalArgumentException("The API BaseUri is required.");
        }

        String _baseUri = Configuration.BaseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri);
        _queryBuilder.append(url);
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>() {
            {
                put( "X-Moesif-Application-Id", Configuration.ApplicationId);
            }
        };

        return new QueryInfo(_queryUrl, _headers);
    }

    private  Map<String, String> executeRequest(final HttpRequest _request) throws Throwable {
        HttpResponse _response = getClientInstance().executeAsString(_request);
        Map<String, String> headers = _response.getHeaders();

        // Wrap the request and the response in an HttpContext object
        HttpContext _context = new HttpContext(_request, _response);

        //invoke the callback after response if its not null
        if (getHttpCallBack() != null)
        {
            getHttpCallBack().OnAfterResponse(_context);
        }

        //handle errors defined at the API level
        validateResponse(_response, _context);

        checkAppConfigEtag(headers.get(APP_CONFIG_ETAG_HEADER));

        // Return headers to the client
        return headers;
    }

    private void executeRequestAsync(final HttpRequest _request, final APICallBack<HttpResponse> callBack) {
        //invoke the callback before request if its not null
        if (getHttpCallBack() != null)
        {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        //invoke request and get response
        Runnable _responseTask = new Runnable() {
            public void run() {
                //make the API call
                getClientInstance().executeAsStringAsync(_request, createHttpResponseCallback(callBack));
            }
        };

        //execute async using thread pool
        APIHelper.getScheduler().execute(_responseTask);
    }

    private AppConfigModel getCachedAppConfig() {
        if (appConfigModel == null) {
            trySyncAppConfig();
            return getDefaultAppConfig();
        } else {
            return appConfigModel;
        }
    }

    public void setAppConfig(AppConfigModel config) {
        if (config != null) {
            appConfigModel = config;
        }
    }

    public void setShouldSyncAppConfig(boolean shouldSync) {
        shouldSyncAppConfig = shouldSync;
    }

    private void checkAppConfigEtag(String newAppConfigEtag) {
        if (newAppConfigEtag != null && !newAppConfigEtag.equals(appConfigEtag)) {
            // only update the etag once we've gotten the new config
            trySyncAppConfig();
        }
    }

    private boolean trySyncAppConfig() {
        long now = new Date().getTime();
        boolean willFetch = shouldSyncAppConfig && lastAppConfigFetch + APP_CONFIG_DEBOUNCE < now;

        if (willFetch) {
            lastAppConfigFetch = now;

            syncAppConfig();
        }

        return willFetch;
    }

    public static AppConfigModel parseAppConfigModel(InputStream jsonTxt) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonTxt, AppConfigModel.class);
    }

    private void syncAppConfig() {
        if (shouldSyncAppConfig) {
            APICallBack<HttpResponse> callback = new APICallBack<HttpResponse>() {
                public void onSuccess(HttpContext context, HttpResponse response) {
                    // Read the response body
                    try {
                        InputStream respBodyIs = response.getRawBody();
                        appConfigModel = parseAppConfigModel(respBodyIs);
                        respBodyIs.close();
                    } catch (Exception e) {
                        logger.warning("Invalid AppConfig JSON: " + e.getMessage());
                    }
                    logger.info("App Config Model returned is " + appConfigModel);

                    appConfigEtag = response
                            .getHeaders()
                            .get(APP_CONFIG_ETAG_HEADER);
                }

                public void onFailure(HttpContext context, Throwable error) {
                    // fail silently
                    // try again later
                }
            };

            try {
                getAppConfigAsync(callback);
            } catch (Exception e) {
                logger.warning("Error performing async operation");
            }
        }
    }

    public AppConfigModel getDefaultAppConfig() {
        return new AppConfigBuilder()
            .sampleRate(100)
            .build();
    }

    public int calculateWeight(int sampleRate) {
        return (int)(sampleRate == 0 ? 1 : Math.floor(100.00 / sampleRate));
    }

    public EventModel buildEventModel(EventRequestModel eventRequestModel,
                           EventResponseModel eventResponseModel,
                           String userId,
                           String companyId,
                           String sessionToken,
                           String apiVersion,
                           Object metadata,
                           String direction) {
        EventBuilder eb = new EventBuilder();

        eb.request(eventRequestModel);
        eb.response(eventResponseModel);

        if (userId != null) {
            eb.userId(userId);
        }
        if (companyId != null) {
            eb.companyId(companyId);
        }
        if (sessionToken != null) {
            eb.sessionToken(sessionToken);
        }
        if (apiVersion != null) {
            eb.tags(apiVersion);
        }
        if (metadata != null) {
            eb.metadata(metadata);
        }

        eb.direction(direction);

        return eb.build();
    }

    public boolean shouldSendSampledEvent() {
        int sampleRate = getCachedAppConfig().getSampleRate();
        double randomPercentage = Math.random() * 100;

        return sampleRate >= randomPercentage;
    }

    public boolean shouldSendSampledEvent(EventModel eventModel) {
        int sampleRate = getSampleRateToUse(eventModel);
        double randomPercentage = Math.random() * 100;

        return sampleRate >= randomPercentage;
    }

    public int getSampleRateToUse(EventModel eventModel) {
        AppConfigModel appConfigModel = getCachedAppConfig();
        int sampleRate = appConfigModel.getSampleRate();

        if (eventModel.getUserId() != null && appConfigModel.getUserSampleRate().containsKey(eventModel.getUserId())) {
            sampleRate = appConfigModel.getUserSampleRate().get(eventModel.getUserId());
        } else if (eventModel.getCompanyId() != null && appConfigModel.getCompanySampleRate().containsKey(eventModel.getCompanyId())) {
            sampleRate = appConfigModel.getCompanySampleRate().get(eventModel.getCompanyId());
        }

        return sampleRate;
    }

    private APICallBack<HttpResponse> createHttpResponseCallback(final APICallBack<HttpResponse> callBack) {

        return new APICallBack<HttpResponse>() {
            public void onSuccess(HttpContext _context, HttpResponse _response) {
                try {

                    //invoke the callback after response if its not null
                    if (getHttpCallBack() != null)
                    {
                        getHttpCallBack().OnAfterResponse(_context);
                    }

                    //handle errors defined at the API level
                    validateResponse(_response, _context);

                    checkAppConfigEtag(_response.getHeaders().get(APP_CONFIG_ETAG_HEADER));

                    //let the caller know of the success
                    callBack.onSuccess(_context, _response);
                } catch (APIException error) {
                    //let the caller know of the error
                    callBack.onFailure(_context, error);
                } catch (Exception exception) {
                    //let the caller know of the caught Exception
                    callBack.onFailure(_context, exception);
                }
            }
            public void onFailure(HttpContext _context, Throwable _error) {
                //invoke the callback after response if its not null
                if (getHttpCallBack() != null)
                {
                    getHttpCallBack().OnAfterResponse(_context);
                }

                //let the caller know of the failure
                callBack.onFailure(_context, _error);
            }
        };
    }
}