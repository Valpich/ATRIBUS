package fr.eseo.atribus.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import fr.eseo.atribus.dao.EnseignantRefMatiereDao;
import fr.eseo.atribus.dao.ExamenDao;
import fr.eseo.atribus.entities.Utilisateur;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test
@ContextConfiguration({ "classpath:spring-dao.xml", "classpath:spring-daoBeans.xml",
    "classpath:beanRefFactory.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
public class ExerciceControllerTest {

  public static final String CONF_BDD_FACTORY = "DaoFactory";
  public static final String ATT_EXERCICE = "exercice";
  public static final String ATT_EXERCICES = "exercices";
  public static final String ATT_FORM = "form";
  public static final String ATT_COMPETENCES = "competences";
  public static final String ATT_EXAMEN = "examen";
  public static final String ATT_EXAMENS = "examens";
  public static final String VUE_MODIFIER = "EnseignantRefMatiere/modifierExercice";
  public static final String VUE_SUPPRIMER = "EnseignantRefMatiere/supprimerExercice";
  public static final String VUE = "EnseignantRefMatiere/addExercice";
  public static final String VUE_AUTO_EVAL = "EnseignantRefMatiere/ajouterAutoEval";
  public static final String ATT_CHOIX_EXAMEN = "choixExamen";
  public static final String ATT_SAVE_EXAMEN = "saveExamen";
  public static final String ATT_SESSION_USER = "sessionUtilisateur";

  private MockMvc mockMvc;
  private EnseignantRefMatiereDao ermDao;
  private ExamenDao examenDao;

  @BeforeClass
  public void setup() {
    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/");
    viewResolver.setSuffix(".jsp");
    MockitoAnnotations.initMocks(this);
    final ExerciceController exerciceController = new ExerciceController();
    exerciceController.init();
    this.mockMvc =
        MockMvcBuilders.standaloneSetup(exerciceController).setViewResolvers(viewResolver).build();
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Enseignant Reférent Matiere */
    this.ermDao = (EnseignantRefMatiereDao) bf.getFactory().getBean("enseignantRefMatiereDao");
    /* Récupération d'une instance de notre DAO Examen */
    this.examenDao = (ExamenDao) bf.getFactory().getBean("examenDao");
  }

  @Test
  public void afficherAjouterExercice() throws Exception {
    this.mockMvc.perform(get("/EnseignantRefMatiere/AjouterExercice")).andExpect(status().isOk())
        .andExpect(view().name(VUE));
  }

  @Test
  public void ajouterExercice() throws Exception {
    this.mockMvc.perform(post("/EnseignantRefMatiere/AjouterExercice").with(request -> {
      final List<String> reponses = new ArrayList<>();
      reponses.add("Reponse1");
      reponses.add("Reponse2");
      final List<String> competences = new ArrayList<>();
      competences.add("Exploiter");
      final List<String> pourcentages = new ArrayList<>();
      pourcentages.add("19");
      final Map<String, String[]> parameters = new HashMap<>();
      final String[] tmp = { "12" };
      final String[] tmpDeux = { "Exploiter" };
      parameters.put("pourcentage1", tmp);
      parameters.put("choixCompetence1", tmpDeux);
      final String point = "10";
      final String reponse = "Bonne réponse";
      final String question = "QuestionTest";
      final String examen =
          ExerciceControllerTest.this.examenDao.trouverTousLesExamens().get(0).getNom();
      request.addParameters(parameters);
      request.setParameter("question", question);
      request.setParameter("reponse", reponse);
      request.setParameter("points", point);
      request.setParameter("choixExamen", examen);
      request.setSession(new MockHttpSession());
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExerciceControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE));
  }

  @Test
  public void afficherModifierExercice() throws Exception {
    this.mockMvc.perform(get("/EnseignantRefMatiere/ModifierExercice").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExerciceControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_MODIFIER));
  }

  @Test
  public void modifierExerciceAvantSauvegarde() throws Exception {
    this.mockMvc.perform(post("/EnseignantRefMatiere/ModifierExercice").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExerciceControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      final List<String> reponses = new ArrayList<>();
      reponses.add("Reponse1");
      reponses.add("Reponse2");
      final List<String> competences = new ArrayList<>();
      competences.add("Exploiter");
      final List<String> pourcentages = new ArrayList<>();
      pourcentages.add("19");
      final Map<String, String[]> parameters = new HashMap<>();
      final String[] tmp = { "12" };
      final String[] tmpDeux = { "Exploiter qdqsd" };
      final String[] tmpTrois = { "1.2" };
      final String[] tmpQuatre = { "Question sdfsdf" };
      final String[] tmpCinq = { "Repons sffe" };
      parameters.put("pourcentage1_1", tmp);
      parameters.put("choixCompetence1_1", tmpDeux);
      parameters.put("points1", tmpTrois);
      parameters.put("question1", tmpQuatre);
      parameters.put("reponse1", tmpCinq);
      final String examen = Integer
          .toString(ExerciceControllerTest.this.examenDao.trouverTousLesExamens().get(0).getId());
      request.addParameter("choixExamen", examen);
      request.addParameters(parameters);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_MODIFIER));
  }

  @Test
  public void modifierExerciceApresSauvegarde() throws Exception {
    this.mockMvc.perform(post("/EnseignantRefMatiere/ModifierExercice").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExerciceControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      final List<String> reponses = new ArrayList<>();
      reponses.add("Reponse1");
      reponses.add("Reponse2");
      final List<String> competences = new ArrayList<>();
      competences.add("Exploiter");
      final List<String> pourcentages = new ArrayList<>();
      pourcentages.add("19");
      final Map<String, String[]> parameters = new HashMap<>();
      final String[] tmp = { "12" };
      final String[] tmpDeux = { "Exploiter qdqsd" };
      final String[] tmpTrois = { "1.2" };
      final String[] tmpQuatre = { "Question sdfsdf" };
      final String[] tmpCinq = { "Repons sffe" };
      parameters.put("pourcentage1_1", tmp);
      parameters.put("choixCompetence1_1", tmpDeux);
      parameters.put("points1", tmpTrois);
      parameters.put("question1", tmpQuatre);
      parameters.put("reponse1", tmpCinq);
      final String examen =
          ExerciceControllerTest.this.examenDao.trouverTousLesExamens().get(0).getNom();
      request.getSession().setAttribute("examen",
          ExerciceControllerTest.this.examenDao.trouverTousLesExamens().get(0));
      request.getSession().setAttribute("saveExamen", examen);
      request.addParameters(parameters);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_MODIFIER));
  }


  @Test
  public void modifierExerciceApresSauvegardePerdue() throws Exception {
    this.mockMvc.perform(post("/EnseignantRefMatiere/ModifierExercice").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExerciceControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      final List<String> reponses = new ArrayList<>();
      reponses.add("Reponse1");
      reponses.add("Reponse2");
      final List<String> competences = new ArrayList<>();
      competences.add("Exploiter");
      final List<String> pourcentages = new ArrayList<>();
      pourcentages.add("19");
      final Map<String, String[]> parameters = new HashMap<>();
      final String[] tmp = { "12" };
      final String[] tmpDeux = { "Exploiter qdqsd" };
      final String[] tmpTrois = { "1.2" };
      final String[] tmpQuatre = { "Question sdfsdf" };
      final String[] tmpCinq = { "Repons sffe" };
      parameters.put("pourcentage1_1", tmp);
      parameters.put("choixCompetence1_1", tmpDeux);
      parameters.put("points1", tmpTrois);
      parameters.put("question1", tmpQuatre);
      parameters.put("reponse1", tmpCinq);
      request.getSession().setAttribute("examen",
          ExerciceControllerTest.this.examenDao.trouverTousLesExamens().get(0));
      request.addParameters(parameters);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_MODIFIER));
  }


  @Test
  public void afficherAjouterAutoEvaluation() throws Exception {
    this.mockMvc.perform(get("/EnseignantRefMatiere/AjouterAutoEvaluation"))
        .andExpect(status().isOk()).andExpect(view().name(VUE_AUTO_EVAL));
  }

  @Test
  public void ajouterAutoEvaluation() throws Exception {
    this.mockMvc.perform(post("/EnseignantRefMatiere/AjouterAutoEvaluation").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExerciceControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      final String examen =
          ExerciceControllerTest.this.examenDao.trouverTousLesExamens().get(0).getNom();
      request.addParameter("choixExamen", examen);
      final String question = "QuestionTest";
      request.addParameter("question", question);
      final String nbPoints = "1";
      request.addParameter("points", nbPoints);
      request.addParameter("reponses", "ReponseTest");
      request.addParameter("choixCompetence", "ChoixCompetenceTest");
      request.addParameter("pourcentages", "19");
      final String bonneReponses = "1";
      request.addParameter("bonneReponses", bonneReponses);
      return request;
    })).andDo(print()).andExpect(status().isOk()).andExpect(view().name(VUE_AUTO_EVAL));
  }

}
