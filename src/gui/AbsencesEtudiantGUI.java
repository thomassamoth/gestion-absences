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
 * Classe pour afficher la fen&ecirc;tre des absences d'un &eacute;l&egrave;ve
 * 
 * @author Thomas Beyet
 * @version 1.1
 */
public class AbsencesEtudiantGUI {

	private JFrame absencesEtudiant;

	/**
	 * Cr&eacute;er la  fen&ecirc;tre  de gestion des absences pour l'&eacute;tudiant
	 * 
	 * @param util l'utilisateur dont on veut garder les infos
	 */
	public AbsencesEtudiantGUI(Utilisateur util) {
		initializeWindow();
		initializeHeader(util);
		initializeSidebar();
		initializeContent(util);
	}

	/**
	 * Initialise le contenu principal de la fen&ecirc;tre.
	 * @param util l'utilisateur dont on veut avoir les infos
	 */
	private void initializeContent(Utilisateur util) {
		JPanel content = new JPanel();
		content.setVisible(true);
		content.setBackground(new Color(255, 255, 255));
		content.setBounds(140, 50, 699, 393);
		content.setLayout(null);
		absencesEtudiant.getContentPane().add(content);

		/** ===== TEXTES =====**/
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(26, 250, 164, 21);
		content.add(lblNewLabel);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		
		JLabel lblCommentaire = new JLabel("Commentaire falcutatif");
		lblCommentaire.setHorizontalAlignment(SwingConstants.LEFT);
		lblCommentaire.setFont(new Font("Arial", Font.ITALIC, 14));
		lblCommentaire.setBounds(20, 114, 217, 30);
		content.add(lblCommentaire);
		
		
		/** ==== ComboBoxs ====**/

		// JComboBox des absences injustifiees
		JComboBox<String> dropAbsInj = new JComboBox<>();
		dropAbsInj.setBounds(20, 88, 653, 21);
		content.add(dropAbsInj);

		// Remplir la JComboBox avec les absences injustifiees de l'utilisateur
		EtudiantDAO etuDAO = new EtudiantDAO();
		
		ArrayList<Absence> listeAbsencesInjustifiees = etuDAO.getAbsencesInjustifiees(util.getIdentifiant());
		
		DefaultComboBoxModel<String> listeDeroulanteAbs = new DefaultComboBoxModel<>();
		
		// Remplisage de l'arrayList
		for (int i = 0; i < listeAbsencesInjustifiees.size(); i++) {
		    Absence absence = listeAbsencesInjustifiees.get(i);
		    listeDeroulanteAbs.addElement(absence.displayToString());
		}

		dropAbsInj.setModel(listeDeroulanteAbs);

		// JButton Annuler
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(20, 281, 85, 21);
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Arial", Font.PLAIN, 10));
		btnAnnuler.setBorderPainted(false);
		btnAnnuler.setBackground(new Color(218, 54, 72));
		content.add(btnAnnuler);

		// Action du JButton Annuler
		btnAnnuler.addActionListener(e -> {
			absencesEtudiant.dispose();
			new AccueilEtudiantGUI(util);
		});

		// JButton Valider
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(115, 281, 85, 21);
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Arial", Font.PLAIN, 10));
		btnValider.setBorderPainted(false);
		btnValider.setBackground(new Color(37, 167, 67));
		btnValider.setEnabled(false);
		content.add(btnValider);

		JButton btnPlanifier = new JButton("Planifier absence");
		btnPlanifier.setForeground(new Color(255, 255, 255));
		btnPlanifier.setBackground(new Color(0, 105, 217));
		btnPlanifier.setBounds(20, 341, 134, 21);
		content.add(btnPlanifier);


		JTextPane fieldCommentaire = new JTextPane();
		fieldCommentaire.setBackground(new Color(243, 244, 246));
		fieldCommentaire.setBounds(20, 142, 341, 119);
		content.add(fieldCommentaire);

		// Action du JButton Planifier
		btnPlanifier.addActionListener(e -> {
			absencesEtudiant.dispose();
			new PlanifierAbsenceGUI(util);
		});

		/* Action de la JComboBox dropAbsInj */
		dropAbsInj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Choix dans la liste
				int indexAbs = dropAbsInj.getSelectedIndex();

				// Récupère indice de la selection
				Absence absenceChoisie = listeAbsencesInjustifiees.get(indexAbs);

				// Récupère ID de l'absence
				int idAbsence = absenceChoisie.getIDAbsence();
				
				// Choix du fichier
				choixFichier(btnValider, etuDAO, idAbsence, fieldCommentaire, util);
			}
		});
	}

	/**
	 * Permet de choisir le fichier pour justifier l'absence
	 * 
	 * @param btnValider       Changer l'&eacute;tat du bouton valider
	 * @param etuDAO           etudiantDAO pour avoir les fonctions associ&eacute;es
	 * @param fieldCommentaire le champ texte dont je veux r&eacute;cuperer le
	 *                         contenu
	 * @param user             l'utilisateur dont on r&eacute;cupère les infos lors
	 *                         du rafraichissement de la page.
	 * @param idabsence        l'identifiant de l'absence
	 */
	private void choixFichier(JButton btnValider, EtudiantDAO etuDAO, int idabsence, JTextPane fieldCommentaire, Utilisateur user) {
		JFileChooser fileChooser = new JFileChooser();

		// Fichiers PDF seulement affichés
		fileChooser.setDialogTitle("Choisir un justificatif d'absence");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers PDF", "pdf"));

		// Afficher explorateur fichiers et récupère le résultat
		int choixUser = fileChooser.showOpenDialog(null);

		/* Si user clique sur le bouton Ouvrir */
		if (choixUser == JFileChooser.APPROVE_OPTION) {
		    // Récupère fichier sélectionné
		    File fichierAbsence = fileChooser.getSelectedFile();
			
			// Récupère l'adresse du dossier de destination
			String dossierDest = getDossierDestination();
		    		    
		    if (dossierDest != null) {
		        // Créer nouveau fichier dans dossier destination
		        File destinationFichier = new File(dossierDest, fichierAbsence.getName());

		        /* Actions bouton Valider */
		        btnValider.setEnabled(true);
		        btnValider.setCursor(new Cursor(Cursor.HAND_CURSOR));
		        btnValider.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	// Ajout commentaire à l'absence
		                String comment = fieldCommentaire.getText();
		                if (comment.length() != 0) {
		                    if (etuDAO.ajouterCommentaireAbsence(idabsence, comment) == 1) {
		                        return;
		                    }
		                }
		                
		                try {
		                    Files.copy(fichierAbsence.toPath(), destinationFichier.toPath(),StandardCopyOption.REPLACE_EXISTING);

		                    // Change statut absence
		                    if (etuDAO.setAbsenceJustifiee(idabsence) == 1) {
		                        JOptionPane.showMessageDialog(null, "Statut de l'absence modifi&eacute;.", "Information",JOptionPane.INFORMATION_MESSAGE);
		                        absencesEtudiant.dispose();
		                        new AbsencesEtudiantGUI(user);
		                        
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Erreur lors de la modification du statut de l'absence",
		                                "Information", JOptionPane.ERROR_MESSAGE);
		                    }
		                } catch (IOException ef) {
		                    System.out.println("Erreur lors de l'enregistrement du fichier !");
		                    ef.printStackTrace();
		                }
		            }
		        });
		    }
		}
	}

	/**
	 * R&eacute;cupère le chemin de destination pour le dossier <i>fichierAbsences</i>
	 * @return le chemin canonique du dossier <i>fichierAbsences</i>
	 */
	private String getDossierDestination(){
		// Chemin dossier pour les fichiers d'absences
		// try & catch ajout&eacute;s par Eclipse
		try {
			return new File("fichiersAbsences").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Initialise les composants de la fen&ecirc;tre
	 */
	public void initializeWindow() {
		// fen&ecirc;tre
		absencesEtudiant = new JFrame();
		absencesEtudiant.setResizable(false);
		absencesEtudiant.getContentPane().setBackground(new Color(255, 255, 255));
		absencesEtudiant.setVisible(true);
		absencesEtudiant.setTitle("Justification Absences");
		absencesEtudiant.setSize(853, 480);
		absencesEtudiant.setLocationRelativeTo(null); // Centre fenêtre dans l'&eacute;cran
		absencesEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		absencesEtudiant.getContentPane().setLayout(null);
	}

	/**
	 * Initialise les composants de l'entête de la fen&ecirc;tre
	 * @param util l'utilisateur dont on affiche le pseudo
	 */
	private void initializeHeader(Utilisateur util) {
		// Header
		JPanel header = new JPanel();
		header.setBackground(new Color(255, 25, 25));
		header.setBounds(0, 0, 839, 50);
		header.setLayout(null);
		absencesEtudiant.getContentPane().add(header);

		// Bouton Menu D&eacute;connexion
		JButton btnDeconnexion = new JButton("Se D&eacute;connecter");
		btnDeconnexion.setForeground(new Color(255, 255, 255));
		btnDeconnexion.setFont(new Font("Arial", Font.PLAIN, 10));
		btnDeconnexion.setBorderPainted(false);
		btnDeconnexion.setBackground(new Color(255, 25, 25));
		btnDeconnexion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change curseur quand survole
		btnDeconnexion.setBounds(712, 0, 117, 50);
		header.add(btnDeconnexion);

		// Action bouton d&eacute;connecter
		btnDeconnexion.addActionListener(e -> {
			absencesEtudiant.dispose();
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
	 * Initialise les composants de la barre lat&eacute;rale
	 */
	private void initializeSidebar() {
		// Menu lat&eacute;ral
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 0, 140, 443);
		sidebar.setLayout(null);
		absencesEtudiant.getContentPane().add(sidebar);

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
	}
}
