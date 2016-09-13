package fr.eseo.atribus.integration;

import static org.junit.Assert.fail;

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

public class Test48 {
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
  public void test50() throws Exception {
    driver.get(baseUrl + "ATRIBUS/connexion");
    driver.findElement(By.name("login")).sendKeys("pichavval");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(10000);
    driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/nav/ul/li[1]/a/i")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/nav/ul/li[1]/ul/li[1]/a"))
        .click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//label[3]")).click();
    driver
        .findElement(By
            .xpath("/html/body/div[1]/div/div[3]/div[1]/div/div/form/div[2]/div[1]/label/div/ins"))
        .click();
    driver
        .findElement(By
            .xpath("/html/body/div[1]/div/div[3]/div[1]/div/div/form/div[2]/div[2]/label/div/ins"))
        .click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver
        .findElement(By
            .xpath("/html/body/div[1]/div/div[3]/div[1]/div/div/form/div[2]/div[2]/label/div/ins"))
        .click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//label[4]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver
        .findElement(By
            .xpath("/html/body/div[1]/div/div[3]/div[1]/div/div/form/div[2]/div[1]/label/div/ins"))
        .click();
    driver.findElement(By.xpath("//label[2]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
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
