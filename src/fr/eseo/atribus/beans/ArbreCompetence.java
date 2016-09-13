package fr.eseo.atribus.beans;

import java.util.List;

/**
 * La classe ArbreCompetence.
 */
public class ArbreCompetence {

  /** La variable id. */
  private Integer id;

  /** La variable nom competence. */
  private String nomCompetence;

  /** La variable numerotation. */
  private String numerotation;

  /** La variable profondeur. */
  private Integer profondeur;

  /** La variable id parent. */
  private Integer idParent;

  /** La variable children. */
  private List<ArbreCompetence> children;



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

  /**
   * Accesseur en lecture sur le paramètre nom competence.
   *
   * @return le paramètre nom competence
   */
  public String getNomCompetence() {
    return this.nomCompetence;
  }

  /**
   * Accesseur en écriture sur le paramètre nom competence.
   *
   * @param nomCompetence le nouveau paramètre nom competence
   */
  public void setNomCompetence(String nomCompetence) {
    this.nomCompetence = nomCompetence;
  }

  /**
   * Accesseur en lecture sur le paramètre children.
   *
   * @return le paramètre children
   */
  public List<ArbreCompetence> getChildren() {
    return this.children;
  }

  /**
   * Accesseur en écriture sur le paramètre children.
   *
   * @param children le nouveau paramètre children
   */
  public void setChildren(List<ArbreCompetence> children) {
    this.children = children;
  }

  /**
   * Accesseur en lecture sur le paramètre numerotation.
   *
   * @return le paramètre numerotation
   */
  public String getNumerotation() {
    return this.numerotation;
  }

  /**
   * Accesseur en écriture sur le paramètre numerotation.
   *
   * @param numerotation le nouveau paramètre numerotation
   */
  public void setNumerotation(String numerotation) {
    this.numerotation = numerotation;
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
   * Accesseur en écriture sur le paramètre profondeur.
   *
   * @param profondeur le nouveau paramètre profondeur
   */
  public void setProfondeur(Integer profondeur) {
    this.profondeur = profondeur;
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
   * Accesseur en écriture sur le paramètre id parent.
   *
   * @param idParent le nouveau paramètre id parent
   */
  public void setIdParent(Integer idParent) {
    this.idParent = idParent;
  }

}
