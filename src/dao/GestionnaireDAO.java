package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//import model.Absence;

public class GestionnaireDAO extends ConnexionBDD{

	/**
	 * Constructeur
	 */
	public GestionnaireDAO() {
		super();
	}

	/**
	 * Ajoute Utilisateur dans BDD
	 * @param nom
	 * @param prenom
	 * @param identifiant
	 * @param motdepasse
	 * @return
	 */
	public int ajouterUtilisateur(String nom, String prenom, String identifiant, String motdepasse) {
		Connection con = null;
		PreparedStatement ps = null;

		int ajoutEffectue = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("INSERT INTO Utilisateur(nom, prenom, identifiant, motdepasse) "
					+ "VALUES (?, ?, ?, ?)");

			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setString(3, identifiant);
			ps.setString(4, motdepasse);

			ajoutEffectue = ps.executeUpdate();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		finally {
			// Fermeture ps
			try {
				if (ps != null) {
					ps.close();
				}
			}catch (Exception ignore) {}

			// Fermeture con
			try {
				if(con != null)
					con.close();
			}catch (Exception ignore) {}
		}
		return ajoutEffectue;
	}

}
