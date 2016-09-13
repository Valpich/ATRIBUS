package fr.eseo.atribus.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Evaluation;
import fr.eseo.atribus.entities.Examen;
import fr.eseo.atribus.entities.Exercice;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class EvaluationDaoImplTest {

  private EvaluationDaoImpl evaluationDao;
  private ExamenDaoImpl examenDao;
  private EleveDaoImpl eleveDao;
  private ExerciceDaoImpl exerciceDao;

  @BeforeTest
  public void beforeTest() {
    this.evaluationDao = new EvaluationDaoImpl();
    this.examenDao = new ExamenDaoImpl();
    this.eleveDao = new EleveDaoImpl();
  }

  @Test
  public void majEvaluation() {

    List<Evaluation> liste = new ArrayList<>();

    liste = this.evaluationDao.trouverToutesLesEvaluations();

    this.evaluationDao.majEvaluation(liste);

    assertEquals(liste.get(0).getId(),
        this.evaluationDao.trouverToutesLesEvaluations().get(0).getId());

  }

  @Test
  public void repondre() {

    Eleve eleve = new Eleve();
    Examen examen = new Examen();
    Exercice exercice1 = new Exercice();
    Exercice exercice2 = new Exercice();
    List<String> reponses = new ArrayList<>();
    List<Exercice> exercices = new ArrayList<>();
    Boolean autoEvaluation = new Boolean(false);

    eleve.setId(1);
    examen = this.examenDao.trouverTousLesExamens().get(0);
    reponses.add("ABCDE");
    reponses.add("FEFZF");
    try {
      this.evaluationDao.repondre(eleve, reponses, examen, autoEvaluation);
    } catch (Exception e) {
    }
    eleve.setId(3);
    eleve.setIdEleve(1);
    this.evaluationDao.repondre(eleve, reponses, examen, autoEvaluation);
    assertNotNull(this.evaluationDao.trouverToutesLesEvaluations());

  }

  @Test
  public void trouverToutesLesEvaluations() {
    assertNotNull(this.evaluationDao.trouverToutesLesEvaluations());
  }
}
