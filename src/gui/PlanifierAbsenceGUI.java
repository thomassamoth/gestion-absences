package gui;

import java.awt.*;

import java.util.Random;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

//Imports File Chooser
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import dao.EtudiantDAO;
import dao.CoursDAO;

import model.Absence;
import model.Utilisateur;

/**
 * Classe pour planifier une absence
 * 
 * @author Thomas Beyet
 */
public class PlanifierAbsenceGUI {
	private JFrame AbsencesEtudiant;
	private JTextField dateField;
	// private boolean ajoutJustificatif = false;
	private java.sql.Date sqlDate;
	private File fichierSelectionne;

	/**
	 * l'id de la matiere que l'on souhaite r&eacute;cuperer
	 */
	private int idMatiere;

	/**
	 * Cr&eacute;er la fen&ecirc;tre de gestion des absences pour l'&eacute;tudiant
	 * 
	 * @param util l'utilisateur
	 */
	public PlanifierAbsenceGUI(Utilisateur util) {
		initializeWindow();
		initializeHeader(util);
		initializeSidebar(util);
		initializeContent(util);
	}

	/**
	 * Initialise la fenetre
	 */
	public void initializeWindow() {
		// Fenêtre
		AbsencesEtudiant = new JFrame();
		AbsencesEtudiant.setResizable(false);
		AbsencesEtudiant.getContentPane().setBackground(new Color(255, 255, 255));
		AbsencesEtudiant.setVisible(true);
		AbsencesEtudiant.setTitle("Planification Absences");
		AbsencesEtudiant.setSize(853, 480);
		AbsencesEtudiant.setLocationRelativeTo(null); // Centre fenêtre dans l'écran
		AbsencesEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AbsencesEtudiant.getContentPane().setLayout(null);
	}

	/**
	 * Initialise l'ent&circ;te de la fen&circ;tre
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
	 * @param util utilis&eacute; le bouton annuler por afficher ses infos sur
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
	 * Initialise le contenu de la fen&ecirc;tre
	 * 
	 * @param util l'utilisateur connect&eacute;
	 */
	@SuppressWarnings("rawtypes")
	private void initializeContent(Utilisateur util) {
		// File fichierAbsence = null;
		String dossierDestination = getDossierDestinationDistance();
		// File destinationFichier = null;

		JPanel content = new JPanel();
		content.setVisible(true);
		content.setBackground(new Color(255, 255, 255));
		content.setBounds(140, 50, 699, 393);
		content.setLayout(null);
		AbsencesEtudiant.getContentPane().add(content);

		/* === JLABEL === */
		// JLabel Bonjour :)
		JLabel txtPlanifierAbsence = new JLabel("Planifier Absence");
		txtPlanifierAbsence.setBounds(20, 10, 148, 30);
		txtPlanifierAbsence.setFont(new Font("Arial", Font.BOLD, 14));
		content.add(txtPlanifierAbsence);

		JLabel lblMatiere = new JLabel("Matière");
		lblMatiere.setBounds(127, 61, 45, 13);
		content.add(lblMatiere);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(20, 61, 45, 13);
		content.add(lblDate);

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
				idMatiere = listeMat.getSelectedIndex();
				if (idMatiere == 0) {
					JOptionPane.showMessageDialog(null, "Impossible de choisir une matière vide.", "Warning",
							JOptionPane.WARNING_MESSAGE);
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

		// Action du bouton Annuler
		btnAnnuler.addActionListener(e -> {
			AbsencesEtudiant.dispose();
			new AccueilEtudiantGUI(util);
		});

		dateField = new JTextField();
		dateField.setBounds(21, 84, 96, 19);
		content.add(dateField);
		dateField.setColumns(10);

		// JButton Valider
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(105, 181, 85, 21);
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Arial", Font.PLAIN, 10));
		btnValider.setBorderPainted(false);
		btnValider.setBackground(new Color(37, 167, 67));
		content.add(btnValider);

		JButton btnAjoutJustif = new JButton("Ajouter justificatif");
		btnAjoutJustif.setBounds(20, 132, 134, 21);
		content.add(btnAjoutJustif);

		btnAjoutJustif.addActionListener(e -> {
			// File fichierAbsences = choixFichier(btnAjoutJustif);
			// if (fichierAbsences != null) {
			fichierSelectionne = choixFichier(btnAjoutJustif);
			// }
		});

		/*-- Actions bouton Valider --*/
		btnValider.addActionListener(e -> {
			int idEtudiant = etuDAO.getIDEtudiant(util.getUtilisateurID());
			String dateChamp = dateField.getText();

			// Champs pas remplis correctement
			if (dateChamp.isEmpty() || listeMat.getSelectedIndex() == 0) {
				// Joue audio erreur :)
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Veuillez saisir une date ou choisir une matière.");
			} else if (fichierSelectionne == null) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Veuillez sélectionner un justificatif d'absence.");
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Format de la date
				try {
					java.util.Date utilDate = dateFormat.parse(dateChamp); // on convertit la chaîne en date
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

					// Vérifie si la date est dans le futur pour voir si c'est une absence planifiée
					if (sqlDate.toLocalDate().isBefore(LocalDate.now())) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Impossible de planifier une absence dans le passé !",
								"Warning", JOptionPane.WARNING_MESSAGE);
					} else {
						File destinationFichier = new File(dossierDestination, fichierSelectionne.getName());
						try {
							// Copie du fichier dans le dossier de destination
							Files.copy(fichierSelectionne.toPath(), destinationFichier.toPath(),
									StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException ex) {
							ex.printStackTrace();
						}

						// Exécute la requête
						if (etuDAO.ajouterAbsencePlanifiee(idEtudiant, sqlDate, idMatiere) == 1) {
							System.out.println("Ajout effectué");
						} else {
							System.out.println("Erreur ajout");
						}
					}
				} catch (Exception et) {
					et.printStackTrace();
				}
			}
		});
	}

	/**
	 * Choix du fichier pour l'absence planifi&eacute;e.
	 * 
	 * @param btnAjoutJustif
	 */
	private File choixFichier(JButton btnAjoutJustif) {
		JFileChooser fileChooser = new JFileChooser();

		// Fichiers pdf seulement affichés
		fileChooser.setDialogTitle("Choisir un justificatif d'absence");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers PDF", "pdf"));

		// Afficher explorateur fichiers et récupérer résultat
		int choixUser = fileChooser.showOpenDialog(null);

		if (choixUser == JFileChooser.APPROVE_OPTION) {
			btnAjoutJustif.setEnabled(true);
			btnAjoutJustif.setCursor(new Cursor(Cursor.HAND_CURSOR));

			File fichierAbsence = fileChooser.getSelectedFile();

			// Créer le dossier de destination s'il n'existe pas
			String dossierDest = getDossierDestinationDistance();
			if (dossierDest != null) {

				// Récupérer le fichier sélectionné
				// File destinationFichier = new File(dossierDest, fichierAbsence.getName());
				return fichierAbsence;
			}
		}
		return null;
	}

	/**
	 * Créer le lien fictif pour la réunion
	 * 
	 * @param longueur
	 * @return la chaine pour la réunion
	 */
	public String genererLienReunion(int longueur) {
		String chaine = "ABCDEFabcdefghijklmnopqrstuvwxyz0123456789";

		Random random = new Random();
		StringBuilder lien = new StringBuilder(longueur);

		for (int i = 0; i < longueur; i++) {
			int randomIndex = random.nextInt(chaine.length());
			char randomChar = chaine.charAt(randomIndex);
			lien.append(randomChar);
		}

		return "http://teams.microsoft.com/" + lien.toString();
	}

	/**
	 * R&eacute;cupère le chemin de destination pour le dossier
	 * <i>fichierAbsences</i>
	 * 
	 * @return le chemin canonique du dossier <i>fichierAbsences</i>
	 */
	private String getDossierDestinationDistance() {
		// Chemin dossier pour les fichiers d'absences
		// try & catch ajoutés par Eclipse
		try {
			return new File("fichiersAbsences/absencesADistance").getCanonicalPath();
		} catch (IOException ef) {
			ef.printStackTrace();
			return null;
		}
	}
}
