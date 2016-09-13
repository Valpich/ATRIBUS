package fr.eseo.atribus.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.testng.Assert.assertNotNull;

import fr.eseo.atribus.dao.NotificationDao;
import fr.eseo.atribus.dao.UtilisateurDao;
import fr.eseo.atribus.entities.Notification;
import fr.eseo.atribus.entities.Utilisateur;
import fr.eseo.atribus.forms.SupprimerNotificationForm;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class NotificationControllerTest {

  public static final String VUE = "EnseignantRefMatiere/addMatiere";

  private MockMvc mockMvc;
  private UtilisateurDao utilisateurDao;
  private NotificationDao notificationDao;

  /**
   * Initialisation.
   */
  @BeforeTest
  public void setup() {

    final NotificationController notificationController = new NotificationController();
    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    viewResolver.setPrefix("/WEB-INF/");
    viewResolver.setSuffix(".jsp");
    MockitoAnnotations.initMocks(this);

    this.mockMvc =
        MockMvcBuilders.standaloneSetup(notificationController).setViewResolvers(viewResolver).build();
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    this.utilisateurDao = (UtilisateurDao) bf.getFactory().getBean("utilisateurDao");
    this.notificationDao = (NotificationDao) bf.getFactory().getBean("notificationDao");
  }

  @Test
  public void afficherNotification() throws Exception {

    this.mockMvc.perform(get("/Notifications"))
        .andExpect(status().isOk()).andExpect(view().name("gererNotification"));

  }
  
  @Test
  public void supprimerNotification() throws Exception {
    Notification notification = new Notification();
    notification.setDateCreation(new Date());
    notification.setDestinataire(1);
    notification.setEmetteur(1);
    notification.setMessage("test");
    notification.setNomEmetteur("nom emeteur");
    this.notificationDao.ajouter(notification);
    SupprimerNotificationForm form = new SupprimerNotificationForm();
    Utilisateur user = this.utilisateurDao.trouverParId(1);
    List<String> test = new ArrayList<>();
    try{
    test.add(new String("a"));
    form.supprimerNotification(test,user);
    }catch(Exception e){
      e.printStackTrace();
    }
    test = new ArrayList<>();
    test.add(new String("0"));
    form.supprimerNotification(test,user);
     test = new ArrayList<>();
    test.add(new String(notification.getId().toString()));
    form = new SupprimerNotificationForm();
    form.supprimerNotification(test,user);
    assertNotNull(form.getResultat());
    this.mockMvc.perform(post("/Notifications").param("table_records", "1").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(this.utilisateurDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    }))
        .andExpect(status().isOk()).andExpect(view().name("gererNotification"));
    
     //  Sans avoir cocher
    this.mockMvc.perform(post("/Notifications").with(request -> {
      final List<Utilisateur> utilisateur = new ArrayList<>();
      utilisateur.add(this.utilisateurDao.recupererListe().get(0));
      request.getSession().setAttribute("sessionUtilisateur", utilisateur);
      return request;
    }))
        .andExpect(status().isOk()).andExpect(view().name("gererNotification"));

  }

}
