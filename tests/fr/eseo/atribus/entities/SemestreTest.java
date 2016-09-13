package fr.eseo.atribus.entities;

import static org.testng.Assert.assertEquals;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.List;

public class SemestreTest {

  Semestre semestre = new Semestre();
  Promotion promotion;
  List<UniteEnseignement> ue;
  int numeroSemestre = 1;
  DateTime dateDebut = new DateTime(2016, 01, 01, 12, 00);
  DateTime dateFin = new DateTime(2016, 02, 01, 12, 00);

  @Test
  public void getDateDebut() {
    this.semestre.setDateDebut(this.dateDebut);
    assertEquals(this.semestre.getDateDebut(), this.dateDebut);
  }

  @Test
  public void getDateFin() {
    this.semestre.setDateFin(this.dateFin);
    assertEquals(this.semestre.getDateFin(), this.dateFin);
  }

  @Test
  public void getNumeroSemestre() {
    this.semestre.setNumeroSemestre(this.numeroSemestre);
    assertEquals(this.semestre.getNumeroSemestre(), this.numeroSemestre);
  }

  @Test
  public void getPromotion() {
    this.semestre.setPromotion(this.promotion);
    assertEquals(this.semestre.getPromotion(), this.promotion);
  }

  @Test
  public void getUniteEnseignement() {
    this.semestre.setUniteEnseignement(this.ue);
    assertEquals(this.semestre.getUniteEnseignement(), this.ue);
  }

  @Test
  public void getIdSemestre() {
    this.semestre.setId(this.numeroSemestre);
    assertEquals(this.semestre.getId(), this.numeroSemestre);
  }

  @Test
  public void setIdSemestre() {
    this.semestre.setId(this.numeroSemestre);
    assertEquals(this.semestre.getId(), this.numeroSemestre);
  }



  @Test
  public void testtoString() {

    final String string = "Semestre [promotion=null, uniteEnseignement=null, numeroSemestre=0, "
        + "dateDebut=null, dateFin=null";

    this.semestre = new Semestre();


    assertEquals(this.semestre.toString(), string);
  }


}
