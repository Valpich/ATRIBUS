package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Examen;
import fr.eseo.atribus.entities.Matiere;

import java.util.Date;
import java.util.List;

/**
 * L'Interface ExamenDao.
 */
public interface ExamenDao {

  /**
   * Ajouter un examen.
   *
   * @param examen l'examen
   * @param matiere la matiere
   */
  void ajouter(Examen examen, Matiere matiere);

  /**
   * Trouver par nom.
   *
   * @param nom le nom
   * @return Le paramètre examen
   */
  Examen trouverParNom(String nom);

  /**
   * Trouver tous les examens.
   *
   * @return Le paramètre list
   */
  List<Examen> trouverTousLesExamens();

  /**
   * Trouver id par nom.
   *
   * @param nom le nom
   * @return Le paramètre int
   */
  int trouverIdParNom(String nom);

  /**
   * Mettre à jour un examen.
   *
   * @param examen l'examen
   * @param ancienNom le ancien nom
   */
  void update(Examen examen, String ancienNom);

  /**
   * Supprimer un examen.
   *
   * @param examen l'examen
   */
  void supprimer(Examen examen);

  /**
   * Calculer historique.
   *
   * @param examen l'examen
   * @param dateDebut la date debut
   * @param dateFin la date fin
   * @return Le paramètre int
   */
  int calculerHistorique(Examen examen, Date dateDebut, Date dateFin);

}
