package fr.eseo.atribus.filters;

import fr.eseo.atribus.beans.Mail;
import fr.eseo.atribus.beans.MessagesParDefaut;
import fr.eseo.atribus.dao.AdminSystDao;
import fr.eseo.atribus.entities.AdminSyst;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Filtre contrôlant le nombre maximal de requête en simultané pour l'application.
 */
public class FiltreLimiteurRequeteApplication extends FiltreLimiteurInit implements Filter {

  /** La constante LIMITE. */
  private static final int LIMITE = 100;

  /** La variable actuel. */
  private int actuel;

  /** La variable verrou. */
  private final Object verrou = new Object();

  /** La variable spring context. */
  private ApplicationContext springContext;

  /** La constante LOGGER. */
  private static final Logger LOGGER =
      Logger.getLogger(FiltreLimiteurRequeteApplication.class.getName());

  /** La variable mail. */
  private Mail mail;

  /** La variable messages. */
  private MessagesParDefaut messages;

  /*
   * (non-Javadoc)
   * 
   * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
   * javax.servlet.FilterChain)
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    final HttpServletResponse reponse = (HttpServletResponse) response;

    try {
      boolean ok;
      // Lors qu'on commence une requête, on augmente le nombre de requête en cours pour
      // les utilisateurs
      synchronized (this.verrou) {
        ok = this.actuel++ < LIMITE;
      }
      if (ok) {
        chain.doFilter(request, response);
      } else {

        // Ecriture des logs
        this.ecrireLog();
        reponse.sendError(429, "Trop de requête sur notre serveur !");
      }
    } finally {
      // Lors qu'on rediminue les requêtes, on peut redonner l'accès
      synchronized (this.verrou) {
        this.actuel--;
      }
    }
  }

  /**
   * Ecrire log.
   */
  private void ecrireLog() {
    FiltreLimiteurRequeteApplication.LOGGER.setLevel(Level.SEVERE);
    FiltreLimiteurRequeteApplication.LOGGER.severe("L'application est probablement attaquée, "
        + "le nombre de requête en cours de traitement à dépassé la charge autorisée!");
    try (ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext("classpath:dispatcher-servlet.xml")) {
      this.springContext = context;
      this.mail = (Mail) this.springContext.getBean("mail");
      this.messages = (MessagesParDefaut) this.springContext.getBean("messagesParDefaut");
    } catch (final BeanCreationException bce) {
      FiltreLimiteurRequeteApplication.LOGGER.log(Level.SEVERE, "Exception", bce);
    }
    new Thread(() -> this.prevenirLesAdministrateursSurchargeApplication()).start();
  }

  /**
   * Prevenir les administrateurs surcharge application.
   */
  private void prevenirLesAdministrateursSurchargeApplication() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    final List<AdminSyst> adminSyst =
        ((AdminSystDao) bf.getFactory().getBean("adminSystDao")).recupererListe();
    for (final AdminSyst admin : adminSyst) {
      try {
        this.mail.mailAutomatiqueSsl(admin.getEmail(), "Avertissement !",
            this.messages.getAdminSystProblemeDeux());
      } catch (final MessagingException me) {
        LOGGER.log(Level.INFO, "Exception", me);
      }
    }
  }

}
