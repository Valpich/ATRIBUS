package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Utilisateur;

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
 * The Class UtilisateurDaoImpl.
 */
public class UtilisateurDaoImpl implements UtilisateurDao {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(UtilisateurDaoImpl.class.getName());

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La variable dao factory. */
  private final DaoFactory daoFactory;

  /** La constante SQL_INSERT. */
  private static final String SQL_INSERT =
      "INSERT INTO utilisateur (login, password, nom, prenom, email, date_inscription) "
          + "VALUES (?, ?, ?, ?, ?, NOW())";

  /** La constante SQL_SELECT_PAR_LOGIN. */
  private static final String SQL_SELECT_PAR_LOGIN = "SELECT * FROM utilisateur WHERE login = ?";

  /** La constante SQL_SELECT_PAR_LOGIN_MDP. */
  private static final String SQL_SELECT_PAR_LOGIN_MDP =
      "SELECT * FROM utilisateur WHERE login = ? AND password = ?";

  /** La constante SQL_SELECT_PAR_NOM_PRENOM. */
  private static final String SQL_SELECT_PAR_NOM_PRENOM =
      "SELECT * FROM utilisateur WHERE nom = ? AND prenom = ?";

  /** La constante SQL_DELETE_PAR_LOGIN. */
  private static final String SQL_DELETE_PAR_LOGIN = "DELETE FROM utilisateur WHERE login = ?";

  /** La constante SQL_SELECT_ALL. */
  private static final String SQL_SELECT_ALL = "SELECT * FROM utilisateur";

  /** La constante SQL_SELECT_ID. */
  private static final String SQL_SELECT_ID = "SELECT * FROM utilisateur WHERE id = ?";

  /** La constante SQL_UPDATE_PROFIL. */
  private static final String SQL_UPDATE_PROFIL =
      "UPDATE utilisateur SET notification_active = ?, notification_mail = ?, "
          + "frequence_notification_mail = ? WHERE id = ?";

  /** La constante SQL_UPDATE_LOGIN_MDP_AUTRES. */
  private static final String SQL_UPDATE_LOGIN_MDP_AUTRES =
      "UPDATE utilisateur SET login = ?, password = ?, "
          + "nom = ?, prenom = ?, email = ? WHERE login = ?";

  /** La constante SQL_UPDATE_LOGIN_AUTRES. */
  private static final String SQL_UPDATE_LOGIN_AUTRES =
      "UPDATE utilisateur SET login = ?, nom = ?, prenom = ?, email = ? WHERE login = ?";

  /** La constante SQL_UPDATE_MDP_AUTRES. */
  private static final String SQL_UPDATE_MDP_AUTRES =
      "UPDATE utilisateur SET password = ?, nom = ?, prenom = ?, email = ? WHERE login = ?";

  /** La constante SQL_UPDATE_AUTRES. */
  private static final String SQL_UPDATE_AUTRES =
      "UPDATE utilisateur SET nom = ?, prenom = ?, email = ? WHERE login = ?";



  /**
   * Instancie un nouveau utilisateur dao impl.
   */
  UtilisateurDaoImpl() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("contextDao");
    this.daoFactory = (DaoFactory) bf.getFactory().getBean("daoFactory");
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.UtilisateurDao#recupererListe()
   */
  @Override
  public List<Utilisateur> recupererListe() {
    final List<Utilisateur> listeUtilisateur = new ArrayList<>();
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          UtilisateurDaoImpl.SQL_SELECT_ALL, false);
      resultSet = preparedStatement.executeQuery();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      while (resultSet.next()) {
        listeUtilisateur.add(UtilisateurDaoImpl.map(resultSet));
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }

    return listeUtilisateur;
  }


  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.UtilisateurDao#trouverIdParLogin(java.lang.String)
   */
  @Override
  public int trouverIdParLogin(String login) {
    return DaoUtilitaire.trouverId(this.daoFactory, UtilisateurDaoImpl.SQL_SELECT_PAR_LOGIN, login);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.UtilisateurDao#trouverParLogin(java.lang.String)
   */
  /* Implémentation de la méthode trouverParLogin() définie dans l'interface UtilisateurBdd */
  @Override
  public Utilisateur trouverParLogin(String login) {
    return this.trouver(UtilisateurDaoImpl.SQL_SELECT_PAR_LOGIN, login);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.UtilisateurDao#trouverParId(int)
   */
  /* Implémentation de la méthode trouverParId() définie dans l'interface UtilisateurBdd */
  @Override
  public Utilisateur trouverParId(int id) {
    return this.trouver(UtilisateurDaoImpl.SQL_SELECT_ID, id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.UtilisateurDao#trouverParLoginPassword(java.lang.String,
   * java.lang.String)
   */
  /*
   * Implémentation de la méthode trouverParLoginPassword() définie dans l'interface UtilisateurBdd
   */
  @Override
  public Utilisateur trouverParLoginPassword(String login, String password) {
    return this.trouver(UtilisateurDaoImpl.SQL_SELECT_PAR_LOGIN_MDP, login, password);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.UtilisateurDao#trouverParNomPrenom(java.lang.String, java.lang.String)
   */
  /*
   * Implémentation de la méthode trouverParNomPrenom() définie dans l'interface UtilisateurBdd
   */
  @Override
  public Utilisateur trouverParNomPrenom(String nom, String prenom) {
    return this.trouver(UtilisateurDaoImpl.SQL_SELECT_PAR_NOM_PRENOM, nom, prenom);
  }

  /**
   * Méthode pour supprimer un utilisateur par son email en BDD.
   *
   * @param login le login
   * @return Le paramètre int
   */
  @Override
  public int supprimerParLogin(String login) {

    int statut;
    Connection connexion = null;
    PreparedStatement preparedStatement = null;

    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          UtilisateurDaoImpl.SQL_DELETE_PAR_LOGIN, false, login);

      statut = preparedStatement.executeUpdate();
      /* Analyse du statut retourné par la requête d'insertion */
      if (statut == 0) {
        throw new DaoException("Échec de la suppression de l'utilisateur, aucune "
            + "ligne n'a été supprimée dans la table.");
      }
    } catch (final SQLException sqle) {
      UtilisateurDaoImpl.LOGGER.log(Level.INFO, UtilisateurDaoImpl.EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);
    }

    return statut;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.UtilisateurDao#ajouter(fr.eseo.atribus.entities.Utilisateur)
   */
  /*
   * Implémentation de la méthode définie dans l'interface UtilisateurBdd Retourne l'utilisateur qui
   * a été ajouté (pour récupérer l'ID)
   */
  @Override
  public Utilisateur ajouter(Utilisateur utilisateur) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;

    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          UtilisateurDaoImpl.SQL_INSERT, true, utilisateur.getLogin(), utilisateur.getPassword(),
          utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail());

      final int statut = preparedStatement.executeUpdate();
      /* Analyse du statut retourné par la requête d'insertion */
      if (statut == 0) {
        throw new DaoException(
            "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
      }
    } catch (final SQLException sqle) {
      UtilisateurDaoImpl.LOGGER.log(Level.INFO, UtilisateurDaoImpl.EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }
    return utilisateur;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.UtilisateurDao#update(fr.eseo.atribus.entities.Utilisateur)
   */
  @Override
  public void update(Utilisateur utilisateur) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;

    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();

      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          UtilisateurDaoImpl.SQL_UPDATE_PROFIL, true, utilisateur.isNotificationActive(),
          utilisateur.isNotificationMail(), utilisateur.getFrequenceNotificationMail(),
          utilisateur.getId());

      final int statut = preparedStatement.executeUpdate();
      /* Analyse du statut retourné par la requête d'insertion */
      if (statut == 0) {
        throw new DaoException(
            "Échec de la modification de l'utilisateur, aucune ligne modifiée dans la table.");
      }
    } catch (final SQLException sqle) {
      UtilisateurDaoImpl.LOGGER.log(Level.INFO, UtilisateurDaoImpl.EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.UtilisateurDao#modifier(java.lang.String,
   * fr.eseo.atribus.entities.Utilisateur)
   */
  @Override
  public Utilisateur modifier(String loginPrecedent, Utilisateur utilisateur) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;

    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();

      preparedStatement = this.choixSqlModifier(loginPrecedent, utilisateur, connexion);

      final int statut = preparedStatement.executeUpdate();
      /* Analyse du statut retourné par la requête d'insertion */
      if (statut == 0) {
        throw new DaoException(
            "Échec de la modification de l'utilisateur, aucune ligne modifiée dans la table.");
      }
    } catch (final SQLException sqle) {
      UtilisateurDaoImpl.LOGGER.log(Level.INFO, UtilisateurDaoImpl.EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }
    return utilisateur;
  }

  /**
   * Choix sql modifier.
   *
   * @param loginPrecedent le login precedent
   * @param utilisateur le utilisateur
   * @param connexion le connexion
   * @return Le paramètre prepared statement
   * @throws SQLException de type SQL exception
   */
  private PreparedStatement choixSqlModifier(String loginPrecedent, Utilisateur utilisateur,
      Connection connexion) throws SQLException {
    PreparedStatement preparedStatement;
    if (utilisateur.getLogin() != null && utilisateur.getPassword() != null) {
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          UtilisateurDaoImpl.SQL_UPDATE_LOGIN_MDP_AUTRES, true, utilisateur.getLogin(),
          utilisateur.getPassword(), utilisateur.getNom(), utilisateur.getPrenom(),
          utilisateur.getEmail(), loginPrecedent);
    } else if (utilisateur.getLogin() != null && utilisateur.getPassword() == null) {
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          UtilisateurDaoImpl.SQL_UPDATE_LOGIN_AUTRES, true, utilisateur.getLogin(),
          utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), loginPrecedent);
    } else if (utilisateur.getLogin() == null && utilisateur.getPassword() != null) {
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          UtilisateurDaoImpl.SQL_UPDATE_MDP_AUTRES, true, utilisateur.getPassword(),
          utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), loginPrecedent);
    } else {
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          UtilisateurDaoImpl.SQL_UPDATE_AUTRES, true, utilisateur.getNom(), utilisateur.getPrenom(),
          utilisateur.getEmail(), loginPrecedent);
    }
    return preparedStatement;
  }

  /**
   * Trouver.
   *
   * @param sql le sql
   * @param objets le objets
   * @return Le paramètre utilisateur
   */
  /*
   * Méthode générique utilisée pour retourner un utilisateur depuis la base de données,
   * correspondant à la requête SQL donnée prenant en paramètres les objets passés en argument.
   */
  private Utilisateur trouver(String sql, Object... objets) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Utilisateur utilisateur = null;

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
        utilisateur = UtilisateurDaoImpl.map(resultSet);
      }
    } catch (final SQLException sqle) {
      UtilisateurDaoImpl.LOGGER.log(Level.INFO, UtilisateurDaoImpl.EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return utilisateur;
  }

  /**
   * Map.
   *
   * @param resultSet le result set
   * @return Le paramètre utilisateur
   * @throws SQLException de type SQL exception
   */
  /*
   * Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne
   * issue de la table des utilisateurs (un ResultSet) et un bean Utilisateur.
   */
  private static Utilisateur map(ResultSet resultSet) throws SQLException {
    final Utilisateur utilisateur = new Utilisateur();
    utilisateur.setId(resultSet.getInt("id"));
    utilisateur.setLogin(resultSet.getString("login"));
    utilisateur.setPassword(resultSet.getString("password"));
    utilisateur.setNom(resultSet.getString("nom"));
    utilisateur.setPrenom(resultSet.getString("prenom"));
    utilisateur.setEmail(resultSet.getString("email"));
    utilisateur.setDateInscription(resultSet.getTimestamp("date_inscription"));
    utilisateur.setNotificationActive(resultSet.getBoolean("notification_active"));
    utilisateur.setNotificationMail(resultSet.getBoolean("notification_mail"));
    utilisateur.setFrequenceNotificationMail(resultSet.getInt("frequence_notification_mail"));
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    final NotificationDao notificationDao =
        (NotificationDao) bf.getFactory().getBean("notificationDao");
    utilisateur.setNotifications(notificationDao.trouverToutesLesNotification(utilisateur));
    return utilisateur;
  }

}
