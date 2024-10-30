package com.moesif.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = ActionRequestModel.Builder.class)
public class ActionRequestModel {
    private final String uri;
    private final String userAgentString;
    private final String ipAddress;
    private final String time;

    private ActionRequestModel(Builder builder) {
        this.uri = builder.uri;
        this.userAgentString = builder.userAgentString;
        this.ipAddress = builder.ipAddress;
        this.time = builder.time;
    }

    public String getUri() {
        return uri;
    }

    public String getUserAgentString() {
        return userAgentString;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getTime() {
        return time;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private final String uri;
        private String userAgentString;
        private String ipAddress;
        private String time;

        public Builder(String uri) {
            if (uri == null || uri.isEmpty()) {
                throw new IllegalArgumentException("uri is required");
            }
            this.uri = uri;
        }

        public Builder setUserAgentString(String userAgentString) {
            this.userAgentString = userAgentString;
            return this;
        }

        public Builder setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public ActionRequestModel build() {
            return new ActionRequestModel(this);
        }
    }
}

