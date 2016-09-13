package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.DaoException;
import fr.eseo.atribus.dao.NotificationDao;
import fr.eseo.atribus.entities.Notification;
import fr.eseo.atribus.entities.Utilisateur;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe SupprimerNotificationForm.
 */
public class SupprimerNotificationForm {

  /** La constante LOGGER. */
  protected static final Logger LOGGER =
      Logger.getLogger(SupprimerNotificationForm.class.getName());

  /** La constante EXCEPTION. */
  protected static final String EXCEPTION = "Exception";

  /** La variable resultat. */
  protected String resultat;

  /** La variable erreurs. */
  protected final Map<String, String> erreurs = new HashMap<>();

  /** La variable notification dao. */
  protected final NotificationDao notificationDao;

  /**
   * Constructeur.
   */
  public SupprimerNotificationForm() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Notification */
    this.notificationDao = (NotificationDao) bf.getFactory().getBean("notificationDao");
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
   * Accesseur en lecture sur le paramètre erreurs.
   *
   * @return le paramètre erreurs
   */
  public Map<String, String> getErreurs() {
    return this.erreurs;
  }

  /**
   * Méthode qui supprime les notifications en fonction des cases cochées.
   * 
   * @param coches les cases cochées
   * @param utilisateur utilisateur
   */
  public void supprimerNotification(List<String> coches, Utilisateur utilisateur) {
    for (final String coche : coches) {
      try {
        final Notification notification = this.verifierNotification(utilisateur, coche);
        if (this.erreurs.isEmpty()) {
          this.notificationDao.supprimer(notification);
          utilisateur
              .setNotifications(this.notificationDao.trouverToutesLesNotification(utilisateur));
          this.resultat = "success";
        } else {
          this.resultat = "error";
        }
      } catch (final DaoException ebdd) {
        this.resultat = "errorDao";
        SupprimerNotificationForm.LOGGER.log(Level.INFO, EXCEPTION, ebdd);
      }
    }
  }

  /**
   * Verifier notification.
   *
   * @param utilisateur l'utilisateur
   * @param coche la coche
   * @return Le paramètre notification
   */
  private Notification verifierNotification(Utilisateur utilisateur, String coche) {
    Notification notification2 = new Notification();
    try {
      notification2 = this.validationDestinataire(coche, utilisateur);
    } catch (final FormValidationException fve) {
      SupprimerNotificationForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("badId",
          "Tentative de suppression d'une notification d'un autre utilisateur.");
    }
    return notification2;
  }

  /**
   * Validation destinataire.
   *
   * @param coche la coche
   * @param utilisateur l'utilisateur
   * @return Le paramètre notification
   * @throws FormValidationException de type form validation exception
   */
  protected Notification validationDestinataire(String coche, Utilisateur utilisateur)
      throws FormValidationException {
    Integer id;
    try {
      id = Integer.parseInt(coche);
    } catch (final NumberFormatException nfe) {
      throw new FormValidationException("Impossible de récupérer l'ID.");
    }
    for (final Notification notification : utilisateur.getNotifications()) {
      if (id.equals(notification.getId())) {
        return notification;
      }
    }
    throw new FormValidationException("La notification ne lui était pas destinée.");
  }

  /**
   * Ajoute une erreur.
   *
   * @param champ le champ
   * @param message le message
   */
  /*
   * Ajoute un message correspondant au champ spécifié à la map des erreurs.
   */
  protected void setErreur(String champ, String message) {
    this.erreurs.put(champ, message);
  }

}
