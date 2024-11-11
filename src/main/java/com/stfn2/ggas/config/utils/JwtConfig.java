package com.stfn2.ggas.config.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt.token")
@Data
public class JwtConfig {
    private String secretKey;
    private long expireLength;
}