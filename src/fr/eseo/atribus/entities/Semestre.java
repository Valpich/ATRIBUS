package fr.eseo.atribus.entities;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

/**
 * The Class Semestre.
 */
public class Semestre implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = 8244904742770101314L;

  /** La variable id. */
  private int id;

  /** La variable promotion. */
  private Promotion promotion;

  /** La variable unite enseignement. */
  private List<UniteEnseignement> uniteEnseignement;

  /** La variable numero semestre. */
  private int numeroSemestre;

  /** La variable date debut. */
  private DateTime dateDebut;

  /** La variable date fin. */
  private DateTime dateFin;

  /**
   * Accesseur en lecture sur le paramètre id.
   *
   * @return le paramètre id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Accesseur en écriture sur le paramètre id.
   *
   * @param id le nouveau paramètre id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Accesseur en lecture sur le paramètre promotion.
   *
   * @return le paramètre promotion
   */
  public Promotion getPromotion() {
    return this.promotion;
  }

  /**
   * Accesseur en lecture sur le paramètre unite enseignement.
   *
   * @return le paramètre unite enseignement
   */
  public List<UniteEnseignement> getUniteEnseignement() {
    return this.uniteEnseignement;
  }

  /**
   * Accesseur en lecture sur le paramètre numero semestre.
   *
   * @return le paramètre numero semestre
   */
  public int getNumeroSemestre() {
    return this.numeroSemestre;
  }

  /**
   * Accesseur en lecture sur le paramètre date debut.
   *
   * @return le paramètre date debut
   */
  public DateTime getDateDebut() {
    return this.dateDebut;
  }

  /**
   * Accesseur en lecture sur le paramètre date fin.
   *
   * @return le paramètre date fin
   */
  public DateTime getDateFin() {
    return this.dateFin;
  }

  /**
   * Accesseur en écriture sur le paramètre promotion.
   *
   * @param promotion le nouveau paramètre promotion
   */
  public void setPromotion(Promotion promotion) {
    this.promotion = promotion;
  }

  /**
   * Accesseur en écriture sur le paramètre unite enseignement.
   *
   * @param uniteEnseignement le nouveau paramètre unite enseignement
   */
  public void setUniteEnseignement(List<UniteEnseignement> uniteEnseignement) {
    this.uniteEnseignement = uniteEnseignement;
  }

  /**
   * Accesseur en écriture sur le paramètre numero semestre.
   *
   * @param numeroSemestre le nouveau paramètre numero semestre
   */
  public void setNumeroSemestre(int numeroSemestre) {
    this.numeroSemestre = numeroSemestre;
  }

  /**
   * Accesseur en écriture sur le paramètre date debut.
   *
   * @param dateDebut le nouveau paramètre date debut
   */
  public void setDateDebut(DateTime dateDebut) {
    this.dateDebut = dateDebut;
  }

  /**
   * Accesseur en écriture sur le paramètre date fin.
   *
   * @param dateFin le nouveau paramètre date fin
   */
  public void setDateFin(DateTime dateFin) {
    this.dateFin = dateFin;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Semestre [promotion=" + this.promotion + ", uniteEnseignement=" + this.uniteEnseignement
        + ", numeroSemestre=" + this.numeroSemestre + ", dateDebut=" + this.dateDebut + ", dateFin="
        + this.dateFin;
  }

}
