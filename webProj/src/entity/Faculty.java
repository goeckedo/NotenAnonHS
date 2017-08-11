package entity;
import javax.persistence.*;

@Entity
@Table(name = "faculty")
public class Faculty {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FacultyPK")
	@SequenceGenerator(name="FacultyPK", sequenceName="faculty_seq", allocationSize=1)
	private int facultyID;
	
	private String name;
	private String token;
	
	public void setFacultyID(int facultyID) {
		this.facultyID = facultyID;
	}

	public Faculty(){
		this("", "");
	}
	
	public Faculty(String name, String token) {
		this.name = name;
		this.token = token;
	}

	@Override
	public String toString() {
		return "Faculty [facultyID=" + facultyID + ", name=" + name + ", token=" + token + "]";
	}

	public int getFacultyID() {
		return facultyID;
	}

	public String getName() {
		return name;
	}

	public String getToken() {
		return token;
	}
	
	
	
}
	
	
	

