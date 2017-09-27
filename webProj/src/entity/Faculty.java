package entity;
import javax.persistence.*;

@Entity
@Table(name = "facultytable")
public class Faculty {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FacultyPK")
	@SequenceGenerator(name="FacultyPK", sequenceName="faculty_seq", allocationSize=1)
	private int facultyID;
	
	private String facultyName;
	private String facultyToken;
	
	public void setFacultyID(int facultyID) {
		this.facultyID = facultyID;
	}

	public Faculty(){
		this("", "");
	}
	
	public Faculty(String name, String token) {
		this.facultyName = name;
		this.facultyToken = token;
	}

	@Override
	public String toString() {
		return "Faculty [facultyID=" + facultyID + ", name=" + facultyName + ", token=" + facultyToken + "]";
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
	
	
	
}
	
	
	

