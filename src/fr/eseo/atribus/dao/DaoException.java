package fr.eseo.atribus.dao;

/**
 * La Classe DaoException.
 */
public class DaoException extends RuntimeException {

  /** La constante serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instancie un nouveau dao exception.
   *
   * @param message le message
   */
  public DaoException(String message) {
    super(message);
  }

  /**
   * Instancie un nouveau dao exception.
   *
   * @param message le message
   * @param cause la cause
   */
  public DaoException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instancie un nouveau dao exception.
   *
   * @param cause la cause
   */
  public DaoException(Throwable cause) {
    super(cause);
  }
}
