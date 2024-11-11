package com.stfn2.ggas.config.security.ldap;

import com.stfn2.ggas.config.utils.LdapAppConfig;
import com.stfn2.ggas.core.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfig {

    @Autowired
    private LdapAppConfig ldapAppConfig;

    private Log log = new Log(this.getClass());

    @Bean
    @Primary
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        try {
            contextSource.setUrl(ldapAppConfig.getUrls());
            contextSource.setBase(ldapAppConfig.getBase());
            //contextSource.setAnonymousReadOnly(true);            
            contextSource.setPooled(false);
            contextSource.afterPropertiesSet();
            log.info("Conexão com o servidor LDAP realizada: " + contextSource.getUserDn());
        } catch (Exception e) {
            contextSource.setUrl("ldap://localhost:389/dc=springframework,dc=org");
            contextSource.setBase("");
            contextSource.setPooled(false);
            log.info("Falha ao conectar ao servidor LDAP: " + e.getMessage());
        }
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate(LdapContextSource contextSource) {
        return new LdapTemplate(contextSource);
    }
}