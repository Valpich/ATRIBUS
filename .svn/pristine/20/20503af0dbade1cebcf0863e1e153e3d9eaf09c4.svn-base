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

public class Test7 {
  
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
  public void test7() throws Exception {
    
    driver.get(baseUrl + "/ATRIBUS/connexion");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Unités d'enseignement")).click();
    driver.findElement(By.linkText("Modifier une unité d'enseignement")).click();
    driver.findElement(By.name("choixUe")).click();
    driver.findElement(By.id("nomNouvelleUe")).clear();
    driver.findElement(By.id("nomNouvelleUe")).sendKeys("Mathématiques test");
    driver.findElement(By.id("ectsNouvelleUe")).clear();
    driver.findElement(By.id("ectsNouvelleUe")).sendKeys("10");
    new Select(driver.findElement(By.id("semestreNouvelleUe"))).selectByVisibleText("4");
    driver.findElement(By.id("nbHeuresNouvelleUe")).clear();
    driver.findElement(By.id("nbHeuresNouvelleUe")).sendKeys("2");
    driver.findElement(By.id("nbHeuresNouvelleUe")).clear();
    driver.findElement(By.id("nbHeuresNouvelleUe")).sendKeys("3");
    driver.findElement(By.id("nbHeuresNouvelleUe")).clear();
    driver.findElement(By.id("nbHeuresNouvelleUe")).sendKeys("4");
    new Select(driver.findElement(By.id("enseignantNouvelleUe"))).selectByVisibleText("Alexis DEMAY");
    driver.findElement(By.name("validerModifierUe")).click();
    driver.findElement(By.name("choixUe")).click();
    driver.findElement(By.linkText("Revenir à l'index")).click();
    
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
