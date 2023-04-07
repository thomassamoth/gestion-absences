package dao;

import java.sql.*;

import java.util.*;


import model.*;

public class AbsenceDAO extends ConnexionBDD {

	public AbsenceDAO() {
		super();
	}

	/**
	 * Récupère la liste des absences non traitées
	 * @return
	 */
	public ArrayList<Absence> getAbsencesNonTraitees() {
		Connection con = null;
		ResultSet rs = null;
		ArrayList<Absence> listeAbsencesNonTraitees = new ArrayList<>();

		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			PreparedStatement ps = con.prepareStatement("SELECT nom, prenom, nom_cours, dateabsence FROM ABSENCE inner join etudiant on (absence.idetudiant = etudiant.idetudiant) inner join utilisateur on (utilisateur.idutilisateur = etudiant.idutilisateur) inner join cours on (cours.idcours = absence.idcours) WHERE is_handled = 0"
);
			
			rs = ps.executeQuery();
			

			while (rs.next()) {
				Absence absenceNonTraitee = new Absence(rs.getString("nom"), rs.getString("prenom"), rs.getString("nom_cours"), rs.getString("dateabsence"));
				listeAbsencesNonTraitees.add(absenceNonTraitee);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		return listeAbsencesNonTraitees;
	}
}
