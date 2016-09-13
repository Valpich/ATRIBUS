package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Enseignant;
import fr.eseo.atribus.entities.Matiere;

import java.util.List;

/**
 * L'Interface EnseignantDao.
 */
public interface EnseignantDao {

  /**
   * Trouver par login hash.
   *
   * @param login le login
   * @param hash le hash
   * @return Le paramètre enseignant
   */
  Enseignant trouverParLoginHash(String login, String hash);

  /**
   * Ajouter.
   *
   * @param enseignant l'enseignant à ajouter
   * @return Le paramètre enseignant
   */
  Enseignant ajouter(Enseignant enseignant);

  /**
   * Trouver par nom.
   *
   * @param nom le nom de l'enseigant recherché
   * @return Le paramètre enseignant
   */
  Enseignant trouverParNom(String nom);

  /**
   * Trouver id par login.
   *
   * @param login le login
   * @return Le paramètre int
   */
  int trouverIdParLogin(String login);

  /**
   * Recuperer liste.
   *
   * @return Le paramètre list
   */
  List<Enseignant> recupererListe();

  /**
   * Trouver matieres.
   *
   * @param enseignant l'enseignant
   * @return Le paramètre list
   */
  List<Matiere> trouverMatieres(Enseignant enseignant);

  /**
   * Trouver tous les enseignants.
   *
   * @return Le paramètre list
   */
  List<Enseignant> trouverTousLesEnseignants();

  /**
   * Supprimer.
   *
   * @param enseignant l'enseignant à supprimer
   * @return Le paramètre int
   */
  int supprimer(Enseignant enseignant);

  /**
   * Trouver par id utilisateur.
   *
   * @param idUtilisateur l'id utilisateur
   * @return Le paramètre enseignant
   */
  Enseignant trouverParIdUtilisateur(int idUtilisateur);

  /**
   * Trouver id utilisateur par id enseignant.
   *
   * @param idEnseignant l'id enseignant
   * @return Le paramètre int
   */
  int trouverIdUtilisateurParIdEnseignant(int idEnseignant);

}
