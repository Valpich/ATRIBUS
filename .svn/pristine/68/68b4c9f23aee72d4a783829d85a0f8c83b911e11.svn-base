package fr.eseo.atribus.controller;

import fr.eseo.atribus.dao.CompetenceDao;
import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.entities.Competence;
import fr.eseo.atribus.entities.DirecteurEtudes;
import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Enseignant;
import fr.eseo.atribus.entities.EnseignantRefMatiere;
import fr.eseo.atribus.entities.EnseignantRefUe;
import fr.eseo.atribus.entities.Utilisateur;
import fr.eseo.atribus.forms.ConvoquerEtudiantForm;
import fr.eseo.atribus.forms.SuggererAjouterCompetenceForm;
import fr.eseo.atribus.forms.SuggererNouvelleCompetenceForm;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * La Classe MailController.
 */
@Controller
public class MailController {

  /** La constante ATT_FORM. */
  public static final String ATT_FORM = "form";

  /** La constante ATT_SESSION_USER. */
  public static final String ATT_SESSION_USER = "sessionUtilisateur";

  /** La constante VUE_SUGGERER_AJOUTER. */
  private static final String VUE_SUGGERER_AJOUTER = "suggererCompetenceExistantes";

  /** La constante VUE_SUGGERER_NOUVELLE. */
  private static final String VUE_SUGGERER_NOUVELLE = "suggererNouvelleCompetence";

  /** La constante VUE_CONVOQUER. */
  private static final String VUE_CONVOQUER = "DirecteurEtudes/convoquerEtudiant";

  /** La constante BEANS_DAO. */
  private static final String BEANS_DAO = "beansDao";

  /** La constante ELEVES. */
  private static final String ELEVES = "eleves";

  /** La constante ELEVE_DAO. */
  private static final String ELEVE_DAO = "eleveDao";

  /** La constante COMPETENCE_DAO. */
  private static final String COMPETENCE_DAO = "competenceDao";

  /** La constante REDIRECT_INDEX. */
  private static final String REDIRECT_INDEX = "redirect:/index";

  /** La constante COMPETENCES. */
  private static final String COMPETENCES = "competences";

  /** La variable convoquer etudiant form. */
  @Autowired(required = true)
  private ConvoquerEtudiantForm convoquerEtudiantForm;

  /** La variable suggerer nouvelle competence form. */
  @Autowired(required = true)
  private SuggererNouvelleCompetenceForm suggererNouvelleCompetenceForm;

  /** La variable suggerer ajouter competence form. */
  @Autowired(required = true)
  private SuggererAjouterCompetenceForm suggererAjouterCompetenceForm;

  /**
   * Afficher convoquer etudiant.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/DirecteurEtudes/ConvoquerEtudiant", method = RequestMethod.GET)
  protected ModelAndView afficherConvoquerEtudiant() {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory(BEANS_DAO);
    final List<Eleve> eleves = ((EleveDao) bf.getFactory().getBean(ELEVE_DAO)).recupererListe();
    attributsRequete.put(ELEVES, eleves);
    return new ModelAndView(VUE_CONVOQUER, attributsRequete);
  }

  /**
   * Convoquer etudiant.
   *
   * @param request la requete
   * @param eleve l'eleve
   * @param date la date
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/DirecteurEtudes/ConvoquerEtudiant", method = RequestMethod.POST)
  protected ModelAndView convoquerEtudiant(HttpServletRequest request,
      @RequestParam("choixEleve") String eleve, @RequestParam("date") String date) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    @SuppressWarnings("unchecked")
    final List<Utilisateur> listeUtilisateur =
        (List<Utilisateur>) request.getSession().getAttribute(ATT_SESSION_USER);
    DirecteurEtudes de = null;
    for (final Utilisateur utilisateur : listeUtilisateur) {
      if (utilisateur.getClass().equals(DirecteurEtudes.class)) {
        de = (DirecteurEtudes) utilisateur;
      }
    }
    if (de != null) {
      this.convoquerEtudiantForm.convoquerEleve(de, eleve, date);
      attributsRequete.put(ATT_FORM, this.convoquerEtudiantForm);
      final BeanFactoryReference bf =
          SingletonBeanFactoryLocator.getInstance().useBeanFactory(BEANS_DAO);
      final List<Eleve> eleves = ((EleveDao) bf.getFactory().getBean(ELEVE_DAO)).recupererListe();
      attributsRequete.put(ELEVES, eleves);
      return new ModelAndView(VUE_CONVOQUER, attributsRequete);
    }
    return new ModelAndView(REDIRECT_INDEX);
  }

  /**
   * Afficher suggerer competence nouvelle.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/SuggererCompetence", method = RequestMethod.GET)
  protected ModelAndView afficherSuggererCompetenceNouvelle() {
    return new ModelAndView(VUE_SUGGERER_NOUVELLE);
  }

  /**
   * Suggerer une nouvelle competence.
   *
   * @param request la requete
   * @param competence la competence
   * @param commentaire le commentaire
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Eleve/SuggererCompetence", method = RequestMethod.POST)
  protected ModelAndView suggererNouvelleCompetence(HttpServletRequest request,
      @RequestParam("competence") String competence,
      @RequestParam("commentaire") String commentaire) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    @SuppressWarnings("unchecked")
    final List<Utilisateur> listeUtilisateur =
        (List<Utilisateur>) request.getSession().getAttribute(ATT_SESSION_USER);
    Eleve eleve = null;
    for (final Utilisateur utilisateur : listeUtilisateur) {
      if (utilisateur.getClass().equals(Eleve.class)) {
        eleve = (Eleve) utilisateur;
      }
    }
    if (eleve != null) {
      this.suggererNouvelleCompetenceForm.suggererCompetence(eleve, competence, commentaire);
      attributsRequete.put(ATT_FORM, this.suggererNouvelleCompetenceForm);
      return new ModelAndView(VUE_SUGGERER_NOUVELLE, attributsRequete);
    }
    return new ModelAndView(REDIRECT_INDEX);
  }

  /**
   * Afficher suggerer competence nouvelle ref ue.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefUE/SuggererCompetence", method = RequestMethod.GET)
  protected ModelAndView afficherSuggererCompetenceNouvelleRefUe() {
    return new ModelAndView(VUE_SUGGERER_NOUVELLE);
  }

  /**
   * Suggerer une nouvelle competence ref ue.
   *
   * @param request la requete
   * @param competence la competence
   * @param commentaire le commentaire
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefUE/SuggererCompetence", method = RequestMethod.POST)
  protected ModelAndView suggererNouvelleCompetenceRefUe(HttpServletRequest request,
      @RequestParam("competence") String competence,
      @RequestParam("commentaire") String commentaire) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    @SuppressWarnings("unchecked")
    final List<Utilisateur> listeUtilisateur =
        (List<Utilisateur>) request.getSession().getAttribute(ATT_SESSION_USER);
    EnseignantRefUe eure = null;
    for (final Utilisateur utilisateur : listeUtilisateur) {
      if (utilisateur.getClass().equals(EnseignantRefUe.class)) {
        eure = (EnseignantRefUe) utilisateur;
      }
    }
    if (eure != null) {
      this.suggererNouvelleCompetenceForm.suggererCompetence(eure, competence, commentaire);
      attributsRequete.put(ATT_FORM, this.suggererNouvelleCompetenceForm);
      return new ModelAndView(VUE_SUGGERER_NOUVELLE, attributsRequete);
    }
    return new ModelAndView(REDIRECT_INDEX);
  }

  /**
   * Afficher suggerer competence nouvelle enseignant.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Enseignant/SuggererCompetence", method = RequestMethod.GET)
  protected ModelAndView afficherSuggererCompetenceNouvelleEnseignant() {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory(BEANS_DAO);
    final List<Competence> competences =
        ((CompetenceDao) bf.getFactory().getBean(COMPETENCE_DAO)).trouverToutesLesCompetences();
    attributsRequete.put(COMPETENCES, competences);
    return new ModelAndView(VUE_SUGGERER_AJOUTER, attributsRequete);
  }

  /**
   * Suggerer une nouvelle competence enseignant.
   *
   * @param request la requete
   * @param competence la competence
   * @param commentaire le commentaire
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Enseignant/SuggererCompetence", method = RequestMethod.POST)
  protected ModelAndView suggererNouvelleCompetenceEnseignant(HttpServletRequest request,
      @RequestParam("choixCompetence") String competence,
      @RequestParam("commentaire") String commentaire) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    @SuppressWarnings("unchecked")
    final List<Utilisateur> listeUtilisateur =
        (List<Utilisateur>) request.getSession().getAttribute(ATT_SESSION_USER);
    Enseignant enseignant = null;
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory(BEANS_DAO);
    final List<Competence> competences =
        ((CompetenceDao) bf.getFactory().getBean(COMPETENCE_DAO)).trouverToutesLesCompetences();
    attributsRequete.put(COMPETENCES, competences);
    for (final Utilisateur utilisateur : listeUtilisateur) {
      if (utilisateur.getClass().equals(Enseignant.class)) {
        enseignant = (Enseignant) utilisateur;
      }
    }
    if (enseignant != null) {
      this.suggererAjouterCompetenceForm.suggererCompetence(enseignant, competence, commentaire);
      attributsRequete.put(ATT_FORM, this.suggererAjouterCompetenceForm);
      return new ModelAndView(VUE_SUGGERER_AJOUTER, attributsRequete);
    }
    return new ModelAndView(REDIRECT_INDEX);
  }

  /**
   * Afficher suggerer ajouter competence enseignant ref matiere.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefMatiere/SuggererCompetence", method = RequestMethod.GET)
  protected ModelAndView afficherSuggererAjouterCompetenceEnseignantRefMatiere() {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory(BEANS_DAO);
    final List<Competence> competences =
        ((CompetenceDao) bf.getFactory().getBean(COMPETENCE_DAO)).trouverToutesLesCompetences();
    attributsRequete.put(COMPETENCES, competences);
    return new ModelAndView(VUE_SUGGERER_AJOUTER, attributsRequete);
  }

  /**
   * Suggerer ajouter une competence enseignant ref matiere.
   *
   * @param request la requete
   * @param competence la competence
   * @param commentaire le commentaire
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/EnseignantRefMatiere/SuggererCompetence", method = RequestMethod.POST)
  protected ModelAndView suggererAjouterCompetenceEnseignantRefMatiere(HttpServletRequest request,
      @RequestParam("choixCompetence") String competence,
      @RequestParam("commentaire") String commentaire) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    @SuppressWarnings("unchecked")
    final List<Utilisateur> listeUtilisateur =
        (List<Utilisateur>) request.getSession().getAttribute(ATT_SESSION_USER);
    EnseignantRefMatiere enseignant = null;
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory(BEANS_DAO);
    final List<Competence> competences =
        ((CompetenceDao) bf.getFactory().getBean(COMPETENCE_DAO)).trouverToutesLesCompetences();
    attributsRequete.put(COMPETENCES, competences);
    for (final Utilisateur utilisateur : listeUtilisateur) {
      if (utilisateur.getClass().equals(EnseignantRefMatiere.class)) {
        enseignant = (EnseignantRefMatiere) utilisateur;
      }
    }
    if (enseignant != null) {
      this.suggererAjouterCompetenceForm.suggererCompetence(enseignant, competence, commentaire);
      attributsRequete.put(ATT_FORM, this.suggererAjouterCompetenceForm);
      return new ModelAndView(VUE_SUGGERER_AJOUTER, attributsRequete);
    }
    return new ModelAndView(REDIRECT_INDEX);
  }

  /**
   * Accesseur en lecture du paramètre convoquer etudiant form.
   *
   * @return le paramètre convoquer etudiant form
   */
  public ConvoquerEtudiantForm getConvoquerEtudiantForm() {
    return this.convoquerEtudiantForm;
  }

  /**
   * Accesseur en écriture du paramètre convoquer etudiant form.
   *
   * @param convoquerEtudiantForm le nouveau paramètre convoquer etudiant form
   */
  public void setConvoquerEtudiantForm(ConvoquerEtudiantForm convoquerEtudiantForm) {
    this.convoquerEtudiantForm = convoquerEtudiantForm;
  }

  /**
   * Accesseur en lecture du paramètre suggerer nouvelle competence form.
   *
   * @return le paramètre suggerer nouvelle competence form
   */
  public SuggererNouvelleCompetenceForm getSuggererNouvelleCompetenceForm() {
    return this.suggererNouvelleCompetenceForm;
  }

  /**
   * Accesseur en écriture du paramètre suggerer nouvelle competence form.
   *
   * @param suggererNouvelleCompetenceForm le nouveau paramètre suggerer nouvelle competence form
   */
  public void setSuggererNouvelleCompetenceForm(
      SuggererNouvelleCompetenceForm suggererNouvelleCompetenceForm) {
    this.suggererNouvelleCompetenceForm = suggererNouvelleCompetenceForm;
  }

  /**
   * Accesseur en lecture du paramètre suggerer ajouter competence form.
   *
   * @return le paramètre suggerer ajouter competence form
   */
  public SuggererAjouterCompetenceForm getSuggererAjouterCompetenceForm() {
    return this.suggererAjouterCompetenceForm;
  }

  /**
   * Accesseur en écriture du paramètre suggerer ajouter competence form.
   *
   * @param suggererAjouterCompetenceForm le nouveau paramètre suggerer ajouter competence form
   */
  public void setSuggererAjouterCompetenceForm(
      SuggererAjouterCompetenceForm suggererAjouterCompetenceForm) {
    this.suggererAjouterCompetenceForm = suggererAjouterCompetenceForm;
  }
}
