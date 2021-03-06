package fr.eseo.atribus.forms;

import fr.eseo.atribus.beans.PasswordStorage;
import fr.eseo.atribus.dao.AdminSystDao;
import fr.eseo.atribus.dao.DaoException;
import fr.eseo.atribus.dao.DirecteurEtudesDao;
import fr.eseo.atribus.dao.DirecteurProgrammesDao;
import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.dao.EnseignantDao;
import fr.eseo.atribus.dao.UtilisateurDao;
import fr.eseo.atribus.entities.AdminSyst;
import fr.eseo.atribus.entities.DirecteurEtudes;
import fr.eseo.atribus.entities.DirecteurProgrammes;
import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Enseignant;
import fr.eseo.atribus.entities.EnseignantRefMatiere;
import fr.eseo.atribus.entities.EnseignantRefUe;
import fr.eseo.atribus.entities.Utilisateur;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * La classe AddUserForm.
 */
public class AddUserForm extends UtilisateurForm {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(AddUserForm.class.getName());

  /**
   * Instancie un nouveau add user form.
   */
  public AddUserForm() {
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
   * Adds the user.
   *
   * @param request la request
   * @return Le paramètre utilisateur
   */
  public Utilisateur addUser(HttpServletRequest request) {
    final String login = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CHAMP_LOGIN);
    final String password = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CHAMP_PASS);
    final String nom = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CHAMP_NOM);
    final String prenom = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CHAMP_PRENOM);
    final String email = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CHAMP_EMAIL);
    final String cbAdminSyst = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_AS);
    final String cbDirecteurEtudes = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_DE);
    final String cbDirecteurProgrammes =
        UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_DP);
    final String cbEnseignant = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_EN);
    final String cbEnseignantRefUe =
        UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_EURE);
    final String cbEnseignantRefMat =
        UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_ERM);
    final String cbEleve = UtilisateurForm.getValeurChamp(request, UtilisateurForm.CB_EL);



    Utilisateur utilisateur = new Utilisateur();

    try {
      this.traiterLogin(login, utilisateur);
      this.traiterPassword(password, utilisateur);
      this.traiterNom(nom, utilisateur);
      this.traiterPrenom(prenom, utilisateur);
      this.traiterEmail(email, utilisateur);
      if (this.erreurs.isEmpty()) {

        utilisateur = this.utilisateurDao.ajouter(utilisateur);

        this.ajouterDroits(cbAdminSyst, cbDirecteurEtudes, cbDirecteurProgrammes, cbEnseignant,
            cbEnseignantRefUe, cbEnseignantRefMat, cbEleve, utilisateur);



        this.resultat = "Succès de l'inscription.";
      } else {
        this.resultat = "Échec de l'inscription.";
      }
    } catch (final DaoException bdde) {
      AddUserForm.LOGGER.log(Level.INFO, "Exception", bdde);
      this.resultat = "Échec de l'inscription : une erreur imprévue est survenue,"
          + " merci de réessayer dans quelques instants.";
    }

    return utilisateur;
  }

  /**
   * Ajouter droits.
   *
   * @param cbAdminSyst le cb admin syst
   * @param cbDirecteurEtudes le cb directeur etudes
   * @param cbDirecteurProgrammes le cb directeur programmes
   * @param cbEnseignant le cb enseignant
   * @param cbEnseignantRefUe le cb enseignant ref ue
   * @param cbEnseignantRefMat le cb enseignant ref mat
   * @param cbEleve le cb eleve
   * @param utilisateur l'utilisateur
   */
  private void ajouterDroits(final String cbAdminSyst, final String cbDirecteurEtudes,
      final String cbDirecteurProgrammes, final String cbEnseignant, final String cbEnseignantRefUe,
      final String cbEnseignantRefMat, final String cbEleve, Utilisateur utilisateur) {
    if (cbAdminSyst != null) { // AS checké
      final AdminSyst adminSyst = new AdminSyst(utilisateur);
      this.adminSystDao.ajouter(adminSyst);
    }

    if (cbDirecteurEtudes != null) { // DE checké
      final DirecteurEtudes directeurEtudes = new DirecteurEtudes(utilisateur);
      this.directeurEtudesDao.ajouter(directeurEtudes);
    }

    if (cbDirecteurProgrammes != null) { // DP checké
      final DirecteurProgrammes directeurProgrammes = new DirecteurProgrammes(utilisateur);
      this.directeurProgrammesDao.ajouter(directeurProgrammes);
    }

    if (cbEnseignant != null) { // EN checké
      final Enseignant enseignant = new Enseignant(utilisateur);
      this.enseignantDao.ajouter(enseignant);

      this.ajouterErmEtErue(cbEnseignantRefUe, cbEnseignantRefMat, utilisateur);

    }



    if (cbEleve != null) { // EN checké
      final Eleve eleve = new Eleve(utilisateur);


      this.eleveDao.ajouter(eleve);
    }
  }

  /**
   * Ajouter erm et erue.
   *
   * @param cbEnseignantRefUe le cb enseignant ref ue
   * @param cbEnseignantRefMat le cb enseignant ref mat
   * @param utilisateur l'utilisateur
   */
  private void ajouterErmEtErue(final String cbEnseignantRefUe, final String cbEnseignantRefMat,
      Utilisateur utilisateur) {
    if (cbEnseignantRefUe != null) { // EN checké
      final EnseignantRefUe enseignantRefUe = new EnseignantRefUe(utilisateur);
      this.enseignantRefUeDao.ajouter(enseignantRefUe);
    }

    if (cbEnseignantRefMat != null) { // EN checké
      final EnseignantRefMatiere enseignantRefMat = new EnseignantRefMatiere(utilisateur);
      this.enseignantRefMatiereDao.ajouter(enseignantRefMat);
    }
  }

  /**
   * Traiter login.
   *
   * @param login le login
   * @param utilisateur l'utilisateur
   */
  private void traiterLogin(String login, Utilisateur utilisateur) {
    try {
      this.validationLogin(login);
    } catch (final FormValidationException fve) {
      AddUserForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur(UtilisateurForm.CHAMP_LOGIN, fve.getMessage());
    }
    utilisateur.setLogin(login);
  }

  /**
   * Traiter password.
   *
   * @param password le password
   * @param utilisateur l'utilisateur
   */
  private void traiterPassword(String password, Utilisateur utilisateur) {
    try {
      String passwordHash;
      this.validationPassword(password);
      passwordHash = PasswordStorage.createHash(password);
      utilisateur.setPassword(passwordHash);
    } catch (final FormValidationException fve) {
      AddUserForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur(UtilisateurForm.CHAMP_PASS, fve.getMessage());
    } catch (final Exception exc) {
      AddUserForm.LOGGER.log(Level.INFO, EXCEPTION, exc);
      this.setErreur(UtilisateurForm.CHAMP_PASS, "Impossible de générer le hash du mot de passe.");
    }
  }

  /**
   * Traiter nom.
   *
   * @param nom le nom
   * @param utilisateur l'utilisateur
   */
  private void traiterNom(String nom, Utilisateur utilisateur) {
    utilisateur.setNom(nom);
  }

  /**
   * Traiter prenom.
   *
   * @param prenom le prenom
   * @param utilisateur l'utilisateur
   */
  private void traiterPrenom(String prenom, Utilisateur utilisateur) {
    utilisateur.setPrenom(prenom);
  }

  /**
   * Traiter email.
   *
   * @param email le email
   * @param utilisateur l'utilisateur
   */
  private void traiterEmail(String email, Utilisateur utilisateur) {
    try {
      this.validationEmail(email);
      utilisateur.setEmail(email);
    } catch (final Exception exc) {
      AddUserForm.LOGGER.log(Level.INFO, EXCEPTION, exc);
      this.setErreur(UtilisateurForm.CHAMP_EMAIL, exc.getMessage());
    }
  }


  /**
   * Validation login.
   *
   * @param login le login
   * @throws FormValidationException de type form validation exception
   */
  private void validationLogin(String login) throws FormValidationException {
    if (login == null || login.length() < 5) {
      throw new FormValidationException(
          "Le nom d'utilisateur doit contenir au moins 5 caractères.");
    } else if (this.utilisateurDao.trouverParLogin(login) != null) {
      throw new FormValidationException(
          "Ce login est déjà utilisée, merci d'en choisir une autre.");
    }
  }

  /**
   * Validation password.
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
   * Accesseur en lecture sur le paramètre utilisateur bdd.
   *
   * @return le paramètre utilisateur bdd
   */
  public UtilisateurDao getUtilisateurBdd() {
    return this.utilisateurDao;
  }

  /**
   * Accesseur en lecture sur le paramètre admin syst bdd.
   *
   * @return le paramètre admin syst bdd
   */
  public AdminSystDao getAdminSystBdd() {
    return this.adminSystDao;
  }

  /**
   * Accesseur en lecture sur le paramètre directeur etudes bdd.
   *
   * @return le paramètre directeur etudes bdd
   */
  public DirecteurEtudesDao getDirecteurEtudesBdd() {
    return this.directeurEtudesDao;
  }

  /**
   * Accesseur en lecture sur le paramètre directeur programmes bdd.
   *
   * @return le paramètre directeur programmes bdd
   */
  public DirecteurProgrammesDao getDirecteurProgrammesBdd() {
    return this.directeurProgrammesDao;
  }

  /**
   * Accesseur en lecture sur le paramètre enseignant bdd.
   *
   * @return le paramètre enseignant bdd
   */
  public EnseignantDao getEnseignantBdd() {
    return this.enseignantDao;
  }

  /**
   * Accesseur en lecture sur le paramètre eleve bdd.
   *
   * @return le paramètre eleve bdd
   */
  public EleveDao getEleveBdd() {
    return this.eleveDao;
  }

  /**
   * Accesseur en écriture sur le paramètre resultat.
   *
   * @param resultat le nouveau paramètre resultat
   */
  public void setResultat(String resultat) {
    this.resultat = resultat;
  }
}

