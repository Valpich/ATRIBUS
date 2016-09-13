package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

public class CompetenceTest {

  @Test
  public void getId() {
    final Integer id = 2;
    final Competence competence = new Competence();
    competence.setId(2);
    competence.setIdParent(1);
    competence.setProfondeur(3);
    competence.setNom("nom");
    assertNotNull(competence);
    assertEquals(competence.getId(), id, "getIdParent OK");
  }

  @Test
  public void getIdParent() {
    final Integer idP = 1;
    final Competence competence = new Competence();
    competence.setId(2);
    competence.setIdParent(1);
    competence.setProfondeur(3);
    competence.setNom("nom");
    assertNotNull(competence);
    assertEquals(competence.getIdParent(), idP, "getIdParent OK");
  }

  @Test
  public void getNom() {
    final Competence competence = new Competence();
    competence.setId(2);
    competence.setIdParent(1);
    competence.setProfondeur(3);
    competence.setNom("nom");
    assertNotNull(competence);
    assertEquals(competence.getNom(), "nom", "getNom OK");
  }

  @Test
  public void getProfondeur() {
    final Integer profondeur = 3;
    final Competence competence = new Competence();
    competence.setId(2);
    competence.setIdParent(1);
    competence.setProfondeur(3);
    competence.setNom("nom");
    assertNotNull(competence);
    assertEquals(competence.getProfondeur(), profondeur, "getProfondeur OK");
  }

  @Test
  public void getUe() {
    final Competence competence = new Competence();
    final UniteEnseignement ue = new UniteEnseignement();
    competence.setUe(ue);
    assertEquals(competence.getUe(), ue);
  }


}
