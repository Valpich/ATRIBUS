package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Matiere;
import fr.eseo.atribus.entities.UniteEnseignement;

import java.util.List;

/**
 * The Interface MatiereDao.
 */
public interface MatiereDao {

  /**
   * Trouver par nom.
   *
   * @param nom le nom
   * @return Le paramètre matiere
   */
  Matiere trouverParNom(String nom);

  /**
   * Trouver toutes les matieres.
   *
   * @return Le paramètre list
   */
  List<Matiere> trouverToutesLesMatieres();

  /**
   * Trouver matiere par ue.
   *
   * @return Le paramètre list
   */
  List<Matiere> trouverMatiereParUe();

  /**
   * Trouver id par nom.
   *
   * @param nom le nom
   * @return Le paramètre int
   */
  public int trouverIdParNom(String nom);

  /**
   * Trouver par id.
   *
   * @param id le id
   * @return Le paramètre matiere
   */
  Matiere trouverParId(int id);

  /**
   * Ajouter.
   *
   * @param matiere le matiere
   * @param utilisateur le utilisateur
   */
  void ajouter(Matiere matiere, int utilisateur);

  /**
   * Trouver une ue dans matiere.
   *
   * @param ue le ue
   * @return true, si réussi
   */
  boolean trouverUneUeDansMatiere(UniteEnseignement ue);

  /**
   * Lister matiere par ue.
   *
   * @param idUe le id ue
   * @return Le paramètre list
   */
  List<Matiere> listerMatiereParUe(String idUe);

  /**
   * Supprimer matiere par id.
   *
   * @param matiere le matiere
   */
  public void supprimerMatiereParId(Matiere matiere);

  /**
   * Modifier matiere par id.
   *
   * @param matiere le matiere
   */
  public void modifierMatiereParId(Matiere matiere);

}
