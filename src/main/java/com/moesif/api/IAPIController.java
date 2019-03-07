/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moesif.api.http.client.APICallBack;
import com.moesif.api.models.CompanyModel;
import com.moesif.api.models.EventModel;
import com.moesif.api.models.UserModel;

public interface IAPIController {

    /**
     * Add Single API Event Call
     * @param    body    The event to create
     * @throws Throwable on error creating event
     */
	Map<String, String> createEvent(final EventModel body) throws Throwable;

    /**
     * Add Single API Event Call
     * @param    body    The event to create
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error creating event
     */
    void createEventAsync(final EventModel body,
                          final APICallBack<Object> callBack) throws JsonProcessingException;

    /**
     * Add multiple API Events in a single batch
     * @param    body    The events to create
     * @throws Throwable on error creating event
     */
    Map<String, String> createEventsBatch(final List<EventModel> body) throws Throwable;

    /**
     * Add multiple API Events in a single batch
     * @param    body    The events to create
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error creating event
     */
    void createEventsBatchAsync(final List<EventModel> body,
                                final APICallBack<Object> callBack) throws JsonProcessingException;

    /**
     * Update a Single User
     * @param    body    The user to update
     * @throws Throwable on error creating event
     */
    void updateUser(final UserModel body) throws Throwable;

    /**
     * Update a Single User async
     * @param    body    The user to update
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error creating event
     */
    void updateUserAsync(final UserModel body,
                         final APICallBack<Object> callBack) throws JsonProcessingException;

    /**
     * Update multiple Users in a single batch
     * @param    body    The list of users to update
     * @throws Throwable on error creating event
     */
    void updateUsersBatch(final List<UserModel> body) throws Throwable;

    /**
     * Update multiple Users in a single batch async
     * @param    body    The list of users to update
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error creating event
     */
    void updateUsersBatchAsync(final List<UserModel> body,
                               final APICallBack<Object> callBack) throws JsonProcessingException;
    
    /**
     * Get the Application config
     * @throws Throwable on error getting app config
     */
    void getAppConfig() throws Throwable;
    
    /**
     * Get the Application config async
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error getting app config
     */
    void getAppConfigAsync(final APICallBack<Object> callBack) throws JsonProcessingException;
    
    /**
     * Update a Single Company
     * @param    body    The company to update
     * @throws Throwable on error updating a company
     */
    void updateCompany(final CompanyModel body) throws Throwable;
    
    /**
     * Update a Single Company async
     * @param    body    The company to update
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error updating a company
     */
    void updateCompanyAsync(final CompanyModel body,
    		final APICallBack<Object> callBack) throws JsonProcessingException;
    
    /**
     * Update multiple Companies in a single batch
     * @param    body    The list of companies to update
     * @throws Throwable on error updating companies
     */
    void updateCompaniesBatch(final List<CompanyModel> body) throws Throwable;
    
    /**
     * Update multiple Companies in a single batch async
     * @param    body    The list of companies to update
     * @param    callBack Called after the HTTP response is received
     * @throws JsonProcessingException on error updating companies
     */
    void updateCompaniesBatchAsync(final List<CompanyModel> body,
    		final APICallBack<Object> callBack) throws JsonProcessingException;

}