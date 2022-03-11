package com.moesif.api.controllers;

public class EnvVars {
    public static String MOESIF_APPLICATION_ID = "MOESIF_APPLICATION_ID";
    public static String MOESIF_BASE_URI = "MOESIF_BASE_URI";

    public static String readMoesifApplicationId() {
        return System.getenv(MOESIF_APPLICATION_ID);
    }

    public static String readMoesifBaseUri() {
        return System.getenv(MOESIF_BASE_URI);
    }
}
