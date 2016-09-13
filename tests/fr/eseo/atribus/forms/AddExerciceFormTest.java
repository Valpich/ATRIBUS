package fr.eseo.atribus.forms;

import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.dao.ExamenDaoImpl;

import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class AddExerciceFormTest {
  AddExerciceForm form;
  ExamenDaoImpl examenDao;

  @BeforeClass
  public void beforeClass() {
    this.form = new AddExerciceForm();
    this.examenDao = new ExamenDaoImpl();
  }

  @Test
  public void ajouterExercice() {

    try {
      final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
      final Map<String, String[]> parameters = new HashMap<>();
      final List<String> reponses = new ArrayList<>();
      final List<String> competences = new ArrayList<>();
      final List<String> pourcentages = new ArrayList<>();

      reponses.add("Reponse1");
      reponses.add("Reponse2");
      competences.add("Exploiter");
      pourcentages.add("19");

      final String[] tmp = { "12" };
      final String[] tmpDeux = { "Exploiter" };
      parameters.put("pourcentage1", tmp);
      parameters.put("choixCompetence1", tmpDeux);

      final String point = "10";
      final String reponse = "Bonne réponse";
      final String question = "QuestionTest";
      final String examen = this.examenDao.trouverTousLesExamens().get(0).getNom();

      Mockito.when(httpServletRequest.getParameterMap()).thenReturn(parameters);
      Mockito.when(httpServletRequest.getParameter("question")).thenReturn(question);
      Mockito.when(httpServletRequest.getParameter("reponse")).thenReturn(reponse);
      Mockito.when(httpServletRequest.getParameter("points")).thenReturn(point);
      Mockito.when(httpServletRequest.getParameter("choixExamen")).thenReturn(examen);

      assertNotNull(this.form.addExercice(httpServletRequest));
    } catch (final Exception except) {
      except.getMessage();
    }

  }

  @Test
  public void ajouterAutoEvaluation() {

    final String question = "QuestionTest";
    final List<String> reponses = new ArrayList<>();
    reponses.add("Reponse1");
    reponses.add("Reponse2");
    final String nbPoints = "1";
    final List<String> competences = new ArrayList<>();
    competences.add("Exploiter");
    final List<String> pourcentages = new ArrayList<>();
    pourcentages.add("19");
    final String examen = this.examenDao.trouverTousLesExamens().get(0).getNom();
    final String bonneReponses = "1";
    assertNotNull(this.form.ajouterAutoEvaluation(question, reponses, nbPoints, competences,
        pourcentages, examen, bonneReponses));

  }


  @Test
  public void ajouterExerciceFail() {

    try {
      final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
      final Map<String, String[]> parameters = new HashMap<>();
      final List<String> competences = new ArrayList<>();
      final List<String> pourcentages = new ArrayList<>();


      competences.add("Exploiter");
      pourcentages.add("120");

      final String[] tmp = { "éé" };
      final String[] tmpDeux = { "Exploiter" };
      parameters.put("pourcentage1", tmp);
      parameters.put("choixCompetence1", tmpDeux);

      final String point = "-1";
      final String reponse = null;
      final String question = null;
      final String examen = null;

      Mockito.when(httpServletRequest.getParameterMap()).thenReturn(parameters);
      Mockito.when(httpServletRequest.getParameter("question")).thenReturn(question);
      Mockito.when(httpServletRequest.getParameter("reponse")).thenReturn(reponse);
      Mockito.when(httpServletRequest.getParameter("points")).thenReturn(point);
      Mockito.when(httpServletRequest.getParameter("choixExamen")).thenReturn(examen);
      this.form.recupererResultat();
      assertNotNull(this.form.addExercice(httpServletRequest));
    } catch (final Exception except) {
      except.getMessage();
    }

  }

  @Test
  public void ajouterExerciceFailDeux() {

    try {
      final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
      final Map<String, String[]> parameters = new HashMap<>();
      final List<String> competences = new ArrayList<>();
      final List<String> pourcentages = new ArrayList<>();


      competences.add("Exploiter");
      pourcentages.add("éé");

      final String[] tmp = { "-1" };
      final String[] tmpDeux = { "Expliter" };
      parameters.put("pourcentage1", tmp);
      parameters.put("choixCompetence1", tmpDeux);

      final String point = "é";
      final String reponse = null;
      final String question = null;
      final String examen = "teteguyqhjdlqssuq";

      Mockito.when(httpServletRequest.getParameterMap()).thenReturn(parameters);
      Mockito.when(httpServletRequest.getParameter("question")).thenReturn(question);
      Mockito.when(httpServletRequest.getParameter("reponse")).thenReturn(reponse);
      Mockito.when(httpServletRequest.getParameter("points")).thenReturn(point);
      Mockito.when(httpServletRequest.getParameter("choixExamen")).thenReturn(examen);
      this.form.recupererResultat();
      assertNotNull(this.form.addExercice(httpServletRequest));
    } catch (final Exception except) {
      except.getMessage();
    }
  }

  @Test
  public void ajouterExerciceFailTrois() {

    try {
      final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
      final Map<String, String[]> parameters = new HashMap<>();
      final List<String> competences = new ArrayList<>();
      final List<String> pourcentages = new ArrayList<>();


      competences.add("Exploiter");
      pourcentages.add("éé");

      final String[] tmp = { "-1" };
      final String[] tmpDeux = { "Expliter" };
      parameters.put("pourcentage1", tmp);
      parameters.put("choixCompetence1", tmpDeux);

      final String point = null;
      final String reponse = null;
      final String question = null;
      final String examen = null;

      Mockito.when(httpServletRequest.getParameterMap()).thenReturn(parameters);
      Mockito.when(httpServletRequest.getParameter("question")).thenReturn(question);
      Mockito.when(httpServletRequest.getParameter("reponse")).thenReturn(reponse);
      Mockito.when(httpServletRequest.getParameter("points")).thenReturn(point);
      Mockito.when(httpServletRequest.getParameter("choixExamen")).thenReturn(examen);
      this.form.recupererResultat();
      assertNotNull(this.form.addExercice(httpServletRequest));
    } catch (final Exception except) {
      except.getMessage();
    }
  }

  @Test
  public void ajouterAutoEvaluationFail() {
    try {
      final String question = "QuestionTest";
      final List<String> reponses = null;
      final String nbPoints = "1";
      final List<String> competences = null;
      final List<String> pourcentages = null;
      final String examen = null;
      final String bonneReponses = "é";
      assertNotNull(this.form.ajouterAutoEvaluation(question, reponses, nbPoints, competences,
          pourcentages, examen, bonneReponses));
    } catch (final Exception except) {
      except.getMessage();
    }
  }

  @Test
  public void ajouterAutoEvaluationFailDeux() {
    try {
      final String question = "QuestionTest";
      final List<String> reponses = new ArrayList<>();
      reponses.add("Reponse1");
      reponses.add("Reponse2");
      final String nbPoints = "1";
      final List<String> competences = new ArrayList<>();
      competences.add("Exploiter");
      final List<String> pourcentages = new ArrayList<>();
      pourcentages.add("19");
      final String examen = "dfgdfytfsuqgihoflk";
      final String bonneReponses = "1";
      assertNotNull(this.form.ajouterAutoEvaluation(question, reponses, nbPoints, competences,
          pourcentages, examen, bonneReponses));
    } catch (final Exception except) {
      except.getMessage();
    }
  }

  @Test
  public void ajouterAutoEvaluationFailTrois() {
    try {
      final String question = "QuestionTest";
      final List<String> reponses = new ArrayList<>();
      reponses.add("Reponse1");
      reponses.add("Reponse2");
      final String nbPoints = "1";
      final List<String> competences = new ArrayList<>();
      competences.add("Exploiter");
      final List<String> pourcentages = new ArrayList<>();
      pourcentages.add("19");
      final String examen = this.examenDao.trouverTousLesExamens().get(0).getNom();
      final String bonneReponses = "3";
      assertNotNull(this.form.ajouterAutoEvaluation(question, reponses, nbPoints, competences,
          pourcentages, examen, bonneReponses));
    } catch (final Exception except) {
      except.getMessage();
    }
  }

  @Test
  public void ajouterAutoEvaluationFailQuatre() {
    try {
      final String question = "QuestionTest";
      final List<String> reponses = new ArrayList<>();
      reponses.add("Re");
      reponses.add("Re");
      final String nbPoints = "1";
      final List<String> competences = new ArrayList<>();
      competences.add("Exploiter");
      final List<String> pourcentages = new ArrayList<>();
      pourcentages.add("19");
      final String examen = this.examenDao.trouverTousLesExamens().get(0).getNom();
      final String bonneReponses = "1";
      assertNotNull(this.form.ajouterAutoEvaluation(question, reponses, nbPoints, competences,
          pourcentages, examen, bonneReponses));
    } catch (final Exception except) {
      except.getMessage();
    }
  }

  @Test
  public void ajouterAutoEvaluationFailCinq() {
    try {
      final String question = "QuestionTest";
      final List<String> reponses = new ArrayList<>();
      reponses.add("Re");
      final String nbPoints = "1";
      final List<String> competences = new ArrayList<>();
      competences.add("Exploiter");
      final List<String> pourcentages = new ArrayList<>();
      pourcentages.add("19");
      final String examen = this.examenDao.trouverTousLesExamens().get(0).getNom();
      final String bonneReponses = "1";
      assertNotNull(this.form.ajouterAutoEvaluation(question, reponses, nbPoints, competences,
          pourcentages, examen, bonneReponses));
    } catch (final Exception except) {
      except.getMessage();
    }
  }
}
