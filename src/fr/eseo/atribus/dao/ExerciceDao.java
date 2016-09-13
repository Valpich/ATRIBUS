package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Competence;
import fr.eseo.atribus.entities.Examen;
import fr.eseo.atribus.entities.Exercice;

import java.util.List;

/**
 * The Interface ExerciceDao.
 */
public interface ExerciceDao {

  /**
   * Ajouter.
   *
   * @param exercice le exercice
   * @param competence le competence
   * @param examen le examen
   */
  void ajouter(Exercice exercice, Competence competence, Examen examen);

  /**
   * Ajouter.
   *
   * @param exercice le exercice
   * @param examen le examen
   */
  void ajouter(Exercice exercice, Examen examen);

  /**
   * Modifier.
   *
   * @param exercice le exercice
   * @param competence le competence
   * @param examen le examen
   */
  void modifier(Exercice exercice, Competence competence, Examen examen);

  /**
   * Trouver par examen.
   *
   * @param examen le examen
   * @return Le paramètre list
   */
  List<Exercice> trouverParExamen(Examen examen);

  /**
   * Trouver id par question reponse.
   *
   * @param question le question
   * @param reponse le reponse
   * @return Le paramètre int
   */
  int trouverIdParQuestionReponse(String question, String reponse);

  /**
   * Trouver par question reponse.
   *
   * @param question le question
   * @param reponse le reponse
   * @return Le paramètre exercice
   */
  Exercice trouverParQuestionReponse(String question, String reponse);

  /**
   * Supprimer.
   *
   * @param exercice le exercice
   */
  void supprimer(Exercice exercice);

  /**
   * Trouver par id.
   *
   * @param id le id
   * @return Le paramètre exercice
   */
  Exercice trouverParId(int id);

  /**
   * Trouver par id ancien.
   *
   * @param int1 le int1
   * @return Le paramètre exercice
   */
  Exercice trouverParIdAncien(int int1);

}
