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
     * @return    Returns the void response from the API call 
     */
    public void getHealthProbeAsync(final APICallBack<StatusModel> callBack);

}