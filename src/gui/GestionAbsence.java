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
	private JTextField passwordClearField;

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
		windowConnexion.setTitle("Gestion des absences");
		windowConnexion.getContentPane().setBackground(new Color(255, 102, 102));
		windowConnexion.setBackground(new Color(255, 128, 128));
		windowConnexion.setResizable(false);
		windowConnexion.setBounds(100, 100, 853, 480);
		windowConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowConnexion.getContentPane().setLayout(null);
		
		// Titre Esigelec
		JLabel ESIGELEC = new JLabel("ESIGELEC");
		ESIGELEC.setForeground(new Color(255, 255, 255));
		ESIGELEC.setFont(new Font("Tahoma", Font.PLAIN, 30));
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
		champIdentifiant.setBounds(348, 169, 169, 33);
		windowConnexion.getContentPane().add(champIdentifiant);
		champIdentifiant.setColumns(10);
		
		// Texte mdp
		JLabel txtMotDePasse = new JLabel("Mot de passe");
		txtMotDePasse.setForeground(Color.WHITE);
		txtMotDePasse.setFont(new Font("Arial", Font.BOLD, 15));
		txtMotDePasse.setBounds(348, 215, 100, 33);
		windowConnexion.getContentPane().add(txtMotDePasse);
		
		// button connection
		JButton buttonConnexion = new JButton("Connexion");
		buttonConnexion.setBackground(new Color(248, 249, 250));
		buttonConnexion.setFont(new Font("Arial", Font.PLAIN, 10));
		buttonConnexion.setBounds(393, 311, 85, 21);
		windowConnexion.getContentPane().add(buttonConnexion);
		
		passwordClearField = new JTextField();
		passwordClearField.setToolTipText("");
		passwordClearField.setFont(new Font("Arial", Font.PLAIN, 10));
		passwordClearField.setColumns(10);
		passwordClearField.setBounds(348, 251, 169, 33);
		windowConnexion.getContentPane().add(passwordClearField);
		
		//buttonConnexion.addActionListener(new ActionListener(this);
		
		buttonConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(champIdentifiant.getText().length() <= 0 || passwordClearField.getText().length() <= 0) {
					JOptionPane.showMessageDialog(null,"Veuillez compléter le/les champ(s) manquants. ", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
				else if(champIdentifiant.getText().length() > 0 && passwordClearField.getText().length() > 0) {
					String username = champIdentifiant.getText();
					String password = passwordClearField.getText();
					
					getUserCredentials(username, password);					
				}
			}
		});
	}
	
	public void getUserCredentials(String username, String password) {
		UtilisateurDAO userDAO = new UtilisateurDAO();
		Utilisateur user = userDAO.getInfoUser(username, password);
		
		if(user != null) {
			JOptionPane.showMessageDialog(null,"Connecté. ", "Connecté", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else {
			JOptionPane.showMessageDialog(null,"Identifiant ou mot de passe incorrect. ", "Dialog", JOptionPane.ERROR_MESSAGE);
		}
	}
}
