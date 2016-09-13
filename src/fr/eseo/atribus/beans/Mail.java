package fr.eseo.atribus.beans;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Classe permettant d'envoyer des emails.
 */
public class Mail implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = 5531174433277180725L;

  /** La variable from. */
  private String from;

  /** La variable username. */
  private String username;

  /** La variable password. */
  private String password;

  /** La variable host. */
  private String host;

  /** La variable propriete. */
  private Properties propriete;

  /**
   * Instancie la classe Mail par defaut.
   *
   * @param from le from
   * @param username le nom d'utilisateur
   * @param password le mot de passe
   * @param host le host
   */
  public Mail(String from, String username, String password, String host) {
    super();
    this.setFrom(from);
    this.setPassword(password);
    this.setUsername(username);
    this.setHost(host);
    this.setPropriete(new Properties());
    this.getPropriete().put("mail.smtp.starttls.enable", false);
    this.getPropriete().put("mail.smtp.host", host);
    this.getPropriete().put("mail.smtp.port", "25");
    this.getPropriete().put("mail.smtp.auth", false);
    this.getPropriete().put("mail.smtp.connectiontimeout", 3000);
  }

  /**
   * Accesseur en lecture de l'adresse de connexion.
   * 
   * @return L'adresse.
   */
  public String getFrom() {
    return this.from;
  }

  /**
   * Accesseur en lecture du nom d'utilisateur.
   * 
   * @return Le nom d'utilisateur.
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Accesseur en lecture du mot de passe.
   * 
   * @return Le mot de passe.
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Accesseur en ecriture de l'adresse de connexion.
   * 
   * @param value La nouvelle adresse.
   */
  public void setFrom(final String value) {
    this.from = value;
  }

  /**
   * Accesseur en ecriture du nom d'utilisateur.
   * 
   * @param value Le nouveau nom d'utilisateur.
   */
  public void setUsername(final String value) {
    this.username = value;
  }

  /**
   * Accesseur en ecriture du mot de passe.
   * 
   * @param value Le nouveau mot de passe.
   */
  public void setPassword(final String value) {
    this.password = value;
  }


  /**
   * Accesseur en lecture de l'hote.
   * 
   * @return L'hote.
   */
  public String getHost() {
    return this.host;
  }

  /**
   * Accesseur en ecriture de l'hote.
   * 
   * @param host Le nouvel hote.
   */
  public void setHost(String host) {
    this.host = host;
  }

  /**
   * Accesseur en lecture de la propriete de connexion.
   * 
   * @return La propriete de connexion.
   */
  public Properties getPropriete() {
    return this.propriete;
  }

  /**
   * Accesseur en ecriture de la propriete de connexion.
   * 
   * @param propriete La nouvelle propriete de connexion.
   */
  public void setPropriete(final Properties propriete) {
    this.propriete = propriete;
  }

  /**
   * Mail automatique.
   *
   * @param receveur le destinataire
   * @param objet l'objet
   * @param contenu le contenu
   * @throws MessagingException de type messagingException
   */
  public void mailAutomatique(String receveur, String objet, String contenu)
      throws MessagingException {
    final Session session =
        Session.getInstance(this.getPropriete(), new javax.mail.Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(Mail.this.getUsername(), Mail.this.getPassword());
          }
        });
    final Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(this.getFrom()));
    message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receveur));
    message.setSubject(objet);
    message.setContent(contenu, "text/html; charset=utf-8");
    Transport.send(message);
  }

  /**
   * Mail automatique ssl.
   *
   * @param receveur le destinataire
   * @param objet l'objet
   * @param contenu le contenu
   * @throws MessagingException de type messagingException
   */
  public void mailAutomatiqueSsl(String receveur, String objet, String contenu)
      throws MessagingException {
    final Properties props = new Properties();
    props.put("mail.smtp.user", this.from);
    props.put("mail.smtp.host", this.host);
    props.put("mail.smtp.port", "465");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback", "false");
    final SmtpAuthenticator auth = new SmtpAuthenticator();
    final Session session = Session.getInstance(props, auth);
    final MimeMessage msg = new MimeMessage(session);
    msg.setSubject(objet);
    msg.setContent(contenu, "text/html; charset=utf-8");
    msg.setFrom(new InternetAddress(this.from));
    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receveur));
    final Transport transport = session.getTransport("smtps");
    transport.connect(this.host, Integer.valueOf("465"), this.from, this.password);
    transport.sendMessage(msg, msg.getAllRecipients());
    transport.close();
  }

  /**
   * La Class SmtpAuthenticator.
   */
  private class SmtpAuthenticator extends javax.mail.Authenticator {

    /*
     * (non-Javadoc)
     * 
     * @see javax.mail.Authenticator#getPasswordAuthentication()
     */
    @Override
    public PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(Mail.this.from, Mail.this.password);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Mail [from=" + this.from + ", username=" + this.username + ", password=" + this.password
        + ", host=" + this.host + ", propriete=" + this.propriete + "]";
  }

}
