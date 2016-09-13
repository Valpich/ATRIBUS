package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminSystTest {

  AdminSyst admin;

  @BeforeTest
  public void beforeTest() {
    this.admin = new AdminSyst();
    assertNotNull(this.admin);
  }

  @Test
  public void contructeurUtilisateur() {

    final Utilisateur utilisateur = new Utilisateur();

    utilisateur.setId(3);
    utilisateur.setNom("NomAdmin");

    this.admin = new AdminSyst(utilisateur);

    assertNotNull(this.admin);

  }

  @Test
  public void getSetIdAdminSystem() {
    this.admin.setIdAdminSyst(1);
    assertEquals(this.admin.getIdAdminSyst(), 1);
  }

  @Test
  public void methodToString() {
    assertEquals(this.admin.toString(), this.admin.toString());
  }

}
