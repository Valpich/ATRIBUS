package fr.eseo.atribus.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import fr.eseo.atribus.entities.Competence;
import fr.eseo.atribus.entities.Matiere;
import fr.eseo.atribus.entities.Ressource;
import fr.eseo.atribus.entities.Utilisateur;

import org.joda.time.DateTime;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RessourceDaoImplTest {

  RessourceDaoImpl ressourceDao;
  CompetenceDaoImpl competenceDao;
  MatiereDaoImpl matiereDao;
  Ressource nouvelleRessource;
  Ressource ressourceRecherche;

  /**
   * Initisialisation des variable avant les tests.
   */
  @BeforeTest
  public void beforeTest() {
    this.ressourceDao = new RessourceDaoImpl();
    this.competenceDao = new CompetenceDaoImpl();
    this.matiereDao = new MatiereDaoImpl();
    this.nouvelleRessource = new Ressource();
    this.ressourceRecherche = new Ressource();
  }

  @Test(priority = 1)
  public void trouverParNomEtMatiere() {

    Ressource ressource = new Ressource();

    final String nomRessource = "BDD";
    final String idMatiere = "1";

    ressource = this.ressourceDao.trouverParNomEtMatiere(nomRessource, idMatiere);

    assertEquals(ressource.getId(), 1);
    assertEquals(ressource.getPath(), "BDD.sql");
    assertEquals(ressource.getNom(), "BDD");
    assertEquals(ressource.getType(), "sql");
    assertEquals(ressource.getMatiere().getId(), 1);

  }

  @Test(priority = 2)
  public void trouverParNomEtType() {

    Ressource ressource = new Ressource();

    final String nomRessource = "BDD";
    final String type = "sql";

    ressource = this.ressourceDao.trouverParNomEtType(nomRessource, type);

    assertEquals(ressource.getId(), 1);
    assertEquals(ressource.getPath(), "BDD.sql");
    assertEquals(ressource.getNom(), "BDD");
    assertEquals(ressource.getType(), "sql");
    assertEquals(ressource.getMatiere().getId(), 1);

  }

  @Test(priority = 3)
  public void trouverTouteLesRessources() {

    List<Ressource> ressource = new ArrayList<>();

    ressource = this.ressourceDao.trouverToutesLesRessources();

    assertEquals(ressource.get(0).getId(), 1);
    assertNotNull(ressource.get(0).getPath());
    assertNotNull(ressource.get(0).getNom());
    assertNotNull(ressource.get(0).getType());
    assertNotNull(ressource.get(0).getMatiere().getId());

  }



  @Test(priority = 4)
  public void trouverParPath() {

    final Ressource ressource = new Ressource();
    final String nomRessource = "BDD";
    final String type = "sql";
    String path;

    ressource.setNom(nomRessource);
    ressource.setType(type);

    path = this.ressourceDao.trouverPath(ressource);

    assertEquals(path, "BDD.sql");

  }

  @Test(priority = 5)
  public void modifierRessource() {

    final String nouveauNomRessource = "newBDD";

    Ressource ancienneRessource = new Ressource();

    ancienneRessource = this.ressourceDao.trouverParNomEtType("BDD", "sql");
    ancienneRessource.setNom(nouveauNomRessource);

    this.ressourceDao.modifier(ancienneRessource);

    this.nouvelleRessource = this.ressourceDao.trouverParNomEtType(nouveauNomRessource, "sql");

    assertEquals(this.nouvelleRessource.getNom(), "newBDD", "Le nom est correct.");

    // On renomme la ressource comme avant
    ancienneRessource = this.ressourceDao.trouverParNomEtType("newBDD", "sql");
    ancienneRessource.setNom("BDD");
    this.ressourceDao.modifier(ancienneRessource);

  }

  @Test(priority = 6)
  public void ajouterRessource() {

    try {
      final Ressource ressource = new Ressource();
      Matiere matiere = new Matiere();

      final String path = "Test.txt";

      matiere = this.matiereDao.trouverParNom("Probabilités");

      ressource.setNom("Test");
      ressource.setType("txt");
      ressource.setMatiere(matiere);
      ressource.setDatePublication(DateTime.now().toDateTime());
      final List<Competence> competences = new ArrayList<>();
      competences.add(this.competenceDao.trouverParId(1));
      ressource.setCompetences(competences);
      this.ressourceDao.ajouter(ressource, path);

      this.ressourceRecherche = this.ressourceDao.trouverParNomEtType("Test", "txt");

      assertEquals(this.ressourceRecherche.getNom(), "Test");
      assertEquals(this.ressourceRecherche.getType(), "txt");
    } catch (final Exception exc) {
      exc.getMessage();
    }

  }

  @Test(priority = 7)
  public void trouverToutesLesRessourcesCompetences() {
    List<Ressource> ressourcesCompetence = new ArrayList<>();
    ressourcesCompetence = this.ressourceDao
        .trouverToutesLesRessourcesCompetence(this.competenceDao.trouverParId(1).getNom());
    assertNotNull(ressourcesCompetence.get(0).getId());
    assertNotNull(ressourcesCompetence.get(0).getPath());
    assertNotNull(ressourcesCompetence.get(0).getNom());
    assertNotNull(ressourcesCompetence.get(0).getType());
    assertNotNull(ressourcesCompetence.get(0).getMatiere().getId());
  }


  @Test(priority = 8)
  public void supprimerRessource() {

    Ressource ressource = new Ressource();
    final Matiere matiere = new Matiere();

    matiere.setId(1);

    ressource = this.ressourceDao.trouverParNomEtType("Test", "txt");

    this.ressourceDao.supprimer(ressource);

    this.ressourceRecherche = this.ressourceDao.trouverParNomEtType("Test", "txt");

    assertNull(this.ressourceRecherche);
  }

  @Test(priority = 9)
  public void consulter() {
    Ressource ressource = new Ressource();

    ressource = this.ressourceDao.trouverToutesLesRessources().get(0);
    Utilisateur utilisateur = new Utilisateur();
    utilisateur.setId(1);
    this.ressourceDao.consulter(ressource, utilisateur);
  }
  
  @Test(priority = 10)
  public void   listerConsultations() {
    assertNotNull(this.ressourceDao.listerConsultations());
  }
}
