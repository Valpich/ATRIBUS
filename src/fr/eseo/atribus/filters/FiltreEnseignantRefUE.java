package fr.eseo.atribus.filters;

import fr.eseo.atribus.entities.EnseignantRefUe;
import fr.eseo.atribus.entities.Utilisateur;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Le filtre FiltreDirecteurEtude.
 */
public class FiltreEnseignantRefUE implements Filter {

  /** La constante ACCES_PUBLIC. */
  public static final String ACCES_PUBLIC = "/errorAccess.jsp";

  /** La constante ATT_SESSION_USER. */
  public static final String ATT_SESSION_USER = "sessionUtilisateur";

  /**
   * Constructeur.
   */
  public FiltreEnseignantRefUE() {
    super();
  }

  /**
   * Destruction.
   *
   * @see Filter#destroy().
   */
  @Override
  public void destroy() {
    return;
  }

  /**
   * Do filter.
   *
   * @param request la request
   * @param response la response
   * @param chain la chain
   * @throws IOException Indique qu'une I/O exception est arrivée
   * @throws ServletException de type servlet exception
   * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain).
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    final HttpServletRequest requete = (HttpServletRequest) request;
    final HttpServletResponse reponse = (HttpServletResponse) response;

    /* Récupération de la session depuis la requête */
    final HttpSession session = requete.getSession();

    /**
     * Si l'objet utilisateur n'existe pas dans la session en cours, alors l'utilisateur n'est pas
     * connecté.
     */
    if (session.getAttribute(FiltreEnseignantRefUE.ATT_SESSION_USER) == null) {
      reponse.sendRedirect(requete.getContextPath() + FiltreEnseignantRefUE.ACCES_PUBLIC);
    } else {
      @SuppressWarnings("unchecked")
      final List<Utilisateur> listeUtilisateur =
          (List<Utilisateur>) session.getAttribute(FiltreEnseignantRefUE.ATT_SESSION_USER);
      boolean autorisation = false;
      for (int i = 0; i < listeUtilisateur.size(); i++) {
        if (listeUtilisateur.get(i).getClass().equals(EnseignantRefUe.class)) {
          autorisation = true;
        }
      }
      if (autorisation) {
        chain.doFilter(request, response);
      } else {
        reponse.sendRedirect(requete.getContextPath() + FiltreEnseignantRefUE.ACCES_PUBLIC);
      }
    }
  }

  /**
   * Initialiase le filtre.
   *
   * @param fiConfig le fi config
   * @throws ServletException de type servlet exception
   * @see Filter#init(FilterConfig).
   */
  @Override
  public void init(FilterConfig fiConfig) throws ServletException {
    return;
  }

}
