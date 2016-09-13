package fr.eseo.atribus.dao;

import fr.eseo.atribus.entities.AdminSyst;

import java.util.List;

/**
 * L'Interface AdminSystDao.
 */
public interface AdminSystDao {

  /**
   * Trouver par login hash.
   *
   * @param login le login
   * @param hash le hash
   * @return Le paramètre admin syst
   */
  AdminSyst trouverParLoginHash(String login, String hash);

  /**
   * Ajouter.
   *
   * @param adminSyst l'admin syst
   * @return Le paramètre admin syst
   */
  AdminSyst ajouter(AdminSyst adminSyst);

  /**
   * Supprimer.
   *
   * @param adminSyst l'admin syst
   * @return Le paramètre int
   */
  int supprimer(AdminSyst adminSyst);

  /**
   * Recuperer liste.
   *
   * @return Le paramètre list
   */
  List<AdminSyst> recupererListe();
}
