package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Competence;
import fr.eseo.atribus.entities.Matiere;
import fr.eseo.atribus.entities.Ressource;
import fr.eseo.atribus.entities.Utilisateur;

import org.joda.time.DateTime;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class RessourceDaoImpl.
 */
public class RessourceDaoImpl implements RessourceDao {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(RessourceDaoImpl.class.getName());

  /** La variable dao factory. */
  private final DaoFactory daoFactory;

  /** La constante SQL_DELETE. */
  private static final String SQL_DELETE = "DELETE FROM ressource WHERE id = ?;";

  /** La constante SQL_INSERT. */
  private static final String SQL_INSERT =
      "INSERT INTO ressource (path, nom, type, date_publication,id_matiere) "
          + "VALUES (?,?,?,?,?)";

  /** La constante SQL_UPDATE. */
  private static final String SQL_UPDATE = "UPDATE ressource SET nom = ? WHERE id = ?;";

  /** La constante SQL_GET_LINK. */
  private static final String SQL_GET_LINK =
      "SELECT * FROM ressource_associe_competence WHERE" + " id_ressource = ?";

  /** La constante SQL_INSERT_LINK. */
  private static final String SQL_INSERT_LINK =
      "INSERT INTO ressource_associe_competence (id_competence, id_ressource) " + "VALUES (?,?)";

  /** La constante SQL_ETOILE. */
  private static final String SQL_ETOILE = "SELECT * FROM ressource ";

  /** La constante SQL_SELECT_PAR_NOM_MATIERE. */
  private static final String SQL_SELECT_PAR_NOM_MATIERE =
      SQL_ETOILE + "WHERE nom = ? AND id_matiere = ?";

  /** La constante SQL_SELECT_ALL. */
  private static final String SQL_SELECT_ALL = "SELECT * FROM ressource";

  /** La constante SQL_SELECT_PAR_NOM_TYPE. */
  private static final String SQL_SELECT_PAR_NOM_TYPE = SQL_ETOILE + "WHERE nom = ? AND type = ?";

  /** La constante SQL_PATH_RESSOURCE. */
  private static final String SQL_PATH_RESSOURCE = SQL_ETOILE + "WHERE nom = ? AND type = ?";

  /** La constante SQL_HISTORIQUE_RESSOURCE. */
  private static final String SQL_HISTORIQUE_RESSOURCE =
      "INSERT INTO consultation_ressource (id_ressource, id_utilisateur) VALUES ( ?,?);";

  /** La constante SQL_SELECT_HISTORIQUE. */
  private static final String SQL_SELECT_HISTORIQUE = "SELECT * FROM consultation_ressource;";

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /**
   * Instancie un nouveau ressource dao impl.
   */
  RessourceDaoImpl() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("contextDao");
    this.daoFactory = (DaoFactory) bf.getFactory().getBean("daoFactory");
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.RessourceDao#trouverParNomEtMatiere(java.lang.String,
   * java.lang.String)
   */
  /* Implémentation de la méthode trouverParNomEtMatiere() définie dans l'interface ressourceDao */
  @Override
  public Ressource trouverParNomEtMatiere(String nom, String matiere) {
    return this.trouver(RessourceDaoImpl.SQL_SELECT_PAR_NOM_MATIERE, nom, matiere);
  }


  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.RessourceDao#trouverParNomEtType(java.lang.String, java.lang.String)
   */
  /* Implémentation de la méthode trouverParNomEtUe() définie dans l'interface ressourceDao */
  @Override
  public Ressource trouverParNomEtType(String nom, String type) {
    return this.trouver(RessourceDaoImpl.SQL_SELECT_PAR_NOM_TYPE, nom, type);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.RessourceDao#trouverToutesLesRessources()
   */
  /*
   * Implémentation de la méthode trouverToutesLesRessources() définie dans l'interface ressourceDao
   */
  @Override
  public List<Ressource> trouverToutesLesRessources() {
    return this.trouverToutes(RessourceDaoImpl.SQL_SELECT_ALL);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.RessourceDao#trouverToutesLesRessourcesCompetence(java.lang.String)
   */
  /*
   * Implémentation de la méthode trouverToutesLesRessourcesCompetence() définie dans l'interface
   * ressourceDao
   */
  @Override
  public List<Ressource> trouverToutesLesRessourcesCompetence(String competence) {
    final List<Ressource> tmp = this.trouverToutes(RessourceDaoImpl.SQL_SELECT_ALL);
    final List<Ressource> trier = new ArrayList<>();
    for (final Ressource ressource : tmp) {
      for (final Competence comp : ressource.getCompetences()) {
        if (comp.getNom().equals(competence)) {
          trier.add(ressource);
        }
      }
    }
    return trier;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.RessourceDao#trouverPath(fr.eseo.atribus.entities.Ressource)
   */
  /* Implémentation de la méthode trouverPath() définie dans l'interface ressourceDao */
  @Override
  public String trouverPath(Ressource ressource) {
    return this.trouverPath(RessourceDaoImpl.SQL_PATH_RESSOURCE, ressource.getNom(),
        ressource.getType());
  }

  /**
   * Trouver path.
   *
   * @param sql le sql
   * @param objets le objets
   * @return Le paramètre string
   */
  /*
   * Méthode générique utilisée pour retourner un utilisateur depuis la base de données,
   * correspondant à la requête SQL donnée prenant en paramètres les objets passés en argument.
   */
  private String trouverPath(String sql, Object... objets) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

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
        return resultSet.getString("path");
      }
    } catch (final SQLException sqle) {
      RessourceDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return null;
  }


  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.RessourceDao#supprimer(fr.eseo.atribus.entities.Ressource)
   */
  /* Implémentation de la méthode définie dans l'interface ressourceDao */
  @Override
  public void supprimer(Ressource ressource) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          RessourceDaoImpl.SQL_DELETE, false, ressource.getId());
      final int statut = preparedStatement.executeUpdate();
      /* Analyse du statut retourné par la requête d'insertion */
      if (statut == 0) {
        throw new DaoException(
            "Échec de la suppresion de la ressource, aucune ligne supprimée dans la table.");
      }
    } catch (final SQLException sqle) {
      RessourceDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.RessourceDao#listerConsultations()
   */
  /* Implémentation de la méthode définie dans l'interface ressourceDao */
  @Override
  public Map<Ressource, Integer> listerConsultations() {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;
    ResultSet resultSet = null;
    final Map<Ressource, Integer> ressources = new HashMap<>();
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          RessourceDaoImpl.SQL_SELECT_HISTORIQUE, false);
      resultSet = preparedStatement.executeQuery();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      this.remplirMapConsultation(resultSet, ressources);
    } catch (final SQLException sqle) {
      RessourceDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }
    return ressources;
  }

  /**
   * Remplir map consultation.
   *
   * @param resultSet le result set
   * @param ressources le ressources
   * @throws SQLException de type SQL exception
   */
  private void remplirMapConsultation(ResultSet resultSet, Map<Ressource, Integer> ressources)
      throws SQLException {
    final List<Ressource> res = this.trouverToutesLesRessources();
    while (resultSet.next()) {
      for (final Ressource re : res) {
        this.compterNombreConsultationRessource(resultSet, ressources, re);
      }
    }
  }

  /**
   * Compter nombre consultation ressource.
   *
   * @param resultSet le result set
   * @param ressources le ressources
   * @param re le re
   * @throws SQLException de type SQL exception
   */
  private void compterNombreConsultationRessource(ResultSet resultSet,
      Map<Ressource, Integer> ressources, Ressource re) throws SQLException {
    if (re.getId() == resultSet.getInt("id_ressource")) {
      if (ressources.get(re) == null) {
        ressources.put(re, 1);
      } else {
        ressources.put(re, ressources.get(re) + 1);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.RessourceDao#consulter(fr.eseo.atribus.entities.Ressource,
   * fr.eseo.atribus.entities.Utilisateur)
   */
  /* Implémentation de la méthode définie dans l'interface ressourceDao */
  @Override
  public void consulter(Ressource ressource, Utilisateur utilisateur) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          RessourceDaoImpl.SQL_HISTORIQUE_RESSOURCE, false, ressource.getId(), utilisateur.getId());
      final int statut = preparedStatement.executeUpdate();
      /* Analyse du statut retourné par la requête d'insertion */
      if (statut == 0) {
        throw new DaoException(
            "Échec de la mise à jour du nombre de consultations de la ressource.");
      }
    } catch (final SQLException sqle) {
      RessourceDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.RessourceDao#modifier(fr.eseo.atribus.entities.Ressource)
   */
  /* Implémentation de la méthode définie dans l'interface ressourceDao */
  @Override
  public void modifier(Ressource ressource) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    final ResultSet valeursAutoGenerees = null;
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          RessourceDaoImpl.SQL_UPDATE, false, ressource.getNom(), ressource.getId());
      final int statut = preparedStatement.executeUpdate();
      /* Analyse du statut retourné par la requête d'insertion */
      if (statut == 0) {
        throw new DaoException(
            "Échec de la modification de la ressource, aucune ligne modifiée dans la table.");
      }
    } catch (final SQLException sqle) {
      RessourceDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.RessourceDao#ajouter(fr.eseo.atribus.entities.Ressource,
   * java.lang.String)
   */
  /* Implémentation de la méthode définie dans l'interface ressourceDao */
  @Override
  public void ajouter(Ressource ressource, String path) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet valeursAutoGenerees = null;
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      final BeanFactoryReference bf =
          SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
      final MatiereDao matiereDao = (MatiereDao) bf.getFactory().getBean("matiereDao");
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
          RessourceDaoImpl.SQL_INSERT, true, path, ressource.getNom(), ressource.getType(),
          ressource.getDatePublication().toDate(),
          matiereDao.trouverIdParNom(ressource.getMatiere().getNom()));
      final int statut = preparedStatement.executeUpdate();
      /* Analyse du statut retourné par la requête d'insertion */
      if (statut == 0) {
        throw new DaoException(
            "Échec de la création de la ressource, aucune ligne ajoutée dans la table.");
      }
      /* Récupération de l'id auto-généré par la requête d'insertion */
      valeursAutoGenerees = preparedStatement.getGeneratedKeys();
      if (valeursAutoGenerees.next()) {
        ressource.setId(valeursAutoGenerees.getInt(1));
      } else {
        throw new DaoException(
            "Échec de la création de la ressource en base, aucun ID auto-généré retourné.");
      }
    } catch (final SQLException sqle) {
      RessourceDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
    }
    this.ajouterLien(ressource);
  }

  /**
   * Ajouter lien.
   *
   * @param ressource le ressource
   */
  private void ajouterLien(Ressource ressource) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    for (final Competence competence : ressource.getCompetences()) {
      try {
        /* Récupération d'une connexion depuis la Factory */
        connexion = this.daoFactory.getConnection();
        preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
            RessourceDaoImpl.SQL_INSERT_LINK, true, competence.getId(), ressource.getId());
        final int statut = preparedStatement.executeUpdate();
        /* Analyse du statut retourné par la requête d'insertion */
        if (statut == 0) {
          throw new DaoException(
              "Échec de la création de la ressource, aucune ligne ajoutée dans la table.");
        }
      } catch (final SQLException sqle) {
        RessourceDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
        throw new DaoException(sqle);
      } finally {
        DaoUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);
      }
    }
  }

  /**
   * Trouver.
   *
   * @param sql le sql
   * @param objets le objets
   * @return Le paramètre ressource
   */
  /*
   * Méthode générique utilisée pour retourner un utilisateur depuis la base de données,
   * correspondant à la requête SQL donnée prenant en paramètres les objets passés en argument.
   */
  private Ressource trouver(String sql, Object... objets) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Ressource ressource = null;

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
        ressource = this.map(resultSet);
      }
    } catch (final SQLException sqle) {
      RessourceDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return ressource;
  }

  /**
   * Trouver toutes.
   *
   * @param sql le sql
   * @return Le paramètre list
   */
  /*
   * Méthode générique utilisée pour retourner un examen depuis la base de données, correspondant à
   * la requête SQL donnée prenant en paramètres les objets passés en argument.
   */
  private List<Ressource> trouverToutes(String sql) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Ressource> ressources = null;
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      /*
       * Préparation de la requête avec les objets passés en arguments (ici, uniquement un id) et
       * exécution.
       */
      preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion, sql, false);
      resultSet = preparedStatement.executeQuery();
      ressources = new ArrayList<>();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      while (resultSet.next()) {
        ressources.add(this.map(resultSet));
      }
    } catch (final SQLException sqle) {
      RessourceDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return ressources;
  }

  /**
   * Map.
   *
   * @param resultSet le result set
   * @return Le paramètre ressource
   * @throws SQLException de type SQL exception
   */
  /*
   * Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne
   * issue de la table des ressources (un ResultSet) et un bean Ressource.
   */
  private Ressource map(ResultSet resultSet) throws SQLException {
    final Ressource ressource = new Ressource();
    ressource.setId(resultSet.getInt("id"));
    ressource.setDatePublication(new DateTime(resultSet.getTimestamp("date_publication")));
    ressource.setNom(resultSet.getString("nom"));
    ressource.setType(resultSet.getString("type"));
    ressource.setPath(resultSet.getString("path"));
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    final MatiereDao matiereDao = (MatiereDao) bf.getFactory().getBean("matiereDao");
    final Matiere matiere = matiereDao.trouverParId(resultSet.getInt("id_matiere"));
    ressource.setMatiere(matiere);
    final CompetenceDao competenceDao = (CompetenceDao) bf.getFactory().getBean("competenceDao");
    ressource.setCompetences(new ArrayList<>());
    for (final int idCompetence : this.trouverIdCompetencesLiee(RessourceDaoImpl.SQL_GET_LINK,
        ressource.getId())) {
      ressource.getCompetences().add(competenceDao.trouverParId(idCompetence));
    }
    return ressource;
  }

  /**
   * Trouver id competences liee.
   *
   * @param sql le sql
   * @param idRessource le id ressource
   * @return Le paramètre list
   */
  private List<Integer> trouverIdCompetencesLiee(String sql, int idRessource) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Integer> idCompetences = null;
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      /*
       * Préparation de la requête avec les objets passés en arguments (ici, uniquement un id) et
       * exécution.
       */
      preparedStatement =
          DaoUtilitaire.initialisationRequetePreparee(connexion, sql, false, idRessource);
      resultSet = preparedStatement.executeQuery();
      idCompetences = new ArrayList<>();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      while (resultSet.next()) {
        idCompetences.add(resultSet.getInt("id_competence"));
      }
    } catch (final SQLException sqle) {
      RessourceDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return idCompetences;
  }

}
