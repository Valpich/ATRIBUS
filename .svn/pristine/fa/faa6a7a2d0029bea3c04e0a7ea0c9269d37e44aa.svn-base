package fr.eseo.atribus.timers;

import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.beans.Mail;

import org.springframework.mock.web.MockServletContext;
import org.testng.annotations.Test;

public class TimerConversionNotificationEnMailTest {

  TimerConversionNotificationEnMail timerConversionNotificationEnMail;

  @Test
  public void TimerConversionNotificationEnMail() {
    this.timerConversionNotificationEnMail = new TimerConversionNotificationEnMail();
    assertNotNull(this.timerConversionNotificationEnMail);
  }

  @Test
  public void executer() {
    final MockServletContext servletContext = new MockServletContext();
    this.timerConversionNotificationEnMail.setServletContext(servletContext);
    assertNotNull(this.timerConversionNotificationEnMail.getServletContext());
    this.timerConversionNotificationEnMail.setMail(new Mail("", "", "", ""));
    assertNotNull(this.timerConversionNotificationEnMail.getMail());
    this.timerConversionNotificationEnMail.executer();
    assertNotNull(this.timerConversionNotificationEnMail);
    assertNotNull(this.timerConversionNotificationEnMail);
  }
}
