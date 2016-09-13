package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.List;

public class RessourceTest {
  Ressource ressource = new Ressource();
  String nom;
  String type;
  int id;
  Matiere matiere;
  List<Competence> competences;
  String path;
  DateTime datePublication;

  @Test
  public void getDatePublication() {
    this.ressource.setDatePublication(this.datePublication);
    assertEquals(this.ressource.getDatePublication(), this.datePublication);
  }

  @Test
  public void getNom() {
    this.ressource.setNom(this.nom);
    assertEquals(this.ressource.getNom(), this.nom);
  }

  @Test
  public void getType() {
    this.ressource.setType(this.type);
    assertEquals(this.ressource.getType(), this.type);
  }

  @Test
  public void getMatiere() {
    this.ressource.setMatiere(this.matiere);
    assertEquals(this.ressource.getMatiere(), this.matiere);
  }


  @Test
  public void getPath() {
    this.ressource.setPath(this.path);
    assertEquals(this.ressource.getPath(), this.path);
  }

  @Test
  public void getCompetences() {
    this.ressource.setCompetences(this.competences);
    assertEquals(this.ressource.getCompetences(), this.competences);
  }

  @Test
  public void testtoString() {
    final String string = "Ressource [id=" + this.id + ", matiere=" + this.matiere + ", nom="
        + this.nom + ", type=" + this.type + ", path=" + this.path + ", datePublication="
        + this.datePublication + ", competences=" + this.competences + "]";
    assertEquals(this.ressource.toString(), string);
  }
}
