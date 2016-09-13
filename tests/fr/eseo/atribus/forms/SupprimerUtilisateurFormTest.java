package fr.eseo.atribus.forms;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import fr.eseo.atribus.dao.UtilisateurDao;
import fr.eseo.atribus.entities.Utilisateur;

import org.mockito.Mockito;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class SupprimerUtilisateurFormTest {
  SupprimerUtilisateurForm form;
  UtilisateurDao utilisateurDao;

  /**
   * Initialisation.
   */
  @BeforeTest
  public void beforeTest() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.form = new SupprimerUtilisateurForm();
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

  @Test
  public void getResultat() {
    this.form.setResultat("Test Resultat");
    assertEquals(this.form.getResultat(), "Test Resultat");
  }

  @Test
  public void getUtilisateurBdd() {
    assertEquals(this.form.getUtilisateurBdd(), this.utilisateurDao);
  }

  @Test
  public void setResultat() {
    this.form.setResultat("Test Resultat");
    assertEquals(this.form.getResultat(), "Test Resultat");
  }

  @Test
  public void supprimerUtilisateur() {
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getParameter(UtilisateurForm.NOM_BOUTON_SUPPRIMER)).thenReturn(null);
    Mockito.when(request.getParameter(UtilisateurForm.CHAMP_LOGIN)).thenReturn(null);
    Mockito.when(request.getParameter(UtilisateurForm.CHAMP_NOM)).thenReturn(null);
    Mockito.when(request.getParameter(UtilisateurForm.CHAMP_PASS)).thenReturn(null);
    Mockito.when(request.getParameter(UtilisateurForm.CHAMP_PRENOM)).thenReturn(null);
    Mockito.when(request.getParameter(UtilisateurForm.CHAMP_EMAIL)).thenReturn(null);
    this.form.supprimerUtilisateur(request);

    assertNotEquals(this.utilisateurDao.trouverParLogin("demayale").getLogin(), null);

  }

  @Test
  public void supprimerUtilisateurLogin() {
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getParameter(UtilisateurForm.NOM_BOUTON_SUPPRIMER)).thenReturn(null);
    Mockito.when(request.getParameter(UtilisateurForm.CHAMP_LOGIN)).thenReturn(null);
    Mockito.when(request.getParameter(UtilisateurForm.CHAMP_NOM)).thenReturn(null);
    Mockito.when(request.getParameter(UtilisateurForm.CHAMP_PASS)).thenReturn(null);
    Mockito.when(request.getParameter(UtilisateurForm.CHAMP_PRENOM)).thenReturn(null);
    Mockito.when(request.getParameter(UtilisateurForm.CHAMP_EMAIL)).thenReturn(null);
    this.form.supprimerUtilisateur(request);
    assertNotEquals(this.utilisateurDao.trouverParLogin("demayale").getLogin(), null);
  }

  @Test
  public void supprimerUtilisateurLoginBis() {
    final String login = UUID.randomUUID().toString();
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setLogin(login);
    utilisateur.setPassword(UUID.randomUUID().toString());
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    this.form = new SupprimerUtilisateurForm();
    this.utilisateurDao.ajouter(utilisateur);
    Mockito.when(request.getParameter(UtilisateurForm.NOM_BOUTON_SUPPRIMER))
        .thenReturn(utilisateur.getLogin());
    this.form.supprimerUtilisateur(request);
  }

  @Test
  public void supprimerUtilisateurLoginFail() {
    final String login = UUID.randomUUID().toString();
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setLogin(login);
    utilisateur.setPassword(UUID.randomUUID().toString());
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    this.form = new SupprimerUtilisateurForm();
    Mockito.when(request.getParameter(UtilisateurForm.NOM_BOUTON_SUPPRIMER))
        .thenReturn(utilisateur.getLogin());
    this.form.supprimerUtilisateur(request);
  }
}
