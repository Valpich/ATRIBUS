package fr.eseo.atribus.forms;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.beans.Mail;
import fr.eseo.atribus.beans.MessagesParDefaut;
import fr.eseo.atribus.dao.UtilisateurDao;
import fr.eseo.atribus.entities.Utilisateur;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MailFormTest {

  MailForm form;
  UtilisateurDao utilisateurDao;

  /**
   * Initialisation.
   */
  @BeforeTest
  public void beforeTest() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.form = new MailForm();
    this.utilisateurDao = (UtilisateurDao) bf.getFactory().getBean("utilisateurDao");
  }

  @Test
  public void getResultat() {
    this.form.setResultat("Test Resultat");
    assertEquals(this.form.getResultat(), "Test Resultat");
  }

  @Test
  public void getErreurs() {
    this.form.setErreur("test", "Message test");
    assertEquals(this.form.getErreurs().get("test"), "Message test");
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Competence */
    this.utilisateurDao = (UtilisateurDao) bf.getFactory().getBean("utilisateurDao");
    form.envoyerMail("test", "test@eseo.fr", "test@eseo.fr");
    this.form.setMail(new Mail("a", "a", "a", "a"));
    this.form.setMessages(new MessagesParDefaut());
    assertNotNull(form.getMail());
    assertNotNull(form.getMessages());
    TestProtectedMethods test = new TestProtectedMethods();
    form.traiterDonnees(null, null);
    form.envoyerMail("", "", "");
    TestProtectedMethodesDeux formDeux = new TestProtectedMethodesDeux();
    formDeux.genererContenuMail("test");
    formDeux.genererContenuMail(new Utilisateur(),"test");

  }

  private class TestProtectedMethods extends MailForm {
    TestProtectedMethods() {
      super();
    }
  }

  private class TestProtectedMethodesDeux extends ContacterAdministrateursForm{
    TestProtectedMethodesDeux(){
      super();
    }
  }
}

