package model;

import java.util.*;

/**
 * Classe absence
 * @author Thomas
 *
 */
public class Absence {
	//protected int absenceID;
	protected String nomEtudiant;
	protected String prenomEtudiant;
	protected String date;
	protected String nomCours;
	protected int duree;

	/**
	 * Constructeur pour les absences non traitées
	 * @param nomEtudiant
	 * @param prenomEtudiant
	 * @param date
	 * @param nomCours
	 */
	public Absence(String nomEtudiant, String prenomEtudiant, String date, String nomCours) {
		//this.absenceID = absenceID;
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
		this.date = date;
		this.nomCours = nomCours;
	}
	
	/**
	 * Constructeur pour les absences, côté élèves
	 * @param duree
	 * @param date
	 * @param nomCours
	 */

	public Absence(int duree, String date, String nomCours) {
		this.duree = duree;
		this.date = date;
		this.nomCours = nomCours;
	}

	public String getNomEtudiant() {
		return nomEtudiant;
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
}
