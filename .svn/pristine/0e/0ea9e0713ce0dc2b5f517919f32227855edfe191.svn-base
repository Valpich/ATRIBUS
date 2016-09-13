package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EnseignantRefMatiereTest {

  EnseignantRefMatiere enseignant;

  @BeforeTest
  public void beforeTest() {
    this.enseignant = new EnseignantRefMatiere();
    assertNotNull(this.enseignant);
  }


  @Test
  public void getId() {
    this.enseignant.setId(1);
    assertEquals(this.enseignant.getId(), 1);
  }

  @Test
  public void getIdEnseignantRefMatiere() {
    this.enseignant.setIdEnseignantRefMatiere(1);
    assertEquals(this.enseignant.getIdEnseignantRefMatiere(), 1);
  }

  @Test
  public void getMatiere() {

    final Matiere matiere = new Matiere();

    matiere.setNom("NomTest");
    this.enseignant.setMatiere(matiere);

    assertEquals(this.enseignant.getMatiere().getNom(), "NomTest");

  }

  @Test
  public void methodToString() {
    assertEquals(this.enseignant.toString(), this.enseignant.toString());
  }
}
