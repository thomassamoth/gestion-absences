package gui;

import java.awt.*;
import javax.swing.*;
/*import javax.swing.border.LineBorder;
import javax.swing.border.Border;*/

// Packages
import dao.GestionnaireDAO;
import model.*;

/**
 * Classe pour afficher la fenêtre d'accueil pour les étudiants
 * 
 * @author Thomas
 * @version 1.0
 */
public class GestionEtudiantGUI {


	private JFrame AccueilGestionnaireWindow;
	private JTextField fieldNom;
	private JTextField fieldPrenom;
	private JTextField fieldMail;
	private JTextField fieldUsername;
	private JTextField fieldPassword;

	/**
	 * Créer la fenêtre d'accueil pour les étudiants
	 * @param util l'utilisteur dont on veut afficher les infos
	 * @param prenom prénom de l'utilisateur, nécessaire pour texte de bienvenue
	 */
	public GestionEtudiantGUI(Utilisateur user) {
		initialize(user);
	}

	/**
	 * Initialise le contenu de la fenêtre d'accueil pour les étudiants
	 * @param util l'utilisteur dont on veut afficher les infos
	 * @param prenom prénom de l'utilisateur, nécessaire pour texte de bienvenue
	 */
	private void initialize(Utilisateur util) {
		// Fenêtre
		AccueilGestionnaireWindow = new JFrame();
		AccueilGestionnaireWindow.setResizable(false);
		AccueilGestionnaireWindow.getContentPane().setBackground(new Color(255, 255, 255));
		AccueilGestionnaireWindow.setVisible(true);
		AccueilGestionnaireWindow.setTitle("Gestion Etudiant");
		AccueilGestionnaireWindow.setSize(853, 480);
		AccueilGestionnaireWindow.setLocationRelativeTo(null); // Centre fenêtre dans l'écran
		AccueilGestionnaireWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AccueilGestionnaireWindow.getContentPane().setLayout(null);

		// Header
		JPanel header = new JPanel();
		header.setBackground(new Color(255, 25, 25));
		header.setBounds(0, 0, 839, 50);
		header.setLayout(null);
		AccueilGestionnaireWindow.getContentPane().add(header);

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

		// Menu latéral
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 0, 140, 443);
		sidebar.setLayout(null);
		AccueilGestionnaireWindow.getContentPane().add(sidebar);

		JLabel txtAjouterEtudiant = new JLabel("Ajouter Etudiant");
		txtAjouterEtudiant.setFont(new Font("Arial", Font.BOLD, 24));
		txtAjouterEtudiant.setBounds(150, 60, 206, 57);
		AccueilGestionnaireWindow.getContentPane().add(txtAjouterEtudiant);

		JLabel txtNom = new JLabel("Nom *");
		txtNom.setBounds(150, 127, 84, 13);
		AccueilGestionnaireWindow.getContentPane().add(txtNom);

		fieldNom = new JTextField();
		fieldNom.setBounds(150, 143, 96, 19);
		AccueilGestionnaireWindow.getContentPane().add(fieldNom);
		fieldNom.setColumns(10);

		JLabel txtPrenom = new JLabel("Prénom *");
		txtPrenom.setBounds(256, 127, 84, 13);
		AccueilGestionnaireWindow.getContentPane().add(txtPrenom);

		fieldPrenom = new JTextField();
		fieldPrenom.setColumns(10);
		fieldPrenom.setBounds(256, 143, 96, 19);
		AccueilGestionnaireWindow.getContentPane().add(fieldPrenom);

		JLabel txtFiliere = new JLabel("Filière");
		txtFiliere.setBounds(150, 172, 84, 13);
		AccueilGestionnaireWindow.getContentPane().add(txtFiliere);

		String[] choixFiliere = { "Apprentissage", "Classique"};
		JComboBox dropGroup = new JComboBox(choixFiliere);
		dropGroup.setBounds(150, 190, 96, 21);
		AccueilGestionnaireWindow.getContentPane().add(dropGroup);
		dropGroup.addActionListener(e -> {
			String choix = (String) dropGroup.getSelectedItem();
			System.out.println("L'utilisateur a choisi : " + choix);
			// Autres actions ici
		});


		fieldMail = new JTextField();
		fieldMail.setColumns(10);
		fieldMail.setBounds(362, 143, 96, 19);
		AccueilGestionnaireWindow.getContentPane().add(fieldMail);

		JLabel txtMail = new JLabel("Adresse mail");
		txtMail.setBounds(362, 127, 84, 13);
		AccueilGestionnaireWindow.getContentPane().add(txtMail);

		JButton btnValider = new JButton("Valider");
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.setFont(new Font("Arial", Font.PLAIN, 10));
		btnValider.setBackground(new Color(37, 167, 67));
		btnValider.setBounds(373, 310, 85, 21);
		AccueilGestionnaireWindow.getContentPane().add(btnValider);

		JLabel txtUsername = new JLabel("Nom d'utilisateur");
		txtUsername.setBounds(502, 127, 84, 13);
		AccueilGestionnaireWindow.getContentPane().add(txtUsername);

		fieldUsername = new JTextField();
		fieldUsername.setBackground(new Color(240, 240, 240));
		fieldUsername.setColumns(10);
		fieldUsername.setBounds(502, 143, 96, 19);
		AccueilGestionnaireWindow.getContentPane().add(fieldUsername);

		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(608, 127, 84, 13);
		AccueilGestionnaireWindow.getContentPane().add(lblMotDePasse);

		fieldPassword = new JTextField();
		fieldPassword.setBackground(new Color(240, 240, 240));
		fieldPassword.setColumns(10);
		fieldPassword.setBounds(608, 143, 96, 19);
		AccueilGestionnaireWindow.getContentPane().add(fieldPassword);
		
		JLabel txtEtuAjoute = new JLabel("");
		txtEtuAjoute.setBackground(new Color(255, 255, 255));
		txtEtuAjoute.setFont(new Font("Arial", Font.BOLD, 12));
		txtEtuAjoute.setForeground(Color.BLACK);
		txtEtuAjoute.setOpaque(true);
		txtEtuAjoute.setBounds(225, 255, 172, 20);
		AccueilGestionnaireWindow.getContentPane().add(txtEtuAjoute);
		
		btnValider.addActionListener(e -> {
			GestionnaireDAO gestio = new GestionnaireDAO();
			if(fieldNom.getText().length() <= 0 || fieldPrenom.getText().length() <= 0 || fieldUsername.getText().length() <= 0 || fieldPassword.getText().length() <= 0) {
				JOptionPane.showMessageDialog(null, "Veuillez compléter le/les champ(s) manquants. ", "Warning",
						JOptionPane.WARNING_MESSAGE);
				
			}
			else {
				if(gestio.ajouterUtilisateur(fieldNom.getText(), fieldPrenom.getText(), fieldUsername.getText(), fieldPassword.getText()) == 1){
					txtEtuAjoute.setBackground(new Color(212, 237,218));
					txtEtuAjoute.setText("Etudiant ajouté");
				}
			}
			
		});
		
	}
}



