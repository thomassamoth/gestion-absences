package dao;

import java.sql.*;

import java.util.*;

import java.text.SimpleDateFormat;

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
	
	/**
	 * Permet de mettre le statut 'is_justified' à 1.
	 * @param absenceid l'ID de l'absence à justifier
	 * @return 1 si requète ok. 0 sinon.
	 */
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

	/**
	 * Récupère l'id etudiant à partir de l'id utilisateur
	 * @param idutilisateur l'id utilisateur
	 * @return 
	 */
	public int getIDEtudiant(int idutilisateur){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idEtudiant = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement(
					"SELECT idetudiant FROM Etudiant WHERE idutilisateur = ?");

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
	 * Récupère l'id etudiant à partir de son prenom & nom.
	 * @param prenom
	 * @param nom
	 * @return
	 */
	public int getIDEtudiantNomPrenom(String prenom, String nom){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idEtudiant = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement(
					"select idetudiant from etudiant "
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
	 * Verifie si etudiant existe à partir de son prenom et nom
	 * @param prenom
	 * @param nom
	 * @return le nombre d'étudiant correspondant
	 */
	public int verifEtudiantExiste(String prenom, String nom){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int nbEtudiant = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement(
					"select count(idetudiant) from etudiant "
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
	 * Retourne l'id utilisateur à partir de l'id etudiant.<br/>
	 * Fonction utilisée car modification mot de passe prend trop de temps
	 * @param prenom
	 * @param nom
	 * @return le nombre d'étudiant correspondant
	 */
	public int getIDUtilisateurFromIdEtudiant(int idetudiant){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int id = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement(
					"SELECT idutilisateur FROM Etudiant WHERE idetudiant = ?");

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
	 * Ajoute une absence planifiée
	 * @param idEtudiant l'id de l'étudiant
	 * @param date la date de l'absence
	 * @param idmatiere l'id de la matiere
	 * @return 1 si requete ok. 0 sinon
	 */
	public int ajouterAbsencePlanifiee(int idEtudiant, java.sql.Date date, int idmatiere) {
	    Connection con = null;
	    PreparedStatement ps = null;

	    int ajoutEffectue = 0;

	    try {
	        con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

	        // conversion de la chaîne de caractères en objet java.sql.Date
	        //java.sql.Date sqlDate = java.sql.Date.valueOf(date);

	        ps = con.prepareStatement(
	                "INSERT INTO Absence(idetudiant, dateabsence, idcours, is_handled, is_justified) "
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

	
	public int ajouterCommentaireAbsence(int idabsence, String commentaire) {
		Connection con = null;
	    PreparedStatement ps = null;

	    int ajoutEffectue = 0;

	    try {
	        con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

	        ps = con.prepareStatement("UPDATE Absence "
	        		+ "SET commentaire = ? where idabsence = ?");

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

