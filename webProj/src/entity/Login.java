package entity;
import javax.persistence.*;

@Entity
@Table(name = "login")
public class Login {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LoginPK")
	@SequenceGenerator(name="LoginPK", sequenceName="login_seq", allocationSize=1)
	private int loginID;
	
	private String lusername;
	private String lpassword;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="facultyID", referencedColumnName="facultyID")
	private Faculty faculty; // FK Faculty
	private String aktiv;
	
	
	
	public Login() {
		this("","",null);
	}


	public Login( String username, String password, Faculty faculty) {
		this.aktiv = "1";
		this.lusername = username;
		this.lpassword = password;
		this.faculty = faculty;
	}


	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}


	public void setLusername(String lusername) {
		this.lusername = lusername;
	}


	public void setLpassword(String lpassword) {
		this.lpassword = lpassword;
	}


	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}


	public void setAktiv(String aktiv) {
		this.aktiv = aktiv;
	}


	public int getLoginID() {
		return loginID;
	}


	public String getUsername() {
		return lusername;
	}


	public String getPassword() {
		return lpassword;
	}


	public Faculty getFaculty() {
		return faculty;
	}
	
	public String getAktiv(){
		return aktiv;
	}


	@Override
	public String toString() {
		return "Login [loginID=" + loginID + ", username=" + lusername + ", password=" + lpassword + ", faculty="
				+ faculty.toString() + "]";
	}

	
}
