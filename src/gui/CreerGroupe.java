package gui;

import java.awt.*;
import javax.swing.*;

//Packages
import dao.*;
//import model.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CreerGroupe {
	private JFrame creerGroupe;
	private JTextField fieldNumero;
	private JTextField fieldCapacite;


	public CreerGroupe(JLabel txtMessages) {
		initialize(txtMessages);
	}

	private void initialize(JLabel txtMessages) {
		// Fenêtre
		creerGroupe = new JFrame();
		creerGroupe.setResizable(false);
		creerGroupe.getContentPane().setBackground(new Color(255, 255, 255));
		creerGroupe.setVisible(true);
		creerGroupe.setTitle("Creer groupe");
		creerGroupe.setSize(320, 180);
		creerGroupe.setLocation(650, 400);
		creerGroupe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		creerGroupe.getContentPane().setLayout(null);
		
		JLabel txtAjoutGroupe = new JLabel("Ajouter groupe");
		txtAjoutGroupe.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtAjoutGroupe.setBackground(new Color(255, 255, 255));
		txtAjoutGroupe.setBounds(103, 10, 80, 15);
		creerGroupe.getContentPane().add(txtAjoutGroupe);
		
		JLabel txtNumeroGroupe = new JLabel("Numéro groupe");
		txtNumeroGroupe.setBounds(47, 30, 91, 13);
		creerGroupe.getContentPane().add(txtNumeroGroupe);
		
		fieldNumero = new JTextField();
		fieldNumero.setBounds(47, 52, 74, 19);
		creerGroupe.getContentPane().add(fieldNumero);
		fieldNumero.setColumns(10);
		
		JLabel lblCapcitMaximale = new JLabel("Capacité maximale");
		lblCapcitMaximale.setBounds(158, 30, 103, 13);
		creerGroupe.getContentPane().add(lblCapcitMaximale);
		
		fieldCapacite = new JTextField();
		fieldCapacite.setColumns(10);
		fieldCapacite.setBounds(158, 52, 74, 19);
		creerGroupe.getContentPane().add(fieldCapacite);
		
		JButton btnOK = new JButton("OK");
		btnOK.setBounds(93, 85, 85, 21);
		btnOK.setBackground(new Color(37, 167, 67));
		creerGroupe.getContentPane().add(btnOK);
		
		JLabel txtErreur = new JLabel("");
		txtErreur.setBackground(new Color(255, 255, 255));
		txtErreur.setHorizontalAlignment(SwingConstants.CENTER);
		txtErreur.setBounds(47, 120, 214, 13);
		txtErreur.setOpaque(true);
		creerGroupe.getContentPane().add(txtErreur);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionnaireDAO gestio = new GestionnaireDAO();
				if(gestio.ajouterGroupe(Integer.valueOf(fieldNumero.getText()), Integer.valueOf(fieldCapacite.getText())) == 1) {
					creerGroupe.dispose();
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
