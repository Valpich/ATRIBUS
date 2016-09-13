package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.DaoException;
import fr.eseo.atribus.dao.EnseignantDao;
import fr.eseo.atribus.dao.EnseignantRefUeDao;
import fr.eseo.atribus.dao.SemestreDao;
import fr.eseo.atribus.dao.UeDao;
import fr.eseo.atribus.entities.EnseignantRefUe;
import fr.eseo.atribus.entities.Matiere;
import fr.eseo.atribus.entities.UniteEnseignement;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * La classe ModifierUeForm.
 */
public class ModifierUeForm {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(ModifierUeForm.class.getName());

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La variable ue dao. */
  private final UeDao ueDao;

  /** La variable enseignant dao. */
  private final EnseignantDao enseignantDao;

  /** La variable enseignant ref ue dao. */
  private final EnseignantRefUeDao enseignantRefUeDao;

  /** La variable semestre dao. */
  private final SemestreDao semestreDao;

  /** La variable liste des ues. */
  private List<UniteEnseignement> listeDesUes;

  /** La variable liste des matieres. */
  private List<Matiere> listeDesMatieres;

  /** La variable erreurs. */
  private final Map<String, String> erreurs = new HashMap<>();

  /** La variable resultat. */
  private String resultat;

  /**
   * Constructeur.
   */
  public ModifierUeForm() {

    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.ueDao = (UeDao) bf.getFactory().getBean("ueDao");
    this.enseignantDao = (EnseignantDao) bf.getFactory().getBean("enseignantDao");
    this.enseignantRefUeDao = (EnseignantRefUeDao) bf.getFactory().getBean("enseignantRefUeDao");
    this.semestreDao = (SemestreDao) bf.getFactory().getBean("semestreDao");

  }

  /**
   * Méthode qui permet la modification d'une unité d'enseignement.
   *
   * @param request la request
   * @return Le paramètre unite enseignement
   */
  public UniteEnseignement modifierUe(HttpServletRequest request) {

    final String nomNouvelleUe = ModifierUeForm.extraireValeurChamp(request, "nomNouvelleUe");
    final String ectsNouvelleUe = ModifierUeForm.extraireValeurChamp(request, "ectsNouvelleUe");
    final String nbHeuresNouvelleUe =
        ModifierUeForm.extraireValeurChamp(request, "nbHeuresNouvelleUe");
    final String semestreNouvelleUe =
        ModifierUeForm.extraireValeurChamp(request, "semestreNouvelleUe");
    final String enseignantNouvelleUe =
        ModifierUeForm.extraireValeurChamp(request, "enseignantNouvelleUe");
    final String idUe = ModifierUeForm.extraireValeurChamp(request, "idUe");

    UniteEnseignement nouvelleUe = new UniteEnseignement();;

    try {

      this.traiterDonnees(nomNouvelleUe, ectsNouvelleUe, nbHeuresNouvelleUe, semestreNouvelleUe,
          enseignantNouvelleUe);

      if (this.recupererErreurs().isEmpty()) {

        final UniteEnseignement ancienneUe = this.ueDao.trouverUeParId(Integer.parseInt(idUe));
        final EnseignantRefUe nouveauEnseignantRefUe = new EnseignantRefUe(
            this.enseignantDao.trouverParIdUtilisateur(Integer.parseInt(enseignantNouvelleUe)));

        nouvelleUe.setNom(nomNouvelleUe);
        nouvelleUe.setNbCreditsEcts(Integer.parseInt(ectsNouvelleUe));
        nouvelleUe.setNbHeures(Integer.parseInt(nbHeuresNouvelleUe));
        nouvelleUe
            .setSemestre(this.semestreDao.trouverParNumero(Integer.parseInt(semestreNouvelleUe)));

        this.enseignantRefUeDao.ajouter(nouveauEnseignantRefUe);

        // On supprime l'ancienne Ue
        this.ueDao.supprimerUe(ancienneUe);

        final int idErue = this.enseignantDao
            .trouverParIdUtilisateur(nouveauEnseignantRefUe.getId()).getIdEnseignant();
        nouveauEnseignantRefUe.setIdEnseignantRefUe(idErue);
        nouvelleUe.setEnseignantRefUe(nouveauEnseignantRefUe);

        // On ajoute le nouvelle Ue
        this.ueDao.ajouterUe(nouvelleUe);

        this.modifierResultat("success");

      } else {
        nouvelleUe = null;
        this.modifierResultat("erreur");
      }

    } catch (final DaoException daoException) {
      this.modifierResultat("erreurDao");
      ModifierUeForm.LOGGER.log(Level.INFO, EXCEPTION, daoException);
    }

    return nouvelleUe;

  }

  /**
   * Traiter donnees.
   *
   * @param nom le nom
   * @param ects les ects
   * @param nbHeures le nb d'heures
   * @param semestre le semestre
   * @param enseignant l'enseignant
   */
  private void traiterDonnees(String nom, String ects, String nbHeures, String semestre,
      String enseignant) {

    try {
      this.validationNomUe(nom);
    } catch (final FormValidationException fve) {
      ModifierUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }

    try {
      this.validationEcts(ects);
    } catch (final FormValidationException fve) {
      ModifierUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }

    try {
      this.validationNbHeures(nbHeures);
    } catch (final FormValidationException fve) {
      ModifierUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }

    try {
      this.validationSemestre(semestre);;
    } catch (final FormValidationException fve) {
      ModifierUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }

    try {
      this.validationEnseignant(enseignant);;
    } catch (final FormValidationException fve) {
      ModifierUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }

  }

  /**
   * Validation nom ue.
   *
   * @param nom le nom
   * @throws FormValidationException de type form validation exception
   */
  private void validationNomUe(String nom) throws FormValidationException {
    if (nom == null || nom.length() < 3) {
      this.ajouterErreur("ue", "Veuillez remplir une UE avec plus de 3 caractères");
      throw new FormValidationException("Veuillez remplir une UE avec plus de 3 caractères.");
    }
  }

  /**
   * Validation ects.
   *
   * @param ects les ects
   * @throws FormValidationException de type form validation exception
   */
  private void validationEcts(String ects) throws FormValidationException {
    try {
      if (Integer.parseInt(ects) < 0) {
        this.ajouterErreur("pointEcts", "Merci d'inscrire un nombre de point ECTS supérieur à 0");
        throw new FormValidationException("Merci d'inscrire un nombre de point ECTS supérieur à 0");
      }
    } catch (final NumberFormatException nfe) {
      this.ajouterErreur("pointEcts", "Le nombre d'ECTS inscrit est invalide");
      throw new FormValidationException("Le nombre d'ECTS inscrit est invalide");
    }
  }

  /**
   * Validation nb heures.
   *
   * @param nbHeures le nombre d'heures
   * @throws FormValidationException de type form validation exception
   */
  private void validationNbHeures(String nbHeures) throws FormValidationException {
    try {
      if (Integer.parseInt(nbHeures) < 0) {
        this.ajouterErreur("nbHeures", "Merci d'inscrire un nombre d'heures supérieur à 0");
        throw new FormValidationException("Merci d'inscrire un nombre d'heures supérieur à 0");
      }
    } catch (final NumberFormatException nfe) {
      this.ajouterErreur("nbHeures", "Le nombre d'heure inscrit est invalide.");
      throw new FormValidationException("Le nombre d'heure inscrit est invalide.");
    }
  }

  /**
   * Validation semestre.
   *
   * @param semestre le semestre
   * @throws FormValidationException de type form validation exception
   */
  private void validationSemestre(String semestre) throws FormValidationException {
    try {
      if (Integer.parseInt(semestre) < 0) {
        this.ajouterErreur("semestre", "Merci d'inscrire un semstre supérieur à 0");
        throw new FormValidationException("Merci d'inscrire un semstre supérieur à 0");
      }
    } catch (final NumberFormatException nfe) {
      this.ajouterErreur("semestre", "Le numéro de semestre inscrit est invalide");
      throw new FormValidationException("Le numéro de semestre inscrit est invalide");
    }
  }

  /**
   * Validation enseignant.
   *
   * @param enseignant l'enseignant
   * @throws FormValidationException de type form validation exception
   */
  protected void validationEnseignant(String enseignant) throws FormValidationException {
    if (enseignant == null) {
      this.ajouterErreur("enseignant", "Aucun enseignant n'a été saisi, veuillez réessayer");
      throw new FormValidationException("Aucun enseignant n'a été saisi, veuillez réessayer");
    }
  }

  /**
   * Accesseur en lecture sur le paramètre liste des ues.
   *
   * @return le paramètre liste des ues
   */
  public List<UniteEnseignement> getListeDesUes() {
    return this.listeDesUes;
  }

  /**
   * Accesseur en écriture sur le paramètre liste des ues.
   *
   * @param listeDesUes le nouveau paramètre liste des ues
   */
  public void setListeDesUes(List<UniteEnseignement> listeDesUes) {
    this.listeDesUes = listeDesUes;
  }

  /**
   * Accesseur en lecture sur le paramètre liste des matieres.
   *
   * @return le paramètre liste des matieres
   */
  public List<Matiere> getListeDesMatieres() {
    return this.listeDesMatieres;
  }

  /**
   * Accesseur en écriture sur le paramètre liste des matieres.
   *
   * @param listeDesMatieres le nouveau paramètre liste des matieres
   */
  public void setListeDesMatieres(List<Matiere> listeDesMatieres) {
    this.listeDesMatieres = listeDesMatieres;
  }

  /**
   * Accesseur en lecture sur le paramètre resultat.
   *
   * @return le paramètre resultat
   */
  public String obtenirResultat() {
    return this.resultat;
  }

  /**
   * Accesseur en lecture sur le paramètre erreurs.
   *
   * @return le paramètre erreurs
   */
  public Map<String, String> recupererErreurs() {
    return this.erreurs;
  }

  /**
   * Accesseur en écriture sur le paramètre resultat.
   *
   * @param resultat le nouveau paramètre resultat
   */
  public void modifierResultat(String resultat) {
    this.resultat = resultat;
  }

  /**
   * Ajoute aux erreurs.
   *
   * @param champ le champ
   * @param message le message
   */
  private void ajouterErreur(String champ, String message) {
    this.erreurs.put(champ, message);
  }

  /**
   * Accesseur en lecture sur le paramètre valeur champ.
   *
   * @param request la request
   * @param nomChamp le nom du champ
   * @return le paramètre valeur champ
   */
  protected static String extraireValeurChamp(HttpServletRequest request, String nomChamp) {
    final String valeur = request.getParameter(nomChamp);
    if (valeur == null || valeur.trim().length() == 0) {
      return null;
    } else {
      return valeur.trim();
    }
  }

}
