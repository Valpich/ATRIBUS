package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.DaoException;
import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Examen;
import fr.eseo.atribus.entities.Exercice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * La classe RepondreAutoEvaluationForm.
 */
public class RepondreAutoEvaluationForm extends ExamenForm {

  /**
   * Instancie un nouveau RepondreAutoEvaluationForm.
   */
  public RepondreAutoEvaluationForm() {
    super();
  }

  /**
   * Repondre.
   *
   * @param eleve l'élève
   * @param reponses les réponses
   * @param examenString le nom de l'examen
   * @param autoEvaluation l'auto évaluation
   * @return Le paramètre int
   */
  public int repondre(Eleve eleve, List<String> reponses, String examenString,
      Boolean autoEvaluation) {
    final Examen examen = this.examenDao.trouverParNom(examenString);
    try {
      final Map<Exercice, List<String>> reponsesParser = new HashMap<>();
      if (examen == null || !examen.getAutoEvaluation()) {
        this.erreurs.put("mauvaisAutoEval", "Auto evaluation invalide");
      } else {
        for (final Exercice exercice : examen.getExercices()) {
          reponsesParser.put(exercice, new ArrayList<>());
        }
        this.traiterDonnees(eleve, reponses, examen, reponsesParser);
      }
      if (this.erreurs.isEmpty()) {
        this.evaluationDao.repondre(eleve, reponsesParser, examen, autoEvaluation);
        this.resultat = "Succès de l'ajout des réponses l'auto evaluation.";
        return 1;
      } else {
        this.resultat = "Échec de l'ajout des réponses l'auto evaluation.";
      }
    } catch (final DaoException ebdd) {
      this.resultat =
          "Échec de l'ajout des réponses de l'auto evaluation : une erreur imprévue est survenue,"
              + " merci de réessayer dans quelques instants.";
      ExamenForm.LOGGER.log(Level.INFO, EXCEPTION, ebdd);
    }
    return -1;
  }

  /**
   * Traiter donnees.
   *
   * @param eleve l'élève
   * @param reponses les réponses
   * @param examen l'examen
   * @param reponsesParser les réponses parser
   */
  private void traiterDonnees(Eleve eleve, List<String> reponses, Examen examen,
      Map<Exercice, List<String>> reponsesParser) {
    if (reponses != null) {
      this.traiterReponses(reponses, examen, reponsesParser);
    }
    try {
      this.validationEleve(eleve);
    } catch (final FormValidationException fve) {
      ExamenForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("eleve", fve.getMessage());
    }
  }

  /**
   * Traiter reponses.
   *
   * @param reponses les réponses
   * @param examen l'examen
   * @param reponsesParser les réponses parser
   */
  private void traiterReponses(List<String> reponses, Examen examen,
      Map<Exercice, List<String>> reponsesParser) {
    for (final String string : reponses) {
      final List<String> parsing =
          new ArrayList<>(Arrays.asList(string.split(Pattern.quote("/////"))));
      for (final Exercice exercice : examen.getExercices()) {
        if (exercice.getQuestion().equals(parsing.get(0))) {
          reponsesParser.get(exercice).add(parsing.get(1));
        }
      }
    }
  }

}

