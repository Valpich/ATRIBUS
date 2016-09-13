package fr.eseo.atribus.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import fr.eseo.atribus.beans.Mail;
import fr.eseo.atribus.beans.MessagesParDefaut;
import fr.eseo.atribus.dao.DirecteurEtudesDao;
import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.dao.EnseignantDao;
import fr.eseo.atribus.dao.EnseignantRefMatiereDao;
import fr.eseo.atribus.dao.EnseignantRefUeDao;
import fr.eseo.atribus.entities.Utilisateur;
import fr.eseo.atribus.forms.ConvoquerEtudiantForm;
import fr.eseo.atribus.forms.SuggererAjouterCompetenceForm;
import fr.eseo.atribus.forms.SuggererNouvelleCompetenceForm;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test
@ContextConfiguration({ "classpath:spring-dao.xml", "classpath:spring-daoBeans.xml",
    "classpath:beanRefFactory.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
public class MailControllerTest {

  public static final String ATT_FORM = "form";
  public static final String ATT_SESSION_USER = "sessionUtilisateur";
  private static final String VUE_SUGGERER_AJOUTER = "suggererCompetenceExistantes";
  private static final String VUE_SUGGERER_NOUVELLE = "suggererNouvelleCompetence";
  private static final String VUE_CONVOQUER = "DirecteurEtudes/convoquerEtudiant";
  private static final String REDIRECT_INDEX = "redirect:/index";

  private MockMvc mockMvc;
  private DirecteurEtudesDao deDao;
  private final MailController mailController = new MailController();
  private EleveDao eleveDao;
  private EnseignantDao enseignantDao;
  private EnseignantRefMatiereDao ermDao;
  private EnseignantRefUeDao erueDao;
  private final MessagesParDefaut messages = new MessagesParDefaut();

  @BeforeClass
  public void setup() {
    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/");
    viewResolver.setSuffix(".jsp");
    MockitoAnnotations.initMocks(this);
    this.mockMvc =
        MockMvcBuilders.standaloneSetup(this.mailController).setViewResolvers(viewResolver).build();
    this.messages.setAdminSystProblemeUn("");
    this.messages.setAdminSystProblemeDeux("");
    this.messages.setAdminSystProblemeTrois("");
    this.messages.setConvocationDePartieUn("");
    this.messages.setConvocationDePartieDeux("");
    this.messages.setConvocationDePartieTrois("");
    this.messages.setEnseignantSuggereCompetenceUn("");
    this.messages.setEnseignantSuggereCompetenceDeux("");
    this.messages.setEnseignantSuggereCompetenceTrois("");
    this.messages.setEnseignantSuggereCompetenceQuatre("");
    this.messages.setEleveSuggereCompetenceUn("");
    this.messages.setEleveSuggereCompetenceDeux("");
    this.messages.setEleveSuggereCompetenceTrois("");
    this.messages.setEleveSuggereCompetenceQuatre("");
    this.messages.setEureSuggereCompetenceUn("");
    this.messages.setEureSuggereCompetenceDeux("");
    this.messages.setEureSuggereCompetenceTrois("");
    this.messages.setEureSuggereCompetenceQuatre("");
    this.messages.setErmSuggereCompetenceUn("");
    this.messages.setErmSuggereCompetenceDeux("");
    this.messages.setErmSuggereCompetenceTrois("");
    this.messages.setErmSuggereCompetenceQuatre("");
    final Mail mail = new Mail("atribus@eseo.fr", "atribus@eseo.fr", null, "192.168.11.4");
    final ConvoquerEtudiantForm convoquerEtudiantForm = new ConvoquerEtudiantForm();
    convoquerEtudiantForm.setMessages(this.messages);
    convoquerEtudiantForm.setMail(mail);
    final SuggererAjouterCompetenceForm suggererAjouterCompetenceForm =
        new SuggererAjouterCompetenceForm();
    final SuggererNouvelleCompetenceForm suggererNouvelleCompetenceForm =
        new SuggererNouvelleCompetenceForm();
    suggererAjouterCompetenceForm.setMail(mail);
    suggererAjouterCompetenceForm.setMessages(this.messages);
    suggererNouvelleCompetenceForm.setMail(mail);
    suggererNouvelleCompetenceForm.setMessages(this.messages);
    this.mailController.setConvoquerEtudiantForm(convoquerEtudiantForm);
    this.mailController.setSuggererAjouterCompetenceForm(suggererAjouterCompetenceForm);
    this.mailController.setSuggererNouvelleCompetenceForm(suggererNouvelleCompetenceForm);
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Examen */
    this.deDao = (DirecteurEtudesDao) bf.getFactory().getBean("directeurEtudesDao");
    /* Récupération d'une instance de notre DAO Eleve */
    this.eleveDao = (EleveDao) bf.getFactory().getBean("eleveDao");
    /* Récupération d'une instance de notre DAO Enseignant */
    this.enseignantDao = (EnseignantDao) bf.getFactory().getBean("enseignantDao");
    /* Récupération d'une instance de notre DAO Enseignant Reférent Matiere */
    this.ermDao = (EnseignantRefMatiereDao) bf.getFactory().getBean("enseignantRefMatiereDao");
    /* Récupération d'une instance de notre DAO Enseignant Reférent Matiere */
    this.erueDao = (EnseignantRefUeDao) bf.getFactory().getBean("enseignantRefUeDao");
  }


  @Test
  public void afficherConvoquerEtudiant() throws Exception {
    this.mockMvc.perform(get("/DirecteurEtudes/ConvoquerEtudiant")).andExpect(status().isOk())
        .andExpect(view().name(VUE_CONVOQUER));
  }

  @Test
  public void convoquerEtudiant() throws Exception {
    this.mockMvc.perform(post("/DirecteurEtudes/ConvoquerEtudiant").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(MailControllerTest.this.deDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.addParameter("choixEleve", "1");
      request.addParameter("date", "le 18/04/2016 à 17h30");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_CONVOQUER));
    this.mockMvc.perform(post("/DirecteurEtudes/ConvoquerEtudiant").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.addParameter("choixEleve", "1");
      request.addParameter("date", "le 18/04/2016 à 17h30");
      return request;
    })).andExpect(status().is(302)).andExpect(view().name(REDIRECT_INDEX));
  }

  @Test
  public void afficherSuggererCompetenceNouvelle() throws Exception {
    this.mockMvc.perform(get("/Eleve/SuggererCompetence")).andExpect(status().isOk())
        .andExpect(view().name(VUE_SUGGERER_NOUVELLE));
  }

  @Test
  public void suggererNouvelleCompetence() throws Exception {
    this.mockMvc.perform(post("/Eleve/SuggererCompetence").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(MailControllerTest.this.eleveDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.addParameter("competence", "CompetenceTest");
      request.addParameter("commentaire", "CommentaireTest");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_SUGGERER_NOUVELLE));
  }

  @Test
  public void afficherSuggererCompetenceNouvelleRefUe() throws Exception {
    this.mockMvc.perform(get("/EnseignantRefUE/SuggererCompetence")).andExpect(status().isOk())
        .andExpect(view().name(VUE_SUGGERER_NOUVELLE));
  }

  @Test
  public void suggererNouvelleCompetenceRefUe() throws Exception {
    this.mockMvc.perform(post("/EnseignantRefUE/SuggererCompetence").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(MailControllerTest.this.erueDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.addParameter("competence", "CompetenceTest");
      request.addParameter("commentaire", "CommentaireTest");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_SUGGERER_NOUVELLE));
  }

  @Test
  public void afficherSuggererCompetenceNouvelleEnseignant() throws Exception {
    this.mockMvc.perform(get("/Enseignant/SuggererCompetence")).andExpect(status().isOk())
        .andExpect(view().name(VUE_SUGGERER_AJOUTER));
  }

  @Test
  public void suggererNouvelleCompetenceEnseignant() throws Exception {
    this.mockMvc.perform(post("/Enseignant/SuggererCompetence").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(MailControllerTest.this.enseignantDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.addParameter("choixCompetence", "1");
      request.addParameter("commentaire", "CommentaireTest");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_SUGGERER_AJOUTER));
  }

  @Test
  public void afficherSuggererAjouterCompetenceEnseignantRefMatiere() throws Exception {
    this.mockMvc.perform(get("/EnseignantRefMatiere/SuggererCompetence")).andExpect(status().isOk())
        .andExpect(view().name(VUE_SUGGERER_AJOUTER));
  }

  @Test
  public void suggererAjouterCompetenceEnseignantRefMatiere() throws Exception {
    this.mockMvc.perform(post("/EnseignantRefMatiere/SuggererCompetence").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(MailControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.addParameter("choixCompetence", "1");
      request.addParameter("commentaire", "CommentaireTest");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_SUGGERER_AJOUTER));
  }
}

