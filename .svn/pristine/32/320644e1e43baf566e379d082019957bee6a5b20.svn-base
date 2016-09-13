package fr.eseo.atribus.entities;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ExamenTest {

  @BeforeClass
  public void beforeClass() {}

  @Test(priority = 1)
  public void testAccesseurGetIdMatiere() {
    final Matiere matiere = new Matiere();
    final Examen examen = new Examen();
    examen.setMatiere(matiere);
    Assert.assertNotNull(examen);
    Assert.assertEquals(examen.getMatiere(), matiere,
        "Erreur sur l'accesseur en lecture suivant : idMatiere !");
  }


  @Test(priority = 2)
  public void testAccesseurSetIdMatiereInt() {
    Matiere matiere = new Matiere();
    final Examen examen = new Examen();
    examen.setMatiere(matiere);
    Assert.assertNotNull(examen);
    Assert.assertEquals(examen.getMatiere(), matiere,
        "Erreur sur l'accesseur en lecture suivant : idMatiere !");
    matiere = new Matiere();
    examen.setMatiere(matiere);
    Assert.assertEquals(examen.getMatiere(), matiere,
        "Erreur sur l'accesseur en écriture suivant : idMatiere !");
  }


  @Test(priority = 3)
  public void testAccesseurGetQuestions() {
    final ArrayList<Exercice> arraylist = new ArrayList<Exercice>();
    final Examen examen = new Examen();
    Assert.assertNotNull(examen);
    Assert.assertNull(examen.getExercices());
    examen.setExercices(arraylist);
    Assert.assertNotNull(examen.getExercices());
  }


  @Test(priority = 4)
  public void testAccesseurSetQuestionsArrayList() {
    final ArrayList<Exercice> arraylist = new ArrayList<Exercice>();
    final Examen examen = new Examen();
    Assert.assertNotNull(examen);
    examen.setExercices(arraylist);
    Assert.assertEquals(examen.getExercices(), arraylist,
        "Erreur sur l'accesseur en écriture suivant : questions !");
  }


  @Test(priority = 5)
  public void testAccesseurGetNom() {
    final String string = "Test";
    final Examen examen = new Examen();
    Assert.assertNotNull(examen);
    examen.setNom(string);
    Assert.assertEquals(examen.getNom(), "Test",
        "Erreur sur l'accesseur en lecture suivant : nom !");
  }


  @Test(priority = 6)
  public void testAccesseurSetNomString() {
    final String string = "Test";
    final Examen examen = new Examen();
    Assert.assertNotNull(examen);
    examen.setNom(string);
    Assert.assertEquals(examen.getNom(), "Test",
        "Erreur sur l'accesseur en lecture suivant : nom !");
  }


  @Test(priority = 7)
  public void testMethodeToString() {
    final String nom = "Test";
    final Examen examen = new Examen();
    Assert.assertNotNull(examen);
    final Matiere matiere = new Matiere();
    final ArrayList<Exercice> exercices = new ArrayList<Exercice>();
    final Boolean autoEvaluation = false;
    final int id = -1;
    examen.setId(id);
    examen.setNom(nom);
    examen.setMatiere(matiere);
    examen.setExercices(exercices);
    examen.setAutoEvaluation(autoEvaluation);
    Assert.assertEquals(examen.toString(),
        "Examen [id=" + id + ", matiere=" + matiere + ", exercices=" + exercices + ", nom=" + nom
            + ", autoEvaluation=" + autoEvaluation + "]",
        "La méthode toString n'a pas retourné le bon résultat !");
  }

  @AfterClass
  public void afterClass() {}

}
