package fr.eseo.atribus.controller;

import fr.eseo.atribus.beans.LogsConnexion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * Servlet d'implementation de la classe LogsController.
 */
@Controller
public class LogsController {

  /** La constante VUE. */
  private static final String VUE = "AdministrateurSysteme/logs";

  /** La constante LISTE_LOGS. */
  private static final String LISTE_LOGS = "listeDesLogs";

  /** La constante CHOIX_LOG. */
  private static final String CHOIX_LOG = "choixLog";

  /**
   * Afficher les logs.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/AdministrateurSysteme/AfficherLogs", method = RequestMethod.GET)
  protected ModelAndView afficherLogs(HttpServletRequest request) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    if (request.getParameter("recherche") != null) {
      attributsRequete.put(LISTE_LOGS,
          LogsConnexion.getListeLogsFiltrer(request.getParameter("recherche")));
    } else {
      attributsRequete.put(LISTE_LOGS, LogsConnexion.getListeLogs());
    }
    return new ModelAndView(VUE, attributsRequete);
  }

  /**
   * Afficher logs recherche.
   *
   * @param request la requete
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/AdministrateurSysteme/AfficherLogs", method = RequestMethod.POST)
  protected ModelAndView afficherLogsRecherche(HttpServletRequest request) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    final String choix = request.getParameter(CHOIX_LOG);
    attributsRequete.put(CHOIX_LOG, request.getParameter(CHOIX_LOG));
    attributsRequete.put(LISTE_LOGS, LogsConnexion.getListeLogs());
    if (choix != null) {
      attributsRequete.put("logSelection", LogsConnexion.getLogFromName(choix));
    }
    return new ModelAndView(VUE, attributsRequete);
  }

}
