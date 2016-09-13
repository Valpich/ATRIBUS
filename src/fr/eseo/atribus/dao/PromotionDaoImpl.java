package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Promotion;

import org.joda.time.DateTime;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class PromotionDaoImpl.
 */
public class PromotionDaoImpl implements PromotionDao {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(CompetenceDaoImpl.class.getName());

  /** La variable dao factory. */
  private final DaoFactory daoFactory;

  /** La constante SQL_SELECT_ALL. */
  private static final String SQL_SELECT_ALL = "SELECT * FROM promotion";

  /** La constante SQL_SELECT_PAR_NOM. */
  private static final String SQL_SELECT_PAR_NOM = "SELECT * FROM promotion WHERE nom = ?";

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /**
   * Instancie un nouveau promotion dao impl.
   */
  PromotionDaoImpl() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("contextDao");
    this.daoFactory = (DaoFactory) bf.getFactory().getBean("daoFactory");
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.PromotionDao#trouverParNom(java.lang.String)
   */
  /* Implémentation de la méthode trouverParNom() définie dans l'interface PromotionDao */
  @Override
  public Promotion trouverParNom(String nom) {
    return this.trouverPromotion(PromotionDaoImpl.SQL_SELECT_PAR_NOM, nom);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.PromotionDao#trouverToutesLesPromotions()
   */
  /*
   * Implémentation de la méthode trouverToutesLesPromtions() définie dans l'interface PromotionDao
   */
  @Override
  public List<Promotion> trouverToutesLesPromotions() {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ArrayList<Promotion> promotions = null;
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      /*
       * Préparation de la requête avec les objets passés en arguments (ici, uniquement un id) et
       * exécution.
       */
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          PromotionDaoImpl.SQL_SELECT_ALL, false);
      resultSet = preparedStatement.executeQuery();
      promotions = new ArrayList<>();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      while (resultSet.next()) {
        promotions.add(this.map(resultSet));
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return promotions;
  }

  /**
   * . Methode utilitaire permettant d'effectuer n'importe quelle requête avec un nombre d'objet
   * variable
   *
   * @param sql le sql
   * @param objets le objets
   * @return Le paramètre promotion
   */
  public Promotion trouverPromotion(String sql, Object... objets) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Promotion promotion = null;

    try {
      /** Récupération d'une connexion depuis la Factory. */
      connexion = this.daoFactory.getConnection();
      /**
       * Préparation de la requête avec les objets passés en arguments et exécution.
       */
      preparedStatement =
          DaoUtilitaire.initialisationRequetePreparee(connexion, sql, false, objets);
      resultSet = preparedStatement.executeQuery();
      /** Parcours de la ligne de données retournée dans le ResultSet. */
      if (resultSet.next()) {
        promotion = this.map(resultSet);
      }
    } catch (final SQLException sqle) {
      PromotionDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return promotion;
  }

  /**
   * Map.
   *
   * @param resultSet le result set
   * @return Le paramètre promotion
   * @throws SQLException de type SQL exception
   */
  /*
   * Méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne issue de
   * la table des adminsyss (un ResultSet) et un bean Utilisateur.
   */
  private Promotion map(ResultSet resultSet) throws SQLException {
    final Promotion promotion = new Promotion();
    promotion.setId(resultSet.getInt("id"));
    promotion.setNom(resultSet.getString("nom"));
    promotion.setAnneeDiplome(new DateTime(resultSet.getTimestamp("annee_diplome")));
    return promotion;
  }

}
