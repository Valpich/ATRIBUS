package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.DaoException;
import fr.eseo.atribus.dao.EnseignantDao;
import fr.eseo.atribus.dao.EnseignantRefUeDao;
import fr.eseo.atribus.dao.SemestreDao;
import fr.eseo.atribus.dao.UeDao;
import fr.eseo.atribus.entities.EnseignantRefUe;
import fr.eseo.atribus.entities.UniteEnseignement;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * La classe AddUeForm.
 */
public class AddUeForm extends UeFormMethods {

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(AddUeForm.class.getName());

  /** La constante EXCEPTION. */
  private static final String EXCEPTION = "Exception";

  /** La constante CHAMP_NOMUE. */
  private static final String CHAMP_NOMUE = "nomUe";

  /** La constante CHAMP_ECTS. */
  private static final String CHAMP_ECTS = "nbCreditsEcts";

  /** La constante CHAMP_NBHEURES. */
  private static final String CHAMP_NBHEURES = "nbHeures";

  /** La constante CHAMP_SEMESTRE. */
  private static final String CHAMP_SEMESTRE = "semestre";

  /** La constante CHAMP_ID_ENSEIGNANT. */
  private static final String CHAMP_ID_ENSEIGNANT = "choixIdEnseignant";

  /** La variable ue dao. */
  private final UeDao ueDao;

  /** La variable semestre dao. */
  private final SemestreDao semestreDao;

  /** La variable enseignant dao. */
  private final EnseignantDao enseignantDao;

  /** La variable enseignant ref ue dao. */
  private final EnseignantRefUeDao enseignantRefUeDao;

  /**
   * Instatiation de AddUeForm.
   */
  public AddUeForm() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.ueDao = (UeDao) bf.getFactory().getBean("ueDao");
    this.semestreDao = (SemestreDao) bf.getFactory().getBean("semestreDao");
    this.enseignantDao = (EnseignantDao) bf.getFactory().getBean("enseignantDao");
    this.enseignantRefUeDao = (EnseignantRefUeDao) bf.getFactory().getBean("enseignantRefUeDao");
  }

  /**
   * Méthode pour l'ajout d'un UE.
   * 
   * @param request la requête
   * @return retourne un UE
   */
  public UniteEnseignement addUe(HttpServletRequest request) {

    final String nom = UeFormMethods.getValeurChamp(request, AddUeForm.CHAMP_NOMUE);
    final String ects = UeFormMethods.getValeurChamp(request, AddUeForm.CHAMP_ECTS);
    final String nbHeures = UeFormMethods.getValeurChamp(request, AddUeForm.CHAMP_NBHEURES);
    final String semestre = UeFormMethods.getValeurChamp(request, AddUeForm.CHAMP_SEMESTRE);
    final String idEnseignant =
        UeFormMethods.getValeurChamp(request, AddUeForm.CHAMP_ID_ENSEIGNANT);

    final UniteEnseignement ue = new UniteEnseignement();

    try {

      // On vérifie que les données envoyées par la requête sont correctes
      this.validerDonnees(nom, ects, nbHeures, semestre, idEnseignant);

      if (this.getErreurs().isEmpty()) {

        final EnseignantRefUe erue = new EnseignantRefUe(
            this.enseignantDao.trouverParIdUtilisateur(Integer.parseInt(idEnseignant)));

        // On ajoute l'enseignant en tant qu'enseignant référant de l'UE
        this.enseignantRefUeDao.ajouter(erue);

        // On crée l'UE avec les valeurs récupérées dans la jsp
        ue.setNom(nom);
        ue.setNbCreditsEcts(Integer.parseInt(ects));
        ue.setNbHeures(Integer.parseInt(nbHeures));
        ue.setSemestre(this.semestreDao.trouverParNumero(Integer.parseInt(semestre)));

        // On récupère l'ID du nouvelle ERUE et on l'ajoute dans l'UE
        final int idErue =
            this.enseignantDao.trouverParIdUtilisateur(erue.getId()).getIdEnseignant();

        erue.setIdEnseignantRefUe(idErue);
        ue.setEnseignantRefUe(erue);

        // On l'ajoute en base de données
        this.ueDao.ajouterUe(ue);

        this.data.setResultat("succes");

      } else {
        this.data.setResultat("echec");
      }

    } catch (final DaoException bdde) {
      this.data.setResultat("echecDao");
      AddUeForm.LOGGER.log(Level.INFO, EXCEPTION, bdde);
    }
    return ue;
  }

  /**
   * Valider donnees.
   *
   * @param nom le nom
   * @param ects les ects
   * @param nbHeures le nomnbre d'heures
   * @param semestre le semestre
   * @param enseignant l'enseignant
   */
  private void validerDonnees(String nom, String ects, String nbHeures, String semestre,
      String enseignant) {

    try {
      this.validationNom(nom);
    } catch (final FormValidationException fve) {
      this.setErreur("nom", fve.getMessage());
      AddUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }

    try {
      this.validationEcts(ects);
    } catch (final FormValidationException fve) {
      this.setErreur("ects", fve.getMessage());
      AddUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }

    try {
      this.validationNbHeures(nbHeures);
    } catch (final FormValidationException fve) {
      this.setErreur("nbHeures", fve.getMessage());
      AddUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }

    try {
      this.validationSemestres(semestre);
    } catch (final FormValidationException fve) {
      this.setErreur("semestre", fve.getMessage());
      AddUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }

    try {
      this.validationEnseignant(enseignant);
    } catch (final FormValidationException fve) {
      this.setErreur("enseignant", fve.getMessage());
      AddUeForm.LOGGER.log(Level.INFO, EXCEPTION, fve);
    }

  }

  /**
   * Validation nom.
   *
   * @param nom le nom
   * @throws FormValidationException de type form validation exception
   */
  private void validationNom(String nom) throws FormValidationException {
    if (nom == null || nom.length() < 3) {
      throw new FormValidationException("Veuillez remplir un UE avec plus de 3 caractères.");
    } else if (this.ueDao.trouverUeParNom(nom) != null) {
      throw new FormValidationException("Cette unité d'enseignement existe déjà.");
    }
  }

  /**
   * Validation ects.
   *
   * @param ects les ects
   * @throws FormValidationException de type form validation exception
   */
  private void validationEcts(String ects) throws FormValidationException {
    try {
      if (Integer.parseInt(ects) < 0) {
        throw new FormValidationException("Merci d'inscrire un nombre de point ECTS supérieur à 0");
      }
    } catch (final NumberFormatException nfe) {
      throw new FormValidationException("Le chiffre inscrit pour le nombre d'ECTS est invalide.");
    }
  }

  /**
   * Validation nombre d'heures.
   *
   * @param nbHeures le nombre d'heures
   * @throws FormValidationException de type form validation exception
   */
  private void validationNbHeures(String nbHeures) throws FormValidationException {
    try {
      if (Integer.parseInt(nbHeures) < 0) {
        throw new FormValidationException("Merci d'inscrire un nombre d'heures supérieur à 0");
      }
    } catch (final NumberFormatException nfe) {
      throw new FormValidationException("Le nombre d'heures inscrit est invalide.");
    }
  }

  /**
   * Validation semestres.
   *
   * @param semestre le semestre
   * @throws FormValidationException de type form validation exception
   */
  private void validationSemestres(String semestre) throws FormValidationException {
    try {
      if (this.semestreDao.trouverParNumero(Integer.parseInt(semestre)) == null) {
        throw new FormValidationException("Le semestre inscrit n'existe pas");
      }
    } catch (final NumberFormatException nfe) {
      throw new FormValidationException("Le chiffre inscrit pour le semestre est invalide.");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.eseo.atribus.forms.UeFormMethods#validationEnseignant(java.lang.String)
   */
  @Override
  protected void validationEnseignant(String enseignant) throws FormValidationException {
    if (this.enseignantDao.trouverParIdUtilisateur(Integer.parseInt(enseignant)) == null) {
      throw new FormValidationException("L'enseignant choisie n'existe pas");
    }
  }

}
