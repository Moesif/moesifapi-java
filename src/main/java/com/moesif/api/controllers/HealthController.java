/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.controllers;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;

import com.moesif.api.*;
import com.moesif.api.models.*;
import com.moesif.api.exceptions.*;
import com.moesif.api.http.client.HttpContext;
import com.moesif.api.http.request.HttpRequest;
import com.moesif.api.http.response.HttpResponse;
import com.moesif.api.http.response.HttpStringResponse;
import com.moesif.api.http.client.APICallBack;
import com.moesif.api.controllers.syncwrapper.APICallBackCatcher;

public class HealthController extends BaseController implements IHealthController {
    private Configuration config;

    public HealthController() {

    }
    /**
     * Health Probe
     * @return  The health probe
     */
    public StatusModel getHealthProbe(
    ) throws Throwable {
        APICallBackCatcher<StatusModel> callback = new APICallBackCatcher<StatusModel>();
        getHealthProbeAsync(callback);
        if(!callback.isSuccess())
            throw callback.getError();
        return callback.getResult();
    }

    /**
     * Health Probe Async
     * @param    callBack Called after the HTTP response is received
     */
    public void getHealthProbeAsync(
                final APICallBack<StatusModel> callBack
    ) {
        //the base uri for api requests
        String _baseUri = config.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri);
        _queryBuilder.append("/health/probe");
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>() {
            private static final long serialVersionUID = 5262405786134955276L;
            {
                    put( "accept", "application/json" );
                    put( "X-Moesif-Application-Id", config.applicationId);
            }
        };

        //prepare and invoke the API call request to fetch the response
        final HttpRequest _request = getClientInstance().get(_queryUrl, _headers, null);

        //invoke the callback before request if its not null
        if (getHttpCallBack() != null)
        {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        //invoke request and get response
        Runnable _responseTask = new Runnable() {
            public void run() {
                //make the API call
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {

                            //invoke the callback after response if its not null
                            if (getHttpCallBack() != null)	
                            {
                                getHttpCallBack().OnAfterResponse(_context);
                            }

                            //handle errors defined at the API level
                            validateResponse(_response, _context);

                            //extract result from the http response
                            String _responseBody = ((HttpStringResponse)_response).getBody();
                            StatusModel _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<StatusModel>(){});

                            //let the caller know of the success
                            callBack.onSuccess(_context, _result);
                        } catch (APIException error) {
                            //let the caller know of the error
                            callBack.onFailure(_context, error);
                        } catch (IOException ioException) {
                            //let the caller know of the caught IO Exception
                            callBack.onFailure(_context, ioException);
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
                });
            }
        };

        //execute async using thread pool
        APIHelper.getScheduler().execute(_responseTask);
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
}