package dao;

import java.sql.*;

import java.util.*;

import model.*;

/**
 * Classe pour tous les appels &agrave; la base de donn&eacute;es pour les
 * absences
 * 
 * @author Thomas Beyet
 * @author Walid Ben Attia
 *
 */
public class AbsenceDAO extends ConnexionBDD {

	/**
	 * Constructeur de l'absenceDAO
	 */
	public AbsenceDAO() {
		super();
	}

	/**
	 * R&eacute;cup &egrave;re la liste des absences non trait&eacute;es
	 * 
	 * @return la liste des absences non traitees
	 */
	public ArrayList<Absence> getAbsencesNonTraitees() {
		Connection con = null;
		ResultSet rs = null;
		ArrayList<Absence> listeAbsencesNonTraitees = new ArrayList<>();

		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			PreparedStatement ps = con.prepareStatement(
					"SELECT nom, prenom, nom_cours, dateabsence , is_justified , is_handled "
					+ "FROM ABSENCE inner join etudiant on (absence.idetudiant = etudiant.idetudiant) inner join utilisateur on (utilisateur.idutilisateur = etudiant.idutilisateur) inner join cours on (cours.idcours = absence.idcours) WHERE is_handled = 0");

			rs = ps.executeQuery();

			while (rs.next()) {
				Absence absenceNonTraitee = new Absence(rs.getString("nom"), rs.getString("prenom"),
						rs.getString("nom_cours"), rs.getString("dateabsence"), rs.getInt("is_justified"),
						rs.getInt("is_handled"));
				listeAbsencesNonTraitees.add(absenceNonTraitee);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		return listeAbsencesNonTraitees;
	}

	public void validerAbsence(int idabsence) {
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			PreparedStatement ps = con.prepareStatement("UPDATE ABSENCE SET IS_VALIDE = 'oui' WHERE idabsence = ? ");
			PreparedStatement ps2 = con.prepareStatement("UPDATE ABSENCE SET IS_HANDLED = 1 WHERE idabsence = ? ");
			ps.setInt(1, idabsence);
			ps2.setInt(1, idabsence);

			rs = ps.executeQuery();
			rs = ps2.executeQuery();

		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}

	public void handled(int idabsence) {
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			PreparedStatement ps2 = con.prepareStatement("UPDATE ABSENCE SET IS_HANDLED = 1 WHERE idabsence = ? ");

			ps2.setInt(1, idabsence);

			rs = ps2.executeQuery();

		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}
}