package fr.eseo.atribus.forms;

import static org.testng.Assert.assertEquals;

import fr.eseo.atribus.dao.RessourceDao;
import fr.eseo.atribus.entities.EnseignantRefMatiere;
import fr.eseo.atribus.entities.Matiere;
import fr.eseo.atribus.entities.Ressource;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ModifierRessourceFormTest {


  AddRessourceForm form;
  Ressource ressource;
  RessourceDao ressourceDao;
  
  @BeforeTest
  public void beforeTest() {
    this.form = new AddRessourceForm();
    this.ressource = new Ressource();
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Ressource */
    this.ressourceDao = (RessourceDao) bf.getFactory().getBean("ressourceDao");
  }

  @Test
  public void supprimerRessource() {
    Ressource ressource = ressourceDao.trouverToutesLesRessources().get(0);
    ressource.setPath("");
    ressource.setId(-1);
    this.ressourceDao.ajouter(ressource, "tessst.xml");
    ModifierRessourceForm form = new ModifierRessourceForm();
    EnseignantRefMatiere erm = new EnseignantRefMatiere();
    Matiere matiere = new Matiere();
    matiere.setId(ressource.getMatiere().getId());
    erm.setMatiere(matiere);
    form.supprimerRessource(erm, ressource.getNom()+"."+ressource.getType(), "tessst.xml");
    
    
  }
  
  @Test
  public void supprimerRessourceFail() {
    Ressource ressource = ressourceDao.trouverToutesLesRessources().get(0);
    ressource.setPath("");
    ressource.setId(-1);
    this.ressourceDao.ajouter(ressource, "tessst.xml");
    ModifierRessourceForm form = new ModifierRessourceForm();
    EnseignantRefMatiere erm = new EnseignantRefMatiere();
    Matiere matiere = new Matiere();
    matiere.setId(-1);
    erm.setMatiere(matiere);
    form.supprimerRessource(erm, ressource.getNom()+"."+ressource.getType(), "tessst.xml"); 
  }

  @Test
  public void supprimerRessourceFailDeux() {
    Ressource ressource = ressourceDao.trouverToutesLesRessources().get(0);
    ressource.setPath("");
    ressource.setId(-1);
    this.ressourceDao.ajouter(ressource, "tessst.xml");
    ModifierRessourceForm form = new ModifierRessourceForm();
    EnseignantRefMatiere erm = new EnseignantRefMatiere();
    Matiere matiere = new Matiere();
    matiere.setId(-1);
    erm.setMatiere(matiere);
    boolean catched = false;
    try{
    form.supprimerRessource(erm, ressource.getNom(), "tessst.xml");  
    }catch (Exception e){
      catched = true;
    }
    assertEquals(catched, true);
  }
}
