package model;

/**
 * Classe pour le modèle des absences
 * 
 * @author Thomas Beyet
 * @author Walid Ben Attia
 * 
 */
public class Absence {
	private int idabsence;
	private String nomEtudiant;
	private String prenomEtudiant;
	private String date;
	private String nomCours;
	private int duree;
	private int justified;
	private int handled;

	/**
	 * Constructeur pour les absences non traitées
	 * 
	 * @param nomEtudiant    le nom de l'etudiant
	 * @param prenomEtudiant le prenom de l'etudiant
	 * @param date           la date de l'absence
	 * @param nomCours       le nom du cours
	 * @param justified      le statut justifié de l'absence
	 * @param handled        si l'absence est prises en charge
	 */
	public Absence(String nomEtudiant, String prenomEtudiant, String date, String nomCours, int justified,
			int handled) {
		// this.absenceID = absenceID;
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
		this.date = date;
		this.nomCours = nomCours;
		this.justified = justified;
		this.handled = handled;
	}

	/**
	 * Constructeur pour les absences, c&ocirc;t&eacute; &eacute;l&egrave;ves
	 * 
	 * @param idabsence l'id de l'absence
	 * @param duree     la dur&eacute;e de l'absence
	 * @param date      la date de l'absence
	 * @param nomCours  le nom du cours
	 */
	public Absence(int idabsence, int duree, String date, String nomCours) {
		this.idabsence = idabsence;
		this.duree = duree;
		this.date = date;
		this.nomCours = nomCours;
	}

	/**
	 * Getter pour le nom de l'etudiant
	 * 
	 * @return le nom de l'etudiant
	 */
	public String getNomEtudiant() {
		return nomEtudiant;
	}

	/**
	 * Récupère l'ID de l'absence
	 * 
	 * @return l'ID de l'absence
	 */
	public int getIDAbsence() {
		return idabsence;
	}

	/**
	 * Récupère si l'absence est traitée ou non
	 * 
	 * @return si l'absence est traitée
	 */
	public int getIsHandled() {
		return handled;
	}

	/**
	 * Récupère si l'absence est justifi&eacute;e
	 * 
	 * @return si l'absence est justifi&eacute;e
	 */
	public int getIsJustified() {
		return justified;
	}

	/**
	 * Setter pour le nom de l'&eacute;tudiant
	 * 
	 * @param nomEtudiant le nom de l'&eacute;tudiant
	 */
	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}

	/**
	 * Getter pour le prenom de l'&eacute;tudiant
	 * 
	 * @return le pr&eacute;nom de l'&eacute;tudiant
	 */
	public String getPrenomEtudiant() {
		return prenomEtudiant;
	}

	/**
	 * Getter pour le pr&eacute;nom de l'etudiant
	 * 
	 * @param prenomEtudiant le prenom de l'etudiant
	 */
	public void setPrenomEtudiant(String prenomEtudiant) {
		this.prenomEtudiant = prenomEtudiant;
	}

	/**
	 * Getter de la date de l'absence
	 * 
	 * @return la date de l'absence
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Setter de la date de l'absence
	 * 
	 * @param date la date de l'absence
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Getter du nom du cours
	 * 
	 * @return le nom du cours
	 */
	public String getNomCours() {
		return nomCours;
	}

	/**
	 * Setter pour le nom du cours
	 * 
	 * @param nomCours le nom du cours
	 */
	public void setNomCours(String nomCours) {
		this.nomCours = nomCours;
	}

	/**
	 * Getter de la dur&eacute;e de l'absence
	 * 
	 * @return la dur&eacute;e de l'absence
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * Setter de la duree de l'absence
	 * 
	 * @param duree la duree de l'absence
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}

	/**
	 * Affiche l'absence sous forme de chaine de caractères
	 * 
	 * @return les éléments de l'absence comme chaine
	 */
	public String displayToString() {
		return nomCours + " - " + date + " - " + duree + " Heures";
	}
}