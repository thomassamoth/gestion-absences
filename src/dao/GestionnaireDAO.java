package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe pour les appels &agrave; la base de donn&eacute;es concernant le gestionnaire
 * @author Thomas Beyet
 * @author Walid Ben Attia
 */
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
	 * @param nom le nom de l'utilisateur
	 * @param prenom le pr&eacute;nom de l'utilisateur
	 * @param identifiant l'identifiant de l'utilisateur
	 * @param motdepasse le mot de passe de l'utilisateur
	 * @return 1 si ajout correct
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
	 * R&eacute;cup &egrave;re l'ID d'un utilisateur &agrave; partir de son pr&eacute;nom et nom
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

	/**
	 * Ajoute un etudiant dans la table
	 * 
	 * @param idutilisateur l'ID de l'utilisateur
	 * @param adresseMail   l'adresse mail de l'&eacute;tudiant
	 * @param groupe        le groupe de l'etudiant
	 * @param filiere       la filiere de l'&eacute;tudiant. Si filiere = 1 ->
	 *                      Apprentissage. Si filiere = 2 -> Classique
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
	 * Cr&eacute;er un nouveau groupe
	 * 
	 * @param numeroGroupe le num&eacute;ro du groupe
	 * @param capaciteMax  la capacit&eacute; maximale du groupe
	 * @return 1 si ajout effectu&eacute;, 0 sinon
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
	 * R&eacute;cupere la liste des groupes présents dans la BDD
	 * 
	 * @return listeGroupes la liste des groupes existants
	 */
	public ArrayList<String> getListeGroupes() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> listeGroupes = new ArrayList<>();

		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("SELECT numerogroupe FROM groupe ORDER BY numeroGroupe");

			rs = ps.executeQuery();

			while (rs.next()) {
				int groupe = rs.getInt("NUMEROGROUPE");
				listeGroupes.add(Integer.toString(groupe));
			}
		} catch (Exception ee) {
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
		return listeGroupes;
	}

	/**
	 * Ajoute le professeur &agrave; la base de données
	 * @param idutilisateur
	 * @param idprofesseur
	 * @param numerotel
	 * @return
	 */
	public int ajouterProfesseur(int idutilisateur, int idprofesseur, String numerotel) {
		Connection con = null;
		PreparedStatement ps = null;

		int ajoutEffectue = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement(
					"INSERT INTO PROFESSEUR(idutilisateur, idprofesseur, numerotel) " + "VALUES (?, ?, ?)");

			ps.setInt(1, idutilisateur);
			ps.setInt(2, idprofesseur);
			ps.setString(3, numerotel);

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
	 * Supprime un utilisateur de la base de donn&eacute;es
	 * @param identifiant
	 * @param prenom
	 * @param nom
	 * @return
	 */
	public int supprimerUtilisateur(String identifiant, String prenom, String nom) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement("DELETE FROM UTILISATEUR WHERE IDUTILISATEUR = ?");
			ps.setInt(1, getIDUtilisateur(identifiant, prenom, nom));

			// Execution de la requete
			returnValue = ps.executeUpdate();
		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Eleve déjà supprimé ou inexistant");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
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

	/**
	 * Modifie les caracteristiques d'un &eacute;tudiant
	 * @param idutilisateur l'id utilisateur de l'&eacute;l&egrave;ve
	 * @param adresseMail l'adresse mail de l'&eacute;tudiant
	 * @param groupe le groupe de l'&eacute;tudiant
	 * @param filiere la filière de l'&eacute;tudiant
	 * @return 1 si la modification a correctement &eacute;t&eacute; effectu&eacute;e
	 */
	public int modifierEtudiant(int idutilisateur, String adresseMail, int groupe, int filiere) {
		Connection con = null;
		PreparedStatement ps = null;

		int modifEffectuee = 0;
		// Connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

			ps = con.prepareStatement(
					"UPDATE Etudiant "
					+ "SET idutilisateur, mailetudiant, idgroupe, filiere " + "VALUES (?, ?, ?, ?)");

			ps.setInt(1, idutilisateur);
			ps.setString(2, adresseMail);
			ps.setInt(3, groupe);
			ps.setInt(4, filiere);

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
	 * Change le groupe d'un &eacute;tudiant
	 * @param idetudiant l'id de l'&eacute;tudiant
	 * @param numeroGroupe le nouveau numéro du groupe
	 * @return 1 si requete ok
	 */
	public int changerGroupeEtudiant(int idetudiant, int numeroGroupe) {
		Connection con = null;
	    PreparedStatement ps = null;

	    int ajoutEffectue = 0;

	    try {
	        con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

	        ps = con.prepareStatement("UPDATE Etudiant "
	        		+ "SET idgroupe = ? where idetudiant = ?");

	        ps.setInt(1, numeroGroupe);
	        ps.setInt(2, idetudiant);

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
	 * Change la fili&egrave;re d'un &eacute;tudiant
	 * @param idetudiant l'id de l'&eacute;tudiant
	 * @param numFiliere le nouveau num&eacute;ro du groupe
	 * @return 1 si requete ok
	 */
	public int changerFiliereEtudiant(int idetudiant, int numFiliere) {
		Connection con = null;
	    PreparedStatement ps = null;

	    int ajoutEffectue = 0;

	    try {
	        con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

	        ps = con.prepareStatement("UPDATE Etudiant "
	        		+ "SET filiere = ? where idetudiant = ?");

	        ps.setInt(1, numFiliere);
	        ps.setInt(2, idetudiant);

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
	 * Change la filière d'un &eacute;tudiant
	 * @param idetudiant l'id de l'&eacute;tudiant
	 * @param mail le nouveau mail de l'etudiant
	 * @return 1 si requete ok
	 */
	public int changerMailEtudiant(int idetudiant, String mail) {
		Connection con = null;
	    PreparedStatement ps = null;

	    int ajoutEffectue = 0;

	    try {
	        con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

	        ps = con.prepareStatement("UPDATE Etudiant "
	        		+ "SET mailetudiant = ? where idetudiant = ?");

	        ps.setString(1, mail);
	        ps.setInt(2, idetudiant);

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
	 * Change le nom d'utilisateur d'un &eacute;tudiant
	 * @param idetudiant l'id de l'&eacute;tudiant
	 * @param username le nouveau username de l'&eacute;tudiant
	 * @return 1 si requete ok
	 */
	public int changerUsernameEtudiant(int idetudiant, String username) {
		Connection con = null;
	    PreparedStatement ps = null;

	    int ajoutEffectue = 0;

	    try {
	        con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

	        ps = con.prepareStatement("UPDATE Utilisateur "
	        		+ "SET identifiant = ? where idutilisateur = ?");

	        ps.setString(1, username);
	        ps.setInt(2, idetudiant);

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
	 * Change le mot de passe de l'&eacute;tudiant
	 * @param idutilisateur l'id utilisateur
	 * @param password le mot de passe
	 * @return 1 si requete effectuée correctement
	 */
	public int changerPasswordEtudiant(int idutilisateur, String password) {
		Connection con = null;
	    PreparedStatement ps = null;

	    int modifEffectuee = 0;

	    try {
	        con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

	        ps = con.prepareStatement("UPDATE Utilisateur SET motdepasse = ? where idutilisateur = ?");
	        
	        ps.setString(1, password);
	        ps.setInt(2, idutilisateur);
	        
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
