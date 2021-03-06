package fr.eseo.atribus.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * A factory for creating Dao objects.
 */
public class DaoFactory {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(DaoFactory.class.getName());

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La variable data source. */
  private final DataSource dataSource;

  /** La variable connection. */
  private Connection connection;

  /**
   * Constructeur par défaut.
   *
   * @param dataSource le data source
   */
  public DaoFactory(DataSource dataSource) {
    this.dataSource = dataSource;
    this.connection = null;
  }

  /**
   * Accesseur en lecture du paramètre connection.
   *
   * @return le paramètre connection
   */
  protected Connection getConnection() {
    try {
      if (this.connection == null || this.connection.isClosed()) {
        this.recupererConnection();
      }
    } catch (final SQLException sqle) {
      DaoFactory.LOGGER.log(Level.INFO, EXCEPTION, sqle);
    }
    return this.connection;
  }

  /**
   * Recuperer connection.
   */
  private void recupererConnection() {
    try {
      this.connection = this.dataSource.getConnection();
    } catch (final SQLException sqle) {
      DaoFactory.LOGGER.log(Level.INFO, EXCEPTION, sqle);
    }
  }

}
