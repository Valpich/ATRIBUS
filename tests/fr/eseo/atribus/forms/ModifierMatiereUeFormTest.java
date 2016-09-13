package fr.eseo.atribus.forms;

import static org.testng.Assert.assertNotNull;

import javax.servlet.http.HttpServletRequest;
import static org.testng.Assert.assertEquals;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import fr.eseo.atribus.dao.EnseignantDao;
import fr.eseo.atribus.dao.EnseignantDaoImpl;
import fr.eseo.atribus.dao.ExamenDaoImpl;
import fr.eseo.atribus.dao.MatiereDao;
import fr.eseo.atribus.dao.MatiereDaoImpl;
import fr.eseo.atribus.dao.UeDao;
import fr.eseo.atribus.dao.UeDaoImpl;

public class ModifierMatiereUeFormTest {

  ModifierMatiereUeForm form;
  MatiereDao matiereDao; 
  EnseignantDao enseignantDao;
  UeDao ueDao;
  
  @BeforeClass
  public void beforeClass() {
    
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    
    this.matiereDao = (MatiereDao) bf.getFactory().getBean("matiereDao");
    this.enseignantDao = (EnseignantDao) bf.getFactory().getBean("enseignantDao"); 
    this.ueDao =(UeDao) bf.getFactory().getBean("ueDao"); 
    
    form = new ModifierMatiereUeForm();
  }

  
  @Test
  public void ModifierMatiereUeForm(){
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);   
   
    final String idNew = "2";
    final String nomNouvelleMatiere = "Nouvelle matiere"; 
    final String ancienCoefficiant ="1";
    final String idAncienEnseignant ="3";
    final String idAncienUe = "1";
   
    Mockito.when(httpServletRequest.getParameter("idMatiereAModifier")).thenReturn(idNew);
    Mockito.when(httpServletRequest.getParameter("nomNouvelleMatiere")).thenReturn(nomNouvelleMatiere);
    Mockito.when(httpServletRequest.getParameter("idEnseignantNouvelleMatiere")).thenReturn(idAncienEnseignant);
    Mockito.when(httpServletRequest.getParameter("idNouvelleUe")).thenReturn(idAncienUe);
    Mockito.when(httpServletRequest.getParameter("coefficient")).thenReturn(ancienCoefficiant);
    
    form.modifierMatiereUe(httpServletRequest);
    assertNotNull(this.form);
    assertNotNull(this.form.getResultat(),"succes");
    
    
    
  }

  
  @Test
  public void ModifierMatiereUeFormFail(){
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);   
   
    final String idNew = "2";
    final String nomNouvelleMatiere = "No"; 
    final String ancienCoefficiant ="a";
    final String idAncienEnseignant ="b";
    final String idAncienUe = "b";
   
    Mockito.when(httpServletRequest.getParameter("idMatiereAModifier")).thenReturn(idNew);
    Mockito.when(httpServletRequest.getParameter("nomNouvelleMatiere")).thenReturn(nomNouvelleMatiere);
    Mockito.when(httpServletRequest.getParameter("idEnseignantNouvelleMatiere")).thenReturn(idAncienEnseignant);
    Mockito.when(httpServletRequest.getParameter("idNouvelleUe")).thenReturn(idAncienUe);
    Mockito.when(httpServletRequest.getParameter("coefficient")).thenReturn(ancienCoefficiant);
    
    form.modifierMatiereUe(httpServletRequest);
    assertNotNull(this.form);
    assertNotNull(this.form.getResultat(),"succes");
    assertNotNull(this.form.getErreurs());
    
    
  }
  @Test
  public void setResultat() {
    this.form.setResultat("resultat");
    assertEquals("resultat",this.form.getResultat()); 
  }
  
  @Test
  public void getValeurChamp(){
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);  
    final String nomNouvelleTest = "test"; 
    Mockito.when(httpServletRequest.getParameter("nomNouvelleMatiere")).thenReturn(nomNouvelleTest);
    assertEquals( form.getValeurChamp(httpServletRequest, "nomNouvelleMatiere"),nomNouvelleTest);
  }
  @Test
  public void getValeurChampNull(){
    
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);  
    final String nomNouvelleTest = null; 
    Mockito.when(httpServletRequest.getParameter("nomNouvelleMatiere")).thenReturn(nomNouvelleTest);
    final String champForm = form.getValeurChamp(httpServletRequest,"nomNouvelleMatiere");
   
    assertEquals(champForm,null);
  }
  
  @Test
  public void getValeurChampTrimNull(){
    
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);  
    final String nomNouvelleTest = ""; 
    Mockito.when(httpServletRequest.getParameter("nomNouvelleMatiere")).thenReturn(nomNouvelleTest);
    final String champForm = form.getValeurChamp(httpServletRequest,"nomNouvelleMatiere");
    assertEquals(champForm,champForm);
  }
  
  @Test
  public void validationUe() throws FormValidationException {
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class); 
    final String idNew = "2";
    final String nomNouvelleMatiere = "Nouvelle matiere"; 
    final String ancienCoefficiant ="1";
    final String idAncienEnseignant ="1";
    final String idAncienUe = "1";
    boolean test = false; 
    
    Mockito.when(httpServletRequest.getParameter("idMatiereAModifier")).thenReturn(idNew);
    Mockito.when(httpServletRequest.getParameter("nomNouvelleMatiere")).thenReturn(nomNouvelleMatiere);
    Mockito.when(httpServletRequest.getParameter("idEnseignantNouvelleMatiere")).thenReturn(idAncienEnseignant);
    Mockito.when(httpServletRequest.getParameter("idNouvelleUe")).thenReturn(idAncienUe);
    Mockito.when(httpServletRequest.getParameter("coefficient")).thenReturn(ancienCoefficiant);
    form.traiterDonnees(nomNouvelleMatiere, ancienCoefficiant, "1", idAncienUe);
    test=true;
    assertEquals(test,true);
   
  }
  
/*
  



  @Test
  public void modifierMatiereUe() {
    throw new RuntimeException("Test not implemented");
  }

 


  @Test
  public void traiterDonnees() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void validationCoefficiant() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void validationEnseignant() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void validationNomMatiere() {
    throw new RuntimeException("Test not implemented");
  }


  */
}
