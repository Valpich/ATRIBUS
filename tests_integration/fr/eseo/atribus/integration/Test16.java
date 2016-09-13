package fr.eseo.atribus.integration;

import static org.testng.Assert.fail;

import org.apache.tomcat.jni.Time;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Test16 {
  
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://192.168.4.12";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void test16() throws Exception {
    
    driver.get(baseUrl + "/ATRIBUS/connexion");
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("enseignantRefMatiere");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("Evaluations")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("Ajouter un examen")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("nom")).clear();
    Thread.sleep(1000);
    driver.findElement(By.id("nom")).sendKeys("Examen de probabilité");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Ajouter un exercice")).click();
    Thread.sleep(2000);
    new Select(driver.findElement(By.id("choixExamen"))).selectByVisibleText("Examen de probabilité [Probabilités]");
    Thread.sleep(2000);
    driver.findElement(By.id("plus_comp")).click();
    Thread.sleep(2000);
    new Select(driver.findElement(By.id("choixCompetence2"))).selectByVisibleText("Mathématiques");
    Thread.sleep(2000);
    new Select(driver.findElement(By.id("choixCompetence1"))).selectByVisibleText("Connaissance des principes fondamentaux");
    Thread.sleep(2000);
    driver.findElement(By.id("pourcentage1")).clear();
    driver.findElement(By.id("pourcentage1")).sendKeys("20");
    Thread.sleep(2000);
    driver.findElement(By.id("pourcentage2")).clear();
    driver.findElement(By.id("pourcentage2")).sendKeys("80");
    Thread.sleep(2000);
    driver.findElement(By.id("question")).clear();
    Thread.sleep(2000);
    driver.findElement(By.id("question")).sendKeys("On lance un dés à 12 faces, La probabilité de tomber sur un nombre pair est égale à ?");
    Thread.sleep(2000);
    driver.findElement(By.id("reponse")).clear();
    driver.findElement(By.id("reponse")).sendKeys("6 sur 12");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("span.glyphicon.glyphicon-off")).click();
    Thread.sleep(1000);

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
    } catch (NoSuchElementException excpt) {
      excpt.getMessage();
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException excpt) {
      excpt.getMessage();
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
