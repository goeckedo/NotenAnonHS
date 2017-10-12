package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "modultable")
public class Modul implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int modulID;
	private String modulName;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private int userFK;
	
	public Modul() {

	}

	public Modul(int modulID, String modulName, int userFK) {
		super();
		this.modulID = modulID;
		this.modulName = modulName;
		this.userFK = userFK;
	}

	public int getModulID() {
		return modulID;
	}

	public void setModulID(int modulID) {
		this.modulID = modulID;
	}

	public String getModulName() {
		return modulName;
	}

	public void setModulName(String modulName) {
		this.modulName = modulName;
	}

	public int getUserFK() {
		return userFK;
	}

	public void setUserFK(int userFK) {
		this.userFK = userFK;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Modul [modulID=" + modulID + ", modulName=" + modulName + ", userFK=" + userFK + "]";
	}
	
}
