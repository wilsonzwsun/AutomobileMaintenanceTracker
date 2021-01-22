package com.pa.amt.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties
public class GlobalProperties {
    @Getter
    @Setter
    @Value("${user.update.token}")
    private String updaeToken;
    @Getter
    @Setter
    @Value("${user.delete.token}")
    private String deleteToken;
}
