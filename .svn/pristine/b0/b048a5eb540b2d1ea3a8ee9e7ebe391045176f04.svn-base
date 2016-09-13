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

import java.util.concurrent.TimeUnit;

public class Test20 {
  
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
  public void test3() throws Exception {
    
    driver.get(baseUrl + "/ATRIBUS/connexion");
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("faralhel");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Evaluations")).click();
    driver.findElement(By.linkText("Passer un examen")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("reponses")).clear();
    driver.findElement(By.id("reponses")).sendKeys("Coucou ezfezfa");
    driver.findElement(By.xpath("(//input[@id='reponses'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@id='reponses'])[2]")).sendKeys("Oui oui ergsreag");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("span.glyphicon.glyphicon-off")).click();
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("pichavval");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Evaluations")).click();
    driver.findElement(By.linkText("Passer un examen")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("reponses")).clear();
    driver.findElement(By.id("reponses")).sendKeys("gzarag");
    driver.findElement(By.xpath("(//input[@id='reponses'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@id='reponses'])[2]")).sendKeys("zegazerhtrhr");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//a[contains(@href, '/ATRIBUS/deconnexion')]")).click();
    Thread.sleep(2000);
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys("demayale");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Evaluations")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Corriger un examen")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("(//input[@id='points'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@id='points'])[3]")).sendKeys("0");
    Thread.sleep(2000);
    driver.findElement(By.xpath("(//input[@id='points'])[5]")).clear();
    driver.findElement(By.xpath("(//input[@id='points'])[5]")).sendKeys("1");
    Thread.sleep(2000);
    driver.findElement(By.xpath("(//input[@id='points'])[6]")).clear();
    driver.findElement(By.xpath("(//input[@id='points'])[6]")).sendKeys("1");
    driver.findElement(By.xpath("(//input[@id='points'])[10]")).clear();
    driver.findElement(By.xpath("(//input[@id='points'])[10]")).sendKeys("0.0");
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
