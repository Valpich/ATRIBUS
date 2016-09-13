package fr.eseo.atribus.integration;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AjouterRessource {
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
  public void testAjouterRessource() throws Exception {
    driver.get(baseUrl + "/ATRIBUS/");
    driver.findElement(By.xpath("//div[@id='sidebar-menu']/div/ul/li[6]/a/span")).click();
    driver.findElement(By.linkText("Ajouter une ressource")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=choixCompetence | label=Travail en équipe]]
    driver.findElement(By.id("file")).clear();
    driver.findElement(By.id("file")).sendKeys("C:\\Users\\Youssef\\Desktop\\ESEO\\Analyse\\2008 - 2009 Corrigé.pdf");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Revenir à l'index")).click();
    driver.findElement(By.linkText("Matières")).click();
    driver.findElement(By.linkText("Matières")).click();
    driver.findElement(By.linkText("Ressources")).click();
    driver.findElement(By.linkText("Ressources")).click();
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
