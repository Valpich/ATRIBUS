package fr.eseo.atribus.controller;

import fr.eseo.atribus.entities.Utilisateur;
import fr.eseo.atribus.forms.SupprimerNotificationForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * La Classe NotificationController.
 */
@Controller
public class NotificationController {

  /**
   * Afficher les notifications.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/Notifications", method = RequestMethod.GET)
  protected ModelAndView afficherNotifications() {
    return new ModelAndView("gererNotification");
  }

  /**
   * Supprimer des notifications.
   *
   * @param request la requete
   * @param coches les coches
   * @return Le paramètre model and view
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/Notifications", method = RequestMethod.POST)
  protected ModelAndView supprimerNotifications(HttpServletRequest request,
      @RequestParam(value = "table_records", required = false) List<String> coches) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    if (coches != null) {
      final SupprimerNotificationForm form = new SupprimerNotificationForm();
      form.supprimerNotification(coches,
          ((List<Utilisateur>) request.getSession().getAttribute("sessionUtilisateur")).get(0));
      if (!form.getErreurs().isEmpty()) {
        // On charge la liste des erreurs
        attributsRequete.put("erreurs", form.getErreurs());
      } else {
        attributsRequete.put("succes", "true");
      }
    } else {
      attributsRequete.put("vide", "true");
    }
    return new ModelAndView("gererNotification", attributsRequete);
  }

}
