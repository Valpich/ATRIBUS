package fr.eseo.atribus.beans;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogsConnexionTest {

  @BeforeClass
  public void beforeClass() {}

  @Test(priority = 1)
  public void testMethodeGetListeLogsFiltrerString() {
    Assert.assertNotNull(LogsConnexion.getListeLogsFiltrer(null),
        "Pas de problème sur la méthode d'obtention des logs avec paramètre !");
  }

  @Test(priority = 2)
  public void testAccesseurGetListeLogs() {
    Assert.assertNotNull(LogsConnexion.getListeLogs(),
        "Pas de problème sur la méthode d'obtention des logs !");

  }

  @Test(priority = 3)
  public void testAccesseurGetLogFromNameString() {
    Assert.assertNotNull(LogsConnexion.getLogFromName(null),
        "Pas de problème sur la méthode d'obtention des logs avec paramètre !");
  }

  @AfterClass
  public void afterClass() {}

}
