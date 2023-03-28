package gui;

import java.awt.*;
import javax.swing.*;

// Packages
import dao.UtilisateurDAO;
import model.Utilisateur;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class validationAbsences {

	private JFrame validationAbsences;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					validationAbsences window = new validationAbsences();
					window.validationAbsences.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public validationAbsences() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		 // Nombre de panneaux que vous souhaitez créer
	    int numPanels = 5;

	    // Coordonnées initiales pour les panneaux
	    int x = 10;
	    int y = 80;
	    int panelWidth = 200;
	    int panelHeight = 300;
	    
	    
		validationAbsences = new JFrame();
		validationAbsences.getContentPane().setBackground(new Color(255, 255, 255));
		validationAbsences.setTitle("Gestion des absences");
		validationAbsences.setBackground(new Color(192, 192, 192));
		validationAbsences.setResizable(false);
		validationAbsences.setBounds(100, 100, 853, 480);
		validationAbsences.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		validationAbsences.setBounds(100, 100, 853, 480);
		validationAbsences.getContentPane().setLayout(null);
		
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 0, 120, 443);
		validationAbsences.getContentPane().add(sidebar);
		sidebar.setLayout(null);
		
		JLabel titreValidationAbsences = new JLabel("Validation Absences");
		titreValidationAbsences.setHorizontalAlignment(SwingConstants.CENTER);
		titreValidationAbsences.setFont(new Font("Arial", Font.BOLD, 24));
		titreValidationAbsences.setBounds(343, 10, 262, 57);
		validationAbsences.getContentPane().add(titreValidationAbsences);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(136, 94, 672, 324);
		scrollPane.setBackground(new Color(255, 102, 102));
		validationAbsences.getContentPane().add(scrollPane);
		
		// Créer et ajouter des panneaux à la fenêtre
	    for (int i = 0; i < numPanels; i++) {
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(255, 0, 102));
	        panel.setBounds(x, y, panelWidth, panelHeight);
	        scrollPane.add(panel);
	        
	        // Ajouter d'autres composants à chaque panneau ici si nécessaire
	        
	        // Déplacer les coordonnées x et y pour le prochain panneau
	        x += panelWidth + 10;
	    }
		

	}
}
