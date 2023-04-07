package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;

// Packages
//import dao.UtilisateurDAO;
import dao.AbsenceDAO;

import model.*;


/**
 * Classe pour afficher la fenêtre de validation des absences
 * @author Thomas
 * @version 1.0
 */
public class AccueilEtudiantGUI {

	private JFrame AccueilEtudiant;
	
	/**
	 * Create the window with a user input
	 */
	public AccueilEtudiantGUI(Utilisateur util) {
		initialize(util);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Utilisateur util) {
		creerFenetreValidationAbsences();
	}
	
	
	/**
	 * Créer la fenêtre de validation des absences.
	 */
	public void creerFenetreValidationAbsences() {
		// Fenêtre
		AccueilEtudiant = new JFrame();
		AccueilEtudiant.getContentPane().setBackground(new Color(255, 102, 102));
		AccueilEtudiant.setVisible(true);
		AccueilEtudiant.setTitle("Accueil - Etudiant");
		AccueilEtudiant.setResizable(false);
		AccueilEtudiant.setSize(853, 480);
		
		//Centre fenêtre dans l'écran
		AccueilEtudiant.setLocationRelativeTo(null);
		AccueilEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Titre Esigelec
		JLabel ESIGELEC = new JLabel("ESIGELEC");
		ESIGELEC.setForeground(new Color(255, 255, 255));
		ESIGELEC.setFont(new Font("Arial", Font.PLAIN, 30));
		ESIGELEC.setBounds(51, 33, 265, 45);
		AccueilEtudiant.getContentPane().add(ESIGELEC);
	}
}
