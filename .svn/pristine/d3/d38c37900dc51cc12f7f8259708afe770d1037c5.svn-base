package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Notification;
import fr.eseo.atribus.entities.Utilisateur;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class NotificationDaoImpl.
 */
public class NotificationDaoImpl implements NotificationDao {

  /** La variable dao factory. */
  private final DaoFactory daoFactory;

  /** La constante SQL_SELECT_ALL_ID. */
  private static final String SQL_SELECT_ALL_ID =
      "SELECT * FROM notification WHERE id_destinataire = ?";

  /** La constante SQL_SELECT_NOM_ID. */
  private static final String SQL_SELECT_NOM_ID =
      "SELECT nom, prenom FROM utilisateur WHERE id = ?";

  /** La constante SQL_INSERT. */
  private static final String SQL_INSERT =
      "INSERT INTO notification (id_emetteur, id_destinataire, message, date_creation) "
          + "VALUES (? , ? , ? , CURRENT_TIMESTAMP);";

  /** La constante SQL_DELETE. */
  private static final String SQL_DELETE = "DELETE FROM notification WHERE id = ?;";

  /**
   * Instancie un nouveau notification dao impl.
   */
  NotificationDaoImpl() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("contextDao");
    this.daoFactory = (DaoFactory) bf.getFactory().getBean("daoFactory");
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.NotificationDao#trouverToutesLesNotification(fr.eseo.atribus.entities.
   * Utilisateur)
   */
  @Override
  public List<Notification> trouverToutesLesNotification(Utilisateur utilisateur) {
    return this.trouver(NotificationDaoImpl.SQL_SELECT_ALL_ID, utilisateur.getId());
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.NotificationDao#supprimer(fr.eseo.atribus.entities.Notification)
   */
  /* Implémentation de la méthode définie dans l'interface NotificationDao */
  @Override
  public void supprimer(Notification notification) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          NotificationDaoImpl.SQL_DELETE, false, notification.getId());
      final int statut = preparedStatement.executeUpdate();
      /* Analyse du statut retourné par la requête d'insertion */
      if (statut == 0) {
        throw new DaoException(
            "Échec de la suppresion de la ressource, aucune ligne supprimée dans la table.");
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.NotificationDao#ajouter(fr.eseo.atribus.entities.Notification)
   */
  @Override
  public void ajouter(Notification notification) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet valeursAutoGenerees = null;
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();

      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          NotificationDaoImpl.SQL_INSERT, true, notification.getEmetteur(),
          notification.getDestinataire(), notification.getMessage());

      final int statut = preparedStatement.executeUpdate();

      /* Analyse du statut retourné par la requête d'insertion */
      if (statut == 0) {
        throw new DaoException(
            "Échec de la création de la matière, aucune ligne ajoutée dans la table.");
      }

      /* Récupération de l'id auto-généré par la requête d'insertion */
      valeursAutoGenerees = preparedStatement.getGeneratedKeys();
      if (valeursAutoGenerees.next()) {
        /* Puis initialisation de la propriété id du bean Notification avec sa valeur */
        notification.setId(valeursAutoGenerees.getInt(1));
      } else {
        throw new DaoException(
            "Échec de la création de la matière en base, aucun ID auto-généré retourné.");
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }

  }

  /**
   * Trouver.
   *
   * @param sql le sql
   * @param objets le objets
   * @return Le paramètre list
   */
  /*
   * Méthode générique utilisée pour retourner un utilisateur depuis la base de données,
   * correspondant à la requête SQL donnée prenant en paramètres les objets passés en argument.
   */
  private List<Notification> trouver(String sql, Object... objets) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    final List<Notification> notifications = new ArrayList<>();
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
      while (resultSet.next()) {
        notifications.add(this.map(resultSet));
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return notifications;
  }

  /**
   * Trouver nom utilisateur.
   *
   * @param notification le notification
   */
  private void trouverNomUtilisateur(Notification notification) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          NotificationDaoImpl.SQL_SELECT_NOM_ID, false, notification.getEmetteur());
      /* Analyse du statut retourné par la requête d'insertion */
      resultSet = preparedStatement.executeQuery();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      if (resultSet.next()) {
        notification
            .setNomEmetteur(resultSet.getString("nom") + " " + resultSet.getString("prenom"));
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
  }

  /**
   * Map.
   *
   * @param resultSet le result set
   * @return Le paramètre notification
   * @throws SQLException de type SQL exception
   */
  /*
   * Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne
   * issue de la table des utilisateurs (un ResultSet) et un bean Notification.
   */
  private Notification map(ResultSet resultSet) throws SQLException {
    final Notification notification = new Notification();
    notification.setDestinataire(resultSet.getInt("id_destinataire"));
    notification.setEmetteur(resultSet.getInt("id_emetteur"));
    notification.setMessage(resultSet.getString("message"));
    notification.setDateCreation(resultSet.getTimestamp("date_creation"));
    notification.setId(resultSet.getInt("id"));
    this.trouverNomUtilisateur(notification);
    return notification;
  }

}
