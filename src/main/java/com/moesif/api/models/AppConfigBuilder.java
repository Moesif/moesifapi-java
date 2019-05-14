package com.moesif.api.models;

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

    public AppConfigBuilder etag(String etag) {
      AppConfigModel.setEtag(etag);
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
