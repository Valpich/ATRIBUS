package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.dao.EvaluationDao;
import fr.eseo.atribus.dao.ExamenDao;
import fr.eseo.atribus.entities.Eleve;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * La classe ExamenForm.
 */
public class ExamenForm {

  /** La constante LOGGER. */
  protected static final Logger LOGGER = Logger.getLogger(ExamenForm.class.getName());

  /** La constante EXCEPTION. */
  protected static final String EXCEPTION = "Exception";

  /** La variable resultat. */
  protected String resultat;

  /** La variable erreurs. */
  protected final Map<String, String> erreurs = new HashMap<>();

  /** La variable examen dao. */
  protected final ExamenDao examenDao;

  /** La variable eleve dao. */
  protected final EleveDao eleveDao;

  /** La variable evaluation dao. */
  protected final EvaluationDao evaluationDao;

  /**
   * Instancie un nouveau examen form.
   */
  public ExamenForm() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Examen */
    this.examenDao = (ExamenDao) bf.getFactory().getBean("examenDao");
    /* Récupération d'une instance de notre DAO Eleve */
    this.eleveDao = (EleveDao) bf.getFactory().getBean("eleveDao");
    /* Récupération d'une instance de notre DAO Evaluation */
    this.evaluationDao = (EvaluationDao) bf.getFactory().getBean("evaluationDao");
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
   * Validation eleve.
   *
   * @param eleve l'eleve
   * @throws FormValidationException de type form validation exception
   */
  protected void validationEleve(Eleve eleve) throws FormValidationException {
    if (this.eleveDao.trouverParLoginHash(eleve.getLogin(), eleve.getPassword()) == null) {
      throw new FormValidationException("L'eleve n'existe pas.");
    }
  }

  /**
   * Ajoute une erreur.
   *
   * @param champ le champ
   * @param message le message
   */
  /*
   * Ajoute un message correspondant au champ spécifié à la map des erreurs.
   */
  protected void setErreur(String champ, String message) {
    this.erreurs.put(champ, message);
  }

}
