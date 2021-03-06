package fr.eseo.atribus.controller;

import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.dao.EnseignantRefMatiereDao;
import fr.eseo.atribus.dao.UtilisateurDao;
import fr.eseo.atribus.entities.Utilisateur;
import fr.eseo.atribus.forms.ModifierRessourceForm;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

@Test
@ContextConfiguration({ "classpath:spring-dao.xml", "classpath:spring-daoBeans.xml",
    "classpath:beanRefFactory.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
public class RessourceControllerTest {
  public static final String VUE_MODIFIER = "EnseignantRefMatiere/modifierRessource";
  public static final String VUE_TELECHARGER = "download";
  public static final String VUE_ELEVE_CONSULTER = "Eleve/consulterRessource";
  public static final String VUE_UPLOAD = "upload";

  private MockMvc mockMvc;
  private EnseignantRefMatiereDao ermDao;
  private UtilisateurDao utilisateurDao;
  final RessourceController ressourceController = new RessourceController();

  @BeforeClass
  public void setup() throws ServletException {
    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/");
    viewResolver.setSuffix(".jsp");
    MockitoAnnotations.initMocks(this);
    final File tmp = new File("/srv/tomcat-8/webapps/ATRIBUS/test.xml");
    if (!tmp.exists()) {
      try {
        tmp.createNewFile();
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
    try (BufferedWriter writer =
        Files.newBufferedWriter(Paths.get("/srv/tomcat-8/webapps/ATRIBUS/test.xml"),
            StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
      writer.append("<texte>test</texte>");
      writer.flush();
      writer.close();
    } catch (final IOException ioe) {
      ioe.printStackTrace();
    }
    final File file = new File("/srv/tomcat-8/webapps/ATRIBUS/upload.properties");
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
    try (BufferedWriter writer =
        Files.newBufferedWriter(Paths.get("/srv/tomcat-8/webapps/ATRIBUS/upload.properties"),
            StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
      writer.append("basePath=/");
      writer.append("serveurPath=/");
      writer.append("tempFolder=/");
      writer.flush();
      writer.close();
    } catch (final IOException ioe) {
      ioe.printStackTrace();
    }
    try {
      this.ressourceController.init();
    } catch (final ServletException se) {
      se.printStackTrace();
    }
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Enseignant Reférent Matiere */
    this.ermDao = (EnseignantRefMatiereDao) bf.getFactory().getBean("enseignantRefMatiereDao");
    /* Récupération d'une instance de notre DAO Utilisateur */
    this.utilisateurDao = (UtilisateurDao) bf.getFactory().getBean("utilisateurDao");
    this.ressourceController.setBasePath(null);
    try {
      this.ressourceController.validerFichier();
    } catch (final Exception e) {
      e.printStackTrace();
    }
    this.ressourceController.setBasePath("/");
    this.ressourceController.validerFichier();
    this.ressourceController.setServeurPath("/srv/tomcat-8/webapps/ATRIBUS");
    this.ressourceController.validerFichier();
    this.ressourceController.setTempFolder("tmpFiles");
    this.ressourceController.validerFichier();

    this.mockMvc = MockMvcBuilders.standaloneSetup(this.ressourceController)
        .setViewResolvers(viewResolver).build();
  }

  @Test
  public void afficherTelechargerRessource() throws Exception {
    this.ressourceController.getBasePath();
    this.ressourceController.getServeurPath();
    this.ressourceController.getTempFolder();
    this.mockMvc.perform(get("/RecupererRessource")).andExpect(status().isOk())
        .andExpect(view().name(VUE_TELECHARGER));
  }

  @Test
  public void afficherUploaderRessource() throws Exception {

    this.mockMvc.perform(get("/EnseignantRefMatiere/EnvoyerRessource")).andExpect(status().isOk())
        .andExpect(view().name(VUE_UPLOAD));
  }

  @Test
  public void uploadFileHandler() throws Exception {
    MockMultipartFile mockFile =
        new MockMultipartFile("file", "test.xml", "", "Juste un fichier de test".getBytes());
    this.mockMvc.perform(
        fileUpload("/EnseignantRefMatiere/EnvoyerRessource").file(mockFile).with(request -> {
          request.setParameter("choixMatiere", "MP Microcontrôleur 1");
          request.setParameter("choixCompetence", "Exploiter");
          return request;
        })).andDo(print()).andExpect(status().isOk()).andExpect(view().name(VUE_UPLOAD));
    mockFile = new MockMultipartFile("file", "Juste un fichier de test".getBytes());
    this.mockMvc.perform(
        fileUpload("/EnseignantRefMatiere/EnvoyerRessource").file(mockFile).with(request -> {
          request.setParameter("choixMatiere", "MP Microcontrôleur 1");
          request.setParameter("choixCompetence", "Exploiter");
          return request;
        })).andDo(print()).andExpect(status().isOk()).andExpect(view().name(VUE_UPLOAD));
  }

  @Test
  public void uploadFileHandlerVide() throws Exception {
    final MockMultipartFile mockFile = new MockMultipartFile("file", "".getBytes());
    this.mockMvc.perform(
        fileUpload("/EnseignantRefMatiere/EnvoyerRessource").file(mockFile).with(request -> {
          request.setParameter("choixMatiere", "MP Microcontrôleur 1");
          request.setParameter("choixCompetence", "Exploiter");
          return request;
        })).andExpect(status().isOk()).andExpect(view().name(VUE_UPLOAD));
  }

  @Test
  public void getFile() throws Exception {
    final File file = new File("test.xml");
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
    this.mockMvc.perform(post("/RecupererRessource").with(new RequestPostProcessor() {
      @Override
      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
        final List<Utilisateur> utilisateur = new ArrayList<>();
        utilisateur.add(RessourceControllerTest.this.utilisateurDao.trouverParLogin("admin"));
        request.getSession().setAttribute("sessionUtilisateur", utilisateur);
        request.setParameter("choixRessource", "test.xml");
        return request;
      }

      public MockHttpServletResponse postProcessResponse(MockHttpServletResponse response) {
        return response;
      }
    })).andExpect(status().isOk());
  }

  @Test
  public void afficherConsulterRessource() throws Exception {
    this.mockMvc.perform(get("/Eleve/ConsulterRessource")).andExpect(status().isOk())
        .andExpect(request().attribute("ressources", notNullValue()))
        .andExpect(view().name(VUE_ELEVE_CONSULTER));
  }

  @Test
  public void afficherModifierRessource() throws Exception {
    this.mockMvc.perform(get("/EnseignantRefMatiere/ModifierRessource").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(RessourceControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().isOk()).andExpect(request().attribute("ressources", notNullValue()))
        .andExpect(view().name(VUE_MODIFIER));
  }

  @Test
  public void modifierRessource() throws Exception {
    this.mockMvc.perform(post("/EnseignantRefMatiere/ModifierRessource").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(RessourceControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.setParameter("nomRessource", "test");
      request.setParameter("choixRessource", "test.xml");
      return request;
    })).andExpect(status().isOk()).andExpect(request().attribute("ressources", notNullValue()))
        .andExpect(view().name(VUE_MODIFIER));
  }

  @Test
  public void afficherExtraireNotes() throws Exception {
    this.mockMvc.perform(get("/DirecteurEtudes/ExtraireToutesLesNotes")).andExpect(status().isOk())
        .andExpect(content().contentType("application/octet-stream"));
  }

  @Test
  public void supprimerRessource() throws Exception {
    ModifierRessourceForm form = new ModifierRessourceForm();
    assertNotNull(form.getErreurs());
    this.mockMvc.perform(post("/EnseignantRefMatiere/SupprimerRessource").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(RessourceControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.setParameter("hiddenRessource", "tst.xml");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_MODIFIER));
    assertNull(form.getResultat());
  }

  @Test
  public void recupererRessources() throws Exception {

    this.mockMvc.perform(get("/RecupererRessource")).andExpect(status().isOk())
        .andExpect(view().name("download"));
  }
  
  @Test
  public void getBasePathTest(){
    RessourceController ressC = new RessourceController();
    final String basePath = "testBP";
    ressC.setBasePath(basePath);
    assertEquals(ressC.getBasePath(), basePath);
  }

  @Test
  public void consulterRessourcesAvecCompetences() throws Exception {
    this.mockMvc.perform(post("/Eleve/ConsulterRessource").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(RessourceControllerTest.this.utilisateurDao.trouverParLogin("admin"));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.setParameter("choixRessource", "test.xml");
      return request;
    })).andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void afficherExtraireNotesDe() throws Exception {
    this.mockMvc.perform(get("/DirecteurEtudes/ExtraireNotes")).andExpect(status().isOk())
        .andExpect(view().name("DirecteurEtudes/extraireNotes"));
  }

  @Test
  public void extraireNotesDe() throws Exception {
    boolean test = false;
    try {
      this.mockMvc
          .perform(post("/DirecteurEtudes/ExtraireNotes").param("choixPromotion", "De Gennes")
              .param("dateDebut", "2015-01-01").param("dateFin", "2017-01-01"))
          .andExpect(status().isOk());
    } catch (final Exception e) {
      test = true;
    }
    assertEquals(test, true);
    this.mockMvc
        .perform(post("/DirecteurEtudes/ExtraireNotes").param("choixPromotion", "1")
            .param("dateDebut", "2015-01-01").param("dateFin", "2017-01-01"))
        .andExpect(status().isOk());
  }
  
  @Test
  public void afficherUtilisationRessources() throws Exception {
    this.mockMvc.perform(get("/EnseignantRefUE/UtilisationRessources")).andExpect(status().isOk())
        .andExpect(view().name("EnseignantRefUE/historiqueRessources"));
  }
}
