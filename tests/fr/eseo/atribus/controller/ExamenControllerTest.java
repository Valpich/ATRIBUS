package fr.eseo.atribus.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.dao.EnseignantDao;
import fr.eseo.atribus.dao.EnseignantRefMatiereDao;
import fr.eseo.atribus.dao.EvaluationDao;
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
import java.util.List;
import java.util.UUID;

@Test
@ContextConfiguration({ "classpath:spring-dao.xml", "classpath:spring-daoBeans.xml",
    "classpath:beanRefFactory.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
public class ExamenControllerTest {

  public static final String ATT_EXAMEN = "examen";
  public static final String ATT_CHOIX_EXAMEN = "choixExamen";
  public static final String ATT_NOM_EXAMEN = "nomExamen";
  public static final String ATT_EXAMENS = "examens";
  public static final String ATT_EVALUATIONS = "evaluations";
  public static final String ATT_FORM = "form";
  public static final String ATT_COMPETENCES = "competences";
  public static final String ATT_COMPETENCES_VIDE = "competencesVide";
  public static final String ATT_MATIERES = "matieres";
  public static final String VUE = "EnseignantRefMatiere/addExamen";
  public static final String VUE_PASSER_AUTO = "Eleve/passerAutoEvaluation";
  public static final String VUE_CONSULTER = "Eleve/consulterCompetencesExamen";
  public static final String VUE_MODIFIER = "EnseignantRefMatiere/modifierExamen";
  public static final String VUE_CORRIGER = "Enseignant/corrigerExamen";
  public static final String VUE_PASSER = "Eleve/passerExamen";
  public static final String VUE_SUPPRIMER = "EnseignantRefMatiere/supprimerExamen";
  public static final String ATT_SESSION_USER = "sessionUtilisateur";
  public static final String VUE_CONSULTER_NOTES = "Eleve/consulterNotes";
  public static final String VUE_AUCUNE_NOTE = "Eleve/aucuneNote";
  public static final String VUE_HISTORIQUE_AUTO = "EnseignantRefMatiere/historiqueAutoEvaluation";
  public static final String VUE_REDIRECT_INDEX = "redirect:/index";
  public static final String VUE_CONSULT_AUTOEVAL = "Eleve/consulterAutoEvaluation";

  private MockMvc mockMvc;
  private ExamenDao examenDao;
  private EvaluationDao evaluationDao;
  private EleveDao eleveDao;
  private EnseignantDao enseignantDao;
  private EnseignantRefMatiereDao ermDao;

  @BeforeClass
  public void setup() {
    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/");
    viewResolver.setSuffix(".jsp");
    MockitoAnnotations.initMocks(this);
    final ExamenController examenController = new ExamenController();
    examenController.init();
    this.mockMvc =
        MockMvcBuilders.standaloneSetup(examenController).setViewResolvers(viewResolver).build();
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Examen */
    this.examenDao = (ExamenDao) bf.getFactory().getBean("examenDao");
    /* Récupération d'une instance de notre DAO Eveluation */
    this.evaluationDao = (EvaluationDao) bf.getFactory().getBean("evaluationDao");
    /* Récupération d'une instance de notre DAO Eleve */
    this.eleveDao = (EleveDao) bf.getFactory().getBean("eleveDao");
    /* Récupération d'une instance de notre DAO Enseignant */
    this.enseignantDao = (EnseignantDao) bf.getFactory().getBean("enseignantDao");
    /* Récupération d'une instance de notre DAO Enseignant Reférent Matiere */
    this.ermDao = (EnseignantRefMatiereDao) bf.getFactory().getBean("enseignantRefMatiereDao");
  }

  @Test
  public void afficherAjouterExamen() throws Exception {
    this.mockMvc.perform(get("/EnseignantRefMatiere/AjouterExamen")).andExpect(status().isOk())
        .andExpect(view().name(VUE));
  }

  @Test
  public void ajouterExamen() throws Exception {
    this.mockMvc.perform(post("/EnseignantRefMatiere/AjouterExamen").with(request -> {
      request.setParameter("choixMatiere", "Probabilités");
      request.setParameter("nom", UUID.randomUUID().toString());
      request.setParameter("autoEvaluation", "on");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE));
    this.mockMvc.perform(get("/EnseignantRefMatiere/AjouterExamen").with(request -> {
      request.setParameter("choixMatiere", "MP Microcontrôleur 1");
      request.setParameter("nom", UUID.randomUUID().toString());
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE));
  }

  @Test
  public void afficherModifierExamen() throws Exception {
    this.mockMvc.perform(get("/EnseignantRefMatiere/ModifierExamen")).andExpect(status().isOk())
        .andExpect(view().name(VUE_MODIFIER));
  }

  @Test
  public void modifierExamenEchec() throws Exception {
    this.mockMvc.perform(post("/EnseignantRefMatiere/ModifierExamen")).andExpect(status().isOk())
        .andExpect(view().name(VUE_MODIFIER));
  }

  @Test
  public void modifierExamen() throws Exception {

    this.mockMvc.perform(post("/EnseignantRefMatiere/ModifierExamen").with(request -> {
      request.setParameter("choixExamen", "Microprocesseur MP");
      request.setSession(new MockHttpSession());
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_MODIFIER));
    this.mockMvc.perform(post("/EnseignantRefMatiere/ModifierExamen").with(request -> {
      request.setParameter("nomExamen", "Microprocesseur MP");
      request.setSession(new MockHttpSession());
      request.getSession().setAttribute("examen",
          ExamenControllerTest.this.examenDao.trouverParNom("Microprocesseur MP"));
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_MODIFIER));

    this.mockMvc.perform(post("/EnseignantRefMatiere/ModifierExamen").with(request -> {
      request.setSession(new MockHttpSession());
      request.setParameter("choixMatiere", "Probabilités");
      request.getSession().setAttribute("examen",
          ExamenControllerTest.this.examenDao.trouverParNom("Microprocesseur MP"));
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_MODIFIER));

    this.mockMvc.perform(post("/EnseignantRefMatiere/ModifierExamen").with(request -> {
      request.setSession(new MockHttpSession());
      request.setParameter("choixMatiere", "Probabilités");
      request.setParameter("nomExamen", "a");
      request.getSession().setAttribute("examen",
          ExamenControllerTest.this.examenDao.trouverParNom("Microprocesseur MP"));
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_MODIFIER));

    this.mockMvc.perform(post("/EnseignantRefMatiere/ModifierExamen").with(request -> {
      request.setSession(new MockHttpSession());
      request.setParameter("choixMatiere", "Probabilités");
      request.setParameter("nomExamen", "Microprocesseur MP");
      request.getSession().setAttribute("examen",
          ExamenControllerTest.this.examenDao.trouverParNom("Microprocesseur MP"));
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_MODIFIER));
  }

  @Test
  public void afficherPasserExamen() throws Exception {
    this.mockMvc.perform(get("/Eleve/PasserExamen")).andExpect(status().isOk())
        .andExpect(view().name(VUE_PASSER));
  }

  @Test
  public void passerExamen() throws Exception {
    this.mockMvc.perform(post("/Eleve/PasserExamen").with(request -> {
      request.setParameter("choixExamen", "Microprocesseur MP");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name("Eleve/passerExamen"));

    this.mockMvc.perform(post("/Eleve/PasserExamen").with(request -> {
      request.setParameter("choixExamen", "Auto Evaluation Java");
      return request;
    })).andExpect(status().is(302)).andExpect(view().name("redirect:/index"));
  }

  @Test
  public void validerExamen() throws Exception {
    this.mockMvc.perform(post("/Eleve/ValiderExamen").with(request -> {
      request.setParameter("choixExamen", "Microprocesseur");
      final String reponse = "test";
      request.setParameter("reponses", reponse);
      request.setSession(new MockHttpSession());
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.eleveDao.trouverParId(1));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_PASSER));
  }

  @Test
  public void validerExamenMauvais() throws Exception {
    this.mockMvc.perform(post("/Eleve/ValiderExamen").with(request -> {
      request.setParameter("choixExamen", "Microprocesseur");
      final String reponse = "test";
      request.setParameter("reponses", reponse);
      request.setSession(new MockHttpSession());
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(new Utilisateur());
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().is(302)).andExpect(view().name("redirect:/index"));
  }

  @Test
  public void afficherCorrigerExamen() throws Exception {
    this.mockMvc.perform(get("/Enseignant/CorrigerExamen").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.enseignantDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_CORRIGER));
  }


  @Test
  public void afficherCorrigerExamenMauvais() throws Exception {
    this.mockMvc.perform(get("/Enseignant/CorrigerExamen").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.eleveDao.recupererListe().get(0));
      utilisateur.add(ExamenControllerTest.this.enseignantDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_CORRIGER));
  }

  @Test
  public void corrigerExamenMauvais() throws Exception {
    this.mockMvc.perform(post("/Enseignant/CorrigerExamen").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.eleveDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.setParameter("choixExamen", "Microprocesseur");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_CORRIGER));
  }

  @Test
  public void corrigerExamen() throws Exception {
    this.mockMvc.perform(post("/Enseignant/CorrigerExamen").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.enseignantDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.setParameter("choixExamen", "Microprocesseur");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_CORRIGER));
  }

  @Test
  public void validerCorrigerExamen() throws Exception {
    this.mockMvc.perform(post("/Enseignant/ValiderCorrigerExamen").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.enseignantDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.setParameter("points", "1");
      final UUID idExamen = UUID.randomUUID();
      request.setParameter("choixExamen", idExamen.toString());
      request.getSession().setAttribute("choixExamen", idExamen);
      request.getSession().setAttribute("evaluations",
          ExamenControllerTest.this.evaluationDao.trouverToutesLesEvaluations());
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_CORRIGER));
  }

  public void validerCorrigerExamenMauvais() throws Exception {
    this.mockMvc.perform(post("/Enseignant/ValiderCorrigerExamen").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.enseignantDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.setParameter("points", "1");
      final UUID idExamen = UUID.randomUUID();
      request.setParameter("choixExamen", idExamen.toString());
      request.getSession().setAttribute("choixExamen", UUID.randomUUID());
      request.getSession().setAttribute("evaluations",
          ExamenControllerTest.this.evaluationDao.trouverToutesLesEvaluations());
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_CORRIGER));
  }

  @Test
  public void afficherSupprimerExamen() throws Exception {
    this.mockMvc.perform(get("/EnseignantRefMatiere/SupprimerExamen").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_SUPPRIMER));
  }

  @Test
  public void supprimerExamen() throws Exception {
    this.mockMvc.perform(post("/EnseignantRefMatiere/SupprimerExamen").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.ermDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      request.setParameter("choixExamen", "Java");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_SUPPRIMER));
  }

  @Test
  public void afficherConsulterCompetencesExamen() throws Exception {
    this.mockMvc.perform(get("/Eleve/ConsulterCompetencesExamen")).andExpect(status().isOk())
        .andExpect(view().name(VUE_CONSULTER));
  }

  @Test
  public void consulterNotes() throws Exception {
    this.mockMvc.perform(get("/Eleve/ConsulterNotes").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.eleveDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name("Eleve/consulterNotes"));
  }

  @Test
  public void consulterCompetencesExamen1() throws Exception {

    this.mockMvc.perform(post("/Eleve/ConsulterCompetencesExamen").param("choixExamen", "Java DS"))
        .andExpect(status().isOk()).andExpect(view().name("Eleve/consulterCompetencesExamen"));

  }

  @Test
  public void consulterCompetencesExamen2() throws Exception {

    this.mockMvc
        .perform(
            post("/Eleve/ConsulterCompetencesExamen").param("choixExamen", "Microprocesseur MP"))
        .andExpect(status().isOk()).andExpect(view().name("Eleve/consulterCompetencesExamen"));
  }

  @Test
  public void afficherHistoriqueAutoEvaluation() throws Exception {
    this.mockMvc.perform(get("/EnseignantRefMatiere/HistoriqueAutoEvaluation"))
        .andExpect(status().isOk()).andExpect(view().name(VUE_HISTORIQUE_AUTO));
  }

  @Test
  public void historiqueAutoEvaluation() throws Exception {

    this.mockMvc
        .perform(post("/EnseignantRefMatiere/HistoriqueAutoEvaluation")
            .param("choixExamen", "Auto Evaluation Java").param("dateDebut", "2016-02-01")
            .param("dateFin", "2016-12-01"))
        .andExpect(status().isOk()).andExpect(view().name(VUE_HISTORIQUE_AUTO));
    try {
      this.mockMvc
          .perform(post("/EnseignantRefMatiere/HistoriqueAutoEvaluation")
              .param("choixExamen", "Auto FAIL Java").param("dateDebut", "2016-02-01")
              .param("dateFin", "2016-12-01"))
          .andExpect(status().isOk()).andExpect(view().name(VUE_HISTORIQUE_AUTO));
    } catch (final Exception exc) {
      // Test réussi
    }
  }

  @Test
  public void afficherConsulterProgres() throws Exception {
    this.mockMvc.perform(get("/DirecteurEtudes/ConsulterProgres")).andExpect(status().isOk())
        .andExpect(view().name("DirecteurEtudes/consulterProgres"));
  }

  @Test
  public void consulterProgres() throws Exception {
    this.mockMvc.perform(post("/DirecteurEtudes/ConsulterProgres").param("idEleve", "5"))
        .andExpect(status().isOk()).andExpect(view().name("DirecteurEtudes/progresChart"));
  }

  @Test
  public void afficherPasserAutoEvaluation() throws Exception {
    this.mockMvc.perform(get("/Eleve/PasserAutoEvaluation")).andExpect(status().isOk())
        .andExpect(view().name(VUE_PASSER_AUTO));
  }

  @Test
  public void passerPasserAutoEvaluation() throws Exception {
    this.mockMvc.perform(post("/Eleve/PasserAutoEvaluation").with(request -> {
      request.setParameter("choixAutoEval", "Auto Evaluation Java");
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_PASSER_AUTO));
    this.mockMvc.perform(post("/Eleve/PasserAutoEvaluation").with(request -> {
      request.setParameter("choixAutoEval", "Autovaluation Java");
      return request;
    })).andExpect(status().is(302)).andExpect(view().name(VUE_REDIRECT_INDEX));

    this.mockMvc.perform(post("/Eleve/PasserAutoEvaluation").with(request -> {
      request.setParameter("choixAutoEval", "Microprocesseur MP");
      return request;
    })).andExpect(status().is(302)).andExpect(view().name(VUE_REDIRECT_INDEX));
  }

  @Test
  public void validerAutoEvaluation() throws Exception {
    this.mockMvc.perform(post("/Eleve/ValiderAutoEvaluation").with(request -> {
      request.setParameter("choixAutoEval", "Auto Evaluation Java");
      request.setParameter("checkboxrep", "Qu'est ce qu'une Interface en Java ?/////2");
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.eleveDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_PASSER));
    this.mockMvc.perform(post("/Eleve/ValiderAutoEvaluation").with(request -> {
      request.setParameter("choixAutoEval", "Auto Evaluation Java");
      request.setParameter("checkboxrep", "");
      final List<Utilisateur> utilisateur = new ArrayList<>();
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().is(302)).andExpect(view().name(VUE_REDIRECT_INDEX));

  }

  @Test
  public void consulterAutoEvaluationGet() throws Exception {
    this.mockMvc.perform(get("/Eleve/ConsulterAutoEvaluation").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.eleveDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_CONSULT_AUTOEVAL));
  }

  @Test
  public void consulterAutoEvaluationPost() throws Exception {
    this.mockMvc.perform(post("/Eleve/ConsulterAutoEvaluation").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(ExamenControllerTest.this.eleveDao.recupererListe().get(0));
      request.setParameter("choixExamen", "Auto Evaluation Java");
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().isOk()).andExpect(view().name(VUE_CONSULT_AUTOEVAL));

    this.mockMvc.perform(post("/Eleve/ConsulterAutoEvaluation").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      request.setParameter("choixExamen", "Auto Evaluation Java");
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    })).andExpect(status().is(302)).andExpect(view().name(VUE_REDIRECT_INDEX));
  }
}
