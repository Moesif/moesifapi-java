/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api;


import com.moesif.api.http.client.APICallBack;
import com.moesif.api.models.StatusModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface IHealthController {


    /**
     * Health Probe
     * @return  The health probe
     * @throws Throwable on getting health probe
     */
    StatusModel getHealthProbe() throws Throwable;

    /**
     * Health Probe async
     * @param    callBack Called after the HTTP response is received
     */
    void getHealthProbeAsync(final APICallBack<StatusModel> callBack);

}