package fr.eseo.atribus.integration;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ModifMatiereUE {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://192.168.4.12/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testModifMatiereUE() throws Exception {
    driver.get(baseUrl + "/ATRIBUS/");
    driver.findElement(By.linkText("Unités d'enseignement")).click();
    driver.findElement(By.linkText("Modifier les matières d'une unité d'enseignement")).click();
    driver.findElement(By.name("choixUe")).click();
    driver.findElement(By.cssSelector("tr.even > td.text-center > button[name=\"modifier\"]")).click();
    new Select(driver.findElement(By.id("idEnseignantNouvelleMatiere"))).selectByVisibleText("Alexis DEMAY");
    new Select(driver.findElement(By.id("idNouvelleUe"))).selectByVisibleText("Fondamentaux en Informatique");
    driver.findElement(By.id("coefficient")).clear();
    driver.findElement(By.id("coefficient")).sendKeys("2");
    driver.findElement(By.id("coefficient")).clear();
    driver.findElement(By.id("coefficient")).sendKeys("3");
    driver.findElement(By.name("validerModifierMatiere")).click();
    driver.findElement(By.linkText("Revenir à l'index")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
