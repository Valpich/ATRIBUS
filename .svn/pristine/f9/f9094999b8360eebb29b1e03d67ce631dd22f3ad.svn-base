package fr.eseo.atribus.entities;

import java.io.Serializable;
import java.util.List;

/**
 * The Class Eleve.
 */
public class Eleve extends Utilisateur implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = 6254840008027131964L;

  /** La variable id eleve. */
  private int idEleve;

  /** La variable promotion. */
  private Promotion promotion;

  /** La variable competence eleve. */
  private List<CompetenceEleve> competenceEleve;

  /**
   * Instancie un nouveau eleve.
   */
  public Eleve() {
    super();
  }

  /**
   * Instancie un nouveau eleve.
   *
   * @param utilisateur le utilisateur
   */
  public Eleve(Utilisateur utilisateur) {
    super(utilisateur);
  }

  /**
   * Accesseur en lecture sur le paramètre promotion.
   *
   * @return le paramètre promotion
   */
  public Promotion getPromotion() {
    return this.promotion;
  }

  /**
   * Accesseur en écriture sur le paramètre promotion.
   *
   * @param promotion le nouveau paramètre promotion
   */
  public void setPromotion(Promotion promotion) {
    this.promotion = promotion;
  }

  /**
   * Accesseur en lecture sur le paramètre id eleve.
   *
   * @return le paramètre id eleve
   */
  public int getIdEleve() {
    return this.idEleve;
  }

  /**
   * Accesseur en écriture sur le paramètre id eleve.
   *
   * @param idEleve le nouveau paramètre id eleve
   */
  public void setIdEleve(int idEleve) {
    this.idEleve = idEleve;
  }

  /**
   * Accesseur en lecture sur le paramètre competence eleve.
   *
   * @return le paramètre competence eleve
   */
  public List<CompetenceEleve> getCompetenceEleve() {
    return this.competenceEleve;
  }

  /**
   * Accesseur en écriture sur le paramètre competence eleve.
   *
   * @param competenceEleve le nouveau paramètre competence eleve
   */
  public void setCompetenceEleve(List<CompetenceEleve> competenceEleve) {
    this.competenceEleve = competenceEleve;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.entities.Utilisateur#toString()
   */
  @Override
  public String toString() {
    return "Eleve [idEleve=" + this.idEleve + ", promotion=" + this.promotion + ", competenceEleve="
        + this.competenceEleve;
  }

}
