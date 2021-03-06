package fr.eseo.atribus.entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * The Class CompetenceEleve.
 */
public class CompetenceEleve extends Competence implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = 7370585375285499336L;

  /** La variable niveau competence eleve. */
  int niveauCompetenceEleve;

  /** La variable unique id. */
  private UUID uniqueId;

  /**
   * Constructeur.
   * 
   * @param competence competence
   */
  public CompetenceEleve(Competence competence) {
    this.setId(competence.getId());
    this.setIdParent(competence.getIdParent());
    this.setNiveau(competence.getNiveau());
    this.setNom(competence.getNom());
    this.setProfondeur(competence.getProfondeur());
    this.setUe(competence.getUe());
    this.setUniqueId(UUID.randomUUID());
  }

  /**
   * Accesseur en lecture sur le paramètre niveau competence eleve.
   *
   * @return le paramètre niveau competence eleve
   */
  public Integer getNiveauCompetenceEleve() {
    return this.niveauCompetenceEleve;
  }

  /**
   * Accesseur en lecture sur le paramètre unique id.
   *
   * @return le paramètre unique id
   */
  public UUID getUniqueId() {
    return this.uniqueId;
  }

  /**
   * Accesseur en écriture sur le paramètre niveau competence eleve.
   *
   * @param niveauCompetenceEleve le nouveau paramètre niveau competence eleve
   */
  public void setNiveauCompetenceEleve(int niveauCompetenceEleve) {
    this.niveauCompetenceEleve = niveauCompetenceEleve;
  }

  /**
   * Accesseur en écriture sur le paramètre unique id.
   *
   * @param uniqueId le nouveau paramètre unique id
   */
  public void setUniqueId(UUID uniqueId) {
    this.uniqueId = uniqueId;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.entities.Competence#toString()
   */
  @Override
  public String toString() {
    return "CompetenceEleve [niveau=" + this.niveauCompetenceEleve + ", uniqueId=" + this.uniqueId
        + ", toString()=" + super.toString() + "]";
  }

}
