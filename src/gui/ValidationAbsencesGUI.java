// -*- coding: utf-8 -*-

package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;

// Packages
//import dao.UtilisateurDAO;
import dao.AbsenceDAO;

import model.*;
import javax.swing.border.LineBorder;


/**
 * Classe pour afficher la fenêtre de validation des absences
 * @author Thomas
 * @version 1.0
 */
public class ValidationAbsencesGUI {
	private JFrame validationAbsences;

	/**
	 * Créer la fenêtre pour la validation des absences
	 * @param util l'utilisateur dont on veut garder les infos
	 */
	public ValidationAbsencesGUI(Utilisateur util) {
		initialize(util);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Utilisateur util) {

		creerFenetreValidationAbsences();

		/** Header */
		JPanel header = new JPanel();
		header.setBackground(new Color(240, 240, 240));
		header.setBounds(0, 0, 839, 50);
		header.setLayout(null);
		validationAbsences.getContentPane().add(header);		

		/** Titre Header */
		JLabel titreValidationAbsences = new JLabel("VALIDATION ABSENCES");
		titreValidationAbsences.setBackground(new Color(179, 179, 179));
		titreValidationAbsences.setBounds(140, 10, 699, 40);
		titreValidationAbsences.setHorizontalAlignment(SwingConstants.CENTER);
		titreValidationAbsences.setFont(new Font("Arial", Font.BOLD, 24));
		header.add(titreValidationAbsences);

		// Affiche identifiant utilisateur
		JLabel usernameLabel = new JLabel("");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		usernameLabel.setBounds(0, 0, 140, 50);
		usernameLabel.setText(util.getIdentifiant());
		header.add(usernameLabel);

		// Menu latéral
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 0, 140, 443);
		sidebar.setLayout(null);
		validationAbsences.getContentPane().add(sidebar);

		// Bouton Menu Absences
		JButton menuBtnAbsences = new JButton("Absences");
		menuBtnAbsences.setForeground(new Color(255, 255, 255));
		menuBtnAbsences.setFont(new Font("Arial", Font.PLAIN, 20));
		menuBtnAbsences.setBorderPainted(false);
		menuBtnAbsences.setBackground(new Color(255, 102, 102));
		menuBtnAbsences.setFocusPainted(false); // Bordure text invisible
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
			// Action here
		});


		/* == Contenu == */
		JLabel titreAbsenceNonTraitee = new JLabel("Liste des absence(s) non traitée(s)");
		titreAbsenceNonTraitee.setFont(new Font("Arial", Font.BOLD, 16));
		titreAbsenceNonTraitee.setBounds(150, 60, 368, 28);
		validationAbsences.getContentPane().add(titreAbsenceNonTraitee);

		displayAbsencesNonTraitees();
	}


	/**
	 * Créer la fenêtre de validation des absences.
	 */
	public void creerFenetreValidationAbsences() {
		// Fenêtre
		validationAbsences = new JFrame();
		validationAbsences.setVisible(true);
		validationAbsences.setTitle("Gestionnaire - Validation des absences");
		validationAbsences.setResizable(false);
		validationAbsences.setSize(853, 480);

		//Centre fenêtre dans l'écran
		validationAbsences.setLocationRelativeTo(null);
		validationAbsences.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Background
		validationAbsences.getContentPane().setBackground(new Color(255, 255, 255));
		validationAbsences.getContentPane().setLayout(null);
	}

	/**
	 * Affiche la liste des absences non traitées dans un tableau
	 */
	public void displayAbsencesNonTraitees() {
		AbsenceDAO absDAO = new AbsenceDAO();
		ArrayList<Absence> listeAbsencesNT = absDAO.getAbsencesNonTraitees();

		DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"Nom", "Prénom", "Matière", "Date"});

		for (int i = 0; i < listeAbsencesNT.size(); i++) {
			Object[] row = new Object[]{
					listeAbsencesNT.get(i).getNomEtudiant(), 
					listeAbsencesNT.get(i).getPrenomEtudiant(),  
					listeAbsencesNT.get(i).getDate(),
					listeAbsencesNT.get(i).getNomCours()};
			model.addRow(row);
		}

		// Créer la table avec les données d'avant
		JTable table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		// Créer JScrollPane et ajouter la table dedans
		JScrollPane test = new JScrollPane(table);
		test.setBounds(160, 98, 653, 296);
		validationAbsences.getContentPane().add(test);
	}
}
