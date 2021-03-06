package fr.eseo.atribus.forms;

import static org.testng.Assert.assertNotNull;

import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class AddUserFormTest {
  AddUserForm form;
  private static final String CHAMP_LOGIN = "login";
  private static final String CHAMP_PASS = "password";
  private static final String CHAMP_NOM = "nom";
  private static final String CHAMP_PRENOM = "prenom";
  private static final String CHAMP_EMAIL = "email";
  private static final String CB_AS = "cbAS"; // CheckBox Admin système
  private static final String CB_DE = "cbDE"; // CheckBox Directeur études
  private static final String CB_DP = "cbDP"; // CheckBox Directeur programmes
  private static final String CB_EN = "cbEN"; // CheckBox Enseignant
  private static final String CB_EL = "cbEL"; // CheckBox élève

  @BeforeClass
  public void beforeClass() {
    this.form = new AddUserForm();
  }

  @Test
  public void ajouterBonUtilisateur() {
    final String login = UUID.randomUUID().toString();
    final String password = "password";
    final String nom = "nomTest";
    final String prenom = "prenomTest";
    final String email = "romain.test@eseo.fr";
    final String cbAdminSyst = "on";
    final String cbDirecteurEtudes = "on";
    final String cbDirecteurProgrammes = "on";
    final String cbEnseignant = "on";
    final String cbEleve = "on";
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_LOGIN)).thenReturn(login);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_PASS)).thenReturn(password);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_NOM)).thenReturn(nom);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_PRENOM)).thenReturn(prenom);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_EMAIL)).thenReturn(email);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_AS)).thenReturn(cbAdminSyst);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_DE))
        .thenReturn(cbDirecteurEtudes);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_DP))
        .thenReturn(cbDirecteurProgrammes);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_EN)).thenReturn(cbEnseignant);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_EL)).thenReturn(cbEleve);
    assertNotNull(this.form.addUser(httpServletRequest));

  }

  @Test
  public void ajouterDéjàPresentUtilisateur() {
    final String login = "hamonrom";
    final String password = "password";
    final String nom = "nomTest";
    final String prenom = "prenomTest";
    final String email = "romain.test@eseo.fr";
    final String cbAdminSyst = "on";
    final String cbDirecteurEtudes = "on";
    final String cbDirecteurProgrammes = "on";
    final String cbEnseignant = "on";
    final String cbEleve = "on";
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_LOGIN)).thenReturn(login);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_PASS)).thenReturn(password);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_NOM)).thenReturn(nom);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_PRENOM)).thenReturn(prenom);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_EMAIL)).thenReturn(email);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_AS)).thenReturn(cbAdminSyst);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_DE))
        .thenReturn(cbDirecteurEtudes);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_DP))
        .thenReturn(cbDirecteurProgrammes);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_EN)).thenReturn(cbEnseignant);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_EL)).thenReturn(cbEleve);
    assertNotNull(this.form.addUser(httpServletRequest));

  }
  
  @Test
  public void ajouterMauvaisUtilisateur() {
    final String login = "testLogin";
    final String password = "rd";
    final String nom = "no";
    final String prenom = "p";
    final String email = "testeseo.fr";
    final String cbAdminSyst = null;
    final String cbDirecteurEtudes = null;
    final String cbDirecteurProgrammes = null;
    final String cbEnseignant = null;
    final String cbEleve = null;
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_LOGIN)).thenReturn(login);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_PASS)).thenReturn(password);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_NOM)).thenReturn(nom);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_PRENOM)).thenReturn(prenom);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CHAMP_EMAIL)).thenReturn(email);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_AS)).thenReturn(cbAdminSyst);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_DE))
        .thenReturn(cbDirecteurEtudes);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_DP))
        .thenReturn(cbDirecteurProgrammes);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_EN)).thenReturn(cbEnseignant);
    Mockito.when(httpServletRequest.getParameter(AddUserFormTest.CB_EL)).thenReturn(cbEleve);
    assertNotNull(this.form.addUser(httpServletRequest));
  }

}
