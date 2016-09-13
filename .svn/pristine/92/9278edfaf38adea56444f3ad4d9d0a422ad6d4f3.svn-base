package fr.eseo.atribus.dao;

import static org.testng.Assert.assertEquals;

import fr.eseo.atribus.entities.Semestre;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SemestreDaoImplTest {

  SemestreDaoImpl semestreDao;

  @BeforeTest
  public void beforeTest() {
    this.semestreDao = new SemestreDaoImpl();
  }

  @Test
  public void trouverParNumero() {
    Semestre semestre = new Semestre();
    semestre = this.semestreDao.trouverParNumero(2);
    assertEquals(semestre.getNumeroSemestre(), 2);
  }
}
