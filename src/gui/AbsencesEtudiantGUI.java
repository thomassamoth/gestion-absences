package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

// Imports File Chooser
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.filechooser.FileNameExtensionFilter;

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

	/**
	 * Créer la fenêtre de gestion des absences pour l'étudiant
	 */
	public AbsencesEtudiantGUI(Utilisateur util) {
		initializeWindow();
		initializeHeader(util);
		initializeSidebar(util);
		initializeContent(util);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeContent(Utilisateur util) {
		JPanel content = new JPanel();
		content.setVisible(true);
		content.setBackground(new Color(255, 255, 255));
		content.setBounds(140, 50, 699, 393);
		content.setLayout(null);
		AbsencesEtudiant.getContentPane().add(content);

		// JLabel Bonjour
		JLabel txtBonjour = new JLabel("Bonjour " + util.getUtilisateurPrenom());
		txtBonjour.setBounds(20, 10, 148, 30);
		txtBonjour.setFont(new Font("Arial", Font.BOLD, 14));
		content.add(txtBonjour);

		// JLabel Liste de vos absences injustifiées
		JLabel txtListeAbsence = new JLabel("Liste de vos absences injustifiées");
		txtListeAbsence.setBounds(20, 50, 217, 30);
		txtListeAbsence.setFont(new Font("Arial", Font.ITALIC, 14));
		txtListeAbsence.setHorizontalAlignment(SwingConstants.LEFT);
		content.add(txtListeAbsence);

		// JComboBox des absences injustifiées
		JComboBox<String> dropAbsInj = new JComboBox<>();
		dropAbsInj.setBounds(20, 88, 653, 21);
		content.add(dropAbsInj);

		// Remplir la JComboBox avec les absences injustifiées de l'utilisateur
		EtudiantDAO etuDAO = new EtudiantDAO();
		ArrayList<Absence> listeAbsencesInjustifiees = etuDAO.getAbsencesInjustifiees(util.getIdentifiant());
		// Debug
		System.out.println("Liste : ");
		for(int i=0; i<listeAbsencesInjustifiees.size();i++) {
			System.out.println(listeAbsencesInjustifiees.get(i).getIDAbsence());
		}
		DefaultComboBoxModel<String> listeDeroulanteAbs = new DefaultComboBoxModel<>();
		for (Absence absence : listeAbsencesInjustifiees) {
			listeDeroulanteAbs.addElement(absence.displayToString());
		}

		dropAbsInj.setModel(listeDeroulanteAbs);

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

		// JButton Valider
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(105, 181, 85, 21);
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Arial", Font.PLAIN, 10));
		btnValider.setBorderPainted(false);
		btnValider.setBackground(new Color(37, 167, 67));
		btnValider.setEnabled(false);
		content.add(btnValider);

		// Action de la JComboBox dropAbsInj
		dropAbsInj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexAbs = dropAbsInj.getSelectedIndex();
				if (indexAbs != -1) {
					Absence absenceChoisie = listeAbsencesInjustifiees.get(indexAbs);
					int idabs = absenceChoisie.getIDAbsence();
					choixFichier(btnValider, etuDAO, idabs, content);
				} else {
					JOptionPane.showMessageDialog(null, "Erreur", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * 
	 * @param btnValider
	 * @param etuDAO
	 * @param idabsence
	 * @param AbsencesEtudiant
	 */
	private void choixFichier(JButton btnValider, EtudiantDAO etuDAO, int idabsence, JPanel content) {
		JFileChooser fileChooser = new JFileChooser();

		// Fichiers pdf seulement affichés
		fileChooser.setDialogTitle("Choisir un justificatif d'absence");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers PDF", "pdf"));

		// Afficher explorateur fichiers et récupérer résultat
		int choixUser = fileChooser.showOpenDialog(null);

		if (choixUser == JFileChooser.APPROVE_OPTION) {
			btnValider.setEnabled(true);
			btnValider.setCursor(new Cursor(Cursor.HAND_CURSOR));
			String dossierDest = null;

			// Récupérer le fichier sélectionné
			File fichierAbsence = fileChooser.getSelectedFile();
			// Créer nouveau fichier dans dossier de dest.
			File destinationFile = new File(dossierDest, fichierAbsence.getName());

			try {
				dossierDest = new File("fichiersAbsences").getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Actions bouton Valider
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Copier le fichier sélectionné dans le dossier de destination
					try {
						Files.copy(fichierAbsence.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						System.out.println("Fichier enregistré dans le dossier !");

						if (etuDAO.setAbsenceJustifiee(idabsence) == 1) {
							System.out.println("Absence modifiee");
						} else {
							System.out.println("Erreur lors de la modif statut absence");
						}
						content.repaint();
					} catch (IOException ea) {
						System.out.println(
								"Erreur enregistrement du fichier !");
						ea.printStackTrace();
					}
				}
			});
		}
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
	 * @param util
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
			AbsencesEtudiant.dispose();
			new AbsencesEtudiantGUI(util);
		});

	}
}
