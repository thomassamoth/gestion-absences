package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//import model.Groupe;

public class GestionnaireDAO extends ConnexionBDD {

	/**
	 * Constructeur
	 */
	public GestionnaireDAO() {
		super();
	}

	/**
	 * Ajoute Utilisateur dans BDD
	 * 
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

			ps = con.prepareStatement(
					"INSERT INTO Utilisateur(nom, prenom, identifiant, motdepasse) " + "VALUES (?, ?, ?, ?)");

			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setString(3, identifiant);
			ps.setString(4, motdepasse);

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
	 * Récupère l'ID d'un utilisateur
	 * 
	 * @param identifiant
	 * @param prenom
	 * @param nom
	 * @return
	 */
	public int getIDUtilisateur(String identifiant, String prenom, String nom) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idUtilisateur = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement(
					"SELECT idutilisateur FROM Utilisateur WHERE identifiant = ? AND prenom = ? AND nom = ?");

			ps.setString(1, identifiant);
			ps.setString(2, prenom);
			ps.setString(3, nom);

			// Execute la requete
			rs = ps.executeQuery();

			if (rs.next()) {
				idUtilisateur = rs.getInt("idutilisateur");
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
		return idUtilisateur;
	}


	}
	/**
	 * Ajoute un etudiant dans la table
	 * 
	 * @param idutilisateur l'ID de l'utilisateur
	 * @param adresseMail   l'adresse mail de l'étudiant
	 * @param groupe        le groupe de l'etudiant
	 * @param filiere       la filiere de l'étudiant. Si filiere = 0 ->
	 *                      Apprentissage. Si filiere = 1 -> Classique
	 * @return ajoutEffectue
	 */
	public int ajouterEtudiant(int idutilisateur, String adresseMail, int groupe, int filiere) {
		Connection con = null;
		PreparedStatement ps = null;

		int ajoutEffectue = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement(
					"INSERT INTO Etudiant(idutilisateur, mailetudiant, idgroupe, filiere) " + "VALUES (?, ?, ?, ?)");

			ps.setInt(1, idutilisateur);
			ps.setString(2, adresseMail);
			ps.setInt(3, groupe);
			ps.setInt(4, filiere);

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
	 * Créer un nouveau groupe
	 * 
	 * @param numeroGroupe le numéro du groupe
	 * @param capaciteMax  la capacité maximale du groupe
	 * @return 1 si ajout effectué, 0 sinon
	 */
	public int ajouterGroupe(int numeroGroupe, int capaciteMax) {
		Connection con = null;
		PreparedStatement ps = null;

		int ajoutEffectue = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("INSERT INTO Groupe(idgroupe, numerogroupe, capacitemax)" + "VALUES (?, ?, ?)");

			ps.setInt(1, numeroGroupe);
			ps.setInt(2, numeroGroupe);
			ps.setInt(3, capaciteMax);

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
	 * Récupère la liste des groupes présents dans la BDD
	 * @return listeGroupes la liste des groupes existants
	 */
	public ArrayList<String> getListeGroupes(){
		Connection con =  null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> listeGroupes = new ArrayList<>();

		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("SELECT numerogroupe FROM groupe ORDER BY numeroGroupe");

			rs = ps.executeQuery();

			while(rs.next()) {
				int groupe = rs.getInt("NUMEROGROUPE");
				listeGroupes.add(Integer.toString(groupe));
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
		return listeGroupes;
	}
}
