package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Semestre;

import org.joda.time.DateTime;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class SemestreDaoImpl.
 */
public class SemestreDaoImpl implements SemestreDao {

  /** La variable dao factory. */
  private final DaoFactory daoFactory;

  /** La constante SQL_LISTER_SEMESTRE. */
  private static final String SQL_LISTER_SEMESTRE = "SELECT * FROM semestre";

  /** La constante SQL_TROUVER_SEMESTRE. */
  private static final String SQL_TROUVER_SEMESTRE = "SELECT * FROM semestre WHERE numero = ?";

  /**
   * Instancie un nouveau semestre dao impl.
   */
  SemestreDaoImpl() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("contextDao");
    this.daoFactory = (DaoFactory) bf.getFactory().getBean("daoFactory");
  }

  /*
   * Procédure qui permet d'établir la liste des semestres disponibles dans la BDD
   */

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.SemestreDao#listerSemestre()
   */
  @Override
  public List<Semestre> listerSemestre() {
    final ArrayList<Semestre> listeSemestre = new ArrayList<>();
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      // Récupération d'une connexion depuis la Factory
      connexion = this.daoFactory.getConnection();
      preparedStatement = connexion.prepareStatement(SemestreDaoImpl.SQL_LISTER_SEMESTRE);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        listeSemestre.add(SemestreDaoImpl.map(resultSet));
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }

    return listeSemestre;
  }

  /**
   * Méthode qui renvoie un semestre null s'il n'est pas dans la BDD.
   *
   * @param numeroSemestre le numero semestre
   * @return Le paramètre semestre
   */
  @Override
  public Semestre trouverParNumero(int numeroSemestre) {

    Semestre semestre = null;
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {

      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          SemestreDaoImpl.SQL_TROUVER_SEMESTRE, false, numeroSemestre);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        semestre = new Semestre();
        semestre = SemestreDaoImpl.map(resultSet);
      }

    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }

    return semestre;

  }

  /**
   * Map.
   *
   * @param resultSet le result set
   * @return Le paramètre semestre
   * @throws SQLException de type SQL exception
   */
  /*
   * Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne
   * issue de la table des semestres (un ResultSet) et un bean semestre.
   */
  private static Semestre map(ResultSet resultSet) throws SQLException {
    final Semestre semestre = new Semestre();
    semestre.setId(resultSet.getInt("id"));
    semestre.setNumeroSemestre(resultSet.getInt("numero"));
    semestre.setDateDebut(new DateTime(resultSet.getDate("date_debut")));
    semestre.setDateFin(new DateTime(resultSet.getInt("date_fin")));
    return semestre;
  }

}
