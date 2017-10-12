package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "graduationtable")
public class Graduation implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int graduationID;
	private String graduationName;
	
	public Graduation() {

	}

	public Graduation(int graduationID, String graduationName) {
		super();
		this.graduationID = graduationID;
		this.graduationName = graduationName;
	}

	public int getGraduationID() {
		return graduationID;
	}

	public void setGraduationID(int graduationID) {
		this.graduationID = graduationID;
	}

	public String getGraduationName() {
		return graduationName;
	}

	public void setGraduationName(String graduationName) {
		this.graduationName = graduationName;
	}

	@Override
	public String toString() {
		return "Graduation [graduationID=" + graduationID + ", graduationName=" + graduationName + "]";
	}
	
}
