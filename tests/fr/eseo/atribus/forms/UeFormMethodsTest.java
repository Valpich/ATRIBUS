package fr.eseo.atribus.forms;

import static org.testng.Assert.assertEquals;

import fr.eseo.atribus.dao.CompetenceDao;
import fr.eseo.atribus.dao.EnseignantDao;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UeFormMethodsTest {

  private TestUeFormMethods test = new TestUeFormMethods();
  @BeforeTest
  public void beforeTest() {
  }

  @Test
  public void validerCompetence() {
    boolean fail = false;
    try{
      test.validationCompetence("Travail en équipe");
    }catch(Exception e){
      fail = true;
      e.printStackTrace();
    }
    assertEquals(fail, true);
    fail = false;
    try{
      test.validationCompetence("success");
    }catch(Exception e){
      e.printStackTrace();
    }
    assertEquals(fail, false);
  }

  @Test
  public void validerEnseignant() {
    boolean fail = false;
    try{
      test.validationEnseignant("dfdfdsfsdfdsxcxcsfdsfdscds");
    }catch(Exception e){
      fail = true;
      e.printStackTrace();
    }
    assertEquals(fail, true);
    fail = false;
    try{
      test.validationEnseignant("Enseignant");
    }catch(Exception e){
      e.printStackTrace();
    }
    assertEquals(fail, false);
  }
  private class TestUeFormMethods extends UeFormMethods{
    TestUeFormMethods(){
      super();
      final BeanFactoryReference bf =
          SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
      this.data.setCompetenceBdd((CompetenceDao) bf.getFactory().getBean("competenceDao"));
      this.data.setEnseignantBdd((EnseignantDao) bf.getFactory().getBean("enseignantDao"));
    }
  }
}
