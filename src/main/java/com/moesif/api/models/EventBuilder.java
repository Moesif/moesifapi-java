/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api.models;

public class EventBuilder {
    //the instance to build
    private EventModel eventModel;

    /**
     * Default constructor to initialize the instance
     */
    public EventBuilder() {
        eventModel = new EventModel();
    }

    /**
     * API request object
     */
    public EventBuilder request(EventRequestModel request) {
        eventModel.setRequest(request);
        return this;
    }

    /**
     * API response Object
     */
    public EventBuilder response(EventResponseModel response) {
        eventModel.setResponse(response);
        return this;
    }

    /**
     * End user's auth/session token
     */
    public EventBuilder sessionToken(String sessionToken) {
        eventModel.setSessionToken(sessionToken);
        return this;
    }

    /**
     * comma separated list of tags, see documentation
     */
    public EventBuilder tags(String tags) {
        eventModel.setTags(tags);
        return this;
    }

    /**
     * End user's user_id string from your app
     */
    public EventBuilder userId(String userId) {
        eventModel.setUserId(userId);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public EventModel build() {
        return eventModel;
    }
}