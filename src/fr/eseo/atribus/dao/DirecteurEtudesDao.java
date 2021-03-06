package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.DirecteurEtudes;

import java.util.List;

/**
 * L'Interface DirecteurEtudesDao.
 */
public interface DirecteurEtudesDao {

  /**
   * Trouver par login hash.
   *
   * @param login le login
   * @param hash le hash
   * @return Le paramètre directeur etudes
   */
  DirecteurEtudes trouverParLoginHash(String login, String hash);

  /**
   * Ajouter.
   *
   * @param directeurEtudes le directeur etudes
   * @return Le paramètre directeur etudes
   */
  DirecteurEtudes ajouter(DirecteurEtudes directeurEtudes);

  /**
   * Recuperer liste.
   *
   * @return Le paramètre list
   */
  List<DirecteurEtudes> recupererListe();

  /**
   * Supprimer.
   *
   * @param directeurEtudes le directeur etudes
   * @return Le paramètre int
   */
  int supprimer(DirecteurEtudes directeurEtudes);
}
