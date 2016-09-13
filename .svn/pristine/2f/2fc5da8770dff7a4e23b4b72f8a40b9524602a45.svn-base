package fr.eseo.atribus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Servlet d'implementation de la classe IndexController.
 */
@Controller
public class IndexController {

  /** La constante INDEX. */
  private static final String INDEX = "index";

  /** La constante ATT_SESSION_USER. */
  public static final String ATT_SESSION_USER = "sessionUtilisateur";

  /**
   * Afficher l'index.
   *
   * @param request la requete
   * @return .
   */
  @RequestMapping(value = { "/index", "/" }, method = RequestMethod.GET)
  public ModelAndView afficherIndex(HttpServletRequest request) {
    if (request.getSession().getAttribute(ATT_SESSION_USER) == null) {
      return new ModelAndView("redirect:/connexion");
    } else {
      return new ModelAndView(INDEX);
    }
  }


}
