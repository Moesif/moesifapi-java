package com.moesif.api.controllers;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnvVarsTest {

    /**
     * For Junit to run, a valid MoesifApplicationId is required
     */
    @Test
    public void testMoesifApplicationIdSet(){
        String msg = "For tests, environment variable must be set: " + EnvVars.MOESIF_APPLICATION_ID;
        assertNotNull(msg, EnvVars.readMoesifApplicationId());
        assertFalse(msg, EnvVars.readMoesifApplicationId().isBlank());
    }
}
