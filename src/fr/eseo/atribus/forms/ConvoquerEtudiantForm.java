package fr.eseo.atribus.forms;

import fr.eseo.atribus.dao.EleveDao;
import fr.eseo.atribus.dao.NotificationDao;
import fr.eseo.atribus.entities.DirecteurEtudes;
import fr.eseo.atribus.entities.Eleve;
import fr.eseo.atribus.entities.Notification;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

/**
 * La classe ConvoquerEtudiantForm.
 */
public class ConvoquerEtudiantForm extends MailForm {

  /** La variable eleve dao. */
  private final EleveDao eleveDao;

  /** La variable notification dao. */
  private final NotificationDao notificationDao;

  /** La constante BR. */
  private static final String BR = "<br/>";

  /**
   * Méthode d'instantiation.
   */
  public ConvoquerEtudiantForm() {
    final BeanFactoryReference bf =
        SingletonBeanFactoryLocator.getInstance().useBeanFactory("beansDao");
    /* Récupération d'une instance de notre DAO Eleve */
    this.eleveDao = (EleveDao) bf.getFactory().getBean("eleveDao");
    /* Récupération d'une instance de notre DAO Notification */
    this.notificationDao = (NotificationDao) bf.getFactory().getBean("notificationDao");
  }

  /**
   * Convoquation d'un étudiant.
   * 
   * @param de Le directeur des études.
   * @param eleveId L'id de l'eleve.
   * @param date La date de la convoquation.
   */
  public void convoquerEleve(DirecteurEtudes de, String eleveId, String date) {
    final String objet = "Convocation par la direction des études.";
    final Eleve eleve = this.eleveDao.trouverParId(Integer.parseInt(eleveId));
    final Notification notification = new Notification();
    notification.setDestinataire(eleve.getId());
    notification.setEmetteur(de.getId());
    notification.setMessage(this.genererContenu(de, date));
    this.notificationDao.ajouter(notification);
    this.envoyerMail(objet, this.genererContenuMail(de, date), eleve.getEmail());
  }

  /**
   * Generer contenu.
   *
   * @param de le directeur des etudes
   * @param date la date
   * @return Le paramètre string
   */
  private String genererContenu(DirecteurEtudes de, String date) {
    final StringBuilder convocation = new StringBuilder();
    convocation.append(this.messages.getConvocationDePartieUn().replaceAll(BR, " "));
    convocation.append(de.getNom() + " " + de.getPrenom());
    convocation.append(this.messages.getConvocationDePartieDeux().replaceAll(BR, ""));
    convocation.append(date);
    convocation.append(this.messages.getConvocationDePartieTrois().replaceAll(BR, ""));
    return convocation.toString();
  }

  /**
   * Generer contenu mail.
   *
   * @param de le directeur des etudes
   * @param date la date
   * @return Le paramètre string
   */
  private String genererContenuMail(DirecteurEtudes de, String date) {
    final StringBuilder convocation = new StringBuilder();
    convocation.append(this.messages.getConvocationDePartieUn());
    convocation.append(de.getNom() + " " + de.getPrenom());
    convocation.append(this.messages.getConvocationDePartieDeux());
    convocation.append(date);
    convocation.append(this.messages.getConvocationDePartieTrois());
    return convocation.toString();
  }

}

