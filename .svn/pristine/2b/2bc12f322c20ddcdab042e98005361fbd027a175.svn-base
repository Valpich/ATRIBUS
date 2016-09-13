package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.DaoException;
import fr.eseo.atribus.dao.ExamenDao;
import fr.eseo.atribus.dao.MatiereDao;
import fr.eseo.atribus.entities.Examen;
import fr.eseo.atribus.entities.Matiere;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * La classe AddExamenForm.
 */
public class AddExamenForm {

  /** La constante CHAMP_MATIERE. */
  private static final String CHAMP_MATIERE = "choixMatiere";

  /** La constante CHAMP_NOM. */
  private static final String CHAMP_NOM = "nom";

  /** La constante CHAMP_AUTO. */
  private static final String CHAMP_AUTO = "autoEvaluation";

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(AddExamenForm.class.getName());

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La variable resultat. */
  private String resultat;

  /** La variable erreurs. */
  private final Map<String, String> erreurs = new HashMap<>();

  /** La variable examen dao. */
  private final ExamenDao examenDao;

  /** La variable matiere dao. */
  private final MatiereDao matiereDao;

  /**
   * Méthode d'instantiation.
   */
  public AddExamenForm() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Examen */
    this.examenDao = (ExamenDao) bf.getFactory().getBean("examenDao");
    /* Récupération d'une instance de notre DAO Matiere */
    this.matiereDao = (MatiereDao) bf.getFactory().getBean("matiereDao");
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
   * Ajout d'un examen dans la DAO en fonction du formulaire.
   * 
   * @param request la request
   * @return Examen
   */
  public Examen addExamen(HttpServletRequest request) {
    final String nom = AddExamenForm.getValeurChamp(request, AddExamenForm.CHAMP_NOM);
    final Examen examen = new Examen();
    final String matiere = AddExamenForm.getValeurChamp(request, AddExamenForm.CHAMP_MATIERE);
    final Matiere mat = new Matiere();
    mat.setNom(matiere);
    final String autoEvaluation = AddExamenForm.getValeurChamp(request, AddExamenForm.CHAMP_AUTO);
    if ("on".equals(autoEvaluation)) {
      examen.setAutoEvaluation(true);
    } else {
      examen.setAutoEvaluation(false);
    }
    try {
      this.traiterDonnees(nom, matiere, examen);
      if (this.erreurs.isEmpty()) {
        this.examenDao.ajouter(examen, mat);
        this.resultat = "success";
      } else {
        this.resultat = "error";
      }
    } catch (final DaoException ebdd) {
      this.resultat = "errorDao";
      AddExamenForm.LOGGER.log(Level.INFO, EXCEPTION, ebdd);
    }
    return examen;
  }

  /**
   * Traiter donnees.
   *
   * @param nom le nom
   * @param matiere la matiere
   * @param examen l'examen
   */
  private void traiterDonnees(String nom, String matiere, Examen examen) {
    this.traiterNomMatiere(nom, matiere);
    examen.setMatiere(this.matiereDao.trouverParNom(matiere));
    examen.setNom(nom);
  }

  /**
   * Validation nom.
   *
   * @param nom le nom
   * @throws FormValidationException de type form validation exception
   */
  private void validationNom(String nom) throws FormValidationException {
    if (nom == null || nom.length() < 3) {
      throw new FormValidationException("Le nom de l'examen au moins 3 caractères.");
    }
    if (this.examenDao.trouverParNom(nom) != null) {
      throw new FormValidationException("Cet examen existe déjà, merci d'en créer un autre.");
    }
  }

  /**
   * Validation matiere.
   *
   * @param matiere la matiere
   * @throws FormValidationException de type form validation exception
   */
  private void validationMatiere(String matiere) throws FormValidationException {
    if (this.matiereDao.trouverParNom(matiere) == null) {
      throw new FormValidationException("Cette matière n'existe pas");
    }
  }

  /**
   * Traiter nom matiere.
   *
   * @param nom le nom
   * @param matiere la matiere
   */
  private void traiterNomMatiere(String nom, String matiere) {
    try {
      this.validationMatiere(matiere);
    } catch (final FormValidationException fve) {
      AddExamenForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("choixMatiere", "Choix de matière invalide ou inexistant.");
    }
    try {
      this.validationNom(nom);
    } catch (final FormValidationException fve) {
      AddExamenForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("nom", "Nom invalide ou inexistant.");
    }
  }

  /**
   * Sets the erreur.
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

  /**
   * Accesseur en lecture sur le paramètre valeur champ.
   *
   * @param request la request
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

