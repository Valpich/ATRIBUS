package fr.eseo.atribus.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Le Filtre FiltreErreurRequete.
 */
public class FiltreErreurRequete implements Filter {

  /** La constante LOGGER. */
  private static final Logger LOGGER =
      Logger.getLogger(FiltreLimiteurRequeteApplication.class.getName());

  /**
   * Constructeur.
   */
  public FiltreErreurRequete() {
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
    if (((HttpServletResponse) response).getStatus() != 200) {
      String adresseIp = ((HttpServletRequest) request).getHeader("X-FORWARDED-FOR");
      if (adresseIp == null) {
        adresseIp = request.getRemoteAddr();
      }
      FiltreErreurRequete.LOGGER.setLevel(Level.WARNING);
      FiltreErreurRequete.LOGGER.severe("L'adresse IP : " + adresseIp + " à généré une erreur : "
          + ((HttpServletResponse) response).getStatus());
    }
    chain.doFilter(request, response);
  }

  /**
   * Initialise le filtre.
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
