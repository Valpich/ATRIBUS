package fr.eseo.atribus.filters;

import fr.eseo.atribus.entities.AdminSyst;
import fr.eseo.atribus.entities.EnseignantRefUe;
import fr.eseo.atribus.entities.Utilisateur;

import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FiltreEnseignantRefUETest {

  @BeforeClass
  public void beforeClass() {}

  @Test(priority = 1)
  public void testConstructeurVide() {
    final FiltreEnseignantRefUE filtreenseignantrefue = new FiltreEnseignantRefUE();
    Assert.assertNotNull(filtreenseignantrefue);
  }

  @Test(priority = 2)
  public void testMethodeDestroy() {
    final FiltreEnseignantRefUE filtreenseignantrefue = new FiltreEnseignantRefUE();
    Assert.assertNotNull(filtreenseignantrefue);
    filtreenseignantrefue.destroy();
  }

  @Test(priority = 3)
  public void testMethodeDoFilterServletRequestServletResponseFilterChain() {
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
    final HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
    final FilterChain filterChain = Mockito.mock(FilterChain.class);
    Mockito.when(httpServletRequest.getSession()).thenReturn(new MockHttpSession());
    Mockito.when(httpServletRequest.getContextPath()).thenReturn("test");
    Mockito.when(httpServletRequest.getRemoteAddr()).thenReturn("test/errorAccess.jsp");
    try {
      Mockito.doNothing().when(httpServletResponse).sendRedirect("test/errorAccess.jsp");
    } catch (final IOException e1) {
      e1.printStackTrace();
    }
    // Test pas d'utilisateur
    FiltreEnseignantRefUE filtreEnseignantRefUE = new FiltreEnseignantRefUE();
    try {
      filtreEnseignantRefUE.doFilter(httpServletRequest, httpServletResponse, filterChain);
    } catch (IOException | ServletException exception) {
      exception.printStackTrace();
    }
    // Test avec un utilisateur du bon type
    MockHttpSession mockHttpSession = new MockHttpSession();
    List<Utilisateur> utilisateur = new ArrayList<>();
    utilisateur.add(new EnseignantRefUe());
    mockHttpSession.setAttribute("sessionUtilisateur", utilisateur);
    Mockito.when(httpServletRequest.getSession()).thenReturn(mockHttpSession);
    filtreEnseignantRefUE = new FiltreEnseignantRefUE();
    try {
      filtreEnseignantRefUE.doFilter(httpServletRequest, httpServletResponse, filterChain);
    } catch (IOException | ServletException exception) {
      exception.printStackTrace();
    }
    // Test avec un utilisateur du mauvais type
    mockHttpSession = new MockHttpSession();
    utilisateur = new ArrayList<>();
    utilisateur.add(new AdminSyst());
    mockHttpSession.setAttribute("sessionUtilisateur", utilisateur);
    Mockito.when(httpServletRequest.getSession()).thenReturn(mockHttpSession);
    filtreEnseignantRefUE = new FiltreEnseignantRefUE();
    try {
      filtreEnseignantRefUE.doFilter(httpServletRequest, httpServletResponse, filterChain);
    } catch (IOException | ServletException exception) {
      exception.printStackTrace();
    }
  }


  @Test(priority = 4)
  public void testMethodeInitFilterConfig() {
    final FilterConfig config = Mockito.mock(FilterConfig.class);
    final FiltreEnseignantRefUE filtreenseignantrefue = new FiltreEnseignantRefUE();
    Assert.assertNotNull(filtreenseignantrefue);
    try {
      filtreenseignantrefue.init(config);
    } catch (final ServletException sev) {
      sev.printStackTrace();
    }
  }

  @AfterClass
  public void afterClass() {}

}
