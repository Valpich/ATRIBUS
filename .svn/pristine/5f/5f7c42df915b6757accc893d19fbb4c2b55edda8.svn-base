package fr.eseo.atribus.controller;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import fr.eseo.atribus.entities.UtilisateurLdap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

/**
 * La Classe LdapControlleur.
 */
@RestController
public class LdapControlleur {

  /** La constante VUE_CONNEXION. */
  public static final String VUE_CONNEXION = "connexionLDAP";

  /** La constante EXCEPTION. */
  public static final String EXCEPTION = "Exception";

  /** La constante LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(LdapControlleur.class.getName());

  /** La variable ldap template. */
  @Autowired
  private LdapTemplate ldapTemplate;


  /**
   * Afficher authenficiation ldap.
   *
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/AdministrateurSysteme/AuthentificationLDAP", method = RequestMethod.GET)
  public ModelAndView afficherAuthenficiationLdap() {
    return new ModelAndView(VUE_CONNEXION);
  }

  /**
   * Authentification ldap.
   *
   * @param login le login
   * @param password le mot de passe
   * @return Le paramètre model and view
   */
  @RequestMapping(value = "/AdministrateurSysteme/AuthentificationLDAP",
      method = RequestMethod.POST)
  public ModelAndView authentificationLdapt(@RequestParam("login") String login,
      @RequestParam("password") String password) {
    final HashMap<String, Object> attributsRequete = new HashMap<>();
    /* Tentative de connexion sur le LDAP */
    final boolean authentifie = this.ldapTemplate.authenticate("", "(uid=" + login + ")", password);
    String erreur = null;
    if (!authentifie) {
      erreur = "true";
    }
    if (authentifie) {
      attributsRequete.put("utilisateur", this.recupererUtilisateur(login));
      return new ModelAndView("indexLDAP", attributsRequete);
    } else {
      attributsRequete.put("erreur", erreur);
      return new ModelAndView(VUE_CONNEXION, attributsRequete);
    }
  }

  /**
   * Recuperer utilisateur.
   *
   * @param login le login
   * @return Le paramètre utilisateur ldap
   */
  public UtilisateurLdap recupererUtilisateur(String login) {
    return this.ldapTemplate.search(query().base("ou=people").where("uid").is(login),
        new UtilisateurLdapAttributesMapper()).get(0);
  }

  /**
   * Recuper tous les utilisateurs.
   *
   * @return Le paramètre list
   */
  @RequestMapping(value = "/AdministrateurSysteme/ListerLDAP", method = RequestMethod.GET)
  public List<UtilisateurLdap> recuperTousLesUtilisateurs() {
    return this.ldapTemplate.search(
        query().base("ou=people").where("objectClass").is("inetOrgPerson"),
        new UtilisateurLdapAttributesMapper());
  }

  /**
   * La Classe UtilisateurLdapAttributesMapper.
   */
  private class UtilisateurLdapAttributesMapper implements AttributesMapper<UtilisateurLdap> {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.ldap.core.AttributesMapper#mapFromAttributes(javax.naming.directory.
     * Attributes)
     */
    @Override
    public UtilisateurLdap mapFromAttributes(Attributes attrs) throws NamingException {
      final UtilisateurLdap utilisateurLdap = new UtilisateurLdap();
      try {
        utilisateurLdap.setCn((String) attrs.get("cn").get());
      } catch (final NullPointerException npe) {
        LOGGER.log(Level.INFO, EXCEPTION, npe);
      }
      try {
        utilisateurLdap.setMail((String) attrs.get("mail").get());
      } catch (final NullPointerException npe) {
        LOGGER.log(Level.INFO, EXCEPTION, npe);
      }
      try {
        utilisateurLdap.setGivenName((String) attrs.get("givenName").get());
      } catch (final NullPointerException npe) {
        LOGGER.log(Level.INFO, EXCEPTION, npe);
      }
      try {
        utilisateurLdap.setObjectClass((String) attrs.get("objectClass").get());
      } catch (final NullPointerException npe) {
        LOGGER.log(Level.INFO, EXCEPTION, npe);
      }
      try {
        utilisateurLdap.setUserpassword(new String((byte[]) attrs.get("userpassword").get()));
      } catch (final NullPointerException npe) {
        LOGGER.log(Level.INFO, EXCEPTION, npe);
      }
      try {
        utilisateurLdap.setSn((String) attrs.get("sn").get());
      } catch (final NullPointerException npe) {
        LOGGER.log(Level.INFO, EXCEPTION, npe);
      }
      try {
        utilisateurLdap.setUid((String) attrs.get("uid").get());
      } catch (final NullPointerException npe) {
        LOGGER.log(Level.INFO, EXCEPTION, npe);
      }
      return utilisateurLdap;
    }
  }

  /**
   * Accesseur en écriture du paramètre ldap template.
   *
   * @param ldapTemplate le nouveau paramètre ldap template
   */
  public void setLdapTemplate(LdapTemplate ldapTemplate) {
    this.ldapTemplate = ldapTemplate;
  }

}
