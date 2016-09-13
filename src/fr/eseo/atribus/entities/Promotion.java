package fr.eseo.atribus.entities;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * The Class Promotion.
 */
public class Promotion implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = -6984430899839938911L;

  /** La variable id. */
  private int id;

  /** La variable nom. */
  private String nom;

  /** La variable annee diplome. */
  private DateTime anneeDiplome;

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
   * Accesseur en lecture sur le paramètre nom.
   *
   * @return le paramètre nom
   */
  public String getNom() {
    return this.nom;
  }

  /**
   * Accesseur en lecture sur le paramètre annee diplome.
   *
   * @return le paramètre annee diplome
   */
  public DateTime getAnneeDiplome() {
    return this.anneeDiplome;
  }

  /**
   * Accesseur en écriture sur le paramètre nom.
   *
   * @param nom le nouveau paramètre nom
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Accesseur en écriture sur le paramètre annee diplome.
   *
   * @param anneeDiplome le nouveau paramètre annee diplome
   */
  public void setAnneeDiplome(DateTime anneeDiplome) {
    this.anneeDiplome = anneeDiplome;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Promotion [semestre=" + ", nom=" + this.nom + ", anneeDiplome=" + this.anneeDiplome
        + "]";
  }
}
