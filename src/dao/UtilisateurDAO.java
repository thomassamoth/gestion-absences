package dao;

import java.util.*;
import java.sql.*;

import model.*;

@SuppressWarnings("unused")
public class UtilisateurDAO extends connexionBDD{
	public UtilisateurDAO() {
		super();
	}
	
	/*
	 * Récupère les informations d'un utilisateur
	 * @param: identifiant : l'indentifiant de l'utilisateur dont on veut récuperer les attributs.
	 */
	public Utilisateur getInfoUser(String identifiant, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Utilisateur returnValue = null;
		
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("SELECT * FROM Utilisateur WHERE identifiant LIKE ? AND motdepasse LIKE ?");
			ps.setString(1, identifiant);
			ps.setString(2, password);
			
			// Execute la requete
			rs = ps.executeQuery();	
			
			if(rs.next()) {
				returnValue = new Utilisateur(rs.getInt("idutilisateur"), rs.getString("identifiant"), rs.getString("motdepasse"));
			}			
		} catch (Exception ee) {
			ee.printStackTrace();
			
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;	
	}
}
