/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moesif.api.http.client.APICallBack;
import com.moesif.api.models.EventModel;
import com.moesif.api.models.UserModel;

public interface IAPIController {

    /**
     * Add Single API Event Call
     * @param    body    Required parameter: Example: 
     * @return    Returns the void response from the API call 
     */
    void createEvent(final EventModel body) throws Throwable;

    /**
     * Add Single API Event Call
     * @param    body    Required parameter: Example: 
     * @return    Returns the void response from the API call 
     */
    void createEventAsync(final EventModel body,
                          final APICallBack<Object> callBack) throws JsonProcessingException;

    /**
     * Add multiple API Events in a single batch
     * @param    body    Required parameter: Example: 
     * @return    Returns the void response from the API call 
     */
    void createEventsBatch(final List<EventModel> body) throws Throwable;

    /**
     * Add multiple API Events in a single batch
     * @param    body    Required parameter: Example: 
     * @return    Returns the void response from the API call 
     */
    void createEventsBatchAsync(final List<EventModel> body,
                                final APICallBack<Object> callBack) throws JsonProcessingException;

    /**
     * Add Single API Event Call
     * @param    body    Required parameter: Example:
     * @return    Returns the void response from the API call
     */
    void updateUser(final UserModel body) throws Throwable;

    /**
     * Add Single API Event Call
     * @param    body    Required parameter: Example:
     * @return    Returns the void response from the API call
     */
    void updateUserAsync(final UserModel body,
                         final APICallBack<Object> callBack) throws JsonProcessingException;

    /**
     * Add multiple API Events in a single batch
     * @param    body    Required parameter: Example:
     * @return    Returns the void response from the API call
     */
    void updateUsersBatch(final List<UserModel> body) throws Throwable;

    /**
     * Add multiple API Events in a single batch
     * @param    body    Required parameter: Example:
     * @return    Returns the void response from the API call
     */
    void updateUsersBatchAsync(final List<UserModel> body,
                               final APICallBack<Object> callBack) throws JsonProcessingException;

}