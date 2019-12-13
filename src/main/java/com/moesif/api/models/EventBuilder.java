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
     * @param request the field to set
     * @return itself
     */
    public EventBuilder request(EventRequestModel request) {
        eventModel.setRequest(request);
        return this;
    }

    /**
     * API response Object
     * @param response the field to set
     * @return itself
     */
    public EventBuilder response(EventResponseModel response) {
        eventModel.setResponse(response);
        return this;
    }

    /**
     * End user's auth/session token
     * @param sessionToken the field to set
     * @return itself
     */
    public EventBuilder sessionToken(String sessionToken) {
        eventModel.setSessionToken(sessionToken);
        return this;
    }

    /**
     * comma separated list of tags, see documentation
     * @param tags the field to set
     * @return itself
     */
    public EventBuilder tags(String tags) {
        eventModel.setTags(tags);
        return this;
    }

    /**
     * End user's user_id string from your app
     * @param userId the field to set
     * @return itself
     */
    public EventBuilder userId(String userId) {
        eventModel.setUserId(userId);
        return this;
    }
    
    /**
     * User's company_id string
     * @param companyId the field to set
     * @return itself
     */
    public EventBuilder companyId(String companyId) {
        eventModel.setCompanyId(companyId);
        return this;
    }

    /**
     * Custom event metadata as a JSON object
     * @param metadata the field to set
     * @return itself
     */
    public EventBuilder metadata(Object metadata) {
        eventModel.setMetadata(metadata);
        return this;
    }

    /**
     * API direction string
     * @param direction the field to set
     * @return itself
     */
    public EventBuilder direction(String direction) {
        eventModel.setDirection(direction);
        return this;
    }

    /**
     * Build the instance with the given values
     * @return the built EventModel
     */
    public EventModel build() {
        return eventModel;
    }
}