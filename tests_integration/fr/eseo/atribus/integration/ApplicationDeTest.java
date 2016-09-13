package fr.eseo.atribus.integration;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ApplicationDeTest {

  private JFrame frame;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ApplicationDeTest window = new ApplicationDeTest();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public ApplicationDeTest() {
    initialize();
  }


  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setResizable(false);
    frame.setSize(600, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    frame.setContentPane(new PanelApplication());
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
        dim.height / 2 - frame.getSize().height / 2);
    frame.setTitle("Application de test d'intégration d'ATRIBUS");
  }

}
