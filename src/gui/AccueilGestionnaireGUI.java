package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.util.ArrayList;

// Packages
import dao.*;
import model.*;

/**
 * Classe pour afficher la fenêtre d'accueil pour les étudiants
 * 
 * @author Thomas
 * @version 1.0
 */
public class AccueilGestionnaireGUI {


	private JFrame AccueilGestionnaireWindow;

	/**
	 * Créer la fenêtre d'accueil pour les étudiants
	 * @param util l'utilisteur dont on veut afficher les infos
	 * @param prenom prénom de l'utilisateur, nécessaire pour texte de bienvenue
	 */
	public AccueilGestionnaireGUI(Utilisateur util, String prenom) {
		initialize(util, prenom);
	}

	/**
	 * Initialise le contenu de la fenêtre d'accueil pour les étudiants
	 * @param util l'utilisteur dont on veut afficher les infos
	 * @param prenom prénom de l'utilisateur, nécessaire pour texte de bienvenue
	 */
	private void initialize(Utilisateur util, String prenom) {
		// Fenêtre
		AccueilGestionnaireWindow = new JFrame();
		AccueilGestionnaireWindow.setResizable(false);
		AccueilGestionnaireWindow.getContentPane().setBackground(new Color(255, 255, 255));
		AccueilGestionnaireWindow.setVisible(true);
		AccueilGestionnaireWindow.setTitle("Accueil - Gestionnaire");
		AccueilGestionnaireWindow.setSize(853, 480);
		AccueilGestionnaireWindow.setLocationRelativeTo(null); // Centre fenêtre dans l'écran
		AccueilGestionnaireWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AccueilGestionnaireWindow.getContentPane().setLayout(null);

		// Header
		JPanel header = new JPanel();
		header.setBackground(new Color(255, 25, 25));
		header.setBounds(0, 0, 839, 50);
		header.setLayout(null);
		AccueilGestionnaireWindow.getContentPane().add(header);

		// Bouton Menu Déconnexion
		JButton btnDeconnexion = new JButton("Se Déconnecter");
		btnDeconnexion.setForeground(new Color(255, 255, 255));
		btnDeconnexion.setFont(new Font("Arial", Font.PLAIN, 10));
		btnDeconnexion.setBorderPainted(false);
		btnDeconnexion.setFocusPainted(false); // Bordure text invisible
		btnDeconnexion.setBackground(new Color(255, 25, 25));
		btnDeconnexion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change curseur quand survole
		btnDeconnexion.setBounds(712, 0, 117, 50);
		header.add(btnDeconnexion);

		// Affichage identifiant utilisateur
		JLabel usernameLabel = new JLabel("");
		usernameLabel.setForeground(new Color(255, 255, 255));
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		usernameLabel.setBounds(0, 0, 140, 50);
		usernameLabel.setText(util.getIdentifiant());
		header.add(usernameLabel);


		// Titre Esigelec
		JLabel ESIGELEC = new JLabel("ESIGELEC");
		ESIGELEC.setHorizontalAlignment(SwingConstants.CENTER);
		ESIGELEC.setBounds(138, 0, 701, 50);
		header.add(ESIGELEC);
		ESIGELEC.setForeground(new Color(255, 255, 255));
		ESIGELEC.setFont(new Font("Arial", Font.PLAIN, 30));

		// Menu latéral
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 0, 140, 443);
		sidebar.setLayout(null);
		AccueilGestionnaireWindow.getContentPane().add(sidebar);

		/*// Bouton Menu absences
		JButton menuBtnAbsences = new JButton("Absences");
		menuBtnAbsences.setForeground(new Color(255, 255, 255));
		menuBtnAbsences.setFont(new Font("Arial", Font.PLAIN, 20));
		menuBtnAbsences.setBorderPainted(false);
		menuBtnAbsences.setFocusPainted(false); // Bordure text invisible
		menuBtnAbsences.setBackground(new Color(255, 102, 102));
		menuBtnAbsences.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change curseur quand survole
		menuBtnAbsences.setBounds(0, 83, 140, 33);
		sidebar.add(menuBtnAbsences);

		// Bouton Menu Planning
		JButton menuBtnPlanning = new JButton("Planning");
		menuBtnPlanning.setForeground(new Color(255, 255, 255));
		menuBtnPlanning.setFont(new Font("Arial", Font.PLAIN, 20));
		menuBtnPlanning.setBorderPainted(false);
		menuBtnPlanning.setBackground(new Color(255, 102, 102));
		menuBtnPlanning.setFocusPainted(false); // Bordure text invisible
		menuBtnPlanning.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change curseur quand survol
		menuBtnPlanning.setBounds(0, 126, 140, 33);
		sidebar.add(menuBtnPlanning);

		// Change couleur fond si cliqué + action bouton
		menuBtnPlanning.addActionListener(e -> {
			menuBtnPlanning.setBackground(new Color(255, 31, 31));
			menuBtnAbsences.setBackground(new Color(255, 102, 102));
			// Action here
		});

		// Change couleur fond si cliqué + action bouton
		menuBtnAbsences.addActionListener(e -> {
			menuBtnAbsences.setBackground(new Color(255, 31, 31));
			menuBtnPlanning.setBackground(new Color(255, 102, 102));
			AccueilGestionnaireWindow.dispose();
			//new AbsencesEtudiantGUI(util, prenom);
		});



		// Change couleur fond si cliqué + action bouton
		btnDeconnexion.addActionListener(e -> {
			btnDeconnexion.setBackground(new Color(255, 31, 31));
			menuBtnPlanning.setBackground(new Color(255, 102, 102));
			AccueilGestionnaireWindow.dispose();
			new GestionAbsence();
		});
		*/
		// texte bonjour + prénom
		JLabel txtBonjour = new JLabel("");
		txtBonjour.setFont(new Font("Arial", Font.BOLD, 14));
		txtBonjour.setBounds(160, 60, 148, 30);
		txtBonjour.setText("Bonjour " + prenom);
		AccueilGestionnaireWindow.getContentPane().add(txtBonjour);
		
		JButton btnCreerEtudiant = new JButton("Créer Etudiant");
		btnCreerEtudiant.setForeground(new Color(255, 255, 255));
		btnCreerEtudiant.setFont(new Font("Arial", Font.PLAIN, 10));
		btnCreerEtudiant.setBackground(new Color(0, 105, 217));
		btnCreerEtudiant.setBounds(160, 124, 128, 21);
		AccueilGestionnaireWindow.getContentPane().add(btnCreerEtudiant);
		btnCreerEtudiant.addActionListener(e -> {
			AccueilGestionnaireWindow.dispose();
			new GestionEtudiantGUI(util);
		});
		
		JButton btnCreerFermeture = new JButton("Créer Période Fermeture");
		btnCreerFermeture.setForeground(Color.WHITE);
		btnCreerFermeture.setFont(new Font("Arial", Font.PLAIN, 10));
		btnCreerFermeture.setBackground(new Color(0, 105, 217));
		btnCreerFermeture.setBounds(312, 124, 157, 21);
		AccueilGestionnaireWindow.getContentPane().add(btnCreerFermeture);
		
		
	}
}
