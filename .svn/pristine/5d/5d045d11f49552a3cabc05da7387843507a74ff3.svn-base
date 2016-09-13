package fr.eseo.atribus.integration;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ModifierExercice {
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
  public void testModifierExercice() throws Exception {
    driver.get(baseUrl + "/ATRIBUS/");
    driver.findElement(By.linkText("Evaluations")).click();
    driver.findElement(By.linkText("Modifier un exercice")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("pourcentage1_1")).clear();
    driver.findElement(By.id("pourcentage1_1")).sendKeys("12");
    driver.findElement(By.id("pourcentage1_1")).clear();
    driver.findElement(By.id("pourcentage1_1")).sendKeys("25");
    new Select(driver.findElement(By.id("choixCompetence1_1"))).selectByVisibleText("Travail en équipe");
    driver.findElement(By.id("question1")).clear();
    driver.findElement(By.id("question1")).sendKeys("ça va ?");
    driver.findElement(By.id("reponse1")).clear();
    driver.findElement(By.id("reponse1")).sendKeys("oui et toi ?");
    driver.findElement(By.id("points1")).clear();
    driver.findElement(By.id("points1")).sendKeys("8");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("span")).click();
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
