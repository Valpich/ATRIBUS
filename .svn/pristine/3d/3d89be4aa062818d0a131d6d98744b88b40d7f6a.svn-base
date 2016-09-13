package fr.eseo.atribus.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.entities.Utilisateur;

import org.powermock.api.mockito.PowerMockito;
import org.testng.annotations.Test;

public class EnseignantDaoImplTest {

  EnseignantDaoImpl enseignantDao = new EnseignantDaoImpl();


  @Test(priority = 1)
  public void trouverTous() {
    assertNotNull(this.enseignantDao.trouverTousLesEnseignants());
  }

  @Test(priority = 1)
  public void trouverIdUtilisateurParIdEnseignant() {
    assertNotNull(this.enseignantDao.trouverIdUtilisateurParIdEnseignant(1));
  }

  @Test(priority = 3)
  public void trouverParNom() {
    assertNotNull(this.enseignantDao
        .trouverParNom(this.enseignantDao.trouverParIdUtilisateur(3).getNom()));
  }
}
