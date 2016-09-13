package fr.eseo.atribus.beans;

import fr.eseo.atribus.beans.PasswordStorage.CannotPerformOperationException;
import fr.eseo.atribus.beans.PasswordStorage.InvalidHashException;

import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Test
public class PasswordStorageTest {
  private String password;
  private String hash;
  private final static String TEST = "TEST";

  @Test
  public void construireHash() {
    this.password = "password";
    try {
      this.hash = PasswordStorage.createHash(this.password);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    }
    try {
      PasswordStorage.verifyPassword(this.password, this.hash);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    } catch (final InvalidHashException e) {
      e.printStackTrace();
    }
    System.out.println(this.hash);
  }


  @Test
  public void echecsDecodeHash() {
    this.hash = "sha256:" + "0:" + "64:" + "EAkgRfTgy8ZPUryECxvzC1beTO56YIr9:"
        + "V2SAOmR3Etol5vNlRB81m7nkC/"
        + "+RwhEWk1x72V2LVN22lv7ulUdIbaA9y4LUPEvv3nAxmF6xJO2uPV7hjA90rQ==";
    try {
      PasswordStorage.verifyPassword(this.password, this.hash);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    } catch (final InvalidHashException e) {
      e.printStackTrace();
    }
    this.hash =
        "sha256:" + "64:" + "EAkgRfTgy8ZPUryECxvzC1beTO56YIr9:" + "V2SAOmR3Etol5vNlRB81m7nkC/"
            + "+RwhEWk1x72V2LVN22lv7ulUdIbaA9y4LUPEvv3nAxmF6xJO2uPV7hjA90rQ==";
    try {
      PasswordStorage.verifyPassword(this.password, this.hash);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    } catch (final InvalidHashException e) {
      e.printStackTrace();
    }
    this.hash = "sha256:" + "e:" + "64:" + "EAkgRfTgy8ZPUryECxvzC1beTO56YIr9:"
        + "V2SAOmR3Etol5vNlRB81m7nkC/"
        + "+RwhEWk1x72V2LVN22lv7ulUdIbaA9y4LUPEvv3nAxmF6xJO2uPV7hjA90rQ==";
    try {
      PasswordStorage.verifyPassword(this.password, this.hash);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    } catch (final InvalidHashException e) {
      e.printStackTrace();
    }
    this.hash = "sha256:" + "64000:" + "64:" + "EAkgRfTgy8ZPUryECxvzC1beTO56YIr9:"
        + "V2SAOmR3Etol5vNlRB81m7nkC/"
        + "+RwhEWk1x72V2LVN22lv7ulUdIbaA9y4LUPEvv3nAxmF6xJO2uPV7hjA90rQ==";
    try {
      PasswordStorage.verifyPassword(this.password, this.hash);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    } catch (final InvalidHashException e) {
      e.printStackTrace();
    }
    this.hash = "sha26:" + "64000:" + "64:" + "EAkgRfTgy8ZPUryECxvzC1beTO56YIr9:"
        + "V2SAOmR3Etol5vNlRB81m7nkC/"
        + "+RwhEWk1x72V2LVN22lv7ulUdIbaA9y4LUPEvv3nAxmF6xJO2uPV7hjA90rQ==";
    try {
      PasswordStorage.verifyPassword(this.password, this.hash);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    } catch (final InvalidHashException e) {
      e.printStackTrace();
    }
    this.hash = "sha256:" + "6400:" + "64:" + "EAkgRfTgy8ZPUryECxvzC1beTO56YIr9:"
        + "V2SAOmR3Etol5vNlRB81m7nkC/"
        + "+RwhEWk1x72V2LVN22lv7ulUdIbaA9y4LUPEvv3nAxmF6xJO2uPV7hjA90rQ==";
    try {
      PasswordStorage.verifyPassword(this.password, this.hash);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    } catch (final InvalidHashException e) {
      e.printStackTrace();
    }
    this.hash = "sha256:" + "64000:" + "4:" + "EAkgRfTgy8ZPUryECxvzC1beTO56YIr9:"
        + "V2SAOmR3Etol5vNlRB81m7nkC/"
        + "+RwhEWk1x72V2LVN22lv7ulUdIbaA9y4LUPEvv3nAxmF6xJO2uPV7hjA90rQ==";
    try {
      PasswordStorage.verifyPassword(this.password, this.hash);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    } catch (final InvalidHashException e) {
      e.printStackTrace();
    }
    this.hash = "sha256:" + "64000:" + "64:" + "EAkgRfTgy8ZPUryExvzC1beTO56YIr9:"
        + "V2SAOmR3Etol5vNlRB81m7nkC/"
        + "+RwhEWk1x72V2LVN22lv7ulUdIbaA9y4LUPEvv3nAxmF6xJO2uPV7hjA90rQ==";
    try {
      PasswordStorage.verifyPassword(this.password, this.hash);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    } catch (final InvalidHashException e) {
      e.printStackTrace();
    }
    this.hash = "sha256:" + "64000:" + "64:" + "EAkgRfTgy8ZPUryECxvzC1beTO56YIr9:"
        + "V2SAOmR3Et<l5vNlRB54m7nkC/"
        + "+RwhEWk1x72V2LVN22lv7ulUdIbaA9y4LUPEvv3nAxmF6xJO2uPV7hjA90rQ==";
    try {
      PasswordStorage.verifyPassword(this.password, this.hash);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    } catch (final InvalidHashException e) {
      e.printStackTrace();
    }
    this.hash = "sha256:" + "64000:" + "64:" + "EAkgRfTgy8ZPUryECxvzC1beTO56YIr9:"
        + "V2SAOmR3Etol5vNlRB81m7nkC/"
        + "+RwhEWk1x72V2LVN<2lv7ulUdIbaA9y4LNHEvv3nAxmF6xJO2uPV7hjA90rQ==";
    try {
      PasswordStorage.verifyPassword(this.password, this.hash);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    } catch (final InvalidHashException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("static-access")
  @Test
  public void testChampPrives() {
    PasswordStorage passwordStorage = null;
    final Constructor<?>[] constructors = PasswordStorage.class.getDeclaredConstructors();
    for (final Constructor constructor : constructors) {
      constructor.setAccessible(true);
      try {
        passwordStorage = (PasswordStorage) constructor.newInstance();
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
          | InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    Field f = null;
    try {
      f = passwordStorage.getClass().getDeclaredField("PBKDF2_ALGORITHM");
    } catch (NoSuchFieldException | SecurityException e1) {
      e1.printStackTrace();
    }
    f.setAccessible(true);
    try {
      f.set(null, PasswordStorageTest.TEST);
    } catch (IllegalArgumentException | IllegalAccessException e1) {
      e1.printStackTrace();
    }
    try {
      PasswordStorage.createHash(this.password);
    } catch (final CannotPerformOperationException e) {
      e.printStackTrace();
    }
  }

}
