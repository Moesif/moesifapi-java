package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = ActionModel.Builder.class)
public class ActionModel {
    private final String actionName;
    private final String userId;
    private final String companyId;
    private final String transactionId;
    private final String sessionToken;
    private final String subscriptionId;
    private final Map<String, Object> metadata;
    private final ActionRequestModel request;

    private ActionModel(Builder builder) {
        this.actionName = builder.actionName;
        this.userId = builder.userId;
        this.companyId = builder.companyId;
        this.transactionId = builder.transactionId;
        this.sessionToken = builder.sessionToken;
        this.subscriptionId = builder.subscriptionId;
        this.metadata = builder.metadata;
        this.request = builder.request;
    }

    public String getActionName() {
        return actionName;
    }

    public String getUserId() {
        return userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public ActionRequestModel getRequest() {
        return request;
    }

    // In the progressive enhancement of the Moesif API, the ActionModel class is introduced using
    // immutable data structures and a static inner Builder class along with simplified Jackson boilerplate

    // Old Builder pattern for constructing an existing Model type
    // CompanyModel company = new CompanyBuilder()
    //    .companyId("company-123")
    //    .metadata(customMetadata)
    //    .build();

    // New way of constructing an ActionModel is very similar to the old way but with a static inner Builder class
    // This checks required fields and results in an immutable output for more rigorous error checking and clearer code
    // ActionRequestModel request = new ActionRequestModel.Builder("https://example.com")
    //    .setUserAgentString("Mozilla/5.0")
    //    .build();
    //
    // ActionModel action = new ActionModel.Builder("Clicked Sign Up", request)
    //    .setUserId("user-123")
    //    .setMetadata(customMetadata)
    //    .build();
    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private final String actionName;
        private String userId;
        private String companyId;
        private String transactionId;
        private String sessionToken;
        private String subscriptionId;
        private Map<String, Object> metadata;
        private final ActionRequestModel request;

        public Builder(String actionName, ActionRequestModel request) {
            if (actionName == null || actionName.isEmpty()) {
                throw new IllegalArgumentException("action_name is required");
            }
            if (request == null) {
                throw new IllegalArgumentException("request is required");
            }
            this.actionName = actionName;
            this.request = request;
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setCompanyId(String companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder setTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder setSessionToken(String sessionToken) {
            this.sessionToken = sessionToken;
            return this;
        }

        public Builder setSubscriptionId(String subscriptionId) {
            this.subscriptionId = subscriptionId;
            return this;
        }

        public Builder setMetadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public ActionModel build() {
            return new ActionModel(this);
        }
    }
}

