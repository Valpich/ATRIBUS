package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Evaluation;
import fr.eseo.atribus.entities.Examen;
import fr.eseo.atribus.entities.Exercice;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La Classe EvaluationDaoImpl.
 */
public class EvaluationDaoImpl implements EvaluationDao {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(EvaluationDaoImpl.class.getName());

  /** La variable dao factory. */
  private final DaoFactory daoFactory;

  /** La constante INSERER_REPONSE. */
  private static final String INSERER_REPONSE =
      "INSERT INTO notes ( id_eleve, id_exercice, reponse, auto_evaluation) VALUES (?,?,?,?);";

  /** La constante ECHEC. */
  private static final String ECHEC =
      "Échec de la création de l'examen, aucune ligne" + " ajoutée dans la table.";

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La constante SQL_SELECT_ALL. */
  private static final String SQL_SELECT_ALL = "SELECT * FROM notes";

  /** La constante SQL_SELECT_ALL_ELEVE. */
  private static final String SQL_SELECT_ALL_ELEVE = "SELECT * FROM notes WHERE id_eleve = ?";

  /** La constante MAJ_EVALUATION. */
  private static final String MAJ_EVALUATION = "UPDATE notes SET note = ? WHERE id = ?;";

  /**
   * Instancie un nouveau evaluation dao impl.
   */
  EvaluationDaoImpl() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("contextDao");
    this.daoFactory = (DaoFactory) bf.getFactory().getBean("daoFactory");
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EvaluationDao#majEvaluation(java.util.List)
   */
  @Override
  public void majEvaluation(List<Evaluation> evaluations) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    for (final Evaluation evaluation : evaluations) {
      try {
        /* Récupération d'une connexion depuis la Factory */
        connexion = this.daoFactory.getConnection();
        preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
            EvaluationDaoImpl.MAJ_EVALUATION, false, evaluation.getNote(), evaluation.getId());
        final int statut = preparedStatement.executeUpdate();
        /* Analyse du statut retourné par la requête d'insertion */
        if (statut == 0) {
          throw new DaoException(ECHEC);
        }
      } catch (final SQLException sqle) {
        EvaluationDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
        throw new DaoException(sqle);
      } finally {
        DaoUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EvaluationDao#repondre(fr.eseo.atribus.entities.Eleve, java.util.List,
   * fr.eseo.atribus.entities.Examen, java.lang.Boolean)
   */
  @Override
  public void repondre(Eleve eleve, List<String> reponses, Examen examen, Boolean autoEvaluation) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    int compteur = 0;
    for (final Exercice exercice : examen.getExercices()) {
      try {
        /* Récupération d'une connexion depuis la Factory */
        connexion = this.daoFactory.getConnection();
        preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
            EvaluationDaoImpl.INSERER_REPONSE, false, eleve.getIdEleve(), exercice.getId(),
            reponses.get(compteur), autoEvaluation);
        final int statut = preparedStatement.executeUpdate();
        /* Analyse du statut retourné par la requête d'insertion */
        if (statut == 0) {
          throw new DaoException(ECHEC);
        }
      } catch (final SQLException sqle) {
        EvaluationDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
        throw new DaoException(sqle);
      } finally {
        DaoUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);
      }
      compteur++;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EvaluationDao#repondre(fr.eseo.atribus.entities.Eleve, java.util.Map,
   * fr.eseo.atribus.entities.Examen, java.lang.Boolean)
   */
  @Override
  public void repondre(Eleve eleve, Map<Exercice, List<String>> reponsesParser, Examen examen,
      Boolean autoEvaluation) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    for (final Exercice exercice : examen.getExercices()) {
      try {
        /* Récupération d'une connexion depuis la Factory */
        connexion = this.daoFactory.getConnection();
        preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion,
            EvaluationDaoImpl.INSERER_REPONSE, false, eleve.getIdEleve(), exercice.getId(),
            String.join("/", reponsesParser.get(exercice)), autoEvaluation);
        final int statut = preparedStatement.executeUpdate();
        /* Analyse du statut retourné par la requête d'insertion */
        if (statut == 0) {
          throw new DaoException(ECHEC);
        }
      } catch (final SQLException sqle) {
        EvaluationDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
        throw new DaoException(sqle);
      } finally {
        DaoUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.dao.EvaluationDao#trouverToutesLesEvaluations()
   */
  /* Implémentation de la méthode trouverParNom() définie dans l'interface ExamenBdd */
  @Override
  public List<Evaluation> trouverToutesLesEvaluations() {
    return this.trouverToutesLesEvaluations(EvaluationDaoImpl.SQL_SELECT_ALL, null);
  }

  /**
   * Trouver toutes les evaluations.
   *
   * @param sql la requete sql
   * @param eleve l'eleve
   * @return Le paramètre list
   */
  private List<Evaluation> trouverToutesLesEvaluations(String sql, Eleve eleve) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Evaluation> evaluations = null;
    try {
      /* Récupération d'une connexion depuis la Factory */
      connexion = this.daoFactory.getConnection();
      if (eleve != null) {
        preparedStatement =
            DaoUtilitaire.initialisationRequetePreparee(connexion, sql, false, eleve.getIdEleve());
      } else {
        preparedStatement = DaoUtilitaire.initialisationRequetePreparee(connexion, sql, false);
      }
      resultSet = preparedStatement.executeQuery();
      evaluations = new ArrayList<>();
      /* Parcours de la ligne de données retournée dans le ResultSet */
      while (resultSet.next()) {
        evaluations.add(this.map(resultSet));
      }
    } catch (final SQLException sqle) {
      EvaluationDaoImpl.LOGGER.log(Level.INFO, EXCEPTION, sqle);
      throw new DaoException(sqle);
    } finally {
      DaoUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
    }
    return evaluations;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * fr.eseo.atribus.dao.EvaluationDao#trouverToutesLesEvaluationsEleve(fr.eseo.atribus.entities.
   * Eleve)
   */
  /* Implémentation de la méthode trouverParNom() définie dans l'interface ExamenBdd */
  @Override
  public List<Evaluation> trouverToutesLesEvaluationsEleve(Eleve eleve) {
    return this.trouverToutesLesEvaluations(EvaluationDaoImpl.SQL_SELECT_ALL_ELEVE, eleve);
  }

  /**
   * Map.
   *
   * @param resultSet le result set
   * @return Le paramètre evaluation
   * @throws SQLException de type SQL exception
   */
  /*
   * Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne
   * issue de la table des evaluations (un ResultSet) et un bean Examen.
   */
  private Evaluation map(ResultSet resultSet) throws SQLException {
    final Evaluation evaluation = new Evaluation();
    evaluation.setDateExamen(resultSet.getTimestamp("date_examen"));
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    final EleveDao eleveDao = (EleveDao) bf.getFactory().getBean("eleveDao");
    final ExerciceDao exerciceDao = (ExerciceDao) bf.getFactory().getBean("exerciceDao");
    evaluation.setEleve(eleveDao.trouverParId(resultSet.getInt("id_eleve")));
    evaluation.setExercice(exerciceDao.trouverParId(resultSet.getInt("id_exercice")));
    if (evaluation.getExercice() == null) {
      evaluation.setExercice(exerciceDao.trouverParIdAncien(resultSet.getInt("id_exercice")));
    }
    evaluation.setId(resultSet.getInt("id"));
    evaluation.setNote(resultSet.getFloat("note"));
    evaluation.setReponse(resultSet.getString("reponse"));
    return evaluation;
  }

}
