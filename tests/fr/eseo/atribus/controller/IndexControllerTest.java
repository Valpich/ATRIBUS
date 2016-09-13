package fr.eseo.atribus.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IndexControllerTest {

  private MockMvc mockMvc;

  /**
   * Initialisation.
   */
  @BeforeTest
  public void beforeTest() {

    final IndexController indexController = new IndexController();
    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    viewResolver.setPrefix("/WEB-INF/");
    viewResolver.setSuffix(".jsp");
    MockitoAnnotations.initMocks(this);

    this.mockMvc =
        MockMvcBuilders.standaloneSetup(indexController).setViewResolvers(viewResolver).build();

  }

  @Test
  public void afficherIndexSessionNotNull() throws Exception {

    final String session = "sessionUtilisateur";

    this.mockMvc.perform(get("/index").sessionAttr(session, "")).andExpect(status().isOk())
        .andExpect(view().name("index"));

    this.mockMvc.perform(get("").sessionAttr(session, "")).andExpect(status().isOk())
        .andExpect(view().name("index"));

  }

  @Test
  public void afficherIndexSessionNull() throws Exception {

    this.mockMvc.perform(get("/index")).andExpect(view().name("redirect:/connexion"));
    this.mockMvc.perform(get("")).andExpect(view().name("redirect:/connexion"));

  }


}
