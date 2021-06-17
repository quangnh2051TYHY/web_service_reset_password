package org.example.quangnh1.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration
public class Properties {

    @Value("${tokenSecret}")
    private String tokenSecret;

    @Bean(name="Properties")
    public Properties getAppProperties()
    {
        return new Properties();
    }

    public String getTokenSecret() {
        return this.tokenSecret;
    }

}
