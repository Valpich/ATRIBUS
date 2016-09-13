package fr.eseo.atribus.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import fr.eseo.atribus.entities.Competence;
import fr.eseo.atribus.entities.CompetenceValidable;
import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Matiere;
import fr.eseo.atribus.entities.UniteEnseignement;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class CompetenceDaoImplTest {
  CompetenceDaoImpl competenceDao;
  UeDaoImpl ueDao;
  EleveDaoImpl eleveDao;
  MatiereDaoImpl matiereDao;
  
  @BeforeClass
  public void beforeClass() {
    this.competenceDao = new CompetenceDaoImpl();
    this.ueDao = new UeDaoImpl();
    this.eleveDao = new EleveDaoImpl();
    this.matiereDao = new MatiereDaoImpl();
  }

  @Test(priority = 1)
  public void trouverParId() {
    assertNotNull(this.competenceDao.trouverParId(1));
  }

  @Test(priority = 2)
  public void trouverParNom() {
    assertNotNull(this.competenceDao.trouverParNom("Biologie"));
  }


  @Test(priority = 3)
  public void trouverUniqueParNom() {
    assertNotNull(this.competenceDao.trouverUniqueParNom("Biologie"));
  }

  @Test(priority = 4)
  public void trouverToutesLesCompetences() {
    assertNotNull(this.competenceDao.trouverToutesLesCompetences());
  }

  @Test(priority = 5)
  public void modifierCompetenceProfondeur() {

    Competence competence = new Competence();

    competence = this.competenceDao.trouverParId(1);

    this.competenceDao.modifierCompetence(competence, 2);

  }

  @Test(priority = 6)
  public void modifierCompetenceNom() {

    Competence competence = new Competence();

    competence = this.competenceDao.trouverParId(1);
    this.competenceDao.modifierCompetence(competence, "Test");

    assertEquals(this.competenceDao.trouverParId(1).getNom(), "Test", "Le nom à été modifié");

    competence = this.competenceDao.trouverParId(1);
    this.competenceDao.modifierCompetence(competence, "Travail en équipe");

    assertEquals(this.competenceDao.trouverParId(1).getNom(), "Travail en équipe",
        "Le nom à été modifié");

  }


  @Test(priority = 7)
  public void trouverParProfondeur() {
    try{
      this.competenceDao.trouverParProfondeur(49);
    }catch(Exception e){
      
    }
    assertNotNull(this.competenceDao.trouverParProfondeur(1));
  }

  @Test(priority = 8)
  public void ajouterCompetence() {

    final Competence competence = new Competence();

    competence.setNom("CompetenceTest");
    competence.setIdParent(1);
    competence.setProfondeur(1);

    this.competenceDao.ajouterCompetence(competence);
    assertNotNull(this.competenceDao.trouverParId(competence.getId()));
    competence.setIdParent(-1);
    competence.setProfondeur(null);
    try{
      this.competenceDao.ajouterCompetence(competence);
    }catch(Exception e){
      
    }
  }

  @Test(priority = 9)
  public void supprimerCompetence() {

    Competence competence = new Competence();
    try{
      this.competenceDao.trouverParNom("-1");
    }catch(Exception e){
      
    }
    competence = this.competenceDao.trouverUniqueParNom("CompetenceTest");
    assertNotNull(this.competenceDao.trouverParNom("CompetenceTest"));
    try{
      this.competenceDao.supprimerCompetenceParNom("swdxfhgcjhvj;bk:;n");
    }catch(Exception e){
      
    }
    this.competenceDao.supprimerCompetenceParNom(competence.getNom());
    assertNull(this.competenceDao.trouverUniqueParNom("CompetenceTest"));

  }

  @Test(priority = 10)
  public void recupererAssociationCompetenceUe() {
    try{
      this.competenceDao.recupererAssociationCompetenceUe(-1);
    }catch(Exception e){
      
    }
    assertNotNull(this.competenceDao.recupererAssociationCompetenceUe(1));
  }

  @Test(priority = 11)
  public void trouverIdParNomTest() {
    try{
      final int id = this.competenceDao.trouverIdParNom("");

    }catch(Exception e){
     
    }
    final int id = this.competenceDao.trouverIdParNom("Travail en équipe");
    assertEquals(id, 1);
  }

  @Test(priority = 12)
  public void modifierCompetenceDeux() {

    final Competence nouvelleCompetence = new Competence();
    final Competence ancienneCompetence = new Competence();

    nouvelleCompetence.setNom("Travail en équipe");
    nouvelleCompetence.setIdParent(1);
    nouvelleCompetence.setProfondeur(1);

    ancienneCompetence.setId(1);

    try {
      this.competenceDao.modifierCompetence(nouvelleCompetence, ancienneCompetence);
    } catch (Exception e) {
      ancienneCompetence.setId(1);
    }
    this.competenceDao.modifierCompetence(nouvelleCompetence, ancienneCompetence);

    final int profondeur =
        this.competenceDao.trouverUniqueParNom("Travail en équipe").getProfondeur();

    assertEquals(profondeur, 1);

  }

  @Test(priority = 13)
  public void associerCompetenceNiveauUe() {

    final Competence competence = new Competence();
    final UniteEnseignement ue = new UniteEnseignement();

    competence.setId(1);
    ue.setNom("Mathématiques");
    ue.setId(1);
    boolean excpt = false;

    try {
      this.competenceDao.associerCompetenceNiveauUe(competence, ue);
    } catch (final DaoException exception) {
      excpt = true;
    }

    assertEquals(excpt, true);

  }

  @Test(priority = 14)
  public void recupererProfilEleve() {

    Map<Competence, Integer> profil;
    final Eleve eleve = this.eleveDao.trouverParId(1);

    profil = this.competenceDao.recupererProfilEleve(eleve);

    assertNotNull(profil);


  }

  @Test(priority = 15)
  public void recupererCompetenceUe() {

    final UniteEnseignement ue = new UniteEnseignement();

    ue.setId(1);

    assertNotNull(this.competenceDao.listeCompetencesUe(ue));

  }

  @Test(priority = 15)
  public void recupererCompetenceMatiere() {

    final Matiere matiere = new Matiere();

    matiere.setId(1);

    assertNotNull(this.competenceDao.listeCompetencesMatiere(matiere));

  }


  @Test(priority = 16)
  public void modifierCompetence() {

    final Competence competence = this.competenceDao.trouverParId(1);
    this.competenceDao.modifierNomCompetence(competence);

  }
  
  @Test(priority = 17)
  public void validerCompetenceMatiere() {
    Competence competence =  this.competenceDao.trouverParId(1);
    final CompetenceValidable competenceValidable =new CompetenceValidable(competence);
    Matiere matiere = this.matiereDao.trouverParId(1);
    this.competenceDao.valider(matiere, competenceValidable);
  }
  
  @Test(priority = 17)
  public void validerCompetenceUe() {
    Competence competence =  this.competenceDao.trouverParId(1);
    final CompetenceValidable competenceValidable =new CompetenceValidable(competence);
    UniteEnseignement ue = this.ueDao.trouverUeParId(1);
    this.competenceDao.valider(ue, competenceValidable);
    try{
      competenceValidable.setId(-1);
      this.competenceDao.valider(ue, competenceValidable);
      }catch (Exception e){
        
      }
  }
}
