package fr.eseo.atribus.forms;

import fr.eseo.atribus.beans.PasswordStorage;
import fr.eseo.atribus.dao.DaoException;
import fr.eseo.atribus.dao.UtilisateurDao;
import fr.eseo.atribus.entities.AdminSyst;
import fr.eseo.atribus.entities.DirecteurEtudes;
import fr.eseo.atribus.entities.DirecteurProgrammes;
import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Enseignant;
import fr.eseo.atribus.entities.Utilisateur;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * La classe ModifierUtilisateurForm.
 */
public class ModifierUtilisateurForm extends UtilisateurForm {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(ModifierUtilisateurForm.class.getName());

  /**
   * Traitement du formulaire de modification d'un utilisateur.
   */
  public ModifierUtilisateurForm() {
    super();
  }

  /**
   * Accesseur en lecture sur le paramètre resultat.
   *
   * @return le paramètre resultat
   */
  public String getResultat() {
    return this.resultat;
  }

  /**
   * Accesseur en lecture sur le paramètre erreurs.
   *
   * @return le paramètre erreurs
   */
  public Map<String, String> getErreurs() {
    return this.erreurs;
  }

  /**
   * Suppression d'un utilisateur.
   * 
   * @param request .
   */
  public void modifierUtilisateur(HttpServletRequest request) {
    final String loginPrecedent =
        UtilisateurForm.getValeurChamp(request, UtilisateurForm.NOM_BOUTON_SUPPRIMER);
    final String nouveauLogin =
        UtilisateurForm.getValeurChamp(request, UtilisateurForm.CHAMP_LOGIN);
    final String password = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CHAMP_PASS);
    final String nom = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CHAMP_NOM);
    final String prenom = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CHAMP_PRENOM);
    final String email = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CHAMP_EMAIL);

    final String cbAdminSyst = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_AS);
    final String cbDirecteurEtudes = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_DE);
    final String cbDirecteurProgrammes =
        UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_DP);
    final String cbEnseignant = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_EN);
    UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_EURE);
    UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_ERM);
    final String cbEleve = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_EL);

    String hash = null;
    String login = null;

    try {
      this.traiterLoginPrecedent(loginPrecedent);
      if (!loginPrecedent.equals(nouveauLogin)) {
        this.traiterLoginNouveau(nouveauLogin);
        login = nouveauLogin;
      }
      if (password != null) {
        hash = this.traiterPassword(password);
      }
      this.traiterEmail(email);

      if (this.erreurs.isEmpty()) {
        final Utilisateur utilisateur = new Utilisateur();
        utilisateur.setLogin(login);
        utilisateur.setPassword(hash);
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);

        /** Attention il faut modifier l'utilisateur avant de modifier les droits */
        this.utilisateurDao.modifier(loginPrecedent, utilisateur);

        /** Ensuite on remet le login a jour **/
        this.miseAJourLogin(loginPrecedent, nouveauLogin, utilisateur);

        /* Puis on modifie les droits */



        final AdminSyst adminSyst = new AdminSyst(utilisateur);
        if (cbAdminSyst != null) {
          this.adminSystDao.ajouter(adminSyst);
        } else {
          this.adminSystDao.supprimer(adminSyst);
        }

        final DirecteurEtudes directeurEtudes = new DirecteurEtudes(utilisateur);
        if (cbDirecteurEtudes != null) {
          this.directeurEtudesDao.ajouter(directeurEtudes);
        } else {
          this.directeurEtudesDao.supprimer(directeurEtudes);
        }

        final DirecteurProgrammes directeurProgrammes = new DirecteurProgrammes(utilisateur);
        if (cbDirecteurProgrammes != null) {
          this.directeurProgrammesDao.ajouter(directeurProgrammes);
        } else {
          this.directeurProgrammesDao.supprimer(directeurProgrammes);
        }

        final Enseignant enseignant = new Enseignant(utilisateur);
        if (cbEnseignant != null) {
          this.enseignantDao.ajouter(enseignant);
        } else {
          this.enseignantDao.supprimer(enseignant);
        }

        final Eleve eleve = new Eleve(utilisateur);
        if (cbEleve != null) {
          this.eleveDao.ajouter(eleve);
        } else {
          this.eleveDao.supprimer(eleve);
        }


        this.resultat = "Succès de la modification.";
      } else {
        this.resultat = "Échec de la modification";
      }
    } catch (final DaoException bdde) {
      ModifierUtilisateurForm.LOGGER.log(Level.INFO, "Exception", bdde);
      this.resultat = "Échec de la suppression : une erreur imprévue est survenue,"
          + " merci de réessayer dans quelques instants.";
    }
  }

  /**
   * Mise a jour du login.
   *
   * @param loginPrecedent le login précédent
   * @param nouveauLogin le nouveau login
   * @param utilisateur l'utilisateur
   */
  private void miseAJourLogin(final String loginPrecedent, final String nouveauLogin,
      final Utilisateur utilisateur) {
    if (!loginPrecedent.equals(nouveauLogin)) {
      utilisateur.setLogin(nouveauLogin);
    } else {
      utilisateur.setLogin(loginPrecedent);
    }
  }


  /**
   * Traiter login précédent.
   *
   * @param login le login précédent
   */
  private void traiterLoginPrecedent(String login) {
    try {
      this.validationLoginPrecedent(login);
    } catch (final FormValidationException fve) {
      ModifierUtilisateurForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur(UtilisateurForm.NOM_BOUTON_SUPPRIMER, fve.getMessage());
    }
  }

  /**
   * Traiter le nouveau login.
   *
   * @param login le nouveau login
   */
  private void traiterLoginNouveau(String login) {
    try {
      this.validationLoginNouveau(login);
    } catch (final FormValidationException fve) {
      ModifierUtilisateurForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur(UtilisateurForm.NOM_BOUTON_SUPPRIMER, fve.getMessage());
    }
  }

  /**
   * Traiter password.
   *
   * @param password le password
   * @return Le paramètre password hashé
   */
  private String traiterPassword(String password) {
    String passwordHash = null;
    try {
      this.validationPassword(password);
      passwordHash = PasswordStorage.createHash(password);
    } catch (final FormValidationException fve) {
      ModifierUtilisateurForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur(UtilisateurForm.CHAMP_PASS, fve.getMessage());
    } catch (final Exception exc) {
      ModifierUtilisateurForm.LOGGER.log(Level.INFO, EXCEPTION, exc);
      this.setErreur(UtilisateurForm.CHAMP_PASS, "Impossible de générer le hash du mot de passe.");
    }

    return passwordHash;
  }

  /**
   * Traiter l'email.
   *
   * @param email l'email
   */
  private void traiterEmail(String email) {
    try {
      this.validationEmail(email);
    } catch (final Exception exc) {
      ModifierUtilisateurForm.LOGGER.log(Level.INFO, EXCEPTION, exc);
      this.setErreur(UtilisateurForm.CHAMP_EMAIL, exc.getMessage());
    }
  }

  /**
   * Validation email.
   *
   * @param email l'email
   * @throws FormValidationException de type form validation exception
   */
  private void validationEmail(String email) throws FormValidationException {
    if (email == null) {
      throw new FormValidationException("Merci de saisir une adresse mail.");
    }
    if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
      throw new FormValidationException("Merci de saisir une adresse mail valide.");
    }
  }

  /**
   * Validation du password.
   *
   * @param password le password
   * @throws FormValidationException de type form validation exception
   */
  private void validationPassword(String password) throws FormValidationException {
    if (password != null) {
      if (password.length() < 5) {
        throw new FormValidationException(
            "Les mots de passe doivent contenir au moins 5 caractères.");
      }
    } else {
      throw new FormValidationException("Merci de saisir un mot de passe.");
    }
  }



  /**
   * Validation login précédent.
   *
   * @param login le login
   * @throws FormValidationException de type form validation exception
   */
  private void validationLoginPrecedent(String login) throws FormValidationException {
    if (login == null || login.length() < 5) {
      throw new FormValidationException(
          "Le nom d'utilisateur doit contenir au moins 5 caractères.");
    } else if (this.utilisateurDao.trouverParLogin(login) == null) {
      throw new FormValidationException("Utilisateur non trouvé dans la base de données.");
    }
  }

  /**
   * Validation du nouveau login.
   *
   * @param login le login
   * @throws FormValidationException de type form validation exception
   */
  private void validationLoginNouveau(String login) throws FormValidationException {
    if (login == null || login.length() < 5) {
      throw new FormValidationException(
          "Le nom d'utilisateur doit contenir au moins 5 caractères.");
    } else if (this.utilisateurDao.trouverParLogin(login) != null) {
      throw new FormValidationException("Le login existe déjà dans la base de données.");
    }
  }

  /**
   * Accesseur en lecture sur le paramètre utilisateur bdd.
   *
   * @return le paramètre utilisateur bdd
   */
  public UtilisateurDao getUtilisateurBdd() {
    return this.utilisateurDao;
  }

  /**
   * Accesseur en écriture sur le paramètre résultat.
   *
   * @param resultat le nouveau paramètre résultat
   */
  public void setResultat(String resultat) {
    this.resultat = resultat;
  }

}

