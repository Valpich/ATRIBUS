package fr.eseo.atribus.forms;

import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.dao.ExamenDaoImpl;
import fr.eseo.atribus.entities.Examen;

import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ModifierExerciceFormTest {
  ModifierExerciceForm form;
  ExamenDaoImpl examenDao;

  @BeforeClass
  public void beforeClass() {
    this.form = new ModifierExerciceForm();
    this.examenDao = new ExamenDaoImpl();
  }

  @Test
  public void modifierExercice() {
    final List<String> reponses = new ArrayList<>();
    reponses.add("Reponse1");
    reponses.add("Reponse2");
    final List<String> competences = new ArrayList<>();
    competences.add("Exploiter");
    final List<String> pourcentages = new ArrayList<>();
    pourcentages.add("19");
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
    final Map<String, String[]> parameters = new HashMap<>();
    final String[] tmp = { "12" };
    final String[] tmpDeux = { "Exploiter qdqsd" };
    final String[] tmpTrois = { "1.2" };
    final String[] tmpQuatre = { "Question sdfsdf" };
    final String[] tmpCinq = { "Repons sffe" };
    parameters.put("pourcentage1_1", tmp);
    parameters.put("choixCompetence1_1", tmpDeux);
    parameters.put("points1", tmpTrois);
    parameters.put("question1", tmpQuatre);
    parameters.put("reponse1", tmpCinq);
    final String examen = this.examenDao.trouverTousLesExamens().get(0).getNom();
    Mockito.when(httpServletRequest.getParameterMap()).thenReturn(parameters);
    Mockito.when(httpServletRequest.getParameter("choixExamen")).thenReturn(examen);
    Mockito.when(httpServletRequest.getParameter("question1")).thenReturn("Question numero 1");
    Mockito.when(httpServletRequest.getParameter("reponse1")).thenReturn("Reponse numero 1");
    Mockito.when(httpServletRequest.getParameter("points1")).thenReturn("1.2");
    final Examen examenAncien = this.examenDao.trouverTousLesExamens().get(0);
    assertNotNull(this.form.modifierExercices(examenAncien, httpServletRequest));
  }
}
