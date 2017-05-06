/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.controllers;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.moesif.api.*;
import com.moesif.api.models.*;
import com.moesif.api.exceptions.*;
import com.moesif.api.http.client.HttpContext;
import com.moesif.api.http.request.HttpRequest;
import com.moesif.api.http.response.HttpResponse;
import com.moesif.api.http.client.APICallBack;
import com.moesif.api.controllers.syncwrapper.APICallBackCatcher;

public class APIController extends BaseController implements IAPIController {
    //private static variables for the singleton pattern
    private static Object syncObject = new Object();
    private static APIController instance = null;

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
    public void createEvent(
                final EventModel body
    ) throws Throwable {
        APICallBackCatcher<Object> callback = new APICallBackCatcher<Object>();
        createEventAsync(body, callback);
        if(!callback.isSuccess())
            throw callback.getError();
        callback.getResult();
    }

    /**
     * Add Single API Event Call
     * @param    body    The event to create
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error creating event
     */
    public void createEventAsync(
                final EventModel body,
                final APICallBack<Object> callBack
    ) throws JsonProcessingException {
        //the base uri for api requests
        String _baseUri = Configuration.BaseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri);
        _queryBuilder.append("/v1/events");
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>() {
            private static final long serialVersionUID = 4703880768413831931L;
            {
                    put( "X-Moesif-Application-Id", Configuration.ApplicationId);
            }
        };

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(_queryUrl, _headers, APIHelper.serialize(body));

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

    /**
     * Add multiple API Events in a single batch
     * @param    body    The events to create
     * @throws Throwable on error creating event
     */
    public void createEventsBatch(
                final List<EventModel> body
    ) throws Throwable {
        APICallBackCatcher<Object> callback = new APICallBackCatcher<Object>();
        createEventsBatchAsync(body, callback);
        if(!callback.isSuccess())
            throw callback.getError();
        callback.getResult();
    }

    /**
     * Add multiple API Events in a single batch
     * @param    body    The events to create
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error creating event
     */
    public void createEventsBatchAsync(
                final List<EventModel> body,
                final APICallBack<Object> callBack
    ) throws JsonProcessingException {
        //the base uri for api requests
        String _baseUri = Configuration.BaseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri);
        _queryBuilder.append("/v1/events/batch");
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>() {
            private static final long serialVersionUID = 5519066674529741692L;
            {
                    put( "X-Moesif-Application-Id", Configuration.ApplicationId);
            }
        };

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(_queryUrl, _headers, APIHelper.serialize(body));

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

    /**
     * Update a Single User
     * @param    body    The user to update
     * @throws Throwable on error creating event
     */
    public void updateUser(
            final UserModel body
    ) throws Throwable {
        APICallBackCatcher<Object> callback = new APICallBackCatcher<Object>();
        updateUserAsync(body, callback);
        if(!callback.isSuccess())
            throw callback.getError();
        callback.getResult();
    }

    /**
     * Update a Single User async
     * @param    body    The user to update
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error creating event
     */
    public void updateUserAsync(
            final UserModel body,
            final APICallBack<Object> callBack
    ) throws JsonProcessingException {
        //the base uri for api requests
        String _baseUri = Configuration.BaseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri);
        _queryBuilder.append("/v1/users");
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>() {
            private static final long serialVersionUID = 4703880768413831931L;
            {
                put( "X-Moesif-Application-Id", Configuration.ApplicationId);
            }
        };

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(_queryUrl, _headers, APIHelper.serialize(body));

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

    /**
     * Update multiple Users in a single batch
     * @param    body    The list of users to update
     * @throws Throwable on error creating event
     */
    public void updateUsersBatch(
            final List<UserModel> body
    ) throws Throwable {
        APICallBackCatcher<Object> callback = new APICallBackCatcher<Object>();
        updateUsersBatchAsync(body, callback);
        if(!callback.isSuccess())
            throw callback.getError();
        callback.getResult();
    }

    /**
     * Update multiple Users in a single batch async
     * @param    body    The list of users to update
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error creating event
     */
    public void updateUsersBatchAsync(
            final List<UserModel> body,
            final APICallBack<Object> callBack
    ) throws JsonProcessingException {
        //the base uri for api requests
        String _baseUri = Configuration.BaseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri);
        _queryBuilder.append("/v1/users/batch");
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>() {
            private static final long serialVersionUID = 5519066674529741692L;
            {
                put( "X-Moesif-Application-Id", Configuration.ApplicationId);
            }
        };

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().postBody(_queryUrl, _headers, APIHelper.serialize(body));

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


    private APICallBack<HttpResponse> createHttpResponseCallback(final APICallBack<Object> callBack) {

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

                    //let the caller know of the success
                    callBack.onSuccess(_context, _context);
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