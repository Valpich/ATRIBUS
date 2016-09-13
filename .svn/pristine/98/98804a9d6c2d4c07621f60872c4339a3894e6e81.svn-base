package fr.eseo.atribus.entities;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ExerciceTest {

  @BeforeClass
  public void beforeClass() {}

  @Test(priority = 1)
  public void testAccesseurGetExamen() {
    final String string = "Test";
    final Examen examen = new Examen();
    Assert.assertNotNull(examen);
    examen.setNom(string);
    final Exercice question = new Exercice();
    Assert.assertNotNull(question);
  }



  @Test(priority = 3)
  public void testAccesseurGetCompetence() {
    final Competence competence = new Competence();
    final Exercice question = new Exercice();
    final Map<Long, Competence> competences = new HashMap<>();
    competences.put(new Long(1), competence);
    Assert.assertNotNull(question);
    question.setCompetences(competences);
    Assert.assertEquals(question.getCompetences(), competences,
        "Erreur sur l'accesseur en lecture suivant : competence !");
  }


  @Test(priority = 4)
  public void testAccesseurSetCompetenceCompetence() {
    final Competence competence = new Competence();
    Map<Long, Competence> competences = new HashMap<>();
    competences.put(new Long(1), competence);
    final Exercice question = new Exercice();
    Assert.assertNotNull(question);
    question.setCompetences(competences);
    Assert.assertEquals(question.getCompetences(), competences,
        "Erreur sur l'accesseur en lecture suivant : competence !");
    competences = new HashMap<>();
    competences.put(new Long(2), competence);
    question.setCompetences(competences);
    Assert.assertEquals(question.getCompetences(), competences,
        "Erreur sur l'accesseur en écriture suivant : competence !");
  }


  @Test(priority = 5)
  public void testAccesseurGetQuestion() {
    final String string = "Comment ça va ?";
    final Exercice question = new Exercice();
    Assert.assertNotNull(question);
    question.setQuestion(string);
    Assert.assertEquals(question.getQuestion(), string,
        "Erreur sur l'accesseur en lecture suivant : question !");
  }


  @Test(priority = 6)
  public void testAccesseurSetQuestionString() {
    final String string = "Comment ça va ?";
    final Exercice question = new Exercice();
    Assert.assertNotNull(question);
    question.setQuestion(string);
    Assert.assertEquals(question.getQuestion(), string,
        "Erreur sur l'accesseur en lecture suivant : question !");
    final String stringDeux = "Très bien et toi ?";
    question.setQuestion(stringDeux);
    Assert.assertEquals(question.getQuestion(), stringDeux,
        "Erreur sur l'accesseur en écriture suivant : question !");
  }


  @Test(priority = 7)
  public void testAccesseurGetReponse() {
    final String reponse = "La bonne réponse";
    final Exercice exercice = new Exercice();
    Assert.assertNotNull(exercice);
    exercice.setQuestion(reponse);
    Assert.assertEquals(exercice.getQuestion(), reponse,
        "Erreur sur l'accesseur en lecture suivant : question !");
  }


  @Test(priority = 8)
  public void testAccesseurSetReponseString() {
    final String string = "La bonne réponse";
    final Exercice question = new Exercice();
    Assert.assertNotNull(question);
    question.setQuestion(string);
    Assert.assertEquals(question.getQuestion(), string,
        "Erreur sur l'accesseur en lecture suivant : question !");
    final String stringDeux = "La mauvaise réponse";
    question.setQuestion(stringDeux);
    Assert.assertEquals(question.getQuestion(), stringDeux,
        "Erreur sur l'accesseur en écriture suivant : question !");
  }


  @Test(priority = 9)
  public void testAccesseurGetNbPoints() {
    final Float nbPoints = 20.0F;
    final Exercice exercice = new Exercice();
    Assert.assertNotNull(exercice);
    exercice.setNbPoints(nbPoints);
    Assert.assertEquals(exercice.getNbPoints(), nbPoints, 0.00001,
        "Erreur sur l'accesseur en écriture suivant : nbPoints !");
  }


  @Test(priority = 10)
  public void testAccesseurSetNbPointsFloat() {
    Float nbPoints = 10.0F;
    final Exercice exercice = new Exercice();
    Assert.assertNotNull(exercice);
    exercice.setNbPoints(nbPoints);
    Assert.assertEquals(exercice.getNbPoints(), nbPoints, 0.00001,
        "Erreur sur l'accesseur en lecture suivant : nbPoints !");
    nbPoints = 8.0F;
    exercice.setNbPoints(nbPoints);
    Assert.assertEquals(exercice.getNbPoints(), nbPoints, 0.00001,
        "Erreur sur l'accesseur en écriture suivant : nbPoints !");
  }

  @Test(priority = 9)
  public void testAccesseurGetNote() {
    final Float nbPoints = 15.0F;
    final Exercice exercice = new Exercice();
    Assert.assertNotNull(exercice);
    exercice.setNbPoints(nbPoints);
    Assert.assertEquals(exercice.getNbPoints(), nbPoints, 0.00001,
        "Erreur sur l'accesseur en écriture suivant : nbPoints !");
  }


  @Test(priority = 10)
  public void testAccesseurSetNote() {
    Float nbPoints = 8.0F;
    final Exercice question = new Exercice();
    Assert.assertNotNull(question);
    question.setNbPoints(nbPoints);
    Assert.assertEquals(question.getNbPoints(), nbPoints, 0.00001,
        "Erreur sur l'accesseur en lecture suivant : nbPoints !");
    nbPoints = 8.0F;
    question.setNbPoints(nbPoints);
    Assert.assertEquals(question.getNbPoints(), nbPoints, 0.00001,
        "Erreur sur l'accesseur en écriture suivant : nbPoints !");
  }


  @Test(priority = 13)
  public void testMethodeToString() {
    final Exercice exercice = new Exercice();
    Assert.assertNotNull(exercice);
    final Competence competence = new Competence();
    final String question = "Question";
    final String reponse = "Reponse";
    final Float nbPoints = 13.3F;
    final Map<Long, Competence> competences = new HashMap<>();
    competences.put(new Long(1), competence);
    exercice.setCompetences(competences);
    exercice.setNbPoints(nbPoints);
    exercice.setQuestion(question);
    exercice.setReponse(reponse);
    Assert.assertEquals(exercice.toString(),
        "Exercice [competence=" + competences + ", question=" + question + ", reponse=" + reponse
            + ", nbPoints=" + nbPoints + "]",
        "La méthode toString n'a pas retourné le bon résultat !");
  }

  @AfterClass
  public void afterClass() {}

}
