ackage fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.AdminSystDao;
import fr.eseo.atribus.dao.DaoException;
import fr.eseo.atribus.dao.DirecteurEtudesDao;
import fr.eseo.atribus.dao.DirecteurProgrammesDao;
import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.dao.EnseignantDao;
import fr.eseo.atribus.entities.AdminSyst;
import fr.eseo.atribus.entities.DirecteurEtudes;
import fr.eseo.atribus.entities.DirecteurProgrammes;
import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Enseignant;
import fr.eseo.atribus.entities.Utilisateur;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public class SwitchUserForm {
  private static final Logger LOGGER = Logger.getLogger(SwitchUserForm.class.getName());
  private static final String CHAMP_GROUPE_UTILISATEUR = "groupeUtilisateur";
  private static final String ERREUR_GROUPE_UTILISATEUR = "erreurGroupe";
  private static final String ERREUR_DROITS_UTILISATEUR = "erreurDroits";
  public static final String ATT_SESSION_USER = "sessionUtilisateur";
  private static final String EXCEPTION = "Exception";

  private String resultat;
  private final Map<String, String> erreurs = new HashMap<>();

  private final AdminSystDao adminSystDao;
  private final DirecteurEtudesDao directeurEtudesDao;
  private final DirecteurProgrammesDao directeurProgrammesDao;
  private final EnseignantDao enseignantDao;
  private final EleveDao eleveDao;

  public SwitchUserForm() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO AdminSyst */
    this.adminSystDao = (AdminSystDao) bf.getFactory().getBean("adminSystDao");
    /* Récupération d'une instance de notre DAO DirecteurEtudes */
    this.directeurEtudesDao = (DirecteurEtudesDao) bf.getFactory().getBean("directeurEtudesDao");
    /* Récupération d'une instance de notre DAO DirecteurProgrammes */
    this.directeurProgrammesDao =
        (DirecteurProgrammesDao) bf.getFactory().getBean("directeurProgrammesDao");
    /* Récupération d'une instance de notre DAO Enseignant */
    this.enseignantDao = (EnseignantDao) bf.getFactory().getBean("enseignantDao");
    /* Récupération d'une instance de notre DAO Eleve */
    this.eleveDao = (EleveDao) bf.getFactory().getBean("eleveDao");
  }

  public String getResultat() {
    return this.resultat;
  }

  public Map<String, String> getErreurs() {
    return this.erreurs;
  }

  public void switcherGroupeUtilisateur(HttpServletRequest request) {
    final String groupe =
        SwitchUserForm.getValeurChamp(request, SwitchUserForm.CHAMP_GROUPE_UTILISATEUR);

    final Utilisateur utilisateur =
        (Utilisateur) request.getSession().getAttribute(SwitchUserForm.ATT_SESSION_USER);

    try {

      switch (groupe) {
        case "AS":
          this.traiterConnexionAS(utilisateur, request);
          break;
        case "DE":
          this.traiterConnexionDE(utilisateur, request);
          break;
        case "DP":
          this.traiterConnexionDP(utilisateur, request);
          break;
        case "EN":
          this.traiterConnexionEN(utilisateur, request);
          break;
        case "EL":
          this.traiterConnexionEL(utilisateur, request);
          break;
        default:
          this.setErreur(SwitchUserForm.ERREUR_GROUPE_UTILISATEUR,
              "Groupe d'utilisateur incorrect.");
      }

    } catch (final DaoException fve) {
      this.resultat = "Échec de l'inscription : une erreur imprévue est survenue,"
          + " merci de réessayer dans quelques instants.";
      SwitchUserForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }

  }



  private void traiterConnexionAS(Utilisateur utilisateur, HttpServletRequest request) {
    try {
      this.validationConnexionAS(utilisateur, request);
    } catch (final FormValidationException fve) {
      this.setErreur(SwitchUserForm.ERREUR_DROITS_UTILISATEUR, fve.getMessage());
      SwitchUserForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }
  }

  private void traiterConnexionDE(Utilisateur utilisateur, HttpServletRequest request) {
    try {
      this.validationConnexionDE(utilisateur, request);
    } catch (final FormValidationException fve) {
      this.setErreur(SwitchUserForm.ERREUR_DROITS_UTILISATEUR, fve.getMessage());
      SwitchUserForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }
  }

  private void traiterConnexionDP(Utilisateur utilisateur, HttpServletRequest request) {
    try {
      this.validationConnexionDP(utilisateur, request);
    } catch (final FormValidationException fve) {
      this.setErreur(SwitchUserForm.ERREUR_DROITS_UTILISATEUR, fve.getMessage());
      SwitchUserForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }
  }

  private void traiterConnexionEN(Utilisateur utilisateur, HttpServletRequest request) {
    try {
      this.validationConnexionEN(utilisateur, request);
    } catch (final FormValidationException fve) {
      this.setErreur(SwitchUserForm.ERREUR_DROITS_UTILISATEUR, fve.getMessage());
      SwitchUserForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }
  }

  private void traiterConnexionEL(Utilisateur utilisateur, HttpServletRequest request) {
    try {
      this.validationConnexionEL(utilisateur, request);
    } catch (final FormValidationException fve) {
      this.setErreur(SwitchUserForm.ERREUR_DROITS_UTILISATEUR, fve.getMessage());
      SwitchUserForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }
  }



  private void validationConnexionAS(Utilisateur utilisateur, HttpServletRequest request)
      throws FormValidationException {
    final AdminSyst utilisateurCheck =
        this.adminSystDao.trouverParLoginHash(utilisateur.getLogin(), utilisateur.getPassword());
    if (utilisateurCheck != null) {
      request.getSession().setAttribute(SwitchUserForm.ATT_SESSION_USER, utilisateurCheck);
    } else {
      throw new FormValidationException(
          "Vous ne pouvez pas vous connecter en tant qu'administrateur système.");
    }
  }

  private void validationConnexionDE(Utilisateur utilisateur, HttpServletRequest request)
      throws FormValidationException {
    final DirecteurEtudes utilisateurCheck = this.directeurEtudesDao
        .trouverParLoginHash(utilisateur.getLogin(), utilisateur.getPassword());
    if (utilisateurCheck != null) {
      request.getSession().setAttribute(SwitchUserForm.ATT_SESSION_USER, utilisateurCheck);
    } else {
      throw new FormValidationException(
          "Vous ne pouvez pas vous connecter en tant que directeur des études.");
    }
  }

  private void validationConnexionDP(Utilisateur utilisateur, HttpServletRequest request)
      throws FormValidationException {
    final DirecteurProgrammes utilisateurCheck = this.directeurProgrammesDao
        .trouverParLoginHash(utilisateur.getLogin(), utilisateur.getPassword());
    if (utilisateurCheck != null) {
      request.getSession().setAttribute(SwitchUserForm.ATT_SESSION_USER, utilisateurCheck);
    } else {
      throw new FormValidationException(
          "Vous ne pouvez pas vous connecter en tant que directeur des programmes.");
    }
  }

  private void validationConnexionEN(Utilisateur utilisateur, HttpServletRequest request)
      throws FormValidationException {
    final Enseignant utilisateurCheck =
        this.enseignantDao.trouverParLoginHash(utilisateur.getLogin(), utilisateur.getPassword());
    if (utilisateurCheck != null) {
      request.getSession().setAttribute(SwitchUserForm.ATT_SESSION_USER, utilisateurCheck);
    } else {
      throw new FormValidationException("Vous ne pouvez pas vous connecter en tant qu'enseignant.");
    }
  }

  private void validationConnexionEL(Utilisateur utilisateur, HttpServletRequest request)
      throws FormValidationException {
    final Eleve utilisateurCheck =
        this.eleveDao.trouverParLoginHash(utilisateur.getLogin(), utilisateur.getPassword());
    if (utilisateurCheck != null) {
      request.getSession().setAttribute(SwitchUserForm.ATT_SESSION_USER, utilisateurCheck);
    } else {
      throw new FormValidationException("Vous ne pouvez pas vous connecter en tant qu'élève.");
    }
  }

  /*
   * Ajoute un message correspondant au champ spécifié à la map des erreurs.
   */
  private void setErreur(String champ, String message) {
    this.erreurs.put(champ, message);
  }

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

