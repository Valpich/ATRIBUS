package fr.eseo.atribus.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SimulerControllerTest {

  public static final String VUE = "simulerDroits";

  private MockMvc mockMvc;

  /**
   * Initialisation.
   */
  @BeforeTest
  public void setup() {

    final SimulerController SimulerController = new SimulerController();

    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    viewResolver.setPrefix("/WEB-INF/");
    viewResolver.setSuffix(".jsp");
    MockitoAnnotations.initMocks(this);

    this.mockMvc =
        MockMvcBuilders.standaloneSetup(SimulerController).setViewResolvers(viewResolver).build();

  }

  @Test
  public void simulationGet() throws Exception {

    this.mockMvc.perform(get("/AdministrateurSysteme/Simuler")).andExpect(status().isOk())
        .andExpect(view().name(VUE));

  }

  @Test
  public void simulationPostAs() throws Exception {

    this.mockMvc.perform(post("/AdministrateurSysteme/Simuler").param("choixDroit", "AS"))
        .andExpect(status().isOk()).andExpect(view().name(VUE));

  }

  @Test
  public void simulationPostDe() throws Exception {

    this.mockMvc.perform(post("/AdministrateurSysteme/Simuler").param("choixDroit", "DE"))
        .andExpect(status().isOk()).andExpect(view().name(VUE));

  }

  @Test
  public void simulationPostEleve() throws Exception {

    this.mockMvc.perform(post("/AdministrateurSysteme/Simuler").param("choixDroit", "Eleve"))
        .andExpect(status().isOk()).andExpect(view().name(VUE));

  }

  @Test
  public void simulationPostProf() throws Exception {

    this.mockMvc.perform(post("/AdministrateurSysteme/Simuler").param("choixDroit", "Prof"))
        .andExpect(status().isOk()).andExpect(view().name(VUE));

  }

  @Test
  public void simulationPostDP() throws Exception {

    this.mockMvc.perform(post("/AdministrateurSysteme/Simuler").param("choixDroit", "DP"))
        .andExpect(status().isOk()).andExpect(view().name(VUE));

  }

  @Test
  public void simulationPostEure() throws Exception {

    this.mockMvc.perform(post("/AdministrateurSysteme/Simuler").param("choixDroit", "EURE"))
        .andExpect(status().isOk()).andExpect(view().name(VUE));

  }

  @Test
  public void simulationPostErm() throws Exception {

    this.mockMvc.perform(post("/AdministrateurSysteme/Simuler").param("choixDroit", "ERM"))
        .andExpect(status().isOk()).andExpect(view().name(VUE));

  }

  @Test
  public void simulationPostDefault() throws Exception {

    this.mockMvc.perform(post("/AdministrateurSysteme/Simuler").param("choixDroit", "Default"))
        .andExpect(status().isOk()).andExpect(view().name(VUE));

  }

}
