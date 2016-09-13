package fr.eseo.atribus.dao;

import static org.testng.Assert.assertEquals;

import fr.eseo.atribus.entities.Competence;
import fr.eseo.atribus.entities.Examen;
import fr.eseo.atribus.entities.Matiere;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.UUID;

public class ExamenDaoImplTest {

  MatiereDaoImpl matiereDao;
  ExamenDaoImpl examenDao;
  CompetenceDaoImpl competenceDao;
  Examen examen;
  Competence competence;
  String nom;
  Matiere matiere;


  /**
   * Initialisation des variables.
   */
  @BeforeTest
  public void beforeTest() {
    this.matiereDao = new MatiereDaoImpl();
    this.examenDao = new ExamenDaoImpl();
    this.examen = new Examen();
    this.competence = new Competence();
    this.nom = UUID.randomUUID().toString();
    this.matiere = this.matiereDao.trouverParId(1);
  }

  @Test(priority = 1)
  public void ajouter() {
    this.examen.setAutoEvaluation(true);
    this.examen.setMatiere(this.matiere);
    this.examen.setNom(this.nom);
    this.examenDao.ajouter(this.examen, this.matiere);
    final Examen examenTrouve = this.examenDao.trouverParNom(this.nom);
    assertEquals(examenTrouve.getAutoEvaluation().booleanValue(), true);
    assertEquals(examenTrouve.getMatiere().getId(), this.matiere.getId());
    assertEquals(examenTrouve.getNom(), this.nom);
  }

  @Test(priority = 2)
  public void modifier() {
    final Examen examenTrouve = this.examenDao.trouverParNom(this.nom);
    final String ancienNom = examenTrouve.getNom();
    this.nom = UUID.randomUUID().toString();
    examenTrouve.setNom(this.nom);
    this.examenDao.update(examenTrouve, ancienNom);
    assertEquals(examenTrouve.getAutoEvaluation().booleanValue(), true);
    assertEquals(examenTrouve.getMatiere().getId(), this.matiere.getId());
    assertEquals(examenTrouve.getNom(), this.nom);
  }

  @Test(priority = 3)
  public void supprimer() {
    final Examen examenTrouve = this.examenDao.trouverParNom(this.nom);;
    this.examenDao.supprimer(examenTrouve);
    assertEquals(examenTrouve.getAutoEvaluation().booleanValue(), true);
    assertEquals(examenTrouve.getMatiere().getId(), this.matiere.getId());
    assertEquals(examenTrouve.getNom(), this.nom);
  }

  @Test(priority = 4)
  public void ajouterEchec() {
    final Examen examenTrouve = this.examenDao.trouverTousLesExamens().get(0);
    this.examenDao.ajouter(examenTrouve, examenTrouve.getMatiere());
    this.examenDao.ajouter(examenTrouve, examenTrouve.getMatiere());
  }

  @Test(priority = 5)
  public void modifierEchec() {
    final Examen examenFaux = this.examenDao.trouverTousLesExamens().get(0);
    examenFaux.setId(0);
    examenFaux.setNom("fail");
    boolean test = false;
    examenFaux.setNom(UUID.randomUUID().toString());
    try {
      this.examenDao.update(examenFaux, UUID.randomUUID().toString());
    } catch (final Exception exc) {
      test = true;
    }
    assertEquals(test, true);
    examenFaux.setId(34567);
    examenFaux.setNom(UUID.randomUUID().toString());
    try {
      this.examenDao.update(examenFaux, UUID.randomUUID().toString());
    } catch (final Exception exc) {
      test = true;
    }
    assertEquals(test, true);
  }

  @Test(priority = 6)
  public void supprimerEchech() {
    final Examen examenFaux = this.examenDao.trouverTousLesExamens().get(0);
    examenFaux.setId(23456);
    examenFaux.setNom("fail");
    examenFaux.setNom(UUID.randomUUID().toString());
    boolean test = false;
    try {
      this.examenDao.supprimer(examenFaux);
    } catch (final Exception exc) {
      test = true;
    }
    assertEquals(test, true);
  }

}
