package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.DirecteurProgrammes;

import java.util.List;

/**
 * L'Interface DirecteurProgrammesDao.
 */
public interface DirecteurProgrammesDao {

  /**
   * Trouver par login hash.
   *
   * @param login le login
   * @param hash le hash
   * @return Le paramètre directeur programmes
   */
  DirecteurProgrammes trouverParLoginHash(String login, String hash);

  /**
   * Ajouter.
   *
   * @param directeurProgrammes le directeur programmes
   * @return Le paramètre directeur programmes
   */
  DirecteurProgrammes ajouter(DirecteurProgrammes directeurProgrammes);

  /**
   * Recuperer liste.
   *
   * @return Le paramètre list
   */
  List<DirecteurProgrammes> recupererListe();

  /**
   * Supprimer.
   *
   * @param directeurProgrammes le directeur programmes
   * @return Le paramètre int
   */
  int supprimer(DirecteurProgrammes directeurProgrammes);
}
