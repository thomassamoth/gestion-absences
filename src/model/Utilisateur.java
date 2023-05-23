package model;

/**
 * Classe pour le modèle des utilisateurs
 * 
 * @author Thomas Beyet
 * @author Walid Ben Attia
 *
 */
public class Utilisateur {
	protected int utilisateurID;
	protected String utilisateurPrenom;
	protected String utilisateurNom;
	protected String identifiant;
	protected String motDePasse;

	/**
	 * Constucteur
	 * 
	 * @param utilisateurID l'id de l'utilisateur
	 * @param identifiant   l'identifiant de l'utilisateur
	 * @param motDePasse    le mot de passe de l'utilisateur
	 */
	public Utilisateur(int utilisateurID, String identifiant, String motDePasse) {
		this.utilisateurID = utilisateurID;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
	}

	/**
	 * Constructeur avec le prenom
	 * 
	 * @param utilisateurID
	 * @param identifiant
	 * @param motDePasse
	 * @param prenom
	 */
	public Utilisateur(int utilisateurID, String identifiant, String motDePasse, String prenom) {
		this.utilisateurID = utilisateurID;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.utilisateurPrenom = prenom;
	}

	/**
	 * Getter de l'ID utilisateur
	 * 
	 * @return idutilisateur
	 */
	public int getUtilisateurID() {
		return utilisateurID;
	}

	/**
	 * Setter utilisateurID
	 * 
	 * @param utilisateurID
	 */
	public void setUtilisateurID(int utilisateurID) {
		this.utilisateurID = utilisateurID;
	}

	/**
	 * Getter identifiant
	 * 
	 * @return identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * Setter identifiant
	 * 
	 * @param identifiant l'identifiant
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * Getter mot de passe
	 * 
	 * @return mot de passe
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * Setter motdepasse
	 * 
	 * @param motDePasse le mot de passe
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * Getter prenom
	 * 
	 * @return prenom
	 */
	public String getUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	/**
	 * Setter prenom
	 * 
	 * @param utilisateurPrenom le prenom
	 */
	public void setUtilisateurPrenom(String utilisateurPrenom) {
		this.utilisateurPrenom = utilisateurPrenom;
	}

	/**
	 * Getter nom etudiant
	 * 
	 * @return le nom de l'etudiant
	 */
	public String getUtilisateurNom() {
		return utilisateurNom;
	}

	/**
	 * Setter utilisateur nom
	 * 
	 * @param utilisateurNom le nom de l'etudiant
	 */
	public void setUtilisateurNom(String utilisateurNom) {
		this.utilisateurNom = utilisateurNom;
	}

}