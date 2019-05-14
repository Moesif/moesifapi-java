package com.moesif.api.controllers;

import com.moesif.api.models.AppConfigModel;

public class ConfigController extends BaseController {
    //private static variables for the singleton pattern
    private static Object syncObject = new Object();
    private static ConfigController instance = null;

    /**
     * Singleton pattern implementation
     * @return The singleton instance of the ConfigController class
     */
    public static ConfigController getInstance() {
        synchronized (syncObject) {
            if (null == instance) {
                instance = new ConfigController();
            }
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public AppConfigModel getCachedAndTryLoadAppConfig() {
        return null;
    }
}
