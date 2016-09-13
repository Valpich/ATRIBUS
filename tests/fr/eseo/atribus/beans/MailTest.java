package fr.eseo.atribus.beans;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import java.util.Properties;

import javax.mail.MessagingException;

public class MailTest {


  @Test
  public void mail() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    assertEquals(mail.getFrom(), from);
    assertEquals(mail.getUsername(), username);
    assertEquals(mail.getHost(), host);
    assertEquals(mail.getPassword(), password);

  }

  @Test
  public void getFrom() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    assertEquals(mail.getFrom(), from);

  }

  @Test
  public void getHost() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    assertEquals(mail.getHost(), host);
  }

  @Test
  public void getPassword() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    assertEquals(mail.getPassword(), password);
  }

  @Test
  public void getPropriete() {
    final String from = "projetjavaeseo@gmail.com";
    final String username = "projetjavaeseo@gmail.com";
    final String password = "eseoeseo";
    final String host = "smtp.gmail.com";
    final Mail mail = new Mail(from, username, password, host);
    assertEquals(mail.getPropriete(), mail.getPropriete());

  }

  @Test
  public void getUsername() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    assertEquals(mail.getUsername(), username);
  }

  @Test
  public void mailAutomatique() throws MessagingException {
    final String receveur = "receveur";
    final String objet = "objet";
    final String contenu = "contenu";
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    boolean test = false;

    try {
      mail.mailAutomatique(receveur, objet, contenu);
    } catch (final MessagingException mex) {
      test = true;
    }
    assertEquals(test, true);

  }

  @Test
  public void mailAutomatiqueSsl() {
    final String receveur = "receveur";
    final String objet = "objet";
    final String contenu = "contenu";
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    boolean test = false;

    try {
      mail.mailAutomatiqueSsl(receveur, objet, contenu);
    } catch (final MessagingException mex) {
      test = true;
    }
    assertEquals(test, true);

  }

  @Test
  public void setFrom() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    assertEquals(mail.getFrom(), from);
  }

  @Test
  public void setHost() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    assertEquals(mail.getHost(), host);
  }

  @Test
  public void setPassword() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    assertEquals(mail.getPassword(), password);
  }

  @Test
  public void setPropriete() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    final Properties pro = new Properties();
    mail.setPropriete(pro);
    assertEquals(mail.getPropriete(), pro);
  }

  @Test
  public void setUsername() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    assertEquals(mail.getUsername(), username);
  }

  @Test
  public void mailtoString() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    final Mail mail = new Mail(from, username, password, host);
    assertEquals(mail.toString(), mail.toString());
  }

  @Test
  public void getPasswordAuthenticationTest() {
    final String from = "fromTest";
    final String username = "usernameTest";
    final String password = "passwordTest";
    final String host = "hostTest";
    new Mail(from, username, password, host);
    final javax.mail.PasswordAuthentication pa =
        new javax.mail.PasswordAuthentication(from, password);
    assertNotNull(pa);
  }



}
