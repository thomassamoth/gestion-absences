package gui;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

// Packages
import dao.*;
import model.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe pour afficher la fenêtre d'accueil pour les étudiants
 * 
 * @author Thomas Beyet
 * @author Walid Ben Attia
 * @version 1.0
 */
public class AccueilGestionnaireGUI {

	private JFrame AccueilGestionnaireWindow;

	/**
	 * Constructeur pour créer la fenêtre d'accueil pour les étudiants
	 * 
	 * @param util   l'utilisteur dont on veut afficher les infos
	 * @param prenom prénom de l'utilisateur, nécessaire pour texte de bienvenue
	 */
	public AccueilGestionnaireGUI(Utilisateur util) {
		intializeWindow();
		initializeHeader(util);
		initializeSidebar();
		initializeContent(util);	
	}

	private void intializeWindow() {
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
	}

	private void initializeHeader(Utilisateur util) {
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
		btnDeconnexion.setBackground(new Color(255, 25, 25));
		btnDeconnexion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change curseur quand survole
		btnDeconnexion.setBounds(712, 0, 117, 50);
		header.add(btnDeconnexion);

		// Action bouton déconnecter
		btnDeconnexion.addActionListener(e -> {
			AccueilGestionnaireWindow.dispose();
			new GestionAbsence();
		});

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
	}

	private void initializeSidebar() {
		// Menu latéral

		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 0, 140, 443);
		sidebar.setLayout(null);
		AccueilGestionnaireWindow.getContentPane().add(sidebar);
	}

	private void initializeContent(Utilisateur util){
		JPanel content = new JPanel();
		content.setBackground(new Color(255, 255, 255));
		content.setBounds(140, 50, 699, 393);
		content.setLayout(null);
		content.setVisible(true);
		AccueilGestionnaireWindow.getContentPane().add(content);

		// Texte bonjour + prénom
		JLabel txtBonjour = new JLabel("");
		txtBonjour.setFont(new Font("Arial", Font.BOLD, 14));
		txtBonjour.setBounds(20, 20, 148, 30);
		txtBonjour.setText("Bonjour " + util.getUtilisateurPrenom());
		content.add(txtBonjour);

		JButton btnAjouterEtudiant = new JButton("Ajouter Etudiant");
		btnAjouterEtudiant.setForeground(new Color(255, 255, 255));
		btnAjouterEtudiant.setFont(new Font("Arial", Font.PLAIN, 10));
		btnAjouterEtudiant.setBackground(new Color(0, 105, 217));
		btnAjouterEtudiant.setBounds(20, 70, 128, 21);
		btnAjouterEtudiant.setBorderPainted(false);
		content.add(btnAjouterEtudiant);
		
		btnAjouterEtudiant.addActionListener(e -> {
			AccueilGestionnaireWindow.dispose();
			new GestionEtudiantGUI(util);
		});

		JLabel txtMessages = new JLabel("");
		txtMessages.setBackground(new Color(255, 255, 255));
		txtMessages.setHorizontalAlignment(SwingConstants.CENTER);
		txtMessages.setBounds(160, 215, 295, 21);
		txtMessages.setOpaque(true);
		content.add(txtMessages);

		JButton btnAjouterGroupe = new JButton("Ajouter Groupe");
		btnAjouterGroupe.setForeground(Color.WHITE);
		btnAjouterGroupe.setFont(new Font("Arial", Font.PLAIN, 10));
		btnAjouterGroupe.setBackground(new Color(253, 126, 20));
		btnAjouterGroupe.setBounds(170, 70, 128, 21);
		btnAjouterGroupe.setBorderPainted(false);
		content.add(btnAjouterGroupe);

		btnAjouterGroupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreerGroupe(txtMessages);
			}
		});

		JButton btnAjouterEnseignant = new JButton("Ajouter Enseignant");
		btnAjouterEnseignant.setForeground(Color.WHITE);
		btnAjouterEnseignant.setFont(new Font("Arial", Font.PLAIN, 10));
		btnAjouterEnseignant.setBorderPainted(false);
		btnAjouterEnseignant.setBackground(new Color(0, 105, 217));
		btnAjouterEnseignant.setBounds(20, 124, 128, 21);
		content.add(btnAjouterEnseignant);
		
		/*JButton btnModifierEtudiant = new JButton("Modifier ou supprimer étudiant");
		btnModifierEtudiant.setForeground(Color.WHITE);
		btnModifierEtudiant.setFont(new Font("Arial", Font.PLAIN, 10));
		btnModifierEtudiant.setBorderPainted(false);
		btnModifierEtudiant.setBackground(new Color(255, 128, 0));
		btnModifierEtudiant.setBounds(160, 169, 181, 21);
		content.add(btnModifierEtudiant);
		
		btnModifierEtudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccueilGestionnaireWindow.dispose();
				new ChoisirEtudiant(txtMessages);
			}
		});*/
		
		
		JButton btnSupprimer = new JButton("Supprimer Utilisateur");
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Arial", Font.PLAIN, 10));
		btnSupprimer.setBorderPainted(false);
		btnSupprimer.setBackground(new Color(0, 105, 217));
		btnSupprimer.setBounds(170, 124, 157, 21);
		content.add(btnSupprimer);
		
		JButton btnAbsence = new JButton("Absences");
		btnAbsence.setForeground(new Color(255, 255, 255));
		btnAbsence.setFont(new Font("Arial", Font.PLAIN, 10));
		btnAbsence.setBackground(new Color(0, 105, 217));
		btnAbsence.setForeground(new Color(255, 255, 255));
		btnAbsence.setBounds(352, 124, 128, 21);
		content.add(btnAbsence);
		
		JButton btnModifierEtudiant = new JButton("Modifier Etudiant");
		btnModifierEtudiant.setForeground(Color.WHITE);
		btnModifierEtudiant.setFont(new Font("Arial", Font.PLAIN, 10));
		btnModifierEtudiant.setBorderPainted(false);
		btnModifierEtudiant.setBackground(new Color(0, 105, 217));
		btnModifierEtudiant.setBounds(170, 159, 157, 21);
		content.add(btnModifierEtudiant);
		
		btnAbsence.addActionListener(e -> {
			AccueilGestionnaireWindow.dispose();
			new ValidationAbsenceGUI(util);
		});
		
		btnAjouterEnseignant.addActionListener(e -> {
			AccueilGestionnaireWindow.dispose();
			new CreerProfesseurGUI(util);
		});
		
		btnSupprimer.addActionListener(e -> {
			AccueilGestionnaireWindow.dispose();
			new SupprimerUtilisateurGUI(util);
		});
		
		btnModifierEtudiant.addActionListener(e -> {
			AccueilGestionnaireWindow.dispose();
			new ChoisirEtudiant(txtMessages, util);
		});
	}
}
