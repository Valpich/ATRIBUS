package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Competence;
import fr.eseo.atribus.entities.CompetenceEleve;
import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Promotion;

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
 * La Classe EleveDaoImpl.
 */
public class EleveDaoImpl implements EleveDao {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(EleveDaoImpl.class.getName());

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La variable dao factory. */
  private final DaoFactory daoFactory;

  /** La constante SEL. */
  private static final String SEL =
      "SELECT eleve.id AS id_eleve, eleve.id_promotion AS id_promotion,utilisateur.*";

  /** La constante SEL_DEUX. */
  private static final String SEL_DEUX =
      " FROM eleve LEFT JOIN utilisateur ON utilisateur.id = eleve.id_utilisateur";

  /** La constante SQL_SELECT_PAR_LOGIN_HASH. */
  private static final String SQL_SELECT_PAR_LOGIN_HASH =
      SEL + SEL_DEUX + " WHERE utilisateur.login = ?" + " AND utilisateur.password = ?";

  /** La constante SQL_INSERT. */
  private static final String SQL_INSERT = "INSERT INTO eleve (id_utilisateur) VALUES (?)";

  /** La constante SQL_SELECT_PAR_ID. */
  private static final String SQL_SELECT_PAR_ID = SEL + SEL_DEUX + " WHERE eleve.id = ?";

  /** La constante SQL_SELECT_PAR_NOM. */
  private static final String SQL_SELECT_PAR_NOM = SEL + SEL_DEUX + " WHERE utilisateur.nom = ?";

  /** La constante SQL_SELECT_ALL. */
  private static final String SQL_SELECT_ALL = SEL + SEL_DEUX;

  /** La constante SQL_SELECT_PAR_ID_UTILISATEUR. */
  private static final String SQL_SELECT_PAR_ID_UTILISATEUR =
      SEL + SEL_DEUX + " WHERE id_utilisateur = ?";

  /** La constante SQL_DELETE_PAR_ID_UTILISATEUR. */
  private static final String SQL_DELETE_PAR_ID_UTILISATEUR =
      "DELETE FROM eleve WHERE id_utilisateur = ?";

  /** La constante SQL_RECUPERER_COMP_ELEVE. */
  private static final String SQL_RECUPERER_COMP_ELEVE =
      "SELECT * FROM eleve_associe_competence WHERE id_eleve = ?";

  /** La constante SQL_RECUPERER_ID_USER. */
  private static final String SQL_RECUPERER_ID_USER =
      "SELECT * FROM eleve WHERE id_utilisateur = ?";

  /** La constante BEANSDAO. */
  private static final String BEANSDAO = "beansDao";

  /**
   * Instancie un nouvel eleve dao impl.
   */
  EleveDaoImpl() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("contextDao");
    this.daoFactory = (DaoFactory) bf.getFactory().getBean("daoFactory");
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EleveDao#trouverParLoginHash(java.lang.String, java.lang.String)
   */
  /* Implémentation de la méthode trouverParLogin() définie dans l'interface EleveBdd */
  @Override
  public Eleve trouverParLoginHash(String login, String hash) {
    return this.trouver(EleveDaoImpl.SQL_SELECT_PAR_LOGIN_HASH, login, hash);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EleveDao#trouverParId(int)
   */
  /* Implémentation de la méthode trouverParId() définie dans l'interface EleveBdd */
  @Override
  public Eleve trouverParId(int id) {
    return this.trouver(EleveDaoImpl.SQL_SELECT_PAR_ID, id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EleveDao#trouverParNom(java.lang.String)
   */
  /* Implémentation de la méthode trouverParNom() définie dans l'interface EleveBdd */
  @Override
  public Eleve trouverParNom(String nom) {
    return this.trouver(EleveDaoImpl.SQL_SELECT_PAR_NOM, nom);
  }

  /**
   * Trouver par id utilisateur.
   *
   * @param idUtilisateur l'id utilisateur
   * @return Le paramètre eleve
   */
  private Eleve trouverParIdUtilisateur(int idUtilisateur) {
    return this.trouver(EleveDaoImpl.SQL_SELECT_PAR_ID_UTILISATEUR, idUtilisateur);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EleveDao#recupererListe()
   */
  @Override
  public List<Eleve> recupererListe() {
    final List<Eleve> listeEleve = new ArrayList<>();
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          EleveDaoImpl.SQL_SELECT_ALL, false);
      resultSet = preparedStatement.executeQuery();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      while (resultSet.next()) {
        listeEleve.add(this.map(resultSet));
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }

    return listeEleve;
  }

  /**
   * Récupérè l'ID utilisateur d'un élève.
   * 
   * @param eleve un eleve
   * @return retourne l'ID utilisateur
   */
  @Override
  public int recupererIdUtilisateur(Eleve eleve) {

    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    int idUtilisateur;

    try {

      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          EleveDaoImpl.SQL_RECUPERER_ID_USER, false, eleve.getId());
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        idUtilisateur = resultSet.getInt("id");
      } else {
        idUtilisateur = 0;
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }

    return idUtilisateur;

  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EleveDao#ajouter(fr.eseo.atribus.entities.Eleve)
   */
  @Override
  public Eleve ajouter(Eleve eleve) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;

    try {
      final BeanFactoryReference bf =
          SingletonBeanFactoryLocator.getInstance().useBeanFactory(BEANSDAO);
      final int idUtilisateur = ((UtilisateurDao) bf.getFactory().getBean("utilisateurDao"))
          .trouverIdParLogin(eleve.getLogin());

      // Si il n'y a pas déja l'utilisateur associé
      if (this.trouverParIdUtilisateur(idUtilisateur) == null) {
        connexion = this.daoFactory.getConnection();
        preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
            EleveDaoImpl.SQL_INSERT, true, idUtilisateur);

        final int statut = preparedStatement.executeUpdate();
        /* Analyse du statut retourné par la requête d'insertion */
        if (statut == 0) {
          throw new DaoException(
              "Échec de la création de l'eleve, aucune ligne ajoutée dans la table.");
        }
      }

      /* Récupération de l'id auto-généré par la requête d'insertion */
    } catch (final SQLException sqle) {
      EleveDaoImpl.LOGGER.log(Level.INFO, "Exception", sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }

    return eleve;
  }


  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EleveDao#supprimer(fr.eseo.atribus.entities.Eleve)
   */
  @Override
  public int supprimer(Eleve eleve) {

    int statut;
    Connection connexion = null;
    PreparedStatement preparedStatement = null;


    try {
      /* Récupération de l'id_utilisateur */
      final BeanFactoryReference bf =
          SingletonBeanFactoryLocator.getInstance().useBeanFactory(BEANSDAO);
      final int idUtilisateur = ((UtilisateurDao) bf.getFactory().getBean("utilisateurDao"))
          .trouverIdParLogin(eleve.getLogin());

      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          EleveDaoImpl.SQL_DELETE_PAR_ID_UTILISATEUR, false, idUtilisateur);

      statut = preparedStatement.executeUpdate();

    } catch (final SQLException sqle) {
      EleveDaoImpl.LOGGER.log(Level.INFO, EleveDaoImpl.EXCEPTION, sqle);
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
   * @param objets les objets de la requete
   * @return Le paramètre eleve
   */
  /*
   * Méthode générique utilisée pour retourner un adminsys depuis la base de données, correspondant
   * à la requête SQL donnée prenant en paramètres les objets passés en argument.
   */
  private Eleve trouver(String sql, Object... objets) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Eleve eleve = null;

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
        eleve = this.map(resultSet);
      }
    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return eleve;
  }

  /**
   * Liste les compétences d'un élève.
   * 
   * @param eleve un élève
   * @return une liste de compétence élève
   */
  @Override
  public List<CompetenceEleve> listerCompetenceEleve(Eleve eleve) {

    List<CompetenceEleve> listeCompetenceEleve = null;
    CompetenceEleve competenceEleve = null;
    Competence competence = null;
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {

      final BeanFactoryReference bf =
          SingletonBeanFactoryLocator.getInstance().useBeanFactory(BEANSDAO);

      final CompetenceDao competenceDao = (CompetenceDao) bf.getFactory().getBean("competenceDao");

      connexion = this.daoFactory.getConnection();
      listeCompetenceEleve = new ArrayList<>();

      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          EleveDaoImpl.SQL_RECUPERER_COMP_ELEVE, true, eleve.getId());

      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {

        competence = competenceDao.trouverParId(resultSet.getInt("id_competence"));

        competenceEleve = new CompetenceEleve(competence);
        competenceEleve.setNiveauCompetenceEleve(resultSet.getInt("niveau"));

        listeCompetenceEleve.add(competenceEleve);

      }

    } catch (final SQLException sqle) {
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }

    return listeCompetenceEleve;

  }

  /**
   * Map.
   *
   * @param resultSet le result set
   * @return Le paramètre eleve
   * @throws SQLException de type SQL exception
   */
  /*
   * Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne
   * issue de la table des adminsyss (un ResultSet) et un bean Utilisateur.
   */
  private Eleve map(ResultSet resultSet) throws SQLException {
    final Eleve eleve = new Eleve();
    eleve.setId(resultSet.getInt("id"));
    eleve.setIdEleve(resultSet.getInt("id_eleve"));
    eleve.setLogin(resultSet.getString("login"));
    eleve.setPassword(resultSet.getString("password"));
    eleve.setNom(resultSet.getString("nom"));
    eleve.setPrenom(resultSet.getString("prenom"));
    eleve.setEmail(resultSet.getString("email"));
    eleve.setDateInscription(resultSet.getTimestamp("date_inscription"));
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory(BEANSDAO);
    final PromotionDao promotionDao = (PromotionDao) bf.getFactory().getBean("promotionDao");
    final List<Promotion> promotions = promotionDao.trouverToutesLesPromotions();
    for (final Promotion promotion : promotions) {
      if (promotion.getId() == resultSet.getInt("id_promotion")) {
        eleve.setPromotion(promotion);
      }
    }
    eleve.setNotificationActive(resultSet.getBoolean("notification_active"));
    eleve.setNotificationMail(resultSet.getBoolean("notification_mail"));
    eleve.setFrequenceNotificationMail(resultSet.getInt("frequence_notification_mail"));
    return eleve;
  }
}
