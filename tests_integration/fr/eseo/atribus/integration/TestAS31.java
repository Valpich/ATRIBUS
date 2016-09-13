package fr.eseo.atribus.integration;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestAS31 {
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
  public void testAS31() throws Exception {
    driver.get(baseUrl + "/ATRIBUS/connexion");
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("hamonrom");
    Thread.sleep(1000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    Thread.sleep(1000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("i.fa.fa-dashboard")).click();
    driver.findElement(By.linkText("Consulter les logs")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("a.user-profile.dropdown-toggle")).click();
    Thread.sleep(1000);
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


