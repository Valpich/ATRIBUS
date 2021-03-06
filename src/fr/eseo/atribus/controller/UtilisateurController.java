package fr.eseo.atribus.controller;

import fr.eseo.atribus.dao.AdminSystDao;
import fr.eseo.atribus.dao.DirecteurEtudesDao;
import fr.eseo.atribus.dao.DirecteurProgrammesDao;
import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.dao.EnseignantDao;
import fr.eseo.atribus.dao.EnseignantRefMatiereDao;
import fr.eseo.atribus.dao.EnseignantRefUeDao;
import fr.eseo.atribus.dao.UeDao;
import fr.eseo.atribus.dao.UtilisateurDao;
import fr.eseo.atribus.entities.AdminSyst;
import fr.eseo.atribus.entities.DirecteurEtudes;
import fr.eseo.atribus.entities.DirecteurProgrammes;
import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Enseignant;
import fr.eseo.atribus.entities.EnseignantRefMatiere;
import fr.eseo.atribus.entities.EnseignantRefUe;
import fr.eseo.atribus.entities.UniteEnseignement;
import fr.eseo.atribus.entities.Utilisateur;
import fr.eseo.atribus.forms.AddUserForm;
import fr.eseo.atribus.forms.ConnexionForm;
import fr.eseo.atribus.forms.ContacterAdministrateursForm;
import fr.eseo.atribus.forms.ModifierProfilUtilisateurForm;
import fr.eseo.atribus.forms.ModifierUtilisateurForm;
import fr.eseo.atribus.forms.SupprimerUtilisateurForm;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet d'implementation de la classe UtilisateurController.
 */
@Controller
public class UtilisateurController {

  /** La constante ATT_USER. */
  private static final String ATT_USER = "utilisateur";

  /** La constante ATT_FORM. */
  private static final String ATT_FORM = "form";

  /** La constante VUE_ADD_USER. */
  private static final String VUE_ADD_USER = "AdministrateurSysteme/gestionUtilisateurs";

  /** La constante VUE_CONNEXION. */
  private static final String VUE_CONNEXION = "connexion";

  /** La constante ATT_SESSION_USER. */
  private static final String ATT_SESSION_USER = "sessionUtilisateur";

  /** La constante SUPPRIMER. */
  private static final String SUPPRIMER = "supprimer";

  /** La constante MODIFIER. */
  private static final String MODIFIER = "modifier";

  /**
   * Afficher ajouter utilisateur.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/AdministrateurSysteme/GestionUtilisateurs", method = RequestMethod.GET)
  protected ModelAndView afficherAjouterUtilisateur() {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    this.genererVueGestionUtilisateurs(attributsRequete);
    return new ModelAndView(VUE_ADD_USER, attributsRequete);
  }

  /**
   * Generer vue gestion utilisateurs.
   *
   * @param hashmap le hashmap
   */
  private void genererVueGestionUtilisateurs(HashMap<String, Object> hashmap) {

    /* Récupération de la lise des utilisateurs */
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");

    final List<Utilisateur> listeUtilisateur =
        ((UtilisateurDao) bf.getFactory().getBean("utilisateurDao")).recupererListe();
    final List<AdminSyst> listeAdminSyst =
        ((AdminSystDao) bf.getFactory().getBean("adminSystDao")).recupererListe();
    final List<DirecteurEtudes> listeDirecteurEtudes =
        ((DirecteurEtudesDao) bf.getFactory().getBean("directeurEtudesDao")).recupererListe();
    final List<DirecteurProgrammes> listeDirecteurProgrammes =
        ((DirecteurProgrammesDao) bf.getFactory().getBean("directeurProgrammesDao"))
            .recupererListe();
    final List<EnseignantRefUe> listeEnseignantRefUe =
        ((EnseignantRefUeDao) bf.getFactory().getBean("enseignantRefUeDao")).recupererListe();
    final List<EnseignantRefMatiere> listeEnseignantRefMatiere =
        ((EnseignantRefMatiereDao) bf.getFactory().getBean("enseignantRefMatiereDao"))
            .recupererListe();
    final List<Enseignant> listeEnseignant =
        ((EnseignantDao) bf.getFactory().getBean("enseignantDao")).recupererListe();
    final List<Eleve> listeEleve =
        ((EleveDao) bf.getFactory().getBean("eleveDao")).recupererListe();


    final String arbreAdmin = this.genererArbreAdmin(listeAdminSyst);

    final String arbreDirecteurEtudes = this.genererArbreDirecteurEtudes(listeDirecteurEtudes);

    final String arbreDirecteurProgrammes =
        this.genererArbreDirecteurProgrammes(listeDirecteurProgrammes);

    final String arbreEnseignantRefUe = this.genererArbreErue(listeEnseignantRefUe);

    final String arbreEnseignantRefMatiere = this.genererArbreErm(listeEnseignantRefMatiere);

    final String arbreEnseignant = this.genererArbreEnseignant(listeEnseignant);

    final String arbreEleve = this.genererArbreEleve(listeEleve);

    final String arbre = new String("" + "["
        + " { \"roleName\" : \"Administrateurs\", \"children\" : [" + arbreAdmin + "]},"
        + " { \"roleName\" : \"Directeur des études\", \"children\" : [" + arbreDirecteurEtudes
        + "]}," + " { \"roleName\" : \"Directeur des programmes\", \"children\" : ["
        + arbreDirecteurProgrammes + "]},"
        + " { \"roleName\" : \"Enseignants réf UE\", \"children\" : [" + arbreEnseignantRefUe
        + "]}," + " { \"roleName\" : \"Enseignants réf Mat.\", \"children\" : ["
        + arbreEnseignantRefMatiere + "]}," + " { \"roleName\" : \"Enseignants\", \"children\" : ["
        + arbreEnseignant + "]}," + " { \"roleName\" : \"Eleves\", \"children\" : ["
        + " { \"promotion\" : \"Fourrier\", \"children\" : [" + arbreEleve + "]}" + " ]}," + " ]");

    /* On met la liste dans la session */
    hashmap.put("nbUtilisateur", listeUtilisateur.size());
    hashmap.put("nbAdminSyst", listeAdminSyst.size());
    hashmap.put("nbEleve", listeEleve.size());
    hashmap.put("nbEnseignant", listeEnseignant.size());
    hashmap.put("arbre", arbre);

  }

  /**
   * Generer arbre eleve.
   *
   * @param listeEleve la liste des eleves
   * @return Le paramètre string
   */
  private String genererArbreEleve(final List<Eleve> listeEleve) {
    String arbreEleve = new String("");
    for (int i = 0; i < listeEleve.size(); i++) {
      if (i != listeEleve.size() - 1) {
        arbreEleve += listeEleve.get(i).toJson() + ",";
      } else {
        arbreEleve += listeEleve.get(i).toJson();
      }
    }
    return arbreEleve;
  }

  /**
   * Generer arbre enseignant.
   *
   * @param listeEnseignant la liste d'enseignants
   * @return Le paramètre string
   */
  private String genererArbreEnseignant(final List<Enseignant> listeEnseignant) {
    String arbreEnseignant = new String("");
    for (int i = 0; i < listeEnseignant.size(); i++) {
      if (i != listeEnseignant.size() - 1) {
        arbreEnseignant += listeEnseignant.get(i).toJson() + ",";
      } else {
        arbreEnseignant += listeEnseignant.get(i).toJson();
      }
    }
    return arbreEnseignant;
  }

  /**
   * Generer arbre erm.
   *
   * @param listeEnseignantRefMatiere la liste des enseignants ref matiere
   * @return Le paramètre string
   */
  private String genererArbreErm(final List<EnseignantRefMatiere> listeEnseignantRefMatiere) {
    String arbreEnseignantRefMatiere = new String("");
    for (int i = 0; i < listeEnseignantRefMatiere.size(); i++) {
      if (i != listeEnseignantRefMatiere.size() - 1) {
        arbreEnseignantRefMatiere += listeEnseignantRefMatiere.get(i).toJson() + ",";
      } else {
        arbreEnseignantRefMatiere += listeEnseignantRefMatiere.get(i).toJson();
      }
    }
    return arbreEnseignantRefMatiere;
  }

  /**
   * Generer arbre erue.
   *
   * @param listeEnseignantRefUe la liste des enseignants ref ue
   * @return Le paramètre string
   */
  private String genererArbreErue(final List<EnseignantRefUe> listeEnseignantRefUe) {
    String arbreEnseignantRefUe = new String("");
    for (int i = 0; i < listeEnseignantRefUe.size(); i++) {
      if (i != listeEnseignantRefUe.size() - 1) {
        arbreEnseignantRefUe += listeEnseignantRefUe.get(i).toJson() + ",";
      } else {
        arbreEnseignantRefUe += listeEnseignantRefUe.get(i).toJson();
      }
    }
    return arbreEnseignantRefUe;
  }

  /**
   * Generer arbre directeur programmes.
   *
   * @param listeDirecteurProgrammes la liste des directeur programmes
   * @return Le paramètre string
   */
  private String genererArbreDirecteurProgrammes(
      final List<DirecteurProgrammes> listeDirecteurProgrammes) {
    String arbreDirecteurProgrammes = new String("");
    for (int i = 0; i < listeDirecteurProgrammes.size(); i++) {
      if (i != listeDirecteurProgrammes.size() - 1) {
        arbreDirecteurProgrammes += listeDirecteurProgrammes.get(i).toJson() + ",";
      } else {
        arbreDirecteurProgrammes += listeDirecteurProgrammes.get(i).toJson();
      }
    }
    return arbreDirecteurProgrammes;
  }

  /**
   * Generer arbre directeur etudes.
   *
   * @param listeDirecteurEtudes la liste de directeur etudes
   * @return Le paramètre string
   */
  private String genererArbreDirecteurEtudes(final List<DirecteurEtudes> listeDirecteurEtudes) {
    String arbreDirecteurEtudes = new String("");
    for (int i = 0; i < listeDirecteurEtudes.size(); i++) {
      if (i != listeDirecteurEtudes.size() - 1) {
        arbreDirecteurEtudes += listeDirecteurEtudes.get(i).toJson() + ",";
      } else {
        arbreDirecteurEtudes += listeDirecteurEtudes.get(i).toJson();
      }
    }
    return arbreDirecteurEtudes;
  }

  /**
   * Generer arbre admin.
   *
   * @param listeAdminSyst la liste des admin syst
   * @return Le paramètre string
   */
  private String genererArbreAdmin(final List<AdminSyst> listeAdminSyst) {
    String arbreAdmin = new String("");
    for (int i = 0; i < listeAdminSyst.size(); i++) {
      if (i != listeAdminSyst.size() - 1) {
        arbreAdmin += listeAdminSyst.get(i).toJson() + ",";
      } else {
        arbreAdmin += listeAdminSyst.get(i).toJson();
      }
    }
    return arbreAdmin;
  }

  /**
   * Ajouter un utilisateur.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/AdministrateurSysteme/GestionUtilisateurs", method = RequestMethod.POST)
  protected ModelAndView ajouterUtilisateur(HttpServletRequest request) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    if (request.getParameter("ajouter") == "") {
      final AddUserForm form = new AddUserForm();
      final Utilisateur utilisateur = form.addUser(request);
      attributsRequete.put(UtilisateurController.ATT_FORM, form);
      attributsRequete.put(UtilisateurController.ATT_USER, utilisateur);
    } else if (request.getParameter(MODIFIER) != null && request.getParameter(MODIFIER) != "") {
      final ModifierUtilisateurForm form = new ModifierUtilisateurForm();
      form.modifierUtilisateur(request);
      attributsRequete.put(UtilisateurController.ATT_FORM, form);
    } else if (request.getParameter(SUPPRIMER) != null && request.getParameter(SUPPRIMER) != "") {
      final SupprimerUtilisateurForm form = new SupprimerUtilisateurForm();
      form.supprimerUtilisateur(request);
      attributsRequete.put(UtilisateurController.ATT_FORM, form);
    }

    this.genererVueGestionUtilisateurs(attributsRequete);
    return new ModelAndView(VUE_ADD_USER, attributsRequete);
  }


  /**
   * Afficher connexion.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/connexion", method = RequestMethod.GET)
  protected ModelAndView afficherConnexion() {
    return new ModelAndView(VUE_CONNEXION);
  }

  /**
   * Tenter connexion.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/connexion", method = RequestMethod.POST)
  protected ModelAndView tenterConnexion(HttpServletRequest request) {
    /* Préparation de l'objet formulaire */
    final ConnexionForm form = new ConnexionForm();
    /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
    form.connecterUtilisateur(request);
    if (request.getSession().getAttribute(ATT_SESSION_USER) != null) {
      ((Set<Utilisateur>) request.getServletContext().getAttribute("utilisateurs"))
          .add(((List<Utilisateur>) request.getSession().getAttribute(ATT_SESSION_USER)).get(0));
      return new ModelAndView("redirect:/");
    } else {
      /* Stockage du formulaire dans l'objet request */
      final HashMap<String, Object> attributsRequete = new HashMap<>();
      attributsRequete.put(UtilisateurController.ATT_FORM, form);
      return new ModelAndView(VUE_CONNEXION, attributsRequete);
    }
  }

  /**
   * Se deconnecter.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
  protected ModelAndView seDeconnecter(HttpServletRequest request) {
    final HttpSession session = request.getSession();
    session.invalidate();
    return new ModelAndView("redirect:/connexion");
  }

  /**
   * Afficher contact admin.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/ContacterAdministrateur", method = RequestMethod.GET)
  protected ModelAndView afficherContactAdmin() {
    return new ModelAndView("contacterAdministrateur");
  }

  /**
   * Contact admin.
   *
   * @param message le message
   * @param request la requete
   * @return Le paramètre model and view
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/ContacterAdministrateur", method = RequestMethod.POST)
  protected ModelAndView contactAdmin(
      @RequestParam(value = "message", required = false) String message,
      HttpServletRequest request) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    if (message != null) {
      final ContacterAdministrateursForm form = new ContacterAdministrateursForm();
      if (((List<Utilisateur>) request.getSession()
          .getAttribute(UtilisateurController.ATT_SESSION_USER)) != null) {
        form.contacterAdministrateurs(((List<Utilisateur>) request.getSession()
            .getAttribute(UtilisateurController.ATT_SESSION_USER)).get(0), message);
      } else {
        form.contacterAdministrateurs(message);
      }
      if (!form.getErreurs().isEmpty()) {
        // On charge la liste des erreurs
        attributsRequete.put("erreurs", form.getErreurs());
      } else {
        attributsRequete.put("succes", "true");
      }
    } else {
      attributsRequete.put("vide", "true");
    }
    return new ModelAndView("contacterAdministrateur", attributsRequete);
  }

  /**
   * Afficher profil.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/MonProfil", method = RequestMethod.GET)
  protected ModelAndView afficherProfil(HttpServletRequest request) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    attributsRequete.put(ATT_SESSION_USER, request.getSession().getAttribute(ATT_SESSION_USER));
    attributsRequete.put("ue",
        this.recupererUe((List<Utilisateur>) request.getSession().getAttribute(ATT_SESSION_USER)));
    return new ModelAndView("profilUtilisateur", attributsRequete);
  }

  /**
   * Modifier profil.
   *
   * @param request la requete
   * @param options les options
   * @param notification la notification
   * @param mail le mail
   * @return Le paramètre model and view
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/MonProfil", method = RequestMethod.POST)
  protected ModelAndView modifierProfil(HttpServletRequest request,
      @RequestParam(value = "options", required = false) String options,
      @RequestParam(value = "notificationActif", required = false) String notification,
      @RequestParam(value = "mailActif", required = false) String mail) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    attributsRequete.put(ATT_SESSION_USER, request.getSession().getAttribute(ATT_SESSION_USER));
    final ModifierProfilUtilisateurForm form = new ModifierProfilUtilisateurForm();
    form.modifierProfilUtilisateur(options, notification, mail,
        ((List<Utilisateur>) request.getSession().getAttribute(ATT_SESSION_USER)).get(0));
    attributsRequete.put(ATT_FORM, form);
    attributsRequete.put("ue",
        this.recupererUe((List<Utilisateur>) request.getSession().getAttribute(ATT_SESSION_USER)));
    return new ModelAndView("profilUtilisateur", attributsRequete);
  }

  /**
   * Recuperer ue.
   *
   * @param listeUtilisateur la liste des utilisateurs
   * @return Le paramètre unite enseignement
   */
  private UniteEnseignement recupererUe(final List<Utilisateur> listeUtilisateur) {
    final UniteEnseignement ue = null;
    for (final Utilisateur utilisateur : listeUtilisateur) {
      if (utilisateur.getClass().equals(EnseignantRefUe.class)) {
        final BeanFactoryReference bf =
            SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
        final List<UniteEnseignement> ues = ((UeDao) bf.getFactory().getBean("ueDao")).listerUe();
        final EnseignantRefUe erue = (EnseignantRefUe) utilisateur;
        if (this.verifierUe(ues, erue) != null) {
          return this.verifierUe(ues, erue);
        }
      }
    }
    return ue;
  }

  /**
   * Verifier ue.
   *
   * @param ues les ues
   * @param erue l'erue
   * @return Le paramètre unite enseignement
   */
  private UniteEnseignement verifierUe(final List<UniteEnseignement> ues, EnseignantRefUe erue) {
    for (final UniteEnseignement ueTmp : ues) {
      if (ueTmp.getEnseignantRefUe() != null
          && ueTmp.getEnseignantRefUe().getIdEnseignantRefUe() == erue.getIdEnseignantRefUe()) {
        return ueTmp;
      }
    }
    return null;
  }
}
