package model;

public class Gestionnaire extends Utilisateur {
	
	private int utilisateurID; 
	private String identifiant;
	private String motdepasse;
	
	/**
	 * Constructeur Gestionnaire
	 * @param utilisateurID
	 * @param identifiant
	 * @param motDePasse
	 */
	public Gestionnaire(int utilisateurID, String identifiant, String motDePasse){
		super(utilisateurID, identifiant, motDePasse);

	}
}
