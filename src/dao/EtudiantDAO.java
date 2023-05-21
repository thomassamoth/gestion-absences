package dao;

import java.sql.*;

import java.util.*;

import java.text.SimpleDateFormat;

import model.*;

/**
 * Classe pour les appels &agrave; la BDD pour les &eacute;tudiants
 * 
 * @author Thomas
 * @version 1.0
 */
public class EtudiantDAO extends ConnexionBDD {
	/**
	 * Constructeur pour EtudiantDAO
	 */
	public EtudiantDAO() {
		super();
	}

	/**
	 * R&eacute;cup &egrave;re les absences injustifi&eacute;es d'un &eacute;tudiant
	 * 
	 * @param identifiant l'identifiant
	 * @return la liste des absences injustifiees
	 */
	public ArrayList<Absence> getAbsencesInjustifiees(String identifiant) {
		Connection con = null;
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
					+ "WHERE is_justified = 0 AND utilisateur.identifiant = ?" + "ORDER BY dateabsence DESC");

			ps.setString(1, identifiant);

			rs = ps.executeQuery();

			while (rs.next()) {
				String dateAbsence = rs.getDate("dateabsence").toString();
				Absence absenceInjustifiee = new Absence(rs.getInt("idabsence"), rs.getInt("quota_absence"),
						dateAbsence, rs.getString("nom_cours"));

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
		return listeAbsencesInjustifiees;
	}

	/**
	 * R&eacute;cup &egrave;re les absences justifi&eacute;es d'un &eacute;tudiant
	 * 
	 * @param identifiant l'identifiant de l'etudiant
	 * @return la liste des absences justifiees
	 */
	public ArrayList<Absence> getAbsencesJustifiees(String identifiant) {
		Connection con = null;
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
					+ "WHERE is_justified = 1 AND utilisateur.identifiant = ?" + "ORDER BY dateabsence DESC");

			ps.setString(1, identifiant);

			rs = ps.executeQuery();

			while (rs.next()) {
				String dateAbsence = rs.getDate("dateabsence").toString();
				Absence absenceJustifiee = new Absence(rs.getInt("idabsence"), rs.getInt("quota_absence"), dateAbsence,
						rs.getString("nom_cours"));
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
		return listeAbsencesJustifiees;
	}

	/**
	 * Permet de mettre le statut 'is_justified' &agrave;1.
	 * 
	 * @param absenceid l'ID de l'absence &agrave;justifier
	 * @return 1 si requète ok. 0 sinon.
	 */
	public int setAbsenceJustifiee(int absenceid) {
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

	/**
	 * R&eacute;cup &egrave;re l'id &eacute;tudiant &agrave;partir de l'id
	 * utilisateur
	 * 
	 * @param idutilisateur l'id utilisateur
	 * @return l'ID de l'etudiant
	 */
	public int getIDEtudiant(int idutilisateur) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idEtudiant = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("SELECT idetudiant FROM Etudiant WHERE idutilisateur = ?");

			ps.setInt(1, idutilisateur);

			// Execute la requete
			rs = ps.executeQuery();

			if (rs.next()) {
				idEtudiant = rs.getInt("idetudiant");
			} else
				System.out.println("ID non trouvé");
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
		return idEtudiant;
	}

	/**
	 * R&eacute;cup &egrave;re l'id &eacute;tudiant &agrave;partir de son
	 * pr&eacute;nom et nom.
	 * 
	 * @param prenom le pr&eacute; de l'etudiant
	 * @param nom    le nom de l'etudiant
	 * @return l'ID etudiant
	 */
	public int getIDEtudiantNomPrenom(String prenom, String nom) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idEtudiant = 0;
		// Connexion &agrave;la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("select idetudiant from etudiant "
					+ "inner join utilisateur on(etudiant.idutilisateur = utilisateur.idutilisateur) "
					+ "where nom = ? and prenom = ?");

			ps.setString(1, nom);
			ps.setString(2, prenom);

			// Execute la requete
			rs = ps.executeQuery();

			if (rs.next()) {
				idEtudiant = rs.getInt("idetudiant");
			} else
				System.out.println("ID non trouvé");
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
		return idEtudiant;
	}

	/**
	 * V&eacute;rifie si &eacute;tudiant existe &agrave;partir de son pr&eacute;nom
	 * et nom
	 * 
	 * @param prenom le prenom de l'etudiant
	 * @param nom    le nom de l'etudiant
	 * @return le nombre d'&eacute;tudiants correspondants
	 */
	public int verifEtudiantExiste(String prenom, String nom) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int nbEtudiant = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("select count(idetudiant) from etudiant "
					+ "inner join utilisateur on(etudiant.idutilisateur = utilisateur.idutilisateur) "
					+ "where nom = ? and prenom = ?");

			ps.setString(1, nom);
			ps.setString(2, prenom);

			// Execute la requete
			rs = ps.executeQuery();

			if (rs.next()) {
				nbEtudiant = rs.getInt(1); // Récupère la 1ere colonne du resultat
			}
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
		return nbEtudiant;
	}

	/**
	 * Retourne l'id utilisateur &agrave; partir de l'id etudiant.<br/>
	 * Fonction utilis&eacute;e car modifications de l'etudiant trop longues
	 * 
	 * @param idetudiant l'id etudiant
	 * @return l'id utilisateur de l'etudiant
	 */
	public int getIDUtilisateurFromIdEtudiant(int idetudiant) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int id = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("SELECT idutilisateur FROM Etudiant WHERE idetudiant = ?");

			ps.setInt(1, idetudiant);

			// Execute la requete
			rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1); // Récupère la 1ere colonne du resultat
			}
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
		return id;
	}

	/**
	 * Ajoute une absence planifi&eacute;e
	 * 
	 * @param idEtudiant l'id de l'&eacute;tudiant
	 * @param date       la date de l'absence
	 * @param idmatiere  l'id de la matiere
	 * @return 1 si requete ok. 0 sinon
	 */
	public int ajouterAbsencePlanifiee(int idEtudiant, java.sql.Date date, int idmatiere) {
		Connection con = null;
		PreparedStatement ps = null;

		int ajoutEffectue = 0;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			// conversion de la chaîne de caractères en objet java.sql.Date
			// java.sql.Date sqlDate = java.sql.Date.valueOf(date);

			ps = con.prepareStatement("INSERT INTO Absence(idetudiant, dateabsence, idcours, is_handled, is_justified) "
					+ "VALUES(?, ?, ?, 0, 1)");

			ps.setInt(1, idEtudiant);
			ps.setDate(2, date);
			ps.setInt(3, idmatiere);

			ajoutEffectue = ps.executeUpdate();

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
		return ajoutEffectue;
	}

	/**
	 * Ajoute un commentaire dans l'absence dont on a l'ID
	 * 
	 * @param idabsence   l'id de l'absence
	 * @param commentaire le commentaire
	 * @return 1 si requete ok
	 */
	public int ajouterCommentaireAbsence(int idabsence, String commentaire) {
		Connection con = null;
		PreparedStatement ps = null;

		int ajoutEffectue = 0;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("UPDATE Absence " + "SET commentaire = ? where idabsence = ?");

			ps.setString(1, commentaire);
			ps.setInt(2, idabsence);

			ajoutEffectue = ps.executeUpdate();

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
		return ajoutEffectue;
	}
}
