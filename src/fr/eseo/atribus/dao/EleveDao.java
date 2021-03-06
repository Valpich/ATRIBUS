package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.CompetenceEleve;
import fr.eseo.atribus.entities.Eleve;

import java.util.List;

/**
 * L'Interface EleveDao.
 */
public interface EleveDao {

  /**
   * Trouver par login hash.
   *
   * @param login le login
   * @param hash le hash
   * @return Le paramètre eleve
   */
  Eleve trouverParLoginHash(String login, String hash);

  /**
   * Trouver par nom.
   *
   * @param nom le nom
   * @return Le paramètre eleve
   */
  Eleve trouverParNom(String nom);

  /**
   * Ajouter.
   *
   * @param eleve l'eleve
   * @return Le paramètre eleve
   */
  Eleve ajouter(Eleve eleve);

  /**
   * Recuperer liste.
   *
   * @return Le paramètre list
   */
  List<Eleve> recupererListe();

  /**
   * Trouver par id.
   *
   * @param id l'id
   * @return Le paramètre eleve
   */
  Eleve trouverParId(int id);

  /**
   * Supprimer.
   *
   * @param eleve l'eleve
   * @return Le paramètre int
   */
  int supprimer(Eleve eleve);

  /**
   * Lister competence eleve.
   *
   * @param eleve l'eleve
   * @return Le paramètre list
   */
  List<CompetenceEleve> listerCompetenceEleve(Eleve eleve);

  /**
   * Recuperer id utilisateur.
   *
   * @param eleve l'eleve
   * @return Le paramètre int
   */
  int recupererIdUtilisateur(Eleve eleve);

}
