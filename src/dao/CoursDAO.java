package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe pour les requetes li&eacute;es aux cours
 * 
 * @author Thomas Beyet
 * @author Walid Ben Attia
 * @version 1.0
 *
 */
public class CoursDAO extends ConnexionBDD {

	/**
	 * R&eacute;cup &egrave;re la liste des matières
	 * 
	 * @return la liste des matières
	 */
	public ArrayList<String> getListeMatieres() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> listeMatieres = new ArrayList<>();

		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("SELECT nom_cours FROM cours ORDER BY idcours");

			rs = ps.executeQuery();

			while (rs.next()) {
				String matiere = rs.getString("nom_cours");
				listeMatieres.add(matiere);
			}
		}

		catch (Exception ee) {
			ee.printStackTrace();
		}

		finally {
			// Fermeture rs
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {
			}

			// Fermeture ps
			try {
				if (ps != null)
					rs.close();
			} catch (Exception ignore) {
			}

			// Fermeture con
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return listeMatieres;
	}

}
