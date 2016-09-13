package fr.eseo.atribus.dao;

import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.entities.Matiere;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PromotionDaoImplTest {

  private PromotionDaoImpl promotionDao;


  @BeforeTest
  public void beforeTest() {
    this.promotionDao = new PromotionDaoImpl();

  }

  @Test
  public void trouverMatiereParUe() {
    assertNotNull(this.promotionDao.trouverParNom("De Gennes"));
  }
}
