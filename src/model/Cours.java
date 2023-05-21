package model;
import java.sql.Timestamp;

public class Cours {
	
	protected String nomCours;
	protected int idCours;
	protected Timestamp horaireCours; 
	protected int idGroupe;
	
	
	public Cours (String nomCours, int idCours ,Timestamp horaireCours, int idGroupe)
	{
		this.nomCours=nomCours;
		this.idCours=idCours;
		this.horaireCours=horaireCours;
		this.idGroupe=idGroupe;
	}
	
	public Timestamp getHoraireCours()
	{
		return horaireCours;
	}
	public int getIdGroupe()
	{
		return idGroupe;
	}
	
	public String getNomCours() {
		return nomCours;
	}
	public void setNomCours(String nomCours) {
		this.nomCours = nomCours;
	}
	public int getIdCours() {
		return idCours;
	}
	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}
}


