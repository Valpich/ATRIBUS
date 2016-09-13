package fr.eseo.atribus.entities;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

/**
 * The Class Ressource.
 */
public class Ressource implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = 7507707833658831460L;

  /** La variable id. */
  private int id;

  /** La variable matiere. */
  private Matiere matiere;

  /** La variable nom. */
  private String nom;

  /** La variable type. */
  private String type;

  /** La variable path. */
  private String path;

  /** La variable date publication. */
  private DateTime datePublication;

  /** La variable competences. */
  private List<Competence> competences;

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
   * Accesseur en lecture sur le paramètre type.
   *
   * @return le paramètre type
   */
  public String getType() {
    return this.type;
  }

  /**
   * Accesseur en lecture sur le paramètre path.
   *
   * @return le paramètre path
   */
  public String getPath() {
    return this.path;
  }

  /**
   * Accesseur en écriture sur le paramètre path.
   *
   * @param path le nouveau paramètre path
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * Accesseur en lecture sur le paramètre date publication.
   *
   * @return le paramètre date publication
   */
  public DateTime getDatePublication() {
    return this.datePublication;
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
   * Accesseur en écriture sur le paramètre type.
   *
   * @param type le nouveau paramètre type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Accesseur en écriture sur le paramètre date publication.
   *
   * @param datePublication le nouveau paramètre date publication
   */
  public void setDatePublication(DateTime datePublication) {
    this.datePublication = datePublication;
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
   * Accesseur en lecture sur le paramètre competences.
   *
   * @return le paramètre competences
   */
  public List<Competence> getCompetences() {
    return this.competences;
  }

  /**
   * Accesseur en écriture sur le paramètre competences.
   *
   * @param competences le nouveau paramètre competences
   */
  public void setCompetences(List<Competence> competences) {
    this.competences = competences;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Ressource [id=" + this.id + ", matiere=" + this.matiere + ", nom=" + this.nom
        + ", type=" + this.type + ", path=" + this.path + ", datePublication="
        + this.datePublication + ", competences=" + this.competences + "]";
  }

}
