package gui;

import java.awt.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.*;
import javax.swing.table.*;

import dao.AbsenceDAO;
import dao.GestionnaireDAO;

import java.util.ArrayList;

// Packages
//import dao.UtilisateurDAO;
//import dao.AbsenceDAO;

import model.*;

/**
 * Classe pour afficher la fenêtre de validation des absences
 * 
 * @author Thomas
 * @version 1.0
 */
public class ValidationAbsenceGUI {

	private JFrame validationAbsences;

	/**
	 * Create the window with a user input
	 */
	public ValidationAbsenceGUI(Utilisateur util) {
		initialize(util);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Utilisateur util) {

		creerFenetreValidationAbsences();

		// Header
		JPanel header = new JPanel();
		header.setBackground(new Color(240, 240, 240));
		header.setBounds(0, 0, 839, 50);
		header.setLayout(null);
		validationAbsences.getContentPane().add(header);

		// Titre header
		JLabel titreValidationAbsences = new JLabel("VALIDATION ABSENCES");
		titreValidationAbsences.setBackground(new Color(179, 179, 179));
		titreValidationAbsences.setBounds(140, 10, 699, 40);
		titreValidationAbsences.setHorizontalAlignment(SwingConstants.CENTER);
		titreValidationAbsences.setFont(new Font("Arial", Font.BOLD, 24));
		header.add(titreValidationAbsences);

		// Affichage de l'identifiant de l'utilisateur
		JLabel usernameLabel = new JLabel("");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		usernameLabel.setBounds(43, 0, 97, 50);
		usernameLabel.setText(util.getIdentifiant());
		header.add(usernameLabel);
		// ImageIcon icon = new ImageIcon("img/icon_user.png");
		JLabel test_icon = new JLabel(
				new ImageIcon("C:\\Users\\Thomas\\Documents\\&ESIGELEC\\2022-2023 - 1A\\PDL\\pdl\\img\\icon_user.png"));
		test_icon.setLocation(0, 10);
		test_icon.setSize(38, 40);
		header.add(test_icon);

		// Menu latéral
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 0, 140, 443);
		sidebar.setLayout(null);
		validationAbsences.getContentPane().add(sidebar);

		// Bouton Menu absences
		JButton menuBtnAbsences = new JButton("Absences");
		menuBtnAbsences.setForeground(new Color(255, 255, 255));
		menuBtnAbsences.setFont(new Font("Arial", Font.PLAIN, 20));
		menuBtnAbsences.setBorderPainted(false);
		menuBtnAbsences.setBackground(new Color(255, 102, 102));
		menuBtnAbsences.setFocusPainted(false); // Bordure text invisible
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
		menuBtnPlanning.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor when hover button
		menuBtnPlanning.setBounds(0, 126, 140, 33);
		sidebar.add(menuBtnPlanning);

		/* == Content == */
		JLabel titreAbsenceNonTraitee = new JLabel("Liste des absence(s) non traitée(s)");
		titreAbsenceNonTraitee.setFont(new Font("Arial", Font.BOLD, 16));
		titreAbsenceNonTraitee.setBounds(150, 60, 368, 28);
		validationAbsences.getContentPane().add(titreAbsenceNonTraitee);

		displayAbsencesNonTraitees(util);
	}

	/**
	 * Créer la fenêtre de validation des absences.
	 */
	public void creerFenetreValidationAbsences() {
		// Fenêtre
		validationAbsences = new JFrame();
		validationAbsences.setVisible(true);
		validationAbsences.setTitle("Gestionnaire - Validation des absences");
		validationAbsences.setResizable(false);
		validationAbsences.setSize(853, 480);

		// Centre fenêtre dans l'écran
		validationAbsences.setLocationRelativeTo(null);
		validationAbsences.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Background
		validationAbsences.getContentPane().setBackground(new Color(255, 255, 255));
		validationAbsences.getContentPane().setLayout(null);
	}

	public class CheckBoxCellRenderer extends JCheckBox implements TableCellRenderer {
		private static final long serialVersionUID = 1L;

		public CheckBoxCellRenderer() {
			super();
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			this.setSelected((Boolean) value);
			return this;
		}
	}

	public class CheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		private JCheckBox checkBox;

		public CheckBoxCellEditor() {
			super();
			checkBox = new JCheckBox();
		}

		public Object getCellEditorValue() {
			return checkBox.isSelected();
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			checkBox.setSelected((Boolean) value);
			return checkBox;
		}
	}

	/**
	 * Affiche la liste des absences non traitées dans un tableau
	 */

	public void displayAbsencesNonTraitees(Utilisateur util) {
		AbsenceDAO absDAO = new AbsenceDAO();

		ArrayList<Absence> listeAbsencesNT = absDAO.getAbsencesNonTraitees();

		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new Object[] { "idabsence", "Nom", "Prénom", "Matière", "Date", "justifié", "Accepter", "Refuser" });

		for (int i = 0; i < listeAbsencesNT.size(); i++) {
			Object[] row = new Object[] { listeAbsencesNT.get(i).getIdAbsence(),
					listeAbsencesNT.get(i).getNomEtudiant(), listeAbsencesNT.get(i).getPrenomEtudiant(),
					listeAbsencesNT.get(i).getNomCours(), listeAbsencesNT.get(i).getDate(),
					listeAbsencesNT.get(i).getIsJustified(), false, false };

			// Si absence non justifiée et pas traitée
			if ((listeAbsencesNT.get(i).getIsJustified() == 1) && (listeAbsencesNT.get(i).getIsHandled() == 0)) {
				model.addRow(row);
			}
		}

		// Créer la table avec le modèle de table précédemment créé
		JTable table = new JTable(model);
		CheckBoxCellRenderer checkBoxRenderer = new CheckBoxCellRenderer();
		CheckBoxCellEditor checkBoxEditor = new CheckBoxCellEditor();

		// set the renderer and editor for the checkbox column
		table.getColumnModel().getColumn(6).setCellRenderer(checkBoxRenderer);
		table.getColumnModel().getColumn(6).setCellEditor(checkBoxEditor);

		CheckBoxCellRenderer checkBoxRenderer2 = new CheckBoxCellRenderer();
		CheckBoxCellEditor checkBoxEditor2 = new CheckBoxCellEditor();

		// set the renderer and editor for the checkbox column
		table.getColumnModel().getColumn(7).setCellRenderer(checkBoxRenderer2);
		table.getColumnModel().getColumn(7).setCellEditor(checkBoxEditor2);

		// Créer le JScrollPane et ajouter la table à l'intérieur
		JScrollPane test = new JScrollPane(table);
		test.setBounds(160, 98, 653, 296);
		validationAbsences.getContentPane().add(test);

		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(712, 412, 117, 21);
		validationAbsences.getContentPane().add(btnValider);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(516, 412, 117, 21);
		validationAbsences.getContentPane().add(btnAnnuler);
		
		// Action du JButton Annuler
		btnAnnuler.addActionListener(e -> {
			validationAbsences.dispose();
			new AccueilGestionnaireGUI(util);
		});

		/** Actions bouton valider **/
		btnValider.addActionListener(e -> {
			AbsenceDAO absDAO1 = new AbsenceDAO();

			for (int i = 0; i < table.getRowCount(); i++) {
				
				// si checkbox est checkée
				if ((boolean) table.getValueAt(i, 7) == true) {
					Object valueObject = table.getValueAt(i, 0);

					int value = 0;

					if (valueObject instanceof Integer) {
						value = (int) valueObject;
					} else if (valueObject instanceof String) {
						value = Integer.parseInt((String) valueObject);
					}

					absDAO1.handled(value);
					JOptionPane.showMessageDialog(null, "Justificatif refusé. ", "Error", JOptionPane.ERROR_MESSAGE);
				}

				if ((boolean) table.getValueAt(i, 6) == true) {
					//System.out.println("Checkbox is checked for row " + (i + 1));

					Object valueObject = table.getValueAt(i, 0);

					int value = 0;

					if (valueObject instanceof Integer) {
						value = (int) valueObject;
					} else if (valueObject instanceof String) {
						value = Integer.parseInt((String) valueObject);
					}

					absDAO1.validerAbsence(value);
					JOptionPane.showMessageDialog(null, "Absence validée ", "Information",
							JOptionPane.INFORMATION_MESSAGE);

					validationAbsences.dispose();
					new ValidationAbsenceGUI(util);

				}
			}
		});
	}
}