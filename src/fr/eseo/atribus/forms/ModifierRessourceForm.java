package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.DaoException;
import fr.eseo.atribus.dao.RessourceDao;
import fr.eseo.atribus.entities.EnseignantRefMatiere;
import fr.eseo.atribus.entities.Ressource;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe ModifierRessourceForm.
 */
public class ModifierRessourceForm {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(ModifierRessourceForm.class.getName());

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La variable resultat. */
  private String resultat;

  /** La variable erreurs. */
  private final Map<String, String> erreurs = new HashMap<>();

  /** La variable ressource dao. */
  private final RessourceDao ressourceDao;

  /**
   * Instancie d'un nouveau modifier ressource form.
   */
  public ModifierRessourceForm() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Ressource */
    this.ressourceDao = (RessourceDao) bf.getFactory().getBean("ressourceDao");
  }

  /**
   * Accesseur en lecture sur le paramètre résultat.
   *
   * @return le paramètre résultat
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
   * Supprimer une ressource.
   *
   * @param erm l'enseignant Ref matière
   * @param choixRessource le choix de ressource
   * @param filePath le file path
   */
  public void supprimerRessource(EnseignantRefMatiere erm, String choixRessource, String filePath) {
    try {
      final Ressource ressource = this.traiterDonnees(erm, choixRessource);
      if (this.erreurs.isEmpty()) {
        this.ressourceDao.supprimer(ressource);
        this.supprimerFichierPhysique(filePath, ressource);
        this.resultat = "Succès de la suppression de la ressource.";
      } else {
        this.resultat = "Échec de la suppression de la ressource.";
      }
    } catch (final DaoException ebdd) {
      this.resultat = "Échec de la suppression de la ressource : une erreur imprévue est survenue,"
          + " merci de réessayer dans quelques instants.";
      ModifierRessourceForm.LOGGER.log(Level.INFO, EXCEPTION, ebdd);
    }
  }

  /**
   * Supprimer fichier physique.
   *
   * @param filePath le file path
   * @param ressource la ressource
   */
  private void supprimerFichierPhysique(String filePath, Ressource ressource) {
    try {
      final File file = new File(filePath + ressource.getPath());
      file.delete();
    } catch (final Exception exc) {
      this.ressourceDao.ajouter(ressource, ressource.getPath());
      ModifierRessourceForm.LOGGER.log(Level.INFO, EXCEPTION, exc);
    }
  }

  /**
   * Modifier ressource.
   *
   * @param erm l'enseignant Ref matière
   * @param nomRessource le de la nom ressource
   * @param choixRessource le choix de la ressource
   */
  public void modifierRessource(EnseignantRefMatiere erm, String nomRessource,
      String choixRessource) {
    try {
      final Ressource ressource = this.traiterDonnees(erm, nomRessource, choixRessource);
      if (this.erreurs.isEmpty()) {
        this.ressourceDao.modifier(ressource);
        this.resultat = "Succès de la mise à jour de la ressource.";
      } else {
        this.resultat = "Échec de la mise à jour de la ressource.";
      }
    } catch (final DaoException ebdd) {
      this.resultat = "Échec de la mise à jour de la ressource : une erreur imprévue est survenue,"
          + " merci de réessayer dans quelques instants.";
      ModifierRessourceForm.LOGGER.log(Level.INFO, EXCEPTION, ebdd);
    }
  }


  /**
   * Traiter données.
   *
   * @param erm l'enseignant Ref matière
   * @param choixRessource le choix de la ressource
   * @return Le paramètre ressource
   */
  private Ressource traiterDonnees(EnseignantRefMatiere erm, String choixRessource) {
    Ressource ressource;
    try {
      this.validationRessource(erm, choixRessource);
    } catch (final FormValidationException fve) {
      ModifierRessourceForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("choixRessource", fve.getMessage());
    }
    ressource = this.ressourceDao.trouverParNomEtType(
        choixRessource.substring(0, choixRessource.indexOf('.')),
        choixRessource.substring(choixRessource.indexOf('.') + 1));
    return ressource;
  }


  /**
   * Traiter données.
   *
   * @param erm l'enseignant Ref matière
   * @param nomRessource le nom de la ressource
   * @param choixRessource le choix de la ressource
   * @return Le paramètre ressource
   */
  private Ressource traiterDonnees(EnseignantRefMatiere erm, String nomRessource,
      String choixRessource) {
    Ressource ressource;
    try {
      this.validationRessource(erm, choixRessource);
    } catch (final FormValidationException fve) {
      ModifierRessourceForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("choixRessource", fve.getMessage());
    }
    ressource = this.ressourceDao.trouverParNomEtType(
        choixRessource.substring(0, choixRessource.indexOf('.')),
        choixRessource.substring(choixRessource.indexOf('.') + 1));
    try {
      this.validationNom(nomRessource);
    } catch (final FormValidationException fve) {
      ModifierRessourceForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("nom", fve.getMessage());
    }
    ressource.setNom(nomRessource);
    return ressource;
  }

  /**
   * Validation du nom.
   *
   * @param nom le nom
   * @throws FormValidationException de type form validation exception
   */
  private void validationNom(String nom) throws FormValidationException {
    if (nom == null || nom.length() < 3) {
      throw new FormValidationException("Le nom de la ressource doit avoir au moins 3 caractères.");
    }
  }

  /**
   * Validation ressource.
   *
   * @param erm l'enseignant Ref matière
   * @param choixRessource le choix de la ressource
   * @throws FormValidationException de type form validation exception
   */
  private void validationRessource(EnseignantRefMatiere erm, String choixRessource)
      throws FormValidationException {
    Ressource ressource;
    try {
      ressource = this.ressourceDao.trouverParNomEtType(
          choixRessource.substring(0, choixRessource.indexOf('.')),
          choixRessource.substring(choixRessource.indexOf('.') + 1));
    } catch (final Exception exc) {
      ModifierRessourceForm.LOGGER.log(Level.INFO, EXCEPTION, exc);
      throw new FormValidationException("Cette ressource n'existe pas");
    }
    if (erm.getMatiere().getId() != ressource.getMatiere().getId()) {
      throw new FormValidationException(
          "Cette ressource ne peut pas être modifiée par cet ERM, elle appartient à la matiere : "
              + ressource.getMatiere().getNom());
    }
  }

  /**
   * Set erreurs.
   *
   * @param champ le champ
   * @param message le message
   */
  /*
   * Ajoute un message correspondant au champ spécifié à la map des erreurs.
   */
  private void setErreur(String champ, String message) {
    this.erreurs.put(champ, message);
  }

}

