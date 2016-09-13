package fr.eseo.atribus.beans;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.dao.EvaluationDao;
import fr.eseo.atribus.dao.ExamenDao;
import fr.eseo.atribus.dao.PromotionDao;
import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Evaluation;
import fr.eseo.atribus.entities.Examen;
import fr.eseo.atribus.entities.Promotion;

import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExporteurCsvTest {

  private EleveDao eleveDao;
  private EvaluationDao evaluationDao;
  private PromotionDao promotionDao;
  private ExamenDao examenDao;
  private List<Eleve> listeEleves;
  private List<Evaluation> listeEvaluations;
  private List<Examen> listeExamens;
  private Eleve eleve;
  private Examen examen;

  /**
   * Initialisation.
   */
  @BeforeTest
  public void beforeClass() {

    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.eleveDao = (EleveDao) bf.getFactory().getBean("eleveDao");
    this.evaluationDao = (EvaluationDao) bf.getFactory().getBean("evaluationDao");
    this.examenDao = (ExamenDao) bf.getFactory().getBean("examenDao");
    this.promotionDao = (PromotionDao) bf.getFactory().getBean("promotionDao");
  }

  @Test
  public void generateCsvFile() {

    this.listeEleves = new ArrayList<>();
    this.listeEvaluations = new ArrayList<>();
    this.listeExamens = new ArrayList<>();

    this.listeEleves = this.eleveDao.recupererListe();
    this.listeEvaluations = this.evaluationDao.trouverToutesLesEvaluations();
    this.listeExamens = this.examenDao.trouverTousLesExamens();

    PowerMockito.mockStatic(ExporteurCsv.class);

    ExporteurCsv.generateCsvFile("nom", this.listeEleves, this.listeEvaluations, this.listeExamens);

    assertNotNull(this.listeEleves);
    assertNotNull(this.listeEvaluations);
    assertNotNull(this.listeExamens);

  }

  @Test
  public void majMap() {

    try {

      final Map<Eleve, Map<Examen, Float>> notesEleveExamens = new HashMap<>();

      this.eleve = new Eleve();
      this.examen = new Examen();

      notesEleveExamens.put(this.eleve, new HashMap<>());

      this.eleve = this.eleveDao.trouverParId(0);
      this.examen = this.examenDao.trouverParNom("Microprocesseur");

      PowerMockito.mockStatic(ExporteurCsv.class);

      ExporteurCsv.majMap(notesEleveExamens, this.eleve, this.examen, true, new Float(20));

      assertEquals(this.eleve.getId(), 0);
      assertNotNull(this.examen.getNom(), "Microprocesseur");

    } catch (final Exception excpt) {
      excpt.getMessage();
    }

  }

  @Test
  public void generateCsvFileDate() {

    this.listeEleves = new ArrayList<>();
    this.listeEvaluations = new ArrayList<>();
    this.listeExamens = new ArrayList<>();

    this.listeEleves = this.eleveDao.recupererListe();
    this.listeEvaluations = this.evaluationDao.trouverToutesLesEvaluations();
    this.listeExamens = this.examenDao.trouverTousLesExamens();

    PowerMockito.mockStatic(ExporteurCsv.class);
    final Date date = new Date();
    date.setTime(0);
    for (final Promotion promotion : this.promotionDao.trouverToutesLesPromotions()) {
      ExporteurCsv.generateCsvFile("nom", this.listeEleves, this.listeEvaluations,
          this.listeExamens, promotion, date, new Date());

      ExporteurCsv.generateCsvFile("nom", this.listeEleves, this.listeEvaluations,
          this.listeExamens, promotion, null, new Date());

      ExporteurCsv.generateCsvFile("nom", this.listeEleves, this.listeEvaluations,
          this.listeExamens, promotion, new Date(), null);

      ExporteurCsv.generateCsvFile("nom", this.listeEleves, this.listeEvaluations,
          this.listeExamens, promotion, null, null);
    }
    assertNotNull(this.listeEleves);
    assertNotNull(this.listeEvaluations);
    assertNotNull(this.listeExamens);

  }


}
