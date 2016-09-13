package fr.eseo.atribus.logs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

public class ExportSQL {

  private String destination;
  private PrintWriter fichier;

  /**
   * Construit un ExporterSQL.
   * 
   * @param destination La destination du fichier à sauvegarder.
   */
  public ExportSQL(String destination) {

    // TODO : Utiliser les logs SQL
    this.setDestination(destination);
    try {
      fichier = new PrintWriter(new FileWriter(getDestination(), true));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  /**
   * Accesseur en lecture de la destination du fichier.
   * 
   * @return La destination du fichier.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Accesseur en ecriture de la destination du fichier.
   * 
   * @param destination La nouvelle destination du fichier.
   */
  public void setDestination(final String destination) {
    this.destination = destination;
  }

  /**
   * Accesseur en lecture sur le PrintWriter du fichier.
   * 
   * @return Le PrintWriter du fichier.
   */
  public PrintWriter getFichier() {
    return fichier;
  }

  /**
   * Accesseur en ecriture sur le PrintWriter du fichier.
   * 
   * @param fichier Le nouveau PrintWriter du fichier.
   */
  public void setFichier(PrintWriter fichier) {
    this.fichier = fichier;
  }

  /**
   * Permet d'exporter une requete SQL dans un fichier texte.
   * 
   * @param requeteSQL La requete SQL.
   */
  public void exporter(String requeteSQL) {
    fichier.println(requeteSQL + " a été effectué à : " + new Date() + ".\n");
  }

  /**
   * Permet d'exporter une requete SQL ayant creer une erreur dans un fichier texte.
   * 
   * @param requeteSQL La requete SQL non valide.
   * 
   * @param esql L'exception creer par la requete.
   */
  public void exporter(String requeteSQL, SQLException esql) {
    fichier.println(requeteSQL + " n'a été effectué à : " + new Date() + ".\n");
    fichier.print("Il en a résulté : \n" + exceptionStackTraceToString(esql) + ".\n");
  }

  /**
   * Permet de fermer le flux sur le fichier.
   */
  public void close() {
    fichier.close();
  }

  /**
   * Permet d'obtenir une chaine de caracteres à partir d'un Exception.
   * 
   * @param e L'Exception à convertir en chaine de caracteres.
   * @return L'Exception convertie en chaine de caractere.
   */
  public static String exceptionStackTraceToString(Exception e) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    e.printStackTrace(ps);
    ps.close();
    return baos.toString();
  }

  /**
   * Permet de detruire tous les fichiers present dans un dossier.
   * 
   * @param emplacement L'emplacement a vider.
   */
  static public void detruireLogs(String emplacement) {
    File path = new File(emplacement);
    if (path.exists()) {
      File[] files = path.listFiles();
      for (int i = 0; i < files.length; i++) {
        if (files[i].isDirectory()) {
          if (System.getProperty("os.name").equals("Mac OS X")) {
            detruireLogs(path + "/" + files[i]);
          } else {
            detruireLogs(path + "/" + files[i]);

            if (System.getProperty("os.name").equals("Linux")) {
            } else {
              detruireLogs(path + "\\" + files[i]);
            }
          }
        }
        files[i].delete();
      }
    }
  }
}
