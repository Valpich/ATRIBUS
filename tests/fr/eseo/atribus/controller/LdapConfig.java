package fr.eseo.atribus.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
public class LdapConfig {

  @Bean
  public LdapTemplate ldapTemplate() {
    final DefaultSpringSecurityContextSource contextSource =
        new DefaultSpringSecurityContextSource("ldap://192.168.4.12:389");
    contextSource.setUserDn("cn=admin,dc=ldcr,dc=tp");
    contextSource.setPassword("L4d3A2p1");
    contextSource.setBase("dc=ldcr,dc=tp");
    return new LdapTemplate(contextSource);
  }

}
