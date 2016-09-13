package fr.eseo.atribus.entities;

import static org.testng.Assert.assertNotNull;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DirecteurEtudesTest {
  DirecteurEtudes de;

  @Test
  public void DirecteurEtudes() {
    final Utilisateur utilisateur = new Utilisateur();
    this.de = new DirecteurEtudes(utilisateur);
    assertNotNull(this.de);
  }

  @Test
  public void DirecteurEtudesConstructeur() {
    assertNotNull(this.de);

  }

  @Test
  public void DirecteurEtudestoString() {
    final DirecteurEtudes de = new DirecteurEtudes();
    Assert.assertEquals(de.toString(), de.toString());
  }

  @Test
  public void DirecteurEtudesgetId() {
    final DirecteurEtudes de = new DirecteurEtudes();
    de.setIdDirecteurEtudes(-1);

    Assert.assertEquals(de.getIdDirecteurEtudes(), -1);
  }
}


