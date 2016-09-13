package fr.eseo.atribus.entities;

import java.io.Serializable;
import java.util.Map;

/**
 * The Class Exercice.
 */
public class Exercice implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = -227609127293122576L;

  /** La variable id. */
  private Integer id;

  /** La variable competences. */
  private Map<Long, Competence> competences;

  /** La variable pourcentages. */
  private Map<Long, Integer> pourcentages;

  /** La variable question. */
  private String question;

  /** La variable reponse. */
  private String reponse;

  /** La variable nb points. */
  private Float nbPoints;

  /**
   * Accesseur en lecture sur le paramètre competences.
   *
   * @return le paramètre competences
   */
  public Map<Long, Competence> getCompetences() {
    return this.competences;
  }

  /**
   * Sets the competences.
   *
   * @param competences le competences
   */
  public void setCompetences(Map<Long, Competence> competences) {
    this.competences = competences;
  }

  /**
   * Accesseur en lecture sur le paramètre pourcentages.
   *
   * @return le paramètre pourcentages
   */
  public Map<Long, Integer> getPourcentages() {
    return this.pourcentages;
  }

  /**
   * Sets the pourcentages.
   *
   * @param pourcentages le pourcentages
   */
  public void setPourcentages(Map<Long, Integer> pourcentages) {
    this.pourcentages = pourcentages;
  }

  /**
   * Accesseur en lecture sur le paramètre question.
   *
   * @return le paramètre question
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * Accesseur en écriture sur le paramètre question.
   *
   * @param question le nouveau paramètre question
   */
  public void setQuestion(String question) {
    this.question = question;
  }

  /**
   * Accesseur en lecture sur le paramètre reponse.
   *
   * @return le paramètre reponse
   */
  public String getReponse() {
    return this.reponse;
  }

  /**
   * Accesseur en écriture sur le paramètre reponse.
   *
   * @param reponse le nouveau paramètre reponse
   */
  public void setReponse(String reponse) {
    this.reponse = reponse;
  }

  /**
   * Accesseur en lecture sur le paramètre nb points.
   *
   * @return le paramètre nb points
   */
  public Float getNbPoints() {
    return this.nbPoints;
  }

  /**
   * Accesseur en écriture sur le paramètre nb points.
   *
   * @param nbPoints le nouveau paramètre nb points
   */
  public void setNbPoints(Float nbPoints) {
    this.nbPoints = nbPoints;
  }

  /**
   * Accesseur en lecture sur le paramètre id.
   *
   * @return le paramètre id
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * Accesseur en écriture sur le paramètre id.
   *
   * @param id le nouveau paramètre id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Exercice [competence=" + this.competences + ", question=" + this.question + ", reponse="
        + this.reponse + ", nbPoints=" + this.nbPoints + "]";
  }
}
