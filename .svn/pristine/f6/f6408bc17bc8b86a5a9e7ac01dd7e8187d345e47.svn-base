package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilisateurTest {

  @Test(priority = 1)
  public void testConstructeurVide() {
    final Utilisateur utilisateur = new Utilisateur();
    assertNotNull(utilisateur);
  }

  @Test(priority = 2)
  public void testConstructeurUtilisateur() {

    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setLogin("login");
    utilisateur.setPassword("password");
    utilisateur.setNom("nom");
    utilisateur.setPrenom("prenom");
    utilisateur.setEmail("email");
    utilisateur.setDateInscription(new Date());
    final Utilisateur utilisateur2 = new Utilisateur(utilisateur);

    assertNotNull(utilisateur2);
    assertNotEquals(utilisateur2, utilisateur);

  }

  @Test(priority = 3)
  public void testAccesseurGetLogin() {
    final String string = "login";
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setLogin("login");
    assertNotNull(utilisateur);
    assertEquals(utilisateur.getLogin(), string);
  }

  @Test(priority = 4)
  public void testAccesseurGetPassword() {
    final String string = "password";
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setPassword("password");
    assertNotNull(utilisateur);
    assertEquals(utilisateur.getPassword(), string);
  }

  @Test(priority = 5)
  public void testAccesseurGetNom() {
    final String string = "nom";
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setNom("nom");
    assertNotNull(utilisateur);
    assertEquals(utilisateur.getNom(), string);
  }



  @Test(priority = 6)
  public void testAccesseurGetPrenom() {
    final String string = "prenom";
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setPrenom("prenom");
    assertNotNull(utilisateur);
    assertEquals(utilisateur.getPrenom(), string);
  }



  @Test(priority = 7)
  public void testAccesseurGetEmail() {
    final String string = "email";
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setEmail("email");
    assertNotNull(utilisateur);
    assertEquals(utilisateur.getEmail(), string);
  }



  @Test(priority = 8)
  public void testAccesseurGetDateInscription() {
    final Date date = new Date();
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setDateInscription(date);
    assertNotNull(utilisateur);
    assertEquals(utilisateur.getDateInscription(), date);
  }

  @Test(priority = 9)
  public void testMethodeToString() {

    final Utilisateur utilisateur = new Utilisateur();
    final String string =
        "Utilisateur [id=1, login=login, password=password, nom=nom, prenom=prenom, "
            + "email=email, dateInscription=null, notificationActive=false, notificationMail=false, "
            + "frequenceNotificationMail=0, notifications=null]";

    utilisateur.setId(1);
    utilisateur.setLogin("login");
    utilisateur.setPassword("password");
    utilisateur.setNom("nom");
    utilisateur.setPrenom("prenom");
    utilisateur.setEmail("email");
    System.out.println(utilisateur.toString());
    assertNotNull(utilisateur);
    assertEquals(utilisateur.toString(), string);

  }

  @Test
  public void testConstructeurPasVide() {

    @SuppressWarnings("deprecation")
    final Utilisateur utilisateur = new Utilisateur("demayale", "password", "DEMAY", "Alexis",
        "alexis.demay@reseau.eseo.fr", new Date(1, 1, 1));

    assertNotNull(utilisateur);
    assertEquals(utilisateur.getNom(), "DEMAY");

  }

  @Test
  public void getSetId() {
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setId(1);
    assertEquals(utilisateur.getId(), 1);
  }

  @Test
  public void toJson() {
    final Utilisateur utilisateur = new Utilisateur();
    assertEquals(utilisateur.toJson(), utilisateur.toJson());
  }

  @Test
  public void valueBoundTest() {
    final Utilisateur utilisateur = new Utilisateur();
    assertNotNull(utilisateur);
  }

  @Test
  public void valueUnboundTest() {
    final Utilisateur utilisateur = new Utilisateur();
    assertNotNull(utilisateur);
  }

  @Test
  public void isNotificationActiveTest() {
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setNotificationActive(true);
    assertEquals(utilisateur.isNotificationActive(), true);
  }

  @Test
  public void isNotificationMailTest() {
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setNotificationMail(true);
    assertEquals(utilisateur.isNotificationMail(), true);
  }

  @Test
  public void frequenceNotificationMailTest() {
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setFrequenceNotificationMail(5);
    assertEquals(utilisateur.getFrequenceNotificationMail(), 5);
  }

  @Test
  public void listeNotificationTest() {

    final Utilisateur utilisateur = new Utilisateur();
    final List<Notification> listeNotifications = new ArrayList<>();
    final Notification notif1 = new Notification();
    final Notification notif2 = new Notification();

    notif1.setId(1);
    notif2.setId(2);

    notif1.setMessage("Notif 1 test");
    notif2.setMessage("Notif 2 test");

    listeNotifications.add(notif1);
    listeNotifications.add(notif2);

    utilisateur.setNotifications(listeNotifications);

    assertNotNull(listeNotifications);
    assertNotNull(utilisateur.getNotifications());

    assertEquals(utilisateur.getNotifications().get(0).getMessage(), "Notif 1 test");
    assertEquals(utilisateur.getNotifications().get(1).getMessage(), "Notif 2 test");

  }

}
