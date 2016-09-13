package fr.eseo.atribus.forms;

import static org.testng.Assert.assertNotNull;

import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class AddCompetenceFormTest {

  AddCompetenceForm form;

  private static final String CHAMP_NOM = "nomCompetence";
  private static final String CHAMP_PARENT_ID = "parentId";
  private static final String CHAMP_PROFONDEUR = "profondeur";

  @BeforeTest
  public void beforeTest() {
    this.form = new AddCompetenceForm();
  }

  @Test
  public void ajouterBonneCompetence() {

    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    final String nom = UUID.randomUUID().toString();
    final String parentId = "1";
    final String profondeur = "1";

    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_NOM)).thenReturn(nom);
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PARENT_ID)).thenReturn(parentId);
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PROFONDEUR))
        .thenReturn(profondeur);
    assertNotNull(this.form.ajouterCompetence(request));
    assertNotNull(this.form.getErreurs());
    assertNotNull(this.form.getResultat());

  }

  @Test
  public void ajouterFausseCompetence() {

    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    final String nom = "Mathématiques";
    final String parentId = "-5";
    final String profondeur = "12";

    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_NOM)).thenReturn(nom);
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PARENT_ID)).thenReturn(parentId);
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PROFONDEUR))
        .thenReturn(profondeur);

    assertNotNull(this.form.ajouterCompetence(request));

    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_NOM)).thenReturn("o");
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PARENT_ID)).thenReturn("a");
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PROFONDEUR))
        .thenReturn("b");

    assertNotNull(this.form.ajouterCompetence(request));
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_NOM)).thenReturn(null);
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PARENT_ID)).thenReturn("a");
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PROFONDEUR))
        .thenReturn("b");

    assertNotNull(this.form.ajouterCompetence(request));

  }


  @Test
  public void modifierCompetenceReussi() {

    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    final String nom = UUID.randomUUID().toString();
    final String parentId = "2";
    final String profondeur = "3";
    form = new AddCompetenceForm();
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_NOM)).thenReturn(nom);
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PARENT_ID)).thenReturn(parentId);
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PROFONDEUR))
        .thenReturn(profondeur);
    Mockito.when(request.getParameter("nomAncienneCompetence")).thenReturn("Mathématiques");

    assertNotNull(this.form.modifierCompetence(request));
    
    form = new AddCompetenceForm();
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_NOM)).thenReturn("Mathématiques");
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PARENT_ID)).thenReturn(parentId);
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PROFONDEUR))
        .thenReturn(profondeur);
    Mockito.when(request.getParameter("nomAncienneCompetence")).thenReturn(nom);

    assertNotNull(this.form.modifierCompetence(request));
  }
  @Test
  public void modifierCompetenceFail() {
    form = new AddCompetenceForm();
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    final String nom ="Mathématiques";
    final String parentId = "2";
    final String profondeur = "3";
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_NOM)).thenReturn(nom);
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PARENT_ID)).thenReturn(parentId);
    Mockito.when(request.getParameter(AddCompetenceFormTest.CHAMP_PROFONDEUR))
        .thenReturn(profondeur);
    Mockito.when(request.getParameter("nomAncienneCompetence")).thenReturn(nom);

    assertNotNull(this.form.modifierCompetence(request));
  }
  

}
