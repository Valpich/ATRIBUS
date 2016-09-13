package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DirecteurProgrammesTest {

  DirecteurProgrammes dp;

  @BeforeTest
  public void beforeTest() {
    this.dp = new DirecteurProgrammes();
    assertNotNull(this.dp);
  }

  @Test
  public void contructeurDp() {

    final Utilisateur utilisateur = new Utilisateur();

    utilisateur.setNom("NomDp");

    this.dp = new DirecteurProgrammes(utilisateur);

    assertNotNull(this.dp);
    assertEquals(this.dp.getNom(), "NomDp");

  }

  @Test
  public void getSetIdDp() {
    this.dp.setIdDirecteurProgrammes(1);
    assertEquals(this.dp.getIdDirecteurProgrammes(), 1);
  }

  @Test
  public void methodToString() {
    assertEquals(this.dp.toString(), this.dp.toString());
  }

}
