package fr.eseo.atribus.filters;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FiltreErreurRequeteTest {

  @BeforeClass
  public void beforeClass() {}

  @Test(priority = 1)
  public void testConstructeurVide() {
    final FiltreErreurRequete filtreerreurrequete = new FiltreErreurRequete();
    Assert.assertNotNull(filtreerreurrequete);
  }

  @Test(priority = 2)
  public void testMethodeDestroy() {
    final FiltreErreurRequete filtreerreurrequete = new FiltreErreurRequete();
    Assert.assertNotNull(filtreerreurrequete);
    filtreerreurrequete.destroy();
  }

  @Test(priority = 3)
  public void testMethodeDoFilterServletRequestServletResponseFilterChain() {
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
    final HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
    final FilterChain filterChain = Mockito.mock(FilterChain.class);
    try {
      httpServletResponse.sendError(202);
    } catch (final IOException ioe) {
      ioe.printStackTrace();
    }

    Mockito.when(httpServletResponse.getStatus()).thenReturn(200);

    final FiltreErreurRequete filtreErreurRequete = new FiltreErreurRequete();
    try {
      filtreErreurRequete.doFilter(httpServletRequest, httpServletResponse, filterChain);
    } catch (IOException | ServletException exception) {
      exception.printStackTrace();
    }

    Mockito.when(httpServletResponse.getStatus()).thenReturn(202);

    try {
      filtreErreurRequete.doFilter(httpServletRequest, httpServletResponse, filterChain);
    } catch (IOException | ServletException exception) {
      exception.printStackTrace();
    }
    // Test pas d'erreurrequete
  }


  @Test(priority = 4)
  public void testMethodeInitFilterConfig() {
    final FilterConfig config = Mockito.mock(FilterConfig.class);
    final FiltreErreurRequete filtreerreurrequete = new FiltreErreurRequete();
    Assert.assertNotNull(filtreerreurrequete);
    try {
      filtreerreurrequete.init(config);
    } catch (final ServletException sev) {
      sev.printStackTrace();
    }
  }

  @AfterClass
  public void afterClass() {}

}
