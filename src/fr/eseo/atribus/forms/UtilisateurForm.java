package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.AdminSystDao;
import fr.eseo.atribus.dao.DirecteurEtudesDao;
import fr.eseo.atribus.dao.DirecteurProgrammesDao;
import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.dao.EnseignantDao;
import fr.eseo.atribus.dao.EnseignantRefMatiereDao;
import fr.eseo.atribus.dao.EnseignantRefUeDao;
import fr.eseo.atribus.dao.UtilisateurDao;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * La classe UtilisateurForm.
 */
public class UtilisateurForm {

  /** La constante NOM_BOUTON_SUPPRIMER. */
  protected static final String NOM_BOUTON_SUPPRIMER = "modifier";

  /** La constante NOM_BOUTON_SUPPRIMER2. */
  protected static final String NOM_BOUTON_SUPPRIMER2 = "supprimer";

  /** La constante CHAMP_LOGIN. */
  protected static final String CHAMP_LOGIN = "login";

  /** La constante CHAMP_PASS. */
  protected static final String CHAMP_PASS = "password";

  /** La constante CHAMP_NOM. */
  protected static final String CHAMP_NOM = "nom";

  /** La constante CHAMP_PRENOM. */
  protected static final String CHAMP_PRENOM = "prenom";

  /** La constante CHAMP_EMAIL. */
  protected static final String CHAMP_EMAIL = "email";

  /** La constante CB_AS. */
  protected static final String CB_AS = "cbAS"; // CheckBox Admin système

  /** La constante CB_DE. */
  protected static final String CB_DE = "cbDE"; // CheckBox Directeur études

  /** La constante CB_DP. */
  protected static final String CB_DP = "cbDP"; // CheckBox Directeur programmes

  /** La constante CB_EN. */
  protected static final String CB_EN = "cbEN"; // CheckBox Enseignant

  /** La constante CB_EURE. */
  protected static final String CB_EURE = "cbERUE"; // CheckBox Enseignant

  /** La constante CB_ERM. */
  protected static final String CB_ERM = "cbERM"; // CheckBox Enseignant

  /** La constante CB_EL. */
  protected static final String CB_EL = "cbEL"; // CheckBox élève

  /** La constante EXCEPTION. */
  protected static final String EXCEPTION = "Exception";

  /** La variable resultat. */
  protected String resultat;

  /** La variable erreurs. */
  protected Map<String, String> erreurs = new HashMap<>();

  /** La variable utilisateur dao. */
  protected UtilisateurDao utilisateurDao;

  /** La variable admin syst dao. */
  protected final AdminSystDao adminSystDao;

  /** La variable directeur études dao. */
  protected final DirecteurEtudesDao directeurEtudesDao;

  /** La variable directeur programmes dao. */
  protected final DirecteurProgrammesDao directeurProgrammesDao;

  /** La variable enseignant dao. */
  protected final EnseignantDao enseignantDao;

  /** La variable enseignant ref matière dao. */
  protected final EnseignantRefMatiereDao enseignantRefMatiereDao;

  /** La variable enseignant ref ue dao. */
  protected final EnseignantRefUeDao enseignantRefUeDao;

  /** La variable élève dao. */
  protected final EleveDao eleveDao;

  /**
   * Instancie un nouvel utilisateur form.
   */
  public UtilisateurForm() {
    super();
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.utilisateurDao = (UtilisateurDao) bf.getFactory().getBean("utilisateurDao");
    this.adminSystDao = (AdminSystDao) bf.getFactory().getBean("adminSystDao");
    this.directeurEtudesDao = (DirecteurEtudesDao) bf.getFactory().getBean("directeurEtudesDao");
    this.directeurProgrammesDao =
        (DirecteurProgrammesDao) bf.getFactory().getBean("directeurProgrammesDao");
    this.enseignantDao = (EnseignantDao) bf.getFactory().getBean("enseignantDao");
    this.enseignantRefMatiereDao =
        (EnseignantRefMatiereDao) bf.getFactory().getBean("enseignantRefMatiereDao");
    this.enseignantRefUeDao = (EnseignantRefUeDao) bf.getFactory().getBean("enseignantRefUeDao");
    this.eleveDao = (EleveDao) bf.getFactory().getBean("eleveDao");
  }

  /**
   * Ajoute un message correspondant au champ spécifié à la map des erreurs.
   *
   * @param champ le champ
   * @param message le message
   */
  protected void setErreur(String champ, String message) {
    this.erreurs.put(champ, message);
  }

  /**
   * Méthode utilitaire qui retourne null si un champ est vide, et son contenu sinon.
   *
   * @param request le request
   * @param nomChamp le nom champ
   * @return le paramètre valeur champ
   */
  protected static String getValeurChamp(HttpServletRequest request, String nomChamp) {
    final String valeur = request.getParameter(nomChamp);
    if (valeur == null || valeur.trim().length() == 0) {
      return null;
    } else {
      return valeur.trim();
    }
  }

}
