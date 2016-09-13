package fr.eseo.atribus.filters;

import fr.eseo.atribus.entities.DirecteurEtudes;
import fr.eseo.atribus.entities.Eleve;
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

public class FiltreDirecteurEtudesTest {

  @BeforeClass
  public void beforeClass() {}

  @Test(priority = 1)
  public void testConstructeurVide() {
    final FiltreDirecteurEtudes filtredirecteuretudes = new FiltreDirecteurEtudes();
    Assert.assertNotNull(filtredirecteuretudes);
  }

  @Test(priority = 2)
  public void testMethodeDestroy() {
    final FiltreDirecteurEtudes filtredirecteuretudes = new FiltreDirecteurEtudes();
    Assert.assertNotNull(filtredirecteuretudes);
    filtredirecteuretudes.destroy();
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
    FiltreDirecteurEtudes filtreDirecteurEtudes = new FiltreDirecteurEtudes();
    try {
      filtreDirecteurEtudes.doFilter(httpServletRequest, httpServletResponse, filterChain);
    } catch (IOException | ServletException exception) {
      exception.printStackTrace();
    }
    // Test avec un utilisateur du bon type
    MockHttpSession mockHttpSession = new MockHttpSession();
    List<Utilisateur> utilisateur = new ArrayList<>();
    utilisateur.add(new DirecteurEtudes());
    mockHttpSession.setAttribute("sessionUtilisateur", utilisateur);
    Mockito.when(httpServletRequest.getSession()).thenReturn(mockHttpSession);
    filtreDirecteurEtudes = new FiltreDirecteurEtudes();
    try {
      filtreDirecteurEtudes.doFilter(httpServletRequest, httpServletResponse, filterChain);
    } catch (IOException | ServletException exception) {
      exception.printStackTrace();
    }
    // Test avec un utilisateur du mauvais type
    mockHttpSession = new MockHttpSession();
    utilisateur = new ArrayList<>();
    utilisateur.add(new Eleve());
    mockHttpSession.setAttribute("sessionUtilisateur", utilisateur);
    Mockito.when(httpServletRequest.getSession()).thenReturn(mockHttpSession);
    filtreDirecteurEtudes = new FiltreDirecteurEtudes();
    try {
      filtreDirecteurEtudes.doFilter(httpServletRequest, httpServletResponse, filterChain);
    } catch (IOException | ServletException exception) {
      exception.printStackTrace();
    }
  }


  @Test(priority = 4)
  public void testMethodeInitFilterConfig() {
    final FilterConfig config = Mockito.mock(FilterConfig.class);
    final FiltreDirecteurEtudes filtredirecteuretudes = new FiltreDirecteurEtudes();
    Assert.assertNotNull(filtredirecteuretudes);
    try {
      filtredirecteuretudes.init(config);
    } catch (final ServletException sev) {
      sev.printStackTrace();
    }
  }

  @AfterClass
  public void afterClass() {}

}
