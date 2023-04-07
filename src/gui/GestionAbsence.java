package gui;

import java.awt.*;
import javax.swing.*;

// Packages
import dao.UtilisateurDAO;
import model.Utilisateur;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionAbsence {

	private JFrame windowConnexion;
	private JTextField champIdentifiant;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAbsence window = new GestionAbsence();
					window.windowConnexion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionAbsence() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		windowConnexion = new JFrame();
		windowConnexion.setTitle("Connexion");
		windowConnexion.getContentPane().setBackground(new Color(255, 80, 80));
		windowConnexion.setBackground(new Color(0, 0, 0));
		windowConnexion.setResizable(false);
		windowConnexion.setSize(853, 480);
		windowConnexion.setLocationRelativeTo(null);
		windowConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowConnexion.getContentPane().setLayout(null);

		// Titre Esigelec
		JLabel ESIGELEC = new JLabel("ESIGELEC");
		ESIGELEC.setForeground(new Color(255, 255, 255));
		ESIGELEC.setFont(new Font("Arial", Font.PLAIN, 30));
		ESIGELEC.setBounds(51, 33, 265, 45);
		windowConnexion.getContentPane().add(ESIGELEC);

		// Texte identifiant
		JLabel Identifiant = new JLabel("Identifiant");
		Identifiant.setForeground(new Color(255, 255, 255));
		Identifiant.setFont(new Font("Arial", Font.BOLD, 15));
		Identifiant.setBounds(348, 133, 100, 33);
		windowConnexion.getContentPane().add(Identifiant);

		// Field Identifiant
		champIdentifiant = new JTextField();
		champIdentifiant.setFont(new Font("Arial", Font.PLAIN, 10));
		champIdentifiant.setToolTipText("");
		champIdentifiant.setBounds(348, 169, 169, 30);
		windowConnexion.getContentPane().add(champIdentifiant);
		champIdentifiant.setColumns(10);

		// Texte mdp
		JLabel txtMotDePasse = new JLabel("Mot de passe");
		txtMotDePasse.setForeground(Color.WHITE);
		txtMotDePasse.setFont(new Font("Arial", Font.BOLD, 15));
		txtMotDePasse.setBounds(348, 215, 100, 33);
		windowConnexion.getContentPane().add(txtMotDePasse);

		// button connexion
		JButton buttonConnexion = new JButton("Connexion");
		buttonConnexion.setBackground(new Color(248, 249, 250));
		buttonConnexion.setFont(new Font("Arial", Font.PLAIN, 10));
		buttonConnexion.setBounds(393, 311, 85, 21);
		windowConnexion.getContentPane().add(buttonConnexion);

		// Password field
		passwordField = new JPasswordField();
		passwordField.setBounds(348, 258, 169, 30);
		windowConnexion.getContentPane().add(passwordField);

		buttonConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (champIdentifiant.getText().length() <= 0
						|| String.valueOf(passwordField.getPassword()).length() <= 0) {
					JOptionPane.showMessageDialog(null, "Veuillez compléter le/les champ(s) manquants. ", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}

				else {
					connect(champIdentifiant.getText(), String.valueOf(passwordField.getPassword()));
				}
			}
		});
	}

	/**
	 * Vérifie les bons identifiants de l'utilisateur et affiche la fenetre suivante
	 * en fonction de son statut.
	 * 
	 * @param username
	 * @param password
	 */
	public void connect(String username, String password) {
		UtilisateurDAO userDAO = new UtilisateurDAO();
		Utilisateur user = userDAO.getInfoUser(username, password);

		if (user != null) {
			int utilisateurStatut = userDAO.getStatutUtilisateur(username);
			System.out.println(utilisateurStatut);
			switch (utilisateurStatut) {
			case 1:
				// Création fenêtre gestion des absences
				new ValidationAbsencesGUI(user);
				break;

			/*case 2:
				JOptionPane.showMessageDialog(null, "Pas encore de fenêtre disponible pour les enseignants.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
				break;*/

			case 3:
				windowConnexion.dispose();
				new AccueilEtudiantGUI(user);
				break;

			default:
				JOptionPane.showMessageDialog(null, "Error : cet utilisateur n'a pas de statut. ", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
	}
}
