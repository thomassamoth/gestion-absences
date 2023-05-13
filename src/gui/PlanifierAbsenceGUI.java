package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.awt.Toolkit;

import javax.swing.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import dao.EtudiantDAO;
import dao.CoursDAO;

import model.Absence;
import model.Utilisateur;

/**
 * Classe pour planifier une absence
 * @author Thomas Beyet
 */
public class PlanifierAbsenceGUI {
	private JFrame AbsencesEtudiant;
	private JTextField dateField;

	java.sql.Date sqlDate;

	/**
	 * L'id de la matiere que l'on souhaite récuperer
	 */
	private int IDMatiere;

	/**
	 * Créer la fenêtre de gestion des absences pour l'étudiant
	 */
	public PlanifierAbsenceGUI(Utilisateur util) {
		initializeWindow();
		initializeHeader(util);
		initializeSidebar(util);
		initializeContent(util);
	}

	public void initializeWindow() {
		// Fenêtre
		AbsencesEtudiant = new JFrame();
		AbsencesEtudiant.setResizable(false);
		AbsencesEtudiant.getContentPane().setBackground(new Color(255, 255, 255));
		AbsencesEtudiant.setVisible(true);
		AbsencesEtudiant.setTitle("Justification Absences");
		AbsencesEtudiant.setSize(853, 480);
		AbsencesEtudiant.setLocationRelativeTo(null); // Centre fenêtre dans l'écran
		AbsencesEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AbsencesEtudiant.getContentPane().setLayout(null);
	}

	/**
	 * 
	 * @param util l'utilisateur dont on veut afficher le username
	 */
	private void initializeHeader(Utilisateur util) {
		// Header
		JPanel header = new JPanel();
		header.setBackground(new Color(255, 25, 25));
		header.setBounds(0, 0, 839, 50);
		header.setLayout(null);
		AbsencesEtudiant.getContentPane().add(header);

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
			AbsencesEtudiant.dispose();
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

	/**
	 * Initialise la barre de navigation
	 * 
	 * @param util utilisé le bouton annuler por afficher ses infos 
	 * sur 
	 */
	private void initializeSidebar(Utilisateur util) {
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

		menuBtnAbsences.setBackground(new Color(255, 31, 31));

		// Change couleur fond si cliqué + action bouton
		menuBtnAbsences.addActionListener(e -> {
			menuBtnAbsences.setBackground(new Color(255, 31, 31));
			menuBtnPlanning.setBackground(new Color(255, 102, 102));
			AbsencesEtudiant.dispose();
			new AbsencesEtudiantGUI(util);
		});
	}

	/**
	 * Initialise le contenu de la fenêtre
	 * 
	 * @param util l'utilisatur connecté
	 */
	private void initializeContent(Utilisateur util) {
		JPanel content = new JPanel();
		content.setVisible(true);
		content.setBackground(new Color(255, 255, 255));
		content.setBounds(140, 50, 699, 393);
		content.setLayout(null);
		AbsencesEtudiant.getContentPane().add(content);

		// JLabel Bonjour :)
		JLabel txtPlanifierAbsence = new JLabel("Planifier Absence");
		txtPlanifierAbsence.setBounds(20, 10, 148, 30);
		txtPlanifierAbsence.setFont(new Font("Arial", Font.BOLD, 14));
		content.add(txtPlanifierAbsence);

		// Remplir la JComboBox avec les matieres
		EtudiantDAO etuDAO = new EtudiantDAO();
		CoursDAO coursDAO = new CoursDAO();

		// ArrayList des matières
		ArrayList<String> listeDeroulanteMat = coursDAO.getListeMatieres();
		listeDeroulanteMat.add(0, ""); // Ajoute un element vide pour que l'utilisateur doive choisir quelquechose

		// Créer un tableau à partir de l'arrayList pour mettre entrée de la combobox
		Object[] listeMatieresArray = listeDeroulanteMat.toArray();


		JComboBox listeMat = new JComboBox(listeMatieresArray);
		listeMat.setBounds(127, 83, 117, 21);
		content.add(listeMat);
		
		// Action de la JComboBox listeMatiere
		listeMat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// récupère ID de la matière
				IDMatiere = listeMat.getSelectedIndex();
				if (IDMatiere == 0) {
					JOptionPane.showMessageDialog(null, "Impossible de choisir une matière vide.", "Warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// JButton Annuler
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(10, 181, 85, 21);
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Arial", Font.PLAIN, 10));
		btnAnnuler.setBorderPainted(false);
		btnAnnuler.setBackground(new Color(218, 54, 72));
		content.add(btnAnnuler);

		// Action du JButton Annuler
		btnAnnuler.addActionListener(e -> {
			AbsencesEtudiant.dispose();
			new AccueilEtudiantGUI(util);
		});

		JLabel lblMatire = new JLabel("Matière");
		lblMatire.setBounds(127, 61, 45, 13);
		content.add(lblMatire);

		dateField = new JTextField();
		dateField.setBounds(21, 84, 96, 19);
		content.add(dateField);
		dateField.setColumns(10);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(20, 61, 45, 13);
		content.add(lblDate);

		// JButton Valider
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(105, 181, 85, 21);
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Arial", Font.PLAIN, 10));
		btnValider.setBorderPainted(false);
		btnValider.setBackground(new Color(37, 167, 67));
		content.add(btnValider);

		// Action bouton valider
		btnValider.addActionListener(e -> {
			int idEtudiant = etuDAO.getIDEtudiant(util.getUtilisateurID());
			String dateChamp = null;
			
			// Champs remplis
			if (dateField.getText().length() == 0 || listeMat.getSelectedIndex() == 0) {
				// Joue audio erreur :)
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Veuillez saisir une date ou choisir une matière.");
			} else {
				dateChamp = dateField.getText();

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Format de la date
				try {
					java.util.Date utilDate = dateFormat.parse(dateChamp); // on convertit la chaine en date
					sqlDate = new java.sql.Date(utilDate.getTime());

					/* Vérification si la date est dans le futur pour voir si c'est bien une absence planifiée */
					if (sqlDate.toLocalDate().isAfter(LocalDate.now()) == false) {
						JOptionPane.showMessageDialog(null, "Impossible de planifier une absence dans le passé !", "Warning",JOptionPane.WARNING_MESSAGE);
					}
					else {
						// Execute requete
						if (etuDAO.ajouterAbsencePlanifiee(idEtudiant, sqlDate, IDMatiere) == 1) {
							System.out.println("Ajout effectué");
						} else
							System.out.println("Erreur ajout");
					}
				} catch (Exception ea) {
					ea.printStackTrace();
				}
				
			}

		});
	}
}
