package fr.eseo.atribus.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

public class DaoConfigurationExceptionTest {

  DaoConfigurationException daoConfiguration;

  @Test
  public void daoConfigurationExceptionString() {
    this.daoConfiguration = new DaoConfigurationException("Message");
    assertEquals(this.daoConfiguration.getMessage(), "Message");
  }

  @Test
  public void daoConfigurationExceptionStringThrowable() {
    this.daoConfiguration = new DaoConfigurationException("Message", new Throwable());
    assertEquals(this.daoConfiguration.getMessage(), "Message");
  }

  @Test
  public void daoConfigurationExceptionThrowable() {
    this.daoConfiguration = new DaoConfigurationException(new Throwable());
    assertNotNull(this.daoConfiguration);
  }
}
