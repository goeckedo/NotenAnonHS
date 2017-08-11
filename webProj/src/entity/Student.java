package entity;

import javax.persistence.*;


@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="StudentPK")
	@SequenceGenerator(name="StudentPK", sequenceName="student_seq", allocationSize=1)
	private int studentID;
	
	private String semester;	//Semester NR
	private String mtkNr;	 	//MatrikelNummer - Unique
	private String absch;		//Bachlor 84 --> Stud ?
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="stg", referencedColumnName="facultyID")
	private Faculty faculty; 		//FK Studigang

	public Student(){
		this("","","",null);
	}
	
	public Student(String semester, String mtknr, String abschl, Faculty faculty) {
		this.semester = semester;
		this.mtkNr = mtknr;
		this.absch = abschl;
		this.faculty = faculty;
	}


	
	public int getStudID() {
		return studentID;
	}



	public void setStudID(int studID) {
		this.studentID = studID;
	}



	public String getSemester() {
		return semester;
	}



	public void setSemester(String semester) {
		this.semester = semester;
	}



	public String getMtknr() {
		return mtkNr;
	}



	public void setMtknr(String mtknr) {
		this.mtkNr = mtknr;
	}



	public String getAbschl() {
		return absch;
	}



	public void setAbschl(String abschl) {
		this.absch = abschl;
	}



	public Faculty getFaculty() {
		return faculty;
	}



	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	@Override
	public String toString() {
		return "Student [studID=" + studentID + ", semester=" + semester + ", mtknr=" + mtkNr + ", abschl=" + absch
				+ ", facultyID=" + faculty.toString() + "]";
	}
		
}
