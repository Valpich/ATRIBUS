package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UETest {

  private UniteEnseignement ue = new UniteEnseignement();
  private final String nom = "nom";
  private final int credit = 12;
  private final int heure = 50;
  private final EnseignantRefUe erue = new EnseignantRefUe();
  private final Promotion promotion = new Promotion();
  private final Map<Long, CompetenceValidable> competences = new HashMap<>();

  @Test(priority = 1)
  public void testAccesseurGetNom() {
    this.ue.setNom(this.nom);
    assertNotNull(this.ue);
    assertEquals(this.ue.getNom(), this.nom);
  }


  @Test(priority = 2)
  public void testAccesseurGetNbCreditsEcts() {
    this.ue.setNbCreditsEcts(this.credit);
    assertNotNull(this.ue);
    assertEquals(this.ue.getNbCreditsEcts(), this.credit);
  }


  @Test(priority = 3)
  public void testAccesseurGetNbHeures() {
    this.ue.setNbHeures(this.heure);
    assertNotNull(this.ue);
    assertEquals(this.ue.getNbHeures(), this.heure);
  }


  @Test(priority = 4)
  public void testAccesseurGetEnseignantRefUe() {
    this.erue.setPrenom("Juan-Carlos");
    this.ue.setEnseignantRefUe(this.erue);
    assertNotNull(this.ue);
    assertEquals(this.ue.getEnseignantRefUe(), this.erue);
  }


  @Test(priority = 5)
  public void testAccesseurSemestre() {
    final Semestre semestre = new Semestre();
    semestre.setNumeroSemestre(1);
    this.ue.setSemestre(semestre);
    assertNotNull(this.ue);
    assertEquals(this.ue.getSemestre().getNumeroSemestre(), 1);
  }

  @Test(priority = 6)
  public void testMethodeToString() {

    this.ue = new UniteEnseignement();

    assertNotNull(this.ue);
    assertEquals(this.ue.toString(), this.ue.toString());

  }

  @Test(priority = 7)
  public void testAccesseurGetCompetences() {
    this.ue.setCompetences(this.competences);
    assertNotNull(this.ue);
    assertEquals(this.ue.getCompetences(), this.competences);
  }

}
