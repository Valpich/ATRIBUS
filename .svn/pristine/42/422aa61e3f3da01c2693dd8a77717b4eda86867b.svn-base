package fr.eseo.atribus.integration;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AjouterExercice {
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
  public void testAjouterExercice() throws Exception {
    driver.get(baseUrl + "/ATRIBUS/");
    driver.findElement(By.linkText("Evaluations")).click();
    driver.findElement(By.linkText("Ajouter un exercice")).click();
    new Select(driver.findElement(By.id("choixCompetence1"))).selectByVisibleText("Modélisation");
    driver.findElement(By.id("pourcentage1")).clear();
    driver.findElement(By.id("pourcentage1")).sendKeys("30");
    driver.findElement(By.id("plus_comp")).click();
    new Select(driver.findElement(By.id("choixCompetence2"))).selectByVisibleText("Chimie");
    driver.findElement(By.id("pourcentage2")).clear();
    driver.findElement(By.id("pourcentage2")).sendKeys("40");
    new Select(driver.findElement(By.id("choixExamen"))).selectByVisibleText("Electronique numérique DS [Algorithmique]");
    driver.findElement(By.id("question")).clear();
    driver.findElement(By.id("question")).sendKeys("Question");
    driver.findElement(By.id("reponse")).clear();
    driver.findElement(By.id("reponse")).sendKeys("Réponse");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("i.fa.fa-graduation-cap")).click();
    driver.findElement(By.linkText("Evaluations")).click();
    driver.findElement(By.linkText("Evaluations")).click();
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
