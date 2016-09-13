package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import java.util.Date;

public class EvaluationTest {

  Evaluation evaluation;

  @Test
  public void evaluation() {
    this.evaluation = new Evaluation();
    assertNotNull(this.evaluation);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void getDateExamen() {
    this.evaluation.setDateExamen(new Date(1, 1, 1));
    assertEquals(this.evaluation.getDateExamen(), new Date(1, 1, 1));
  }

  @Test
  public void getEleve() {
    final Eleve eleve = new Eleve();
    eleve.setNom("TestNom");
    this.evaluation.setEleve(eleve);
    assertEquals(this.evaluation.getEleve().getNom(), "TestNom");
  }

  @Test
  public void getExercice() {
    final Exercice exercice = new Exercice();
    exercice.setReponse("TestReponse");
    this.evaluation.setExercice(exercice);
    assertEquals(this.evaluation.getExercice().getReponse(), "TestReponse");
  }

  @Test
  public void getId() {
    this.evaluation.setId(1);
    assertEquals(this.evaluation.getId(), 1);
  }

  @Test
  public void getNote() {
    this.evaluation.setNote(new Float(1));
    assertEquals(this.evaluation.getNote(), new Float(1));
  }

  @Test
  public void getReponse() {
    this.evaluation.setReponse("ReponseTest");
    assertEquals(this.evaluation.getReponse(), "ReponseTest");
  }

  @Test
  public void methodToString() {
    assertEquals(this.evaluation.toString(), this.evaluation.toString());
  }
}
