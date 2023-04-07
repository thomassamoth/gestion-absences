package model;

import java.util.*;

public class Absence {
	//protected int absenceID;
	protected String nomEtudiant;
	protected String prenomEtudiant;
	protected String date;
	protected String nomCours;

	public Absence(String nomEtudiant, String prenomEtudiant, String date, String nomCours) {
		//this.absenceID = absenceID;
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
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
	
	
}
/*JScrollPane
Jtable*/
