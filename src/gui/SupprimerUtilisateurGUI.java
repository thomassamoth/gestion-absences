package gui;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

// Packages
import dao.GestionnaireDAO;
import model.*;

/**
 * Classe de la fen&egrave;tre permettant de supprimer un utilisateur
 * 
 * @author Walid Ben Attia
 * @version 1.0
 */
public class SupprimerUtilisateurGUI {

	private JFrame gestionEtudiantWindow;
	private JTextField fieldNom;
	private JTextField fieldPrenom;
	private JTextField fieldIdentifiant;
	
	

	/**
	 * Constructeur pour la fen&ecirc;tre de creation des professeurs
	 * @param user
	 */
	public SupprimerUtilisateurGUI(Utilisateur user) {
		intializeWindow();
		initializeHeader(user);
		initializeSidebar();
		initializeContent(user);
	}

	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeContent(Utilisateur util){
		JPanel content = new JPanel();
		content.setVisible(true);
		content.setBackground(new Color(255, 255, 255));
		content.setBounds(140, 50, 699, 393);
		content.setLayout(null);
		gestionEtudiantWindow.getContentPane().add(content);

		JLabel txtSupprimerUtilisateur = new JLabel("Supprimer utilisateur");
		txtSupprimerUtilisateur.setFont(new Font("Arial", Font.BOLD, 24));
		txtSupprimerUtilisateur.setBounds(236, 10, 294, 28);
		content.add(txtSupprimerUtilisateur);

		JLabel txtNom = new JLabel("Nom *");
		txtNom.setFont(new Font("Arial", Font.BOLD, 10));
		txtNom.setBounds(10, 60, 96, 13);
		content.add(txtNom);

		fieldNom = new JTextField();
		fieldNom.setBounds(10, 76, 96, 19);
		fieldNom.setColumns(10);
		content.add(fieldNom);

		JLabel txtPrenom = new JLabel("Prénom *");
		txtPrenom.setFont(new Font("Arial", Font.BOLD, 10));
		txtPrenom.setBounds(116, 60, 96, 13);
		content.add(txtPrenom);

		fieldPrenom = new JTextField();
		fieldPrenom.setColumns(10);
		fieldPrenom.setBounds(116, 76, 96, 19);
		content.add(fieldPrenom);

		/**
		 * Texte modifié s'il y a une erreur avec les ComboBox
		 */
		JLabel lblErreur = new JLabel("");
		lblErreur.setOpaque(true);
		lblErreur.setHorizontalAlignment(SwingConstants.CENTER);
		lblErreur.setForeground(Color.BLACK);
		lblErreur.setFont(new Font("Arial", Font.BOLD, 12));
		lblErreur.setBackground(Color.WHITE);
		lblErreur.setBounds(222, 173, 308, 26);
		content.add(lblErreur);

		

		// Texte mail
		JLabel txtIdentifiant = new JLabel("identifiant");
		txtIdentifiant.setBounds(222, 60, 84, 13);
		content.add(txtIdentifiant);

		// Champ adresse mail
		fieldIdentifiant = new JTextField();
		fieldIdentifiant.setColumns(10);
		fieldIdentifiant.setBounds(222, 76, 96, 19);
		content.add(fieldIdentifiant);

		// Bouton valider
		JButton btnValider = new JButton("Valider");
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.setFont(new Font("Arial", Font.PLAIN, 10));
		btnValider.setBorderPainted(false);
		btnValider.setBackground(new Color(37, 167, 67));
		btnValider.setBounds(127, 206, 85, 21);
		content.add(btnValider);

		/**
		 * Texte modifié si professeur correctement ajouté
		 */
		JLabel txtEtuAjoute = new JLabel("");
		txtEtuAjoute.setHorizontalAlignment(SwingConstants.CENTER);
		txtEtuAjoute.setBackground(new Color(255, 255, 255));
		txtEtuAjoute.setFont(new Font("Arial", Font.BOLD, 12));
		txtEtuAjoute.setForeground(Color.BLACK);
		txtEtuAjoute.setOpaque(true);
		txtEtuAjoute.setBounds(10, 173, 308, 26);
		content.add(txtEtuAjoute);

		GestionnaireDAO gestion = new GestionnaireDAO();
		ArrayList<String> listeG = gestion.getListeGroupes();

		listeG.add(0, ""); // On ajoute un element vide pour que l'utilisateur doive choisir quelquechose 

		// Créer un tableau à partir de l'arrayList
		Object[] listeGroupesArray = listeG.toArray();

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
			gestionEtudiantWindow.dispose();
			new AccueilGestionnaireGUI(util);
		});

		// Actions bouton Valider
		btnValider.addActionListener(e -> {
			GestionnaireDAO gestio = new GestionnaireDAO();

			// Verification si champs obligatoires remplis
			if(fieldNom.getText().length() <= 0 || fieldPrenom.getText().length() <= 0 || 
					fieldIdentifiant.getText().length() <= 0 ) {
				JOptionPane.showMessageDialog(null, "Veuillez compléter le/les champ(s) manquants. ", "Warning",
						JOptionPane.WARNING_MESSAGE);

			}
			else {
				// suppression d'un utilisateur 
				if(gestio.supprimerUtilisateur(fieldIdentifiant.getText(), fieldPrenom.getText(), fieldNom.getText() ) == 1){
				
					
					
					 
						txtEtuAjoute.setBackground(new Color(212, 237,218));
						txtEtuAjoute.setText("Utilisateur supprimé");
					}
					else {
						txtEtuAjoute.setBackground(new Color(248, 215,218));
						txtEtuAjoute.setText("Erreur lors de la suppression de l'utilisateur");				
					}
				}
				
			
		});
	}

		/**
		 * Initialise la fen&ecirc;tre
		 */
		private void intializeWindow(){
			// Fenêtre
			gestionEtudiantWindow = new JFrame();
			gestionEtudiantWindow.setResizable(false);
			gestionEtudiantWindow.getContentPane().setBackground(new Color(255, 255, 255));
			gestionEtudiantWindow.setVisible(true);
			gestionEtudiantWindow.setTitle("Ajouter Professeur");
			gestionEtudiantWindow.setSize(853, 480);
			gestionEtudiantWindow.setLocationRelativeTo(null); // Centre fenêtre dans l'écran
			gestionEtudiantWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gestionEtudiantWindow.getContentPane().setLayout(null);
		}

	private void initializeHeader(Utilisateur util){
		// Header
		JPanel header = new JPanel();
		header.setBackground(new Color(255, 25, 25));
		header.setBounds(0, 0, 839, 50);
		header.setLayout(null);
		gestionEtudiantWindow.getContentPane().add(header);

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
				gestionEtudiantWindow.dispose();
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
	 * Initalise le contenu du menu latéral
	 */
	private void initializeSidebar(){
		// Menu latéral
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 0, 140, 443);
		sidebar.setLayout(null);
		gestionEtudiantWindow.getContentPane().add(sidebar);
	}
}