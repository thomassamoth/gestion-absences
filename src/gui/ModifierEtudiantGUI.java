package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.TimerTask;

import javax.swing.*;
import java.util.ArrayList;

// Packages
import dao.GestionnaireDAO;
import dao.EtudiantDAO;

import model.*;
import javax.swing.border.LineBorder;

/**
 * Classe pour afficher la fen&ecirc;tre d'accueil pour les &eacute;tudiants
 * 
 * @author Thomas Beyet
 * @version 1.0
 */
public class ModifierEtudiantGUI {

	private JFrame modifEtudiantWindow;
	private JTextField fieldMail;
	private JTextField fieldUsername;
	private JTextField fieldPassword;
	/**
	 * Num&eacute;ro associ&eacute; si apprentissage ou classique
	 */
	private int numFiliere;

	/**
	 * Groupe de l'&eacute;l&egrave;ve etudiant
	 */
	private int numeroGroupe;

	/**
	 * Constructeur pour la fen&ecirc;tre de modification des &eacute;tudiants
	 * @param user
	 * @param prenom
	 * @param nom
	 */
	public ModifierEtudiantGUI(Utilisateur user, String prenom, String nom) {
		initializeWindow();
		initializeHeader(user);
		initializeSidebar();
		initializeContent(user, prenom, nom);
	}

	private void setFiliere(int numFiliere) {
		this.numFiliere = numFiliere;
	}

	/**
	 * Setter du num&eacute;ro de groupe
	 * @param numGroup le num&eacute;ro du groupe
	 */
	private void setNumeroGroupe(int numGroup) {
		this.numeroGroupe = numGroup;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeContent(Utilisateur util, String prenom, String nom) {
		JPanel content = new JPanel();
		content.setVisible(true);
		content.setBackground(new Color(255, 255, 255));
		content.setBounds(140, 50, 699, 393);
		content.setLayout(null);
		modifEtudiantWindow.getContentPane().add(content);

		JLabel titreAjouterEtudiant = new JLabel("Modifier Etudiant");
		titreAjouterEtudiant.setFont(new Font("Arial", Font.BOLD, 24));
		titreAjouterEtudiant.setBounds(236, 10, 211, 28);
		content.add(titreAjouterEtudiant);

		JLabel txtNom = new JLabel("Nom de l'&eacute;lève *");
		txtNom.setFont(new Font("Arial", Font.BOLD, 10));
		txtNom.setBounds(10, 60, 96, 13);
		content.add(txtNom);

		JLabel txtPrenom = new JLabel("Pr&eacute;nom de l'&eacute;lève *");
		txtPrenom.setFont(new Font("Arial", Font.BOLD, 10));
		txtPrenom.setBounds(116, 60, 96, 13);
		content.add(txtPrenom);

		JLabel txtNomEtu = new JLabel("");
		txtNomEtu.setForeground(new Color(0, 0, 0));
		txtNomEtu.setBounds(10, 76, 96, 19);
		txtNomEtu.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		content.add(txtNomEtu);
		txtNomEtu.setText(nom);

		JLabel txtPrenomEtu = new JLabel("");
		txtPrenomEtu.setForeground(Color.BLACK);
		txtPrenomEtu.setBorder(null);
		txtPrenomEtu.setBounds(116, 76, 96, 19);
		content.add(txtPrenomEtu);
		txtPrenomEtu.setText(prenom);

		JLabel txtFiliere = new JLabel("Filière");
		txtFiliere.setBounds(10, 105, 84, 13);
		content.add(txtFiliere);

		/**
		 * Texte modifi&eacute; s'il y a une erreur avec les ComboBox
		 */
		JLabel lblErreur = new JLabel("");
		lblErreur.setOpaque(true);
		lblErreur.setHorizontalAlignment(SwingConstants.CENTER);
		lblErreur.setForeground(Color.BLACK);
		lblErreur.setFont(new Font("Arial", Font.BOLD, 12));
		lblErreur.setBackground(Color.WHITE);
		lblErreur.setBounds(222, 173, 308, 26);
		content.add(lblErreur);

		String[] choixFiliere = { "", "Apprentissage", "Classique" };
		JComboBox<String> dropFiliere = new JComboBox(choixFiliere);
		dropFiliere.setBounds(10, 123, 96, 21);
		content.add(dropFiliere);

		// Action ComboBox Filières
		dropFiliere.addActionListener(e -> {
			String selectedOption = (String) dropFiliere.getSelectedItem();
			if (!selectedOption.equals("")) {
				int selectedValue = 0;
				if (selectedOption.equals("Apprentissage")) {
					selectedValue = 1;
				} else if (selectedOption.equals("Classique")) {
					selectedValue = 2;
				}

				setFiliere(selectedValue);
			} else {
				lblErreur.setText("Veuillez faire un choix pour la filière !");
				lblErreur.setBackground(new Color(248, 215, 218));
			}
		});

		// Texte mail
		JLabel txtMail = new JLabel("Adresse mail");
		txtMail.setBounds(222, 60, 84, 13);
		content.add(txtMail);

		// Champ adresse mail
		fieldMail = new JTextField();
		fieldMail.setColumns(10);
		fieldMail.setBounds(222, 76, 96, 19);
		content.add(fieldMail);

		// Bouton valider
		JButton btnValider = new JButton("Valider");
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.setFont(new Font("Arial", Font.PLAIN, 10));
		btnValider.setBorderPainted(false);
		btnValider.setBackground(new Color(37, 167, 67));
		btnValider.setBounds(127, 206, 85, 21);
		content.add(btnValider);

		JLabel txtUsername = new JLabel("Nom d'utilisateur *");
		txtUsername.setFont(new Font("Arial", Font.BOLD, 10));
		txtUsername.setBounds(362, 60, 96, 13);
		content.add(txtUsername);

		// Champ username
		fieldUsername = new JTextField();
		fieldUsername.setBackground(new Color(240, 240, 240));
		fieldUsername.setColumns(10);
		fieldUsername.setBounds(362, 76, 96, 19);
		content.add(fieldUsername);

		JLabel lblMotDePasse = new JLabel("Mot de passe *");
		lblMotDePasse.setFont(new Font("Arial", Font.BOLD, 10));
		lblMotDePasse.setBounds(468, 60, 84, 13);
		content.add(lblMotDePasse);

		// Champ mot de passe
		fieldPassword = new JTextField();
		fieldPassword.setBackground(new Color(240, 240, 240));
		fieldPassword.setColumns(10);
		fieldPassword.setBounds(468, 76, 96, 19);
		content.add(fieldPassword);

		/**
		 * Texte modifi&eacute; si &eacute;tudiant correctement ajout&eacute;
		 */
		JLabel txtNotif = new JLabel("");
		txtNotif.setHorizontalAlignment(SwingConstants.CENTER);
		txtNotif.setBackground(new Color(255, 255, 255));
		txtNotif.setFont(new Font("Arial", Font.BOLD, 12));
		txtNotif.setForeground(Color.BLACK);
		txtNotif.setOpaque(true);
		txtNotif.setBounds(10, 173, 308, 26);
		content.add(txtNotif);

		// Texte Groupe
		JLabel lblGroupe = new JLabel("Groupe");
		lblGroupe.setBounds(116, 105, 84, 13);
		content.add(lblGroupe);

		GestionnaireDAO gestion = new GestionnaireDAO();
		ArrayList<String> listeG = gestion.getListeGroupes();

		listeG.add(0, ""); // On ajoute un element vide pour que l'utilisateur doive choisir quelquechose

		// Créer un tableau à partir de l'arrayList
		Object[] listeGroupesArray = listeG.toArray();

		/**
		 * Menu d&eacute;roulant liste des Groupes
		 */
		JComboBox<String> dropGroupes = new JComboBox(listeGroupesArray);
		dropGroupes.setBounds(116, 123, 96, 21);
		content.add(dropGroupes);

		// Bouton Annuler
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Arial", Font.PLAIN, 10));
		btnAnnuler.setBorderPainted(false);
		btnAnnuler.setBackground(new Color(218, 54, 72));
		btnAnnuler.setBounds(10, 206, 85, 21);
		content.add(btnAnnuler);

		// Actions bouton Annuler
		btnAnnuler.addActionListener(e -> {
			modifEtudiantWindow.dispose();
			new AccueilGestionnaireGUI(util);
		});

		// Action ComboBox Filières
		dropGroupes.addActionListener(e -> {
			String selectedOption = (String) dropGroupes.getSelectedItem();
			if (!selectedOption.equals("")) { // Si l'otion choisie est vide
				int selectedValue = Integer.parseInt(selectedOption);
				setNumeroGroupe(selectedValue);
			} else {
				lblErreur.setText("Veuillez faire un choix pour le groupe!");
				lblErreur.setBackground(new Color(248, 215, 218));
			}
		});

		// Actions bouton Valider
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GestionnaireDAO gestio = new GestionnaireDAO();
				EtudiantDAO etu = new EtudiantDAO();

				// Récupère id Etudiant
				int idEtudiant = etu.getIDEtudiantNomPrenom(prenom, nom);

				int idutilisateur = etu.getIDUtilisateurFromIdEtudiant(idEtudiant);

				int messageErreur = 0;

				// Modification groupe
				if(numeroGroupe != 0){
					if(gestio.changerGroupeEtudiant(idEtudiant, numeroGroupe) != 1){
						messageErreur = 1;
					}
				}

				// Modification filière
				if(numFiliere != 0) {
					if(gestio.changerFiliereEtudiant(idEtudiant, numFiliere) != 1) {
						messageErreur = 1;
					}
				}

				//Modification mail
				if(fieldMail.getText().length() != 0) {
					if(gestio.changerMailEtudiant(idEtudiant, fieldMail.getText()) != 1) {
						messageErreur = 1;
					}
				}

				// Modification username
				if(fieldUsername.getText().length() != 0){
					if(gestio.changerUsernameEtudiant(idutilisateur, fieldUsername.getText()) != 1) {
						messageErreur = 1;
					}
				}

				// Modification motdepasse
				if(fieldPassword.getText().length() != 0){
					if(gestio.changerPasswordEtudiant(idutilisateur, fieldPassword.getText()) != 1) {
						messageErreur = 1;
					}
				}

				if(messageErreur == 1) {
					JOptionPane.showMessageDialog(null, "Erreur lors de la modification de l'&eacute;tudiant. ", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					txtNotif.setText("Modification(s) effectu&eacute;e(s)");
					txtNotif.setBackground(new Color(217, 237, 218));
					
					// Retire le texte après 3 secondes !
					Timer timer = new Timer(3000, new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
				            txtNotif.setText("");
				            txtNotif.setBackground(null);
				            ((Timer)e.getSource()).stop(); 
				        }
				    });
				    timer.setRepeats(false);
				    timer.start();
				}
			}
		});


		//gestio.modifierEtudiant(idEtudiant, nom, numeroGroupe, numFiliere);	
	}

	/**
	 * Initialise la fen&ecirc;tre
	 */
	private void initializeWindow() {
		// Fenêtre
		modifEtudiantWindow = new JFrame();
		modifEtudiantWindow.setResizable(false);
		modifEtudiantWindow.getContentPane().setBackground(new Color(255, 255, 255));
		modifEtudiantWindow.setVisible(true);
		modifEtudiantWindow.setTitle("Modification Etudiant");
		modifEtudiantWindow.setSize(853, 480);
		modifEtudiantWindow.setLocationRelativeTo(null); // Centre fenêtre dans l'écran
		modifEtudiantWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modifEtudiantWindow.getContentPane().setLayout(null);
	}

	/**
	 * Initialise les composants de l'entête de la page
	 * @param util
	 */
	private void initializeHeader(Utilisateur util) {
		// Header
		JPanel header = new JPanel();
		header.setBackground(new Color(255, 25, 25));
		header.setBounds(0, 0, 839, 50);
		header.setLayout(null);
		modifEtudiantWindow.getContentPane().add(header);

		// Bouton Menu Déconnexion
		JButton btnDeconnexion = new JButton("Se Déconnecter");
		btnDeconnexion.setForeground(new Color(255, 255, 255));
		btnDeconnexion.setFont(new Font("Arial", Font.PLAIN, 10));
		btnDeconnexion.setBorderPainted(false);
		btnDeconnexion.setFocusPainted(false); // Bordure text invisible
		btnDeconnexion.setBackground(new Color(255, 25, 25));
		btnDeconnexion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change curseur quand survole
		btnDeconnexion.setBounds(712, 0, 117, 50);
		header.add(btnDeconnexion);

		// Action bouton déconnecter
		btnDeconnexion.addActionListener(e -> {
			modifEtudiantWindow.dispose();
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
	 * Initalise le contenu du menu lat&eacute;ral
	 */
	private void initializeSidebar() {
		// Menu latéral
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 0, 140, 443);
		sidebar.setLayout(null);
		modifEtudiantWindow.getContentPane().add(sidebar);
	}
}