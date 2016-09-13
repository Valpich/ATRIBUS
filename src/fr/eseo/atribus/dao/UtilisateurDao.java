package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Utilisateur;

import java.util.List;

/**
 * The Interface UtilisateurDao.
 */
public interface UtilisateurDao {

  /**
   * Trouver id par login.
   *
   * @param login le login
   * @return Le paramètre int
   */
  int trouverIdParLogin(String login);

  /**
   * Ajouter.
   *
   * @param utilisateur le utilisateur
   * @return Le paramètre utilisateur
   */
  Utilisateur ajouter(Utilisateur utilisateur);

  /**
   * Modifier.
   *
   * @param loginPrecedent le login precedent
   * @param utilisateur le utilisateur
   * @return Le paramètre utilisateur
   */
  Utilisateur modifier(String loginPrecedent, Utilisateur utilisateur);

  /**
   * Trouver par login.
   *
   * @param login le login
   * @return Le paramètre utilisateur
   */
  Utilisateur trouverParLogin(String login);

  /**
   * Trouver par login password.
   *
   * @param login le login
   * @param password le password
   * @return Le paramètre utilisateur
   */
  Utilisateur trouverParLoginPassword(String login, String password);

  /**
   * Trouver par nom prenom.
   *
   * @param nom le nom
   * @param prenom le prenom
   * @return Le paramètre utilisateur
   */
  Utilisateur trouverParNomPrenom(String nom, String prenom);

  /**
   * Supprimer par login.
   *
   * @param email le email
   * @return Le paramètre int
   */
  int supprimerParLogin(String email);

  /**
   * Recuperer liste.
   *
   * @return Le paramètre list
   */
  List<Utilisateur> recupererListe();

  /**
   * Trouver par id.
   *
   * @param id le id
   * @return Le paramètre utilisateur
   */
  Utilisateur trouverParId(int id);

  /**
   * Update.
   *
   * @param utilisateur le utilisateur
   */
  void update(Utilisateur utilisateur);
}
