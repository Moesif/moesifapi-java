/*
 * MoesifAPILib
 *
 *
 */
package com.moesif.api;

public class Configuration {

    public static String DefaultBaseUri = "https://api.moesif.net";
    public static final String DateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    /***
     * Your Moesif Collector Application Id which can be found by logging into your Moesif account.
     */
    public String applicationId;

    /***
     * The base Uri for API calls. Change if using Moesif secure proxy.
     */
    public String baseUri;

    /***
     * Set to true to print debug logs if you're having integration issues.
     */
    public boolean debug = false;

    /***
     * Custom User-Agent string for HTTP requests. 
     * If null or empty, defaults to "moesifapi-java/{version}"
     */
    public String userAgent;
}
