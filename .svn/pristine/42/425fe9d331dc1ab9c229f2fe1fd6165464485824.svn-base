package fr.eseo.atribus.dao;

import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.entities.Matiere;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MatiereDaoImplTest {

  private MatiereDaoImpl matiereDao;
  private UeDaoImpl ueDao;
  private EnseignantRefMatiereDaoImpl ermDao;

  @BeforeTest
  public void beforeTest() {
    this.matiereDao = new MatiereDaoImpl();
    this.ueDao = new UeDaoImpl();
    this.ermDao = new EnseignantRefMatiereDaoImpl();
  }

  @Test
  public void testAjoutModDelete() {
    Matiere matiere = new Matiere();
    matiere.setCoefficient(1);
    matiere.setNom("test");
    matiere.setUe(this.ueDao.listerUe().get(0));
    matiere.setErm(this.ermDao.recupererListe().get(0));
    matiereDao.ajouter(matiere, 1);
    assertNotNull(matiere.getId());
    matiere = this.matiereDao.trouverParId(matiere.getId());
    matiere.setErm(this.ermDao.recupererListe().get(0));
    this.matiereDao.modifierMatiereParId(matiere);
    this.matiereDao.supprimerMatiereParId(matiere);
  }

  @Test
  public void trouverMatiereParUe() {
    assertNotNull(this.matiereDao.trouverMatiereParUe());
  }
}
