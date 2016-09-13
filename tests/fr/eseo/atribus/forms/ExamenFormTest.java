package fr.eseo.atribus.forms;

import static org.testng.Assert.assertEquals;

import fr.eseo.atribus.dao.UtilisateurDao;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExamenFormTest {

  ExamenForm form;
  UtilisateurDao utilisateurDao;

  /**
   * Initialisation.
   */
  @BeforeTest
  public void beforeTest() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.form = new ExamenForm();
    this.utilisateurDao = (UtilisateurDao) bf.getFactory().getBean("utilisateurDao");
  }

  @Test
  public void getErreurs() {
    this.form.setErreur("test", "Message test");
    assertEquals(this.form.getErreurs().get("test"), "Message test");
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Competence */
    this.utilisateurDao = (UtilisateurDao) bf.getFactory().getBean("utilisateurDao");
  }

}
