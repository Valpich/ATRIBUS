package fr.eseo.atribus.forms;

import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.dao.EnseignantRefMatiereDaoImpl;
import fr.eseo.atribus.entities.Matiere;
import fr.eseo.atribus.entities.Utilisateur;

import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class AddMatiereFormTest {

  private static final String CHAMP_NOM = "nom";
  private static final String CHAMP_COEFFICIENT = "coefficient_matiere";
  private static final String CHAMP_SEMESTRE = "listeSemestre";
  private static final String CHAMP_UE = "listeUe";

  AddMatiereForm form;
  Matiere matiere;
  EnseignantRefMatiereDaoImpl ermDao;

  @BeforeTest
  public void beforeTest() {
    this.form = new AddMatiereForm();
    this.matiere = new Matiere();
    this.ermDao = new EnseignantRefMatiereDaoImpl();
  }

  @Test(priority = 1)
  public void ajouterBonneMatiere() {

    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    final String nomMatiere = UUID.randomUUID().toString();
    final String coeffMatiere = "1.75";
    final String numeroSemestre = "3";
    final String nomUe = "Automatique";
    final MockHttpSession mockHtppSession = new MockHttpSession();
    final List<Utilisateur> utilisateurs = new ArrayList<>();
    utilisateurs.add(this.ermDao.recupererListe().get(0));
    mockHtppSession.putValue("sessionUtilisateur", utilisateurs);
    Mockito.when(request.getSession()).thenReturn(mockHtppSession);
    Mockito.when(request.getParameter(AddMatiereFormTest.CHAMP_NOM)).thenReturn(nomMatiere);
    Mockito.when(request.getParameter(AddMatiereFormTest.CHAMP_COEFFICIENT))
        .thenReturn(coeffMatiere);
    Mockito.when(request.getParameter(AddMatiereFormTest.CHAMP_SEMESTRE))
        .thenReturn(numeroSemestre);
    Mockito.when(request.getParameter(AddMatiereFormTest.CHAMP_UE)).thenReturn(nomUe);

    assertNotNull(this.form.getListEnseignantRef());
    assertNotNull(this.form.getListSemestre());
    assertNotNull(this.form.getListUe());
    assertNotNull(this.form.getErreurs());

  }

  @Test(priority = 2)
  public void ajouterMauvaiseMatiere() {

    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    final MockHttpSession mockHtppSession = new MockHttpSession();
    final List<Utilisateur> utilisateurs = new ArrayList<>();
    utilisateurs.add(this.ermDao.recupererListe().get(0));
    mockHtppSession.putValue("sessionUtilisateur", utilisateurs);
    Mockito.when(request.getSession()).thenReturn(mockHtppSession);

    assertNotNull(this.form.addMatiere(request));

  }

}
