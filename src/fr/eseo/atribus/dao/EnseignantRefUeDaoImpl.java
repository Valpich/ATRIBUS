package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.EnseignantRefUe;

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
 * La Classe EnseignantRefUeDaoImpl.
 */
public class EnseignantRefUeDaoImpl implements EnseignantRefUeDao {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(EnseignantRefUeDaoImpl.class.getName());

  /** La variable dao factory. */
  private final DaoFactory daoFactory;

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "exception";

  /** La constante SQL_SELECT_PAR_LOGIN_HASH. */
  private static final String SQL_SELECT_PAR_LOGIN_HASH =
      "SELECT enseignant_ref_ue.id AS id_enseignant_ref_ue, enseignant.id "
          + "AS id_enseignant, utilisateur.* " + "FROM enseignant_ref_ue "
          + "LEFT JOIN enseignant ON enseignant.id = enseignant_ref_ue.id_enseignant "
          + "LEFT JOIN utilisateur ON utilisateur.id = enseignant.id_utilisateur "
          + "WHERE utilisateur.login = ? " + "AND utilisateur.password = ?";

  /** La constante SQL_INSERT. */
  private static final String SQL_INSERT =
      "INSERT IGNORE INTO enseignant_ref_ue (id_enseignant) VALUES (?)";

  /** La constante SQL_SELECT_ALL. */
  private static final String SQL_SELECT_ALL =
      "SELECT enseignant_ref_ue.id AS id_enseignant_ref_ue, enseignant.id "
          + "AS id_enseignant, utilisateur.*" + " FROM enseignant_ref_ue"
          + " LEFT JOIN enseignant ON enseignant.id = enseignant_ref_ue.id_enseignant"
          + " LEFT JOIN utilisateur ON utilisateur.id = enseignant.id_utilisateur";

  /** La constante DELETE_ENSEIGNANT_REF_UE. */
  private static final String DELETE_ENSEIGNANT_REF_UE =
      "DELETE FROM enseignant_ref_ue WHERE id = ?";

  /**
   * Instancie un nouveau enseignant ref ue dao impl.
   */
  EnseignantRefUeDaoImpl() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("contextDao");
    this.daoFactory = (DaoFactory) bf.getFactory().getBean("daoFactory");
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EnseignantRefUeDao#recupererListe()
   */
  @Override
  public List<EnseignantRefUe> recupererListe() {
    final List<EnseignantRefUe> listeEnseignantRefUe = new ArrayList<>();
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          EnseignantRefUeDaoImpl.SQL_SELECT_ALL, false);
      resultSet = preparedStatement.executeQuery();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      while (resultSet.next()) {
        listeEnseignantRefUe.add(EnseignantRefUeDaoImpl.map(resultSet));
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }

    return listeEnseignantRefUe;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EnseignantRefUeDao#trouverParLoginHash(java.lang.String,
   * java.lang.String)
   */
  /* Implémentation de la méthode trouverParLogin() définie dans l'interface UtilisateurBdd */
  @Override
  public EnseignantRefUe trouverParLoginHash(String login, String hash) {
    return this.trouver(EnseignantRefUeDaoImpl.SQL_SELECT_PAR_LOGIN_HASH, login, hash);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EnseignantRefUeDao#ajouter(fr.eseo.atribus.entities.EnseignantRefUe)
   */
  @Override
  public EnseignantRefUe ajouter(EnseignantRefUe enseignantRefUe) {

    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;

    try {
      final BeanFactoryReference bf =
          SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
      final int idEnseignant = ((EnseignantDao) bf.getFactory().getBean("enseignantDao"))
          .trouverIdParLogin(enseignantRefUe.getLogin());
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          EnseignantRefUeDaoImpl.SQL_INSERT, true, idEnseignant);
      preparedStatement.executeUpdate();

    } catch (final SQLException sqle) {
      EnseignantRefUeDaoImpl.LOGGER.log(Level.INFO, "Exception", sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }

    return enseignantRefUe;
  }


  /**
   * Trouver.
   *
   * @param sql la requete sql
   * @param objets les objets de la requete
   * @return Le paramètre enseignant ref ue
   */
  /*
   * Méthode générique utilisée pour retourner un adminsys depuis la base de données, correspondant
   * à la requête SQL donnée prenant en paramètres les objets passés en argument.
   */
  private EnseignantRefUe trouver(String sql, Object... objets) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    EnseignantRefUe enseignantRefUe = null;

    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      /*
       * Préparation de la requête avec les objets passés en arguments (ici, uniquement un id) et
       * exécution.
       */
      preparedStatement =
          DaoUtilitaire.initialisationRequetePreparee(connexion, sql, false, objets);
      resultSet = preparedStatement.executeQuery();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      if (resultSet.next()) {
        enseignantRefUe = EnseignantRefUeDaoImpl.map(resultSet);
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return enseignantRefUe;
  }

  /**
   * Suppression d'un enseignant référant à un UE par son ID.
   *
   * @param enseignantRefUe le enseignant ref ue
   */
  @Override
  public void supprimerEnseignantRefUeParId(EnseignantRefUe enseignantRefUe) {

    Connection connexion = null;
    PreparedStatement preparedStatement = null;

    try {
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          EnseignantRefUeDaoImpl.DELETE_ENSEIGNANT_REF_UE, false,
          enseignantRefUe.getIdEnseignantRefUe());

      preparedStatement.executeUpdate();

    } catch (final SQLException sqle) {
      EnseignantRefUeDaoImpl.LOGGER.log(Level.INFO, EnseignantRefUeDaoImpl.EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);
    }

  }

  /**
   * Map.
   *
   * @param resultSet le result set
   * @return Le paramètre enseignant ref ue
   * @throws SQLException de type SQL exception
   */
  /*
   * Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne
   * issue de la table des adminsyss (un ResultSet) et un bean Utilisateur.
   */
  private static EnseignantRefUe map(ResultSet resultSet) throws SQLException {
    final EnseignantRefUe enseignantRefUe = new EnseignantRefUe();
    enseignantRefUe.setId(resultSet.getInt("id"));
    enseignantRefUe.setIdEnseignant(resultSet.getInt("id_enseignant"));
    enseignantRefUe.setIdEnseignantRefUe(resultSet.getInt("id_enseignant_ref_ue"));
    enseignantRefUe.setLogin(resultSet.getString("login"));
    enseignantRefUe.setPassword(resultSet.getString("password"));
    enseignantRefUe.setNom(resultSet.getString("nom"));
    enseignantRefUe.setPrenom(resultSet.getString("prenom"));
    enseignantRefUe.setEmail(resultSet.getString("email"));
    enseignantRefUe.setDateInscription(resultSet.getTimestamp("date_inscription"));
    enseignantRefUe.setNotificationActive(resultSet.getBoolean("notification_active"));
    enseignantRefUe.setNotificationMail(resultSet.getBoolean("notification_mail"));
    enseignantRefUe.setFrequenceNotificationMail(resultSet.getInt("frequence_notification_mail"));
    return enseignantRefUe;
  }
}
