package gui;

import java.awt.*;
import javax.swing.*;

//Packages
import dao.*;
//import model.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ChoisirEtudiant {
	private JFrame choisirEtudiant;
	private JTextField fieldNumero;
	private JTextField fieldCapacite;


	public ChoisirEtudiant(JLabel txtMessages) {
		initialize(txtMessages);
	}

	private void initialize(JLabel txtMessages) {
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
		
		JLabel txtNumeroGroupe = new JLabel("Prénom Etudiant");
		txtNumeroGroupe.setBounds(10, 35, 91, 13);
		choisirEtudiant.getContentPane().add(txtNumeroGroupe);
		
		fieldNumero = new JTextField();
		fieldNumero.setBounds(10, 57, 74, 19);
		choisirEtudiant.getContentPane().add(fieldNumero);
		fieldNumero.setColumns(10);
		
		JLabel lblCapcitMaximale = new JLabel("Nom");
		lblCapcitMaximale.setBounds(158, 35, 103, 13);
		choisirEtudiant.getContentPane().add(lblCapcitMaximale);
		
		fieldCapacite = new JTextField();
		fieldCapacite.setColumns(10);
		fieldCapacite.setBounds(158, 57, 74, 19);
		choisirEtudiant.getContentPane().add(fieldCapacite);
		
		JButton btnOK = new JButton("Modifier");
		btnOK.setBounds(158, 86, 103, 21);
		btnOK.setBackground(new Color(37, 167, 67));
		choisirEtudiant.getContentPane().add(btnOK);
		
		JLabel txtErreur = new JLabel("");
		txtErreur.setBackground(new Color(255, 255, 255));
		txtErreur.setHorizontalAlignment(SwingConstants.CENTER);
		txtErreur.setBounds(47, 120, 214, 13);
		txtErreur.setOpaque(true);
		choisirEtudiant.getContentPane().add(txtErreur);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(new Color(37, 167, 67));
		btnSupprimer.setBounds(10, 86, 91, 21);
		choisirEtudiant.getContentPane().add(btnSupprimer);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionnaireDAO gestio = new GestionnaireDAO();
				if(gestio.ajouterGroupe(Integer.valueOf(fieldNumero.getText()), Integer.valueOf(fieldCapacite.getText())) == 1) {
					choisirEtudiant.dispose();
					txtMessages.setText("Le groupe a bien été ajouté");
					txtMessages.setBackground(new Color(217, 237, 218));
				}
				else {
					txtErreur.setText("Erreur lors de l'ajout d'un groupe");
					txtErreur.setBackground(new Color(248, 215,218));
				}
			}
		});
	}
}
