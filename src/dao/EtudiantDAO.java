package dao;

import java.sql.*;

import java.util.*;

import model.*;

/**
 * Classe pour les appels à la BDD pour les étudiants
 * 
 * @author Thomas
 * @version 1.0
 */
public class EtudiantDAO extends ConnexionBDD {
	public EtudiantDAO() {
		super();
	}

	/**
	 * Récupère les absences injustifiées d'un étudiant
	 * @param identifiant
	 * @return
	 */
	public ArrayList<Absence> getAbsencesInjustifiees(String identifiant) {
		Connection con =  null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Absence> listeAbsencesInjustifiees = new ArrayList<>();


		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("SELECT idabsence,nom_cours, quota_absence, dateabsence "
					+ "FROM ABSENCE INNER JOIN etudiant ON (absence.idetudiant = etudiant.idetudiant) "
					+ "INNER JOIN utilisateur ON (utilisateur.idutilisateur = etudiant.idutilisateur) "
					+ "INNER JOIN cours ON (cours.idcours = absence.idcours) "
					+ "WHERE is_justified = 0 AND utilisateur.identifiant = ?"
					+ "ORDER BY dateabsence DESC");

			ps.setString(1, identifiant);

			rs = ps.executeQuery();

			while(rs.next()) {
				String dateAbsence = rs.getDate("dateabsence").toString();
				Absence absenceInjustifiee = new Absence(rs.getInt("idabsence"), rs.getInt("quota_absence"), 
				dateAbsence ,rs.getString("nom_cours") );

				listeAbsencesInjustifiees.add(absenceInjustifiee);
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
			}catch (Exception ignore) {}

			// Fermeture ps
			try {
				if (ps != null)
					rs.close();
			}catch (Exception ignore) {}

			// Fermeture con
			try {
				if(con != null)
					con.close();
			}catch (Exception ignore) {}
		}
		return listeAbsencesInjustifiees;
	}
	
	
	/**
	 * Récupère les absences justifiées d'un étudiant
	 * @param identifiant
	 * @return
	 */
	public ArrayList<Absence> getAbsencesJustifiees(String identifiant) {
		Connection con =  null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Absence> listeAbsencesJustifiees = new ArrayList<>();


		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("SELECT idabsence,nom_cours, quota_absence, dateabsence "
					+ "FROM ABSENCE INNER JOIN etudiant ON (absence.idetudiant = etudiant.idetudiant) "
					+ "INNER JOIN utilisateur ON (utilisateur.idutilisateur = etudiant.idutilisateur) "
					+ "INNER JOIN cours ON (cours.idcours = absence.idcours) "
					+ "WHERE is_justified = 1 AND utilisateur.identifiant = ?"
					+ "ORDER BY dateabsence DESC");

			ps.setString(1, identifiant);

			rs = ps.executeQuery();

			while(rs.next()) {
				String dateAbsence = rs.getDate("dateabsence").toString();
				Absence absenceJustifiee = new Absence(rs.getInt("idabsence"), rs.getInt("quota_absence"), dateAbsence ,rs.getString("nom_cours") );
				listeAbsencesJustifiees.add(absenceJustifiee);
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
			}catch (Exception ignore) {}

			// Fermeture ps
			try {
				if (ps != null)
					rs.close();
			}catch (Exception ignore) {}

			// Fermeture con
			try {
				if(con != null)
					con.close();
			}catch (Exception ignore) {}
		}
		return listeAbsencesJustifiees;
	}

	public int setAbsenceJustifiee(int absenceid){
		Connection con = null;
		PreparedStatement ps = null;

		int modifEffectuee = 0;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("UPDATE Absence SET is_justified = 1 WHERE idabsence = ?");

			ps.setInt(1, absenceid);
			
			modifEffectuee = ps.executeUpdate();
			
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// Fermeture ps
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}

			// Fermeture con
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return modifEffectuee;

	}

}

