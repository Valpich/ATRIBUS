package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.CompetenceDao;
import fr.eseo.atribus.dao.DaoException;
import fr.eseo.atribus.dao.ExamenDao;
import fr.eseo.atribus.dao.ExerciceDao;
import fr.eseo.atribus.entities.Examen;
import fr.eseo.atribus.entities.Exercice;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * La classe ModifierExerciceForm.
 */
public class ModifierExerciceForm extends ExerciceFormMethods {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(ModifierExerciceForm.class.getName());

  /**
   * Instancie un nouveau modifier exercice form.
   */
  public ModifierExerciceForm() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.data.setExerciceBdd((ExerciceDao) bf.getFactory().getBean("exerciceDao"));
    this.data.setCompetenceBdd((CompetenceDao) bf.getFactory().getBean("competenceDao"));
    this.data.setExamenBdd((ExamenDao) bf.getFactory().getBean("examenDao"));
  }

  /**
   * Modifier les exercices de l'examen.
   *
   * @param examenAncien l'ancien examen
   * @param request la request
   * @return Le paramètre list
   */
  public List<Exercice> modifierExercices(Examen examenAncien, HttpServletRequest request) {
    final Map<String, String[]> parameters = request.getParameterMap();
    final Map<Long, Map<Long, String>> pourcentages = new HashMap<>();
    final Map<Long, Map<Long, String>> competences = new HashMap<>();
    final Map<Long, String> questions = new HashMap<>();
    final Map<Long, String> reponses = new HashMap<>();
    final Map<Long, String> nbPoints = new HashMap<>();
    final Map<Long, Long> indexMaxCompetences = new HashMap<>();

    Long indexMaxQuestion = 0L;
    for (final String parameter : parameters.keySet()) {
      indexMaxQuestion = this.recupererPourcentages(parameters, pourcentages, indexMaxCompetences,
          indexMaxQuestion, parameter);
      indexMaxQuestion = this.recupererCompetences(parameters, competences, indexMaxCompetences,
          indexMaxQuestion, parameter);
      this.recupererQuestions(request, questions, parameter);
      this.recupererReponses(request, reponses, parameter);
      this.recupererPoints(request, nbPoints, parameter);
    }
    final List<Exercice> exercices = new ArrayList<>();
    for (Long i = new Long(1); i <= competences.size(); i++) {
      exercices.add(this.traiterExercice(examenAncien.getExercices().get(i.intValue() - 1),
          questions.get(i), reponses.get(i), nbPoints.get(i), competences.get(i),
          pourcentages.get(i), examenAncien.getNom(), indexMaxCompetences.get(i)));
    }
    return exercices;
  }

  /**
   * Récupérer les points.
   *
   * @param request la request
   * @param nbPoints le nombre de points
   * @param parameter le parameter recupéré depuis la page web
   */
  private void recupererPoints(HttpServletRequest request, final Map<Long, String> nbPoints,
      String parameter) {
    Long indexQuestion;
    if (parameter.startsWith(ExerciceFormData.CHAMP_POINTS)) {
      indexQuestion = new Long(
          Integer.parseInt(parameter.substring(parameter.indexOf(ExerciceFormData.CHAMP_POINTS)
              + ExerciceFormData.CHAMP_POINTS.length())));
      nbPoints.put(indexQuestion, ExerciceFormMethods.recupererValeurChamp(request,
          ExerciceFormData.CHAMP_POINTS + indexQuestion));
    }
  }

  /**
   * Récupérer réponses.
   *
   * @param request la request
   * @param reponses les réponses
   * @param parameter le parametre récupéré depuis la page web
   */
  private void recupererReponses(HttpServletRequest request, final Map<Long, String> reponses,
      String parameter) {
    Long indexQuestion;
    if (parameter.startsWith(ExerciceFormData.CHAMP_REPONSE)) {
      indexQuestion = new Long(
          Integer.parseInt(parameter.substring(parameter.indexOf(ExerciceFormData.CHAMP_REPONSE)
              + ExerciceFormData.CHAMP_REPONSE.length())));
      reponses.put(indexQuestion, ExerciceFormMethods.recupererValeurChamp(request,
          ExerciceFormData.CHAMP_REPONSE + indexQuestion));
    }
  }

  /**
   * Récupérer les questions de l'examen.
   *
   * @param request la request
   * @param questions les questions
   * @param parameter le parametre recupéré depuis la page web
   */
  private void recupererQuestions(HttpServletRequest request, final Map<Long, String> questions,
      String parameter) {
    Long indexQuestion;
    if (parameter.startsWith(ExerciceFormData.CHAMP_QUESTION)) {
      indexQuestion = new Long(
          Integer.parseInt(parameter.substring(parameter.indexOf(ExerciceFormData.CHAMP_QUESTION)
              + ExerciceFormData.CHAMP_QUESTION.length())));
      questions.put(indexQuestion, ExerciceFormMethods.recupererValeurChamp(request,
          ExerciceFormData.CHAMP_QUESTION + indexQuestion));
    }
  }

  /**
   * Récupérer les competences de l'examen.
   *
   * @param parameters les parametres recupérés depuis la page web
   * @param competences les compétences
   * @param indexMaxCompetences l'index max des compétences
   * @param indexMaxQuestion l'index max des questions
   * @param parameter le parametre recupéré depuis la page web
   * @return Le paramètre long
   */
  private Long recupererCompetences(Map<String, String[]> parameters,
      final Map<Long, Map<Long, String>> competences, final Map<Long, Long> indexMaxCompetences,
      Long indexMaxQuestion, String parameter) {
    Long indexQuestion;
    Long indexMaxQuestionTmp = indexMaxQuestion;
    if (parameter.startsWith(ExerciceFormData.CHAMP_COMPETENCE)) {
      indexQuestion =
          new Long(
              Integer
                  .parseInt(
                      parameter.substring(
                          parameter.indexOf(ExerciceFormData.CHAMP_COMPETENCE)
                              + ExerciceFormData.CHAMP_COMPETENCE.length(),
                          parameter.indexOf('_'))));
      final Long indexCompetence =
          new Long(Integer.parseInt(parameter.substring(parameter.indexOf('_') + 1)));
      if (indexQuestion > indexMaxQuestion) {
        indexMaxQuestionTmp = indexQuestion;
      }
      for (final String competence : parameters.get(parameter)) {
        if (competences.get(indexQuestion) == null) {
          competences.put(new Long(indexQuestion), new HashMap<>());
        }
        competences.get(indexQuestion).put(new Long(indexCompetence), competence);
      }
      if (indexMaxCompetences.get(indexQuestion) == null) {
        indexMaxCompetences.put(indexQuestion, indexCompetence);
      } else {
        if (indexCompetence > indexMaxCompetences.get(indexQuestion)) {
          indexMaxCompetences.put(indexQuestion, indexCompetence);
        }
      }
    }
    return indexMaxQuestionTmp;
  }

  /**
   * Recuperer pourcentages.
   *
   * @param parameters les parametres recupérés depuis la page web
   * @param pourcentages les pourcentages
   * @param indexMaxCompetences l'index max des compétences
   * @param indexMaxQuestion l'index max des questions
   * @param parameter le parametre recupéré depuis la page web
   * @return Le paramètre long
   */
  private Long recupererPourcentages(Map<String, String[]> parameters,
      final Map<Long, Map<Long, String>> pourcentages, final Map<Long, Long> indexMaxCompetences,
      Long indexMaxQuestion, String parameter) {
    Long indexQuestion;
    Long indexMaxQuestionTmp = indexMaxQuestion;
    if (parameter.startsWith(ExerciceFormData.CHAMP_POURCENTAGE)) {
      indexQuestion =
          new Long(
              Integer
                  .parseInt(
                      parameter.substring(
                          parameter.indexOf(ExerciceFormData.CHAMP_POURCENTAGE)
                              + ExerciceFormData.CHAMP_POURCENTAGE.length(),
                          parameter.indexOf('_'))));
      final Long indexPourcentage =
          new Long(Integer.parseInt(parameter.substring(parameter.indexOf('_') + 1)));
      if (indexQuestion > indexMaxQuestion) {
        indexMaxQuestionTmp = indexQuestion;
      }
      if (indexMaxCompetences.get(indexQuestion) == null) {
        indexMaxCompetences.put(indexQuestion, indexPourcentage);
      } else {
        if (indexPourcentage > indexMaxCompetences.get(indexQuestion)) {
          indexMaxCompetences.put(indexQuestion, indexPourcentage);
        }
      }
      for (final String pourcentage : parameters.get(parameter)) {
        if (pourcentages.get(indexQuestion) == null) {
          pourcentages.put(new Long(indexQuestion), new HashMap<>());
        }
        pourcentages.get(indexQuestion).put(new Long(indexPourcentage), pourcentage);
      }

    }
    return indexMaxQuestionTmp;
  }

  /**
   * Traiter les exercice de l'examen.
   *
   * @param ancienExercice l'ancien exercice
   * @param question la question
   * @param reponse la réponse
   * @param nbPoints le nombre de points
   * @param competences les compétences
   * @param pourcentages les pourcentages
   * @param examen l'examen
   * @param indexMax l'index max
   * @return Le paramètre exercice
   */
  private Exercice traiterExercice(Exercice ancienExercice, String question, String reponse,
      String nbPoints, Map<Long, String> competences, Map<Long, String> pourcentages, String examen,
      Long indexMax) {
    final Exercice exercice = new Exercice();
    final Examen exam = new Examen();
    exam.setNom(examen);
    try {
      this.traiterValeurs(question, reponse, nbPoints, exercice);
      this.traiterDonnees(competences, pourcentages, exercice, indexMax);
      if (this.getErreurs().isEmpty()) {
        this.data.getExerciceBdd().ajouter(exercice, exam);
        this.data.getExerciceBdd().supprimer(ancienExercice);
        this.data.setResultat("Succès de la modification de l'exercice.");
      } else {
        this.data.setResultat("Échec de la modification de l'exercice.");
      }
    } catch (final DaoException ebdd) {
      this.data.setResultat("Échec de l'ajout de l'exercice : une erreur imprévue est survenue,"
          + " merci de réessayer dans quelques instants.");
      ModifierExerciceForm.LOGGER.log(Level.INFO, ExerciceFormData.EXCEPTION, ebdd);
    }
    if ("Échec de l'ajout de l'exercice.".equals(this.recupererResultat())) {
      return ancienExercice;
    }
    return exercice;
  }

  /**
   * Traiter donnees.
   *
   * @param competences les compétences
   * @param pourcentages les pourcentages
   * @param exercice l'exercice
   * @param index l'index
   */
  private void traiterDonnees(Map<Long, String> competences, Map<Long, String> pourcentages,
      Exercice exercice, Long index) {
    for (Long i = new Long(1); i < index + 1; i++) {
      try {
        this.validationPourcentage(pourcentages.get(i));
        if (exercice.getPourcentages() == null) {
          exercice.setPourcentages(new HashMap<>());
        }
        exercice.getPourcentages().put(new Long(i), Integer.parseInt(pourcentages.get(i)));
      } catch (final FormValidationException fve) {
        this.setErreur(ExerciceFormData.CHAMP_POURCENTAGE, fve.getMessage());
        ModifierExerciceForm.LOGGER.log(Level.INFO, ExerciceFormData.EXCEPTION, fve);
      }
    }
    for (Long i = new Long(1); i < index + 1; i++) {
      try {
        this.validationCompetence(competences.get(i));
        if (exercice.getCompetences() == null) {
          exercice.setCompetences(new HashMap<>());
        }
        exercice.getCompetences().put(new Long(i),
            this.data.getCompetenceBdd().trouverUniqueParNom(competences.get(i)));
      } catch (final FormValidationException fve) {
        this.setErreur(ExerciceFormData.CHAMP_COMPETENCE, fve.getMessage());
        ModifierExerciceForm.LOGGER.log(Level.INFO, ExerciceFormData.EXCEPTION, fve);
      }
    }
  }

  /**
   * Traiter valeurs.
   *
   * @param question la question
   * @param reponse la réponse
   * @param nbPoints le nombre de points
   * @param exercice l'exercice
   */
  private void traiterValeurs(String question, String reponse, String nbPoints, Exercice exercice) {
    try {
      this.validationQuestion(question);
      exercice.setQuestion(question);
    } catch (final FormValidationException fve) {
      this.setErreur(ExerciceFormData.CHAMP_QUESTION, fve.getMessage());
      ModifierExerciceForm.LOGGER.log(Level.INFO, ExerciceFormData.EXCEPTION, fve);
    }
    try {
      this.validationReponse(reponse);
      exercice.setReponse(reponse);
    } catch (final FormValidationException fve) {
      this.setErreur(ExerciceFormData.CHAMP_REPONSE, fve.getMessage());
      ModifierExerciceForm.LOGGER.log(Level.INFO, ExerciceFormData.EXCEPTION, fve);
    }
    try {
      this.validationPoints(nbPoints);
      exercice.setNbPoints(Float.parseFloat(nbPoints));
    } catch (final FormValidationException fve) {
      this.setErreur(ExerciceFormData.CHAMP_POINTS, fve.getMessage());
      ModifierExerciceForm.LOGGER.log(Level.INFO, ExerciceFormData.EXCEPTION, fve);
    }
  }

}

