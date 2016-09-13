package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;



// TODO = questions à poser
public class MatiereTest {
  Matiere matiere = new Matiere();
  private final float coef = 1;
  private final String nom = "nom";



  @Test
  public void getCoefficiant() {
    this.matiere.setCoefficient(this.coef);
    assertEquals(this.matiere.getCoefficient(), this.coef);
  }

  @Test
  public void getId() {
    this.matiere.setId(2);
    assertEquals(this.matiere.getId(), 2);
  }


  @Test
  public void getIdUniteEnseignement() {
    final UniteEnseignement uniteEnseignement = new UniteEnseignement();
    uniteEnseignement.setId(0);
    this.matiere.setUe(uniteEnseignement);
    assertEquals(this.matiere.getUe().getId(), 0);
  }

  @Test
  public void getNom() {
    this.matiere.setNom(this.nom);
    assertEquals(this.matiere.getNom(), this.nom);
  }

  @Test
  public void getSemestre() {
    final Semestre semestre = new Semestre();
    this.matiere.setSemestre(semestre);
    assertEquals(this.matiere.getSemestre(), semestre);
  }

  @Test
  public void getUe() {
    final UniteEnseignement ue = new UniteEnseignement();
    this.matiere.setUe(ue);
    assertEquals(this.matiere.getUe(), ue);
  }

}
