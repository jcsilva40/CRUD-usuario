package com.stfn2.ggas.config.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "configuracoes.ldap")
@Data
public class LdapAppConfig {
    private String urls;
    private String base;
}