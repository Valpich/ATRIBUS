package fr.eseo.atribus.integration;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelApplication extends JPanel implements ListSelectionListener {
  private static final long serialVersionUID = 1L;
  private JList<String> list;
  private DefaultListModel<String> listModel;

  private JButton testButton;

  public PanelApplication() {
    super(new BorderLayout());

    listModel = new DefaultListModel<>();
    // 1
    listModel.addElement(
        "1 - [Must] En tant qu'utilisateur, je dois pouvoir me loguer sur la plate-forme de manière sécurisée afin d'accéder à mes ressources");
    // 2
    listModel.addElement(
        "2 - [Must] En tant que DE, je dois être capable de voir les progrès de tout étudiant de manière individuelle afin de pouvoir éventuellement convoquer ceux qui en ont besoin.");
    // 3
    listModel.addElement(
        "3 - [Must] En tant que DE, je dois extraire du système les notes de l'ensemble des étudiants afin d'éditer des bulletins individuels.");
    // 4
    listModel.addElement(
        "4 - [Must] En tant que DP, je dois pouvoir accéder au référentiel de compétences afin de le consulter.");
    // 5
    listModel.addElement(
        "5 - [Must] En tant que DP, je dois pouvoir modifier le référentiel de compétences afin de le mettre à jour d'année en année.");
    // 6
    listModel.addElement(
        "6 - [Must] En tant DP, je dois pouvoir créer les UE relatives à chaque semestre afin de définir le programme du cursus.");
    // 7
    listModel.addElement(
        "7 - [Must] En tant DP, je dois pouvoir modifier les UE relatives à chaque semestre afin de mettre à jour le programme du cursus.");
    // 8
    listModel.addElement(
        "8 - [Must] En tant qu'ERUE,  je dois accéder au référentiel de compétences afin de pouvoir le consulter.");
    // 9
    listModel.addElement(
        "9 - [Must] En tant qu'ERUE, je dois associer des compétences à l'UE en déterminant le niveau attendu afin de définir l'UE.");
    // 10
    listModel.addElement(
        "10 - [Must] En tant qu'ERUE, je dois définir les matières de l'UE afin de préciser le contenu de l'UE.");
    // 11
    listModel.addElement(
        "11 - [Must] En tant qu'ERUE, je dois modifier les compétences relatives à l'UE ainsi que leur niveau afin de les mettre à jour.");
    // 12
    listModel.addElement(
        "12 - [Must] En tant qu'ERUE, je dois modifier les matières de l'UE afin de mettre à jour l'UE d'année en année");
    // 14
    listModel.addElement(
        "13 - [Must] En tant qu'ERM, je dois pouvoir accéder au référentiel de compétences afin de le consulter.");
    // 14
    listModel.addElement(
        "14 - [Must] En tant qu'ERM, je dois pouvoir accéder aux compétences attendues dans l'UE afin de référer les différentes ressources pédagogiques ainsi que les évaluations.");
    // 15
    listModel.addElement(
        "15 - [Must] En tant qu'ERM, je dois pouvoir ajouter une nouvelle ressource en précisant les différentes compétences mises en jeu dans la matière afin d'identifier quelles sont les compétences qui sont abordées par la ressource.");
    // 16
    listModel.addElement(
        "16 - [Must] En tant qu'ERM, je dois être capable d'ajouter des évaluations dont chaque question évalue une ou plusieurs compétences afin de pouvoir évaluer les différentes compétences attendues dans l'UE.");
    // 17
    listModel.addElement(
        "17 - [Must] En tant qu'ERM, je dois modifier les ressources afin de mettre à jour la matière.");
    // 18
    listModel.addElement(
        "18 - [Must] En tant qu'ERM, je dois modifier les évaluations afin de mettre à jour la matière.");
    // 19
    listModel.addElement(
        "19 - [Must] En tant qu'enseignant, je dois être capable d'accéder aux compétences de l'UE et de chaque matière afin que je puisse préparer mon cours.");
    // 20
    listModel.addElement(
        "20 - [Must] En tant qu'enseignant, je dois évaluer le travail des élèves afin de déterminer s'ils ont acquis le niveau requis.");
    // 21
    listModel.addElement(
        "21 - [Must] En tant qu'élève, je dois pouvoir accéder au référentiel de compétences afin de comprendre le but de la formation.");
    // 22
    listModel.addElement(
        "22 - [Must] En tant qu'élève, je dois être capable d'accéder à mes évaluations afin de savoir quelles UEs sont validées ou non.");
    // 23
    listModel.addElement(
        "23 - [Must] En tant qu'élève, je dois être capable de consulter les ressources de formation pour une ou plusieurs compétences données afin de pouvoir acquérir la ou les compétences manquantes.");
    // 24
    listModel.addElement(
        "24 - [Must] En tant qu'administrateur système, je dois être capable de déployer facilement le projet sur l'infrastructure réseau existante.");
    // 25
    listModel.addElement(
        "25 - [Must] En tant qu'administrateur système, je dois avoir accès aux logs de la plate-forme afin d'en assurer le bon fonctionnement.");
    // 26
    listModel.addElement(
        "26 - [Must] En tant qu'administrateur système, je dois pouvoir ajouter des comptes utilisateur afin que chaque utilisateur puisse accéder à la plate-forme.");
    // 27
    listModel.addElement(
        "27 - [Must] En tant qu'administrateur système, je dois pouvoir ajouter des rôles liés avec des comptes utilisateur afin que chaque utilisateur puisse accéder aux ressources de la plate-forme.");
    // 28
    listModel.addElement(
        "28 - [Should] En tant que DE, je devrais être capable d'extraire tous les résultats d'une promotion pour une année afin de pouvoir effectuer un jury.");
    // 29
    listModel.addElement(
        "29 - [Should] En tant que DP,  je devrais accéder aux compétences de chaque matière de chaque UE afin de pouvoir les valider.");
    // 30
    listModel.addElement(
        "30 - [Should] En tant qu'ERUE, je devrais pouvoir suggérer des compétences qui n'apparaissent pas dans le référentiel afin de les ajouter dans la définition de l'UE.");
    // 31
    listModel.addElement(
        "31 - [Should] En tant qu'ERM, je devrais être capable de mettre en avant des compétences qui ne seraient pas référencées dans l'UE afin que l'on puisse déterminer des référencements croisés de compétences entre UE.");
    // 32
    listModel.addElement(
        "32 - [Should] En tant qu'ERM,  je devrais définir et modifier l'ensemble des auto-évaluations des matières de l'UE afin d'établir une auto-évaluation des compétences pour l'UE pour les étudiants (notation non prise en compte pour la validation de l'UE).");
    // 33
    listModel.addElement(
        "33 - [Should] En tant qu'enseignant, je devrais être capable de proposer des compétences qui ne sont pas référencées dans l'UE, et que je voudrais voir être ajoutées afin de prévenir mes collègues (ERM et/ou ERUE) pour qu'ils puissent les prendre en compte dans la description de l'UE.");
    // 34
    listModel.addElement(
        "34 - [Should] En tant qu'élève, je devrais pouvoir voir les compétences attendues pour chaque ressource et chaque évaluation dans l'UE afin de mieux comprendre les objectifs attendus.");
    // 35
    listModel.addElement(
        "35 - [Should] En tant qu'élève, je devrais être capable d'accéder à mes auto-évaluations afin de savoir quels points je devrais développer afin d'acquerir les compétences faisant défaut.");
    // 36
    listModel.addElement(
        "36 - [Should] En tant qu'élève, je devrais être capable de voir quelles sont les compétences que j'ai validées afin de savoir quels modules je pourrais prendre pour valider ces compétences.");
    // 37
    listModel.addElement(
        "37 - [Should] En tant qu'administrateur système, je devrais utiliser l'annuaire LDAP de mon entreprise pour l'identification des différents acteurs du système afin de sécuriser l'accès à mon réseau.");
    // 38
    listModel.addElement(
        "38 - [Could] En tant que DE,  je pourrais accéder au référentiel de compétences afin de le consulter.");
    // 39
    listModel.addElement(
        "39 - [Could] En tant que DE, il serait bien que en cliquant sur un nom d'étudiant, il sache qu'il est convoqué");
    // 40
    listModel.addElement(
        "40 - [Could] En tant DP, je devrais pouvoir modifier les différentes matières constituant les UEs, afin de modifier le cursus d'année en année.");
    // 41
    listModel.addElement(
        "41 - [Could] En tant que DP,  je pourrais accéder aux compétences d'une UE afin de pouvoir les modifier.");
    // 42
    listModel.addElement(
        "42 - [Could] En tant qu'ERUE, je pourrais avoir accès à l'historique de l'utilisation des ressources mises à disposition pour les matières afin de voir celles qui sont utilisées et celles qui le sont moins, et d'en informer les enseignants de l'année suivante.");
    // 43
    listModel.addElement(
        "43 - [Could] En tant qu'ERM, je pourrais construire des tests dont chaque question évalue une ou plusieurs compétences afin de pouvoir établir une auto-évaluation de chaque compétence.");
    // 44
    listModel.addElement(
        "44 - [Could] En tant qu'ERM, je pourrais avoir accès à l'historique des auto-évaluations afin d'améliorer les auto-évaluations pour l'année suivante.");
    // 45
    listModel.addElement(
        "45 - [Could] En tant qu'enseignant, je dois pouvoir accéder au référentiel de compétences afin de le consulter.");
    // 46
    listModel.addElement(
        "46 - [Could] En tant qu'élève, je pourrais proposer des compétences possibles que j'ai pu acquérir par ailleurs et qui seraient relatives à la formation afin de compléter mon bulletin.");
    // 47
    listModel.addElement(
        "47 - [Would] En tant qu'utilisateur, je veux pouvoir disposer d'un système de notification.");
    // 48
    listModel.addElement(
        "48 - [Would] En tant qu'utilisateur, je veux pouvoir configurer mon système de notification.");

    list = new JList<>(listModel);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    list.addListSelectionListener(this);
    list.setVisibleRowCount(5);
    JScrollPane listScrollPane = new JScrollPane(list);

    testButton = new JButton("Lancer le test");
    testButton.setActionCommand("Lancer le test");
    testButton.addActionListener(new TesterListener());

    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
    buttonPane.add(testButton);
    buttonPane.add(Box.createHorizontalStrut(5));
    buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
    buttonPane.add(Box.createHorizontalStrut(5));
    buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    add(listScrollPane, BorderLayout.CENTER);
    add(buttonPane, BorderLayout.PAGE_END);
  }

  private class TesterListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      int index = list.getSelectedIndex();
      try {
        resetBdd();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      switch (index + 1) {
        case 1:
          lancerUnTest(Test1.class);
          break;
        case 2:
          lancerUnTest(Test2.class);
          break;
        case 3:
          lancerUnTest(Test3.class);
          break;
        case 4:
          lancerUnTest(Test4.class);
          break;
        case 5:
          lancerUnTest(Test5.class);
          break;
        case 6:
          lancerUnTest(Test6.class);
          break;
        case 7:
          lancerUnTest(Test7.class);
          break;
        case 8:
          lancerUnTest(Test8.class);
          break;
        case 9:
          lancerUnTest(Test9.class);
          break;
        case 10:
          lancerUnTest(Test10.class);
          break;
        case 11:
          lancerUnTest(Test11.class);
          break;
        case 12:
          lancerUnTest(Test12.class);
          break;
        case 13:
          lancerUnTest(Test13.class);
          break;
        case 14:
          lancerUnTest(Test14.class);
          break;
        case 15:
          lancerUnTest(Test15.class);
          break;
        case 16:
          lancerUnTest(Test16.class);
          break;
        case 17:
          lancerUnTest(Test17.class);
          break;
        case 18:
          lancerUnTest(Test18.class);
          break;
        case 19:
          lancerUnTest(Test19.class);
          break;
        case 20:
          lancerUnTest(Test20.class);
          break;
        case 21:
          lancerUnTest(Test21.class);
          break;
        case 22:
          lancerUnTest(Test22.class);
          break;
        case 23:
          lancerUnTest(Test23.class);
          break;
        case 24:
          lancerUnTest(Test24.class);
          break;
        case 25:
          lancerUnTest(Test25.class);
          break;
        case 26:
          lancerUnTest(Test26.class);
          break;
        case 27:
          lancerUnTest(Test27.class);
          break;
        case 28:
          lancerUnTest(Test28.class);
          break;
        case 29:
          lancerUnTest(Test29.class);
          break;
        case 30:
          lancerUnTest(Test30.class);
          break;
        case 31:
          lancerUnTest(Test31.class);
          break;
        case 32:
          lancerUnTest(Test32.class);
          break;
        case 33:
          lancerUnTest(Test33.class);
          break;
        case 34:
          lancerUnTest(Test34.class);
          break;
        case 35:
          lancerUnTest(Test35.class);
          break;
        case 36:
          lancerUnTest(Test36.class);
          break;
        case 37:
          lancerUnTest(Test37.class);
          break;
        case 38:
          lancerUnTest(Test38.class);
          break;
        case 39:
          lancerUnTest(Test39.class);
          break;
        case 40:
          lancerUnTest(Test40.class);
          break;
        case 41:
          lancerUnTest(Test41.class);
          break;
        case 42:
          lancerUnTest(Test42.class);
          break;
        case 43:
          lancerUnTest(Test43.class);
          break;
        case 44:
          lancerUnTest(Test44.class);
          break;
        case 45:
          lancerUnTest(Test45.class);
          break;
        case 46:
          lancerUnTest(Test46.class);
          break;
        case 47:
          lancerUnTest(Test47.class);
          break;
        case 48:
          lancerUnTest(Test48.class);
          break;
        default:
          break;
      }
      int size = listModel.getSize();
      if (size == 0) {
        testButton.setEnabled(false);
      } else {
        if (index == listModel.getSize()) {
          index--;
        }
        list.setSelectedIndex(index);
        list.ensureIndexIsVisible(index);
      }
    }
  }

  public void valueChanged(ListSelectionEvent e) {
    if (e.getValueIsAdjusting() == false) {
      if (list.getSelectedIndex() == -1) {
        testButton.setEnabled(false);
      } else {
        testButton.setEnabled(true);
      }
    }
  }

  public static void resetBdd() throws SQLException {
    String s = new String();
    StringBuffer sb = new StringBuffer();
    Connection conn = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");

      String url = "jdbc:mysql://192.168.4.12/atribus?useUnicode=yes&characterEncoding=UTF-8";
      String user = "atribususer";
      String passwd = "atribuspassword";

      conn = DriverManager.getConnection(url, user, passwd);
      System.out.println("Connexion effective !");

    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      FileReader fr = new FileReader(new File("BDD.sql"));
      // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character

      BufferedReader br = new BufferedReader(fr);

      while ((s = br.readLine()) != null) {
        sb.append(s);
      }
      br.close();

      // here is our splitter ! We use ";" as a delimiter for each request
      // then we are sure to have well formed statements
      String[] inst = sb.toString().split(";");

      Statement st = conn.createStatement();

      for (int i = 0; i < inst.length; i++) {
        // we ensure that there is no spaces before or after the request string
        // in order to not execute empty statements
        if (!inst[i].trim().equals("")) {
          st.executeUpdate(inst[i]);
          System.out.println(">>" + inst[i]);
        }
      }

    } catch (Exception e) {
      System.out.println("*** Error : " + e.toString());
      System.out.println("*** ");
      System.out.println("*** Error : ");
      e.printStackTrace();
      System.out.println("################################################");
      System.out.println(sb.toString());
    }
  }

  private void lancerUnTest(Class<?> classe) {
    new Thread(() -> tester(classe)).start();
  }

  @Test
  private void tester(Class<?> classe) {
    TestListenerAdapter tla = new TestListenerAdapter();
    TestNG testng = new TestNG();
    testng.setTestClasses(new Class[] { classe });
    testng.addListener(tla);
    testng.run();
  }
}
