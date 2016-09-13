package fr.eseo.atribus.beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La Classe LogsConnexion.
 */
public class LogsConnexion implements Serializable {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = -2446434313782516825L;

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(LogsConnexion.class.getName());

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La constante CATALINA. */
  private static final String CATALINA = "catalina.base";

  /** La constante LOG. */
  private static final String LOG = "/logs/";

  /**
   * Instancie un nouveau logs connexion.
   */
  private LogsConnexion() {
    super();
  }

  /**
   * Accesseur en lecture sur le paramètre listeLogsFiltre.
   *
   * @param filtre le filtre
   * @return le paramètre listeLogsFiltrer
   */
  public static List<String> getListeLogsFiltrer(String filtre) {
    final ArrayList<String> logs = new ArrayList<>();
    try {
      final File dossier = new java.io.File(System.getProperty(CATALINA) + LOG);
      final File[] listeLogs = dossier.listFiles();
      for (final File log : listeLogs) {
        if (log.getName().contains(filtre)) {
          logs.add(log.getName());
        }
      }
    } catch (final NullPointerException npe) {
      LogsConnexion.LOGGER.log(Level.INFO, EXCEPTION, npe);
    }
    return logs;
  }

  /**
   * Accesseur en lecture sur le paramètre listeLogs.
   *
   * @return le paramètre listeLogs
   */
  public static List<String> getListeLogs() {
    final ArrayList<String> logs = new ArrayList<>();
    try {
      final File dossier = new java.io.File(System.getProperty(CATALINA) + LOG);
      for (final File log : dossier.listFiles()) {
        logs.add(log.getName());
      }
    } catch (final NullPointerException npe) {
      LogsConnexion.LOGGER.log(Level.INFO, "Exception", npe);
    }
    return logs;
  }

  /**
   * Accesseur en lecture sur le paramètre logFromName.
   *
   * @param choix le choix
   * @return le paramètre logFromName
   */
  public static List<String> getLogFromName(String choix) {
    final ArrayList<String> logs = new ArrayList<>();
    java.io.FileInputStream fin = null;
    try {
      fin =
          new java.io.FileInputStream(new java.io.File(System.getProperty(CATALINA) + LOG + choix));
      extracted(logs, fin);
      extracted(fin);
    } catch (final IOException ioe) {
      LogsConnexion.LOGGER.log(Level.INFO, EXCEPTION, ioe);
    }

    return logs;
  }

  /**
   * Extraction des logs.
   *
   * @param fin la fin
   */
  private static void extracted(java.io.FileInputStream fin) {
    try {
      fin.close();
    } catch (final IOException ioe) {
      LogsConnexion.LOGGER.log(Level.INFO, EXCEPTION, ioe);
    }
  }

  /**
   * Extraction d'un log particulier.
   *
   * @param logs le logs
   * @param fin la fin
   */
  private static void extracted(final ArrayList<String> logs, java.io.FileInputStream fin) {
    try {
      @SuppressWarnings("resource")
      final java.util.Scanner scanner = new java.util.Scanner(fin, "UTF-8").useDelimiter("\n");
      String testString;
      while (scanner.hasNext()) {
        testString = scanner.next();
        logs.add(testString);
      }
      scanner.close();
    } catch (final NullPointerException npe) {
      LogsConnexion.LOGGER.log(Level.INFO, EXCEPTION, npe);
    }
  }
}
