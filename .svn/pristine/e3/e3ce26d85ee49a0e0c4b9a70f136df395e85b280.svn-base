package fr.eseo.atribus.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The Class Utilisateur.
 */
public class Utilisateur implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = -7064005782577919384L;

  /** La variable id. */
  private int id;

  /** La variable login. */
  private String login;

  /** La variable password. */
  private String password;

  /** La variable nom. */
  private String nom;

  /** La variable prenom. */
  private String prenom;

  /** La variable email. */
  private String email;

  /** La variable date inscription. */
  private Date dateInscription;

  /** La variable notification active. */
  private boolean notificationActive;

  /** La variable notification mail. */
  private boolean notificationMail;

  /** La variable frequence notification mail. */
  private int frequenceNotificationMail;

  /** La variable notifications. */
  private List<Notification> notifications;


  /**
   * Instancie un nouveau utilisateur.
   */
  public Utilisateur() {
    super();
  }

  /**
   * Instancie un nouveau utilisateur.
   *
   * @param login le login
   * @param password le password
   * @param nom le nom
   * @param prenom le prenom
   * @param email le email
   * @param dateInscription le date inscription
   */
  public Utilisateur(String login, String password, String nom, String prenom, String email,
      Date dateInscription) {
    super();
    this.login = login;
    this.password = password;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.dateInscription = dateInscription;
  }

  /**
   * Instancie un nouveau utilisateur.
   *
   * @param utilisateur le utilisateur
   */
  public Utilisateur(Utilisateur utilisateur) {
    super();
    this.id = utilisateur.id;
    this.login = utilisateur.login;
    this.password = utilisateur.password;
    this.nom = utilisateur.nom;
    this.prenom = utilisateur.prenom;
    this.email = utilisateur.email;
    this.dateInscription = utilisateur.dateInscription;
  }

  /**
   * Accesseur en lecture sur le paramètre id.
   *
   * @return le paramètre id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Accesseur en écriture sur le paramètre id.
   *
   * @param id le nouveau paramètre id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Accesseur en lecture sur le paramètre login.
   *
   * @return le paramètre login
   */
  public String getLogin() {
    return this.login;
  }

  /**
   * Accesseur en écriture sur le paramètre login.
   *
   * @param login le nouveau paramètre login
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * Accesseur en lecture sur le paramètre password.
   *
   * @return le paramètre password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Accesseur en écriture sur le paramètre password.
   *
   * @param password le nouveau paramètre password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Accesseur en lecture sur le paramètre nom.
   *
   * @return le paramètre nom
   */
  public String getNom() {
    return this.nom;
  }

  /**
   * Accesseur en écriture sur le paramètre nom.
   *
   * @param nom le nouveau paramètre nom
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Accesseur en lecture sur le paramètre prenom.
   *
   * @return le paramètre prenom
   */
  public String getPrenom() {
    return this.prenom;
  }

  /**
   * Accesseur en écriture sur le paramètre prenom.
   *
   * @param prenom le nouveau paramètre prenom
   */
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  /**
   * Accesseur en lecture sur le paramètre email.
   *
   * @return le paramètre email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Accesseur en écriture sur le paramètre email.
   *
   * @param email le nouveau paramètre email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Accesseur en lecture sur le paramètre date inscription.
   *
   * @return le paramètre date inscription
   */
  public Date getDateInscription() {
    return this.dateInscription;
  }

  /**
   * Accesseur en écriture sur le paramètre date inscription.
   *
   * @param dateInscription le nouveau paramètre date inscription
   */
  public void setDateInscription(Date dateInscription) {
    this.dateInscription = dateInscription;
  }

  /**
   * Verifie que la propriété notification active est vraie.
   *
   * @return true, pour notification active vrai
   */
  public boolean isNotificationActive() {
    return this.notificationActive;
  }

  /**
   * Accesseur en écriture sur le paramètre notification active.
   *
   * @param notificationActive le nouveau paramètre notification active
   */
  public void setNotificationActive(boolean notificationActive) {
    this.notificationActive = notificationActive;
  }

  /**
   * Verifie que la propriété notification mail est vraie.
   *
   * @return true, pour notification mail vrai
   */
  public boolean isNotificationMail() {
    return this.notificationMail;
  }

  /**
   * Accesseur en écriture sur le paramètre notification mail.
   *
   * @param notificationMail le nouveau paramètre notification mail
   */
  public void setNotificationMail(boolean notificationMail) {
    this.notificationMail = notificationMail;
  }

  /**
   * Accesseur en lecture sur le paramètre frequence notification mail.
   *
   * @return le paramètre frequence notification mail
   */
  public int getFrequenceNotificationMail() {
    return this.frequenceNotificationMail;
  }

  /**
   * Accesseur en écriture sur le paramètre frequence notification mail.
   *
   * @param frequenceNotificationMail le nouveau paramètre frequence notification mail
   */
  public void setFrequenceNotificationMail(int frequenceNotificationMail) {
    this.frequenceNotificationMail = frequenceNotificationMail;
  }

  /**
   * Accesseur en lecture sur le paramètre notifications.
   *
   * @return le paramètre notifications
   */
  public List<Notification> getNotifications() {
    return this.notifications;
  }

  /**
   * Accesseur en écriture sur le paramètre notifications.
   *
   * @param notifications le nouveau paramètre notifications
   */
  public void setNotifications(List<Notification> notifications) {
    this.notifications = notifications;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Utilisateur [id=" + this.id + ", login=" + this.login + ", password=" + this.password
        + ", nom=" + this.nom + ", prenom=" + this.prenom + ", email=" + this.email
        + ", dateInscription=" + this.dateInscription + ", notificationActive="
        + this.notificationActive + ", notificationMail=" + this.notificationMail
        + ", frequenceNotificationMail=" + this.frequenceNotificationMail + ", notifications="
        + this.notifications + "]";
  }

  /**
   * Fonction permettant de convertir le bean en JSON.
   *
   * @return Le paramètre string
   */
  public String toJson() {
    return "{ \"nom\" : \"" + this.nom + "\", \"prenom\" : \"" + this.prenom
        + "\", \"separator\" : \"-\", \"login\" : \"" + this.login + "\", \"email\" : \""
        + this.email + "\", \"children\" : []}";
  }

}
