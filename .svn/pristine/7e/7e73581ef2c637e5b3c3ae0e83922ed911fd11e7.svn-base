package fr.eseo.atribus.timers;

import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.dao.UtilisateurDao;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

@Test
@ContextConfiguration({ "classpath:spring-dao.xml", "classpath:spring-daoBeans.xml",
    "classpath:beanRefFactory.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
public class NotificationUpdaterTest {

  NotificationUpdater notificationUpdater;

  @Test
  public void NotificationUpdater() {
    this.notificationUpdater = new NotificationUpdater();
    assertNotNull(this.notificationUpdater);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void executer() {
    final MockServletContext servletContext = new MockServletContext();
    this.notificationUpdater.setServletContext(servletContext);
    final Set set = new HashSet<>();
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Notification */
    final UtilisateurDao utilisateurDao =
        (UtilisateurDao) bf.getFactory().getBean("utilisateurDao");
    set.add(utilisateurDao.trouverParId(1));
    this.notificationUpdater.getServletContext().setAttribute("utilisateurs", set);
    this.notificationUpdater.executer();
    assertNotNull(this.notificationUpdater);
  }
}
