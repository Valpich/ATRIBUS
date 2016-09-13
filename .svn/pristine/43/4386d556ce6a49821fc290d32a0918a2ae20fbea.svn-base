package fr.eseo.atribus.integration;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestE26 {

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
  public void testE2526() throws Exception {
    driver.get(baseUrl + "/ATRIBUS/connexion");
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("faralhel");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Compétences")).click();
    driver.findElement(By.linkText("Afficher le référentiel")).click();
    driver.findElement(By.xpath("//div[@id='test']/div/div/treecontrol/ul/li/i")).click();
    driver.findElement(By.xpath("//div[@id='test']/div/div/treecontrol/ul/li/treeitem/ul/li/i")).click();
    driver.findElement(By.xpath("//div[@id='test']/div/div/treecontrol/ul/li/treeitem/ul/li/treeitem/ul/li/div/span")).click();
    driver.findElement(By.xpath("//div[@id='test']/div/div/treecontrol/ul/li/treeitem/ul/li/treeitem/ul/li/div/span")).click();
    driver.findElement(By.xpath("//div[@id='test']/div/div/treecontrol/ul/li/treeitem/ul/li/treeitem/ul/li/div/span")).click();
    driver.findElement(By.linkText("Afficher mes compétences")).click();
    driver.findElement(By.cssSelector("span.glyphicon.glyphicon-off")).click();
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

