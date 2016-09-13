package fr.eseo.atribus.integration;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestDP50 {
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
  public void testDP50() throws Exception {
    driver.get(baseUrl + "/ATRIBUS/connexion");
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("directeurProgrammes");
    Thread.sleep(1000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    Thread.sleep(1000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Unités d'enseignement")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("Modifier les compétences d'une unité d'enseignement")).click();
    Thread.sleep(1000);
    driver.findElement(By.name("modifier")).click();
    new Select(driver.findElement(By.id("listeCompetence"))).selectByVisibleText("Mathématiques");
    Thread.sleep(1000);
    driver.findElement(By.id("niveau")).clear();
    driver.findElement(By.id("niveau")).sendKeys("2");
    Thread.sleep(1000);
    driver.findElement(By.name("validerModification")).click();
    Thread.sleep(3000);
    driver.findElement(By.name("modifier")).click();
    driver.findElement(By.id("niveau")).clear();
    driver.findElement(By.id("niveau")).sendKeys("3");
    Thread.sleep(1000);
    driver.findElement(By.name("validerModification")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("a.user-profile.dropdown-toggle")).click();
    driver.findElement(By.linkText("Déconnexion")).click();
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
