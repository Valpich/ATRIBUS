package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.EnseignantRefMatiere;

import java.util.List;

/**
 * L'Interface EnseignantRefMatiereDao.
 */
public interface EnseignantRefMatiereDao {

  /**
   * Trouver par login hash.
   *
   * @param login le login
   * @param hash le hash
   * @return Le paramètre enseignant ref matiere
   */
  EnseignantRefMatiere trouverParLoginHash(String login, String hash);

  /**
   * Ajouter.
   *
   * @param enseignant l'enseignant à ajouter
   * @return Le paramètre enseignant ref matiere
   */
  EnseignantRefMatiere ajouter(EnseignantRefMatiere enseignant);

  /**
   * Recuperer liste.
   *
   * @return Le paramètre list
   */
  List<EnseignantRefMatiere> recupererListe();
}
