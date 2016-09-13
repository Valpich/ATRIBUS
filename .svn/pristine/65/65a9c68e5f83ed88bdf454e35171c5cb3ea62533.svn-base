package fr.eseo.atribus.integration;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AjouterExerciceAuto {
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
  public void testAjouterExerciceAuto() throws Exception {
    driver.get(baseUrl + "/ATRIBUS/");
    driver.findElement(By.xpath("//div[@id='sidebar-menu']/div/ul/li[7]/a/span")).click();
    driver.findElement(By.linkText("Ajouter un exercice d'auto évaluation")).click();
    new Select(driver.findElement(By.id("choixCompetence1"))).selectByVisibleText("Physique");
    driver.findElement(By.id("pourcentage1")).clear();
    driver.findElement(By.id("pourcentage1")).sendKeys("1");
    driver.findElement(By.id("pourcentage1")).clear();
    driver.findElement(By.id("pourcentage1")).sendKeys("2");
    driver.findElement(By.id("pourcentage1")).clear();
    driver.findElement(By.id("pourcentage1")).sendKeys("3");
    driver.findElement(By.id("pourcentage1")).clear();
    driver.findElement(By.id("pourcentage1")).sendKeys("4");
    driver.findElement(By.id("pourcentage1")).clear();
    driver.findElement(By.id("pourcentage1")).sendKeys("5");
    driver.findElement(By.id("pourcentage1")).clear();
    driver.findElement(By.id("pourcentage1")).sendKeys("6");
    driver.findElement(By.id("pourcentage1")).clear();
    driver.findElement(By.id("pourcentage1")).sendKeys("7");
    driver.findElement(By.id("question")).clear();
    driver.findElement(By.id("question")).sendKeys("Question");
    driver.findElement(By.id("reponse1")).clear();
    driver.findElement(By.id("reponse1")).sendKeys("Réponse");
    driver.findElement(By.xpath("(//button[@id='plus_comp'])[2]")).click();
    driver.findElement(By.id("autoEvaluation1")).click();
    driver.findElement(By.id("reponse2")).clear();
    driver.findElement(By.id("reponse2")).sendKeys("Réponse1");
    driver.findElement(By.id("points")).clear();
    driver.findElement(By.id("points")).sendKeys("15");
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
