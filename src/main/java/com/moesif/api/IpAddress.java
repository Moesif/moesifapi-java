package com.moesif.api;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by derric on 4/10/17.
 */
public class IpAddress {
    private static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String getClientIp(Map<String, String> headers, String remoteAddr) {
        // Standard headers used by Amazon EC2, Heroku, and others.
        String header = getClientIpFromHeader(headers.get("x-client-ip"));
        if (isNotEmpty(header)) {
            return header;
        }

        header = getClientIpFromHeader(headers.get("client-ip"));
        if (isNotEmpty(header)) {
            return header;
        }

        header = getClientIpFromHeader(headers.get("x-forwarded-for"));
        if (isNotEmpty(header)) {
            return header;
        }

        // Cloudflare.
        // @see https://support.cloudflare.com/hc/en-us/articles/200170986-How-does-Cloudflare-handle-HTTP-Request-headers-
        // CF-Connecting-IP - applied to every request to the origin.
        header = getClientIpFromHeader(headers.get("cf-connecting-ip"));
        if (isNotEmpty(header)) {
            return header;
        }

        // Fastly
        header = getClientIpFromHeader(headers.get("fastly-client-ip"));
        if (isNotEmpty(header)) {
            return header;
        }

        // Akamai and Cloudflare: True-Client-IP.
        header = getClientIpFromHeader(headers.get("true-client-ip"));
        if (isNotEmpty(header)) {
            return header;
        }

        // Default nginx proxy/fcgi; alternative to x-forwarded-for, used by some proxies.
        header = getClientIpFromHeader(headers.get("x-real-ip"));
        if (isNotEmpty(header)) {
            return header;
        }

        // (Rackspace LB and Riverbed's Stingray)
        // http://www.rackspace.com/knowledge_center/article/controlling-access-to-linux-cloud-sites-based-on-the-client-ip-address
        // https://splash.riverbed.com/docs/DOC-1926
        header = getClientIpFromHeader(headers.get("x-cluster-client-ip"));
        if (isNotEmpty(header)) {
            return header;
        }

        header = getClientIpFromHeader(headers.get("x-forwarded"));
        if (isNotEmpty(header)) {
            return header;
        }

        header = getClientIpFromHeader(headers.get("forwarded-for"));
        if (isNotEmpty(header)) {
            return header;
        }

        header = getClientIpFromHeader(headers.get("forwarded"));
        if (isNotEmpty(header)) {
            return header;
        }

        return remoteAddr;
    }

    private static Pattern PRIVATE_ADDRESS_PATTERN = Pattern.compile(
            "(^127\\.)|(^192\\.168\\.)|(^10\\.)|(^172\\.1[6-9]\\.)|(^172\\.2[0-9]\\.)|(^172\\.3[0-1]\\.)|(^::1$)|(^[fF][cCdD])",
            Pattern.CANON_EQ);

    private static String getClientIpFromHeader(String header) {

        if (isEmpty(header)) {
            return null;
        }

        String firstValue = header.split(",")[0];

        if (isEmpty(firstValue) || isPrivateOrLocalAddress(firstValue)) {
            return null;
        } else {
            return firstValue;
        }
    }

    private static boolean isPrivateOrLocalAddress(String address) {
        Matcher regexMatcher = PRIVATE_ADDRESS_PATTERN.matcher(address);
        return regexMatcher.matches();
    }
}
