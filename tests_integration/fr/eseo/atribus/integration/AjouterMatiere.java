package fr.eseo.atribus.integration;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AjouterMatiere {
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
  public void testAjouterMatiere() throws Exception {
    driver.get(baseUrl + "/ATRIBUS/");
    driver.findElement(By.xpath("//div[@id='sidebar-menu']/div/ul/li[5]/a/span")).click();
    driver.findElement(By.linkText("Ajouter une matière")).click();
    driver.findElement(By.id("nom")).clear();
    driver.findElement(By.id("nom")).sendKeys("Projet");
    new Select(driver.findElement(By.id("listeSemestre"))).selectByVisibleText("6");
    driver.findElement(By.id("coefficient_matiere")).clear();
    driver.findElement(By.id("coefficient_matiere")).sendKeys("1.5");
    driver.findElement(By.id("coefficient_matiere")).clear();
    driver.findElement(By.id("coefficient_matiere")).sendKeys("2");
    driver.findElement(By.id("coefficient_matiere")).clear();
    driver.findElement(By.id("coefficient_matiere")).sendKeys("2.5");
    driver.findElement(By.id("coefficient_matiere")).clear();
    driver.findElement(By.id("coefficient_matiere")).sendKeys("3");
    driver.findElement(By.id("coefficient_matiere")).clear();
    driver.findElement(By.id("coefficient_matiere")).sendKeys("3.5");
    driver.findElement(By.id("coefficient_matiere")).clear();
    driver.findElement(By.id("coefficient_matiere")).sendKeys("4");
    new Select(driver.findElement(By.id("listeEnseignantRefMatiere"))).selectByVisibleText("DEMAY");
    new Select(driver.findElement(By.id("listeUe"))).selectByVisibleText("Electronique analogique");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Revenir à l'index")).click();
    driver.findElement(By.linkText("Matières")).click();
    driver.findElement(By.linkText("Matières")).click();
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
