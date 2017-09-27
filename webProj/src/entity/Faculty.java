package entity;

import javax.persistence.*;

@Entity
@Table(name = "facultytable")
public class Faculty {

	@Id
	private int facultyID;
	private String facultyName;
	private String facultyToken;

	public Faculty() {

	}

	public Faculty(String name, String token) {
		this.facultyName = name;
		this.facultyToken = token;
	}

	public int getFacultyID() {
		return facultyID;
	}

	public String getName() {
		return facultyName;
	}

	public String getToken() {
		return facultyToken;
	}

	@Override
	public String toString() {
		return "Faculty [facultyID=" + facultyID + ", name=" + facultyName + ", token=" + facultyToken + "]";
	}

}
