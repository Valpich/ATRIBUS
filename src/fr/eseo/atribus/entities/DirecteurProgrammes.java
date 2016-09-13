package fr.eseo.atribus.entities;

import java.io.Serializable;

/**
 * The Class DirecteurProgrammes.
 */
public class DirecteurProgrammes extends Utilisateur implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = 490905206891334145L;

  /** La variable id directeur programmes. */
  private int idDirecteurProgrammes;

  /**
   * Instancie un nouveau directeur programmes.
   */
  public DirecteurProgrammes() {
    super();
  }

  /**
   * Instancie un nouveau directeur programmes.
   *
   * @param utilisateur le utilisateur
   */
  public DirecteurProgrammes(Utilisateur utilisateur) {
    super(utilisateur);
  }

  /**
   * Accesseur en lecture sur le paramètre id directeur programmes.
   *
   * @return le paramètre id directeur programmes
   */
  public int getIdDirecteurProgrammes() {
    return this.idDirecteurProgrammes;
  }

  /**
   * Accesseur en écriture sur le paramètre id directeur programmes.
   *
   * @param idDirecteurProgrammes le nouveau paramètre id directeur programmes
   */
  public void setIdDirecteurProgrammes(int idDirecteurProgrammes) {
    this.idDirecteurProgrammes = idDirecteurProgrammes;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.entities.Utilisateur#toString()
   */
  @Override
  public String toString() {
    return "DirecteurProgrammes [idDirecteurProgrammes=" + this.idDirecteurProgrammes + "]";
  }
}
