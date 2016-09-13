package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UtilisateurLdapTest {

  UtilisateurLdap utilisateur;

  @BeforeTest
  public void init() {
    this.utilisateur = new UtilisateurLdap();
  }

  @Test
  public void utilisateurLdap() {
    assertNotNull(this.utilisateur);
  }

  @Test
  public void getCn() {
    this.utilisateur.setCn("cn");
    assertEquals(this.utilisateur.getCn(), "cn");
  }

  @Test
  public void getGivenName() {
    this.utilisateur.setGivenName("givenName");
    assertEquals(this.utilisateur.getGivenName(), "givenName");
  }

  @Test
  public void getMail() {
    this.utilisateur.setMail("mail");
    assertEquals(this.utilisateur.getMail(), "mail");
  }

  @Test
  public void getObjectClass() {
    this.utilisateur.setObjectClass("objectClass");
    assertEquals(this.utilisateur.getObjectClass(), "objectClass");
  }

  @Test
  public void getSn() {
    this.utilisateur.setSn("sn");
    assertEquals(this.utilisateur.getSn(), "sn");
  }

  @Test
  public void getUid() {
    this.utilisateur.setUid("uid");
    assertEquals(this.utilisateur.getUid(), "uid");
  }

  @Test
  public void getUserpassword() {
    this.utilisateur.setUserpassword("userpassword");
    assertEquals(this.utilisateur.getUserpassword(), "userpassword");
  }

  @Test
  public void methodToString() {
    assertEquals(this.utilisateur.toString(), this.utilisateur.toString());
  }
}
