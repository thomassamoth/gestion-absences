package model;

public class Utilisateur{
	protected int utilisateurID;
	protected String utilisateurPrenom;
	//protected String utilisateurNom;
	protected String identifiant;
	protected String motDePasse;

	/**
	 * Constructor
	 * @param utilisateurID
	 * @param utilisateurPrenom
	 * @param utilisateurNom
	 */
	public Utilisateur(int utilisateurID, String identifiant, String motDePasse) {
		this.utilisateurID = utilisateurID;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
	}
	
	/**
	 * Constructeur avec le prenom
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
	
	public int getUtilisateurID() {
		return utilisateurID;
	}
	public void setUtilisateurID(int utilisateurID) {
		this.utilisateurID = utilisateurID;
	}
	
	public String getIdentifiant() {
		return identifiant;
	}
	
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	public void setUtilisateurPrenom(String utilisateurPrenom) {
		this.utilisateurPrenom = utilisateurPrenom;
	}
	
	
	
	
}