package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

// Packages
import dao.UtilisateurDAO;
import model.Utilisateur;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ValidationAbsences {

	private JFrame validationAbsences;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ValidationAbsences window = new ValidationAbsences();
					window.validationAbsences.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the window.
	 */
	public ValidationAbsences() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		   
	    
	    // Créer nouvelle fenêtre 
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
		validationAbsences.setBackground(new Color(192, 192, 192));
				
		validationAbsences.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 839, 50);
		validationAbsences.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel titreValidationAbsences = new JLabel("Validation Absences");
		titreValidationAbsences.setBounds(307, 10, 234, 40);
		panel.add(titreValidationAbsences);
		titreValidationAbsences.setHorizontalAlignment(SwingConstants.CENTER);
		titreValidationAbsences.setFont(new Font("Arial", Font.BOLD, 24));
		
		// Menu latéral
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 0, 140, 443);
		validationAbsences.getContentPane().add(sidebar);
		sidebar.setLayout(null);
		
		JLabel sidebarAbsences = new JLabel("Absences");
		sidebarAbsences.setBackground(new Color(255, 0, 0));
		sidebarAbsences.setHorizontalAlignment(SwingConstants.CENTER);
		sidebarAbsences.setFont(new Font("Arial", Font.PLAIN, 20));
		sidebarAbsences.setBounds(0, 86, 140, 33);
		sidebar.add(sidebarAbsences);
		
		JLabel sidebarPlanning = new JLabel("Planning");
		sidebarPlanning.setHorizontalAlignment(SwingConstants.CENTER);
		sidebarPlanning.setFont(new Font("Arial", Font.PLAIN, 20));
		sidebarPlanning.setBackground(Color.RED);
		sidebarPlanning.setBounds(0, 129, 140, 33);
		sidebar.add(sidebarPlanning);
		
	}
}
