package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.CompetenceDao;
import fr.eseo.atribus.dao.ExamenDao;
import fr.eseo.atribus.dao.ExerciceDao;

import java.util.Map;

/**
 * La classe ExerciceFormData.
 */
public class ExerciceFormData {

  /** La variable resultat. */
  private String resultat;

  /** La variable erreurs. */
  private Map<String, String> erreurs;

  /** La variable exercice bdd. */
  private ExerciceDao exerciceBdd;

  /** La variable examen bdd. */
  private ExamenDao examenBdd;

  /** La variable competence bdd. */
  private CompetenceDao competenceBdd;

  /** La constante CHAMP_QUESTION. */
  public static final String CHAMP_QUESTION = "question";

  /** La constante CHAMP_REPONSE. */
  public static final String CHAMP_REPONSE = "reponse";

  /** La constante CHAMP_POURCENTAGE. */
  public static final String CHAMP_POURCENTAGE = "pourcentage";

  /** La constante CHAMP_POINTS. */
  public static final String CHAMP_POINTS = "points";

  /** La constante EXCEPTION. */
  public static final String EXCEPTION = "Exception";

  /** La constante CHAMP_COMPETENCE. */
  public static final String CHAMP_COMPETENCE = "choixCompetence";

  /** La constante CHAMP_EXAMEN. */
  public static final String CHAMP_EXAMEN = "choixExamen";

  /**
   * Instancie un nouveau exercice form data.
   *
   * @param erreurs les erreurs
   */
  public ExerciceFormData(Map<String, String> erreurs) {
    this.erreurs = erreurs;
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
   * Accesseur en écriture sur le paramètre resultat.
   *
   * @param resultat le nouveau paramètre resultat
   */
  public void setResultat(String resultat) {
    this.resultat = resultat;
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
   * Ajoute une map d'erreurs.
   *
   * @param erreurs le erreurs
   */
  public void setErreurs(Map<String, String> erreurs) {
    this.erreurs = erreurs;
  }

  /**
   * Accesseur en lecture sur le paramètre exercice bdd.
   *
   * @return le paramètre exercice bdd
   */
  public ExerciceDao getExerciceBdd() {
    return this.exerciceBdd;
  }

  /**
   * Accesseur en écriture sur le paramètre exercice bdd.
   *
   * @param exerciceBdd le nouveau paramètre exercice bdd
   */
  public void setExerciceBdd(ExerciceDao exerciceBdd) {
    this.exerciceBdd = exerciceBdd;
  }

  /**
   * Accesseur en lecture sur le paramètre examen bdd.
   *
   * @return le paramètre examen bdd
   */
  public ExamenDao getExamenBdd() {
    return this.examenBdd;
  }

  /**
   * Accesseur en écriture sur le paramètre examen bdd.
   *
   * @param examenBdd le nouveau paramètre examen bdd
   */
  public void setExamenBdd(ExamenDao examenBdd) {
    this.examenBdd = examenBdd;
  }

  /**
   * Accesseur en lecture sur le paramètre competence bdd.
   *
   * @return le paramètre competence bdd
   */
  public CompetenceDao getCompetenceBdd() {
    return this.competenceBdd;
  }

  /**
   * Accesseur en écriture sur le paramètre competence bdd.
   *
   * @param competenceBdd le nouveau paramètre competence bdd
   */
  public void setCompetenceBdd(CompetenceDao competenceBdd) {
    this.competenceBdd = competenceBdd;
  }

}
