package fr.eseo.atribus.timers;

import fr.eseo.atribus.dao.NotificationDao;
import fr.eseo.atribus.entities.Utilisateur;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

/**
 * La classe NotificationUpdater.
 */
@Service
public class NotificationUpdater {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(NotificationUpdater.class.getName());

  /** La variable servlet context. */
  @Autowired(required = true)
  ServletContext servletContext;

  /** La variable notification dao. */
  NotificationDao notificationDao;

  /**
   * Instancie un nouveau notification updater.
   */
  public NotificationUpdater() {
    NotificationUpdater.LOGGER.info("Instantiation du rafraichisseur de notifications");
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Notification */
    this.notificationDao = (NotificationDao) bf.getFactory().getBean("notificationDao");
  }

  /**
   * Accesseur en lecture sur le paramètre servlet context.
   *
   * @return le paramètre servlet context
   */
  public ServletContext getServletContext() {
    return this.servletContext;
  }

  /**
   * Accesseur en écriture sur le paramètre servlet context.
   *
   * @param servletContext le nouveau paramètre servlet context
   */
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  /**
   * Mise à jour automatique des notifications toutes les secondes.
   */
  @SuppressWarnings("unchecked")
  @Scheduled(fixedDelay = 1000)
  public void executer() {
    for (final Utilisateur utilisateur : (Set<Utilisateur>) this.servletContext
        .getAttribute("utilisateurs")) {
      utilisateur.setNotifications(this.notificationDao.trouverToutesLesNotification(utilisateur));
    }
  }
}
