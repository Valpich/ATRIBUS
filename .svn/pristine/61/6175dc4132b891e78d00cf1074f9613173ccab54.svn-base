package fr.eseo.atribus.dao;

import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.entities.Matiere;
import fr.eseo.atribus.entities.UniteEnseignement;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UeDaoImplTest {

  private MatiereDaoImpl matiereDao;
  private UeDaoImpl ueDao;

  @BeforeTest
  public void beforeTest() {
    this.matiereDao = new MatiereDaoImpl();
    this.ueDao = new UeDaoImpl();
  }

  @Test
  public void supprimerAssociationMatiereUe() {
    Matiere matiere = this.matiereDao.trouverParId(1);
    UniteEnseignement ue = this.ueDao.listerUe().get(0);
    this.ueDao.supprimerAssociationMatiereUe(ue.getNom(), matiere.getNom());
  }

}
