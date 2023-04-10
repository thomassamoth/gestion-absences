package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import javax.swing.border.LineBorder;


// Packages
import dao.*;
import model.*;

/**
 * Classe pour afficher la fenêtre des absences d'un élève
 * 
 * @author Thomas
 * @version 1.0
 */
public class AbsencesEtudiantGUI {

	private JFrame AbsencesEtudiant;
	private JTable table;

	/**
	 * Create the window with a user input
	 */
	public AbsencesEtudiantGUI(Utilisateur util, String prenom) {
		initialize(util, prenom);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Utilisateur util, String prenom) {
		// Fenêtre
		AbsencesEtudiant = new JFrame();
		AbsencesEtudiant.setResizable(false);
		AbsencesEtudiant.getContentPane().setBackground(new Color(255, 255, 255));
		AbsencesEtudiant.setVisible(true);
		AbsencesEtudiant.setTitle("Absences - Etudiant");
		AbsencesEtudiant.setSize(853, 480);

		// Centre fenêtre dans l'écran
		AbsencesEtudiant.setLocationRelativeTo(null);
		AbsencesEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AbsencesEtudiant.getContentPane().setLayout(null);

		// Header
		JPanel header = new JPanel();
		header.setBackground(new Color(255, 25, 25));
		header.setBounds(0, 0, 839, 50);
		header.setLayout(null);
		AbsencesEtudiant.getContentPane().add(header);

		// Affichage de l'identifiant de l'utilisateur
		JLabel usernameLabel = new JLabel("");
		usernameLabel.setForeground(new Color(255, 255, 255));
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		usernameLabel.setBounds(0, 0, 140, 50);
		usernameLabel.setText(util.getIdentifiant());
		header.add(usernameLabel);

		// Titre Esigelec
		JLabel ESIGELEC = new JLabel("Absences");
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
		AbsencesEtudiant.getContentPane().add(sidebar);

		// Bouton Menu absences
		JButton menuBtnAbsences = new JButton("Absences");
		menuBtnAbsences.setForeground(new Color(255, 255, 255));
		menuBtnAbsences.setFont(new Font("Arial", Font.PLAIN, 20));
		menuBtnAbsences.setBorderPainted(false);
		menuBtnAbsences.setFocusPainted(false); // Bordure text invisible
		menuBtnAbsences.setBackground(new Color(255, 31, 31));
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

		JLabel txtBonjour = new JLabel("");
		txtBonjour.setFont(new Font("Arial", Font.BOLD, 14));
		txtBonjour.setBounds(160, 60, 148, 30);
		txtBonjour.setText("Bonjour " + prenom);
		AbsencesEtudiant.getContentPane().add(txtBonjour);

		//displayAbsencesInjustifiees(util.getIdentifiant());
	}
}