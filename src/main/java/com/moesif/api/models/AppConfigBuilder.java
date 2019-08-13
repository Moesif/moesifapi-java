package com.moesif.api.models;

import java.util.Map;

public class AppConfigBuilder {
    private AppConfigModel AppConfigModel;

    /**
     * Default constructor to initialize the instance
     */
    public AppConfigBuilder() {
      AppConfigModel = new AppConfigModel();
    }

    public AppConfigBuilder orgId(String orgId) {
      AppConfigModel.setOrgId(orgId);
      return this;
    }

    public AppConfigBuilder appId(String appId) {
      AppConfigModel.setAppId(appId);
      return this;
    }

    public AppConfigBuilder sampleRate(int sampleRate) {
      AppConfigModel.setSampleRate(sampleRate);
      return this;
    }

    public AppConfigBuilder userSampleRate(Map<String, Integer> userSampleRate) {
        AppConfigModel.setUserSampleRate(userSampleRate);
        return this;
    }

    public AppConfigBuilder companySampleRate(Map<String, Integer> companySampleRate) {
        AppConfigModel.setCompanySampleRate(companySampleRate);
        return this;
    }

    /**
     * Build the instance with the given values
     * @return The built UserModel
     */
    public AppConfigModel build() {
        return AppConfigModel;
    }
}
