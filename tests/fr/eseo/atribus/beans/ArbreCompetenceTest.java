package fr.eseo.atribus.beans;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ArbreCompetenceTest {

  private ArbreCompetence arbre;

  @BeforeTest
  public void init() {
    this.arbre = new ArbreCompetence();
  }

  @Test
  public void accesseurNomCompetence() {
    this.arbre.setNomCompetence("test");
    assertNotNull(this.arbre.getNomCompetence());
  }

  @Test
  public void accesseuChildren() {
    this.arbre.setChildren(new ArrayList<>());
    assertNotNull(this.arbre.getChildren());
  }
}
