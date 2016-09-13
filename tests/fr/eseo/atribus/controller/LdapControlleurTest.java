package fr.eseo.atribus.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.mockito.MockitoAnnotations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring-dao.xml", "classpath:spring-daoBeans.xml",
    "classpath:beanRefFactory.xml", "classpath:dispatcher-servlet.xml" })
public class LdapControlleurTest {

  private MockMvc mockMvc;
  private LdapTemplate ldapTemplate;
  private LdapControlleur ldapController;

  /**
   * Initialisation.
   */
  @BeforeTest
  public void setup() {

    this.ldapController = new LdapControlleur();
    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/");
    viewResolver.setSuffix(".jsp");
    MockitoAnnotations.initMocks(this);
    this.mockMvc =
        MockMvcBuilders.standaloneSetup(this.ldapController).setViewResolvers(viewResolver).build();
    final DefaultSpringSecurityContextSource contextSource =
        new DefaultSpringSecurityContextSource("ldap://192.168.4.12:389");
    contextSource.setUserDn("cn=admin,dc=ldcr,dc=tp");
    contextSource.setPassword("L4d3A2p1");
    contextSource.setBase("dc=ldcr,dc=tp");
    contextSource.afterPropertiesSet();
    this.ldapTemplate = new LdapTemplate(contextSource);
  }

  @Test
  public void afficherAuthenficiationLdap() throws Exception {

    this.mockMvc.perform(get("/AdministrateurSysteme/AuthentificationLDAP"))
        .andExpect(status().isOk()).andExpect(view().name("connexionLDAP"));

  }

  @Test
  public void authenficiationLdap() throws Exception {
    this.ldapController.setLdapTemplate(this.ldapTemplate);
    this.mockMvc.perform(post("/AdministrateurSysteme/AuthentificationLDAP").with(request -> {
      request.setParameter("login", "testldap");
      request.setParameter("password", "testmdp");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name("indexLDAP"));

  }

  @Test
  public void authenficiationLdapFail() throws Exception {
    this.ldapController.setLdapTemplate(this.ldapTemplate);
    this.mockMvc.perform(post("/AdministrateurSysteme/AuthentificationLDAP").with(request -> {
      request.setParameter("login", "testldap");
      request.setParameter("password", "tesmdp");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name("connexionLDAP"));
  }

  @Test
  public void listerLdap() throws Exception {
    this.ldapController.setLdapTemplate(this.ldapTemplate);
    this.mockMvc.perform(get("/AdministrateurSysteme/ListerLDAP")).andExpect(status().isOk());
  }

}
