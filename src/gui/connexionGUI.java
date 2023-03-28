//package gui;
//import java.awt.EventQueue;
//
//
//import javax.swing.JFrame;
//import java.awt.Color;
//import javax.swing.JLabel;
//import java.awt.Font;
//import javax.swing.JTextField;
//import javax.swing.JButton;
//import javax.swing.DropMode;
//import javax.swing.JPasswordField;
//import java.awt.Toolkit;
//
//import javax.swing.BoxLayout;
//import javax.swing.JPanel;
//import javax.swing.JOptionPane;
//
///*import dao.SupplierDAO;
//import model.Supplier;*/
//
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//public class connexionGUI {
//
//	private JFrame windowConnexion;
//	private JTextField fieldIdentifiant;
//	private JPasswordField fieldPassword;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GestionAbsence window = new GestionAbsence();
//					window.windowConnexion.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public GestionAbsence() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		windowConnexion = new JFrame();
//		windowConnexion.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Thomas\\Downloads\\Logo_ESIGELEC.svg.png"));
//		windowConnexion.setTitle("Gestion des absences");
//		windowConnexion.getContentPane().setBackground(new Color(255, 102, 102));
//		windowConnexion.setBackground(new Color(255, 128, 128));
//		windowConnexion.setResizable(false);
//		windowConnexion.setBounds(100, 100, 853, 480);
//		windowConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		windowConnexion.getContentPane().setLayout(null);
//		
//		JLabel ESIGELEC = new JLabel("ESIGELEC");
//		ESIGELEC.setForeground(new Color(255, 255, 255));
//		ESIGELEC.setFont(new Font("Tahoma", Font.PLAIN, 30));
//		ESIGELEC.setBounds(51, 33, 265, 45);
//		windowConnexion.getContentPane().add(ESIGELEC);
//		
//		JLabel Identifiant = new JLabel("Identifiant");
//		Identifiant.setForeground(new Color(255, 255, 255));
//		Identifiant.setFont(new Font("Arial", Font.BOLD, 15));
//		Identifiant.setBounds(348, 133, 100, 33);
//		windowConnexion.getContentPane().add(Identifiant);
//		
//		fieldIdentifiant = new JTextField();
//		fieldIdentifiant.setFont(new Font("Arial", Font.PLAIN, 10));
//		fieldIdentifiant.setText("Veuillez entrer votre identifiant");
//		fieldIdentifiant.setToolTipText("");
//		fieldIdentifiant.setBounds(348, 169, 169, 33);
//		windowConnexion.getContentPane().add(fieldIdentifiant);
//		fieldIdentifiant.setColumns(10);
//		
//		JLabel lblMotDePasse = new JLabel("Mot de passe");
//		lblMotDePasse.setForeground(Color.WHITE);
//		lblMotDePasse.setFont(new Font("Arial", Font.BOLD, 15));
//		lblMotDePasse.setBounds(348, 215, 100, 33);
//		windowConnexion.getContentPane().add(lblMotDePasse);
//		
//		JButton buttonConnexion = new JButton("Connexion");
//		buttonConnexion.setFont(new Font("Arial", Font.PLAIN, 10));
//		buttonConnexion.setBounds(393, 311, 85, 21);
//		windowConnexion.getContentPane().add(buttonConnexion);
//		
//		buttonConnexion.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(fieldIdentifiant.getText().length() > 0) {
//					System.out.println(fieldIdentifiant.getText());
//				}
//			}
//		}						
//		);
//		
//		fieldPassword = new JPasswordField();
//		fieldPassword.setEchoChar('•');
//		fieldPassword.setToolTipText("");
//		fieldPassword.setBounds(348, 249, 169, 26);
//		windowConnexion.getContentPane().add(fieldPassword);
//	}
//}
