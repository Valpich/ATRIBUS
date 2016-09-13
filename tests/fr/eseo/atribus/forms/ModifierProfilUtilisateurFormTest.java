package fr.eseo.atribus.forms;

import javax.servlet.http.HttpServletRequest;

import org.mockito.Mockito;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import fr.eseo.atribus.dao.UtilisateurDao;
import fr.eseo.atribus.entities.Utilisateur;

public class ModifierProfilUtilisateurFormTest {

  ModifierProfilUtilisateurForm form;
  UtilisateurDao utilisateurDao;
  Utilisateur utilisateur; 
  
  @BeforeTest
  public void beforeTest() {
    form = new ModifierProfilUtilisateurForm();
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.utilisateurDao = (UtilisateurDao) bf.getFactory().getBean("utilisateurDao");
  }

  @Test
  public void ModifierProfilUtilisateurForm() {
  
    throw new RuntimeException("Test not implemented");
    //form.modifierProfilUtilisateur(options,notification, mail,utilisateur);
  
  }

  @Test
  public void getErreurs() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getResultat() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void modifierProfilUtilisateur() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void traiterDonnees() {
    throw new RuntimeException("Test not implemented");
  }
}
