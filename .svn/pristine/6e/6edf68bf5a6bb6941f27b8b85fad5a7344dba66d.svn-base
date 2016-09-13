package fr.eseo.atribus.entities;

import java.io.Serializable;
import java.util.List;

/**
 * The Class Enseignant.
 */
public class Enseignant extends Utilisateur implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = -6061038723633320784L;

  /** La variable id enseignant. */
  private int idEnseignant;

  /** La variable enseigne matiere. */
  private List<Matiere> enseigneMatiere;

  /**
   * Instancie un nouveau enseignant.
   */
  public Enseignant() {
    super();
  }

  /**
   * Instancie un nouveau enseignant.
   *
   * @param utilisateur le utilisateur
   */
  public Enseignant(Utilisateur utilisateur) {
    super(utilisateur);
  }

  /**
   * Accesseur en lecture sur le paramètre id enseignant.
   *
   * @return le paramètre id enseignant
   */
  public int getIdEnseignant() {
    return this.idEnseignant;
  }

  /**
   * Accesseur en écriture sur le paramètre id enseignant.
   *
   * @param idEnseignant le nouveau paramètre id enseignant
   */
  public void setIdEnseignant(int idEnseignant) {
    this.idEnseignant = idEnseignant;
  }

  /**
   * Accesseur en lecture sur le paramètre enseigne matiere.
   *
   * @return le paramètre enseigne matiere
   */
  public List<Matiere> getEnseigneMatiere() {
    return this.enseigneMatiere;
  }

  /**
   * Accesseur en écriture sur le paramètre enseigne matiere.
   *
   * @param enseigneMatiere le nouveau paramètre enseigne matiere
   */
  public void setEnseigneMatiere(List<Matiere> enseigneMatiere) {
    this.enseigneMatiere = enseigneMatiere;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.entities.Utilisateur#toString()
   */
  @Override
  public String toString() {
    return "Enseignant [idEnseignant=" + this.idEnseignant + ", enseigneMatiere="
        + this.enseigneMatiere + "]";
  }
}
