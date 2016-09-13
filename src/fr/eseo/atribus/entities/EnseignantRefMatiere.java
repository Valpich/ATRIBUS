package fr.eseo.atribus.entities;

import java.io.Serializable;

/**
 * The Class EnseignantRefMatiere.
 */
public class EnseignantRefMatiere extends Enseignant implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = -955316190050101426L;

  /** La variable id enseignant ref matiere. */
  private int idEnseignantRefMatiere;

  /** La variable matiere. */
  private Matiere matiere;

  /**
   * Instancie un nouveau enseignant ref matiere.
   */
  public EnseignantRefMatiere() {
    super();
  }

  /**
   * Instancie un nouveau enseignant ref matiere.
   *
   * @param utilisateur le utilisateur
   */
  public EnseignantRefMatiere(Utilisateur utilisateur) {
    super(utilisateur);
  }

  /**
   * Accesseur en lecture sur le paramètre matiere.
   *
   * @return le paramètre matiere
   */
  public Matiere getMatiere() {
    return this.matiere;
  }

  /**
   * Accesseur en écriture sur le paramètre matiere.
   *
   * @param matiere le nouveau paramètre matiere
   */
  public void setMatiere(Matiere matiere) {
    this.matiere = matiere;
  }

  /**
   * Accesseur en lecture sur le paramètre id enseignant ref matiere.
   *
   * @return le paramètre id enseignant ref matiere
   */
  public int getIdEnseignantRefMatiere() {
    return this.idEnseignantRefMatiere;
  }

  /**
   * Accesseur en écriture sur le paramètre id enseignant ref matiere.
   *
   * @param idEnseignantRefMatiere le nouveau paramètre id enseignant ref matiere
   */
  public void setIdEnseignantRefMatiere(int idEnseignantRefMatiere) {
    this.idEnseignantRefMatiere = idEnseignantRefMatiere;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.entities.Enseignant#toString()
   */
  @Override
  public String toString() {
    return "EnseignantRefMatiere [idEnseignantRefMatiere=" + this.idEnseignantRefMatiere
        + ", matiere=" + this.matiere + "]";
  }
}
