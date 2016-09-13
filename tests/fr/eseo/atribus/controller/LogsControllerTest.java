package fr.eseo.atribus.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogsControllerTest {

  private static final String VUE = "AdministrateurSysteme/logs";

  private MockMvc mockMvc;

  /**
   * Méthode d'initialisation.
   */
  @BeforeClass
  public void setup() {

    final LogsController logsController = new LogsController();
    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    viewResolver.setPrefix("/WEB-INF/");
    viewResolver.setSuffix(".jsp");
    MockitoAnnotations.initMocks(this);

    this.mockMvc =
        MockMvcBuilders.standaloneSetup(logsController).setViewResolvers(viewResolver).build();

  }

  @Test
  public void afficherLogs() throws Exception {

    this.mockMvc.perform(get("/AdministrateurSysteme/AfficherLogs").param("recherche", "NotNull"))
        .andExpect(status().isOk()).andExpect(view().name(VUE));

  }

  @Test
  public void afficherLogsChoixNotNull() throws Exception {

    this.mockMvc.perform(get("/AdministrateurSysteme/AfficherLogs")).andExpect(status().isOk())
        .andExpect(view().name(VUE));

  }

  @Test
  public void afficherLogsRecherche() throws Exception {

    this.mockMvc.perform(post("/AdministrateurSysteme/AfficherLogs")).andExpect(status().isOk())
        .andExpect(view().name(VUE));

  }

  @Test
  public void afficherLogsRechercheNotNull() throws Exception {

    this.mockMvc.perform(post("/AdministrateurSysteme/AfficherLogs").param("choixLog", "NotNull"))
        .andExpect(status().isOk()).andExpect(view().name(VUE));

  }

}
