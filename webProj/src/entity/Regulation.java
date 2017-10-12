package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "regulationtable")
public class Regulation implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int regulationID;
	private String regulationOrg;
	
	public Regulation() {

	}

	public Regulation(int regulationID, String regulationOrg) {
		super();
		this.regulationID = regulationID;
		this.regulationOrg = regulationOrg;
	}

	public int getRegulationID() {
		return regulationID;
	}

	public void setRegulationID(int regulationID) {
		this.regulationID = regulationID;
	}

	public String getRegulationOrg() {
		return regulationOrg;
	}

	public void setRegulationOrg(String regulationOrg) {
		this.regulationOrg = regulationOrg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Regulation [regulationID=" + regulationID + ", regulationOrg=" + regulationOrg + "]";
	}
	
	
}
