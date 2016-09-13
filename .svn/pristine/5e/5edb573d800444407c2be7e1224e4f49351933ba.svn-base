package fr.eseo.atribus.forms;

import fr.eseo.atribus.beans.Mail;
import fr.eseo.atribus.beans.MessagesParDefaut;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;

/**
 * La classe MailForm.
 */
public class MailForm {

  /** La constante EXCEPTION. */
  protected static final String EXCEPTION = "Exception";

  /** La constante ERROR. */
  protected static final String ERROR = "error";

  /** La constante LOGGER. */
  protected static final Logger LOGGER = Logger.getLogger(MailForm.class.getName());

  /** La variable resultat. */
  protected String resultat;

  /** La variable erreurs. */
  protected final Map<String, String> erreurs = new HashMap<>();

  /** La variable mail. */
  protected Mail mail;

  /** La variable messages. */
  protected MessagesParDefaut messages;

  /**
   * Instancie un nouveau mail form.
   */
  public MailForm() {
    super();
  }

  /**
   * Accesseur en lecture sur le paramètre mail.
   *
   * @return le paramètre mail
   */
  public Mail getMail() {
    return this.mail;
  }

  /**
   * Accesseur en écriture sur le paramètre mail.
   *
   * @param mail le nouveau paramètre mail
   */
  public void setMail(Mail mail) {
    this.mail = mail;
  }

  /**
   * Accesseur en lecture sur le paramètre messages.
   *
   * @return le paramètre messages
   */
  public MessagesParDefaut getMessages() {
    return this.messages;
  }

  /**
   * Accesseur en écriture sur le paramètre messages.
   *
   * @param messages le nouveau paramètre messages
   */
  public void setMessages(MessagesParDefaut messages) {
    this.messages = messages;
  }

  /**
   * Accesseur en lecture sur le paramètre erreurs.
   *
   * @return le paramètre erreurs
   */
  public Map<String, String> getErreurs() {
    return this.erreurs;
  }

  /**
   * Accesseur en écriture sur le paramètre resultat.
   *
   * @param resultat le nouveau paramètre resultat
   */
  protected void setResultat(String resultat) {
    this.resultat = resultat;
  }

  /**
   * Accesseur en lecture sur le paramètre resultat.
   *
   * @return le paramètre resultat
   */
  public String getResultat() {
    return this.resultat;
  }

  /**
   * Envoyer mail.
   *
   * @param objet l'objet
   * @param contenu le contenu
   * @param destinataire le destinataire
   */
  protected void envoyerMail(String objet, String contenu, String destinataire) {
    try {
      if (this.erreurs.isEmpty()) {
        this.mail.mailAutomatique(destinataire, objet, contenu);
        this.resultat = "success";
      } else {
        this.resultat = "error";
      }
    } catch (final MessagingException mesesx) {
      this.resultat = "errorMail";
      MailForm.LOGGER.log(Level.INFO, EXCEPTION, mesesx);
    }
  }

  /**
   * Traiter donnees.
   *
   * @param competence la competence
   * @param commentaire le commentaire
   */
  protected void traiterDonnees(String competence, String commentaire) {
    try {
      this.validationCommentaire(commentaire);
    } catch (final FormValidationException fve) {
      MailForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("commentaire", "Aucun commentaire saisi.");
    }
    try {
      this.validationCompetence(competence);
    } catch (final FormValidationException fve) {
      MailForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("competence", "Aucune compétence saisie.");
    }
  }

  /**
   * Validation competence.
   *
   * @param competence la competence
   * @throws FormValidationException de type form validation exception
   */
  protected void validationCompetence(String competence) throws FormValidationException {
    if (competence == null) {
      throw new FormValidationException("Merci de saisir une competence.");
    }
  }

  /**
   * Validation commentaire.
   *
   * @param commentaire le commentaire
   * @throws FormValidationException de type form validation exception
   */
  protected void validationCommentaire(String commentaire) throws FormValidationException {
    if (commentaire == null) {
      throw new FormValidationException("Merci de saisir un commentaire.");
    }
  }


  /**
   * Ajoute un message correspondant au champ spécifié à la map des erreurs.
   *
   * @param champ le champ
   * @param message le message
   */
  protected void setErreur(String champ, String message) {
    this.erreurs.put(champ, message);
  }

}
