package model;

//import java.util.*;

/**
 * Classe absence
 * @author Thomas Beyet
 * @author Walid Ben Attia
 *
 */

/*protected String nomEtudiant;
protected String prenomEtudiant;
protected String date;
protected String nomCours;
protected int duree;
 */



public class Absence {
	private int idabsence;
	private String nomEtudiant;
	private String prenomEtudiant;
	private String date;
	private String nomCours;
	private int duree;
	protected int justified;
	protected int handled;

	/**
	 * Constructeur pour les absences non traitées
	 * @param nomEtudiant
	 * @param prenomEtudiant
	 * @param date
	 * @param nomCours
	 */
	public Absence(String nomEtudiant, String prenomEtudiant, String date, String nomCours , int justified , int handled) {
		//this.absenceID = absenceID;
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
		this.date = date;
		this.nomCours = nomCours;
		this.justified = justified;
		this.handled = handled;
	}

	/**
	 * Constructeur pour les absences, côté élèves
	 * @param idabsence
	 * @param duree
	 * @param date
	 * @param nomCours
	 */
	public Absence(int idabsence,int duree, String date, String nomCours) {
		this.idabsence = idabsence;
		this.duree = duree;
		this.date = date;
		this.nomCours = nomCours;
	}

	public String getNomEtudiant() {
		return nomEtudiant;
	}

	public int getIDAbsence(){
		return idabsence;
	}

	public int  getIsHandled()
	{
		return handled;
	}
	public int getIsJustified()
	{
		return justified;
	}
	
	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}

	public String getPrenomEtudiant() {
		return prenomEtudiant;
	}

	public void setPrenomEtudiant(String prenomEtudiant) {
		this.prenomEtudiant = prenomEtudiant;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNomCours() {
		return nomCours;
	}

	public void setNomCours(String nomCours) {
		this.nomCours = nomCours;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	/**
	 * Affiche l'absence sous forme de chaine de caractères
	 * @return les éléments de l'absence comme chaine
	 */
	public String displayToString() {
		return nomCours + " - " + date + " - " + duree + " Heures";
	}
}