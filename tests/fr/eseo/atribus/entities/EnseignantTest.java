package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class EnseignantTest {

  List<Matiere> listMatiere;
  Enseignant enseignant;
  Utilisateur utilisateur;

  /**
   * Instanciation des variables avant les tests.
   */
  @BeforeClass
  public void beforeClass() {
    this.listMatiere = new ArrayList<>();
    this.enseignant = new Enseignant();
    this.utilisateur = new Utilisateur();
  }

  @Test
  public void enseignantConstructeur() {
    assertNotNull(this.enseignant);
  }

  @Test
  public void enseignantUtilisateur() {
    assertNotNull(this.utilisateur);
  }

  @Test
  public void getEnseigneMatiere() {
    this.enseignant.setEnseigneMatiere(this.listMatiere);
    assertNotNull(this.enseignant.getEnseigneMatiere());
  }

  @Test
  public void getIdEnseignant() {
    this.enseignant.setId(5);
    assertEquals(this.enseignant.getId(), 5);
  }

  @Test
  public void setEnseigneMatiere() {
    this.enseignant.setEnseigneMatiere(this.listMatiere);
    assertNotNull(this.enseignant.getEnseigneMatiere());
  }

  @Test
  public void setIdEnseignant() {
    this.enseignant.setId(4);
    assertEquals(this.enseignant.getId(), 4);
  }

  @Test
  public void enseignantToString() {

    final String toStringEnseignant = "Enseignant [idEnseignant=0, enseigneMatiere=null]";

    this.enseignant = new Enseignant();

    assertEquals(this.enseignant.toString(), toStringEnseignant);

  }
}
