package fr.eseo.atribus.listeners;

import fr.eseo.atribus.entities.Utilisateur;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSessionEvent;

/**
 * La classe SessionListener.
 */
@Component
public class SessionListener
    implements javax.servlet.http.HttpSessionListener, ApplicationContextAware {

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.
   * context.ApplicationContext)
   */
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    if (applicationContext instanceof WebApplicationContext) {
      ((WebApplicationContext) applicationContext).getServletContext().addListener(this);
    } else {
      throw new ErreurListenerException("Impossible d'utiliser le listener");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
   */
  @Override
  public void sessionCreated(HttpSessionEvent event) {
    // Vide
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
   */
  @SuppressWarnings("unchecked")
  @Override
  public void sessionDestroyed(HttpSessionEvent event) {
    final Set<Utilisateur> utilisateurs =
        (Set<Utilisateur>) event.getSession().getServletContext().getAttribute("utilisateurs");
    if (((List<Utilisateur>) event.getSession().getAttribute("sessionUtilisateur")) != null) {
      utilisateurs.remove(
          ((List<Utilisateur>) event.getSession().getAttribute("sessionUtilisateur")).get(0));
    }
  }

}
