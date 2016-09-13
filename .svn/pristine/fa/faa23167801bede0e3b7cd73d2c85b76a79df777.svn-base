package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.AdminSyst;

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
 * La Classe AdminSystDaoImpl.
 */
public class AdminSystDaoImpl implements AdminSystDao {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(AdminSystDaoImpl.class.getName());

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La variable dao factory. */
  private final DaoFactory daoFactory;

  /** La constante SELECT_1. */
  private static final String SELECT_1 =
      "SELECT administrateur_systeme.id AS id_administrateur_systeme, utilisateur.* ";

  /** La constante SELECT_2. */
  private static final String SELECT_2 = " FROM administrateur_systeme ";

  /** La constante SELECT_3. */
  private static final String SELECT_3 =
      " LEFT JOIN utilisateur ON utilisateur.id = administrateur_systeme.id_utilisateur ";

  /** La constante SQL_SELECT_PAR_LOGIN_HASH. */
  private static final String SQL_SELECT_PAR_LOGIN_HASH = SELECT_1 + SELECT_2 + SELECT_3
      + " WHERE utilisateur.login = ?" + " AND utilisateur.password = ?";

  /** La constante SQL_SELECT_PAR_ID_UTILISATEUR. */
  private static final String SQL_SELECT_PAR_ID_UTILISATEUR =
      SELECT_1 + " FROM administrateur_systeme" + SELECT_3 + " WHERE id_utilisateur = ?";

  /** La constante SQL_DELETE_PAR_ID_UTILISATEUR. */
  private static final String SQL_DELETE_PAR_ID_UTILISATEUR =
      "DELETE FROM administrateur_systeme WHERE id_utilisateur = ?";

  /** La constante SQL_INSERT. */
  private static final String SQL_INSERT =
      "INSERT INTO administrateur_systeme (id_utilisateur) VALUES (?)";

  /** La constante SQL_SELECT_ALL. */
  private static final String SQL_SELECT_ALL = SELECT_1 + SELECT_2 + SELECT_3;

  /**
   * Instancie un nouveau admin syst dao impl.
   */
  AdminSystDaoImpl() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("contextDao");
    this.daoFactory = (DaoFactory) bf.getFactory().getBean("daoFactory");
  }


  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.AdminSystDao#recupererListe()
   */
  @Override
  public List<AdminSyst> recupererListe() {
    final List<AdminSyst> listeAdminSyst = new ArrayList<>();
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          AdminSystDaoImpl.SQL_SELECT_ALL, false);
      resultSet = preparedStatement.executeQuery();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      while (resultSet.next()) {
        listeAdminSyst.add(AdminSystDaoImpl.map(resultSet));
      }
    } catch (final SQLException except) {
      throw new DaoException(except);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }

    return listeAdminSyst;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.AdminSystDao#trouverParLoginHash(java.lang.String, java.lang.String)
   */
  /* Implémentation de la méthode trouverParLogin() définie dans l'interface UtilisateurBdd */
  @Override
  public AdminSyst trouverParLoginHash(String login, String hash) {
    return this.trouver(AdminSystDaoImpl.SQL_SELECT_PAR_LOGIN_HASH, login, hash);
  }

  /**
   * Trouver par id utilisateur.
   *
   * @param idUtilisateur le id utilisateur
   * @return Le paramètre admin syst
   */
  private AdminSyst trouverParIdUtilisateur(int idUtilisateur) {
    return this.trouver(AdminSystDaoImpl.SQL_SELECT_PAR_ID_UTILISATEUR, idUtilisateur);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.AdminSystDao#ajouter(fr.eseo.atribus.entities.AdminSyst)
   */
  @Override
  public AdminSyst ajouter(AdminSyst adminSyst) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;

    try {
      final BeanFactoryReference bf =
          SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
      final int idUtilisateur = ((UtilisateurDao) bf.getFactory().getBean("utilisateurDao"))
          .trouverIdParLogin(adminSyst.getLogin());

      // Si il n'y a pas déja l'utilisateur associé
      if (this.trouverParIdUtilisateur(idUtilisateur) == null) {
        connexion = this.daoFactory.getConnection();
        preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
            AdminSystDaoImpl.SQL_INSERT, true, idUtilisateur);

        final int statut = preparedStatement.executeUpdate();
        /* Analyse du statut retourné par la requête d'insertion */
        if (statut == 0) {
          throw new DaoException(
              "Échec de la création de l'adminSyst, aucune ligne ajoutée dans la table.");
        }
      }

    } catch (final SQLException sqle) {
      AdminSystDaoImpl.LOGGER.log(Level.INFO, "Exception", sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }

    return adminSyst;
  }


  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.AdminSystDao#supprimer(fr.eseo.atribus.entities.AdminSyst)
   */
  @Override
  public int supprimer(AdminSyst adminSyst) {

    int statut;
    Connection connexion = null;
    PreparedStatement preparedStatement = null;


    try {
      /* Récupération de l'id_utilisateur */
      final BeanFactoryReference bf =
          SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
      final int idUtilisateur = ((UtilisateurDao) bf.getFactory().getBean("utilisateurDao"))
          .trouverIdParLogin(adminSyst.getLogin());

      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          AdminSystDaoImpl.SQL_DELETE_PAR_ID_UTILISATEUR, false, idUtilisateur);

      statut = preparedStatement.executeUpdate();

    } catch (final SQLException sqle) {
      AdminSystDaoImpl.LOGGER.log(Level.INFO, AdminSystDaoImpl.EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);
    }

    return statut;
  }


  /**
   * Trouver.
   *
   * @param sql la requete sql
   * @param objets les objets
   * @return Le paramètre admin syst
   */
  /*
   * Méthode générique utilisée pour retourner un adminsys depuis la base de données, correspondant
   * à la requête SQL donnée prenant en paramètres les objets passés en argument.
   */
  private AdminSyst trouver(String sql, Object... objets) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    AdminSyst adminsys = null;

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
        adminsys = AdminSystDaoImpl.map(resultSet);
      }
    } catch (final SQLException except) {
      throw new DaoException(except);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return adminsys;
  }

  /**
   * Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne
   * issue de la table des adminsyss (un ResultSet) et un bean Utilisateur.
   *
   * @param resultSet le result set
   * @return Le paramètre admin syst
   * @throws SQLException de type SQL exception
   */
  private static AdminSyst map(ResultSet resultSet) throws SQLException {
    final AdminSyst adminsys = new AdminSyst();
    adminsys.setId(resultSet.getInt("id"));
    adminsys.setIdAdminSyst(resultSet.getInt("id_administrateur_systeme"));
    adminsys.setLogin(resultSet.getString("login"));
    adminsys.setPassword(resultSet.getString("password"));
    adminsys.setNom(resultSet.getString("nom"));
    adminsys.setPrenom(resultSet.getString("prenom"));
    adminsys.setEmail(resultSet.getString("email"));
    adminsys.setDateInscription(resultSet.getTimestamp("date_inscription"));
    adminsys.setNotificationActive(resultSet.getBoolean("notification_active"));
    adminsys.setNotificationMail(resultSet.getBoolean("notification_mail"));
    adminsys.setFrequenceNotificationMail(resultSet.getInt("frequence_notification_mail"));
    return adminsys;
  }


}
