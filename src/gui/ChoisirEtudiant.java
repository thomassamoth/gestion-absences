package gui;

import java.awt.*;
import javax.swing.*;

//Packages
import dao.*;
import model.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ChoisirEtudiant {
	private JFrame choisirEtudiant;
	private JTextField fieldPrenom;
	private JTextField fieldNom;


	public ChoisirEtudiant(JLabel txtMessages, Utilisateur user) {
		initialize(txtMessages, user);
	}

	private void initialize(JLabel txtMessages, Utilisateur user) {
		// Fenêtre
		choisirEtudiant = new JFrame();
		choisirEtudiant.setResizable(false);
		choisirEtudiant.getContentPane().setBackground(new Color(255, 255, 255));
		choisirEtudiant.setVisible(true);
		choisirEtudiant.setTitle("Choisir Etudiant");
		choisirEtudiant.setSize(330, 210);
		choisirEtudiant.setLocation(650, 400);
		choisirEtudiant.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		choisirEtudiant.getContentPane().setLayout(null);

		JLabel txtAjoutGroupe = new JLabel("Choisir Etudiant");
		txtAjoutGroupe.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtAjoutGroupe.setBackground(new Color(255, 255, 255));
		txtAjoutGroupe.setBounds(92, 10, 91, 15);
		choisirEtudiant.getContentPane().add(txtAjoutGroupe);

		JLabel txtNumeroGroupe = new JLabel("Prenom Etudiant");
		txtNumeroGroupe.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtNumeroGroupe.setBounds(10, 35, 91, 13);
		choisirEtudiant.getContentPane().add(txtNumeroGroupe);

		fieldPrenom = new JTextField();
		fieldPrenom.setBounds(10, 57, 74, 19);
		choisirEtudiant.getContentPane().add(fieldPrenom);
		fieldPrenom.setColumns(10);

		JLabel lblCapcitMaximale = new JLabel("Nom");
		lblCapcitMaximale.setBounds(158, 35, 103, 13);
		choisirEtudiant.getContentPane().add(lblCapcitMaximale);

		fieldNom = new JTextField();
		fieldNom.setColumns(10);
		fieldNom.setBounds(158, 57, 74, 19);
		choisirEtudiant.getContentPane().add(fieldNom);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(158, 86, 103, 21);
		btnModifier.setBackground(new Color(37, 167, 67));
		choisirEtudiant.getContentPane().add(btnModifier);


		JLabel txtErreur = new JLabel("");
		txtErreur.setBackground(new Color(255, 255, 255));
		txtErreur.setHorizontalAlignment(SwingConstants.CENTER);
		txtErreur.setBounds(47, 120, 214, 13);
		txtErreur.setOpaque(true);
		choisirEtudiant.getContentPane().add(txtErreur);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBackground(new Color(218, 54, 72));
		btnAnnuler.setBounds(10, 89, 103, 21);
		choisirEtudiant.getContentPane().add(btnAnnuler);

		// Action du JButton Annuler
		btnAnnuler.addActionListener(e -> {
			choisirEtudiant.dispose();
			new AccueilGestionnaireGUI(user);
		});


		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldNom.getText().length() <= 0 || fieldPrenom.getText().length() <= 0) {
					JOptionPane.showMessageDialog(null, "Veuillez compléter le/les champ(s) manquants. ", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
				else {
					EtudiantDAO etu = new EtudiantDAO();
					if(etu.verifEtudiantExiste(fieldPrenom.getText(), fieldNom.getText()) != 1) {
						JOptionPane.showMessageDialog(null, "Etudiant inexistant. Veuillez réessayer.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					else {
						choisirEtudiant.dispose();
						new ModifierEtudiantGUI(user, fieldPrenom.getText(), fieldNom.getText());
					}

				}

			}
		});
	}
}
