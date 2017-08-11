package entity;
import javax.persistence.*;

@Entity
@Table(name = "user_")
public class User_ {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UserPK")
	@SequenceGenerator(name="UserPK", sequenceName="user_seq", allocationSize=1)
	private int userID;
	
	private String name;
	private String personalnummer;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="facultyID", referencedColumnName="facultyID")
	private Faculty faculty;
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REMOVE} )
	@JoinColumn(name="loginID", referencedColumnName="loginID")
	private Login login;

	public User_() {
		this("","",null,null);
	}

	public User_( String name, String personalnummer, Faculty faculty, Login login) {
		this.name = name;
		this.personalnummer = personalnummer;
		this.faculty = faculty;
		this.login = login;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonalnummer() {
		return personalnummer;
	}

	public void setPersonalnummer(String personalnummer) {
		this.personalnummer = personalnummer;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", name=" + name + ", personalnummer=" + personalnummer + ", faculty="
				+ faculty.toString() + ", login=" + login.toString() + "]";
	}
	
}
	
	
	


