package fr.eseo.atribus.filters;

import fr.eseo.atribus.entities.AdminSyst;
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
 * Servlet Filter implementation class FiltreDirecteurEtude.
 */
public class FiltreAdminSyst implements Filter {

  /** La constante ACCES_PUBLIC. */
  public static final String ACCES_PUBLIC = "/errorAccess.jsp";

  /** La constante ATT_SESSION_USER. */
  public static final String ATT_SESSION_USER = "sessionUtilisateur";

  /**
   * Default constructor.
   */
  public FiltreAdminSyst() {
    super();
  }

  /**
   * Destroy.
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
   * @param request le request
   * @param response le response
   * @param chain le chain
   * @throws IOException Indique qu'une I/O exception est arrivée.
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
    if (session.getAttribute(FiltreAdminSyst.ATT_SESSION_USER) == null) {
      reponse.sendRedirect(requete.getContextPath() + FiltreAdminSyst.ACCES_PUBLIC);
    } else {
      @SuppressWarnings("unchecked")
      final List<Utilisateur> listeUtilisateur =
          (List<Utilisateur>) session.getAttribute(FiltreAdminSyst.ATT_SESSION_USER);
      boolean autorisation = false;
      for (int i = 0; i < listeUtilisateur.size(); i++) {
        if (listeUtilisateur.get(i).getClass().equals(AdminSyst.class)) {
          autorisation = true;
        }
      }
      if (autorisation) {
        chain.doFilter(request, response);
      } else {
        reponse.sendRedirect(requete.getContextPath() + FiltreAdminSyst.ACCES_PUBLIC);
      }
    }
  }

  /**
   * Inits the.
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
