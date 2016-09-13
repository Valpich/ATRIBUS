package fr.eseo.atribus.controller;

import fr.eseo.atribus.beans.ExporteurCsv;
import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.dao.EvaluationDao;
import fr.eseo.atribus.dao.ExamenDao;
import fr.eseo.atribus.dao.MatiereDao;
import fr.eseo.atribus.entities.Competence;
import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Enseignant;
import fr.eseo.atribus.entities.EnseignantRefMatiere;
import fr.eseo.atribus.entities.Evaluation;
import fr.eseo.atribus.entities.Examen;
import fr.eseo.atribus.entities.Exercice;
import fr.eseo.atribus.entities.Matiere;
import fr.eseo.atribus.entities.Utilisateur;
import fr.eseo.atribus.filters.FiltreUtilisateur;
import fr.eseo.atribus.forms.AddExamenForm;
import fr.eseo.atribus.forms.ModifierExamenForm;
import fr.eseo.atribus.forms.RepondreAutoEvaluationForm;
import fr.eseo.atribus.forms.RepondreExamenForm;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet d'implementation de la classe ExamenController.
 */
@Controller
public class ExamenController {

  /** La constante CONF_BDD_FACTORY. */
  public static final String CONF_BDD_FACTORY = "DaoFactory";

  /** La constante ATT_EXAMEN. */
  public static final String ATT_EXAMEN = "examen";

  /** La constante ATT_CHOIX_EXAMEN. */
  public static final String ATT_CHOIX_EXAMEN = "choixExamen";

  /** La constante ATT_NOM_EXAMEN. */
  public static final String ATT_NOM_EXAMEN = "nomExamen";

  /** La constante ATT_EXAMENS. */
  public static final String ATT_EXAMENS = "examens";

  /** La constante ATT_EVALUATIONS. */
  public static final String ATT_EVALUATIONS = "evaluations";

  /** La constante ATT_FORM. */
  public static final String ATT_FORM = "form";

  /** La constante ATT_COMPETENCES. */
  public static final String ATT_COMPETENCES = "competences";

  /** La constante ATT_COMPETENCES_VIDE. */
  public static final String ATT_COMPETENCES_VIDE = "competencesVide";

  /** La constante ATT_MATIERES. */
  public static final String ATT_MATIERES = "matieres";

  /** La constante VUE. */
  public static final String VUE = "EnseignantRefMatiere/addExamen";

  /** La constante VUE_CONSULTER. */
  public static final String VUE_CONSULTER = "Eleve/consulterCompetencesExamen";

  /** La constante VUE_MODIFIER. */
  public static final String VUE_MODIFIER = "EnseignantRefMatiere/modifierExamen";

  /** La constante VUE_CORRIGER. */
  public static final String VUE_CORRIGER = "Enseignant/corrigerExamen";

  /** La constante VUE_PASSER. */
  public static final String VUE_PASSER = "Eleve/passerExamen";

  /** La constante VUE_PASSER_AUTO. */
  public static final String VUE_PASSER_AUTO = "Eleve/passerAutoEvaluation";

  /** La constante VUE_SUPPRIMER. */
  public static final String VUE_SUPPRIMER = "EnseignantRefMatiere/supprimerExamen";

  /** La constante VUE_HISTORIQUE_AUTO. */
  public static final String VUE_HISTORIQUE_AUTO = "EnseignantRefMatiere/historiqueAutoEvaluation";

  /** La constante ATT_SESSION_USER. */
  public static final String ATT_SESSION_USER = "sessionUtilisateur";

  /** La constante VUE_CONSULTER_NOTES. */
  public static final String VUE_CONSULTER_NOTES = "Eleve/consulterNotes";

  /** La constante VUE_AUCUNE_NOTE. */
  public static final String VUE_AUCUNE_NOTE = "Eleve/aucuneNote";

  /** La constante VUE_CONSULT_AUTOEVAL. */
  public static final String VUE_CONSULT_AUTOEVAL = "Eleve/consulterAutoEvaluation";

  /** La constante VUE_REDIRECT_INDEX. */
  public static final String VUE_REDIRECT_INDEX = "redirect:/index";

  /** La constante REPONSES. */
  public static final String REPONSES = "/**REPONSES**/";

  /** La constante VALIDES. */
  public static final String VALIDES = "/**VALIDES**/";

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(ExamenController.class.getName());

  /** La variable examen dao. */
  private ExamenDao examenDao;

  /** La variable matiere dao. */
  private MatiereDao matiereDao;

  /** La variable evaluation dao. */
  private EvaluationDao evaluationDao;

  /** La variable eleve dao. */
  private EleveDao eleveDao;

  /**
   * Initialisation.
   */
  @PostConstruct
  public void init() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Examen */
    this.examenDao = (ExamenDao) bf.getFactory().getBean("examenDao");
    /* Récupération d'une instance de notre DAO Matiere */
    this.matiereDao = (MatiereDao) bf.getFactory().getBean("matiereDao");
    /* Récupération d'une instance de notre DAO Eveluation */
    this.evaluationDao = (EvaluationDao) bf.getFactory().getBean("evaluationDao");
    /* Récupération d'une instance de notre DAO Eleve */
    this.eleveDao = (EleveDao) bf.getFactory().getBean("eleveDao");
  }


  /**
   * Afficher ajouter examen.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefMatiere/AjouterExamen", method = RequestMethod.GET)
  protected ModelAndView afficherAjouterExamen() {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    // On charge la liste des matieres
    attributsRequete.put(ExamenController.ATT_MATIERES, this.matiereDao.trouverToutesLesMatieres());
    return new ModelAndView(VUE, attributsRequete);
  }

  /**
   * Ajouter un examen.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefMatiere/AjouterExamen", method = RequestMethod.POST)
  protected ModelAndView ajouterExamen(HttpServletRequest request) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    // On charge la liste des matieres
    attributsRequete.put(ExamenController.ATT_MATIERES, this.matiereDao.trouverToutesLesMatieres());

    /* Préparation de l'objet formulaire */
    final AddExamenForm form = new AddExamenForm();

    /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
    final Examen examen = form.addExamen(request);

    /* Stockage du formulaire et du bean dans l'objet request */
    attributsRequete.put(ExamenController.ATT_FORM, form);
    attributsRequete.put(ExamenController.ATT_EXAMEN, examen);

    return new ModelAndView(VUE, attributsRequete);
  }

  /**
   * Afficher modifier examen.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefMatiere/ModifierExamen", method = RequestMethod.GET)
  protected ModelAndView afficherModifierExamen() {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    // On charge la liste des examens
    attributsRequete.put(ExamenController.ATT_EXAMENS, this.examenDao.trouverTousLesExamens());
    return new ModelAndView(VUE_MODIFIER, attributsRequete);
  }

  /**
   * Modifier un examen.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefMatiere/ModifierExamen", method = RequestMethod.POST)
  protected ModelAndView modifierExamen(HttpServletRequest request) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    // On charge la liste des matieres
    attributsRequete.put(ExamenController.ATT_MATIERES, this.matiereDao.trouverToutesLesMatieres());
    // On charge la liste des examens
    attributsRequete.put(ExamenController.ATT_EXAMENS, this.examenDao.trouverTousLesExamens());
    final String nomExamen = ExamenController.getValeurChamp(request, ATT_CHOIX_EXAMEN);
    final String nomExamenModifier = ExamenController.getValeurChamp(request, ATT_NOM_EXAMEN);
    final HttpSession session = request.getSession();
    if (nomExamen == null) {
      if (nomExamenModifier == null) {
        session.removeAttribute(ExamenController.ATT_EXAMEN);
        return new ModelAndView(VUE_MODIFIER, attributsRequete);
      } else {
        /* Préparation de l'objet formulaire */
        final ModifierExamenForm form = new ModifierExamenForm();
        form.modifierExamen(request);
        /* Stockage du formulaire et du bean dans l'objet request */
        attributsRequete.put(ExamenController.ATT_FORM, form);
        attributsRequete.put(ExamenController.ATT_EXAMENS, this.examenDao.trouverTousLesExamens());
        session.removeAttribute(ExamenController.ATT_EXAMEN);
        return new ModelAndView(VUE_MODIFIER, attributsRequete);
      }
    } else {
      session.setAttribute(ExamenController.ATT_EXAMEN, this.examenDao.trouverParNom(nomExamen));
      attributsRequete.put(ExamenController.ATT_EXAMEN, this.examenDao.trouverParNom(nomExamen));
    }
    return new ModelAndView(VUE_MODIFIER, attributsRequete);
  }

  /**
   * Afficher l'historique d'une auto evaluation.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefMatiere/HistoriqueAutoEvaluation",
      method = RequestMethod.GET)
  protected ModelAndView afficherHistoriqueAutoEvaluation() {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Examen> examens = this.examenDao.trouverTousLesExamens();
    final List<Examen> autoEvaluations = new ArrayList<>();
    for (final Examen examen : examens) {
      if (examen.getAutoEvaluation() && !examen.getExercices().isEmpty()) {
        autoEvaluations.add(examen);
      }
    }
    // On charge la liste des examens
    attributsRequete.put(ExamenController.ATT_EXAMENS, autoEvaluations);
    return new ModelAndView(VUE_HISTORIQUE_AUTO, attributsRequete);
  }

  /**
   * Historique auto evaluation.
   *
   * @param choixExamen le choix examen
   * @param dateDebut le date debut
   * @param dateFin le date fin
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefMatiere/HistoriqueAutoEvaluation",
      method = RequestMethod.POST)
  protected ModelAndView historiqueAutoEvaluation(@RequestParam("choixExamen") String choixExamen,
      @RequestParam("dateDebut") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
      @RequestParam("dateFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Examen> examens = this.examenDao.trouverTousLesExamens();
    final List<Examen> autoEvaluations = new ArrayList<>();
    for (final Examen examen : examens) {
      if (examen.getAutoEvaluation() && !examen.getExercices().isEmpty()) {
        autoEvaluations.add(examen);
      }
    }
    Examen examen = null;
    for (final Examen exam : autoEvaluations) {
      if (exam.getNom().equals(choixExamen)) {
        examen = exam;
      }
    }
    final int nombreUtilisation = this.examenDao.calculerHistorique(examen, dateDebut, dateFin);
    // On charge la liste des examens
    attributsRequete.put(ExamenController.ATT_EXAMENS, autoEvaluations);
    attributsRequete.put(ExamenController.ATT_EXAMEN, examen);
    attributsRequete.put("dateDebut", dateDebut);
    attributsRequete.put("dateFin", dateFin);
    attributsRequete.put("quantite", nombreUtilisation);
    return new ModelAndView(VUE_HISTORIQUE_AUTO, attributsRequete);
  }

  /**
   * Afficher passer examen.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/PasserExamen", method = RequestMethod.GET)
  protected ModelAndView afficherPasserExamen() {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    // On charge la liste des examens
    final List<Examen> examens = this.examenDao.trouverTousLesExamens();
    final List<Examen> evaluations = new ArrayList<>();
    for (final Examen examen : examens) {
      if (!examen.getAutoEvaluation()) {
        evaluations.add(examen);
      }
    }
    attributsRequete.put(ExamenController.ATT_EXAMENS, evaluations);
    return new ModelAndView(VUE_PASSER, attributsRequete);
  }

  /**
   * Passer un examen.
   *
   * @param choixExamen le choix d'examen
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/PasserExamen", method = RequestMethod.POST)
  protected ModelAndView passerExamen(@RequestParam("choixExamen") String choixExamen) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    // On charge la liste des examens
    final Examen examen = this.examenDao.trouverParNom(choixExamen);
    if (!examen.getAutoEvaluation()) {
      // Empeche la triche en ne transmettant pas les réponses aux élèves dans la requête !
      for (final Exercice exercice : examen.getExercices()) {
        exercice.setReponse(null);
      }
      attributsRequete.put(ExamenController.ATT_EXAMEN, examen);
      return new ModelAndView(VUE_PASSER, attributsRequete);
    }
    return new ModelAndView(VUE_REDIRECT_INDEX);
  }

  /**
   * Valider un examen.
   *
   * @param request la requete
   * @param choixExamen le choix d'examen
   * @param reponses les reponses
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/ValiderExamen", method = RequestMethod.POST)
  protected ModelAndView validerExamen(HttpServletRequest request,
      @RequestParam("choixExamen") String choixExamen,
      @RequestParam("reponses") List<String> reponses) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Utilisateur> listeUtilisateur = this.recupererSession(request);
    final Eleve eleve = this.recupererEleve(listeUtilisateur);
    if (eleve != null) {
      final RepondreExamenForm form = new RepondreExamenForm();
      form.repondre(eleve, reponses, choixExamen, false);
      attributsRequete.put(ATT_FORM, form);
      return new ModelAndView(VUE_PASSER, attributsRequete);
    } else {
      return new ModelAndView(VUE_REDIRECT_INDEX);
    }
  }

  /**
   * Afficher passer auto evaluation.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/PasserAutoEvaluation", method = RequestMethod.GET)
  protected ModelAndView afficherPasserAutoEvaluation() {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Examen> examens = this.examenDao.trouverTousLesExamens();
    final List<Examen> autoEvaluations = new ArrayList<>();
    for (final Examen examen : examens) {
      if (examen.getAutoEvaluation()) {
        autoEvaluations.add(examen);
      }
    }
    // On charge la liste des examens
    attributsRequete.put(ExamenController.ATT_EXAMENS, autoEvaluations);
    return new ModelAndView(VUE_PASSER_AUTO, attributsRequete);
  }

  /**
   * Passer une auto evaluation.
   *
   * @param choixExamen le choix d'examen
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/PasserAutoEvaluation", method = RequestMethod.POST)
  protected ModelAndView passerAutoEvaluation(@RequestParam("choixAutoEval") String choixExamen) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    // On charge la liste des examens
    final Examen examen = this.examenDao.trouverParNom(choixExamen);
    if (examen == null) {
      return new ModelAndView(VUE_REDIRECT_INDEX);
    }
    if (examen.getAutoEvaluation()) {
      final HashMap<String, List<String>> reponses = new HashMap<>();
      final HashMap<String, List<String>> reponsesValides = new HashMap<>();
      this.parserReponsesQcm(examen, reponses, reponsesValides);
      attributsRequete.put(ExamenController.ATT_EXAMEN, examen);
      attributsRequete.put("reponsesQCM", reponses);
      return new ModelAndView(VUE_PASSER_AUTO, attributsRequete);
    }
    return new ModelAndView(VUE_REDIRECT_INDEX);
  }

  /**
   * Valider une auto evaluation.
   *
   * @param request la requete
   * @param choixExamen le choix d'examen
   * @param reponses les reponses
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/ValiderAutoEvaluation", method = RequestMethod.POST)
  protected ModelAndView validerAutoEvaluation(HttpServletRequest request,
      @RequestParam("choixAutoEval") String choixExamen,
      @RequestParam(value = "checkboxrep", required = false) List<String> reponses) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Utilisateur> listeUtilisateur = this.recupererSession(request);
    final Eleve eleve = this.recupererEleve(listeUtilisateur);
    if (eleve != null) {
      final RepondreAutoEvaluationForm form = new RepondreAutoEvaluationForm();
      form.repondre(eleve, reponses, choixExamen, true);
      attributsRequete.put(ATT_FORM, form);
      return new ModelAndView(VUE_PASSER, attributsRequete);
    } else {
      return new ModelAndView(VUE_REDIRECT_INDEX);
    }
  }

  /**
   * Parser reponses qcm.
   *
   * @param examen l'examen
   * @param reponses les reponses
   * @param reponsesValides les reponses valides
   */
  private void parserReponsesQcm(Examen examen, final HashMap<String, List<String>> reponses,
      final HashMap<String, List<String>> reponsesValides) {
    for (final Exercice exercice : examen.getExercices()) {
      final ArrayList<String> rep = new ArrayList<>();
      int index;
      String tmpRep = exercice.getReponse();
      if (tmpRep.contains(REPONSES)) {
        rep.add(tmpRep.substring(0, tmpRep.indexOf(REPONSES)));
        index = tmpRep.indexOf(REPONSES) + REPONSES.length();
        tmpRep = tmpRep.substring(index);
      } else {
        rep.add(tmpRep.substring(0, tmpRep.indexOf(VALIDES)));
        index = tmpRep.indexOf(VALIDES) + VALIDES.length();
        tmpRep = tmpRep.substring(index);
      }
      boolean parsingReponse = true;
      while (parsingReponse) {
        if (tmpRep.contains(REPONSES)) {
          rep.add(tmpRep.substring(0, tmpRep.indexOf(REPONSES)));
          index = tmpRep.indexOf(REPONSES) + REPONSES.length();
          tmpRep = tmpRep.substring(index);
        } else {
          parsingReponse = false;
          tmpRep = this.parserNumeroBonnesReponses(rep, tmpRep);
        }
      }
      reponses.put(exercice.getQuestion(), rep);
      reponsesValides.put(exercice.getQuestion(),
          new ArrayList<String>(Arrays.asList(tmpRep.split("/"))));
    }
  }


  /**
   * Parser numero bonnes reponses.
   *
   * @param rep les reponses
   * @param tmpRep le tmp rep
   * @return Le paramètre string
   */
  private String parserNumeroBonnesReponses(ArrayList<String> rep, String tmpRep) {
    int index;
    String tmpRepDeux = tmpRep;
    try {
      rep.add(tmpRepDeux.substring(0, tmpRepDeux.indexOf(VALIDES)));
      index = tmpRepDeux.indexOf(VALIDES) + VALIDES.length();
      tmpRepDeux = tmpRep.substring(index);
    } catch (final StringIndexOutOfBoundsException sioobe) {
      ExamenController.LOGGER.log(Level.INFO, "Exception", sioobe);
    }
    return tmpRepDeux;
  }

  /**
   * Afficher consulter auto evaluation.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/ConsulterAutoEvaluation", method = RequestMethod.GET)
  protected ModelAndView afficherConsulterAutoEvaluation(HttpServletRequest request) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Examen> examens = this.examenDao.trouverTousLesExamens();
    final List<Utilisateur> listeUtilisateur = this.recupererSession(request);
    final Eleve eleve = this.recupererEleve(listeUtilisateur);
    if (eleve != null) {
      final List<Examen> listeAutoEval = this.recupererAutoEvalEleve(examens, eleve);
      // On charge la liste des auto evaluation
      attributsRequete.put(ExamenController.ATT_EXAMENS, listeAutoEval);
    }
    return new ModelAndView(VUE_CONSULT_AUTOEVAL, attributsRequete);
  }


  /**
   * Recuperer auto eval eleve.
   *
   * @param examens les examens
   * @param eleve l'eleve
   * @return Le paramètre list
   */
  private List<Examen> recupererAutoEvalEleve(final List<Examen> examens, Eleve eleve) {
    final List<Examen> listeAutoEval = new ArrayList<>();
    for (final Examen examen : examens) {
      for (final Exercice exercice : examen.getExercices()) {
        this.recupererAutoEvalEleve(eleve, listeAutoEval, examen, exercice);
      }
    }
    return listeAutoEval;
  }

  /**
   * Recuperer auto eval eleve.
   *
   * @param eleve l'eleve
   * @param listeAutoEval la liste des auto-evaluation de l'eleve
   * @param examen le examen
   * @param exercice le exercice
   */
  private void recupererAutoEvalEleve(Eleve eleve, final List<Examen> listeAutoEval, Examen examen,
      Exercice exercice) {
    for (final Evaluation evaluation : this.evaluationDao.trouverToutesLesEvaluationsEleve(eleve)) {
      if (evaluation.getExercice().getId() == exercice.getId() && examen.getAutoEvaluation()
          && !listeAutoEval.contains(examen)) {
        listeAutoEval.add(examen);
      }
    }
  }

  /**
   * Consulter auto evaluation.
   *
   * @param request la requete
   * @param choixExamen le choix d'examen
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/ConsulterAutoEvaluation", method = RequestMethod.POST)
  protected ModelAndView consulterAutoEvaluation(HttpServletRequest request,
      @RequestParam("choixExamen") String choixExamen) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Examen> examens = this.examenDao.trouverTousLesExamens();
    final List<Utilisateur> listeUtilisateur = this.recupererSession(request);
    final Eleve eleve = this.recupererEleve(listeUtilisateur);
    if (eleve != null) {
      final List<Examen> listeAutoEval = this.recupererAutoEvalEleve(examens, eleve);
      final Examen exam = this.examenDao.trouverParNom(choixExamen);
      boolean verificationChoix = false;
      for (final Examen examen : listeAutoEval) {
        if (examen.getId() == exam.getId()) {
          verificationChoix = true;
        }
      }
      if (verificationChoix) {
        attributsRequete.put(ExamenController.ATT_EXAMEN, exam);
        final Map<Exercice, List<String>> reponsesEleve = this.mapperVersEleve(eleve, exam);
        final HashMap<String, List<String>> reponses = new HashMap<>();
        final HashMap<String, List<String>> reponsesValides = new HashMap<>();
        this.parserReponsesQcm(exam, reponses, reponsesValides);
        attributsRequete.put("reponsesQCM", reponses);
        attributsRequete.put("reponsesValides", reponsesValides);
        attributsRequete.put("reponsesEleve", reponsesEleve);
        return new ModelAndView(VUE_CONSULT_AUTOEVAL, attributsRequete);
      }
    }
    return new ModelAndView(VUE_REDIRECT_INDEX);
  }


  /**
   * Recuperer session.
   *
   * @param request la requete
   * @return Le paramètre list
   */
  @SuppressWarnings("unchecked")
  private List<Utilisateur> recupererSession(HttpServletRequest request) {
    return (List<Utilisateur>) request.getSession().getAttribute(ATT_SESSION_USER);
  }


  /**
   * Recuperer eleve.
   *
   * @param listeUtilisateur la liste des utilisateurs
   * @return Le paramètre eleve
   */
  private Eleve recupererEleve(final List<Utilisateur> listeUtilisateur) {
    Eleve eleve = null;
    for (final Utilisateur utilisateur : listeUtilisateur) {
      if (utilisateur.getClass().equals(Eleve.class)) {
        eleve = (Eleve) utilisateur;
      }
    }
    return eleve;
  }


  /**
   * Mapper vers eleve.
   *
   * @param eleve l'eleve
   * @param exam l'examan
   * @return Le paramètre map
   */
  private Map<Exercice, List<String>> mapperVersEleve(Eleve eleve, Examen exam) {
    final Map<Exercice, List<String>> reponsesEleve = new HashMap<>();
    for (final Exercice exercice : exam.getExercices()) {
      reponsesEleve.put(exercice, new ArrayList<>());
      for (final Evaluation evaluation : this.evaluationDao
          .trouverToutesLesEvaluationsEleve(eleve)) {
        if (evaluation.getExercice().getId() == exercice.getId()) {
          this.ajouterReponseEleveListe(reponsesEleve, exercice, evaluation);
        }
      }
    }
    return reponsesEleve;
  }


  /**
   * Ajouter reponse eleve liste.
   *
   * @param reponsesEleve les reponses de l'eleve
   * @param exercice l'exercice
   * @param evaluation l'evaluation
   */
  private void ajouterReponseEleveListe(final Map<Exercice, List<String>> reponsesEleve,
      Exercice exercice, Evaluation evaluation) {
    for (final String rep : evaluation.getReponse().split("/")) {
      if (!rep.isEmpty()) {
        reponsesEleve.get(exercice).add(rep);
      }
    }
  }

  /**
   * Afficher corriger examen.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Enseignant/CorrigerExamen", method = RequestMethod.GET)
  protected ModelAndView afficherCorrigerExamen(HttpServletRequest request) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Examen> examens = this.examenDao.trouverTousLesExamens();
    final List<Utilisateur> listeUtilisateur = this.recupererSession(request);
    Enseignant enseignant = null;
    enseignant = this.recupererEnseignant(listeUtilisateur, enseignant);
    if (enseignant != null) {
      final List<Examen> listeExamens = this.recupererListeExamensMatiere(examens, enseignant);
      final List<Examen> listeExamen = new ArrayList<>();
      for (Examen exam : listeExamens) {
        if (!exam.getAutoEvaluation()) {
          listeExamen.add(exam);
        }
      }
      // On charge la liste des examens
      attributsRequete.put(ExamenController.ATT_EXAMENS, listeExamen);
    }
    return new ModelAndView(VUE_CORRIGER, attributsRequete);
  }

  /**
   * Corriger un examen.
   *
   * @param request la requete
   * @param choixExamen le choix d'examen
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Enseignant/CorrigerExamen", method = RequestMethod.POST)
  protected ModelAndView corrigerExamen(HttpServletRequest request,
      @RequestParam("choixExamen") String choixExamen) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Utilisateur> listeUtilisateur = this.recupererSession(request);
    Enseignant enseignant = null;
    enseignant = this.recupererEnseignant(listeUtilisateur, enseignant);
    if (enseignant != null) {
      final Examen examen = this.examenDao.trouverParNom(choixExamen);
      final List<Evaluation> evaluations = this.recupererEvaluations(examen, enseignant);
      attributsRequete.put(ExamenController.ATT_EXAMEN, this.examenDao.trouverParNom(choixExamen));
      attributsRequete.put(ExamenController.ATT_EVALUATIONS, evaluations);
      final UUID idExamen = UUID.randomUUID();
      attributsRequete.put(ExamenController.ATT_CHOIX_EXAMEN, idExamen);
      request.getSession().setAttribute(ExamenController.ATT_CHOIX_EXAMEN, idExamen);
      request.getSession().setAttribute(ExamenController.ATT_EVALUATIONS,
          (ArrayList<Evaluation>) evaluations);
    }
    return new ModelAndView(VUE_CORRIGER, attributsRequete);
  }

  /**
   * Corriger un examen.
   *
   * @param request la requete
   * @param points les points
   * @param idExamen l'id fr l'examen
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Enseignant/ValiderCorrigerExamen", method = RequestMethod.POST)
  protected ModelAndView corrigerExamen(HttpServletRequest request,
      @RequestParam("points") List<String> points, @RequestParam("choixExamen") String idExamen) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Utilisateur> listeUtilisateur = this.recupererSession(request);
    Enseignant enseignant = null;
    enseignant = this.recupererEnseignant(listeUtilisateur, enseignant);
    if (enseignant != null && idExamen != null) {
      final UUID idExamenSession =
          (UUID) request.getSession().getAttribute(ExamenController.ATT_CHOIX_EXAMEN);
      if (idExamen.equals(idExamenSession.toString())) {
        @SuppressWarnings("unchecked")
        final List<Evaluation> evaluations =
            (List<Evaluation>) request.getSession().getAttribute(ExamenController.ATT_EVALUATIONS);
        this.majEvaluations(points, evaluations);
        attributsRequete.put("resultatModifications", evaluations);
        request.getSession().removeAttribute(ExamenController.ATT_EVALUATIONS);
        request.getSession().removeAttribute(ExamenController.ATT_CHOIX_EXAMEN);
      } else {
        return new ModelAndView(VUE_CORRIGER, attributsRequete);
      }
    }
    return new ModelAndView(VUE_CORRIGER, attributsRequete);
  }

  /**
   * Recuperer enseignant.
   *
   * @param listeUtilisateur la liste des utilisateurs
   * @param enseignant l'enseignant
   * @return Le paramètre enseignant
   */
  private Enseignant recupererEnseignant(final List<Utilisateur> listeUtilisateur,
      Enseignant enseignant) {

    for (final Utilisateur utilisateur : listeUtilisateur) {
      if (utilisateur.getClass().equals(Enseignant.class)) {
        return (Enseignant) utilisateur;
      }
    }
    return enseignant;
  }

  /**
   * Afficher supprimer examen.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefMatiere/SupprimerExamen", method = RequestMethod.GET)
  protected ModelAndView afficherSupprimerExamen(HttpServletRequest request) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Examen> examensTrier = new ArrayList<>();
    final List<Utilisateur> listeUtilisateur = this.recupererSession(request);
    final EnseignantRefMatiere erm = this.recupererErm(listeUtilisateur);
    this.trierExamens(erm, this.examenDao.trouverTousLesExamens(), examensTrier);
    // On charge la liste des examens
    attributsRequete.put(ExerciceController.ATT_EXAMENS, examensTrier);
    return new ModelAndView(VUE_SUPPRIMER, attributsRequete);
  }


  /**
   * Recuperer erm.
   *
   * @param listeUtilisateur la liste des utilisateurs
   * @return Le paramètre enseignant ref matiere
   */
  private EnseignantRefMatiere recupererErm(final List<Utilisateur> listeUtilisateur) {
    EnseignantRefMatiere erm = null;
    for (final Utilisateur utilisateur : listeUtilisateur) {
      if (utilisateur.getClass().equals(EnseignantRefMatiere.class)) {
        erm = (EnseignantRefMatiere) utilisateur;
      }
    }
    return erm;
  }

  /**
   * Supprimer un examen.
   *
   * @param request la requete
   * @param choixExamen le choix d'examen
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefMatiere/SupprimerExamen", method = RequestMethod.POST)
  protected ModelAndView supprimerExamen(HttpServletRequest request,
      @RequestParam("choixExamen") String choixExamen) {
    final List<Utilisateur> listeUtilisateur = this.recupererSession(request);
    final EnseignantRefMatiere erm = this.recupererErm(listeUtilisateur);
    if (choixExamen != null) {
      // On charge la liste des examens
      final List<Examen> examens = this.examenDao.trouverTousLesExamens();
      final List<Examen> examensTrier = new ArrayList<>();
      this.trierExamens(erm, examens, examensTrier);
      for (final Examen exam : examensTrier) {
        if (choixExamen.equals(exam.getNom())) {
          this.examenDao.supprimer(exam);
        }
      }
    }
    return this.afficherSupprimerExamen(request);
  }

  /**
   * Afficher consulter competences examen.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/ConsulterCompetencesExamen", method = RequestMethod.GET)
  protected ModelAndView afficherConsulterCompetencesExamen() {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    // On charge la liste des examens
    attributsRequete.put(ExamenController.ATT_EXAMENS, this.examenDao.trouverTousLesExamens());
    return new ModelAndView(VUE_CONSULTER, attributsRequete);
  }

  /**
   * Afficher resultat consulter competences examen.
   *
   * @param choixExamen le choix d'examen
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/ConsulterCompetencesExamen", method = RequestMethod.POST)
  protected ModelAndView afficherResultatConsulterCompetencesExamen(
      @RequestParam("choixExamen") String choixExamen) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    // On charge la liste des examens
    final Examen examen = this.examenDao.trouverParNom(choixExamen);
    final List<Competence> competences = new ArrayList<>();
    for (final Exercice exercice : examen.getExercices()) {
      @SuppressWarnings("rawtypes")
      final Iterator it = exercice.getCompetences().entrySet().iterator();
      this.trierCompetences(competences, it);
    }
    if (competences.isEmpty()) {
      attributsRequete.put(ExamenController.ATT_COMPETENCES_VIDE, " ");
    } else {
      attributsRequete.put(ExamenController.ATT_COMPETENCES, competences);
    }
    return new ModelAndView(VUE_CONSULTER, attributsRequete);
  }

  /**
   * Consulter notes.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/ConsulterNotes", method = RequestMethod.GET)
  protected ModelAndView consulterNotes(HttpServletRequest request) {
    final HttpSession session = request.getSession();
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    @SuppressWarnings("unchecked")
    final List<Utilisateur> listeUtilisateur =
        (List<Utilisateur>) session.getAttribute(FiltreUtilisateur.ATT_SESSION_USER);
    final Eleve eleve = this.recupererEleve(listeUtilisateur);

    final Map<Eleve, Map<Examen, Float>> notesEleveExamens = new HashMap<>();
    notesEleves(notesEleveExamens, this.eleveDao.recupererListe(),
        this.evaluationDao.trouverToutesLesEvaluations(), this.examenDao.trouverTousLesExamens());
    Map<Examen, Float> map = new HashMap<>();
    if (eleve != null) {
      for (final Entry<Eleve, Map<Examen, Float>> entry : notesEleveExamens.entrySet()) {
        if (entry.getKey().getId() == eleve.getId()) {
          map = entry.getValue();
        }
      }
    }
    attributsRequete.put("notesEleve", map);
    return new ModelAndView(VUE_CONSULTER_NOTES, attributsRequete);

  }

  /**
   * Afficher consulter progres.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/DirecteurEtudes/ConsulterProgres", method = RequestMethod.GET)
  protected ModelAndView afficherConsulterProgres() {

    final HashMap<String, Object> attributsRequete = new HashMap<>();
    attributsRequete.put("eleves", this.eleveDao.recupererListe());

    return new ModelAndView("DirecteurEtudes/consulterProgres", attributsRequete);
  }

  /**
   * Consulter les progres.
   *
   * @param idEleve l'id eleve
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/DirecteurEtudes/ConsulterProgres", method = RequestMethod.POST)
  protected ModelAndView consulterProgres(@RequestParam("idEleve") Integer idEleve) {

    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final List<Evaluation> evaluationList = this.evaluationDao.trouverToutesLesEvaluations();
    final List<Evaluation> evaluations = new ArrayList<>();
    Eleve eleve;


    eleve = this.eleveDao.trouverParId(idEleve);
    eleve.setId(this.eleveDao.recupererIdUtilisateur(eleve));

    for (final Evaluation eval : evaluationList) {
      if (eval.getEleve().getId() == eleve.getId()) {
        evaluations.add(eval);
      }
    }

    eleve.setCompetenceEleve(this.eleveDao.listerCompetenceEleve(eleve));

    attributsRequete.put("eleveProgres", eleve);
    attributsRequete.put("competenceEleve", eleve.getCompetenceEleve());

    return new ModelAndView("DirecteurEtudes/progresChart", attributsRequete);

  }

  /**
   * Trier competences.
   *
   * @param competences les competences
   * @param it l'itérateur
   */
  private void trierCompetences(List<Competence> competences,
      @SuppressWarnings("rawtypes") Iterator it) {
    while (it.hasNext()) {
      @SuppressWarnings("rawtypes")
      final Map.Entry pair = (Map.Entry) it.next();
      boolean present = false;
      for (final Competence competence : competences) {
        if (competence.getNom().contentEquals(((Competence) pair.getValue()).getNom())) {
          present = true;
        }
      }
      if (!present) {
        competences.add((Competence) pair.getValue());
      }
    }
  }

  /**
   * Trier examens.
   *
   * @param erm l'erm
   * @param examens les examens
   * @param examensTrier les examens triés
   */
  private void trierExamens(EnseignantRefMatiere erm, final List<Examen> examens,
      final List<Examen> examensTrier) {
    if (erm != null) {
      for (final Examen examen : examens) {
        if (examen.getMatiere().getId() == erm.getMatiere().getId()) {
          examensTrier.add(examen);
        }
      }
    }
  }


  /**
   * Maj evaluations.
   *
   * @param points les points
   * @param evaluations les evaluations
   */
  private void majEvaluations(List<String> points, List<Evaluation> evaluations) {
    final Set<Integer> tmp = new HashSet<>();
    for (final Evaluation evaluation : evaluations) {
      if (!tmp.contains(evaluation.getEleve().getIdEleve())) {
        tmp.add(evaluation.getEleve().getIdEleve());
      }
    }
    final int nombreQuestion = evaluations.size() / tmp.size();
    int questionActuelle = 0;
    int numeroEleve = 0;
    for (final Evaluation evaluation : evaluations) {
      try {
        evaluation
            .setNote(Float.parseFloat(points.get(questionActuelle * tmp.size() + numeroEleve)));
      } catch (final Exception exc) {
        ExamenController.LOGGER.log(Level.INFO, "Exception", exc);
      }
      questionActuelle++;
      if (questionActuelle == nombreQuestion) {
        questionActuelle = 0;
        numeroEleve++;
      }
    }
    this.evaluationDao.majEvaluation(evaluations);
  }


  /**
   * Recuperer evaluations.
   *
   * @param examen l'examen
   * @param enseignant l'enseignant
   * @return Le paramètre list
   */
  private List<Evaluation> recupererEvaluations(Examen examen, Enseignant enseignant) {
    final List<Evaluation> listeEvaluations = new ArrayList<>();
    for (final Evaluation evaluation : this.evaluationDao.trouverToutesLesEvaluations()) {
      for (final Matiere matiere : enseignant.getEnseigneMatiere()) {
        if (examen.getMatiere().getId() == matiere.getId()) {
          this.extraireExercice(examen, listeEvaluations, evaluation);
        }
      }
    }
    return listeEvaluations;
  }


  /**
   * Extraire exercice.
   *
   * @param examen l'examen
   * @param listeEvaluations la liste des evaluations
   * @param evaluation l'evaluation
   */
  private void extraireExercice(Examen examen, List<Evaluation> listeEvaluations,
      Evaluation evaluation) {
    for (final Exercice exercice : examen.getExercices()) {
      if (evaluation.getExercice().getId() == exercice.getId()) {
        listeEvaluations.add(evaluation);
      }
    }
  }

  /**
   * Recuperer liste examens matiere.
   *
   * @param examens les examens
   * @param enseignant l'enseignant
   * @return Le paramètre list
   */
  private List<Examen> recupererListeExamensMatiere(List<Examen> examens, Enseignant enseignant) {
    final List<Examen> listeExamens = new ArrayList<>();
    for (final Examen examen : examens) {
      for (final Matiere matiere : enseignant.getEnseigneMatiere()) {
        if (examen.getMatiere().getId() == matiere.getId()) {
          listeExamens.add(examen);
        }
      }
    }
    return listeExamens;
  }


  /**
   * Notes eleves.
   *
   * @param notesEleveExamens les notes des eleve aux examens
   * @param eleves les eleves
   * @param evaluations les evaluations
   * @param examens les examens
   */
  private static void notesEleves(Map<Eleve, Map<Examen, Float>> notesEleveExamens,
      List<Eleve> eleves, List<Evaluation> evaluations, List<Examen> examens) {

    parcourirMapping(eleves, evaluations, examens, notesEleveExamens);
  }

  /**
   * Parcourir mapping.
   *
   * @param eleves les eleves
   * @param evaluations les evaluations
   * @param examens les examens
   * @param notesEleveExamens les notes des eleves aux examens
   */
  private static void parcourirMapping(List<Eleve> eleves, List<Evaluation> evaluations,
      List<Examen> examens, Map<Eleve, Map<Examen, Float>> notesEleveExamens) {
    for (final Eleve eleve : eleves) {
      for (final Examen examen : examens) {
        if (!examen.getAutoEvaluation()) {
          ExporteurCsv.mapping(evaluations, notesEleveExamens, eleve, examen);
        }
      }
    }
  }


  /**
   * Accesseur en lecture du paramètre valeurChamp.
   *
   * @param request la requete
   * @param nomChamp le nom du champ
   * @return le paramètre valeur champ
   */
  /*
   * Méthode utilitaire qui retourne null si un champ est vide, et son contenu sinon.
   */
  private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
    final String valeur = request.getParameter(nomChamp);
    if (valeur == null || valeur.trim().length() == 0) {
      return null;
    } else {
      return valeur.trim();
    }
  }
}
