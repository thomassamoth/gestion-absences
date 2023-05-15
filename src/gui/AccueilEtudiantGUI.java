package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
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
public class AccueilEtudiantGUI {

	private JFrame AccueilEtudiant;

	/**
	 * Créer la fenêtre d'accueil pour les étudiants
	 * @param util l'utilisteur dont on veut afficher les infos
	 * @param prenom prénom de l'utilisateur, nécessaire pour texte de bienvenue
	 */
	public AccueilEtudiantGUI(Utilisateur util) {
		initializeWindow();
		initializeHeader(util);
		initializeSidebar(util);
		initialize(util);
	}

	/**
	 * Initialise le contenu de la fenêtre d'accueil pour les étudiants
	 * @param util l'utilisteur dont on veut afficher les infos
	 * @param prenom prénom de l'utilisateur, nécessaire pour texte de bienvenue
	 */
	private void initialize(Utilisateur util) {
		// texte bonjour + prénom
		JLabel txtBonjour = new JLabel("");
		txtBonjour.setFont(new Font("Arial", Font.BOLD, 14));
		txtBonjour.setBounds(160, 60, 148, 30);
		txtBonjour.setText("Bonjour " + util.getUtilisateurPrenom());
		AccueilEtudiant.getContentPane().add(txtBonjour);

		// Affichage absences
		displayAbsencesJustifiees(util.getIdentifiant());
		displayAbsencesInjustifiees(util.getIdentifiant());		
	}

	/**
	 * Initialise la fenêtre
	 */
	private void initializeWindow(){
		// Fenêtre
		AccueilEtudiant = new JFrame();
		AccueilEtudiant.setResizable(false);
		AccueilEtudiant.getContentPane().setBackground(new Color(255, 255, 255));
		AccueilEtudiant.setVisible(true);
		AccueilEtudiant.setTitle("Accueil Etudiant");
		AccueilEtudiant.setSize(853, 480);
		AccueilEtudiant.setLocationRelativeTo(null); // Centre fenêtre dans l'écran
		AccueilEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AccueilEtudiant.getContentPane().setLayout(null);
	}

	/**
	 * 
	 * @param util
	 */
	private void initializeHeader(Utilisateur util) {
		// Header
		JPanel header = new JPanel();
		header.setBackground(new Color(255, 25, 25));
		header.setBounds(0, 0, 839, 50);
		header.setLayout(null);
		AccueilEtudiant.getContentPane().add(header);

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
			AccueilEtudiant.dispose();
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

	private void initializeSidebar(Utilisateur util){
		// Menu latéral
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 0, 140, 443);
		sidebar.setLayout(null);
		AccueilEtudiant.getContentPane().add(sidebar);

		// Bouton Menu absences
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
			AccueilEtudiant.dispose();
			new AbsencesEtudiantGUI(util);
		});

	}
	/**
	 * Affiche les absences injustifiées d'un élève à partir de son identifiant
	 * @param identifiant l'idenfiant de l'utilisateur dont on veut connaitre les absences
	 */
	public void displayAbsencesInjustifiees(String identifiant) {
		EtudiantDAO etuDAO = new EtudiantDAO();
		ArrayList<Absence> listeAbsencesInjustifiees = etuDAO.getAbsencesInjustifiees(identifiant);

		// Texte absences injustifiées
		JLabel txtListeAbsence = new JLabel("Liste de vos absences injustifiées");
		txtListeAbsence.setHorizontalAlignment(SwingConstants.LEFT);
		txtListeAbsence.setFont(new Font("Arial", Font.ITALIC, 14));
		txtListeAbsence.setBounds(160, 239, 217, 30);
		AccueilEtudiant.getContentPane().add(txtListeAbsence);

		DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"Cours", "Durée", "Date"});

		for (int i = 0; i < listeAbsencesInjustifiees.size(); i++) {
			Object[] row = new Object[]{
					listeAbsencesInjustifiees.get(i).getNomCours(), 
					listeAbsencesInjustifiees.get(i).getDuree(),  
					listeAbsencesInjustifiees.get(i).getDate()};
			model.addRow(row);
		}

		// Créer la table avec les données d'avant
		JTable table = new JTable(model);
		table.setEnabled(false);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		// Créer JScrollPane et ajouter la table dedans
		JScrollPane ScrollAbsJustif = new JScrollPane(table);
		ScrollAbsJustif.setBounds(159, 269, 653, 125);
		AccueilEtudiant.getContentPane().add(ScrollAbsJustif);

		JLabel txtAbsencesPage = new JLabel("Pour valider vos absences ou consulter leur statut, passer par le menu");
		txtAbsencesPage.setFont(new Font("Arial", Font.PLAIN, 14));
		txtAbsencesPage.setBounds(164, 404, 464, 13);
		AccueilEtudiant.getContentPane().add(txtAbsencesPage);
	}

	/**
	 * Affiche les absences justifiées d'un élève à partir de son identifiant
	 * @param identifiant l'idenfiant de l'utilisateur dont on veut connaitre les absences
	 */
	public void displayAbsencesJustifiees(String identifiant) {
		EtudiantDAO etuDAO = new EtudiantDAO();
		ArrayList<Absence> listeAbsencesInjustifiees = etuDAO.getAbsencesJustifiees(identifiant);

		// Texte absences justifiées
		JLabel txtListeAbsencesJustifiees = new JLabel("Liste de vos absences justifiées");
		txtListeAbsencesJustifiees.setHorizontalAlignment(SwingConstants.LEFT);
		txtListeAbsencesJustifiees.setFont(new Font("Arial", Font.ITALIC, 14));
		txtListeAbsencesJustifiees.setBounds(159, 100, 215, 30);
		AccueilEtudiant.getContentPane().add(txtListeAbsencesJustifiees);

		// Créer tableau par défaut
		DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"Cours", "Heures", "Date"});

		// Rempli tableau avec les données de la liste
		for (int i = 0; i < listeAbsencesInjustifiees.size(); i++) {
			Object[] row = new Object[]{
					listeAbsencesInjustifiees.get(i).getNomCours(), 
					listeAbsencesInjustifiees.get(i).getDuree(),  
					listeAbsencesInjustifiees.get(i).getDate()};
			model.addRow(row);
		}

		// Créer table avec les données d'avant
		JTable table = new JTable(model);
		table.setEnabled(false);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		// Créer JScrollPane et ajouter la table dedans
		JScrollPane ScrollAbsJustif = new JScrollPane(table);
		ScrollAbsJustif.setBounds(159, 130, 653, 110);
		AccueilEtudiant.getContentPane().add(ScrollAbsJustif);
	}
}

