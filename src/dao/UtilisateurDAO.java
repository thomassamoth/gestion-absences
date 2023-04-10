package dao;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

import model.*;

@SuppressWarnings("unused")

/**
 * Classe pour les appels à la BDD pour les utilisateurs
 * 
 * @author Thomas
 * @version 1.0
 */
public class UtilisateurDAO extends ConnexionBDD {

	public UtilisateurDAO() {
		super();
	}


	/**
	 * Recupère les informations de connexion de l'utilisateur
	 * 
	 * @param identifiant
	 * @param password
	 * @return Utilisateur
	 */
	public Utilisateur getInfoUser(String identifiant, String password) {
		Connection con = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		Utilisateur returnValue = null;

		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("SELECT * FROM Utilisateur WHERE identifiant LIKE ? AND motdepasse LIKE ? ");
			ps.setString(1, identifiant);
			ps.setString(2, password);

			// Execute la requete
			rs = ps.executeQuery();

			if (rs.next()) {
				returnValue = new Utilisateur(rs.getInt("idutilisateur"), rs.getString("identifiant"),
						rs.getString("motdepasse"), rs.getString("prenom"));
			} else {
				JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect. ", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// Fermeture rs
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {}
			
			// Fermeture ps
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {}
			
			// Fermeture con
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {}
		}
		return returnValue;
	}

	/**
	 * Récupère le statut d'un utilisateur à partir de son identifiant.
	 * @param identifiant
	 * @return statutUtilisateur
	 */
	public int getStatutUtilisateur(String identifiant) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int statutUtilisateur = -1;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("SELECT count(idgestionnaire) " +
					"FROM Gestionnaire " +
					"INNER JOIN Utilisateur on (Utilisateur.idutilisateur = Gestionnaire.idutilisateur) " +
					"where identifiant LIKE ?");

			ps.setString(1, identifiant);

			rs = ps.executeQuery();

			if (rs.next() && rs.getInt(1) > 0) {
				statutUtilisateur = 1;
			} else {
				ps = con.prepareStatement("SELECT count(idprofesseur) " +
						"FROM Professeur " +
						"INNER JOIN Utilisateur on (Utilisateur.idutilisateur = Professeur.idutilisateur) " +
						"where identifiant LIKE ?");

				ps.setString(1, identifiant);

				rs = ps.executeQuery();

				if (rs.next() && rs.getInt(1) > 0) {
					statutUtilisateur = 2;
				} else {
					ps = con.prepareStatement("SELECT count(idetudiant) " +
							"FROM Etudiant " +
							"INNER JOIN Utilisateur on (Utilisateur.idutilisateur = Etudiant.idutilisateur) " +
							"where identifiant LIKE ?");

					ps.setString(1, identifiant);

					rs = ps.executeQuery();

					if (rs.next() && rs.getInt(1) > 0) {
						statutUtilisateur = 3;
					}
				}
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return statutUtilisateur;
	}

	/**
	 * Récupère le prénom d'un utilisateur à partir de son identifiant.
	 * @param identifiant
	 * @return prénom de l'utilisateur
	 */
	public String getPrenomUtilisateur(String identifiant) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String prenomUtilisateur = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("SELECT prenom FROM Utilisateur WHERE identifiant LIKE ?");
			ps.setString(1, identifiant);

			rs = ps.executeQuery();

			if (rs.next()) {
				prenomUtilisateur = rs.getString("prenom");
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return prenomUtilisateur;
	}
}