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

public class Test18 {
  
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
  public void test18() throws Exception {
    
    driver.get(baseUrl + "/ATRIBUS/connexion");
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("enseignantRefMatiere");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Evaluations")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Modifier un exercice")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("reponse1")).clear();
    driver.findElement(By.id("reponse1")).sendKeys("Oui ça va ?");
    Thread.sleep(2000);
    driver.findElement(By.id("plus_comp_1")).click();
    new Select(driver.findElement(By.id("choixCompetence1_2"))).selectByVisibleText("Physique");
    Thread.sleep(2000);
    driver.findElement(By.id("pourcentage1_1")).clear();
    driver.findElement(By.id("pourcentage1_1")).sendKeys("50");
    Thread.sleep(2000);
    driver.findElement(By.id("points1")).clear();
    driver.findElement(By.id("points1")).sendKeys("1.01");
    driver.findElement(By.id("points1")).clear();
    driver.findElement(By.id("points1")).sendKeys("1.02");
    driver.findElement(By.id("points1")).clear();
    driver.findElement(By.id("points1")).sendKeys("1.03");
    driver.findElement(By.id("points1")).clear();
    driver.findElement(By.id("points1")).sendKeys("1.04");
    driver.findElement(By.id("points1")).clear();
    driver.findElement(By.id("points1")).sendKeys("1.05");
    driver.findElement(By.id("points1")).clear();
    driver.findElement(By.id("points1")).sendKeys("1.06");
    driver.findElement(By.id("points1")).clear();
    driver.findElement(By.id("points1")).sendKeys("1.07");
    driver.findElement(By.id("points1")).clear();
    driver.findElement(By.id("points1")).sendKeys("1.08");
    driver.findElement(By.id("points1")).clear();
    driver.findElement(By.id("points1")).sendKeys("1.09");
    driver.findElement(By.id("points1")).clear();
    driver.findElement(By.id("points1")).sendKeys("1.1");
    Thread.sleep(2000);
    driver.findElement(By.id("pourcentage1_2")).clear();
    driver.findElement(By.id("pourcentage1_2")).sendKeys("50");
    Thread.sleep(2000);
    driver.findElement(By.id("plus_comp_2")).click();
    Thread.sleep(2000);
    new Select(driver.findElement(By.id("choixCompetence2_2"))).selectByVisibleText("Raisonnement analytique et résolution des problèmes");
    Thread.sleep(2000);
    new Select(driver.findElement(By.id("choixCompetence2_1"))).selectByVisibleText("Analyse et gestion des incertitudes");
    Thread.sleep(2000);
    driver.findElement(By.id("pourcentage2_1")).clear();
    driver.findElement(By.id("pourcentage2_1")).sendKeys("30");
    driver.findElement(By.id("pourcentage2_2")).clear();
    driver.findElement(By.id("pourcentage2_2")).sendKeys("70");
    Thread.sleep(2000);
    driver.findElement(By.id("reponse2")).clear();
    driver.findElement(By.id("reponse2")).sendKeys("Non non ...");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.21");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.22");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.23");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.24");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.25");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.26");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.27");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.28");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.29");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.3");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.31");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.32");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.33");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.34");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.92");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.93");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.94");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.95");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.96");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.97");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.98");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("1.99");
    driver.findElement(By.id("points2")).clear();
    driver.findElement(By.id("points2")).sendKeys("2");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("span.glyphicon.glyphicon-off")).click();
    Thread.sleep(2000);

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
