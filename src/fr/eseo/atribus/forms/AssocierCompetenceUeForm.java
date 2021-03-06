package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.CompetenceDao;
import fr.eseo.atribus.dao.DaoException;
import fr.eseo.atribus.dao.UeDao;
import fr.eseo.atribus.entities.Competence;
import fr.eseo.atribus.entities.UniteEnseignement;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * La classe AssocierCompetenceUeForm.
 */
public class AssocierCompetenceUeForm extends UeFormMethods {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(AddExerciceForm.class.getName());

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La constante CHAMP_UE. */
  private static final String CHAMP_UE = "listeUe";

  /** La constante CHAMP_COMPETENCE. */
  private static final String CHAMP_COMPETENCE = "listeCompetence";

  /** La constante CHAMP_NIVEAU. */
  private static final String CHAMP_NIVEAU = "niveau";

  /** La variable ue dao. */
  private final UeDao ueDao;

  /** La variable competence dao. */
  private final CompetenceDao competenceDao;

  /**
   * Constructeur.
   * 
   */
  public AssocierCompetenceUeForm() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.competenceDao = (CompetenceDao) bf.getFactory().getBean("competenceDao");
    this.ueDao = (UeDao) bf.getFactory().getBean("ueDao");
  }

  /**
   * Accesseur en lecture sur le paramètre list ue.
   *
   * @return le paramètre list ue
   */
  public List<UniteEnseignement> getListUe() {
    return this.ueDao.listerUe();
  }

  /**
   * Accesseur en lecture sur le paramètre list competences.
   *
   * @return le paramètre list competences
   */
  public List<Competence> getListCompetences() {
    return this.competenceDao.trouverToutesLesCompetences();
  }

  /**
   * Permet d'associer à un UE une compétence et un niveau.
   *
   * @param request récupération des formulaires
   * @return Le paramètre competence
   */
  public Competence associerUeCompetence(HttpServletRequest request) {

    final String ueChamp = UeFormMethods.getValeurChamp(request, AssocierCompetenceUeForm.CHAMP_UE);
    final String competenceChamp =
        UeFormMethods.getValeurChamp(request, AssocierCompetenceUeForm.CHAMP_COMPETENCE);
    final String niveauChamp =
        UeFormMethods.getValeurChamp(request, AssocierCompetenceUeForm.CHAMP_NIVEAU);

    final Competence competence = new Competence();
    final UniteEnseignement ue = new UniteEnseignement();

    try {

      this.traiterValeurs(competenceChamp, ueChamp, niveauChamp);

      if (this.data.getErreurs().isEmpty()) {

        ue.setNom(ueChamp);
        competence.setNom(competenceChamp);
        competence.setNiveau(Integer.parseInt(niveauChamp));

        this.competenceDao.associerCompetenceNiveauUe(competence, ue);
        this.data.setResultat("success");

      }

    } catch (final DaoException daoEx) {
      this.data.setResultat("erreurDao");
      AssocierCompetenceUeForm.LOGGER.log(Level.INFO, EXCEPTION, daoEx);
    }

    return competence;

  }

  /**
   * Permet de modifier le niveau et les compétence d'un UE.
   * 
   * @param request récupération des formulaires
   */
  public void modifierUeCompetence(HttpServletRequest request) {

    final String competenceChamp =
        UeFormMethods.getValeurChamp(request, AssocierCompetenceUeForm.CHAMP_COMPETENCE);
    final String niveauChamp =
        UeFormMethods.getValeurChamp(request, AssocierCompetenceUeForm.CHAMP_NIVEAU);

    try {

      this.traiterValeursDeux(competenceChamp, niveauChamp);

    } catch (final DaoException daoEx) {
      this.data.setResultat("Échec de l'association : une erreur imprévue est survenue,"
          + " merci de réessayer dans quelques instants.");
      AssocierCompetenceUeForm.LOGGER.log(Level.INFO, EXCEPTION, daoEx);
    }

  }

  /**
   * Traiter valeurs deux.
   *
   * @param competence la competence
   * @param niveau le niveau
   */
  private void traiterValeursDeux(String competence, String niveau) {
    try {
      this.validationCompetence(competence);
    } catch (final FormValidationException fve) {
      AssocierCompetenceUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("choixCompetence", "Choix de compétence invalide ou inexistant.");
    }
    try {
      this.validationNiveau(niveau);
    } catch (final FormValidationException fve) {
      AssocierCompetenceUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur(CHAMP_NIVEAU, fve.getMessage());
    }
  }

  /**
   * Vérifie que les valeurs saisies soient correctes.
   * 
   * @param competence le champ competence
   * @param ue le champ ue
   * @param niveau le champ niveau
   */
  public void traiterValeurs(String competence, String ue, String niveau) {
    try {
      this.validationCompetence(competence);
    } catch (final FormValidationException fve) {
      AssocierCompetenceUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("nomCompetence", fve.getMessage());
    }
    try {
      this.validationUe(ue);
    } catch (final FormValidationException fve) {
      AssocierCompetenceUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("nomUe", fve.getMessage());
    }
    try {
      this.validationNiveau(niveau);
    } catch (final FormValidationException fve) {
      AssocierCompetenceUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
      this.setErreur("niveau", fve.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.forms.UeFormMethods#validationCompetence(java.lang.String)
   */
  @Override
  protected void validationCompetence(String competence) throws FormValidationException {
    if (this.competenceDao.trouverUniqueParNom(competence) == null) {
      throw new FormValidationException("Cette compétence n'existe pas");
    }
  }

  /**
   * Validation ue.
   *
   * @param ue l'ue
   * @throws FormValidationException de type form validation exception
   */
  private void validationUe(String ue) throws FormValidationException {
    if (this.ueDao.trouverUeParNom(ue) == null) {
      throw new FormValidationException("Cette unité d'enseignement n'existe pas");
    }
  }

  /**
   * Validation niveau.
   *
   * @param niveau le niveau
   * @throws FormValidationException de type form validation exception
   */
  private void validationNiveau(String niveau) throws FormValidationException {
    try {

      if (Integer.parseInt(niveau) < 0 || Integer.parseInt(niveau) > 5) {
        throw new FormValidationException("Merci d'inscrire un niveau entre 0 et 5.");
      }
    } catch (final NumberFormatException nfe) {
      this.setErreur(AssocierCompetenceUeForm.CHAMP_NIVEAU, nfe.getMessage());
      throw new FormValidationException("Le chiffre inscrit est invalide.");
    }
  }

}
