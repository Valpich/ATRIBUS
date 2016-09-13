package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

public class NotificationTest {

  @BeforeClass
  public void beforeClass() {}

  @Test(priority = 1)
  public void testConstructeurVide() {
    final Notification notification = new Notification();
    assertNotNull(notification);
  }

  @Test(priority = 2)
  public void testAccesseurGetId() {
    final Notification notification = new Notification();
    notification.setId(1);
    assertEquals(notification.getId().intValue(), 1,
        "Erreur sur l'accesseur en lecture suivant : id !");
  }


  @Test(priority = 3)
  public void testAccesseurSetIdInteger() {
    final Notification notification = new Notification();
    notification.setId(1);
    assertNotNull(notification);
    notification.setId(new Integer(3));
    assertEquals(notification.getId().intValue(), 3,
        "Erreur sur l'accesseur en écriture suivant : id !");
    // TODO : FAIRE UN TEST !
  }


  @Test(priority = 4)
  public void testAccesseurGetEmetteur() {
    final Notification notification = new Notification();
    assertNotNull(notification);
    notification.setEmetteur(3);
    assertEquals(notification.getEmetteur().intValue(), 3,
        "Erreur sur l'accesseur en lecture suivant : emetteur !");
  }


  @Test(priority = 5)
  public void testAccesseurSetEmetteurInteger() {
    final Notification notification = new Notification();
    assertNotNull(notification);
    notification.setEmetteur(3);
    assertEquals(notification.getEmetteur().intValue(), 3,
        "Erreur sur l'accesseur en écriture suivant : emetteur !");
    notification.setEmetteur(4);
    assertEquals(notification.getEmetteur().intValue(), 4,
        "Erreur sur l'accesseur en écriture suivant : emetteur !");
  }


  @Test(priority = 6)
  public void testAccesseurGetDestinataire() {
    final Notification notification = new Notification();
    assertNotNull(notification);
    notification.setDestinataire(3);
    assertEquals(notification.getDestinataire().intValue(), 3,
        "Erreur sur l'accesseur en lecture suivant : destinataire!");
  }


  @Test(priority = 7)
  public void testAccesseurSetDestinataireInteger() {
    final Notification notification = new Notification();
    assertNotNull(notification);
    notification.setDestinataire(3);
    assertEquals(notification.getDestinataire().intValue(), 3,
        "Erreur sur l'accesseur en écriture suivant : destinataire !");
    notification.setDestinataire(4);
    assertEquals(notification.getDestinataire().intValue(), 4,
        "Erreur sur l'accesseur en écriture suivant : destinataire !");
  }


  @Test(priority = 8)
  public void testAccesseurGetMessage() {
    final String message = "message";
    final Notification notification = new Notification();
    assertNotNull(notification);
    notification.setMessage(message);
    assertEquals(notification.getMessage(), message,
        "Erreur sur l'accesseur en lecture suivant : message!");
  }


  @Test(priority = 9)
  public void testAccesseurSetMessageString() {
    String message = "message";
    final Notification notification = new Notification();
    assertNotNull(notification);
    notification.setMessage(message);
    assertEquals(notification.getMessage(), message,
        "Erreur sur l'accesseur en écriture suivant : message !");
    message = "messageDeux";
    notification.setMessage(message);
    assertEquals(notification.getMessage(), message,
        "Erreur sur l'accesseur en écriture suivant : message !");
  }


  @Test(priority = 10)
  public void testAccesseurGetNomEmetteur() {
    final Notification notification = new Notification();
    assertNotNull(notification);
    final String nom = "test";
    notification.setNomEmetteur(nom);
    assertEquals(notification.getNomEmetteur(), nom,
        "Erreur sur l'accesseur en lecture suivant : nomEmetteur !");
  }


  @Test(priority = 11)
  public void testAccesseurSetNomEmetteurString() {
    final Notification notification = new Notification();
    assertNotNull(notification);
    String nom = "test";
    notification.setNomEmetteur(nom);
    assertEquals(notification.getNomEmetteur(), nom,
        "Erreur sur l'accesseur en écriture suivant : nomEmetteur !");
    nom = "testDeux";
    notification.setNomEmetteur(nom);
    assertEquals(notification.getNomEmetteur(), nom,
        "Erreur sur l'accesseur en écriture suivant : nomEmetteur !");
  }


  @Test(priority = 12)
  public void testAccesseurGetDateCreation() {
    final Date date = new Date();
    final Notification notification = new Notification();
    assertNotNull(notification);
    notification.setDateCreation(date);
    assertEquals(notification.getDateCreation(), date,
        "Erreur sur l'accesseur en lecture suivant : dateCreation !");
  }


  @Test(priority = 13)
  public void testAccesseurSetDateCreationDate() {
    Date date = new Date();
    final Notification notification = new Notification();
    assertNotNull(notification);
    notification.setDateCreation(date);
    assertEquals(notification.getDateCreation(), date,
        "Erreur sur l'accesseur en écriture suivant : dateCreation !");
    date = new Date();
    notification.setDateCreation(date);
    assertEquals(notification.getDateCreation(), date,
        "Erreur sur l'accesseur en écriture suivant : dateCreation !");
  }


  @Test(priority = 14)
  public void testMethodeToString() {
    final String string =
        "Notification [id=null, emetteur=null, destinataire=null, message=null, nomEmetteur=null, dateCreation=null]";
    final Notification notification = new Notification();
    assertNotNull(notification);
    assertEquals(notification.toString(), string,
        "La méthode toString n'a pas retourné le bon résultat !");
  }

  @AfterClass
  public void afterClass() {}

}
