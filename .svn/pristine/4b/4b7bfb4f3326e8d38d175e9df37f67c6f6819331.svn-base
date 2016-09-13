package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;

import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PromotionTest {
  Promotion promotion = new Promotion();
  Eleve eleve;
  Semestre semestre;
  String nom;
  DateTime anneeDiplome;

  @Test
  public void getAnneeDiplome() {
    this.promotion.setAnneeDiplome(this.anneeDiplome);
    assertEquals(this.promotion.getAnneeDiplome(), this.anneeDiplome);
  }

  @Test
  public void getNom() {
    this.promotion.setNom(this.nom);
    assertEquals(this.promotion.getNom(), this.nom);
  }

  @Test
  public void getId() {
    final int id = 1;
    this.promotion.setId(id);
    assertEquals(this.promotion.getId(), id);
  }

  @Test
  public void testMethodeToString() {

    final String expected = "Promotion [semestre=, nom=PromoTest, anneeDiplome=null]";

    final String nom = "PromoTest";
    final Promotion promotion = new Promotion();
    final int id = 1;

    promotion.setId(id);
    promotion.setNom(nom);

    Assert.assertEquals(promotion.toString(), expected);

  }

}


