package fr.eseo.atribus.forms;

import static org.testng.Assert.assertEquals;

import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.entities.Eleve;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RepondreExamenFormTest {

  private RepondreExamenForm form;
  
  private EleveDao eleveDao;
  
  @BeforeTest
  public void beforeTest() {
    this.form = new RepondreExamenForm();
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.eleveDao = (EleveDao) bf.getFactory().getBean("eleveDao");
  }

  @Test
  public void repondreMauvaisExamen() {

    final Eleve eleve = new Eleve();
    final List<String> reponses = new ArrayList<>();
    final String nomExamen = "Reseau";
    final Boolean autoEvaluation = false;

    // Test d'un élève qui n'existe pas : on lève une exception
    eleve.setLogin("abcd");
    eleve.setPassword("sha256:64000:64:SM2UQzuDDgkP8UJyjo0hFT24cim8wPhs:r6t/bI/4EXfs2"
        + "qysS3oS6tqIhSgSw5bpyWTFjEj45cvJRbfwhVHJ68UieOkemcw/imKl2cTs4KKPGiQ+3pUAhw==");

    // Nombre de réponse qui ne correspond pas
    reponses.add("Réponse 1 : abcdefghij");
    reponses.add("Réponse 2 : abcdefghij");
    reponses.add("Réponse 3 : abcdefghij");

    final int repondre = this.form.repondre(eleve, reponses, nomExamen, autoEvaluation);

    assertEquals(repondre, -1, "La réponse n'a pas été ajoutée.");

  }

  @Test
  public void repondreBonExamen() {
    this.form = new RepondreExamenForm();
    final Eleve eleve = eleveDao.trouverParId(1);
    final List<String> reponses = new ArrayList<>();
    reponses.add("C'est dur les maths");
    reponses.add("Vraiment trop dur ...");
    final String nomExamen = "Microprocesseur MP";
    final Boolean autoEvaluation = false;

    final int repondre = this.form.repondre(eleve, reponses, nomExamen, autoEvaluation);


  }


  @Test
  public void repondreMauvaisExamenDeux() {
    this.form = new RepondreExamenForm();
    final Eleve eleve = new Eleve();
    final List<String> reponses = new ArrayList<>();
    reponses.add("C'est dur les maths");
    reponses.add("Vraiment trop dur ...");
    reponses.add("Avec trop de reponses");
    final String nomExamen = "Microprocesseur MP";
    final Boolean autoEvaluation = false;

    final int repondre = this.form.repondre(eleve, reponses, nomExamen, autoEvaluation);


  }
}
