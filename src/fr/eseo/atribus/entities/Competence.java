package fr.eseo.atribus.entities;

import java.io.Serializable;

/**
 * The Class Competence.
 */
public class Competence implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = 8319832015092822547L;

  /** La variable id. */
  private Integer id;

  /** La variable nom. */
  private String nom;

  /** La variable profondeur. */
  private Integer profondeur;

  /** La variable id parent. */
  private Integer idParent;

  /** La variable niveau. */
  private Integer niveau;

  /** La variable ue. */
  private UniteEnseignement ue;

  /**
   * Accesseur en lecture sur le paramètre id.
   *
   * @return le paramètre id
   */
  public Integer getId() {
    return this.id;
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
   * Accesseur en lecture sur le paramètre profondeur.
   *
   * @return le paramètre profondeur
   */
  public Integer getProfondeur() {
    return this.profondeur;
  }

  /**
   * Accesseur en lecture sur le paramètre id parent.
   *
   * @return le paramètre id parent
   */
  public Integer getIdParent() {
    return this.idParent;
  }

  /**
   * Accesseur en lecture sur le paramètre niveau.
   *
   * @return le paramètre niveau
   */
  public Integer getNiveau() {
    return this.niveau;
  }

  /**
   * Accesseur en lecture sur le paramètre ue.
   *
   * @return le paramètre ue
   */
  public UniteEnseignement getUe() {
    return this.ue;
  }

  /**
   * Accesseur en écriture sur le paramètre id.
   *
   * @param id le nouveau paramètre id
   */
  public void setId(Integer id) {
    this.id = id;
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
   * Accesseur en écriture sur le paramètre profondeur.
   *
   * @param profondeur le nouveau paramètre profondeur
   */
  public void setProfondeur(Integer profondeur) {
    this.profondeur = profondeur;
  }

  /**
   * Accesseur en écriture sur le paramètre id parent.
   *
   * @param idParent le nouveau paramètre id parent
   */
  public void setIdParent(Integer idParent) {
    this.idParent = idParent;
  }

  /**
   * Accesseur en écriture sur le paramètre niveau.
   *
   * @param niveau le nouveau paramètre niveau
   */
  public void setNiveau(Integer niveau) {
    this.niveau = niveau;
  }

  /**
   * Accesseur en écriture sur le paramètre ue.
   *
   * @param ue le nouveau paramètre ue
   */
  public void setUe(UniteEnseignement ue) {
    this.ue = ue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Competence [id=" + this.id + ", nom=" + this.nom + ", profondeur=" + this.profondeur
        + ", idParent=" + this.idParent + ", niveaux=" + this.niveau + ", ues=" + this.ue
        + ", toString()=" + super.toString() + "]";
  }

}
