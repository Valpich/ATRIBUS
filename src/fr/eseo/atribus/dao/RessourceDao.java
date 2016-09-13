package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Ressource;
import fr.eseo.atribus.entities.Utilisateur;

import java.util.List;
import java.util.Map;

/**
 * The Interface RessourceDao.
 */
public interface RessourceDao {

  /**
   * Ajouter.
   *
   * @param ressource le ressource
   * @param path le path
   */
  void ajouter(Ressource ressource, String path);

  /**
   * Modifier.
   *
   * @param ressource le ressource
   */
  void modifier(Ressource ressource);

  /**
   * Consulter.
   *
   * @param ressource le ressource
   * @param utilisateur le utilisateur
   */
  void consulter(Ressource ressource, Utilisateur utilisateur);

  /**
   * Supprimer.
   *
   * @param ressource le ressource
   */
  void supprimer(Ressource ressource);

  /**
   * Trouver par nom et type.
   *
   * @param nom le nom
   * @param type le type
   * @return Le paramètre ressource
   */
  Ressource trouverParNomEtType(String nom, String type);

  /**
   * Trouver toutes les ressources.
   *
   * @return Le paramètre list
   */
  List<Ressource> trouverToutesLesRessources();

  /**
   * Trouver path.
   *
   * @param ressource le ressource
   * @return Le paramètre string
   */
  String trouverPath(Ressource ressource);

  /**
   * Trouver par nom et matiere.
   *
   * @param nom le nom
   * @param matiere le matiere
   * @return Le paramètre ressource
   */
  Ressource trouverParNomEtMatiere(String nom, String matiere);

  /**
   * Trouver toutes les ressources competence.
   *
   * @param competence le competence
   * @return Le paramètre list
   */
  List<Ressource> trouverToutesLesRessourcesCompetence(String competence);

  /**
   * Lister consultations.
   *
   * @return Le paramètre map
   */
  Map<Ressource, Integer> listerConsultations();

}
