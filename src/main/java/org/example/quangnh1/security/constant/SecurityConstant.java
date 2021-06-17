package org.example.quangnh1.security.constant;

import org.example.quangnh1.SpringApplicationContext;
import org.example.quangnh1.model.Properties;

public class SecurityConstant {
    public static final Long EXPIRATION_TIME = 864000000L; //correct 10 dáy
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/create-user";
    public static final String TOKEN_SECRET = "1ht792hzze1232fwq4";

    public static String getTokenSecret() {
        Properties properties = (Properties) SpringApplicationContext.getBean("Properties");
        return properties.getTokenSecret();
    }
}

