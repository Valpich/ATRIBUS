package fr.eseo.atribus.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class Evaluation.
 */
public class Evaluation implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = 2722236148177332532L;

  /** La variable id. */
  private int id;

  /** La variable note. */
  private Float note;

  /** La variable date examen. */
  private Date dateExamen;

  /** La variable eleve. */
  private Eleve eleve;

  /** La variable reponse. */
  private String reponse;

  /** La variable exercice. */
  private Exercice exercice;

  /**
   * Instancie un nouveau evaluation.
   */
  public Evaluation() {
    super();
  }

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
   * Accesseur en lecture sur le paramètre note.
   *
   * @return le paramètre note
   */
  public Float getNote() {
    return this.note;
  }

  /**
   * Accesseur en écriture sur le paramètre note.
   *
   * @param note le nouveau paramètre note
   */
  public void setNote(Float note) {
    this.note = note;
  }

  /**
   * Accesseur en lecture sur le paramètre date examen.
   *
   * @return le paramètre date examen
   */
  public Date getDateExamen() {
    return this.dateExamen;
  }

  /**
   * Accesseur en écriture sur le paramètre date examen.
   *
   * @param dateExamen le nouveau paramètre date examen
   */
  public void setDateExamen(Date dateExamen) {
    this.dateExamen = dateExamen;
  }

  /**
   * Accesseur en lecture sur le paramètre eleve.
   *
   * @return le paramètre eleve
   */
  public Eleve getEleve() {
    return this.eleve;
  }

  /**
   * Accesseur en écriture sur le paramètre eleve.
   *
   * @param eleve le nouveau paramètre eleve
   */
  public void setEleve(Eleve eleve) {
    this.eleve = eleve;
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
   * Accesseur en lecture sur le paramètre exercice.
   *
   * @return le paramètre exercice
   */
  public Exercice getExercice() {
    return this.exercice;
  }

  /**
   * Accesseur en écriture sur le paramètre exercice.
   *
   * @param exercice le nouveau paramètre exercice
   */
  public void setExercice(Exercice exercice) {
    this.exercice = exercice;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Evaluation [id=" + this.id + ", note=" + this.note + ", dateExamen=" + this.dateExamen
        + ", eleve=" + this.eleve + ", reponse=" + this.reponse + ", exercice=" + this.exercice
        + "]";
  }

}
