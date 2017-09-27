package entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "examtable")
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExamPK")
	@SequenceGenerator(name = "ExamPK", sequenceName = "exam_seq", allocationSize = 1)
	private int examID; // PK exam

	private Date pdatum; // Prüfungsdatum
	private String pNR; // Prüfungs Nummer
	private String pversion; // version
	private String sortname; // bezeichner

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "stg", referencedColumnName = "facultyID")
	private Faculty faculty; // FK Studigang

	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private User_ user; // FK User/Prüfer

	public Exam() {
		this(new Date(), "", "", "", null, null);
	}

	public Exam(Date pdatum, String pNR, String pversion, String sortname, Faculty faculty, User_ user) {
		this.pdatum = pdatum;
		this.pNR = pNR;
		this.pversion = pversion;
		this.sortname = sortname;
		this.faculty = faculty;
		this.user = user;
	}

	public int getExamID() {
		return examID;
	}

	public void setExamID(int examID) {
		this.examID = examID;
	}

	public Date getPdatum() {
		return pdatum;
	}

	public void setPdatum(Date pdatum) {
		this.pdatum = pdatum;
	}

	public String getpNR() {
		return pNR;
	}

	public void setpNR(String pNR) {
		this.pNR = pNR;
	}

	public String getPversion() {
		return pversion;
	}

	public void setPversion(String pversion) {
		this.pversion = pversion;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public User_ getUser() {
		return user;
	}

	public void setUser(User_ user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Exam [examID=" + examID + ", pdatum=" + pdatum + ", pNR=" + pNR + ", pversion=" + pversion
				+ ", sortname=" + sortname + ", faculty=" + faculty.toString() + ", user=" + user.toString() + "]";
	}

}
