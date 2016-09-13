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

public class Test13 {
  
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
  public void test13() throws Exception {
    
    driver.get(baseUrl + "/ATRIBUS/connexion");
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("enseignantRefMatiere");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("Compétences")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("Afficher le référentiel de compétence")).click();
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("i.tree-branch-head")).click();
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("treecontrol.tree-classic.ng-isolate-scope > ul.ng-scope > li.ng-scope.tree-collapsed > i.tree-branch-head")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='test']/div/div/treecontrol/ul/li[2]/treeitem/ul/li/i")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='test']/div/div/treecontrol/ul/li[2]/treeitem/ul/li/treeitem/ul/li[2]/div/span")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//a[contains(@href, '/ATRIBUS/deconnexion')]")).click();
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
