package fr.eseo.atribus.entities;

import java.io.Serializable;

/**
 * The Class EnseignantRefUe.
 */
public class EnseignantRefUe extends Enseignant implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = -955316190050101426L;

  /** La variable id enseignant ref ue. */
  private int idEnseignantRefUe;

  /**
   * Instancie un nouveau enseignant ref ue.
   */
  public EnseignantRefUe() {
    // Constructeur vide
  }

  /**
   * Instancie un nouveau enseignant ref ue.
   *
   * @param utilisateur le utilisateur
   */
  public EnseignantRefUe(Utilisateur utilisateur) {
    super(utilisateur);
  }

  /**
   * Accesseur en lecture sur le paramètre id enseignant ref ue.
   *
   * @return le paramètre id enseignant ref ue
   */
  public int getIdEnseignantRefUe() {
    return this.idEnseignantRefUe;
  }

  /**
   * Accesseur en écriture sur le paramètre id enseignant ref ue.
   *
   * @param idEnseignantRefUe le nouveau paramètre id enseignant ref ue
   */
  public void setIdEnseignantRefUe(int idEnseignantRefUe) {
    this.idEnseignantRefUe = idEnseignantRefUe;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.entities.Enseignant#toString()
   */
  @Override
  public String toString() {
    return "EnseignantRefUe [idEnseignantRefUe=" + this.idEnseignantRefUe + "]";
  }

}
