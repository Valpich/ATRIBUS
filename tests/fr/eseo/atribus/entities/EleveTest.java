package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EleveTest {

  Eleve eleve;

  @BeforeTest
  public void beforeTest() {
    this.eleve = new Eleve();
  }

  @Test
  public void constructeurEleve() {

    final Utilisateur utilisateur = new Utilisateur();

    utilisateur.setNom("NomTest");
    this.eleve = new Eleve(utilisateur);

    assertNotNull(this.eleve);
    assertEquals(this.eleve.getNom(), "NomTest");

  }

  @Test
  public void setGetPromotion() {

    final Promotion promotion = new Promotion();

    promotion.setNom("PromotionTest");
    this.eleve.setPromotion(promotion);

    assertEquals(this.eleve.getPromotion().getNom(), "PromotionTest");

  }

  @Test
  public void setGetIdEleve() {
    this.eleve.setIdEleve(1);
    assertEquals(this.eleve.getIdEleve(), 1);
  }

  @Test
  public void methodToString() {
    assertEquals(this.eleve.toString(), this.eleve.toString());
  }

}
