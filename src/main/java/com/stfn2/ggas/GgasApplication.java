package com.stfn2.ggas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {LdapRepositoriesAutoConfiguration.class})
@EnableFeignClients
@EnableJpaRepositories(basePackages = {"com.stfn2.ggas.core.abstractClasses", "com.stfn2.ggas.repositories"})
public class GgasApplication {

        public static void main(String[] args) {
                SpringApplication.run(GgasApplication.class, args);
        }
}
