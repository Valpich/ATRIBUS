package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.EnseignantRefMatiere;
import fr.eseo.atribus.entities.Matiere;

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
 * La Classe EnseignantRefMatiereDaoImpl.
 */
public class EnseignantRefMatiereDaoImpl implements EnseignantRefMatiereDao {

  /** La constante LOGGER. */
  private static final Logger LOGGER =
      Logger.getLogger(EnseignantRefMatiereDaoImpl.class.getName());

  /** La variable dao factory. */
  private final DaoFactory daoFactory;

  /** La constante SQL_SELECT_PAR_LOGIN_HASH. */
  private static final String SQL_SELECT_PAR_LOGIN_HASH =
      "SELECT enseignant_ref_matiere.id " + "AS id_enseignant_ref_matiere, enseignant.id "
          + "AS id_enseignant, utilisateur.* " + "FROM enseignant_ref_matiere "
          + "LEFT JOIN enseignant ON enseignant.id = enseignant_ref_matiere.id_enseignant "
          + "LEFT JOIN utilisateur ON utilisateur.id = enseignant.id_utilisateur "
          + "WHERE utilisateur.login = ?" + " AND utilisateur.password = ?";

  /** La constante SQL_SELECT_ALL. */
  private static final String SQL_SELECT_ALL =
      "SELECT enseignant_ref_matiere.id " + "AS id_enseignant_ref_matiere, enseignant.id "
          + "AS id_enseignant, utilisateur.*" + " FROM enseignant_ref_matiere"
          + " LEFT JOIN enseignant ON enseignant.id = enseignant_ref_matiere.id_enseignant"
          + " LEFT JOIN utilisateur ON utilisateur.id = enseignant.id_utilisateur";

  /** La constante SQL_INSERT. */
  private static final String SQL_INSERT =
      "INSERT IGNORE INTO enseignant_ref_matiere (id_enseignant) VALUES (?)";


  /**
   * Constructeur.
   */
  public EnseignantRefMatiereDaoImpl() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("contextDao");
    this.daoFactory = (DaoFactory) bf.getFactory().getBean("daoFactory");
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EnseignantRefMatiereDao#recupererListe()
   */
  @Override
  public List<EnseignantRefMatiere> recupererListe() {
    final List<EnseignantRefMatiere> listeEnseignantRefMatiere = new ArrayList<>();
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          EnseignantRefMatiereDaoImpl.SQL_SELECT_ALL, false);
      resultSet = preparedStatement.executeQuery();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      while (resultSet.next()) {
        listeEnseignantRefMatiere.add(EnseignantRefMatiereDaoImpl.map(resultSet));
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }

    return listeEnseignantRefMatiere;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EnseignantRefMatiereDao#trouverParLoginHash(java.lang.String,
   * java.lang.String)
   */
  /* Implémentation de la méthode trouverParLogin() définie dans l'interface UtilisateurBdd */
  @Override
  public EnseignantRefMatiere trouverParLoginHash(String login, String hash) {
    return this.trouver(EnseignantRefMatiereDaoImpl.SQL_SELECT_PAR_LOGIN_HASH, login, hash);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EnseignantRefMatiereDao#ajouter(fr.eseo.atribus.entities.
   * EnseignantRefMatiere)
   */
  @Override
  public EnseignantRefMatiere ajouter(EnseignantRefMatiere enseignantRefMatiere) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;

    try {
      final BeanFactoryReference bf =
          SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
      final int idEnseignant = ((EnseignantDao) bf.getFactory().getBean("enseignantDao"))
          .trouverIdParLogin(enseignantRefMatiere.getLogin());
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          EnseignantRefMatiereDaoImpl.SQL_INSERT, true, idEnseignant);
      final int statut = preparedStatement.executeUpdate();
      /* Analyse du statut retourné par la requête d'insertion */
      if (statut == 0) {
        throw new DaoException(
            "Échec de la création de l'enseignantRefMatiere, aucune ligne ajoutée dans la table.");
      }
    } catch (final SQLException sqle) {
      EnseignantRefMatiereDaoImpl.LOGGER.log(Level.INFO, "Exception", sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }

    return enseignantRefMatiere;
  }


  /**
   * Trouver.
   *
   * @param sql la requete sql
   * @param objets les objets de la requete
   * @return Le paramètre enseignant ref matiere
   */
  /*
   * Méthode générique utilisée pour retourner un adminsys depuis la base de données, correspondant
   * à la requête SQL donnée prenant en paramètres les objets passés en argument.
   */
  private EnseignantRefMatiere trouver(String sql, Object... objets) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    EnseignantRefMatiere enseignantRefMatiere = null;

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
        enseignantRefMatiere = map(resultSet);
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return enseignantRefMatiere;
  }

  /**
   * Map.
   *
   * @param resultSet le result set
   * @return Le paramètre enseignant ref matiere
   * @throws SQLException de type SQL exception
   */
  /*
   * Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne
   * issue de la table des EnseignantRefMatiere (un ResultSet) et un bean ERM .
   */
  private static EnseignantRefMatiere map(ResultSet resultSet) throws SQLException {
    final EnseignantRefMatiere enseignantRefMatiere = new EnseignantRefMatiere();
    enseignantRefMatiere.setId(resultSet.getInt("id"));
    enseignantRefMatiere.setIdEnseignant(resultSet.getInt("id_enseignant"));
    enseignantRefMatiere.setIdEnseignantRefMatiere(resultSet.getInt("id_enseignant_ref_matiere"));
    enseignantRefMatiere.setLogin(resultSet.getString("login"));
    enseignantRefMatiere.setPassword(resultSet.getString("password"));
    enseignantRefMatiere.setNom(resultSet.getString("nom"));
    enseignantRefMatiere.setPrenom(resultSet.getString("prenom"));
    enseignantRefMatiere.setEmail(resultSet.getString("email"));
    enseignantRefMatiere.setDateInscription(resultSet.getTimestamp("date_inscription"));
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");

    final Matiere matiere = ((MatiereDao) bf.getFactory().getBean("matiereDao"))
        .trouverParId(enseignantRefMatiere.getIdEnseignantRefMatiere());
    enseignantRefMatiere.setMatiere(matiere);
    enseignantRefMatiere.setNotificationActive(resultSet.getBoolean("notification_active"));
    enseignantRefMatiere.setNotificationMail(resultSet.getBoolean("notification_mail"));
    enseignantRefMatiere
        .setFrequenceNotificationMail(resultSet.getInt("frequence_notification_mail"));
    return enseignantRefMatiere;
  }
}
