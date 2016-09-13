package fr.eseo.atribus.forms;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("deprecation")
public class ConnexionFormTest {
  ConnexionForm form;

  @BeforeClass
  public void beforeClass() {
    this.form = new ConnexionForm();
  }

  @Test
  public void testerBonneConnexion() {
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(httpServletRequest.getParameter("password")).thenReturn("password");
    Mockito.when(httpServletRequest.getParameter("login")).thenReturn("admin");
    final MockHttpSession mockHtppSession = new MockHttpSession();
    Mockito.when(httpServletRequest.getSession()).thenReturn(mockHtppSession);
    Mockito.when(httpServletRequest.getSession()).thenReturn(mockHtppSession);
    this.form.connecterUtilisateur(httpServletRequest);
    assertNotNull(mockHtppSession.getAttribute("sessionUtilisateur"));
  }

  @Test
  public void testerMauvaiseConnexion() {
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(httpServletRequest.getParameter("password")).thenReturn("passwordFaux");
    Mockito.when(httpServletRequest.getParameter("login")).thenReturn("admin");
    final MockHttpSession mockHtppSession = new MockHttpSession();
    Mockito.when(httpServletRequest.getSession()).thenReturn(mockHtppSession);
    Mockito.when(httpServletRequest.getSession()).thenReturn(mockHtppSession);
    this.form.connecterUtilisateur(httpServletRequest);
    assertNull(mockHtppSession.getAttribute("sessionUtilisateur"));
  }

}
