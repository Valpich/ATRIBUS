package fr.eseo.atribus.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.entities.Utilisateur;

import org.powermock.api.mockito.PowerMockito;
import org.testng.annotations.Test;

public class UtilisateurDaoImplTest {

  UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl();


  @Test(priority = 1)
  public void ajouter() {

    final Utilisateur utilisateur = new Utilisateur();

    utilisateur.setLogin("user");
    utilisateur.setPassword("password");
    utilisateur.setNom("Utilisateur 1");
    utilisateur.setPrenom("Ldcr");
    utilisateur.setEmail("user@reseau.eseo.fr");

    this.utilisateurDao.ajouter(utilisateur);

    assertEquals(this.utilisateurDao.trouverParLogin("user").getNom(), new String("Utilisateur 1"),
        "L'utilisateur a bien été trouvé dans la BDD.");

  }

  @Test(priority = 2)
  public void trouver() throws Exception {

    final UtilisateurDaoImpl mockedUtilisateurDao = PowerMockito.spy(new UtilisateurDaoImpl());

    final String requeteSql = "SELECT * FROM utilisateur WHERE login = ?";
    final String login = "admin";

    PowerMockito.doReturn(1).when(mockedUtilisateurDao, "trouver", requeteSql, login);

  }

  @Test(priority = 3)
  public void trouverIdParLogin() {
    assertEquals(this.utilisateurDao.trouverIdParLogin("admin"), 1,
        "L'ID en fonction du login a été trouvé.");
  }

  @Test(priority = 4)
  public void trouverParLogin() {
    assertEquals(this.utilisateurDao.trouverParLogin("admin").getPrenom(), "Administrateur",
        "Le nom en fonction du login et du password a été trouvé.");
  }

  @Test(priority = 5)
  public void trouverParLoginPassword() {
    assertEquals(this.utilisateurDao.trouverParLoginPassword("user", "password").getPrenom(),
        "Ldcr", "Le prenom en fonction du login a été trouvé.");
  }

  @Test(priority = 6)
  public void trouverParNomPrenom() {
    assertEquals(this.utilisateurDao.trouverParNomPrenom("DEMAY", "Alexis").getEmail(),
        "alexis.demay@reseau.eseo.fr", "L'email en fonction du login a été trouvé.");
  }

  @Test(priority = 7)
  public void supprimerParLoginFail() {
    boolean test = false;
    try {
      this.utilisateurDao.supprimerParLogin("");
    } catch (final DaoException daoe) {
      test = true;
    }
    assertEquals(test, true, "Il est bien impossible de supprimer un utilisateut non présent.");
  }


  @Test(priority = 8)
  public void supprimerParLoginFailDeux() {
    boolean test = false;
    try {
      this.utilisateurDao.supprimerParLogin(null);
    } catch (final DaoException daoe) {
      test = true;
    }
    assertEquals(test, true, "Il est bien impossible de supprimer un utilisateut non présent.");
  }

  @Test(priority = 9)
  public void supprimerParEmail() {
    assertEquals(this.utilisateurDao.supprimerParLogin("user"), 1,
        "L'utilisateur a bien été supprimé.");
  }

  @Test(priority = 10)
  public void trouverListe() {
    assertNotNull(this.utilisateurDao.recupererListe());
  }

  @Test(priority = 11)
  public void ajouterFail() {

    final Utilisateur utilisateur = new Utilisateur();

    utilisateur.setLogin(null);
    utilisateur.setPassword("password");
    utilisateur.setNom("Utilisateur 1");
    utilisateur.setPrenom("Ldcr");
    utilisateur.setEmail("user@reseau.eseo.fr");
    boolean test = false;
    try {
      this.utilisateurDao.ajouter(utilisateur);
    } catch (final DaoException daoe) {
      test = true;
    }
    assertEquals(test, true, "L'utilisateur n'a pas été ajouté dans la BDD.");
  }

  @Test(priority = 12)
  public void modifier() {

    final Utilisateur utilisateur = this.utilisateurDao.trouverParLogin("admin");
    boolean test = false;
    try {
      this.utilisateurDao.modifier("admin", utilisateur);
    } catch (final DaoException daoe) {
      test = true;
    }
    assertEquals(test, false, "L'utilisateur n'a pas été ajouté dans la BDD.");
  }

  @Test(priority = 13)
  public void modifierFail() {
    final Utilisateur utilisateur = this.utilisateurDao.trouverParLogin("admin");
    boolean test = false;
    try {
      this.utilisateurDao.modifier(null, utilisateur);
    } catch (final DaoException daoe) {
      test = true;
    }
    assertEquals(test, true, "L'utilisateur n'a pas été ajouté dans la BDD.");
  }

  @Test(priority = 14)
  public void modifierFailDeux() {
    final Utilisateur utilisateur = this.utilisateurDao.trouverParLogin("admin");
    boolean test = false;
    try {
      utilisateur.setPassword(null);
      this.utilisateurDao.modifier(null, utilisateur);
    } catch (final DaoException daoe) {
      test = true;
    }
    assertEquals(test, true, "L'utilisateur n'a pas été ajouté dans la BDD.");
  }

  @Test(priority = 15)
  public void modifierFailTrois() {
    final Utilisateur utilisateur = this.utilisateurDao.trouverParLogin("admin");
    boolean test = false;
    try {
      utilisateur.setPassword(null);
      utilisateur.setLogin(null);
      this.utilisateurDao.modifier(null, utilisateur);
    } catch (final DaoException daoe) {
      test = true;
    }
    assertEquals(test, true, "L'utilisateur n'a pas été ajouté dans la BDD.");
  }

  @Test(priority = 16)
  public void modifierFailQuatre() {
    final Utilisateur utilisateur = this.utilisateurDao.trouverParLogin("admin");
    boolean test = false;
    try {
      utilisateur.setLogin(null);
      this.utilisateurDao.modifier(null, utilisateur);
    } catch (final DaoException daoe) {
      test = true;
    }
    assertEquals(test, true, "L'utilisateur n'a pas été ajouté dans la BDD.");
  }
}
